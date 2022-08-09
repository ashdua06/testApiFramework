package com.test.exceptions;


public class UserException extends RuntimeException{
    public UserException() {
    }

    private UserException(String var1) {
        super(var1);
    }

    public UserException(Object var1) {
        this(String.valueOf(var1));
        if (var1 instanceof Throwable) {
            this.initCause((Throwable) var1);
        }

    }

    public UserException(boolean var1) {
        this(String.valueOf(var1));
    }

    public UserException(char var1) {
        this(String.valueOf(var1));
    }

    public UserException(int var1) {
        this(String.valueOf(var1));
    }

    public UserException(long var1) {
        this(String.valueOf(var1));
    }

    public UserException(float var1) {
        this(String.valueOf(var1));
    }

    public UserException(double var1) {
        this(String.valueOf(var1));
    }

    public UserException(String var1, Throwable var2) {
        super(var1, var2);
    }

}
