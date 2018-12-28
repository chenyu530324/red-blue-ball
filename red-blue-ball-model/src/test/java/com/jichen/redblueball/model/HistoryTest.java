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

    @Test
    public void shouldReturnCorrectACValueWhenGivenAHistoryData() {
        History history = aHistory().withRedBalls(1, 4, 8, 15, 27, 32).withBlueBall(16).withNumber(2017003).build();
        assertThat(history.getACValue(), is(9));
    }

    @Test
    public void shouldReturnCorrectACValueWhenGivenAHistory() {
        History history = aHistory().withRedBalls(2, 4, 8, 23, 26, 29).withBlueBall(16).withNumber(2017003).build();
        assertThat(history.getACValue(), is(7));
    }
}