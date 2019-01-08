package com.jichen.redblueball.etl.service.history;

import com.jichen.redblueball.common.annotations.Etl;
import com.jichen.redblueball.etl.mapper.HistoryEtlMapper;
import com.jichen.redblueball.common.model.History;
import com.jichen.redblueball.common.model.HistorySum;
import com.jichen.redblueball.etl.service.HistoryEtlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@Etl(name = "historySumEtl")
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
