package com.jichen.redblueball.killer.service.redballkiller;

import com.jichen.redblueball.common.annotations.Killer;
import com.jichen.redblueball.common.model.History;
import com.jichen.redblueball.killer.service.KillerService;

import java.util.Set;

import static com.jichen.redblueball.common.BallType.RED;
import static java.util.Collections.singleton;

@Killer(name = "index6SubtractIndex1", type = RED)
public class Index6SubtractIndex1Killer implements KillerService {

    public Set<Integer> kill(History history) {
        int killNumber = history.getRed6() - history.getRed1();
        return singleton(killNumber);
    }
}
