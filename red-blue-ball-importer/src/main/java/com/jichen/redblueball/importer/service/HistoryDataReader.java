package com.jichen.redblueball.importer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

@Component
public class HistoryDataReader {

    private static final Logger LOGGER = LoggerFactory.getLogger(HistoryDataReader.class);

    @Value("${history.data.local.directory}")
    private String historyDataFileDirectory;

    @Value("${history.data.file.name}")
    private String historyDataFileName;

    @Value("${history.data.file.suffix}")
    private String historyDataFileSuffix = ".txt";


    public List<String> read(String year) throws IOException {
        File historyDataFile = new File(getFilePath(year));
        if (!historyDataFile.exists()) {
            LOGGER.error("File " + historyDataFile.getName() + "doesn't exist");
            return newArrayList();
        }
        return readFileAsLineList(historyDataFile);
    }

    private List<String> readFileAsLineList(File historyDataFile) throws IOException {
        List<String> annualDataList = newArrayList();
        try (FileReader fileReader = new FileReader(historyDataFile);
             BufferedReader reader = new BufferedReader(fileReader)) {
            String data = reader.readLine();
            while (isNotBlank(data)) {
                annualDataList.add(data);
                data = reader.readLine();
            }
            return annualDataList;
        }
    }

    private String getFilePath(String year) {
        return historyDataFileDirectory + File.separator + historyDataFileName + year + historyDataFileSuffix;
    }
}
