package com.jichen.redblueball.killer.service.redballkiller;


import com.jichen.redblueball.common.model.History;
import org.junit.Test;

import static com.jichen.redblueball.common.HistoryBuilder.aHistory;
import static java.util.Collections.singleton;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ThreeKillerTest {

    @Test
    public void shouldReturnCorrectNumberWhenGivenAHistory() {
        ThreeKiller killer = new ThreeKiller();
        History history = aHistory().withRedBalls(4, 9, 17, 21, 25, 31).withBlueBall(16).build();
        assertThat(killer.kill(history), is(singleton(3)));
    }

}