package com.jichen.redblueball.etl.service.history;

import com.jichen.redblueball.common.model.History;

import java.util.List;

public interface HistoryEtlService {

    boolean etl(List<History> historyList);
}
