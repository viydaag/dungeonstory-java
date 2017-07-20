package com.dungeonstory.backend.service;

import java.util.List;

import com.dungeonstory.backend.data.ClassSpecialization;
import com.dungeonstory.backend.data.Deity;

public interface DeityDataService extends DataService<Deity, Long> {

    public List<Deity> findAllByDomain(ClassSpecialization domain);
}
