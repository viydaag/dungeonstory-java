package com.dungeonstory.ui.view.character.wizard.form;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.dungeonstory.backend.data.Character;
import com.dungeonstory.backend.data.CharacterClass;
import com.dungeonstory.backend.data.ClassFeature;
import com.dungeonstory.backend.data.ClassLevelFeature;
import com.dungeonstory.backend.data.util.ClassUtil;
import com.dungeonstory.ui.component.AbstractForm;
import com.dungeonstory.ui.field.SubSetSelectorDraggable;
import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;

public class ClassFeatureChoiceForm extends CharacterWizardStepForm<Character> implements AbstractForm.SavedHandler<Character> {

    private static final long serialVersionUID = 7368308386556387917L;

    private CharacterClass chosenClass;
    private Map<ClassFeature, SubSetSelectorDraggable<ClassFeature, List<ClassFeature>>> featuresWithChildren;

    public ClassFeatureChoiceForm(CharacterClass assignedClass) {
        super(Character.class);
        setSavedHandler(this);
        setBinding(false);
        this.chosenClass = assignedClass;
        this.featuresWithChildren = new HashMap<>();
    }

    @Override
    protected Component createContent() {
        VerticalLayout mainLayout = new VerticalLayout();

        Stream<ClassFeature> classFeatures = ClassUtil.getClassFeaturesForLevel(chosenClass.getClasse(), chosenClass.getClassLevel());
        List<ClassFeature> parentClassFeatures = classFeatures.filter(cf -> !cf.getChildren().isEmpty()).collect(Collectors.toList());

        for (ClassFeature parent : parentClassFeatures) {
            ClassLevelFeature classLevelFeature = ClassUtil.getClassLevelFeature(chosenClass, parent);

            if (classLevelFeature != null) {
                SubSetSelectorDraggable<ClassFeature, List<ClassFeature>> selector = new SubSetSelectorDraggable<>(parent.getName(),
                        parent.getChildren());
                selector.setWidth("50%");
                selector.setDescriptionGenerator(ClassFeature::getDescription);
                selector.setLimit(classLevelFeature.getNbToChoose());
                selector.addValueChangeListener(event -> adjustButtons());
                selector.setValue(ClassUtil.getChildrenCharacterClassFeatures(chosenClass.getCharacter(), parent));

                mainLayout.addComponent(selector);

                featuresWithChildren.put(parent, selector);
            }
        }

        return mainLayout;
    }
    
    @Override
    public void afterSetEntity() {
        super.afterSetEntity();
    }

    @Override
    protected void adjustSaveButtonState() {
        if (isBound()) {
            boolean allClassFeaturesAssigned = true;
            for (Entry<ClassFeature, SubSetSelectorDraggable<ClassFeature, List<ClassFeature>>> entry : featuresWithChildren.entrySet()) {
                SubSetSelectorDraggable<ClassFeature, List<ClassFeature>> selector = entry.getValue();
                if (selector != null && selector.getValue().size() < selector.getLimit()) {
                    allClassFeaturesAssigned = false;
                    break;
                }
            }
            getSaveButton().setEnabled(allClassFeaturesAssigned && getBinder().isValid());
        }
    }

    @Override
    public void onSave(Character entity) {

        for (Entry<ClassFeature, SubSetSelectorDraggable<ClassFeature, List<ClassFeature>>> entry : featuresWithChildren.entrySet()) {
            //remove current class features with same parent
            ClassFeature parent = entry.getKey();
            chosenClass.getClassFeatures().removeIf(cf -> cf.getParent() != null && cf.getParent().equals(parent));

            //add new ones selected
            SubSetSelectorDraggable<ClassFeature, List<ClassFeature>> selector = entry.getValue();
            chosenClass.getClassFeatures().addAll(selector.getValue());
        }

    }
    
}
