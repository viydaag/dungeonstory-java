package com.dungeonstory.backend.service;

import com.dungeonstory.backend.Configuration;
import com.dungeonstory.backend.data.Background;
import com.dungeonstory.backend.data.CreatureType;
import com.dungeonstory.backend.data.DSClass;
import com.dungeonstory.backend.data.Deity;
import com.dungeonstory.backend.data.Race;
import com.dungeonstory.backend.data.Region;
import com.dungeonstory.backend.data.Skill;
import com.dungeonstory.backend.service.impl.AdventureService;
import com.dungeonstory.backend.service.impl.AlignmentService;
import com.dungeonstory.backend.service.impl.BackgroundService;
import com.dungeonstory.backend.service.impl.CharacterService;
import com.dungeonstory.backend.service.impl.ClassService;
import com.dungeonstory.backend.service.impl.CreatureTypeService;
import com.dungeonstory.backend.service.impl.DeityService;
import com.dungeonstory.backend.service.impl.DivineDomainService;
import com.dungeonstory.backend.service.impl.EquipmentService;
import com.dungeonstory.backend.service.impl.FeatService;
import com.dungeonstory.backend.service.impl.LanguageService;
import com.dungeonstory.backend.service.impl.RaceService;
import com.dungeonstory.backend.service.impl.RegionService;
import com.dungeonstory.backend.service.impl.SkillService;
import com.dungeonstory.backend.service.impl.SpellService;
import com.dungeonstory.backend.service.impl.UserService;
import com.dungeonstory.backend.service.mock.MockAdventureService;
import com.dungeonstory.backend.service.mock.MockAlignmentService;
import com.dungeonstory.backend.service.mock.MockBackgroundService;
import com.dungeonstory.backend.service.mock.MockCharacterService;
import com.dungeonstory.backend.service.mock.MockClassService;
import com.dungeonstory.backend.service.mock.MockCreatureTypeService;
import com.dungeonstory.backend.service.mock.MockDeityService;
import com.dungeonstory.backend.service.mock.MockDivineDomainService;
import com.dungeonstory.backend.service.mock.MockEquipmentService;
import com.dungeonstory.backend.service.mock.MockFeatService;
import com.dungeonstory.backend.service.mock.MockLanguageService;
import com.dungeonstory.backend.service.mock.MockRaceService;
import com.dungeonstory.backend.service.mock.MockRegionService;
import com.dungeonstory.backend.service.mock.MockSkillService;
import com.dungeonstory.backend.service.mock.MockSpellService;
import com.dungeonstory.backend.service.mock.MockUserService;

public abstract class Services {
    
    public static DataService<Region, Long> getRegionService() {
        if (Configuration.getInstance().isMock()) {
            return MockRegionService.getInstance();
        }
        return RegionService.getInstance();
    }
    
    public static AlignmentDataService getAlignmentService() {
        if (Configuration.getInstance().isMock()) {
            return MockAlignmentService.getInstance();
        }
        return AlignmentService.getInstance();
    }
    
    public static LanguageDataService getLanguageService() {
        if (Configuration.getInstance().isMock()) {
            return MockLanguageService.getInstance();
        }
        return LanguageService.getInstance();
    }
    
    public static FeatDataService getFeatService() {
        if (Configuration.getInstance().isMock()) {
            return MockFeatService.getInstance();
        }
        return FeatService.getInstance();
    }
    
    public static CharacterDataService getCharacterService() {
        if (Configuration.getInstance().isMock()) {
            return MockCharacterService.getInstance();
        }
        return CharacterService.getInstance();
    }
    
    public static DivineDomainDataService getDivineDomainService() {
        if (Configuration.getInstance().isMock()) {
            return MockDivineDomainService.getInstance();
        }
        return DivineDomainService.getInstance();
    }
    
    public static SpellDataService getSpellService() {
        if (Configuration.getInstance().isMock()) {
            return MockSpellService.getInstance();
        }
        return SpellService.getInstance();
    }
    
    public static UserDataService getUserService() {
        if (Configuration.getInstance().isMock()) {
            return MockUserService.getInstance();
        }
        return UserService.getInstance();
    }
    
    public static EquipmentDataService getEquipmentService() {
        if (Configuration.getInstance().isMock()) {
            return MockEquipmentService.getInstance();
        }
        return EquipmentService.getInstance();
    }
    
    public static AdventureDataService getAdventureService() {
        if (Configuration.getInstance().isMock()) {
            return MockAdventureService.getInstance();
        }
        return AdventureService.getInstance();
    }
    
    public static DataService<Background, Long> getBackgroundService() {
        if (Configuration.getInstance().isMock()) {
            return MockBackgroundService.getInstance();
        }
        return BackgroundService.getInstance();
    }
    
    public static DataService<Skill, Long> getSkillService() {
        if (Configuration.getInstance().isMock()) {
            return MockSkillService.getInstance();
        }
        return SkillService.getInstance();
    }
    
    public static DataService<DSClass, Long> getClassService() {
        if (Configuration.getInstance().isMock()) {
            return MockClassService.getInstance();
        }
        return ClassService.getInstance();
    }
    
    public static DataService<CreatureType, Long> getCreatureTypeService() {
        if (Configuration.getInstance().isMock()) {
            return MockCreatureTypeService.getInstance();
        }
        return CreatureTypeService.getInstance();
    }
    
    public static DataService<Deity, Long> getDeityService() {
        if (Configuration.getInstance().isMock()) {
            return MockDeityService.getInstance();
        }
        return DeityService.getInstance();
    }

    public static DataService<Race, Long> getRaceService() {
        if (Configuration.getInstance().isMock()) {
            return MockRaceService.getInstance();
        }
        return RaceService.getInstance();
    }


}
