package com.dungeonstory.backend.service.impl;

import java.util.List;

import com.dungeonstory.backend.data.Spell;
import com.dungeonstory.backend.factory.impl.SpellFactory;
import com.dungeonstory.backend.repository.impl.SpellRepository;
import com.dungeonstory.backend.service.AbstractDataService;
import com.dungeonstory.backend.service.SpellDataService;

public class SpellService extends AbstractDataService<Spell, Long> implements SpellDataService {

    private static final long serialVersionUID = -7744320160012123649L;
    
    private static SpellService instance = null;
    private SpellRepository     repository = null;

    public static synchronized SpellService getInstance() {
        if (instance == null) {
            instance = new SpellService();
        }
        return instance;
    }

    private SpellService() {
        super();
        repository = new SpellRepository();
        setEntityFactory(new SpellFactory());
        setRepository(repository);
    }

    @Override
    public List<Spell> findAllSpellsByLevel(int level) {
        return repository.findAllSpellsByLevel(level);
    }

    @Override
    public List<Spell> findAllUnknownSpellsByLevel(int level, Long characterId) {
        return repository.findAllUnknownSpellsByLevel(level, characterId);
    }

    @Override
    public List<Spell> findAllSpellsSortedByLevelAndName() {
        return repository.findAllSpellsSortedByLevelAndName();
    }

    @Override
    public List<Spell> findAllKnownSpellsByLevel(int level, Long characterId) {
        return repository.findAllKnownSpellsByLevel(level, characterId);
    }

}
