package com.jichen.redblueball.importer.service;

import com.jichen.redblueball.common.model.History;
import org.easymock.EasyMockRunner;
import org.easymock.EasyMockSupport;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.List;

import static com.jichen.redblueball.common.HistoryBuilder.aHistory;
import static java.util.Arrays.asList;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;

@RunWith(EasyMockRunner.class)
public class HistoryDataImporterTest extends EasyMockSupport {

    @TestSubject
    private HistoryDataImporter importer = new HistoryDataImporter();

    @Mock
    private HistoryDataReader reader;

    @Mock
    private HistoryDataProcess process;

    @Mock
    private HistoryDataWriter writer;

    @Test
    public void couldImportHistoryDataWhenGivenYear() throws IOException {
        String[] years = {"test"};
        List<String> expectDataList =
                asList("1\t2010153\t2010-12-09\t03,06,12,19,30,31\\13",
                        "2\t2010152\t2010-12-13\t04,09,17,21,25,31\\01");
        List<History> expectHistoryList = asList(aHistory().withNumber(2010153).withDate("2010-12-09")
                        .withRedBalls(3, 6, 12, 19, 30, 31).withBlueBall(13).build(),
                aHistory().withNumber(2010152).withDate("2010-12-13")
                        .withRedBalls(4, 9, 17, 21, 25, 31).withBlueBall(1).build());
        expect(reader.read("test")).andReturn(expectDataList);
        expect(process.process(expectDataList)).andReturn(expectHistoryList);
        writer.write(expectHistoryList);
        expectLastCall().times(1);

        replayAll();
        importer.importHistoryData(years);
        verifyAll();
    }

    @Test
    public void couldNotImportHistoryDataWhenGivenBlankYear() {
        String[] years = {""};
        replayAll();
        importer.importHistoryData(years);
        verifyAll();
    }

    @Test
    public void couldContinueReadNextFileWhenCatchException() throws IOException {
        String[] years = {"2010", "test"};
        List<String> expectDataList =
                asList("1\t2010153\t2010-12-09\t03,06,12,19,30,31\\13",
                        "2\t2010152\t2010-12-13\t04,09,17,21,25,31\\01");
        List<History> expectHistoryList = asList(aHistory().withNumber(2010153).withDate("2010-12-09")
                        .withRedBalls(3, 6, 12, 19, 30, 31).withBlueBall(13).build(),
                aHistory().withNumber(2010152).withDate("2010-12-13")
                        .withRedBalls(4, 9, 17, 21, 25, 31).withBlueBall(1).build());
        expect(reader.read("2010")).andThrow(new IOException());
        expect(reader.read("test")).andReturn(expectDataList);
        expect(process.process(expectDataList)).andReturn(expectHistoryList);
        writer.write(expectHistoryList);
        expectLastCall().times(1);

        replayAll();
        importer.importHistoryData(years);
        verifyAll();
    }


}