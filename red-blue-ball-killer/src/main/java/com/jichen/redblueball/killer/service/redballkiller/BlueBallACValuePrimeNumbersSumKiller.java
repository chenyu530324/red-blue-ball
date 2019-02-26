package com.jichen.redblueball.killer.service.redballkiller;

import com.jichen.redblueball.common.annotations.Killer;
import com.jichen.redblueball.common.model.History;
import com.jichen.redblueball.killer.service.KillerService;
import org.springframework.stereotype.Component;

import java.util.Set;

import static com.jichen.redblueball.common.BallType.RED;
import static java.util.Collections.singleton;

@Component
@Killer(name = "blueBallACValuePrimeNumbersSumKiller", type = RED)
public class BlueBallACValuePrimeNumbersSumKiller implements KillerService {
    @Override
    public Set<Integer> kill(History history) {
        int killNumber = history.getBlue() + history.getACValue() + history.getPrimeCount();
        if (killNumber > 33) {
            return singleton(killNumber - 33);
        }
        return singleton(killNumber);
    }
}
