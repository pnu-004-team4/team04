package com.team04.musiccloud.auth;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;
import java.util.logging.Logger;

public class TempKey {

    private boolean lowerCheck;
    private int size;
    private Random random;
    private static final Logger logger = Logger.getGlobal();

    public String getKey(int size, boolean lowerCheck) {
        this.size = size;
        this.lowerCheck = lowerCheck;
        return init();
    }

    private String init() {
        try {
            random = SecureRandom.getInstanceStrong();
        } catch (NoSuchAlgorithmException e) {
            logger.warning(e.toString());
            return "";
        }
        StringBuffer sb = new StringBuffer();
        generateTempKey(sb);
        if(lowerCheck) {
            return sb.toString().toLowerCase();
        }
        return sb.toString();
    }

    private void generateTempKey(StringBuffer sb) {
        do {
            process(sb);
        } while (sb.length() < size);
    }

    private void process(StringBuffer sb) {
        int num;
        num = random.nextInt(75)+48;
        if((num>=48 && num<=57) || (num>=65 && num<=90) || (num>=97 && num<=122)) {
            sb.append((char)num);
        }
    }

}
