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

    public static synchronized FeatService getInstance() {
        if (instance == null) {
            instance = new FeatService();
        }
        return instance;
    }

    private FeatService() {
        super();
        setEntityFactory(() -> new Feat());
        setRepository(new FeatRepository());
    }

    @Override
    public List<Feat> findAllFeats() {
        return ((FeatRepository) entityRepository).findAllFeats();
    }

    @Override
    public List<Feat> findAllClassFeatures() {
        return ((FeatRepository) entityRepository).findAllClassFeatures();
    }

    @Override
    public List<Feat> findAllFeatsExcept(Feat feat) {
        return ((FeatRepository) entityRepository).findAllFeatsExcept(feat);
    }

    @Override
    public List<Feat> findAllUnassignedFeats(Character character) {
        return ((FeatRepository) entityRepository).findAllUnassignedFeats(character);
    }

    @Override
    public List<Feat> findAllClassFeaturesWithoutParent() {
        return ((FeatRepository) entityRepository).findAllClassFeaturesWithoutParent();
    }

    @Override
    public List<Feat> findAllClassFeaturesWithoutChildren() {
        return ((FeatRepository) entityRepository).findAllClassFeaturesWithoutChildren();
    }

    @Override
    public List<Feat> findAllClassFeatureExcept(Feat feat) {
        return ((FeatRepository) entityRepository).findAllClassFeatureExcept(feat);
    }

}
