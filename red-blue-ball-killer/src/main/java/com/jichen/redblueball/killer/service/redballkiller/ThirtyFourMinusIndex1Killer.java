package com.jichen.redblueball.killer.service.redballkiller;

import com.jichen.redblueball.common.annotations.Killer;
import com.jichen.redblueball.common.model.History;
import com.jichen.redblueball.killer.service.KillerService;
import org.springframework.stereotype.Component;

import java.util.Set;

import static com.jichen.redblueball.common.BallType.RED;
import static java.util.Collections.singleton;

@Killer(name = "thirtyFourMinusIndex1Killer", type = RED)
@Component
public class ThirtyFourMinusIndex1Killer implements KillerService {

    @Override
    public Set<Integer> kill(History history) {
        return singleton(34 - history.getRed1());
    }
}
