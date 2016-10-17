package com.dungeonstory.view.character;

import org.vaadin.viritin.fields.MTextArea;
import org.vaadin.viritin.fields.TypedSelect;
import org.vaadin.viritin.form.AbstractForm;

import com.dungeonstory.backend.data.Character;
import com.dungeonstory.backend.data.DSClass;
import com.dungeonstory.backend.service.impl.ClassService;
import com.dungeonstory.util.layout.HorizontalSpacedLayout;
import com.dungeonstory.util.layout.VerticalSpacedLayout;
import com.vaadin.ui.Component;

public class ClassChoiceForm extends AbstractForm<Character> {

    private static final long serialVersionUID = 6382868944768026273L;

    private ClassService classService = ClassService.getInstance();

    private TypedSelect<DSClass> classe;
    private MTextArea            classDescription;

    public ClassChoiceForm() {
        super();
    }

    @Override
    protected Component createContent() {

        HorizontalSpacedLayout layout = new HorizontalSpacedLayout();
        classe = new TypedSelect<DSClass>("Choix de classe", classService.findAll());

        VerticalSpacedLayout classDescriptionLayout = new VerticalSpacedLayout();
        classDescription = new MTextArea("Description").withRows(10);

        classe.addMValueChangeListener(event -> {
            classDescription.setValue(event.getValue().getDescription());
        });

        classDescriptionLayout.addComponents(classDescription);

        layout.setSizeFull();
        layout.addComponents(classe, classDescriptionLayout);
        layout.setExpandRatio(classe, 1);
        layout.setExpandRatio(classDescriptionLayout, 2);

        return layout;
    }

    public TypedSelect<DSClass> getClasse() {
        return classe;
    }

}
