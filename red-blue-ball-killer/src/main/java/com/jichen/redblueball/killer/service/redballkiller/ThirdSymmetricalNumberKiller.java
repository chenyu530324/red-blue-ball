package com.jichen.redblueball.killer.service.redballkiller;

import com.jichen.redblueball.killer.service.KillerService;
import com.jichen.redblueball.model.Ball;
import com.jichen.redblueball.model.History;
import com.jichen.redblueball.model.annotations.Killer;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.TreeSet;

import static com.jichen.redblueball.model.common.BallFactory.createRedBall;
import static com.jichen.redblueball.model.common.BallType.RED;

@Component
@Killer(name = "thirdSymmetricalNumberKiller", type = RED)
public class ThirdSymmetricalNumberKiller implements KillerService<History> {
    @Override
    public Set<Ball> kill(History history) {
        Set<Ball> killSet = new TreeSet<>();
        int killNumber = history.getSymmetricalNumber(3) + 7;
        killSet.add(createRedBall(killNumber));
        return killSet;
    }
}
