package com.dungeonstory.ui.view.character;

import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.dungeonstory.backend.data.Character;
import com.dungeonstory.ui.component.HtmlLabel;
import com.dungeonstory.ui.converter.CollectionToStringListConverter;
import com.vaadin.data.ValueContext;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

public class ProficiencyList extends VerticalLayout {

    private static final long serialVersionUID = 8431089060102974528L;

    public ProficiencyList(Character character) {
        
        setSpacing(true);

        CollectionToStringListConverter<Set<?>> setConverter = new CollectionToStringListConverter<Set<?>>();

        Label proficiencyBonus = new Label("Bonus de maitrise : " + String.valueOf(character.getLevel().getProficiencyBonus()));

        String armorProficiencies = setConverter.convertToPresentation(character.getArmorProficiencies(), new ValueContext());
        HtmlLabel armorLabel = new HtmlLabel(StringUtils.defaultIfEmpty(armorProficiencies, "Aucune"));
        Panel armorProficiencyPanel = new Panel("Armures", armorLabel);

        String savingThrowProficiencies = setConverter.convertToPresentation(character.getSavingThrowProficiencies(), new ValueContext());
        HtmlLabel savingThrowLabel = new HtmlLabel(StringUtils.defaultIfEmpty(savingThrowProficiencies, "Aucune"));
        Panel savingThrowProficiencyPanel = new Panel("Jets de sauvegarde", savingThrowLabel);

        String toolProficiencies = setConverter.convertToPresentation(character.getToolProficiencies(), new ValueContext());
        HtmlLabel toolLabel = new HtmlLabel(StringUtils.defaultIfEmpty(toolProficiencies, "Aucune"));
        Panel toolProficiencyPanel = new Panel("Outils", toolLabel);

        String skillProficiencies = setConverter.convertToPresentation(character.getSkillProficiencies(), new ValueContext());
        HtmlLabel skillLabel = new HtmlLabel(StringUtils.defaultIfEmpty(skillProficiencies, "Aucune"));
        Panel skillProficiencyPanel = new Panel("Compétences", skillLabel);

        setConverter.setNbColumns(2);
        String weaponProficiencies = setConverter.convertToPresentation(character.getWeaponProficiencies(), new ValueContext());
        HtmlLabel weaponLabel = new HtmlLabel(StringUtils.defaultIfEmpty(weaponProficiencies, "Aucune"));
        Panel weaponProficiencyPanel = new Panel("Armes", weaponLabel);

        addComponents(proficiencyBonus, armorProficiencyPanel, weaponProficiencyPanel, skillProficiencyPanel, savingThrowProficiencyPanel,
                toolProficiencyPanel);

    }

}
