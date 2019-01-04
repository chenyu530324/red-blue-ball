package com.jichen.redblueball.common;

import com.jichen.redblueball.common.model.BlueBall;
import com.jichen.redblueball.common.model.RedBall;

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
