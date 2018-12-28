package com.jichen.redblueball.etl.service;


import com.jichen.redblueball.model.History;

import java.util.List;

public interface HistoryEtlService {

    boolean etl(List<History> historyList);

}
