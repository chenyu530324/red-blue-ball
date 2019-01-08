package com.jichen.redblueball.common;

import com.jichen.redblueball.common.model.BlueBall;
import com.jichen.redblueball.common.model.History;
import com.jichen.redblueball.common.model.RedBall;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class HistoryBuilder {

    private int red1;
    private int red2;
    private int red3;
    private int red4;
    private int red5;
    private int red6;
    private int blueBall;
    private int number;
    private Date date;

    private HistoryBuilder() {
    }

    public static HistoryBuilder aHistory() {
        return new HistoryBuilder();
    }

    public HistoryBuilder withRedBalls(int tempRed1, int tempRed2, int tempRed3, int tempRed4, int tempRed5, int tempRed6) {
        this.red1 = tempRed1;
        this.red2 = tempRed2;
        this.red3 = tempRed3;
        this.red4 = tempRed4;
        this.red5 = tempRed5;
        this.red6 = tempRed6;
        return this;
    }

    public HistoryBuilder withBlueBall(int tempBlueBall) {
        this.blueBall = tempBlueBall;
        return this;
    }

    public HistoryBuilder withNumber(int tempNumber) {
        this.number = tempNumber;
        return this;
    }

    public HistoryBuilder withDate(String tempDate) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            this.date = format.parse(tempDate);
        } catch (ParseException e) {
            this.date = new Date();
        }
        return this;
    }

    public History build() {
        History history = new History();
        history.setRed1(new RedBall(red1));
        history.setRed2(new RedBall(red2));
        history.setRed3(new RedBall(red3));
        history.setRed4(new RedBall(red4));
        history.setRed5(new RedBall(red5));
        history.setRed6(new RedBall(red6));
        history.setBlueBall(new BlueBall(blueBall));
        history.setNumber(this.number);
        history.setDate(this.date);
        return history;
    }

}
