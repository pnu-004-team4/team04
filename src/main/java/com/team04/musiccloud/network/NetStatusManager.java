package com.team04.musiccloud.network;

import java.util.HashMap;
import java.util.Map;

public class NetStatusManager {
    public static final int DEFAULT_MAX_RECORDS_PER_USER = 50;
    
    private Map<String, UserNetStatus> userStatusMap;
    private final int maxRecordsPerUser;
    
    public NetStatusManager() {
        this(DEFAULT_MAX_RECORDS_PER_USER);
    }
    
    public NetStatusManager(int maxRecordsPerUser) {
        userStatusMap = new HashMap<>();
        this.maxRecordsPerUser = maxRecordsPerUser;
    }
    
    public boolean exist(String user) {
        return userStatusMap.containsKey(user);
    }
    
    public UserNetStatus getUserNetState(String user) {
        UserNetStatus found = null;
        
        if ( this.exist(user) ) {
            found = userStatusMap.get(user);
        }
        
        return found;
    }
    
    public UserNetStatus createUserNetState(String user) {
        final UserNetStatus userNetStatus = new UserNetStatus(maxRecordsPerUser);
        userStatusMap.put(user, userNetStatus);
        return userNetStatus;
    }
}
