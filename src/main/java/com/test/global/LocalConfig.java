package com.test.global;
import com.test.helpers.utils.*;
public class LocalConfig {

    public static final String PET_STORE_URL;

    static {
        try {
            PropertyUtils.getInstance().load("config.properties");
            PET_STORE_URL= System.getProperty("PET_STORE_URL",PropertyUtils.getInstance().getValue("PET_STORE_URL"));
        } catch (Throwable t) {
            t.printStackTrace();
            throw new RuntimeException("Something wrong !!! Check configurations.", t);
        }
    }
}
