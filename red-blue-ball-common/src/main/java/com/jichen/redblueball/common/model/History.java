package com.jichen.redblueball.common.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class History {

    private static final int SYMMETRICAL = 34;
    private int number;

    private Date date;

    private int red1;

    private int red2;

    private int red3;

    private int red4;

    private int red5;

    private int red6;

    private int blueBall;

    private Set<Integer> intSet;

    public void setRed1(int red1) {
        this.red1 = red1;
    }

    public void setRed2(int red2) {
        this.red2 = red2;
    }

    public void setRed3(int red3) {
        this.red3 = red3;
    }

    public void setRed4(int red4) {
        this.red4 = red4;
    }

    public void setRed5(int red5) {
        this.red5 = red5;
    }

    public void setRed6(int red6) {
        this.red6 = red6;
    }

    public void setBlueBall(Integer blueBall) {
        this.blueBall = blueBall;
    }

    public int getRed1() {
        return red1;
    }

    public int getRed2() {
        return red2;
    }

    public int getRed3() {
        return red3;
    }

    public int getRed4() {
        return red4;
    }

    public int getRed5() {
        return red5;
    }

    public int getRed6() {
        return red6;
    }

    public int getBlueBall() {
        return blueBall;
    }

    public Set<Integer> getRedBallSet() {
        if (null == this.intSet) {
            this.intSet = new TreeSet<>();
            this.intSet.add(red1);
            this.intSet.add(red2);
            this.intSet.add(red3);
            this.intSet.add(red4);
            this.intSet.add(red5);
            this.intSet.add(red6);
        }
        return intSet;
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
        return getRedBallSet().stream().mapToInt(Integer::intValue).sum();
    }

    public int getSum() {
        return getRedBallSum() + getBlueBall();
    }

    public int getACValue() {
        Set<Integer> differValues = new HashSet<>();
        List<Integer> ints = new ArrayList<>(getRedBallSet());
        for (int i = 1; i < ints.size(); i++) {
            for (int j = 0; j < ints.size() - 1; j++) {
                if (j >= i) {
                    continue;
                }
                int differ = ints.get(i) - ints.get(j);
                differValues.add(differ);
            }
        }
        return differValues.size() - (ints.size() - 1);
    }

    public int getSymmetricalNumber(int index) {
        List<Integer> ints = new ArrayList<>(getRedBallSet());
        return SYMMETRICAL - ints.get(index - 1);
    }

    public int getPrimeCount() {
        return getRedBallSet().stream().filter(this::isPrime).collect(Collectors.toList()).size();
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
                && getRed1() == history.getRed1()
                && getRed2() == history.getRed2()
                && getRed3() == history.getRed3()
                && getRed4() == history.getRed4()
                && getRed5() == history.getRed5()
                && getRed6() == history.getRed6()
                && getBlueBall() == history.getBlueBall()
                && Objects.equals(getDate(), history.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNumber(),
                getDate(),
                getRed1(),
                getRed2(),
                getRed3(),
                getRed4(),
                getRed5(),
                getRed6(),
                getBlueBall());
    }

    private boolean isPrime(int n) {
        if (n < 2) {
            return false;
        }
        if (n == 2) {
            return true;
        }
        if (n % 2 == 0) {
            return false;
        }
        for (int i = 3; i * i <= n; i += 2) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

}
