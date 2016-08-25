package com.dungeonstory.backend.service.impl;

import com.dungeonstory.backend.data.Spell;
import com.dungeonstory.backend.factory.impl.SpellFactory;
import com.dungeonstory.backend.repository.impl.SpellRepository;
import com.dungeonstory.backend.service.AbstractDataService;

public class SpellService extends AbstractDataService<Spell, Long> {

    private static final long serialVersionUID = -7744320160012123649L;
    
    private static SpellService instance = null;

    public static synchronized SpellService getInstance() {
        if (instance == null) {
            instance = new SpellService();
        }
        return instance;
    }

    private SpellService() {
        super();
        setEntityFactory(new SpellFactory());
        setRepository(new SpellRepository());
    }

}
