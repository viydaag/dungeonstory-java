package com.dungeonstory.backend.service.mock;

import java.util.ArrayList;
import java.util.List;

import com.dungeonstory.backend.data.Character;
import com.dungeonstory.backend.data.Feat;
import com.dungeonstory.backend.repository.mock.MockFeatRepository;
import com.dungeonstory.backend.service.AbstractDataService;
import com.dungeonstory.backend.service.FeatDataService;

public class MockFeatService extends AbstractDataService<Feat, Long> implements FeatDataService {

    private static final long serialVersionUID = 2149292729288574964L;

    private static MockFeatService instance = null;

    public static synchronized MockFeatService getInstance() {
        if (instance == null) {
            instance = new MockFeatService();
        }
        return instance;
    }

    private MockFeatService() {
        super();
        setEntityFactory(() -> new Feat());
        setRepository(new MockFeatRepository());
    }

    @Override
    public List<Feat> findAllFeats() {
        return ((MockFeatRepository) entityRepository).findAllFeats();
    }

    @Override
    public List<Feat> findAllClassFeatures() {
        return ((MockFeatRepository) entityRepository).findAllClassFeatures();
    }

    @Override
    public List<Feat> findAllFeatsExcept(Feat feat) {
        // TODO Auto-generated method stub
        return new ArrayList<Feat>();
    }

    @Override
    public List<Feat> findAllUnassignedFeats(Character character) {
        // TODO Auto-generated method stub
        return new ArrayList<Feat>();
    }

    @Override
    public List<Feat> findAllClassFeaturesWithoutParent() {
        // TODO Auto-generated method stub
        return new ArrayList<Feat>();
    }

    @Override
    public List<Feat> findAllClassFeaturesWithoutChildren() {
        // TODO Auto-generated method stub
        return new ArrayList<Feat>();
    }

    @Override
    public List<Feat> findAllClassFeatureExcept(Feat feat) {
        // TODO Auto-generated method stub
        return new ArrayList<Feat>();
    }

}
