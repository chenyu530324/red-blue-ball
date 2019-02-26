package com.jichen.redblueball.killer.service.redballkiller;


import com.jichen.redblueball.common.model.History;
import org.junit.Test;

import static com.jichen.redblueball.common.HistoryBuilder.aHistory;
import static java.util.Collections.singleton;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class Index1Index2Index5KillerTest {

    @Test
    public void shouldReturnCorrectNumberWhenGivenAHistory() {
        Index1Index2Index5Killer killer = new Index1Index2Index5Killer();
        History history = aHistory().withRedBalls(4, 9, 17, 21, 25, 31).withBlueBall(1).build();
        assertThat(killer.kill(history), is(singleton(5)));
    }

    @Test
    public void shouldReturnCorrectNumberWhenGivenAHistoryAndValueLessThan33() {
        Index1Index2Index5Killer killer = new Index1Index2Index5Killer();
        History history = aHistory().withRedBalls(4, 9, 17, 18, 19, 31).withBlueBall(1).build();
        assertThat(killer.kill(history), is(singleton(32)));
    }
}