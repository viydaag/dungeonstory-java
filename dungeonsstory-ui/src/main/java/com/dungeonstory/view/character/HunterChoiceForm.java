package com.dungeonstory.view.character;

import java.util.Arrays;

import com.dungeonstory.backend.data.Character;
import com.dungeonstory.backend.data.CreatureType;
import com.dungeonstory.backend.data.Terrain;
import com.dungeonstory.backend.service.impl.CreatureTypeService;
import com.dungeonstory.form.DSAbstractForm;
import com.dungeonstory.util.field.DSSubSetSelector;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;

public class HunterChoiceForm extends DSAbstractForm<Character> {

    private static final long serialVersionUID = -3212849518855573114L;

    DSSubSetSelector<CreatureType> favoredEnnemies;
    DSSubSetSelector<Terrain>      favoredTerrains;

    private CreatureTypeService creatureTypeService = null;
    private int                 nbFavoredEnnemy     = 0;
    private int                 nbFavoredTerrain    = 0;

    public HunterChoiceForm() {
        creatureTypeService = CreatureTypeService.getInstance();
    }

    @Override
    protected Component createContent() {
        FormLayout layout = new FormLayout();

        favoredEnnemies = new DSSubSetSelector<CreatureType>(CreatureType.class);
        favoredEnnemies.setCaption("Type de créature favori");
        favoredEnnemies.setVisibleProperties("name");
        favoredEnnemies.setColumnHeader("name", "Type de créature");
        favoredEnnemies.setOptions(creatureTypeService.findAll());
        favoredEnnemies.setWidth("80%");
        //        favoredEnnemies.setValue(null); //nothing selected
        layout.addComponent(favoredEnnemies);


        favoredTerrains = new DSSubSetSelector<Terrain>(Terrain.class);
        favoredTerrains.setCaption("Terrain favori");
        favoredTerrains.setVisibleProperties("name");
        favoredTerrains.setColumnHeader("name", "Terrain");
        favoredTerrains.setOptions(Arrays.asList(Terrain.values()));
        favoredTerrains.setWidth("80%");
        layout.addComponent(favoredTerrains);

        return layout;
    }

    @Override
    public void afterSetEntity() {
        super.afterSetEntity();

        nbFavoredEnnemy = getEntity().getFavoredEnnemies().size() + 1;
        nbFavoredTerrain = getEntity().getFavoredTerrains().size() + 1;
        favoredEnnemies.setLimit(nbFavoredEnnemy);
        favoredTerrains.setLimit(nbFavoredTerrain);
        //        favoredEnnemies.addValueChangeListener(new ValueChangeListener() {
        //
        //            @Override
        //            public void valueChange(ValueChangeEvent event) {
        //                if (favoredEnnemies.getTable().size() >= nbFavoredEnnemy) {
        //                    System.out.println("Dépassé la limite");
        //                } else {
        //                    System.out.println("OK");
        //                }
        //
        //            }
        //        });
        //        favoredTerrains.addValueChangeListener(new ValueChangeListener() {
        //
        //            @Override
        //            public void valueChange(ValueChangeEvent event) {
        //                if (favoredTerrains.getTable().size() >= nbFavoredEnnemy) {
        //                    System.out.println("Dépassé la limite");
        //                } else {
        //                    System.out.println("OK");
        //                }
        //
        //            }
        //        });
    }

}
