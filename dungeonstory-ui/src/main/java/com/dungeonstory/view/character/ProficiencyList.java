package com.dungeonstory.view.character;

import org.apache.commons.lang3.StringUtils;

import com.dungeonstory.backend.data.Character;
import com.dungeonstory.ui.component.HtmlLabel;
import com.dungeonstory.util.converter.CollectionToStringListConverter;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

public class ProficiencyList extends VerticalLayout {

    private static final long serialVersionUID = 8431089060102974528L;

    public ProficiencyList(Character character) {
        
        setSpacing(true);

        CollectionToStringListConverter converter = new CollectionToStringListConverter();

        Label proficiencyBonus = new Label("Bonus de maitrise : " + String.valueOf(character.getLevel().getProficiencyBonus()));

        String armorProficiencies = converter.convertToPresentation(character.getArmorProficiencies(), String.class, null);
        HtmlLabel armorLabel = new HtmlLabel(StringUtils.defaultIfEmpty(armorProficiencies, "Aucune"));
        Panel armorProficiencyPanel = new Panel("Armures", armorLabel);

        String savingThrowProficiencies = converter.convertToPresentation(character.getSavingThrowProficiencies(), String.class, null);
        HtmlLabel savingThrowLabel = new HtmlLabel(StringUtils.defaultIfEmpty(savingThrowProficiencies, "Aucune"));
        Panel savingThrowProficiencyPanel = new Panel("Jets de sauvegarde", savingThrowLabel);

        String toolProficiencies = converter.convertToPresentation(character.getToolProficiencies(), String.class, null);
        HtmlLabel toolLabel = new HtmlLabel(StringUtils.defaultIfEmpty(toolProficiencies, "Aucune"));
        Panel toolProficiencyPanel = new Panel("Outils", toolLabel);

        String skillProficiencies = converter.convertToPresentation(character.getSkillProficiencies(), String.class, null);
        HtmlLabel skillLabel = new HtmlLabel(StringUtils.defaultIfEmpty(skillProficiencies, "Aucune"));
        Panel skillProficiencyPanel = new Panel("Compétences", skillLabel);

        converter.setNbColumns(2);
        String weaponProficiencies = converter.convertToPresentation(character.getWeaponProficiencies(), String.class, null);
        HtmlLabel weaponLabel = new HtmlLabel(StringUtils.defaultIfEmpty(weaponProficiencies, "Aucune"));
        Panel weaponProficiencyPanel = new Panel("Armes", weaponLabel);

        addComponents(proficiencyBonus, armorProficiencyPanel, weaponProficiencyPanel, skillProficiencyPanel, savingThrowProficiencyPanel,
                toolProficiencyPanel);

    }

}
