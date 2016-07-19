package com.dungeonstory.backend.factory.impl;

import com.dungeonstory.backend.data.Skill;
import com.dungeonstory.backend.factory.Factory;

public class SkillFactory implements Factory<Skill> {

    public SkillFactory() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public Skill create() {
        return new Skill();
    }

}
