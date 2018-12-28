package com.jichen.redblueball.model;

import org.junit.Test;

import static com.jichen.redblueball.model.common.HistoryBuilder.aHistory;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class HistoryTest {

    @Test
    public void shouldReturnCorrectSum() {
        History history = aHistory().withRedBalls(3, 6, 12, 19, 30, 31).withBlueBall(13).withNumber(2015113).build();
        assertThat(history.getSum(), is(114));
    }
}