package com.dungeonstory.backend.service;

import com.dungeonstory.backend.data.Adventure;
import com.dungeonstory.backend.data.Message;

public interface AdventureDataService extends DataService<Adventure, Long> {
    
    public Message getLastPersistedMessage(Adventure adventure);

}
