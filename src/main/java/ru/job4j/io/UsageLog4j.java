package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        byte bt = 100;
        short sh = 1000;
        char ch = 'a';
        int in = 10000;
        boolean bl = false;
        double db = 55.25;
        long ln = 100000000L;
        float fl = 2.5f;
        LOG.debug("Example of byte : {}, short : {}, char : {}, int : {}, boolean : {}, double : {}, long : {}, "
                + "float : {}", bt, sh, ch, in, bl, db, ln, fl);
    }
}