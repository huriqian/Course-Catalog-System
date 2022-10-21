package edu.hdu.variant1.utils;

public enum Status {
    SUCCESS(1, "Success"),
    FAILURE(-1, "Failure"),
    UNKNOWN(0, "Unknown");

    public final Integer CODE;
    public final String INFO;

    Status(Integer code, String info) {
        this.CODE = code;
        this.INFO = info;
    }
}
