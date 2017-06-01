package com.dungeonstory.backend.service.mock;

import java.util.ArrayList;
import java.util.List;

import com.dungeonstory.backend.data.ClassFeature;
import com.dungeonstory.backend.repository.mock.MockClassFeatureRepository;
import com.dungeonstory.backend.service.AbstractDataService;
import com.dungeonstory.backend.service.ClassFeatureDataService;

public class MockClassFeatureService extends AbstractDataService<ClassFeature, Long> implements ClassFeatureDataService {

    private static final long serialVersionUID = 4362753599268254537L;

    private static MockClassFeatureService instance = null;

    public static synchronized MockClassFeatureService getInstance() {
        if (instance == null) {
            instance = new MockClassFeatureService();
        }
        return instance;
    }

    private MockClassFeatureService() {
        super();
        setEntityFactory(() -> new ClassFeature());
        setRepository(new MockClassFeatureRepository());
    }


    @Override
    public List<ClassFeature> findAllClassFeaturesWithoutParent() {
        // TODO Auto-generated method stub
        return new ArrayList<ClassFeature>();
    }

    @Override
    public List<ClassFeature> findAllClassFeaturesWithoutChildren() {
        // TODO Auto-generated method stub
        return new ArrayList<ClassFeature>();
    }

    @Override
    public List<ClassFeature> findAllClassFeatureExcept(ClassFeature ClassFeature) {
        // TODO Auto-generated method stub
        return new ArrayList<ClassFeature>();
    }

}
