package com.test.validators.api;

public class PetValidator {
    private static volatile PetValidator instance;

    private PetValidator() {
    }

    public static PetValidator getInstance() {
        if (instance == null) {
            synchronized (PetValidator.class) {
                if (instance == null) {
                    instance = new PetValidator();
                }
            }
        }
        return instance;
    }
}