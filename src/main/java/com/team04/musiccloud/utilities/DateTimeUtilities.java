package com.team04.musiccloud.utilities;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Optional;

public class DateTimeUtilities {
    private DateTimeUtilities() {
    }
    
    public static boolean isLocalDateTime(String dateString) {
        boolean isDateTime = true;
        
        try {
            LocalDateTime.parse(dateString);
        } catch ( DateTimeParseException e ) {
            isDateTime = false;
        }
        
        return isDateTime;
    }
    
    public static Optional<LocalDateTime> getLocalDateTime(String dateString) {
        Optional<LocalDateTime> localDateTime = Optional.empty();
        
        if ( isLocalDateTime(dateString) ) {
            localDateTime = Optional.of(LocalDateTime.parse(dateString));
        }
        
        return localDateTime;
    }
    
    public static Optional<LocalDateTime> getLocalDateTime(Date date) {
        final LocalDateTime localDateTime = date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
        
        return Optional.ofNullable(localDateTime);
    }
    
    
}
