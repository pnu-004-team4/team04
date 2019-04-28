package com.team04.musiccloud.audio;

import java.io.File;
import java.io.FileFilter;

public class TempGarbageCollector implements Runnable {
    
    public static final int DELAY_ON_EMPTY = 5000;
    public static final int DELAY_ON_EACH = 500;
    
    @Override
    public void run() {
//        while ( true ) {
//            File[] files = TempManager.TEMP_DIRECTORY.toFile().listFiles(File::isDirectory);
//
//            try {
//                if ( files == null ) {
//                        Thread.sleep(DELAY_ON_EMPTY);
//                } else {
//                    for ( File file : files ) {
//                        file.
//
//                        Thread.sleep(DELAY_ON_EACH);
//                    }
//                }
//            } catch ( InterruptedException e ) {
//                e.printStackTrace();
//            }
//        }
    }
}
