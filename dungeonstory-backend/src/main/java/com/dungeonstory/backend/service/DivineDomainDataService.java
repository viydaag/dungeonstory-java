package com.dungeonstory.backend.service;

import java.util.List;

import com.dungeonstory.backend.data.Deity;
import com.dungeonstory.backend.data.DivineDomain;

public interface DivineDomainDataService extends DataService<DivineDomain, Long> {
    
    public List<DivineDomain> findAllByDeity(Deity deity);

}
