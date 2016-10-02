package com.clara.lending.exception;

public class ClaraValidationError implements ValidationError{
    private final String code;
    private final String msg;
    private final String recoverability;

    public ClaraValidationError(String code, String msg, String recoverability) {
        this.code = code;
        this.msg = msg;
        this.recoverability = recoverability;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    @Override
    public String getRecoverability() {
        return recoverability;
    }

    @Override
    public String toString() {
        return "ClaraValidationError : {" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", recoverability='" + recoverability + '\'' +
                '}';
    }
}
