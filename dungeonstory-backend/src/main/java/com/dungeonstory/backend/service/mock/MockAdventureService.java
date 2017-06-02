package com.dungeonstory.backend.service.mock;

import com.dungeonstory.backend.data.Adventure;
import com.dungeonstory.backend.data.Message;
import com.dungeonstory.backend.repository.mock.MockAdventureRepository;
import com.dungeonstory.backend.service.AbstractDataService;
import com.dungeonstory.backend.service.AdventureDataService;

public class MockAdventureService extends AbstractDataService<Adventure, Long> implements AdventureDataService {

    private static final long serialVersionUID = 3713358506919598497L;

    private static MockAdventureService instance = null;

    public static synchronized MockAdventureService getInstance() {
        if (instance == null) {
            instance = new MockAdventureService();
        }
        return instance;
    }

    private MockAdventureService() {
        super();
        setEntityFactory(() -> new Adventure());
        setRepository(new MockAdventureRepository());
    }

    @Override
    public Message getLastPersistedMessage(Adventure adventure) {
        return adventure.getMessages().get(adventure.getMessages().size() - 1);
    }

}
