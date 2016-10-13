package com.dungeonstory.backend.repository.impl;

import com.dungeonstory.backend.data.Skill;
import com.dungeonstory.backend.repository.AbstractRepository;

public class SkillRepository extends AbstractRepository<Skill, Long> {

    private static final long serialVersionUID = -582349526865630337L;

    @Override
    protected Class<Skill> getEntityClass() {
        return Skill.class;
    }

}
