package com.dungeonstory.backend.service;

import java.util.Set;

import com.dungeonstory.backend.data.enums.Alignment;

public interface AlignmentDataService {

    public Set<Alignment> findAllPlayable();

}
