package com.jichen.redblueball.model;

import java.util.Objects;

public class HistorySum {

    private int number;

    private int sum;

    public HistorySum(int number, int sum) {
        this.number = number;
        this.sum = sum;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof HistorySum)) {
            return false;
        }
        HistorySum that = (HistorySum) o;
        return getNumber() == that.getNumber()
                && getSum() == that.getSum();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNumber(), getSum());
    }
}
