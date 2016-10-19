package com.dungeonstory.view.character;

import org.vaadin.viritin.fields.MTextArea;
import org.vaadin.viritin.fields.TypedSelect;
import org.vaadin.viritin.fields.config.ListSelectConfig;
import org.vaadin.viritin.form.AbstractForm;

import com.dungeonstory.backend.data.Character;
import com.dungeonstory.backend.data.Race;
import com.dungeonstory.backend.service.impl.RaceService;
import com.dungeonstory.util.layout.HorizontalSpacedLayout;
import com.dungeonstory.util.layout.VerticalSpacedLayout;
import com.vaadin.ui.Component;

public class RaceChoiceForm extends AbstractForm<Character> {

    private static final long serialVersionUID = 7418266123213990672L;

    private RaceService raceService = RaceService.getInstance();

    TypedSelect<Race> race;
    private MTextArea raceDescription;
    private MTextArea raceTraits;

    public RaceChoiceForm() {
        super();
    }

    @Override
    protected Component createContent() {

        HorizontalSpacedLayout layout = new HorizontalSpacedLayout();
        race = new TypedSelect<Race>("Choix de race", raceService.findAll())
                .asListSelectType(new ListSelectConfig().withRows((int) raceService.count()))
                .withNullSelectionAllowed(false);

        VerticalSpacedLayout raceDescriptionLayout = new VerticalSpacedLayout();
        raceDescription = new MTextArea("Description").withRows(10);
        raceTraits = new MTextArea().withRows(10);

        race.addMValueChangeListener(event -> {
            if (event.getValue() != null) {
                raceDescription.setValue(event.getValue().getDescription());
                raceTraits.setValue(event.getValue().getTraits());
            } else {
                raceDescription.clear();
                raceTraits.clear();
            }
        });

        raceDescriptionLayout.addComponents(raceDescription, raceTraits);

        layout.setSizeFull();
        layout.addComponents(race, raceDescriptionLayout);
        layout.setExpandRatio(race, 1);
        layout.setExpandRatio(raceDescriptionLayout, 2);

        return layout;
    }

}
