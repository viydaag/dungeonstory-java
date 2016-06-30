package com.dungeonstory.samples.backend;

import java.io.Serializable;
import java.util.Collection;

import com.dungeonstory.samples.backend.data.Ability;
import com.dungeonstory.samples.backend.data.Category;
import com.dungeonstory.samples.backend.data.Product;
import com.dungeonstory.samples.backend.data.Skill;
import com.dungeonstory.samples.backend.mock.MockDataService;

/**
 * Back-end service interface for retrieving and updating product data.
 */
public abstract class DataService implements Serializable {

	private static final long serialVersionUID = 455739023358167689L;

	public abstract Collection<Product> getAllProducts();

    public abstract Collection<Category> getAllCategories();
    
    public abstract Collection<Ability> getAllAbilities();
    
    public abstract Collection<Skill> getAllSkills();

    public abstract void updateProduct(Product p);

    public abstract void deleteProduct(int productId);

    public abstract Product getProductById(int productId);

    public static DataService get() {
        return MockDataService.getInstance();
    }

}
