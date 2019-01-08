package com.jichen.redblueball.predictor.service.redballpredictor;

import com.jichen.redblueball.common.annotations.Predictor;
import com.jichen.redblueball.common.model.History;
import com.jichen.redblueball.predictor.service.PredictService;
import org.springframework.stereotype.Component;

import java.util.Set;

import static com.jichen.redblueball.common.BallType.RED;
import static java.util.Collections.singleton;

@Component
@Predictor(name = "redBallFiveSubtractOnePredictor", type = RED)
public class RedBallFiveSubtractOnePredictor implements PredictService<History> {

    @Override
    public Set<Integer> predict(History history) {
        Integer ball = history.getRed5() - history.getRed1();
        return singleton(ball);
    }
}
