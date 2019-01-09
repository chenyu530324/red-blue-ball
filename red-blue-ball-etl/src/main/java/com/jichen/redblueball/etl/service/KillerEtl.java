package com.jichen.redblueball.etl.service;

import com.jichen.redblueball.common.BallType;
import com.jichen.redblueball.common.annotations.Killer;
import com.jichen.redblueball.common.model.ArithmeticEtl;
import com.jichen.redblueball.common.model.History;
import com.jichen.redblueball.etl.configuration.ClassScannerService;
import com.jichen.redblueball.etl.mapper.HistoryEtlMapper;
import com.jichen.redblueball.killer.service.KillerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.google.common.collect.Lists.newArrayList;
import static java.lang.String.format;

@Service
public class KillerEtl implements EtlService {

    private static final Logger LOGGER = LoggerFactory.getLogger(KillerEtl.class);

    @Autowired
    private ClassScannerService classScannerService;

    @Autowired
    private HistoryEtlMapper mapper;

    @Override
    public boolean etl(ConfigurableApplicationContext ctx) {
        List<Killer> killers = classScannerService.scanKillers();

        LOGGER.info(format("Identified killer size is %s ", killers.size()));
        List<History> histories = mapper.queryHistoryData();
        for (Killer killer : killers) {
            String killerName = killer.name();
            BallType killerType = killer.type();
            LOGGER.info(format("Start etl killer : %s , type is : %s", killerName, killerType));
            mapper.deleteExistArithmeticEtlResult(killerName, killerType);
            List<ArithmeticEtl> arithmeticEtlList = executeKillEtl(ctx, histories, killerName, killerType);
            List<ArithmeticEtl> successResults = arithmeticEtlList.stream().filter(ArithmeticEtl::isResult).collect(Collectors.toList());
            double rate = (double) successResults.size() / (double) histories.size();
            LOGGER.info(format("Killer %s success rate is %s", killerName, rate));
            arithmeticEtlList.forEach(arithmeticEtl -> mapper.insertArithmetic(arithmeticEtl));
            LOGGER.info(format("finished killer : %s etl , type is: %s", killerName, killerType));
        }
        return true;
    }

    private List<ArithmeticEtl> executeKillEtl(ConfigurableApplicationContext ctx, List<History> histories, String killerName, BallType killerType) {
        KillerService killerService = (KillerService) ctx.getBean(killerName);
        int index = 0;
        List<ArithmeticEtl> arithmeticEtlList = newArrayList();
        while (index < histories.size() - 1) {
            arithmeticEtlList.add(createArithmeticEtl(killerName, killerType, histories, killerService, index));
            index++;
        }
        return arithmeticEtlList;
    }

    private ArithmeticEtl createArithmeticEtl(String killerName, BallType killerType, List<History> histories,
                                              KillerService killerService, int index) {
        History currentHistory = histories.get(index);
        History nextHistory = histories.get(index + 1);
        Set<Integer> killNumbers = killerService.kill(currentHistory);
        ArithmeticEtl arithmetic = new ArithmeticEtl();
        arithmetic.setArithmeticName(killerName);
        arithmetic.setBallType(killerType);
        arithmetic.setArithmeticResult(buildArithmeticResult(killNumbers));
        arithmetic.setCurrentBalls(currentHistory.toString());
        arithmetic.setCurrentNumber(currentHistory.getNumber());
        arithmetic.setResult(nextHistory.notExistBalls(killerType, killNumbers));
        return arithmetic;
    }

    private String buildArithmeticResult(Set<Integer> killNumbers) {
        StringBuilder sb = new StringBuilder();
        killNumbers.forEach(ball -> sb.append(ball.intValue()).append(" "));
        return sb.toString();
    }

}
