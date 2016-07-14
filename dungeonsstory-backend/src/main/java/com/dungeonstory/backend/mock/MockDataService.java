package com.dungeonstory.backend.mock;

import java.util.Collection;
import java.util.List;

import com.dungeonstory.backend.DataService;
import com.dungeonstory.backend.data.Ability;
import com.dungeonstory.backend.data.Alignment;
import com.dungeonstory.backend.data.Category;
import com.dungeonstory.backend.data.DamageType;
import com.dungeonstory.backend.data.Level;
import com.dungeonstory.backend.data.Product;
import com.dungeonstory.backend.data.Skill;

/**
 * Mock data model. This implementation has very simplistic locking and does not
 * notify users of modifications.
 */
public class MockDataService extends DataService {

	private static final long serialVersionUID = 1413016825631815194L;

	private static MockDataService INSTANCE;

    private static List<Ability> abilities;
    private static List<Skill> skills;
    private static List<Level> levels;
    private static List<Alignment> alignments;
    private static List<DamageType> damageTypes;
    
    private static List<Product> products;
    private static List<Category> categories;
    private static int nextProductId = 0;

    private MockDataService() {
    	
    }

	private static void init() {
		abilities = MockDataGenerator.createAbilities();
    	skills = MockDataGenerator.createSkills();
    	levels = MockDataGenerator.createLevels();
    	alignments = MockDataGenerator.createAlignments();
    	damageTypes = MockDataGenerator.createDamageTypes();
    	
        categories = MockDataGenerator.createCategories();
        products = MockDataGenerator.createProducts(categories);
        nextProductId = products.size() + 1;
	}

    public synchronized static DataService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new MockDataService();
            init();
        }
        return INSTANCE;
    }

    @Override
    public synchronized List<Ability> getAllAbilities() {
        return abilities;
    }
    
    @Override
	public Collection<Product> getAllProducts() {
		return products;
	}

    @Override
    public synchronized List<Category> getAllCategories() {
        return categories;
    }

    @Override
    public synchronized void updateProduct(Product p) {
        if (p.getId() < 0) {
            // New product
            p.setId(nextProductId++);
            products.add(p);
            return;
        }
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == p.getId()) {
                products.set(i, p);
                return;
            }
        }

        throw new IllegalArgumentException("No product with id " + p.getId()
                + " found");
    }

    @Override
    public synchronized Product getProductById(int productId) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == productId) {
                return products.get(i);
            }
        }
        return null;
    }

    @Override
    public synchronized void deleteProduct(int productId) {
        Product p = getProductById(productId);
        if (p == null) {
            throw new IllegalArgumentException("Product with id " + productId
                    + " not found");
        }
        products.remove(p);
    }

	@Override
	public Collection<Skill> getAllSkills() {
		return skills;
	}

    @Override
    public Collection<Level> getAllLevels() {
        return levels;
    }

    @Override
    public Collection<Alignment> getAllAlignments() {
        return alignments;
    }

    @Override
    public Collection<DamageType> getAllDamageTypes() {
        return damageTypes;
    }

}
