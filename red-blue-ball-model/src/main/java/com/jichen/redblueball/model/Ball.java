package com.jichen.redblueball.model;


import com.jichen.redblueball.model.common.BallType;

import java.util.Objects;

public abstract class Ball implements Comparable<Ball> {

    private int value;

    private BallType type;

    public Ball(int value, BallType type) {
        this.value = value;
        this.type = type;
    }

    public int getValue() {
        return value;
    }

    public BallType getType() {
        return type;
    }

    @Override
    public int compareTo(Ball o) {
        return Integer.compare(this.value, o.getValue());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (!(o instanceof Ball)){
            return false;
        }
        Ball ball = (Ball) o;
        return getValue() == ball.getValue()
                && getType() == ball.getType();
    }

    @Override
    public int hashCode() {

        return Objects.hash(getValue());
    }
}
