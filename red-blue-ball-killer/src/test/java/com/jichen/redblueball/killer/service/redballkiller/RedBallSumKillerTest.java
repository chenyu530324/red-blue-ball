package com.jichen.redblueball.killer.service.redballkiller;

import com.jichen.redblueball.common.model.History;
import org.junit.Test;

import java.util.HashSet;

import static com.google.common.primitives.Ints.asList;
import static com.jichen.redblueball.common.HistoryBuilder.aHistory;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class RedBallSumKillerTest {

    @Test
    public void couldKillerCorrectNumber() {
        RedBallSumKiller killer = new RedBallSumKiller();
        History history = aHistory().withRedBalls(3, 6, 8, 19, 29, 30).withBlueBall(5).build();
        assertThat(killer.kill(history), is(new HashSet<>(asList(14))));
    }
}