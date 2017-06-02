package com.dungeonstory.backend.service.impl;

import java.util.List;

import com.dungeonstory.backend.data.Character;
import com.dungeonstory.backend.data.Feat;
import com.dungeonstory.backend.repository.impl.FeatRepository;
import com.dungeonstory.backend.service.AbstractDataService;
import com.dungeonstory.backend.service.FeatDataService;

public class FeatService extends AbstractDataService<Feat, Long> implements FeatDataService {

    private static final long serialVersionUID = -904004337605184211L;

    private static FeatService instance = null;
    private FeatRepository repository = null;

    public static synchronized FeatService getInstance() {
        if (instance == null) {
            instance = new FeatService();
        }
        return instance;
    }

    private FeatService() {
        super();
        repository = new FeatRepository();
        setEntityFactory(() -> new Feat());
        setRepository(repository);
    }


    @Override
    public List<Feat> findAllFeatsExcept(Feat feat) {
        return repository.findAllFeatsExcept(feat);
    }

    @Override
    public List<Feat> findAllUnassignedFeats(Character character) {
        return repository.findAllUnassignedFeats(character);
    }


}
