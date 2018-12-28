package com.jichen.redblueball.model.common;

import com.jichen.redblueball.model.BlueBall;
import com.jichen.redblueball.model.RedBall;

public final class BallFactory {

    private BallFactory() {

    }

    public static RedBall createRedBall(int number) {
        return new RedBall(number, BallType.RED);
    }

    public static BlueBall createBlueBall(int number) {
        return new BlueBall(number, BallType.BLUE);
    }
}
