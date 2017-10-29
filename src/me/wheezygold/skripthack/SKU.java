package me.wheezygold.skripthack;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class SKU {

    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    public @interface data {
        String name();
        String desc();
        String example();
    }

}
