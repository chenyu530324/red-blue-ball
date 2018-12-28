package com.jichen.redblueball.killer.service.redballkiller;

import com.jichen.redblueball.killer.service.KillerService;
import com.jichen.redblueball.model.Ball;
import com.jichen.redblueball.model.History;
import org.springframework.stereotype.Component;

import java.util.Set;

import static com.jichen.redblueball.model.common.BallFactory.createRedBall;
import static java.util.Collections.singleton;

@Component
public class RedBallSumKiller implements KillerService<History> {

    @Override
    public Set<Ball> kill(History history) {
        int redBallSum = history.getRedBallSum();
        String sumValue = String.valueOf(redBallSum);
        int sum = sumValue.chars().map(Character::getNumericValue).sum();
        return singleton(createRedBall(sum));
    }

}