package com.dungeonstory.view.admin;

import com.dungeonstory.backend.Configuration;
import com.dungeonstory.backend.data.Equipment;
import com.dungeonstory.backend.data.Equipment.EquipmentType;
import com.dungeonstory.backend.service.DataService;
import com.dungeonstory.backend.service.impl.ArmorService;
import com.dungeonstory.backend.service.impl.EquipmentService;
import com.dungeonstory.backend.service.impl.WeaponService;
import com.dungeonstory.backend.service.mock.MockEquipmentService;
import com.dungeonstory.form.DSAbstractForm;
import com.dungeonstory.form.EquipmentForm;
import com.dungeonstory.util.ViewConfig;
import com.dungeonstory.view.AbstractCrudView;
import com.dungeonstory.view.component.BeanGrid;
import com.dungeonstory.view.component.EquipmentGrid;

@ViewConfig(uri = "equipments", displayName = "Équipements")
public class EquipmentView<T extends Equipment> extends AbstractCrudView<T> {

    private static final long serialVersionUID = 7866174212951058905L;

    @Override
    public DSAbstractForm<T> getForm() {
        return new EquipmentForm<T>();
    }

    @SuppressWarnings("unchecked")
    @Override
    public BeanGrid<T> getGrid() {
        return (BeanGrid<T>) new EquipmentGrid();
    }

    @SuppressWarnings("unchecked")
    @Override
    public DataService<T, Long> getDataService() {
        if (Configuration.getInstance().isMock()) {
            return (DataService<T, Long>) MockEquipmentService.getInstance();
        }
        return (DataService<T, Long>) EquipmentService.getInstance();
    }
    
    @Override
    public void entrySelected() {
        T entity = grid.getSelectedRow();
        initDataService(entity);
        super.entrySelected();
    }
    
    @Override
    public void entrySaved(T entity) {
        initDataService(entity);
        super.entrySaved(entity);
    }
    
    @Override
    public void deleteSelected(T entity) {
        initDataService(entity);
        super.deleteSelected(entity);
    }

    @SuppressWarnings("unchecked")
    private void initDataService(T entity) {
        if (entity == null || entity.getType() == null) {
            setService((DataService<T, Long>) EquipmentService.getInstance());
        } else {
            EquipmentType type = entity.getType();
            switch (type) {
                case ARMOR:
                    setService((DataService<T, Long>) ArmorService.getInstance());
                    break;
                case WEAPON:
                    setService((DataService<T, Long>) WeaponService.getInstance());
                    break;
                default:
                    setService((DataService<T, Long>) EquipmentService.getInstance());
                    break;
            }
        }
    }

}
