package com.jichen.redblueball.importer.service;

import com.jichen.redblueball.importer.mapper.HistoryMapper;
import com.jichen.redblueball.common.model.History;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class HistoryDataWriter {

    @Autowired
    private HistoryMapper historyMapper;

    public void write(List<History> historyList) {
        historyList.forEach(history -> historyMapper.insertHistory(history));
    }
}
