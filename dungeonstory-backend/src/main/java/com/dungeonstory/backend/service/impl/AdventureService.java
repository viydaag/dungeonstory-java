package com.dungeonstory.backend.service.impl;

import java.util.List;

import com.dungeonstory.backend.data.Adventure;
import com.dungeonstory.backend.data.Message;
import com.dungeonstory.backend.factory.impl.AdventureFactory;
import com.dungeonstory.backend.repository.impl.AdventureRepository;
import com.dungeonstory.backend.service.AbstractDataService;
import com.dungeonstory.backend.service.AdventureDataService;

public class AdventureService extends AbstractDataService<Adventure, Long> implements AdventureDataService {

    private static final long       serialVersionUID = -3637998347027964634L;

    private static AdventureService instance         = null;

    public static synchronized AdventureService getInstance() {
        if (instance == null) {
            instance = new AdventureService();
        }
        return instance;
    }

    private AdventureService() {
        super();
        setEntityFactory(new AdventureFactory());
        setRepository(new AdventureRepository());
    }

    @Override
    public Message getLastPersistedMessage(Adventure adventure) {
        List<Message> messages = adventure.getMessages();
        for (int i = messages.size() - 1; i >= 0; i--) {
            Message message = messages.get(i);
            if (message.getId() != null) {
                return message;
            }
        }
        return null;
    }

}
