package com.dungeonstory.backend.service.mock;

import java.util.ArrayList;
import java.util.List;

import com.dungeonstory.backend.data.Spell;
import com.dungeonstory.backend.repository.mock.MockSpellRepository;
import com.dungeonstory.backend.service.AbstractDataService;
import com.dungeonstory.backend.service.SpellDataService;

public class MockSpellService extends AbstractDataService<Spell, Long> implements SpellDataService {

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
        setEntityFactory(() -> new Spell());
        setRepository(new MockSpellRepository());
    }

    @Override
    public List<Spell> findAllSpellsByLevel(int level) {
        // TODO Auto-generated method stub
        return new ArrayList<>();
    }

    @Override
    public List<Spell> findAllUnknownClassSpellsByLevel(int level, Long characterId, Long classId) {
        // TODO Auto-generated method stub
        return new ArrayList<>();
    }

    @Override
    public List<Spell> findAllKnownClassSpellsByLevel(int level, Long characterId, Long classId) {
        // TODO Auto-generated method stub
        return new ArrayList<>();
    }

    @Override
    public List<Spell> findAllSpellsSortedByLevelAndName() {
        // TODO Auto-generated method stub
        return new ArrayList<>();
    }

}
