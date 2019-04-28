package com.team04.musiccloud.audio;

import java.io.File;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;

public class TempGarbageCollector implements Runnable {
    
    private static final int DELAY_ON_EMPTY = 5000;
    private static final int DELAY_ON_EACH = 500;
    private static final int MAX_USER_TEMP_FILES = 50;
    private static final int MIN_MINUTE_TO_STORE = 10;
    
    public static void startGC() {
        Runnable runnable = new TempGarbageCollector();
        String name = "TempGC";
        
        new Thread(runnable, name).start();
    }
    
    @Override
    public void run() {
        while ( true ) {
            File[] userDirectories = TempManager.TEMP_DIRECTORY.toFile().listFiles(File::isDirectory);
        
            if ( userDirectories == null ) {
                System.out.println(Thread.currentThread().getName() + " is sleeping for " + DELAY_ON_EMPTY);
                sleep(DELAY_ON_EMPTY);
            } else {
                for ( File userDirectory : userDirectories ) {
                    System.out.println(Thread.currentThread().getName() + " working on user " + userDirectory.getName());
                    gcOnUserDirectory(userDirectory);
                }
            }
        }
    }
    
    private void gcOnUserDirectory(File directory) {
        File[] files = directory.listFiles(File::isFile);
        if ( files == null ) return;
        
        final Comparator<File> sortByLastModified = (o1, o2) -> ((int) (o2.lastModified() - o1.lastModified()));
        Arrays.sort(files, sortByLastModified);
        
        for ( int i = 0; i < files.length; i++ ) {
            System.out.println(Thread.currentThread().getName() + " working on file " + files[i].getName());
            if ( isFilePassDue(files[i]) || i >= MAX_USER_TEMP_FILES ) {
                System.out.println(" >> deleted");
                files[i].delete();
            }
        }
        
        System.out.println(Thread.currentThread().getName() + " is sleeping for " + DELAY_ON_EACH);
        sleep(DELAY_ON_EACH);
    }
    
    private void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch ( InterruptedException e ) {
            e.printStackTrace();
        }
    }
    
    private boolean isFilePassDue(File file) {
        Date date = new Date(file.lastModified());
        LocalDateTime localDateTime = DateTimeUtilities.getLocalDateTime(date).orElseThrow(NullPointerException::new);
        
        Duration duration = Duration.between(localDateTime, LocalDateTime.now());
        return duration.toMinutes() > MIN_MINUTE_TO_STORE;
    }
}
