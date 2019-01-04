package com.jichen.redblueball.predictor.service.redballpredictor;

import com.jichen.redblueball.common.model.History;
import org.junit.Test;

import static com.jichen.redblueball.common.BallFactory.createRedBall;
import static com.jichen.redblueball.common.HistoryBuilder.aHistory;
import static java.util.Collections.singleton;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class RedBallFiveSubtractOnePredictorTest {

    @Test
    public void couldPredictRedBallWhenGivenHistory() {
        RedBallFiveSubtractOnePredictor predictor = new RedBallFiveSubtractOnePredictor();
        History history = aHistory().withRedBalls(3, 6, 12, 19, 30, 31).withBlueBall(13).build();
        assertThat(predictor.predict(history), is(singleton(createRedBall(27))));
    }
}