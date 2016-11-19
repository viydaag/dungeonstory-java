package com.dungeonstory.backend.service;

import java.util.List;

import com.dungeonstory.backend.data.Alignment;

public interface AlignmentDataService extends DataService<Alignment, Long> {

    public List<Alignment> findAllPlayable();

}
