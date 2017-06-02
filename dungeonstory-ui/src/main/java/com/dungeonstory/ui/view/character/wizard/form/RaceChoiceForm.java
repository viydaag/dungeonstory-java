package com.dungeonstory.ui.view.character.wizard.form;

import org.vaadin.viritin.form.AbstractForm;

import com.dungeonstory.backend.data.Character;
import com.dungeonstory.backend.data.Language;
import com.dungeonstory.backend.data.Race;
import com.dungeonstory.backend.service.DataService;
import com.dungeonstory.backend.service.LanguageDataService;
import com.dungeonstory.backend.service.Services;
import com.dungeonstory.ui.component.DSAbstractForm;
import com.dungeonstory.ui.component.DSTextArea;
import com.dungeonstory.ui.i18n.Messages;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

public class RaceChoiceForm extends DSAbstractForm<Character> implements AbstractForm.SavedHandler<Character> {

    private static final long serialVersionUID = 7418266123213990672L;

    private DataService<Race, Long> raceService;
    private LanguageDataService     languageService;

    ComboBox<Race>     race;
    ComboBox<Language> language;
    private DSTextArea raceDescription;
    private DSTextArea raceTraits;

    public RaceChoiceForm() {
        super(Character.class);
        setSavedHandler(this);

        raceService = Services.getRaceService();
        languageService = Services.getLanguageService();
    }

    @Override
    protected Component createContent() {

        Messages messages = Messages.getInstance();

        HorizontalLayout layout = new HorizontalLayout();

        FormLayout raceFieldsLayout = new FormLayout();
        race = new ComboBox<Race>(messages.getMessage("raceStep.race.label"), raceService.findAll());
        race.setEmptySelectionAllowed(false);
        language = new ComboBox<Language>(messages.getMessage("raceStep.extraLanguage.label"));
        language.setVisible(false);
        raceFieldsLayout.addComponents(race, language);

        VerticalLayout raceDescriptionLayout = new VerticalLayout();
        raceDescription = new DSTextArea(messages.getMessage("raceStep.description.label")).withFullWidth().withRows(10);
        raceTraits = new DSTextArea().withFullWidth().withRows(10);

        race.addValueChangeListener(event -> {
            Race chosenRace = event.getValue();
            if (chosenRace != null) {
                if (chosenRace.getExtraLanguage() == true) {
                    language.setVisible(true);
                    language.setItems(languageService.getLanguagesNotInRace(chosenRace));
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
