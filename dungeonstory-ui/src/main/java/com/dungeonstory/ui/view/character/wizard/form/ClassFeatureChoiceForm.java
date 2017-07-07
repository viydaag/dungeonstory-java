package com.dungeonstory.ui.view.character.wizard.form;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.dungeonstory.backend.data.Character;
import com.dungeonstory.backend.data.CharacterClass;
import com.dungeonstory.backend.data.ClassFeature;
import com.dungeonstory.backend.data.util.ClassUtil;
import com.dungeonstory.ui.component.AbstractForm;
import com.vaadin.ui.Component;
import com.vaadin.ui.TwinColSelect;
import com.vaadin.ui.VerticalLayout;

public class ClassFeatureChoiceForm extends CharacterWizardStepForm<Character> implements AbstractForm.SavedHandler<Character> {

    private static final long serialVersionUID = 7368308386556387917L;

    private CharacterClass chosenClass;

    public ClassFeatureChoiceForm(CharacterClass assignedClass) {
        super(Character.class);
        setSavedHandler(this);
        this.chosenClass = assignedClass;
    }

    @Override
    protected Component createContent() {
        VerticalLayout mainLayout = new VerticalLayout();

        Stream<ClassFeature> classFeatures = ClassUtil.getClassFeaturesForLevel(chosenClass.getClasse(), chosenClass.getClassLevel());
        List<ClassFeature> parentClassFeatures = classFeatures.filter(cf -> !cf.getChildren().isEmpty()).collect(Collectors.toList());

        for (ClassFeature parent : parentClassFeatures) {
            TwinColSelect<ClassFeature> select = new TwinColSelect<>(parent.getName(), parent.getChildren());
            mainLayout.addComponent(select);
        }

        return mainLayout;
    }
    
    @Override
    public void afterSetEntity() {
        super.afterSetEntity();
        
        //        CharacterClass assignedClass = ClassUtil.getCharacterClass(getEntity(), this.chosenClass);
    }

    @Override
    public void onSave(Character entity) {
        // TODO Auto-generated method stub

    }
    
}
