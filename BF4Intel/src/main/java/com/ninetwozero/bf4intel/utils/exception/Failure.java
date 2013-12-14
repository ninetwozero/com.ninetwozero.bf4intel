package com.ninetwozero.bf4intel.utils.exception;

public class Failure extends Exception {
    public Failure(Exception e) {
        super(e);
    }

    public Failure(String message) {
        super(message);
    }
}
