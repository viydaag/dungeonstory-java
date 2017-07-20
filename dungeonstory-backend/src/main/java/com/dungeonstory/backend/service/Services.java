package com.dungeonstory.backend.service;

import com.dungeonstory.backend.Configuration;
import com.dungeonstory.backend.service.impl.*;
import com.dungeonstory.backend.service.mock.*;

public abstract class Services {
    
    public static RegionDataService getRegionService() {
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

    public static MonsterDataService getMonsterService() {
        if (Configuration.getInstance().isMock()) {
            return MockMonsterService.getInstance();
        }
        return MonsterService.getInstance();
    }
    
    public static InnDataService getInnService() {
        if (Configuration.getInstance().isMock()) {
            return MockInnService.getInstance();
        }
        return InnService.getInstance();
    }

    public static TempleDataService getTempleService() {
        if (Configuration.getInstance().isMock()) {
            return MockTempleService.getInstance();
        }
        return TempleService.getInstance();
    }


}
