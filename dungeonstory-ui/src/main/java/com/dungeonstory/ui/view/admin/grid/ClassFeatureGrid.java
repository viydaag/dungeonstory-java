package com.dungeonstory.ui.view.admin.grid;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.dungeonstory.backend.data.ClassFeature;
import com.dungeonstory.backend.data.ClassLevelFeature;
import com.dungeonstory.backend.data.ClassSpecLevelFeature;
import com.dungeonstory.backend.data.ClassSpecialization;
import com.dungeonstory.backend.data.DSClass;

public class ClassFeatureGrid extends DSGrid<ClassFeature> {

    private static final long serialVersionUID = -6577254670865533975L;

    public ClassFeatureGrid() {
        super();
        addColumn(ClassFeature::getName).setCaption("Nom").setId("name");
        addColumn(ClassFeature::getUsage).setCaption("Usage").setId("usage");
        addColumn(ClassFeature::getParent).setCaption("Don parent").setId("parent");
        addColumn(ClassFeature::getReplacement).setCaption("Remplace").setId("replacement");
        addColumn(feature -> {
            final String DELIMITER = ", ";
            String classes = feature.getClassLevels().stream().map(ClassLevelFeature::getClasse).map(DSClass::getName)
                    .collect(Collectors.joining(DELIMITER));
            String classSpecs = feature.getClassSpecLevels().stream().map(ClassSpecLevelFeature::getClassSpec).map(ClassSpecialization::getName)
                    .collect(Collectors.joining(DELIMITER));
            return Arrays.asList(classes, classSpecs).stream().filter(StringUtils::isNotBlank).distinct().collect(Collectors.joining(DELIMITER));
        }).setCaption("Classe(s)").setId("classes").setSortable(false);
    }

}
