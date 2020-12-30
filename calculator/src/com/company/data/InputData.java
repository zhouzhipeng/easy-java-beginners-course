package com.company.data;

public class InputData {
    int input;
    int input2;
    String operator;

    public int getInput() {
        return input;
    }

    public int getInput2() {
        return input2;
    }

    public String getOperator() {
        return operator;
    }

    public InputData(int input, int input2, String operator) {
        this.input = input;
        this.input2 = input2;
        this.operator = operator;
    }
}
