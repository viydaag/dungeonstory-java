package com.dungeonstory.ui.view.admin;

import java.util.ArrayList;
import java.util.List;

import com.dungeonstory.backend.data.Shop;
import com.dungeonstory.backend.data.ShopEquipment;
import com.dungeonstory.backend.service.DataService;
import com.dungeonstory.backend.service.Services;
import com.dungeonstory.ui.component.DSAbstractForm;
import com.dungeonstory.ui.util.ViewConfig;
import com.dungeonstory.ui.view.AbstractCrudView;
import com.dungeonstory.ui.view.admin.form.ShopForm;
import com.dungeonstory.ui.view.admin.grid.DSGrid;
import com.dungeonstory.ui.view.admin.grid.ShopGrid;

@ViewConfig(uri = "shops", displayName = "Magasins")
public class ShopView extends AbstractCrudView<Shop> {

    private static final long serialVersionUID = 4239959044896030062L;

    @Override
    public DSAbstractForm<Shop> getForm() {
        return new ShopForm();
    }

    @Override
    public DSGrid<Shop> getGrid() {
        return new ShopGrid();
    }

    @Override
    public DataService<Shop, Long> getDataService() {
        return Services.getShopService();
    }
    
    @Override
    public void entrySaved(Shop entity) {

        //set shop for each nested objects
        if (entity.getShopEquipments() != null) {
            List<ShopEquipment> shopEquipments = new ArrayList<ShopEquipment>(entity.getShopEquipments());
            shopEquipments.stream().forEach(equip -> equip.setShop(entity));
            entity.setShopEquipments(shopEquipments);
        }
        
        //save to database with nested objects
        super.entrySaved(entity);
    }

}
