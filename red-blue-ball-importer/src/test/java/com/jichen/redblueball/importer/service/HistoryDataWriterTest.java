package com.jichen.redblueball.importer.service;

import com.jichen.redblueball.importer.mapper.HistoryMapper;
import com.jichen.redblueball.common.model.History;
import org.easymock.EasyMockRunner;
import org.easymock.EasyMockSupport;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static com.jichen.redblueball.common.HistoryBuilder.aHistory;
import static java.util.Arrays.asList;
import static org.easymock.EasyMock.expectLastCall;

@RunWith(EasyMockRunner.class)
public class HistoryDataWriterTest extends EasyMockSupport {

    @TestSubject
    private HistoryDataWriter writer = new HistoryDataWriter();

    @Mock
    private HistoryMapper mapper;

    @Test
    public void couldWriteHistoryDataByMapper() {
        List<History> historyList = asList(aHistory().withNumber(2010153).withDate("2010-12-09")
                        .withRedBalls(3, 6, 12, 19, 30, 31).withBlueBall(13).build(),
                aHistory().withNumber(2010152).withDate("2010-12-13")
                        .withRedBalls(4, 9, 17, 21, 25, 31).withBlueBall(1).build());
        mapper.insertHistory(historyList.get(0));
        mapper.insertHistory(historyList.get(1));
        expectLastCall().times(1);
        replayAll();
        writer.write(historyList);
        verifyAll();
    }

}