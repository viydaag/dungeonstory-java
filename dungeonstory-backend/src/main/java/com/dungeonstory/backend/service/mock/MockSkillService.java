package com.dungeonstory.backend.service.mock;

import com.dungeonstory.backend.data.Skill;
import com.dungeonstory.backend.factory.impl.SkillFactory;
import com.dungeonstory.backend.repository.mock.MockSkillRepository;
import com.dungeonstory.backend.service.AbstractDataService;

public class MockSkillService extends AbstractDataService<Skill, Long> {

	private static final long serialVersionUID = -3373138453796994075L;
	
	private static MockSkillService instance = null;

    public static synchronized MockSkillService getInstance() {
        if (instance == null) {
            instance = new MockSkillService();
        }
        return instance;
    }

    private MockSkillService() {
        super();
        setEntityFactory(new SkillFactory());
        setRepository(new MockSkillRepository());
    }

}
