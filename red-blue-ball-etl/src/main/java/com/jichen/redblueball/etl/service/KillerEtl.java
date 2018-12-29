package com.jichen.redblueball.etl.service;

import com.jichen.redblueball.model.History;
import com.jichen.redblueball.model.annotations.Killer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KillerEtl implements HistoryEtlService {

    @Autowired
    private ClassScannerService classScannerService;

    @Override
    public boolean etl(List<History> historyList) {
        List<Killer> killerList = classScannerService.scanKillers();
        return true;
    }


}
