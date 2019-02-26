package com.jichen.redblueball.killer.service.redballkiller;

import com.jichen.redblueball.common.annotations.Killer;
import com.jichen.redblueball.common.model.History;
import com.jichen.redblueball.killer.service.KillerService;
import org.springframework.stereotype.Component;

import java.util.Set;

import static com.jichen.redblueball.common.BallType.RED;
import static java.util.Collections.singleton;

@Component
@Killer(name = "blueMinusRed1AndRed6Killer", type = RED)
public class BlueMinusRed1AndRed6Killer implements KillerService {
    @Override
    public Set<Integer> kill(History history) {
        int killNumber = history.getBlue() - history.getRed1() - history.getRed6();
        if (killNumber < 0) {
            killNumber = killNumber * -1;
        }
        return singleton(killNumber);
    }
}
