package com.nationwide.nf.rp.util;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by jorgej2 on 6/27/2017.
 */
public class GeneralUtil {

    public static String prepareDataItem(String dataItem) {
        return  dataItem == null ? "" :  dataItem.trim();
    }

    public static String getPrefix(String token) {
        if (StringUtils.isBlank(token)) {
            return token;
        }
        int hyphenPos = token.indexOf("-");
        String schoolDistrictPrefix = "";
        if (hyphenPos > 0) {
            schoolDistrictPrefix = token.substring(0, hyphenPos);
        } else {
            schoolDistrictPrefix = token;
        }
        return schoolDistrictPrefix;
    }

    public static String getPostfix(String token) {
        if (StringUtils.isBlank(token)) {
            return token;
        }
        int hyphenPos = token.indexOf("-");
        String schoolDistrictPrefix = "";
        if (hyphenPos > 0) {
            schoolDistrictPrefix = token.substring(hyphenPos + 1, token.length());
        } else {
            schoolDistrictPrefix = token;
        }
        return schoolDistrictPrefix;
    }

    public static String formatColumnValue(String csvColumnValue) {
        if (csvColumnValue == null) {
            return " ";
        } else if ("".equals(csvColumnValue)){
            return " ";
        }
        return csvColumnValue;
    }

    public static boolean isNumeric(String maybeNumeric) {
        return maybeNumeric != null && maybeNumeric.matches("[0-9]+");
    }

    public static char getVerticalTab() {
        return '\u000b';
    }

    public static boolean inDate(java.util.Date beginDate, java.util.Date endDate, java.util.Date flowDate) {
        if (flowDate.getTime() >= beginDate.getTime()) {
            return (endDate == null || endDate.getTime() >= flowDate.getTime());
        }
        return false;
    }

    public static String getLastCharactersOfString(String string, int numberOfCharacters) {
        int length = string.length();
        return string.substring(length - numberOfCharacters, length);
    }
}
