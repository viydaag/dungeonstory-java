package com.dungeonstory.backend.service.mock;

import com.dungeonstory.backend.data.Skill;
import com.dungeonstory.backend.repository.mock.MockSkillRepository;
import com.dungeonstory.backend.service.AbstractDataService;
import com.dungeonstory.backend.service.SkillDataService;

public class MockSkillService extends AbstractDataService<Skill, Long> implements SkillDataService {

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
        setEntityFactory(() -> new Skill());
        setRepository(new MockSkillRepository());
    }

}
