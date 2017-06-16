package com.dungeonstory.backend.service;

import java.util.List;

import com.dungeonstory.backend.data.ClassSpecialization;

public interface ClassSpecializationDataService extends DataService<ClassSpecialization, Long> {

    public List<ClassSpecialization> findAllDivineDomainSpecializations();

}
