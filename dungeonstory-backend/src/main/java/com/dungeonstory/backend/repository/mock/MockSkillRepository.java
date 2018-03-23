package com.dungeonstory.backend.repository.mock;

import com.dungeonstory.backend.data.Skill;

public class MockSkillRepository extends MockAbstractRepository<Skill> {

    private static Long idSkill = 1L;

    public MockSkillRepository() {
        super();
    }

    @Override
    public void init() {
        
    }

    @Override
    public void setId(Skill entity) {
        if (entity.getId() == null) {
            entity.setId(idSkill++);
        }
    }

}
