package com.arelrojo.rps.domain;


public enum RPSMove {
    ROCK("ROCK"),
    PAPER("PAPER"),
    SCISSOR("SCISSOR");

    private String value;

    RPSMove(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

}
