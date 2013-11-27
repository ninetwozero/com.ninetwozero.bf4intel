package com.ninetwozero.bf4intel.base.utils;

public enum Result {
    SUCCESS, FAILURE, ERROR, NETWORK_FAILURE("network error"), CANCELLED("cancelled");

    private String message = "";

    private Result(String message) {
        this.message = message;
    }

    private Result() {
    }

    public void setResultMessage(String resultMessage) {
        this.message = resultMessage;
    }

    public String getResultMessage() {
        return message;
    }
}
