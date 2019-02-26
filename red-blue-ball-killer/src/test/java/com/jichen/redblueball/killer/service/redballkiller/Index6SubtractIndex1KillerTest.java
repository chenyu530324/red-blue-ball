package com.jichen.redblueball.killer.service.redballkiller;

import com.jichen.redblueball.common.model.History;
import org.junit.Test;

import static com.jichen.redblueball.common.HistoryBuilder.aHistory;
import static java.util.Collections.singleton;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class Index6SubtractIndex1KillerTest {

    @Test
    public void shouldReturnCorrectNumberWhenGivenAHistory() {
        Index6SubtractIndex1Killer killer = new Index6SubtractIndex1Killer();
        History history = aHistory().withRedBalls(4, 9, 17, 21, 25, 31).withBlueBall(1).build();
        assertThat(killer.kill(history), is(singleton(27)));
    }
}