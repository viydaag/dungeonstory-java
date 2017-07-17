package com.dungeonstory.ui.view.character;

import com.dungeonstory.backend.data.Character;
import com.dungeonstory.backend.data.ClassFeature;
import com.dungeonstory.backend.data.util.ClassUtil;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

public class ClassFeatureList extends VerticalLayout {

    private static final long serialVersionUID = 8431089060102974528L;

    public ClassFeatureList(Character character) {
        
        VerticalLayout classFeatureLayout = new VerticalLayout();

        for (ClassFeature feature : ClassUtil.getAllCharacterClassFeatures(character)) {
            Label featureLabel = new Label(feature.getName());
            featureLabel.setDescription(feature.getDescription(), ContentMode.HTML);
            classFeatureLayout.addComponent(featureLabel);
        }

        Panel classFeaturePanel = new Panel("Dons de classe", classFeatureLayout);

        addComponents(classFeaturePanel);

    }

}
