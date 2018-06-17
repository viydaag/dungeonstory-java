package com.dungeonstory.backend.mock;

import java.util.ArrayList;
import java.util.List;

import com.dungeonstory.backend.data.Adventure;
import com.dungeonstory.backend.data.Background;
import com.dungeonstory.backend.data.City;
import com.dungeonstory.backend.data.ClassFeature;
import com.dungeonstory.backend.data.ClassSpecialization;
import com.dungeonstory.backend.data.CreatureType;
import com.dungeonstory.backend.data.DSClass;
import com.dungeonstory.backend.data.Deity;
import com.dungeonstory.backend.data.Inn;
import com.dungeonstory.backend.data.Level;
import com.dungeonstory.backend.data.Message;
import com.dungeonstory.backend.data.Monster;
import com.dungeonstory.backend.data.Race;
import com.dungeonstory.backend.data.Region;
import com.dungeonstory.backend.data.Shop;
import com.dungeonstory.backend.data.Temple;
import com.dungeonstory.backend.data.User;
import com.dungeonstory.backend.data.WeaponType;
import com.dungeonstory.backend.data.enums.AccessRole;
import com.dungeonstory.backend.data.enums.Alignment;
import com.dungeonstory.backend.data.enums.DamageType;
import com.dungeonstory.backend.service.mock.MockCityService;
import com.dungeonstory.backend.service.mock.MockClassService;
import com.dungeonstory.backend.service.mock.MockDeityService;
import com.dungeonstory.backend.service.mock.MockLevelService;
import com.dungeonstory.backend.service.mock.MockRegionService;
import com.dungeonstory.backend.service.mock.MockUserService;

public class MockDataGenerator {

    private static final Integer[][] storedLevels = new Integer[][] { { 1, 1000, 1 }, { 2, 2000, 2 } };

    private static final String[][] storedRegions = new String[][] { { "test region" }, { "another region" } };

    private static final String[][] storedClass = new String[][] { { "Guerrier" }, { "Mage" }, { "Voleur" },
            { "Barde" }, { "Sorcier" }, { "Paladin" }, { "Rodeur" }, { "Druide" }, { "Clerc" }, { "Barbare" }, };

    private static final String[][] storedUsers = new String[][] { { "admin", "admin", "admin", "ADMIN", "ACTIVE" },
            { "test", "test", "user", "PLAYER", "ACTIVE" }, { "inactive", "inactive", "user", "PLAYER", "INACTIVE" },
            { "waiting", "waiting", "user", "PLAYER", "WAITING_FOR_APPROBATION" } };

    private static final String[][] storedWeaponTypes = new String[][] {
            { "Dague", "SIMPLE", "LIGHT", "ONE_HANDED", "MELEE_RANGE", "THROWN", "1d4", "1", "PERCING" } };

    private static final String[][] storedArmorTypes = new String[][] {
            { "Cuir", "LIGHT", "-1", "12", "false", "1", "1" } };

    private static final String[][] storedRaces = new String[][] {
            { "Humain", "1", "1", "1", "1", "1", "1", "16", "60", "5'4\"", "150" },
            { "Elfe", "0", "1", "0", "1", "0", "0", "75", "800", "5'0\"", "90" } };


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
        List<User> users = new ArrayList<User>();
        for (String[] user : storedUsers) {
            users.add(new User(user[0], user[1], AccessRole.valueOf(user[3]), user[1], "",
                    User.UserStatus.valueOf(user[4])));
        }
        return users;
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
        for (String[] tab : storedWeaponTypes) {
            WeaponType type = new WeaponType(tab[0], WeaponType.ProficiencyType.valueOf(tab[1]),
                    WeaponType.SizeType.valueOf(tab[2]), WeaponType.HandleType.valueOf(tab[3]),
                    WeaponType.UsageType.valueOf(tab[4]), DamageType.valueOf(tab[8]));
            type.setRangeType(WeaponType.RangeType.valueOf(tab[5]));
            type.setOneHandBaseDamage(tab[6]);
            type.setBaseWeight(Double.parseDouble(tab[7]));
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

    public static List<Shop> createShops() {
        ArrayList<Shop> shops = new ArrayList<>();

        Shop shop = new Shop();
        shop.setName("testShop");

        shops.add(shop);
        return shops;
    }

    public static List<Adventure> createAdventures() {

        ArrayList<Adventure> adventures = new ArrayList<>();
        Adventure openedAdventure = new Adventure();
        openedAdventure.setName("Aventure ouverte");
        openedAdventure.setDescription("");
        openedAdventure.setStatus(Adventure.AdventureStatus.OPENED);
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
        startedAdventure.setStatus(Adventure.AdventureStatus.STARTED);
        startedAdventure.setChallengeRating(MockLevelService.getInstance().read(1L));
        startedAdventure.setCreator(user);
        startedAdventure.addMessage(new Message("message 1"));
        adventures.add(startedAdventure);

        Adventure cancelledAdventure = new Adventure();
        cancelledAdventure.setName("Aventure annulée");
        cancelledAdventure.setDescription("");
        cancelledAdventure.setStatus(Adventure.AdventureStatus.CANCELLED);
        cancelledAdventure.setChallengeRating(MockLevelService.getInstance().read(1L));
        cancelledAdventure.setCreator(user);
        cancelledAdventure.addMessage(new Message("message 1", user));
        adventures.add(cancelledAdventure);

        Adventure closedAdventure = new Adventure();
        closedAdventure.setName("Aventure fermée");
        closedAdventure.setDescription("");
        closedAdventure.setStatus(Adventure.AdventureStatus.CLOSED);
        closedAdventure.setChallengeRating(MockLevelService.getInstance().read(1L));
        closedAdventure.setCreator(user);
        closedAdventure.addMessage(new Message("message 1", user));
        adventures.add(closedAdventure);

        return adventures;
    }

    public static List<Deity> createDeities() {

        ArrayList<Deity> deities = new ArrayList<>();

        Deity heaume = new Deity();
        heaume.setName("Heaume");
        heaume.setAlignment(Alignment.LAWFUL_GOOD);

        deities.add(heaume);
        return deities;
    }

    public static List<CreatureType> createCreatureTypes() {
        ArrayList<CreatureType> types = new ArrayList<>();
        CreatureType type1 = new CreatureType("Humanoïde");
        CreatureType type2 = new CreatureType("Bête");

        types.add(type1);
        types.add(type2);
        return types;
    }

    public static List<Background> createBackgrounds() {
        ArrayList<Background> backgrounds = new ArrayList<>();
        backgrounds.add(new Background("background1"));
        backgrounds.add(new Background("background2"));
        return backgrounds;
    }

    public static List<ClassSpecialization> createClassSpecializations() {
        ArrayList<ClassSpecialization> specs = new ArrayList<>();
        
        ClassSpecialization spec = new ClassSpecialization();
        spec.setName("spec1");
        spec.setParentClass(MockClassService.getInstance().read(1L));
        specs.add(spec);
        
        return specs;
    }

    public static List<ClassFeature> createClassFeatures() {
        // TODO Auto-generated method stub
        return new ArrayList<>();
    }

    public static List<Monster> createMonsters() {
        return new ArrayList<>();
    }

    public static List<Inn> createInns() {
        ArrayList<Inn> inns = new ArrayList<>();
        
        Inn inn = new Inn("Brasamical", null);
        inns.add(inn);

        return inns;
    }

    public static List<Temple> createTemples() {
        ArrayList<Temple> temples = new ArrayList<>();
        
        Temple temple1 = new Temple("Temple de Heaume", MockDeityService.getInstance().read(1L), MockCityService.getInstance().read(1L));
        temples.add(temple1);
        
        return temples;
    }

    public static List<City> createCities() {
        ArrayList<City> cities = new ArrayList<>();

        City city1 = new City("EauProfonde", MockRegionService.getInstance().read(1L));
        City city2 = new City("Nashkel", MockRegionService.getInstance().read(2L));
        
        cities.add(city1);
        cities.add(city2);
        return cities;
    }

}
