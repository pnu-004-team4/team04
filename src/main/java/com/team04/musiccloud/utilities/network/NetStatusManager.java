package com.team04.musiccloud.utilities.network;

import java.util.HashMap;
import java.util.Map;
import java.util.function.IntPredicate;
import java.util.function.Predicate;

public class NetStatusManager {
    private static NetStatusManager netStatusManager;
    
    public static synchronized NetStatusManager getInstance() {
        if ( netStatusManager == null ) {
            netStatusManager = new NetStatusManager();
        }
        
        return netStatusManager;
    }
    
    
    private static final int MAX_STATUS_PER_USER = 50;
    
    private Map<String, UserNetStatus> userStatusMap;
    
    private NetStatusManager() {
        userStatusMap = new HashMap<>();
    }
    
    public boolean exist(String user) {
        return userStatusMap.containsKey(user);
    }
    
    public UserNetStatus findUserNetState(String user) {
        UserNetStatus found = null;
        
        if ( this.exist(user) ) {
            found = userStatusMap.get(user);
        }
        
        return found;
    }
    
    public UserNetStatus getUserNetState(String user) {
        UserNetStatus userNetStatus;
        
        if ( this.exist(user) ) {
            userNetStatus = findUserNetState(user);
        } else {
            userNetStatus = createUserNetState(user);
        }
        
        return userNetStatus;
    }
    
    public UserNetStatus createUserNetState(String user) {
        final UserNetStatus userNetStatus = new UserNetStatus(MAX_STATUS_PER_USER);
        userStatusMap.put(user, userNetStatus);
        return userNetStatus;
    }
    
    public void addUserNetDelay(String user, int delay) {
        UserNetStatus userNetStatus = getUserNetState(user);
        userNetStatus.addNetDelay(delay);
    }
    
    public double getUserNetDelayAverage(String user) {
        UserNetStatus userNetStatus = getUserNetState(user);
        return userNetStatus.getAverageNetDelay();
    }
    
    public double getUserNetDelayAverage(String user, Predicate<Integer> predicate) {
        UserNetStatus userNetStatus = getUserNetState(user);
        return userNetStatus.getAverageNetDelay(predicate);
    }
    
    public double getUserNetDelayAverage(String user, IntPredicate intPredicate) {
        UserNetStatus userNetStatus = getUserNetState(user);
        return userNetStatus.getAverageNetDelay(intPredicate);
    }
}
