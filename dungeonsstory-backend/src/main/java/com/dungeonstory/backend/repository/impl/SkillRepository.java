package com.dungeonstory.backend.repository.impl;

import com.dungeonstory.backend.data.Skill;
import com.dungeonstory.backend.repository.AbstractRepository;

public class SkillRepository extends AbstractRepository<Skill, Long> {

    @Override
    protected Class<? extends Skill> getEntityClass() {
        return Skill.class;
    }

}
