package com.dungeonstory.view.admin;

import java.util.ArrayList;
import java.util.List;

import com.dungeonstory.backend.Configuration;
import com.dungeonstory.backend.data.Shop;
import com.dungeonstory.backend.data.ShopEquipment;
import com.dungeonstory.backend.service.DataService;
import com.dungeonstory.backend.service.impl.ShopService;
import com.dungeonstory.backend.service.mock.MockShopService;
import com.dungeonstory.form.DSAbstractForm;
import com.dungeonstory.form.ShopForm;
import com.dungeonstory.util.ViewConfig;
import com.dungeonstory.view.AbstractCrudView;
import com.dungeonstory.view.component.BeanGrid;
import com.dungeonstory.view.component.ShopGrid;

@ViewConfig(uri = "shops", displayName = "Magasins")
public class ShopView extends AbstractCrudView<Shop> {

    private static final long serialVersionUID = 4239959044896030062L;

    @Override
    public DSAbstractForm<Shop> getForm() {
        return new ShopForm();
    }

    @Override
    public BeanGrid<Shop> getGrid() {
        return new ShopGrid();
    }

    @Override
    public DataService<Shop, Long> getDataService() {
        if (Configuration.getInstance().isMock()) {
            return MockShopService.getInstance();
        }
        return ShopService.getInstance();
    }
    
    @Override
    public void entrySaved(Shop entity) {

        //set shop for each nested objects
        List<ShopEquipment> shopEquipments = new ArrayList<ShopEquipment>(entity.getShopEquipments());
        shopEquipments.stream().forEach(equip -> equip.setShop(entity));
        entity.setShopEquipments(shopEquipments);
        
        //save to database with nested objects
        super.entrySaved(entity);
    }

}
