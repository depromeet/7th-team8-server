package com.nonoplan.batch.common;

import org.apache.commons.lang3.StringUtils;

public class RegexpUtils {
    private static final String REGEXP_NUMBER = "[^0-9]";

    public static String getOnlyNumber(String str) {
        return str.replaceAll(REGEXP_NUMBER, StringUtils.EMPTY);
    }
}
