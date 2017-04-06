package com.dungeonstory.view.character;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import org.vaadin.viritin.label.MLabel;

import com.dungeonstory.DSConstant;
import com.dungeonstory.backend.data.Character;
import com.dungeonstory.backend.data.CharacterClass;
import com.dungeonstory.backend.data.Feat;
import com.dungeonstory.backend.data.util.ModifierUtil;
import com.dungeonstory.i18n.Messages;
import com.dungeonstory.util.CharacterWizardStep;
import com.dungeonstory.util.converter.CollectionToStringConverter;
import com.dungeonstory.util.layout.FormLayoutNoSpace;
import com.vaadin.server.FileResource;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.themes.ValoTheme;

public class SummaryStep extends CharacterWizardStep<Character> {

    private static final long serialVersionUID = -8516216761141601232L;

    public SummaryStep(CharacterWizard wizard) {
        super(wizard);
    }

    @Override
    public String getCaption() {
        return Messages.getInstance().getMessage("summaryStep.caption");
    }

    @Override
    public Component getContent() {

        Messages messages = Messages.getInstance();

        Character character = wizard.getCharacter();
        Character original = wizard.getOriginal();

        FormLayout layout = new FormLayout();
        layout.setMargin(new MarginInfo(true, true));

        MLabel levelLabel = new MLabel(messages.getMessage("summaryStep.level.label"), character.getLevel().toString());
        layout.addComponent(levelLabel);

        CollectionToStringConverter collectionConverter = new CollectionToStringConverter();

        if (character.getId() == null) {
            MLabel raceLabel = new MLabel(messages.getMessage("summaryStep.race.label"), character.getRace().getName());
            layout.addComponent(raceLabel);
            MLabel languagesLabel = new MLabel(messages.getMessage("summaryStep.language.label"),
                    collectionConverter.convertToPresentation(character.getLanguages(), String.class, null));
            layout.addComponent(languagesLabel);
        }

        CollectionToStringConverter classCollectionConverter = new CollectionToStringConverter();
        classCollectionConverter.setDelimiter(" / ");
        MLabel classesLabel = new MLabel(messages.getMessage("summaryStep.class.label"),
                classCollectionConverter.convertToPresentation(character.getClasses(), String.class, null));
        layout.addComponent(classesLabel);

        int nbLifePoints = 0;
        for (CharacterClass cc : character.getClasses()) {
            nbLifePoints += (cc.getClassLevel()
                    * (cc.getClasse().getLifePointPerLevel() + ModifierUtil.getAbilityModifier(character.getConstitution())));
        }
        int difLifePoints = nbLifePoints - original.getLifePoints();

        if (character.getId() == null) {
            MLabel lifePoints = new MLabel(messages.getMessage("summaryStep.lifePoint.label"), String.valueOf(nbLifePoints));
            layout.addComponent(lifePoints);
        } else {
            MLabel lifePointsLabel = new MLabel(messages.getMessage("summaryStep.lifePoint.label"),
                    String.valueOf(nbLifePoints) + "(+" + difLifePoints + ")");
            layout.addComponent(lifePointsLabel);
        }
        character.setLifePoints(nbLifePoints);

        Set<Feat> featSet = new HashSet<Feat>(character.getFeats());
        featSet.removeAll(original.getFeats());
        if (!featSet.isEmpty()) {
            MLabel featsLabel = new MLabel(messages.getMessage("summaryStep.newFeat.label"),
                    collectionConverter.convertToPresentation(featSet, String.class, null));
            layout.addComponent(featsLabel);
        }

        if (character.getId() == null) {
            MLabel abilitiesLabel = new MLabel().withCaption(messages.getMessage("summaryStep.ability.label")).withStyleName(ValoTheme.LABEL_H4);
            FormLayoutNoSpace abilityLayout = new FormLayoutNoSpace();
            MLabel strengthLabel = new MLabel(messages.getMessage("ability.str.caption"), String.valueOf(character.getStrength()));
            MLabel dexterityLabel = new MLabel(messages.getMessage("ability.dex.caption"), String.valueOf(character.getDexterity()));
            MLabel constitutionLabel = new MLabel(messages.getMessage("ability.con.caption"), String.valueOf(character.getConstitution()));
            MLabel intelligneceLabel = new MLabel(messages.getMessage("ability.int.caption"), String.valueOf(character.getIntelligence()));
            MLabel wisdomLabel = new MLabel(messages.getMessage("ability.wis.caption"), String.valueOf(character.getWisdom()));
            MLabel charismaLabel = new MLabel(messages.getMessage("ability.cha.caption"), String.valueOf(character.getCharisma()));
            abilityLayout.addComponents(strengthLabel, dexterityLabel, constitutionLabel, intelligneceLabel, wisdomLabel, charismaLabel);
            layout.addComponents(abilitiesLabel, abilityLayout);
        } else {
            //check which ability has changed with original
        }

        if (character.getId() == null) {
            MLabel proficienciesLabel = new MLabel().withCaption(messages.getMessage("summaryStep.proficiency.label"))
                    .withStyleName(ValoTheme.LABEL_H4);
            MLabel armorProficienciesLabel = new MLabel(messages.getMessage("summaryStep.proficiency.armor.label"),
                    collectionConverter.convertToPresentation(character.getArmorProficiencies(), String.class, null));
            MLabel weaponProficienciesLabel = new MLabel(messages.getMessage("summaryStep.proficiency.weapon.label"),
                    collectionConverter.convertToPresentation(character.getWeaponProficiencies(), String.class, null));
            MLabel savingThrowProficienciesLabel = new MLabel(messages.getMessage("summaryStep.proficiency.savingThrow.label"),
                    collectionConverter.convertToPresentation(character.getSavingThrowProficiencies(), String.class, null));
            MLabel skillProficienciesLabel = new MLabel(messages.getMessage("summaryStep.proficiency.skill.label"),
                    collectionConverter.convertToPresentation(character.getSkillProficiencies(), String.class, null));
            layout.addComponents(proficienciesLabel, armorProficienciesLabel, weaponProficienciesLabel, savingThrowProficienciesLabel,
                    skillProficienciesLabel);
        } else {
            //check if new proficiencies are available
        }

        if (character.getId() == null) {
            MLabel infoLabel = new MLabel().withCaption(messages.getMessage("summaryStep.info.label")).withStyleName(ValoTheme.LABEL_H4);
            File imageFile = new File(DSConstant.getImageDir() + character.getImage());
            FileResource resource = new FileResource(imageFile);
            Image image = new Image("Image", resource);
            layout.addComponent(image);
            MLabel nameLabel = new MLabel(messages.getMessage("summaryStep.name.label"), character.getName());
            MLabel genderLabel = new MLabel(messages.getMessage("summaryStep.sex.label"), character.getGender().toString());
            MLabel ageLabel = new MLabel(messages.getMessage("summaryStep.age.label"), String.valueOf(character.getAge()));
            MLabel weightLabel = new MLabel(messages.getMessage("summaryStep.weight.label"), character.getWeight() + " lbs");
            MLabel heightLabel = new MLabel(messages.getMessage("summaryStep.height.label"), character.getHeight());
            MLabel alignmentLabel = new MLabel(messages.getMessage("summaryStep.alignment.label"), character.getAlignment().toString());
            MLabel regionLabel = new MLabel(messages.getMessage("summaryStep.region.label"), character.getRegion().toString());
            MLabel backgroundLabel = new MLabel(messages.getMessage("summaryStep.background.label"),
                    character.getBackground().getBackground().toString());
            layout.addComponents(infoLabel, image, nameLabel, genderLabel, ageLabel, weightLabel, heightLabel, alignmentLabel, regionLabel,
                    backgroundLabel);
        }

        //Starting gold

        return layout;
    }

}
