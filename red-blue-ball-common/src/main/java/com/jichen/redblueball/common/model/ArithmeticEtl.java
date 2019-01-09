package com.jichen.redblueball.common.model;

import com.jichen.redblueball.common.BallType;

public class ArithmeticEtl {

    private String arithmeticName;

    private BallType ballType;

    private int currentNumber;

    private String currentBalls;

    private String arithmeticResult;

    private boolean result;

    public String getArithmeticName() {
        return arithmeticName;
    }

    public void setArithmeticName(String arithmeticName) {
        this.arithmeticName = arithmeticName;
    }

    public int getCurrentNumber() {
        return currentNumber;
    }

    public void setCurrentNumber(int currentNumber) {
        this.currentNumber = currentNumber;
    }

    public String getCurrentBalls() {
        return currentBalls;
    }

    public void setCurrentBalls(String currentBalls) {
        this.currentBalls = currentBalls;
    }

    public String getArithmeticResult() {
        return arithmeticResult;
    }

    public void setArithmeticResult(String arithmeticResult) {
        this.arithmeticResult = arithmeticResult;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public BallType getBallType() {
        return ballType;
    }

    public void setBallType(BallType ballType) {
        this.ballType = ballType;
    }
}
