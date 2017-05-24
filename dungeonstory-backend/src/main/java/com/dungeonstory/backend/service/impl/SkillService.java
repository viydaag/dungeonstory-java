package com.dungeonstory.backend.service.impl;

import com.dungeonstory.backend.data.Skill;
import com.dungeonstory.backend.factory.impl.SkillFactory;
import com.dungeonstory.backend.repository.impl.SkillRepository;
import com.dungeonstory.backend.service.AbstractDataService;
import com.dungeonstory.backend.service.SkillDataService;

public class SkillService extends AbstractDataService<Skill, Long> implements SkillDataService {

    private static final long serialVersionUID = -5037432994965855361L;

    private static SkillService instance = null;

    public static synchronized SkillService getInstance() {
        if (instance == null) {
            instance = new SkillService();
        }
        return instance;
    }

    private SkillService() {
        super();
        setEntityFactory(new SkillFactory());
        setRepository(new SkillRepository());
    }

}
