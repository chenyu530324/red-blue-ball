package com.jichen.redblueball.predictor.service.redballpredictor;

import com.jichen.redblueball.common.model.History;
import com.jichen.redblueball.common.model.RedBall;
import org.junit.Test;

import static com.jichen.redblueball.common.HistoryBuilder.aHistory;
import static java.util.Collections.singleton;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class RedBallFiveSubtractOnePredictorTest {

    @Test
    public void couldPredictRedBallWhenGivenHistory() {
        RedBallFiveSubtractOnePredictor predictor = new RedBallFiveSubtractOnePredictor();
        History history = aHistory().withRedBalls(3, 6, 12, 19, 30, 31).withBlueBall(13).build();
        assertThat(predictor.predict(history), is(singleton(new RedBall(27))));
    }
}