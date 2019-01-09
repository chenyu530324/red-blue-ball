package com.jichen.redblueball.killer.service.redballkiller;

import com.jichen.redblueball.common.annotations.Killer;
import com.jichen.redblueball.common.model.History;
import com.jichen.redblueball.killer.service.KillerService;
import org.springframework.stereotype.Service;

import java.util.Set;

import static com.jichen.redblueball.common.BallType.RED;
import static java.util.Collections.singleton;

@Service
@Killer(name = "redBallSumKiller", type = RED)
public class RedBallSumKiller implements KillerService {

    @Override
    public Set<Integer> kill(History history) {
        int redBallSum = history.getRedBallSum();
        String sumValue = String.valueOf(redBallSum);
        int sum = sumValue.chars().map(Character::getNumericValue).sum();
        return singleton(sum);
    }

}