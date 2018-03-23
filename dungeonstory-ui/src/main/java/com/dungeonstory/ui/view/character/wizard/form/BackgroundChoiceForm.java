package com.dungeonstory.ui.view.character.wizard.form;

import java.util.EnumSet;
import java.util.Set;

import com.dungeonstory.backend.data.Character;
import com.dungeonstory.backend.data.CharacterBackground;
import com.dungeonstory.backend.data.enums.Background;
import com.dungeonstory.backend.data.enums.Language;
import com.dungeonstory.backend.service.LanguageDataService;
import com.dungeonstory.backend.service.Services;
import com.dungeonstory.ui.component.AbstractForm;
import com.dungeonstory.ui.component.DSLabel;
import com.dungeonstory.ui.component.EnumComboBox;
import com.dungeonstory.ui.converter.CollectionToStringConverter;
import com.dungeonstory.ui.field.SubSetSelector;
import com.dungeonstory.ui.i18n.Messages;
import com.vaadin.data.ValueContext;
import com.vaadin.fluent.ui.FTextArea;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class BackgroundChoiceForm extends CharacterWizardStepForm<CharacterBackground>
        implements AbstractForm.SavedHandler<CharacterBackground> {

    private static final long serialVersionUID = -8079455641743140814L;

    private LanguageDataService languageService;

    private Character character;

    private EnumComboBox<Background>                background;
    private FTextArea                               look;
    private FTextArea                               traits;
    private FTextArea                               ideals;
    private FTextArea                               purposes;
    private FTextArea                               flaws;
    private SubSetSelector<Language, Set<Language>> language;

    private FTextArea traitsSuggestion;
    private FTextArea idealsSuggestion;
    private FTextArea purposesSuggestion;
    private FTextArea flawsSuggestion;

    private DSLabel proficienciesLabel;
    private DSLabel skillProficiencies;
    private DSLabel toolProficiencies;
    private DSLabel additionalLanguage;

    public BackgroundChoiceForm(Character character) {
        super(CharacterBackground.class);
        this.character = character;
        setSavedHandler(this);

        languageService = Services.getLanguageService();
    }

    @Override
    protected Component createContent() {

        Messages messages = Messages.getInstance();

        HorizontalLayout layout = new HorizontalLayout();

        VerticalLayout backgroundFieldsLayout = new VerticalLayout();
        background = new EnumComboBox<Background>(Background.class,
                messages.getMessage("backgroundStep.background.label"));
        language = new SubSetSelector<>(Language.class);
        language.setCaption(messages.getMessage("backgroundStep.languages.label"));
        language.getGrid()
                .addColumn(Language::getName)
                .setCaption(messages.getMessage("backgroundStep.languages.table.column.name"))
                .setId("name");
        language.getGrid().setColumnOrder("name");
        language.setItems(EnumSet.noneOf(Language.class));
        language.setVisible(false);
        language.addValueChangeListener(event -> adjustButtons());

        look = new FTextArea(messages.getMessage("backgroundStep.look.label")).withFullWidth().withRows(6);
        traits = new FTextArea(messages.getMessage("backgroundStep.traits.label")).withFullWidth().withRows(6);
        ideals = new FTextArea(messages.getMessage("backgroundStep.ideals.label")).withFullWidth().withRows(6);
        purposes = new FTextArea(messages.getMessage("backgroundStep.purposes.label")).withFullWidth().withRows(6);
        flaws = new FTextArea(messages.getMessage("backgroundStep.flaws.label")).withFullWidth().withRows(6);

        FormLayout backgroundProperties = new FormLayout();
        proficienciesLabel = new DSLabel().withCaption(messages.getMessage("backgroundStep.proficiencies.label")).withStyleName(ValoTheme.LABEL_H4);
        skillProficiencies = new DSLabel().withCaption(messages.getMessage("backgroundStep.proficiencies.skill.label"));
        toolProficiencies = new DSLabel().withCaption(messages.getMessage("backgroundStep.proficiencies.tool.label"));
        additionalLanguage = new DSLabel().withCaption(messages.getMessage("backgroundStep.additionalLanguages.label"));
        backgroundProperties.addComponents(proficienciesLabel, skillProficiencies, toolProficiencies, additionalLanguage);

        traitsSuggestion = new FTextArea(messages.getMessage("backgroundStep.suggestedTraits.label")).withFullWidth().withRows(12);
        idealsSuggestion = new FTextArea(messages.getMessage("backgroundStep.suggestedIdeals.label")).withFullWidth().withRows(12);
        purposesSuggestion = new FTextArea(messages.getMessage("backgroundStep.suggestedPurposes.label")).withFullWidth().withRows(12);
        flawsSuggestion = new FTextArea(messages.getMessage("backgroundStep.suggestedFlaws.label")).withFullWidth().withRows(12);

        backgroundFieldsLayout.addComponents(background, language, look, traits, ideals, purposes, flaws);

        CollectionToStringConverter collectionConverter = new CollectionToStringConverter();

        VerticalLayout backgroundDescriptionLayout = new VerticalLayout();

        background.addValueChangeListener(event -> {
            Background chosenBackground = event.getValue();
            if (chosenBackground != null) {
                if (chosenBackground.getNbAdditionalLanguage() > 0) {
                    language.setVisible(true);
                    language.setItems(languageService.getUnassignedLanguages(character));
                    language.setValue(EnumSet.noneOf(Language.class));
                    language.setLimit(chosenBackground.getNbAdditionalLanguage());
                } else {
                    language.clear();
                    language.setVisible(false);
                }
                proficienciesLabel.setVisible(true);
                skillProficiencies.setVisible(true);
                toolProficiencies.setVisible(true);
                additionalLanguage.setVisible(true);
                skillProficiencies.setValue(chosenBackground.getSkillProficiencies().isEmpty()
                        ? messages.getMessage("backgroundStep.none.text")
                        : collectionConverter.convertToPresentation(chosenBackground.getSkillProficiencies(), new ValueContext()));
                toolProficiencies.setValue(chosenBackground.getToolProficiencies().isEmpty()
                        ? messages.getMessage("backgroundStep.none.text")
                        : collectionConverter.convertToPresentation(chosenBackground.getToolProficiencies(), new ValueContext()));
                additionalLanguage.setValue(String.valueOf(chosenBackground.getNbAdditionalLanguage()));
                traitsSuggestion.setValue(chosenBackground.getTraits());
                idealsSuggestion.setValue(chosenBackground.getIdeals());
                purposesSuggestion.setValue(chosenBackground.getPurposes());
                flawsSuggestion.setValue(chosenBackground.getFlaws());
            } else {
                proficienciesLabel.setVisible(false);
                skillProficiencies.setVisible(false);
                toolProficiencies.setVisible(false);
                additionalLanguage.setVisible(false);
                traitsSuggestion.clear();
                idealsSuggestion.clear();
                purposesSuggestion.clear();
                flawsSuggestion.clear();
                language.clear();
                language.setVisible(false);
            }
        });

        backgroundDescriptionLayout.addComponents(backgroundProperties, traitsSuggestion, idealsSuggestion, purposesSuggestion, flawsSuggestion);

        layout.setSizeFull();
        layout.addComponents(backgroundFieldsLayout, backgroundDescriptionLayout);
        layout.setExpandRatio(backgroundFieldsLayout, 1);
        layout.setExpandRatio(backgroundDescriptionLayout, 1);

        return layout;
    }

    @Override
    public void afterSetEntity() {
        super.afterSetEntity();
        getEntity().setCharacter(character);
    }

    @Override
    public void onSave(CharacterBackground entity) {
        if (language.getValue() != null) {
            character.getLanguages().addAll(language.getValue());
        }
        character.getSkillProficiencies().addAll(entity.getBackground().getSkillProficiencies());
        character.getToolProficiencies().addAll(entity.getBackground().getToolProficiencies());
        character.setBackground(entity);
    }

    @Override
    protected void adjustSaveButtonState() {
        if (isBound()) {
            boolean valid = getBinder().isValid();
            boolean requiredFieldsFilled = true;
            if (background.getValue() != null && language.isVisible()
                    && language.getValue().size() < background.getValue().getNbAdditionalLanguage()) {
                requiredFieldsFilled = false;
            }
            getSaveButton().setEnabled(requiredFieldsFilled && valid);
        }
    }

}
