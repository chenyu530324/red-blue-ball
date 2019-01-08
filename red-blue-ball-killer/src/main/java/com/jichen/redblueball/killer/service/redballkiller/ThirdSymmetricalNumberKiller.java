package com.jichen.redblueball.killer.service.redballkiller;

import com.jichen.redblueball.common.annotations.Killer;
import com.jichen.redblueball.common.model.Ball;
import com.jichen.redblueball.common.model.History;
import com.jichen.redblueball.common.model.RedBall;
import com.jichen.redblueball.killer.service.KillerService;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.TreeSet;

import static com.jichen.redblueball.common.BallType.RED;

@Component
@Killer(name = "thirdSymmetricalNumberKiller", type = RED)
public class ThirdSymmetricalNumberKiller implements KillerService<History> {
    @Override
    public Set<Ball> kill(History history) {
        Set<Ball> killSet = new TreeSet<>();
        int killNumber = history.getSymmetricalNumber(3) + 7;
        killSet.add(new RedBall(killNumber));
        return killSet;
    }
}
