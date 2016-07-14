package com.dungeonstory.backend.mock;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

import com.dungeonstory.backend.data.Ability;
import com.dungeonstory.backend.data.Alignment;
import com.dungeonstory.backend.data.Availability;
import com.dungeonstory.backend.data.Category;
import com.dungeonstory.backend.data.DamageType;
import com.dungeonstory.backend.data.Level;
import com.dungeonstory.backend.data.Product;
import com.dungeonstory.backend.data.Skill;

public class MockDataGenerator {
    private static int nextCategoryId = 1;
    private static int nextProductId = 1;
    private static final Random random = new Random(1);
    private static final String categoryNames[] = new String[] {
            "Children's books", "Best sellers", "Romance", "Mystery",
            "Thriller", "Sci-fi", "Non-fiction", "Cookbooks" };

    private static String[] word1 = new String[] { "The art of", "Mastering",
            "The secrets of", "Avoiding", "For fun and profit: ",
            "How to fail at", "10 important facts about",
            "The ultimate guide to", "Book of", "Surviving", "Encyclopedia of",
            "Very much", "Learning the basics of", "The cheap way to",
            "Being awesome at", "The life changer:", "The Vaadin way:",
            "Becoming one with", "Beginners guide to",
            "The complete visual guide to", "The mother of all references:" };

    private static String[] word2 = new String[] { "gardening",
            "living a healthy life", "designing tree houses", "home security",
            "intergalaxy travel", "meditation", "ice hockey",
            "children's education", "computer programming", "Vaadin TreeTable",
            "winter bathing", "playing the cello", "dummies", "rubber bands",
            "feeling down", "debugging", "running barefoot",
            "speaking to a big audience", "creating software", "giant needles",
            "elephants", "keeping your wife happy" };
    
    private static final String storedAbilities[][] = new String[][] {
    	{"Force", "FOR"},
    	{"Dextérité", "DEX"},
    	{"Constitution", "CON"},
    	{"Intelligence", "INT"},
    	{"Sagesse", "SAG"},
    	{"Charisme", "CHA"}
    };
    
    private static final String storedAlignment[][] = new String[][] {
        {"Loyal Bon"},
        {"Neutre Bon"},
        {"Chaotique Bon"},
        {"Loyal Neutre"},
        {"Neutre strict"},
        {"Chaotique Neutre"},
        {"Loyal Mauvais"},
        {"Neutre Mauvais"},
        {"Chaotique Mauvais"}
    };
    
    private static final String storedDamageType[][] = new String[][] {
        {"Tranchant"},
        {"Contandant"},
        {"Perçant"},
        {"Feu"},
        {"Froid"},
        {"Acide"},
        {"Électricité"},
        {"Nécrotique"},
        {"Force"},
        {"Magique"},
        {"Radiant"},
    };
    
    private static final String storedSkills[][] = new String[][] {
    	{"Athlétisme", "1"},
    	{"Acrobatie", "2"},
    	{"Vol à la tire", "2"},
    	{"Furtivité", "2"},
    	{"Arcane", "4"},
    	{"Histoire", "4"},
    	{"Investigation", "4"},
    	{"Nature", "4"},
    	{"Religion", "4"},
    	{"Manipulation des animaux", "5"},
    	{"Perspicacité", "5"},
    	{"Soin", "5"},
    	{"Perception", "5"},
    	{"Survie", "5"},
    	{"Tromperie", "6"},
    	{"Intimidation", "6"},
    	{"Performance", "6"},
    	{"Persuasion", "6"}
    };
    
    private static final Integer[][] storedLevels = new Integer[][] {
        {1, 1000, 1},
        {2, 2000, 2}
    };

    static List<Category> createCategories() {
        List<Category> categories = new ArrayList<Category>();
        for (String name : categoryNames) {
            Category c = createCategory(name);
            categories.add(c);
        }
        return categories;

    }

    static List<Product> createProducts(List<Category> categories) {
        List<Product> products = new ArrayList<Product>();
        for (int i = 0; i < 100; i++) {
            Product p = createProduct(categories);
            products.add(p);
        }

        return products;
    }
    
    static List<Ability> createAbilities() {
        List<Ability> abilities = new ArrayList<Ability>();
        for (String[] ability : storedAbilities) {
        	abilities.add(new Ability(ability[0], ability[1], ""));
        }
        return abilities;
    }
    
    static List<Alignment> createAlignments() {
        List<Alignment> alignments = new ArrayList<Alignment>();
        for (String[] alignment : storedAlignment) {
            alignments.add(new Alignment(alignment[0], "", ""));
        }
        return alignments;
    }
    
    static List<DamageType> createDamageTypes() {
        List<DamageType> types = new ArrayList<DamageType>();
        for (String[] type : storedDamageType) {
            types.add(new DamageType(type[0]));
        }
        return types;
    }
    
    static List<Skill> createSkills() {
        List<Skill> skills = new ArrayList<Skill>();
        Collection<Ability> abilities = MockDataService.getInstance().getAllAbilities();
        for (String[] skill : storedSkills) {
        	Optional<Ability> ability = abilities.stream().filter(a -> a.getId().equals(Long.valueOf(skill[1]))).findFirst();
        	skills.add(new Skill(skill[0], ability.get()));
        }
        return skills;
    }
    
    static List<Level> createLevels() {
        List<Level> levels = new ArrayList<Level>();
        for (Integer[] level : storedLevels) {
            levels.add(new Level(level[1], level[2]));
        }
        return levels;
    }

    private static Category createCategory(String name) {
        Category c = new Category();
        c.setId(nextCategoryId++);
        c.setName(name);
        return c;
    }

    private static Product createProduct(List<Category> categories) {
        Product p = new Product();
        p.setId(nextProductId++);
        p.setProductName(generateName());

        p.setPrice(new BigDecimal((random.nextInt(250) + 50) / 10.0));
        p.setAvailability(Availability.values()[random.nextInt(Availability
                .values().length)]);
        if (p.getAvailability() == Availability.AVAILABLE) {
            p.setStockCount(random.nextInt(523));
        }

        p.setCategory(getCategory(categories, 1, 2));
        return p;
    }

    private static Set<Category> getCategory(List<Category> categories,
            int min, int max) {
        int nr = random.nextInt(max) + min;
        HashSet<Category> productCategories = new HashSet<Category>();
        for (int i = 0; i < nr; i++) {
            productCategories.add(categories.get(random.nextInt(categories
                    .size())));
        }

        return productCategories;
    }

    private static String generateName() {
        return word1[random.nextInt(word1.length)] + " "
                + word2[random.nextInt(word2.length)];
    }

}
