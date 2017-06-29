package com.dungeonstory.backend.data;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Shop")
public class Shop extends AbstractTimestampEntity {

    private static final long serialVersionUID = 4969359354645768702L;

    @NotNull
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
    
    @OneToMany(mappedBy = "shop", cascade = CascadeType.PERSIST)
    private List<ShopEquipment> shopEquipments;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name = "cityId")
    private City city;

    public Shop() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ShopEquipment> getShopEquipments() {
        return shopEquipments;
    }

    public void setShopEquipments(List<ShopEquipment> shopEquipments) {
        this.shopEquipments = shopEquipments;
    }

    public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	@Override
    public String toString() {
        return getName();
    }
}
