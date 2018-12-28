package com.jichen.redblueball.etl.service;

import com.jichen.redblueball.etl.mapper.HistoryEtlMapper;
import com.jichen.redblueball.model.History;
import com.jichen.redblueball.model.HistorySum;
import org.easymock.EasyMockRunner;
import org.easymock.EasyMockSupport;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static com.jichen.redblueball.model.common.HistoryBuilder.aHistory;
import static java.util.Collections.singletonList;
import static org.easymock.EasyMock.expectLastCall;

@RunWith(EasyMockRunner.class)
public class HistorySumEtlTest extends EasyMockSupport {

    @TestSubject
    private HistorySumEtl sumEtl = new HistorySumEtl();

    @Mock
    private HistoryEtlMapper mapper;

    @Test
    public void shouldEtlSuccessfully() {
        List<History> historyList = singletonList(aHistory().withRedBalls(3, 6, 12, 19, 30, 31).withBlueBall(13).withNumber(2015113).build());
        HistorySum historySum = new HistorySum(2015113, 114);
        mapper.insertHistorySum(historySum);
        expectLastCall().times(1);

        replayAll();
        sumEtl.etl(historyList);
        verifyAll();
    }

}