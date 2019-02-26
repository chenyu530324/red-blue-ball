package com.jichen.redblueball.killer.service.redballkiller;


import com.jichen.redblueball.common.model.History;
import org.junit.Test;

import static com.jichen.redblueball.common.HistoryBuilder.aHistory;
import static java.util.Collections.singleton;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BlueMinusRed1AndRed6KillerTest {

    @Test
    public void shouldReturnCorrectNumberWhenGivenAHistoryAndBlueBallIsLarge() {
        BlueMinusRed1AndRed6Killer killer = new BlueMinusRed1AndRed6Killer();
        History history = aHistory().withRedBalls(4, 9, 17, 21, 25, 10).withBlueBall(16).build();
        assertThat(killer.kill(history), is(singleton(2)));
    }

    @Test
    public void shouldReturnCorrectNumberWhenGivenAHistory() {
        BlueMinusRed1AndRed6Killer killer = new BlueMinusRed1AndRed6Killer();
        History history = aHistory().withRedBalls(4, 9, 17, 21, 25, 31).withBlueBall(16).build();
        assertThat(killer.kill(history), is(singleton(19)));
    }
}