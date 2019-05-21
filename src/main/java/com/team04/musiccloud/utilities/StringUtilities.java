package com.team04.musiccloud.utilities;

import java.util.function.UnaryOperator;

public class StringUtilities {
    private StringUtilities() {
    }
    
    public static boolean hasContent(String string, UnaryOperator<String> operator) {
        boolean exists = false;
        
        if ( string != null ) {
            String modifiedString = operator.apply(string);
            exists = (modifiedString.length() != 0);
        }
        
        return exists;
    }
    
    public static boolean hasContentAfterTrim(String string) {
        return hasContent(string, String::trim);
    }
}
