package com.dungeonstory.ui.view.character;

import com.dungeonstory.backend.data.Character;
import com.dungeonstory.backend.data.ClassFeature;
import com.dungeonstory.backend.data.Feat;
import com.dungeonstory.backend.data.util.ClassUtil;
import com.dungeonstory.ui.component.DSLabel;
import com.vaadin.fluent.ui.FHorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Tree;
import com.vaadin.ui.VerticalLayout;

public class ClassFeatureList extends VerticalLayout {

    private static final long serialVersionUID = 8431089060102974528L;

    public ClassFeatureList(Character character) {
        
        // class features
        FHorizontalLayout classFeatureLayout = new FHorizontalLayout().withFullWidth();
        Panel classFeaturePanel = new Panel("Dons de classe", classFeatureLayout);
        
        Tree<ClassFeature> classfeatureTree = new Tree<ClassFeature>();
        classfeatureTree.setItems(ClassUtil.getAllRootCharacterClassFeatures(character), ClassFeature::getChildren);
        classFeatureLayout.addComponent(classfeatureTree);
        
        DSLabel classFeatureDescription = new DSLabel();
        classFeatureLayout.addComponent(classFeatureDescription);
        classfeatureTree.addItemClickListener(event -> {
            if (event.getItem() != null) {
                classFeatureDescription.setValue(event.getItem().getDescription());
            } else {
                classFeatureDescription.setValue("");
            }
        });
        addComponent(classFeaturePanel);

        // feats
        if (!character.getFeats().isEmpty()) {
            FHorizontalLayout featLayout = new FHorizontalLayout().withFullWidth();
            Panel featPanel = new Panel("Dons", featLayout);
            Tree<Feat> featTree = new Tree<Feat>();
            featTree.setItems(character.getFeats());
            featLayout.addComponent(featTree);

            DSLabel featDescription = new DSLabel();
            featLayout.addComponent(featDescription);
            featTree.addItemClickListener(event -> {
                if (event.getItem() != null) {
                    featDescription.setValue(event.getItem().getDescription());
                } else {
                    featDescription.setValue("");
                }
            });
            addComponent(featPanel);
        }

    }

}
