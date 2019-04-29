package com.team04.musiccloud.audio;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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
    
            try {
                if ( userDirectories == null ) {
                    System.out.println(Thread.currentThread().getName() + " is sleeping for " + DELAY_ON_EMPTY);
                    Thread.sleep(DELAY_ON_EMPTY);
                } else {
                    for ( File userDirectory : userDirectories ) {
                        System.out.println(Thread.currentThread().getName() + " working on user " + userDirectory.getName());
                        gcOnUserDirectory(userDirectory);
                    }
                }
            } catch ( InterruptedException e ) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            } catch ( IOException e ) {
                e.printStackTrace();
            }
        }
    }
    
    private void gcOnUserDirectory(File directory) throws InterruptedException, IOException {
        File[] files = directory.listFiles(File::isFile);
        if ( files == null ) return;
        
        final Comparator<File> sortByLastModified = (o1, o2) -> ((int) (o2.lastModified() - o1.lastModified()));
        Arrays.sort(files, sortByLastModified);
        
        for ( int i = 0; i < files.length; i++ ) {
            System.out.println(Thread.currentThread().getName() + " working on file " + files[i].getName());
    
            if ( i >= MAX_USER_TEMP_FILES || isFilePassDue(files[i]) ) {
                System.out.println(" >> deleted");
                deleteFile(files[i].toPath());
            }
        }
        
        System.out.println(Thread.currentThread().getName() + " is sleeping for " + DELAY_ON_EACH);
        Thread.sleep(DELAY_ON_EACH);
    }
    
    private boolean isFilePassDue(File file) {
        Date date = new Date(file.lastModified());
        LocalDateTime localDateTime = DateTimeUtilities.getLocalDateTime(date).orElseThrow(NullPointerException::new);
        
        Duration duration = Duration.between(localDateTime, LocalDateTime.now());
        return duration.toMinutes() > MIN_MINUTE_TO_STORE;
    }
    
    private void deleteFile(Path path) throws IOException {
        Files.delete(path);
    }
}
