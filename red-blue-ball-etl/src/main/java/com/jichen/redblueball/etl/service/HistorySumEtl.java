package com.jichen.redblueball.etl.service;

import com.jichen.redblueball.etl.mapper.HistoryEtlMapper;
import com.jichen.redblueball.common.model.History;
import com.jichen.redblueball.common.model.HistorySum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class HistorySumEtl implements HistoryEtlService {

    @Autowired
    private HistoryEtlMapper mapper;

    @Override
    public boolean etl(List<History> historyList) {
        List<HistorySum> historySumList = historyList.stream().map(this::createHistorySum).collect(toList());
        historySumList.forEach(historySum -> mapper.insertHistorySum(historySum));
        return true;
    }

    private HistorySum createHistorySum(History history) {
        return new HistorySum(history.getNumber(), history.getSum());
    }
}
