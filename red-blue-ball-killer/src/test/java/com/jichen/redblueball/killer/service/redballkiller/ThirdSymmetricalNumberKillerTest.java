package com.jichen.redblueball.killer.service.redballkiller;

import com.jichen.redblueball.common.model.History;
import org.junit.Test;

import java.util.HashSet;

import static com.jichen.redblueball.common.HistoryBuilder.aHistory;
import static java.util.Collections.singleton;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ThirdSymmetricalNumberKillerTest {

    @Test
    public void couldKillRightNumberCorrectWhenValueLessThan33() {
        ThirdSymmetricalNumberKiller killer = new ThirdSymmetricalNumberKiller();
        History history = aHistory().withRedBalls(3, 6, 8, 19, 29, 30).withBlueBall(5).build();
        assertThat(killer.kill(history), is(new HashSet<>(singleton(33))));
    }

    @Test
    public void couldKillRightNumberCorrectWhenValueLargeThan33() {
        ThirdSymmetricalNumberKiller killer = new ThirdSymmetricalNumberKiller();
        History history = aHistory().withRedBalls(3, 4, 5, 19, 29, 30).withBlueBall(5).build();
        assertThat(killer.kill(history), is(new HashSet<>(singleton(3))));
    }
}