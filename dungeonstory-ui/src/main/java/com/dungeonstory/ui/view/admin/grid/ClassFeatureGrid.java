package com.dungeonstory.ui.view.admin.grid;

import java.util.stream.Collectors;

import com.dungeonstory.backend.data.ClassFeature;
import com.dungeonstory.backend.data.ClassLevelFeature;
import com.dungeonstory.backend.data.DSClass;

public class ClassFeatureGrid extends DSGrid<ClassFeature> {

    private static final long serialVersionUID = -6577254670865533975L;

    public ClassFeatureGrid() {
        super();
        addColumn(ClassFeature::getName).setCaption("Nom").setId("name");
        addColumn(ClassFeature::getUsage).setCaption("Usage").setId("usage");
        addColumn(feature -> feature.getClassLevels().stream().map(ClassLevelFeature::getClasse).map(DSClass::getName)
                .collect(Collectors.joining(", "))).setCaption("Classe(s)").setId("classes").setSortable(false);
    }

}
