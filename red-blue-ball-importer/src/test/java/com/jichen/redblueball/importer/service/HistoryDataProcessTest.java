package com.jichen.redblueball.importer.service;

import com.jichen.redblueball.common.model.History;
import org.junit.Test;

import java.util.List;

import static com.jichen.redblueball.common.HistoryBuilder.aHistory;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class HistoryDataProcessTest {

    @Test
    public void couldReturnHistoryListWhenGivenDataList() {
        HistoryDataProcess process = new HistoryDataProcess();
        List<String> dataList =
                asList("1\t2010153\t2010-12-09\t03,06,12,19,30,31\\13",
                        "2\t2010152\t2010-12-13\t04,09,17,21,25,31\\01");
        List<History> expectHistoryList = asList(aHistory().withNumber(2010153).withDate("2010-12-09")
                        .withRedBalls(3, 6, 12, 19, 30, 31).withBlueBall(13).build(),
                aHistory().withNumber(2010152).withDate("2010-12-13")
                        .withRedBalls(4, 9, 17, 21, 25, 31).withBlueBall(1).build());
        assertThat(process.process(dataList), is(expectHistoryList));
    }

    @Test
    public void couldReturnHistoryWithOutDateWhenDateParseException() {
        HistoryDataProcess process = new HistoryDataProcess();
        List<String> dataList = singletonList("1\t2010153\t20101209\t03,06,12,19,30,31\\13");
        List<History> expectHistoryList = singletonList(aHistory().withNumber(2010153).withRedBalls(3, 6, 12, 19, 30, 31)
                .withBlueBall(13).build());
        assertThat(process.process(dataList), is(expectHistoryList));
    }
}