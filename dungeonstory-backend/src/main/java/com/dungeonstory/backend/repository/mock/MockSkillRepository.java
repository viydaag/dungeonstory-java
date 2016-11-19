package com.dungeonstory.backend.repository.mock;

import java.util.List;

import com.dungeonstory.backend.data.Skill;
import com.dungeonstory.backend.mock.MockDataGenerator;

public class MockSkillRepository extends MockAbstractRepository<Skill> {

    private static Long idSkill = 1L;

    public MockSkillRepository() {
        super();
    }

    @Override
    public void init() {
        List<Skill> list = MockDataGenerator.createSkills();
        list.stream().forEach(this::create);
    }

    @Override
    public void setId(Skill entity) {
        if (entity.getId() == null) {
            entity.setId(idSkill++);
        }
    }

}
