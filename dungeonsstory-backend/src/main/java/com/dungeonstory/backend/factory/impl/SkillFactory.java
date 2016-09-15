package com.dungeonstory.backend.factory.impl;

import com.dungeonstory.backend.data.Skill;
import com.dungeonstory.backend.factory.Factory;

public class SkillFactory implements Factory<Skill> {

    private static final long serialVersionUID = 5557817143180738685L;

    @Override
    public Skill create() {
        return new Skill();
    }

}
