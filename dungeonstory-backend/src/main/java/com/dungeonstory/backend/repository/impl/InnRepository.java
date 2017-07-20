package com.dungeonstory.backend.repository.impl;

import com.dungeonstory.backend.data.Inn;
import com.dungeonstory.backend.repository.AbstractRepository;

public class InnRepository extends AbstractRepository<Inn, Long> {

    private static final long serialVersionUID = -6896047396829225836L;

    @Override
    protected Class<Inn> getEntityClass() {
        return Inn.class;
    }

}
