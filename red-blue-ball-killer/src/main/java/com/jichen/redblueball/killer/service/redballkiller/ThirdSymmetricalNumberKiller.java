package com.jichen.redblueball.killer.service.redballkiller;

import com.jichen.redblueball.common.annotations.Killer;
import com.jichen.redblueball.common.model.History;
import com.jichen.redblueball.killer.service.KillerService;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.TreeSet;

import static com.jichen.redblueball.common.BallType.RED;

@Component
@Killer(name = "thirdSymmetricalNumberKiller", type = RED)
public class ThirdSymmetricalNumberKiller implements KillerService {

    @Override
    public Set<Integer> kill(History history) {
        Set<Integer> killSet = new TreeSet<>();
        int killNumber = history.getSymmetricalNumber(3) + 7;
        if (killNumber > 33) {
            killNumber = killNumber - 33;
        }
        killSet.add(killNumber);
        return killSet;
    }
}
