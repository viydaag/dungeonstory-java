package com.dungeonstory.backend.service;

import java.util.List;

import com.dungeonstory.backend.data.ClassFeature;

public interface ClassFeatureDataService extends DataService<ClassFeature, Long> {

    public List<ClassFeature> findAllClassFeaturesWithoutParent();

    public List<ClassFeature> findAllClassFeaturesWithoutChildren();

    public List<ClassFeature> findAllClassFeatureExcept(ClassFeature feat);
	
}
