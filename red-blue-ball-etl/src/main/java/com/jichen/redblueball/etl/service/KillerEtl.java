package com.jichen.redblueball.etl.service;

<<<<<<< HEAD
import com.jichen.redblueball.etl.configuration.ClassScannerService;
import com.jichen.redblueball.model.History;
import com.jichen.redblueball.model.annotations.Killer;
=======
import com.jichen.redblueball.model.History;
import com.jichen.redblueball.model.annotations.Killer;
import com.jichen.redblueball.model.annotations.Predictor;
>>>>>>> 2ce36f2e09cb4bc0aa03aafcb5e1c6b3c4b2350d
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
<<<<<<< HEAD
        return true;
=======
        List<Predictor> predictList = classScannerService.scanPredictors();
        killerList.forEach(killer -> System.out.println(killer.name()));
        predictList.forEach(predictor -> System.out.println(predictor.name()));
        return false;
>>>>>>> 2ce36f2e09cb4bc0aa03aafcb5e1c6b3c4b2350d
    }


}
