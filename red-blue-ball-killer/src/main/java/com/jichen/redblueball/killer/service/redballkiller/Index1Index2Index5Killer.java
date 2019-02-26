package com.jichen.redblueball.killer.service.redballkiller;

import com.jichen.redblueball.common.annotations.Killer;
import com.jichen.redblueball.common.model.History;
import com.jichen.redblueball.killer.service.KillerService;
import org.springframework.stereotype.Component;

import java.util.Set;

import static com.jichen.redblueball.common.BallType.RED;
import static java.util.Collections.singleton;

@Component
@Killer(name = "index1Index2Index5Killer", type = RED)
public class Index1Index2Index5Killer implements KillerService {
    @Override
    public Set<Integer> kill(History history) {
        int killNumber = history.getRed1() + history.getRed2() + history.getRed5();
        if (killNumber > 33) {
            return singleton(killNumber - 33);
        }
        return singleton(killNumber);
    }
}
