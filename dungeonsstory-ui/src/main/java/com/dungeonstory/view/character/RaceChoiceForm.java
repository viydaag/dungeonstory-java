package com.dungeonstory.view.character;

import org.vaadin.viritin.fields.MTextArea;
import org.vaadin.viritin.fields.TypedSelect;
import org.vaadin.viritin.fields.config.ListSelectConfig;
import org.vaadin.viritin.form.AbstractForm;

import com.dungeonstory.backend.data.Character;
import com.dungeonstory.backend.data.Language;
import com.dungeonstory.backend.data.Race;
import com.dungeonstory.backend.service.impl.LanguageService;
import com.dungeonstory.backend.service.impl.RaceService;
import com.dungeonstory.util.layout.HorizontalSpacedLayout;
import com.dungeonstory.util.layout.VerticalSpacedLayout;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;

public class RaceChoiceForm extends AbstractForm<Character> implements AbstractForm.SavedHandler<Character> {

    private static final long serialVersionUID = 7418266123213990672L;

    private RaceService raceService = RaceService.getInstance();
    private LanguageService languageService = LanguageService.getInstance();

    TypedSelect<Race> race;
    TypedSelect<Language> language;
    private MTextArea raceDescription;
    private MTextArea raceTraits;

    public RaceChoiceForm() {
        super();
        setSavedHandler(this);
    }

    @Override
    protected Component createContent() {

        HorizontalSpacedLayout layout = new HorizontalSpacedLayout();

        FormLayout raceFieldsLayout = new FormLayout();
        race = new TypedSelect<Race>("Choix de race", raceService.findAll())
                .asListSelectType(new ListSelectConfig().withRows((int) raceService.count()))
                .withNullSelectionAllowed(false);
        language = new TypedSelect<Language>("Langage extra").asComboBoxType()
                .withVisible(false);
        raceFieldsLayout.addComponents(race, language);

        VerticalSpacedLayout raceDescriptionLayout = new VerticalSpacedLayout();
        raceDescription = new MTextArea("Description").withRows(10);
        raceTraits = new MTextArea().withRows(10);

        race.addMValueChangeListener(event -> {
            Race chosenRace = event.getValue();
            if (chosenRace != null) {
                if (chosenRace.getExtraLanguage() == true) {
                    language.setVisible(true);
                    language.setOptions(languageService.getLanguagesNotInRace(chosenRace));
                } else {
                    language.clear();
                    language.setVisible(false);
                }
                raceDescription.setValue(chosenRace.getDescription());
                raceTraits.setValue(chosenRace.getTraits());
            } else {
                raceDescription.clear();
                raceTraits.clear();
                language.clear();
                language.setVisible(false);
            }
        });

        raceDescriptionLayout.addComponents(raceDescription, raceTraits);

        layout.setSizeFull();
        layout.addComponents(raceFieldsLayout, raceDescriptionLayout);
        layout.setExpandRatio(raceFieldsLayout, 1);
        layout.setExpandRatio(raceDescriptionLayout, 2);

        return layout;
    }

    @Override
    public void onSave(Character entity) {
        if (language.getValue() != null) {
            entity.getLanguages().add(language.getValue());
        }
        entity.getLanguages().addAll(race.getValue().getLanguages());
    }

}
