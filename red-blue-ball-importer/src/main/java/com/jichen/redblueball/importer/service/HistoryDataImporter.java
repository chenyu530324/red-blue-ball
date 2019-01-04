package com.jichen.redblueball.importer.service;

import com.jichen.redblueball.common.model.History;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

@Service
public class HistoryDataImporter {

    private static final Logger LOGGER = LoggerFactory.getLogger(HistoryDataImporter.class);

    @Autowired
    private HistoryDataReader reader;

    @Autowired
    private HistoryDataProcess process;

    @Autowired
    private HistoryDataWriter writer;

    public void importHistoryData(String[] args) {
        for (String year : args) {
            if (isNotBlank(year)) {
                try {
                    importAnnualHistoryData(year);
                } catch (IOException e) {
                    LOGGER.error("Import history data error : " + e.getMessage());
                    continue;
                }
            }
        }
    }

    private void importAnnualHistoryData(String year) throws IOException {
        List<String> dataLineList = reader.read(year);
        List<History> historyList = this.process.process(dataLineList);
        writer.write(historyList);
    }
}
