package com.dungeonstory.backend.service.mock;

import com.dungeonstory.backend.data.Spell;
import com.dungeonstory.backend.factory.impl.SpellFactory;
import com.dungeonstory.backend.repository.mock.MockSpellRepository;
import com.dungeonstory.backend.service.AbstractDataService;

public class MockSpellService extends AbstractDataService<Spell, Long> {

    private static final long serialVersionUID = 4787241452010818522L;
    
    private static MockSpellService instance = null;

    public static synchronized MockSpellService getInstance() {
        if (instance == null) {
            instance = new MockSpellService();
        }
        return instance;
    }

    private MockSpellService() {
        super();
        setEntityFactory(new SpellFactory());
        setRepository(new MockSpellRepository());
    }

}
