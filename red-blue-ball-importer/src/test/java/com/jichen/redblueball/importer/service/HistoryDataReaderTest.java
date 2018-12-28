package com.jichen.redblueball.importer.service;


import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.springframework.test.util.ReflectionTestUtils.setField;

public class HistoryDataReaderTest {

    private HistoryDataReader reader = new HistoryDataReader();
    private String historyDataLocalDirectory = "/Users/jichen/workspace/github/red-blue-ball/history-data";
    private String historyDataFileName = "ssqtxt_result";
    private String historyDataFileSuffix = ".txt";


    @Before
    public void setUp() {
        setField(reader, "historyDataFileDirectory", historyDataLocalDirectory);
        setField(reader, "historyDataFileName", historyDataFileName);
        setField(reader, "historyDataFileSuffix", historyDataFileSuffix);
    }

    @Test
    public void couldReadHistoryDataFileWhenGivenTestAsYear() throws IOException {
        List<String> lineDataList = reader.read("test");
        List<String> expectDataList =
                asList("1\t2010153\t2010-12-09\t03,06,12,19,30,31\\13",
                        "2\t2010152\t2010-12-13\t04,09,17,21,25,31\\01");
        assertThat(lineDataList, is(expectDataList));
    }

    @Test
    public void couldReturnEmptyListWhenFileDoesNotExist() throws IOException {
        List<String> lineDataList = reader.read("NOT_EXIST");
        assertThat(lineDataList, is(Collections.<String>emptyList()));
    }
}