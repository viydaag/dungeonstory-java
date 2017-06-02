package com.dungeonstory.backend.service.impl;

import java.util.List;

import com.dungeonstory.backend.data.ClassFeature;
import com.dungeonstory.backend.repository.impl.ClassFeatureRepository;
import com.dungeonstory.backend.service.AbstractDataService;
import com.dungeonstory.backend.service.ClassFeatureDataService;

public class ClassFeatureService extends AbstractDataService<ClassFeature, Long> implements ClassFeatureDataService {

    private static final long serialVersionUID = 307446360704287234L;
    
    private static ClassFeatureService instance = null;
    private ClassFeatureRepository repository = null;

    public static synchronized ClassFeatureService getInstance() {
        if (instance == null) {
            instance = new ClassFeatureService();
        }
        return instance;
    }

    private ClassFeatureService() {
        super();
        repository = new ClassFeatureRepository();
        setEntityFactory(() -> new ClassFeature());
        setRepository(repository);
    }


    @Override
    public List<ClassFeature> findAllClassFeaturesWithoutParent() {
        return repository.findAllClassFeaturesWithoutParent();
    }

    @Override
    public List<ClassFeature> findAllClassFeaturesWithoutChildren() {
        return repository.findAllClassFeaturesWithoutChildren();
    }

    @Override
    public List<ClassFeature> findAllClassFeatureExcept(ClassFeature feat) {
        return repository.findAllClassFeatureExcept(feat);
    }

}
