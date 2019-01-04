package com.jichen.redblueball.common;

import com.jichen.redblueball.common.model.History;
import org.junit.Test;

import static com.jichen.redblueball.common.HistoryBuilder.aHistory;
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

    @Test
    public void shouldReturnCorrectSymmetricalNumberWhenGivenAIndex() {
        History history = aHistory().withRedBalls(2, 4, 8, 23, 26, 29).withBlueBall(16).withNumber(2017003).build();
        assertThat(history.getSymmetricalNumber(1), is(32));
        assertThat(history.getSymmetricalNumber(2), is(30));
        assertThat(history.getSymmetricalNumber(3), is(26));
        assertThat(history.getSymmetricalNumber(4), is(11));
        assertThat(history.getSymmetricalNumber(5), is(8));
        assertThat(history.getSymmetricalNumber(6), is(5));
    }

    @Test
    public void couldReturnPrimeCountWhenGivenAHistory() {
        History history = aHistory().withRedBalls(2, 4, 8, 23, 26, 29).withBlueBall(16).withNumber(2017003).build();
        assertThat(history.getPrimeCount(), is(3));
    }
}