package com.dungeonstory.ui.view.character.wizard.form;

import com.dungeonstory.ui.component.DSAbstractForm;
import com.vaadin.ui.Button;

public abstract class CharacterWizardStepForm<T> extends DSAbstractForm<T> {

    private static final long serialVersionUID = -8439829227742157402L;

    public CharacterWizardStepForm(Class<T> entityClass) {
        super(entityClass);
    }
    
    @Override
    public void setSaveButton(Button button) {
        super.setSaveButton(button);
        
        //we remove the save listener because the wizard next button will take care of save action.
        removeSaveClickListener();
    }
    
    public void save() {
        super.save(null);
    }


}
