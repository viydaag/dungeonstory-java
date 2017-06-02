package com.dungeonstory.backend.service;

import com.dungeonstory.backend.Configuration;
import com.dungeonstory.backend.data.Region;
import com.dungeonstory.backend.service.impl.AbilityService;
import com.dungeonstory.backend.service.impl.AdventureService;
import com.dungeonstory.backend.service.impl.AlignmentService;
import com.dungeonstory.backend.service.impl.ArmorTypeService;
import com.dungeonstory.backend.service.impl.BackgroundService;
import com.dungeonstory.backend.service.impl.CharacterService;
import com.dungeonstory.backend.service.impl.CityService;
import com.dungeonstory.backend.service.impl.ClassFeatureService;
import com.dungeonstory.backend.service.impl.ClassService;
import com.dungeonstory.backend.service.impl.ClassSpecializationService;
import com.dungeonstory.backend.service.impl.CreatureTypeService;
import com.dungeonstory.backend.service.impl.DamageTypeService;
import com.dungeonstory.backend.service.impl.DeityService;
import com.dungeonstory.backend.service.impl.DivineDomainService;
import com.dungeonstory.backend.service.impl.EquipmentService;
import com.dungeonstory.backend.service.impl.FeatService;
import com.dungeonstory.backend.service.impl.LanguageService;
import com.dungeonstory.backend.service.impl.LevelService;
import com.dungeonstory.backend.service.impl.RaceService;
import com.dungeonstory.backend.service.impl.RegionService;
import com.dungeonstory.backend.service.impl.ShopService;
import com.dungeonstory.backend.service.impl.SkillService;
import com.dungeonstory.backend.service.impl.SpellService;
import com.dungeonstory.backend.service.impl.UserService;
import com.dungeonstory.backend.service.impl.WeaponTypeService;
import com.dungeonstory.backend.service.mock.MockAbilityService;
import com.dungeonstory.backend.service.mock.MockAdventureService;
import com.dungeonstory.backend.service.mock.MockAlignmentService;
import com.dungeonstory.backend.service.mock.MockArmorTypeService;
import com.dungeonstory.backend.service.mock.MockBackgroundService;
import com.dungeonstory.backend.service.mock.MockCharacterService;
import com.dungeonstory.backend.service.mock.MockCityService;
import com.dungeonstory.backend.service.mock.MockClassFeatureService;
import com.dungeonstory.backend.service.mock.MockClassService;
import com.dungeonstory.backend.service.mock.MockClassSpecializationService;
import com.dungeonstory.backend.service.mock.MockCreatureTypeService;
import com.dungeonstory.backend.service.mock.MockDamageTypeService;
import com.dungeonstory.backend.service.mock.MockDeityService;
import com.dungeonstory.backend.service.mock.MockDivineDomainService;
import com.dungeonstory.backend.service.mock.MockEquipmentService;
import com.dungeonstory.backend.service.mock.MockFeatService;
import com.dungeonstory.backend.service.mock.MockLanguageService;
import com.dungeonstory.backend.service.mock.MockLevelService;
import com.dungeonstory.backend.service.mock.MockRaceService;
import com.dungeonstory.backend.service.mock.MockRegionService;
import com.dungeonstory.backend.service.mock.MockShopService;
import com.dungeonstory.backend.service.mock.MockSkillService;
import com.dungeonstory.backend.service.mock.MockSpellService;
import com.dungeonstory.backend.service.mock.MockUserService;
import com.dungeonstory.backend.service.mock.MockWeaponTypeService;

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
    
    public static ClassFeatureDataService getClassFeatureService() {
        if (Configuration.getInstance().isMock()) {
            return MockClassFeatureService.getInstance();
        }
        return ClassFeatureService.getInstance();
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
    
    public static BackgroundDataService getBackgroundService() {
        if (Configuration.getInstance().isMock()) {
            return MockBackgroundService.getInstance();
        }
        return BackgroundService.getInstance();
    }
    
    public static SkillDataService getSkillService() {
        if (Configuration.getInstance().isMock()) {
            return MockSkillService.getInstance();
        }
        return SkillService.getInstance();
    }
    
    public static ClassDataService getClassService() {
        if (Configuration.getInstance().isMock()) {
            return MockClassService.getInstance();
        }
        return ClassService.getInstance();
    }
    
    public static CreatureTypeDataService getCreatureTypeService() {
        if (Configuration.getInstance().isMock()) {
            return MockCreatureTypeService.getInstance();
        }
        return CreatureTypeService.getInstance();
    }
    
    public static DeityDataService getDeityService() {
        if (Configuration.getInstance().isMock()) {
            return MockDeityService.getInstance();
        }
        return DeityService.getInstance();
    }

    public static RaceDataService getRaceService() {
        if (Configuration.getInstance().isMock()) {
            return MockRaceService.getInstance();
        }
        return RaceService.getInstance();
    }

    public static LevelDataService getLevelService() {
        if (Configuration.getInstance().isMock()) {
            return MockLevelService.getInstance();
        }
        return LevelService.getInstance();
    }

    public static WeaponTypeDataService getWeaponTypeService() {
        if (Configuration.getInstance().isMock()) {
            return MockWeaponTypeService.getInstance();
        }
        return WeaponTypeService.getInstance();
    }
    
    public static ArmorTypeDataService getArmorTypeService() {
        if (Configuration.getInstance().isMock()) {
            return MockArmorTypeService.getInstance();
        }
        return ArmorTypeService.getInstance();
    }
    
    public static DamageTypeDataService getDamageTypeService() {
        if (Configuration.getInstance().isMock()) {
            return MockDamageTypeService.getInstance();
        }
        return DamageTypeService.getInstance();
    }

    public static AbilityDataService getAbilityService() {
        if (Configuration.getInstance().isMock()) {
            return MockAbilityService.getInstance();
        }
        return AbilityService.getInstance();
    }

    public static CityDataService getCityService() {
        if (Configuration.getInstance().isMock()) {
            return MockCityService.getInstance();
        }
        return CityService.getInstance();
    }

    public static ClassSpecializationDataService getClassSpecializationService() {
        if (Configuration.getInstance().isMock()) {
            return MockClassSpecializationService.getInstance();
        }
        return ClassSpecializationService.getInstance();
    }

    public static ShopDataService getShopService() {
        if (Configuration.getInstance().isMock()) {
            return MockShopService.getInstance();
        }
        return ShopService.getInstance();
    }


}
