package com.dungeonstory.backend.service.impl;

import java.util.Collection;
import java.util.Optional;

import com.dungeonstory.backend.data.Level;
import com.dungeonstory.backend.factory.impl.LevelFactory;
import com.dungeonstory.backend.repository.impl.LevelRepository;
import com.dungeonstory.backend.service.AbstractDataService;

public class LevelService extends AbstractDataService<Level, Long> {
    
    private static LevelService instance = null;
    
    public static synchronized LevelService getInstance() {
        if (instance == null) {
            instance = new LevelService();
        }
        return instance;
    }

	private LevelService() {
		super();
		setEntityFactory(new LevelFactory());
		setRepository(new LevelRepository());
	}
	
	@Override
    public Level create() {
        Level level = super.create();
        Collection<Level> allLevels = entityRepository.findAll();
        Optional<Level> maxLevel = allLevels.stream().max((e1, e2) -> Long.compare(e1.getId(), e2.getId()));
        if (maxLevel.isPresent()) {
            level.setId(maxLevel.get().getId() + 1);
        } else {
            level.setId(1L);
        }
        return level;
    }
	
}
