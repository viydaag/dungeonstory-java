ALTER TABLE ClassEquipment DROP FOREIGN KEY FK_ClassEquipment_equipmentId
ALTER TABLE ClassEquipment DROP FOREIGN KEY FK_ClassEquipment_classId
ALTER TABLE Deity DROP FOREIGN KEY FK_Deity_alignmentId
ALTER TABLE Tool DROP FOREIGN KEY FK_Tool_id
ALTER TABLE Class DROP FOREIGN KEY FK_Class_spellCasingAbilityId
ALTER TABLE City DROP FOREIGN KEY FK_City_regionId
ALTER TABLE ClassSpecLevelFeature DROP FOREIGN KEY FK_ClassSpecLevelFeature_classSpecId
ALTER TABLE ClassSpecLevelFeature DROP FOREIGN KEY FK_ClassSpecLevelFeature_levelId
ALTER TABLE ClassSpecLevelFeature DROP FOREIGN KEY FK_ClassSpecLevelFeature_featId
ALTER TABLE ClassSpecialization DROP FOREIGN KEY FK_ClassSpecialization_classId
ALTER TABLE ClassLevelBonusFeat DROP FOREIGN KEY FK_ClassLevelBonusFeat_levelId
ALTER TABLE ClassLevelBonusFeat DROP FOREIGN KEY FK_ClassLevelBonusFeat_classId
ALTER TABLE ClassLevelBonusFeat DROP FOREIGN KEY FK_ClassLevelBonusFeat_featId
ALTER TABLE CharacterClass DROP FOREIGN KEY FK_CharacterClass_classId
ALTER TABLE CharacterClass DROP FOREIGN KEY FK_CharacterClass_characterId
ALTER TABLE DivineDomainSpell DROP FOREIGN KEY FK_DivineDomainSpell_spellId
ALTER TABLE DivineDomainSpell DROP FOREIGN KEY FK_DivineDomainSpell_domainId
ALTER TABLE ClassLevelBonus DROP FOREIGN KEY FK_ClassLevelBonus_levelId
ALTER TABLE ClassLevelBonus DROP FOREIGN KEY FK_ClassLevelBonus_classId
ALTER TABLE Feat DROP FOREIGN KEY FK_Feat_prerequisiteAbilityId
ALTER TABLE ClassSkill DROP FOREIGN KEY FK_ClassSkill_skillId
ALTER TABLE ClassSkill DROP FOREIGN KEY FK_ClassSkill_classId
ALTER TABLE CharacterEquipment DROP FOREIGN KEY FK_CharacterEquipment_characterId
ALTER TABLE CharacterEquipment DROP FOREIGN KEY FK_CharacterEquipment_equipmentId
ALTER TABLE Shop DROP FOREIGN KEY FK_Shop_cityId
ALTER TABLE WeaponType DROP FOREIGN KEY FK_WeaponType_damageTypeId
ALTER TABLE Spell DROP FOREIGN KEY FK_Spell_savingThrowAbilityId
ALTER TABLE Race DROP FOREIGN KEY FK_Race_damageTypeId
ALTER TABLE SpellEffect DROP FOREIGN KEY FK_SpellEffect_spellId
ALTER TABLE SpellEffect DROP FOREIGN KEY FK_SpellEffect_damageTypeId
ALTER TABLE Weapon DROP FOREIGN KEY FK_Weapon_additionalDamageTypeId
ALTER TABLE Weapon DROP FOREIGN KEY FK_Weapon_weaponTypeId
ALTER TABLE Weapon DROP FOREIGN KEY FK_Weapon_id
ALTER TABLE ShopEquipment DROP FOREIGN KEY FK_ShopEquipment_shopId
ALTER TABLE ShopEquipment DROP FOREIGN KEY FK_ShopEquipment_equipmentId
ALTER TABLE Armor DROP FOREIGN KEY FK_Armor_id
ALTER TABLE Armor DROP FOREIGN KEY FK_Armor_armorTypeId
ALTER TABLE Adventure DROP FOREIGN KEY FK_Adventure_creatorId
ALTER TABLE DSCharacter DROP FOREIGN KEY FK_DSCharacter_levelId
ALTER TABLE DSCharacter DROP FOREIGN KEY FK_DSCharacter_raceId
ALTER TABLE DSCharacter DROP FOREIGN KEY FK_DSCharacter_regionId
ALTER TABLE DSCharacter DROP FOREIGN KEY FK_DSCharacter_alignmentId
ALTER TABLE DSCharacter DROP FOREIGN KEY FK_DSCharacter_adventureId
ALTER TABLE User DROP FOREIGN KEY FK_User_roleId
ALTER TABLE User DROP FOREIGN KEY FK_User_characterId
ALTER TABLE Skill DROP FOREIGN KEY FK_Skill_keyAbilityId
ALTER TABLE Message DROP FOREIGN KEY FK_Message_adventureId
ALTER TABLE Message DROP FOREIGN KEY FK_Message_characterId
ALTER TABLE DeityDomain DROP FOREIGN KEY FK_DeityDomain_domainId
ALTER TABLE DeityDomain DROP FOREIGN KEY FK_DeityDomain_deityId
ALTER TABLE ClassSavingThrowProficiencies DROP FOREIGN KEY FK_ClassSavingThrowProficiencies_classId
ALTER TABLE ClassSavingThrowProficiencies DROP FOREIGN KEY FK_ClassSavingThrowProficiencies_abilityId
ALTER TABLE ClassArmorProficiencies DROP FOREIGN KEY FK_ClassArmorProficiencies_classId
ALTER TABLE ClassWeaponProficiencies DROP FOREIGN KEY FK_ClassWeaponProficiencies_weaponTypeId
ALTER TABLE ClassWeaponProficiencies DROP FOREIGN KEY FK_ClassWeaponProficiencies_classId
ALTER TABLE ClassSpell DROP FOREIGN KEY FK_ClassSpell_classId
ALTER TABLE ClassSpell DROP FOREIGN KEY FK_ClassSpell_spellId
ALTER TABLE CharacterFeat DROP FOREIGN KEY FK_CharacterFeat_featId
ALTER TABLE CharacterFeat DROP FOREIGN KEY FK_CharacterFeat_characterId
ALTER TABLE BackgroundSkillProficiencies DROP FOREIGN KEY FK_BackgroundSkillProficiencies_skillId
ALTER TABLE BackgroundSkillProficiencies DROP FOREIGN KEY FK_BackgroundSkillProficiencies_backgroundId
ALTER TABLE BackgroundToolProficiencies DROP FOREIGN KEY FK_BackgroundToolProficiencies_backgroundId
ALTER TABLE SpellComponentType DROP FOREIGN KEY FK_SpellComponentType_spellId
ALTER TABLE SpellComponent DROP FOREIGN KEY FK_SpellComponent_equipmentId
ALTER TABLE SpellComponent DROP FOREIGN KEY FK_SpellComponent_spellId
ALTER TABLE RaceLanguage DROP FOREIGN KEY FK_RaceLanguage_languageId
ALTER TABLE RaceLanguage DROP FOREIGN KEY FK_RaceLanguage_raceId
ALTER TABLE RaceSavingThrowProficiencies DROP FOREIGN KEY FK_RaceSavingThrowProficiencies_raceId
ALTER TABLE RaceArmorProficiencies DROP FOREIGN KEY FK_RaceArmorProficiencies_raceId
ALTER TABLE RaceWeaponProficiencies DROP FOREIGN KEY FK_RaceWeaponProficiencies_raceId
ALTER TABLE RaceWeaponProficiencies DROP FOREIGN KEY FK_RaceWeaponProficiencies_weaponTypeId
ALTER TABLE RaceSkillProficiencies DROP FOREIGN KEY FK_RaceSkillProficiencies_skillId
ALTER TABLE RaceSkillProficiencies DROP FOREIGN KEY FK_RaceSkillProficiencies_raceId
ALTER TABLE CharacterProficientSkill DROP FOREIGN KEY FK_CharacterProficientSkill_characterId
ALTER TABLE CharacterProficientSkill DROP FOREIGN KEY FK_CharacterProficientSkill_skillId
DROP TABLE ClassEquipment
DROP TABLE Deity
DROP TABLE Equipment
DROP TABLE Tool
DROP TABLE Class
DROP TABLE Ability
DROP TABLE City
DROP TABLE ClassSpecLevelFeature
DROP TABLE ClassSpecialization
DROP TABLE Alignment
DROP TABLE ClassLevelBonusFeat
DROP TABLE CharacterClass
DROP TABLE DivineDomainSpell
DROP TABLE ArmorType
DROP TABLE DamageType
DROP TABLE ClassLevelBonus
DROP TABLE DivineDomain
DROP TABLE Feat
DROP TABLE ClassSkill
DROP TABLE Region
DROP TABLE CharacterEquipment
DROP TABLE Background
DROP TABLE Shop
DROP TABLE WeaponType
DROP TABLE Level
DROP TABLE Spell
DROP TABLE Language
DROP TABLE Race
DROP TABLE SpellEffect
DROP TABLE Weapon
DROP TABLE ShopEquipment
DROP TABLE Armor
DROP TABLE Adventure
DROP TABLE DSCharacter
DROP TABLE AccessRole
DROP TABLE User
DROP TABLE Skill
DROP TABLE Message
DROP TABLE DeityDomain
DROP TABLE ClassSavingThrowProficiencies
DROP TABLE ClassArmorProficiencies
DROP TABLE ClassWeaponProficiencies
DROP TABLE ClassSpell
DROP TABLE CharacterFeat
DROP TABLE BackgroundSkillProficiencies
DROP TABLE BackgroundToolProficiencies
DROP TABLE SpellComponentType
DROP TABLE SpellComponent
DROP TABLE RaceLanguage
DROP TABLE RaceSavingThrowProficiencies
DROP TABLE RaceArmorProficiencies
DROP TABLE RaceWeaponProficiencies
DROP TABLE RaceSkillProficiencies
DROP TABLE CharacterProficientSkill
