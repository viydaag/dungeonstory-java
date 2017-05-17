package com.dungeonstory.backend.mock;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import com.dungeonstory.backend.data.Ability;
import com.dungeonstory.backend.data.AccessRole;
import com.dungeonstory.backend.data.AccessRole.RoleType;
import com.dungeonstory.backend.data.Adventure;
import com.dungeonstory.backend.data.Adventure.AdventureStatus;
import com.dungeonstory.backend.data.Alignment;
import com.dungeonstory.backend.data.ArmorType;
import com.dungeonstory.backend.data.Background;
import com.dungeonstory.backend.data.CreatureType;
import com.dungeonstory.backend.data.DSClass;
import com.dungeonstory.backend.data.DamageType;
import com.dungeonstory.backend.data.Deity;
import com.dungeonstory.backend.data.DivineDomain;
import com.dungeonstory.backend.data.Feat;
import com.dungeonstory.backend.data.Feat.FeatUsage;
import com.dungeonstory.backend.data.Level;
import com.dungeonstory.backend.data.Message;
import com.dungeonstory.backend.data.Race;
import com.dungeonstory.backend.data.Region;
import com.dungeonstory.backend.data.Shop;
import com.dungeonstory.backend.data.Skill;
import com.dungeonstory.backend.data.User;
import com.dungeonstory.backend.data.User.UserStatus;
import com.dungeonstory.backend.data.WeaponType;
import com.dungeonstory.backend.data.WeaponType.HandleType;
import com.dungeonstory.backend.data.WeaponType.ProficiencyType;
import com.dungeonstory.backend.data.WeaponType.RangeType;
import com.dungeonstory.backend.data.WeaponType.SizeType;
import com.dungeonstory.backend.data.WeaponType.UsageType;
import com.dungeonstory.backend.service.impl.UserService;
import com.dungeonstory.backend.service.mock.MockAbilityService;
import com.dungeonstory.backend.service.mock.MockAccessRoleService;
import com.dungeonstory.backend.service.mock.MockDamageTypeService;
import com.dungeonstory.backend.service.mock.MockLanguageService;
import com.dungeonstory.backend.service.mock.MockLevelService;
import com.dungeonstory.backend.service.mock.MockUserService;

public class MockDataGenerator {

    private static final String      storedAbilities[][]  = new String[][] { { "Force", "FOR" }, { "Dextérité", "DEX" },
            { "Constitution", "CON" }, { "Intelligence", "INT" }, { "Sagesse", "SAG" }, { "Charisme", "CHA" } };

    private static final String      storedAlignment[][]  = new String[][] { { "Loyal Bon" }, { "Neutre Bon" },
            { "Chaotique Bon" }, { "Loyal Neutre" }, { "Neutre strict" }, { "Chaotique Neutre" }, { "Loyal Mauvais" },
            { "Neutre Mauvais" }, { "Chaotique Mauvais" } };

    private static final String      storedDamageType[][] = new String[][] { { "Tranchant" }, { "Contandant" },
            { "Perçant" }, { "Feu" }, { "Froid" }, { "Acide" }, { "Électricité" }, { "Nécrotique" }, { "Force" },
            { "Magique" }, { "Radiant" }, };

    private static final String      storedSkills[][]     = new String[][] { { "Athlétisme", "1" },
            { "Acrobatie", "2" }, { "Vol à la tire", "2" }, { "Furtivité", "2" }, { "Arcane", "4" },
            { "Histoire", "4" }, { "Investigation", "4" }, { "Nature", "4" }, { "Religion", "4" },
            { "Manipulation des animaux", "5" }, { "Perspicacité", "5" }, { "Soin", "5" }, { "Perception", "5" },
            { "Survie", "5" }, { "Tromperie", "6" }, { "Intimidation", "6" }, { "Performance", "6" },
            { "Persuasion", "6" } };

    private static final Integer[][] storedLevels         = new Integer[][] { { 1, 1000, 1 }, { 2, 2000, 2 } };

    private static final String[][]  storedRegions        = new String[][] { { "test region" }, { "another region" } };

    private static final String[][]  storedClass          = new String[][] { { "Guerrier" }, { "Mage" }, { "Voleur" },
            { "Barde" }, { "Sorcier" }, { "Paladin" }, { "Rodeur" }, { "Druide" }, { "Clerc" }, { "Barbare" }, };

    private static final String[][]  storedRoles          = new String[][] { { "Administrateur", "ADMIN" },
            { "Joueur", "PLAYER" }, { "Modérateur", "MODERATOR" } };

    private static final String[][]  storedUsers          = new String[][] {
            { "admin", "admin", "admin", "0", "ACTIVE" }, { "test", "test", "user", "1", "ACTIVE" },
            { "inactive", "inactive", "user", "1", "INACTIVE" },
            { "waiting", "waiting", "user", "1", "WAITING_FOR_APPROBATION" } };

    private static final String[][]  storedWeaponTypes    = new String[][] {
            { "Dague", "SIMPLE", "LIGHT", "ONE_HANDED", "MELEE_RANGE", "THROWN", "1d4", "1" } };

    private static final String[][]  storedArmorTypes     = new String[][] {
            { "Cuir", "LIGHT", "-1", "12", "false", "1", "1" } };

    private static final String[][]  storedRaces          = new String[][] {
            { "Humain", "1", "1", "1", "1", "1", "1", "16", "60", "5'4\"", "150" },
            { "Elfe", "0", "1", "0", "1", "0", "0", "75", "800", "5'0\"", "90" }};

    private static final String[][]  storedFeats          = new String[][] { { "feat1", "ACTION" },
            { "feat2", "PASSIVE" }, { "feat3", "REACTION" } };

    public static List<Ability> createAbilities() {
        List<Ability> abilities = new ArrayList<Ability>();
        for (String[] ability : storedAbilities) {
            abilities.add(new Ability(ability[0], ability[1], ""));
        }
        return abilities;
    }

    public static List<Alignment> createAlignments() {
        List<Alignment> alignments = new ArrayList<Alignment>();
        for (String[] alignment : storedAlignment) {
            alignments.add(new Alignment(alignment[0], "", "", true));
        }
        return alignments;
    }

    public static List<DamageType> createDamageTypes() {
        List<DamageType> types = new ArrayList<DamageType>();
        for (String[] type : storedDamageType) {
            types.add(new DamageType(type[0]));
        }
        return types;
    }

    public static List<Skill> createSkills() {
        List<Skill> skills = new ArrayList<Skill>();
        Collection<Ability> abilities = MockAbilityService.getInstance().findAll();
        for (String[] skill : storedSkills) {
            Optional<Ability> ability = abilities.stream().filter(a -> a.getId().equals(Long.valueOf(skill[1])))
                    .findFirst();
            if (ability.isPresent()) {
                skills.add(new Skill(skill[0], ability.get()));
            }
        }
        return skills;
    }

    public static List<Region> createRegions() {
        List<Region> regions = new ArrayList<Region>();
        for (String[] region : storedRegions) {
            regions.add(new Region(region[0]));
        }
        return regions;
    }

    public static List<Level> createLevels() {
        List<Level> levels = new ArrayList<Level>();
        for (Integer[] level : storedLevels) {
            levels.add(new Level(level[0], level[1], level[2]));
        }
        return levels;
    }

    public static List<User> createUsers() {
        List<AccessRole> roles = MockAccessRoleService.getInstance().findAll();
        List<User> users = new ArrayList<User>();
        for (String[] user : storedUsers) {
            users.add(new User(user[0], user[1], roles.get(Integer.parseInt(user[3])), user[1], "",
                    UserStatus.valueOf(user[4])));
        }
        return users;
    }

    public static List<AccessRole> createRoles() {
        List<AccessRole> roles = new ArrayList<AccessRole>();
        for (String[] roleString : storedRoles) {
            AccessRole role = new AccessRole(roleString[0], RoleType.valueOf(roleString[1]));
            roles.add(role);
        }
        return roles;
    }

    public static List<DSClass> createClasses() {
        List<DSClass> classes = new ArrayList<DSClass>();
        for (String[] tab : storedClass) {
            DSClass dsClass = new DSClass();
            dsClass.setName(tab[0]);
            classes.add(dsClass);
        }
        return classes;
    }

    public static List<WeaponType> createWeaponTypes() {
        List<WeaponType> types = new ArrayList<WeaponType>();
        Collection<DamageType> damageTypes = MockDamageTypeService.getInstance().findAll();
        if (!damageTypes.isEmpty()) {
            for (String[] tab : storedWeaponTypes) {
                WeaponType type = new WeaponType(tab[0], ProficiencyType.valueOf(tab[1]), SizeType.valueOf(tab[2]),
                        HandleType.valueOf(tab[3]), UsageType.valueOf(tab[4]), damageTypes.iterator().next());
                type.setRangeType(RangeType.valueOf(tab[5]));
                type.setOneHandBaseDamage(tab[6]);
                type.setBaseWeight(Integer.parseInt(tab[7]));
                types.add(type);
            }
        }
        return types;
    }

    public static List<ArmorType> createArmorTypes() {
        List<ArmorType> types = new ArrayList<ArmorType>();
        for (String[] tab : storedArmorTypes) {
            Integer maxDexBonus = tab[2] == null ? null : Integer.valueOf(tab[2]);
            ArmorType.ProficiencyType armorProficiency = ArmorType.ProficiencyType.valueOf(tab[1]);
            int armorClass = Integer.parseInt(tab[3]);
            boolean stealthDisavantage = Boolean.parseBoolean(tab[4]);
            int minStrength = Integer.parseInt(tab[5]);
            int weight = Integer.parseInt(tab[6]);
            ArmorType type = new ArmorType(tab[0], "", armorProficiency, maxDexBonus, armorClass, stealthDisavantage,
                    minStrength, weight, 10);
            types.add(type);
        }
        return types;
    }

    public static List<Race> createRaces() {
        List<Race> races = new ArrayList<Race>();
        for (String[] tab : storedRaces) {
            Race race = new Race(tab[0]);
            race.setStrModifier(Integer.parseInt(tab[1]));
            race.setDexModifier(Integer.parseInt(tab[2]));
            race.setConModifier(Integer.parseInt(tab[3]));
            race.setIntModifier(Integer.parseInt(tab[4]));
            race.setWisModifier(Integer.parseInt(tab[5]));
            race.setChaModifier(Integer.parseInt(tab[6]));
            race.setMinAge(Integer.parseInt(tab[7]));
            race.setMaxAge(Integer.parseInt(tab[8]));
            race.setAverageHeight(tab[9]);
            race.setAverageWeight(Integer.parseInt(tab[10]));
            races.add(race);
        }
        return races;
    }

    public static List<Feat> createFeats() {
        List<Feat> feats = new ArrayList<Feat>();
        for (String[] tab : storedFeats) {
            feats.add(new Feat(tab[0], "", FeatUsage.valueOf(tab[1])));
        }
        return feats;
    }

    public static List<Shop> createShops() {
        // TODO Auto-generated method stub
        return null;
    }

    public static List<Adventure> createAdventures() {

        ArrayList<Adventure> adventures = new ArrayList<>();
        Adventure openedAdventure = new Adventure();
        openedAdventure.setName("Aventure ouverte");
        openedAdventure.setDescription("");
        openedAdventure.setStatus(AdventureStatus.OPENED);
        openedAdventure.setChallengeRating(MockLevelService.getInstance().read(1L));
        User user = MockUserService.getInstance().findByUsername("admin");
        openedAdventure.setCreator(user);
        Message m1 = new Message("message 1");
        m1.setCreator(user);
        openedAdventure.addMessage(m1);
        Message m2 = new Message("message 2");
        adventures.add(openedAdventure);
        openedAdventure.addMessage(m2);

        Adventure startedAdventure = new Adventure();
        startedAdventure.setName("Aventure commencée");
        startedAdventure.setDescription("");
        startedAdventure.setStatus(AdventureStatus.STARTED);
        startedAdventure.setChallengeRating(MockLevelService.getInstance().read(1L));
        startedAdventure.setCreator(user);
        startedAdventure.addMessage(new Message("message 1"));
        adventures.add(startedAdventure);

        Adventure cancelledAdventure = new Adventure();
        cancelledAdventure.setName("Aventure annulée");
        cancelledAdventure.setDescription("");
        cancelledAdventure.setStatus(AdventureStatus.CANCELLED);
        cancelledAdventure.setChallengeRating(MockLevelService.getInstance().read(1L));
        cancelledAdventure.setCreator(user);
        cancelledAdventure.addMessage(new Message("message 1", user));
        adventures.add(cancelledAdventure);

        Adventure closedAdventure = new Adventure();
        closedAdventure.setName("Aventure fermée");
        closedAdventure.setDescription("");
        closedAdventure.setStatus(AdventureStatus.CLOSED);
        closedAdventure.setChallengeRating(MockLevelService.getInstance().read(1L));
        closedAdventure.setCreator(user);
        closedAdventure.addMessage(new Message("message 1", user));
        adventures.add(closedAdventure);

        return adventures;
    }

    public static List<DivineDomain> createDivineDomains() {
        // TODO Auto-generated method stub
        return new ArrayList<>();
    }

    public static List<Deity> createDeities() {
        // TODO Auto-generated method stub
        return new ArrayList<>();
    }

    public static List<CreatureType> createCreatureTypes() {
        // TODO Auto-generated method stub
        return new ArrayList<>();
    }

    public static List<Background> createBackgrounds() {
        ArrayList<Background> backgrounds = new ArrayList<>();
        backgrounds.add(new Background("background1"));
        backgrounds.add(new Background("background2"));
        return backgrounds;
    }

}
