package com.jichen.redblueball.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class History {

    private int number;

    private Date date;

    private RedBall red1;

    private RedBall red2;

    private RedBall red3;

    private RedBall red4;

    private RedBall red5;

    private RedBall red6;

    private BlueBall blueBall;

    private Set<RedBall> redBallSet;

    public RedBall getRed1() {
        return red1;
    }

    public void setRed1(RedBall red1) {
        this.red1 = red1;
    }

    public RedBall getRed2() {
        return red2;
    }

    public void setRed2(RedBall red2) {
        this.red2 = red2;
    }

    public RedBall getRed3() {
        return red3;
    }

    public void setRed3(RedBall red3) {
        this.red3 = red3;
    }

    public RedBall getRed4() {
        return red4;
    }

    public void setRed4(RedBall red4) {
        this.red4 = red4;
    }

    public RedBall getRed5() {
        return red5;
    }

    public void setRed5(RedBall red5) {
        this.red5 = red5;
    }

    public RedBall getRed6() {
        return red6;
    }

    public void setRed6(RedBall red6) {
        this.red6 = red6;
    }

    public BlueBall getBlueBall() {
        return blueBall;
    }

    public void setBlueBall(BlueBall blueBall) {
        this.blueBall = blueBall;
    }

    public Set<RedBall> getRedBallSet() {
        if (null == this.redBallSet) {
            this.redBallSet = new HashSet<>();
            this.redBallSet.add(red1);
            this.redBallSet.add(red2);
            this.redBallSet.add(red3);
            this.redBallSet.add(red4);
            this.redBallSet.add(red5);
            this.redBallSet.add(red6);
        }
        return redBallSet;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getRedBallSum() {
        return getRedBallSet().stream().mapToInt(Ball::getValue).sum();
    }

    public int getSum() {
        return getRedBallSum() + getBlueBall().getValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof History)) {
            return false;
        }
        History history = (History) o;
        return getNumber() == history.getNumber()
                && getRed1().getValue() == history.getRed1().getValue()
                && getRed2().getValue() == history.getRed2().getValue()
                && getRed3().getValue() == history.getRed3().getValue()
                && getRed4().getValue() == history.getRed4().getValue()
                && getRed5().getValue() == history.getRed5().getValue()
                && getRed6().getValue() == history.getRed6().getValue()
                && getBlueBall().getValue() == history.getBlueBall().getValue()
                && Objects.equals(getDate(), history.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNumber(),
                getDate(),
                getRed1().getValue(),
                getRed2().getValue(),
                getRed3().getValue(),
                getRed4().getValue(),
                getRed5().getValue(),
                getRed6().getValue(),
                getBlueBall().getValue());
    }

}
