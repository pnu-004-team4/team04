package com.team04.musiccloud.network;

import com.team04.musiccloud.data_structure.UpdateDeque;

import java.util.Objects;
import java.util.Queue;

public class UserNetStatus {
    private static final int DEFAULT_CAPACITY = 20;
    
    private String user;
    private Queue<Integer> netDelayRecords;
    
    public UserNetStatus(int capacity) {
        netDelayRecords = new UpdateDeque<>(capacity);
    }
    
    public UserNetStatus() {
        this(DEFAULT_CAPACITY);
    }
    
    public void addNetDelayRecord(Integer delayTime) {
        netDelayRecords.add(delayTime);
    }
    
    public void clearAllNetDelayRecord() {
        if ( !netDelayRecords.isEmpty() ) {
            netDelayRecords.clear();
        }
    }
    
    public double getAverageNetDalay() {
        return netDelayRecords.stream()
                .mapToInt(Integer::intValue)
                .average()
                .orElse(0);
    }
    
    @Override
    public boolean equals(Object o) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        
        UserNetStatus that = (UserNetStatus) o;
        
        return Objects.equals(user, that.user) &&
                Objects.equals(netDelayRecords, that.netDelayRecords);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(user, netDelayRecords);
    }
}
