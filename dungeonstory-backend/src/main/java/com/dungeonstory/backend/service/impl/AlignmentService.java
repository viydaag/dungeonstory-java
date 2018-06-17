package com.dungeonstory.backend.service.impl;

import java.util.EnumSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.dungeonstory.backend.data.enums.Alignment;
import com.dungeonstory.backend.service.AlignmentDataService;

public class AlignmentService implements AlignmentDataService {

    private static AlignmentService instance = null;

    public static synchronized AlignmentService getInstance() {
        if (instance == null) {
            instance = new AlignmentService();
        }
        return instance;
    }

    private AlignmentService() {
        super();
    }

    @Override
    public Set<Alignment> findAllPlayable() {
        return Stream.of(Alignment.values()).filter(Alignment::isPlayable).collect(
                Collectors.toCollection(() -> EnumSet.noneOf(Alignment.class)));
    }

}
