package com.test.exceptions;

public class PetException extends RuntimeException{
    public PetException() {
    }

    private PetException(String var1) {
        super(var1);
    }

    public PetException(Object var1) {
        this(String.valueOf(var1));
        if (var1 instanceof Throwable) {
            this.initCause((Throwable) var1);
        }

    }

    public PetException(boolean var1) {
        this(String.valueOf(var1));
    }

    public PetException(char var1) {
        this(String.valueOf(var1));
    }

    public PetException(int var1) {
        this(String.valueOf(var1));
    }

    public PetException(long var1) {
        this(String.valueOf(var1));
    }

    public PetException(float var1) {
        this(String.valueOf(var1));
    }

    public PetException(double var1) {
        this(String.valueOf(var1));
    }

    public PetException(String var1, Throwable var2) {
        super(var1, var2);
    }

}
