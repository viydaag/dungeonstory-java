package com.dungeonstory.view.character;

import java.util.ArrayList;

import org.vaadin.viritin.fields.MTextArea;
import org.vaadin.viritin.fields.TypedSelect;
import org.vaadin.viritin.fields.config.ListSelectConfig;
import org.vaadin.viritin.form.AbstractForm;
import org.vaadin.viritin.label.MLabel;

import com.dungeonstory.backend.data.Background;
import com.dungeonstory.backend.data.Background.LanguageChoice;
import com.dungeonstory.backend.data.Character;
import com.dungeonstory.backend.data.CharacterBackground;
import com.dungeonstory.backend.data.Language;
import com.dungeonstory.backend.service.impl.BackgroundService;
import com.dungeonstory.backend.service.impl.LanguageService;
import com.dungeonstory.form.DSAbstractForm;
import com.dungeonstory.i18n.Messages;
import com.dungeonstory.util.converter.CollectionToStringConverter;
import com.dungeonstory.util.field.DSSubSetSelector;
import com.dungeonstory.util.layout.HorizontalSpacedLayout;
import com.dungeonstory.util.layout.VerticalSpacedLayout;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.themes.ValoTheme;

public class BackgroundChoiceForm extends DSAbstractForm<CharacterBackground> implements AbstractForm.SavedHandler<CharacterBackground> {

    private static final long serialVersionUID = -8079455641743140814L;

    private BackgroundService backgroundService = BackgroundService.getInstance();
    private LanguageService   languageService   = LanguageService.getInstance();

    private Character character;

    TypedSelect<Background>    background;
    private MTextArea          look;
    private MTextArea          traits;
    private MTextArea          ideals;
    private MTextArea          purposes;
    private MTextArea          flaws;
    DSSubSetSelector<Language> language;

    private MTextArea traitsSuggestion;
    private MTextArea idealsSuggestion;
    private MTextArea purposesSuggestion;
    private MTextArea flawsSuggestion;

    private MLabel proficienciesLabel;
    private MLabel skillProficiencies;
    private MLabel toolProficiencies;
    private MLabel additionalLanguage;

    public BackgroundChoiceForm(Character character) {
        super();
        this.character = character;
        setSavedHandler(this);
    }

    @Override
    protected Component createContent() {

        Messages messages = Messages.getInstance();

        HorizontalSpacedLayout layout = new HorizontalSpacedLayout();

        VerticalSpacedLayout backgroundFieldsLayout = new VerticalSpacedLayout();
        background = new TypedSelect<Background>(messages.getMessage("backgroundStep.background.label"), backgroundService.findAll())
                .asListSelectType(new ListSelectConfig().withRows((int) backgroundService.count())).withNullSelectionAllowed(false);
        language = new DSSubSetSelector<Language>(Language.class);
        language.setCaption(messages.getMessage("backgroundStep.languages.label"));
        language.setVisibleProperties("name");
        language.setColumnHeader("name", messages.getMessage("backgroundStep.languages.table.column.name"));
        language.setOptions(languageService.findAll());
        language.setVisible(false);

        look = new MTextArea(messages.getMessage("backgroundStep.look.label")).withFullWidth().withRows(6);
        traits = new MTextArea(messages.getMessage("backgroundStep.traits.label")).withFullWidth().withRows(6);
        ideals = new MTextArea(messages.getMessage("backgroundStep.ideals.label")).withFullWidth().withRows(6);
        purposes = new MTextArea(messages.getMessage("backgroundStep.purposes.label")).withFullWidth().withRows(6);
        flaws = new MTextArea(messages.getMessage("backgroundStep.flaws.label")).withFullWidth().withRows(6);

        FormLayout backgroundProperties = new FormLayout();
        proficienciesLabel = new MLabel().withCaption(messages.getMessage("backgroundStep.proficiencies.label")).withStyleName(ValoTheme.LABEL_H4);
        skillProficiencies = new MLabel().withCaption(messages.getMessage("backgroundStep.proficiencies.skill.label"));
        toolProficiencies = new MLabel().withCaption(messages.getMessage("backgroundStep.proficiencies.tool.label"));
        additionalLanguage = new MLabel().withCaption(messages.getMessage("backgroundStep.additionalLanguages.label"));
        backgroundProperties.addComponents(proficienciesLabel, skillProficiencies, toolProficiencies, additionalLanguage);

        traitsSuggestion = new MTextArea(messages.getMessage("backgroundStep.suggestedTraits.label")).withFullWidth().withRows(12);
        idealsSuggestion = new MTextArea(messages.getMessage("backgroundStep.suggestedIdeals.label")).withFullWidth().withRows(12);
        purposesSuggestion = new MTextArea(messages.getMessage("backgroundStep.suggestedPurposes.label")).withFullWidth().withRows(12);
        flawsSuggestion = new MTextArea(messages.getMessage("backgroundStep.suggestedFlaws.label")).withFullWidth().withRows(12);

        backgroundFieldsLayout.addComponents(background, language, look, traits, ideals, purposes, flaws);

        CollectionToStringConverter collectionConverter = new CollectionToStringConverter();

        VerticalSpacedLayout backgroundDescriptionLayout = new VerticalSpacedLayout();

        background.addMValueChangeListener(event -> {
            Background chosenBackground = event.getValue();
            if (chosenBackground != null) {
                if (chosenBackground.getAdditionalLanguage() != LanguageChoice.NONE) {
                    language.setVisible(true);
                    language.setOptions(languageService.getUnassignedLanguages(character));
                    language.setValue(new ArrayList<>());
                    language.setLimit(chosenBackground.getAdditionalLanguage().getNbLanguage());
                } else {
                    language.clear();
                    language.setVisible(false);
                }
                proficienciesLabel.setVisible(true);
                skillProficiencies.setVisible(true);
                toolProficiencies.setVisible(true);
                additionalLanguage.setVisible(true);
                skillProficiencies.setValue(chosenBackground.getSkillProficiencies().isEmpty() ? messages.getMessage("backgroundStep.none.text")
                        : collectionConverter.convertToPresentation(chosenBackground.getSkillProficiencies(), String.class, null));
                toolProficiencies.setValue(chosenBackground.getToolProficiencies().isEmpty() ? messages.getMessage("backgroundStep.none.text")
                        : collectionConverter.convertToPresentation(chosenBackground.getToolProficiencies(), String.class, null));
                additionalLanguage.setValue(String.valueOf(chosenBackground.getAdditionalLanguage().getNbLanguage()));
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

    @SuppressWarnings("unchecked")
    @Override
    public void onSave(CharacterBackground entity) {
        if (language.getValue() != null) {
            character.getLanguages().addAll(language.getValue());
        }
        character.getSkillProficiencies().addAll(entity.getBackground().getSkillProficiencies());
        character.getToolProficiencies().addAll(entity.getBackground().getToolProficiencies());
        character.setBackground(entity);
    }

}
