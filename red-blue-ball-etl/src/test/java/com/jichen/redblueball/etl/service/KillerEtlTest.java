package com.jichen.redblueball.etl.service;

import com.jichen.redblueball.common.annotations.Killer;
import com.jichen.redblueball.common.model.History;
import com.jichen.redblueball.etl.configuration.ClassScannerService;
import com.jichen.redblueball.etl.mapper.HistoryEtlMapper;
import com.jichen.redblueball.killer.service.KillerService;
import org.easymock.EasyMockRunner;
import org.easymock.EasyMockSupport;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.jichen.redblueball.common.BallType.RED;
import static com.jichen.redblueball.common.HistoryBuilder.aHistory;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.easymock.EasyMock.anyObject;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;

@RunWith(EasyMockRunner.class)
public class KillerEtlTest extends EasyMockSupport {

    @TestSubject
    private KillerEtl killerEtl = new KillerEtl();

    @Mock
    private ClassScannerService classScannerService;

    @Mock
    private HistoryEtlMapper mapper;

    @Mock
    private Killer killer;

    @Mock
    private ConfigurableApplicationContext ctx;

    @Mock
    private KillerService killerService;

    @Test
    public void couldEtlKiller() {
        expect(classScannerService.scanKillers()).andReturn(getKillers());
        String killerName = "mock killer";
        expect(mapper.queryHistoryData()).andReturn(getHistories());
        expect(killer.name()).andReturn(killerName);
        expect(killer.type()).andReturn(RED);
        mapper.deleteExistArithmeticEtlResult(killerName, RED);
        expectLastCall().times(1);
        expect(ctx.getBean(killerName)).andReturn(killerService);
        expect(killerService.kill(getHistories().get(0))).andReturn(getKillNumberSet());
        mapper.insertArithmetic(anyObject());
        expectLastCall().times(1);
        replayAll();
        killerEtl.etl(ctx);
        verifyAll();
    }

    private Set<Integer> getKillNumberSet() {
        return new HashSet<>(singletonList(7));
    }

    private List<Killer> getKillers() {
        return singletonList(killer);
    }

    private List<History> getHistories() {
        History history1 = aHistory().withRedBalls(3, 6, 8, 19, 29, 30).withBlueBall(5).build();
        History history2 = aHistory().withRedBalls(5, 7, 13, 19, 27, 33).withBlueBall(9).build();
        return asList(history1, history2);
    }

}