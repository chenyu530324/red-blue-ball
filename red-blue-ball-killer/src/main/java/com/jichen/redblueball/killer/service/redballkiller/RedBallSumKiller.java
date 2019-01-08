package com.jichen.redblueball.killer.service.redballkiller;

import com.jichen.redblueball.common.model.History;
import com.jichen.redblueball.killer.service.KillerService;
import org.springframework.stereotype.Component;

import java.util.Set;

import static java.util.Collections.singleton;

@Component
public class RedBallSumKiller implements KillerService<History> {

    @Override
    public Set<Integer> kill(History history) {
        int redBallSum = history.getRedBallSum();
        String sumValue = String.valueOf(redBallSum);
        int sum = sumValue.chars().map(Character::getNumericValue).sum();
        return singleton(sum);
    }

}