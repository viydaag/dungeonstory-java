package com.dungeonstory.ui.test.integration.character;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Locale;

import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

import com.dungeonstory.ui.i18n.Messages;
import com.dungeonstory.ui.test.integration.IntegrationTestBase;
import com.dungeonstory.ui.test.integration.authentication.LoginPageObject;
import com.vaadin.testbench.elements.ButtonElement;
import com.vaadin.testbench.elements.ComboBoxElement;
import com.vaadin.testbench.elements.CustomFieldElement;
import com.vaadin.testbench.elements.FormLayoutElement;
import com.vaadin.testbench.elements.GridElement;
import com.vaadin.testbench.elements.GridLayoutElement;
import com.vaadin.testbench.elements.ImageElement;
import com.vaadin.testbench.elements.LabelElement;
import com.vaadin.testbench.elements.MenuBarElement;
import com.vaadin.testbench.elements.NotificationElement;
import com.vaadin.testbench.elements.RadioButtonGroupElement;
import com.vaadin.testbench.elements.TextAreaElement;
import com.vaadin.testbench.elements.TextFieldElement;
import com.vaadin.testbench.elements.VerticalLayoutElement;

public class NewCharacterIT
        extends IntegrationTestBase {

    private static final String RASHEMEN   = "Rashemen";
    private static final String HOMME      = "Homme";
    private static final String ACOLYTE    = "Acolyte";
    private static final String NEUTRE_BON = "Neutre Bon";
    private static final String HUMAIN     = "Humain";
    private static final String BARBARE    = "Barbare";

    private LoginPageObject        loginPO;
    private NewCharacterPageObject newCharacterPO;
    private static int             random = getRandomNumberInRange(1, 100);
    private final String           name   = "TestBarbarian" + random;
    private Messages               messages;

    @Override
    public void setUp() throws Exception {
        super.setUp();

        messages = Messages.getInstance(Locale.getDefault());

        loginPO = PageFactory.initElements(getDriver(), LoginPageObject.class);
        loginPO.login("admin", "admin");
    }

    @Test
    public void barbarian() {
        $(ButtonElement.class).caption(messages.getMessage("newCharacterView.caption")).first().click();

        newCharacterPO = PageFactory.initElements(getDriver(), NewCharacterPageObject.class);

        //Race choice
        ButtonElement nextButton = $(ButtonElement.class).id("nextButton");
        assertFalse(nextButton.isEnabled());
        ButtonElement backButton = $(ButtonElement.class).id("backButton");
        assertFalse(backButton.isEnabled());
        ButtonElement finishButton = $(ButtonElement.class).id("finishButton");
        assertFalse(finishButton.isEnabled());
        ButtonElement cancelButton = $(ButtonElement.class).id("cancelButton");
        assertTrue(cancelButton.isEnabled());

        newCharacterPO.chooseRace(HUMAIN);

        TextAreaElement descriptionTextArea = $(TextAreaElement.class).caption("Description").first();
        assertFalse(descriptionTextArea.getValue().isEmpty());
        assertFalse(nextButton.isEnabled());

        newCharacterPO.chooseRaceLanguage("Elfique");
        assertTrue(nextButton.isEnabled());

        newCharacterPO.clickNext();

        //Class choice
        assertFalse(nextButton.isEnabled());
        assertTrue(backButton.isEnabled());
        assertFalse(finishButton.isEnabled());
        assertTrue(cancelButton.isEnabled());

        newCharacterPO.chooseClass(BARBARE);

        TextAreaElement classDescriptionTextArea = $(TextAreaElement.class).caption("Description").first();
        assertFalse(classDescriptionTextArea.getValue().isEmpty());
        assertFalse(nextButton.isEnabled());
        LabelElement classFeatureList = $(VerticalLayoutElement.class).$$(LabelElement.class).first();
        FormLayoutElement proficiencies = $(FormLayoutElement.class).first();
        assertNotNull(classFeatureList);
        assertNotNull(proficiencies);

        ComboBoxElement classSkills = $(CustomFieldElement.class).caption("Maitrises de compétence (2)").$(ComboBoxElement.class).first();
        classSkills.selectByText("Athlétisme");
        GridElement classSkillsGrid = $(GridElement.class).first();
        assertEquals(1, classSkillsGrid.getRowCount());
        assertEquals("Athlétisme", classSkillsGrid.getRow(0).getCell(1).getText());
        assertFalse(nextButton.isEnabled());

        ButtonElement deleteSkillButton1 = $(VerticalLayoutElement.class).$(ButtonElement.class).first();
        deleteSkillButton1.click();
        assertEquals(0, classSkillsGrid.getRowCount());
        assertFalse(nextButton.isEnabled());

        classSkills.selectByText("Athlétisme");
        classSkills.selectByText("Intimidation");
        assertFalse(classSkills.isEnabled());
        assertTrue(nextButton.isEnabled());

        newCharacterPO.clickNext();

        //Ability scores
        assertFalse(nextButton.isEnabled());
        assertTrue(backButton.isEnabled());
        assertFalse(finishButton.isEnabled());
        assertTrue(cancelButton.isEnabled());

        TextFieldElement points = $(TextFieldElement.class).first();
        moveTo(points);
        assertEquals("27", points.getValue());

        TextFieldElement strScore = $(GridLayoutElement.class).$(TextFieldElement.class).get(1);
        TextFieldElement dexScore = $(GridLayoutElement.class).$(TextFieldElement.class).get(3);
        TextFieldElement conScore = $(GridLayoutElement.class).$(TextFieldElement.class).get(5);
        TextFieldElement intScore = $(GridLayoutElement.class).$(TextFieldElement.class).get(7);
        TextFieldElement wisScore = $(GridLayoutElement.class).$(TextFieldElement.class).get(9);
        TextFieldElement chaScore = $(GridLayoutElement.class).$(TextFieldElement.class).get(11);

        //humans have 1 more in each abilities
        assertEquals("9", strScore.getValue());
        assertEquals("9", dexScore.getValue());
        assertEquals("9", conScore.getValue());
        assertEquals("9", intScore.getValue());
        assertEquals("9", wisScore.getValue());
        assertEquals("9", chaScore.getValue());

        ButtonElement strPlusButton = $(GridLayoutElement.class).$$(ButtonElement.class).get(0);
        ButtonElement dexPlusButton = $(GridLayoutElement.class).$$(ButtonElement.class).get(2);
        ButtonElement conPlusButton = $(GridLayoutElement.class).$$(ButtonElement.class).get(4);
        ButtonElement intPlusButton = $(GridLayoutElement.class).$$(ButtonElement.class).get(6);
        ButtonElement wisPlusButton = $(GridLayoutElement.class).$$(ButtonElement.class).get(8);
        ButtonElement chaPlusButton = $(GridLayoutElement.class).$$(ButtonElement.class).get(10);

        click(strPlusButton, 8);

        NotificationElement notification = $(NotificationElement.class).first();
        assertEquals("humanized", notification.getType());
        notification.close();
        assertEquals("16", strScore.getValue());
        assertEquals("19", points.getValue());

        click(dexPlusButton, 4);
        assertEquals("13", dexScore.getValue());
        assertEquals("15", points.getValue());

        click(conPlusButton, 3);
        assertEquals("12", conScore.getValue());
        assertEquals("12", points.getValue());

        click(intPlusButton, 1);
        assertEquals("10", intScore.getValue());
        assertEquals("11", points.getValue());
        ButtonElement intMinusButton = $(GridLayoutElement.class).$$(ButtonElement.class).get(7);
        intMinusButton.click();
        assertEquals("9", intScore.getValue());
        assertEquals("12", points.getValue());
        intMinusButton.click();
        notification = $(NotificationElement.class).first();
        assertEquals("humanized", notification.getType());
        notification.close();

        click(wisPlusButton, 2);
        click(chaPlusButton, 3);
        assertEquals("7", points.getValue());
        assertFalse(nextButton.isEnabled());

        click(dexPlusButton, 3);
        click(conPlusButton, 4);
        assertEquals("0", points.getValue());
        assertTrue(nextButton.isEnabled());

        newCharacterPO.clickNext();

        //background choice
        assertFalse(nextButton.isEnabled());
        assertTrue(backButton.isEnabled());
        assertFalse(finishButton.isEnabled());
        assertTrue(cancelButton.isEnabled());

        //        TextAreaElement lookTextArea = $(TextAreaElement.class).caption(messages.getMessage("backgroundStep.look.label")).first();
        TextAreaElement traitsTextArea = $(TextAreaElement.class).caption(messages.getMessage("backgroundStep.suggestedTraits.label")).first();
        TextAreaElement idealsTextArea = $(TextAreaElement.class).caption(messages.getMessage("backgroundStep.suggestedIdeals.label")).first();
        TextAreaElement purposesTextArea = $(TextAreaElement.class).caption(messages.getMessage("backgroundStep.suggestedPurposes.label")).first();
        TextAreaElement flawsTextArea = $(TextAreaElement.class).caption(messages.getMessage("backgroundStep.suggestedFlaws.label")).first();

        assertTrue(traitsTextArea.getValue().isEmpty());
        assertTrue(idealsTextArea.getValue().isEmpty());
        assertTrue(purposesTextArea.getValue().isEmpty());
        assertTrue(flawsTextArea.getValue().isEmpty());

        newCharacterPO.chooseBackground(ACOLYTE);

        assertFalse(traitsTextArea.getValue().isEmpty());
        assertFalse(idealsTextArea.getValue().isEmpty());
        assertFalse(purposesTextArea.getValue().isEmpty());
        assertFalse(flawsTextArea.getValue().isEmpty());
        assertFalse(nextButton.isEnabled());

        ComboBoxElement bgLanguageComboBox = $(CustomFieldElement.class).caption(messages.getMessage("backgroundStep.languages.label"))
                                                                        .$(ComboBoxElement.class)
                                                                        .first();
        newCharacterPO.chooseBackgroundLanguage("Gobelin");
        assertTrue(bgLanguageComboBox.isEnabled());
        assertFalse(nextButton.isEnabled());
        newCharacterPO.chooseBackgroundLanguage("Halfelin");
        assertFalse(bgLanguageComboBox.isEnabled());
        assertTrue(nextButton.isEnabled());

        newCharacterPO.clickNext();

        //Info
        assertFalse(nextButton.isEnabled());
        assertTrue(backButton.isEnabled());
        assertFalse(finishButton.isEnabled());
        assertTrue(cancelButton.isEnabled());

        TextFieldElement nameTextField = $(TextFieldElement.class).caption(messages.getMessage("informationStep.name.label")).first();
        RadioButtonGroupElement sexRadioButtonGroup = $(RadioButtonGroupElement.class).caption(messages.getMessage("informationStep.sex.label"))
                                                                                      .first();
        TextFieldElement age = $(CustomFieldElement.class).caption(messages.getMessage("informationStep.age.label"))
                                                          .$$(TextFieldElement.class)
                                                          .first();
        TextFieldElement weight = $(CustomFieldElement.class).caption(messages.getMessage("informationStep.weight.label"))
                                                             .$$(TextFieldElement.class)
                                                             .first();
        TextFieldElement height = $(TextFieldElement.class).caption(messages.getMessage("informationStep.height.label")).first();
        ComboBoxElement alignmentComboBox = $(ComboBoxElement.class).caption(messages.getMessage("informationStep.alignment.label")).first();
        ComboBoxElement regionComboBox = $(ComboBoxElement.class).caption(messages.getMessage("informationStep.region.label")).first();

        nameTextField.setValue(name);
        sexRadioButtonGroup.selectByText(HOMME);

        age.setValue("12");
        height.setValue("6");
        weight.setValue("200");
        alignmentComboBox.selectByText(NEUTRE_BON);
        regionComboBox.selectByText(RASHEMEN);

        ImageElement image1 = $(ImageElement.class).first();
        String imgSrc = getAttribute(image1, "src").substring(getAttribute(image1, "src").lastIndexOf('/'));
        image1.click();

        assertTrue(height.hasClassName("v-textfield-error"));
        assertTrue(age.hasClassName("v-textfield-error"));
        assertFalse(nextButton.isEnabled());

        age.setValue("20");
        height.setValue("6'");

        assertFalse(height.hasClassName("v-textfield-error"));
        assertFalse(age.hasClassName("v-textfield-error"));
        assertTrue(nextButton.isEnabled());

        newCharacterPO.clickNext();

        //Summary
        assertFalse(nextButton.isEnabled());
        assertTrue(backButton.isEnabled());
        assertTrue(finishButton.isEnabled());
        assertTrue(cancelButton.isEnabled());

        LabelElement levelLabel = $(LabelElement.class).caption(messages.getMessage("summaryStep.level.label")).first();
        LabelElement raceLabel = $(LabelElement.class).caption(messages.getMessage("summaryStep.race.label")).first();
        LabelElement languageLabel = $(LabelElement.class).caption(messages.getMessage("summaryStep.language.label")).first();
        LabelElement classLabel = $(LabelElement.class).caption(messages.getMessage("summaryStep.class.label")).first();
        LabelElement lifeLabel = $(LabelElement.class).caption(messages.getMessage("summaryStep.lifePoint.label")).first();

        assertEquals("1", getText(levelLabel));
        assertEquals(HUMAIN, getText(raceLabel));
        assertTrue(getText(languageLabel).contains("Commun"));
        assertTrue(getText(languageLabel).contains("Elfique"));
        assertTrue(getText(languageLabel).contains("Gobelin"));
        assertTrue(getText(languageLabel).contains("Halfelin"));
        assertTrue(getText(classLabel).contains(BARBARE));
        assertEquals("14", getText(lifeLabel));

        LabelElement strLabel = $(LabelElement.class).caption(messages.getMessage("ability.str.caption")).first();
        LabelElement dexLabel = $(LabelElement.class).caption(messages.getMessage("ability.dex.caption")).first();
        LabelElement conLabel = $(LabelElement.class).caption(messages.getMessage("ability.con.caption")).first();
        LabelElement intLabel = $(LabelElement.class).caption(messages.getMessage("ability.int.caption")).first();
        LabelElement wisLabel = $(LabelElement.class).caption(messages.getMessage("ability.wis.caption")).first();
        LabelElement chaLabel = $(LabelElement.class).caption(messages.getMessage("ability.cha.caption")).first();

        assertEquals("16", getText(strLabel));
        assertEquals("16", getText(dexLabel));
        assertEquals("14", getText(conLabel));
        assertEquals("9", getText(intLabel));
        assertEquals("11", getText(wisLabel));
        assertEquals("12", getText(chaLabel));

        LabelElement armorProficiencyLabel = $(LabelElement.class).caption(messages.getMessage("summaryStep.proficiency.armor.label")).first();
        LabelElement weaponProficiencyLabel = $(LabelElement.class).caption(messages.getMessage("summaryStep.proficiency.weapon.label")).first();
        LabelElement savingThrowProficiencyLabel = $(LabelElement.class).caption(messages.getMessage("summaryStep.proficiency.savingThrow.label"))
                                                                        .first();
        LabelElement skillProficiencyLabel = $(LabelElement.class).caption(messages.getMessage("summaryStep.proficiency.skill.label")).first();

        assertTrue(getText(skillProficiencyLabel).contains("Athlétisme"));
        assertTrue(getText(skillProficiencyLabel).contains("Intimidation"));

        ImageElement summaryImage = $(ImageElement.class).caption("Image").first();
        String summaryImgSrc = getAttribute(summaryImage, "src").substring(getAttribute(summaryImage, "src").lastIndexOf('/'));
        assertEquals(summaryImgSrc, imgSrc);

        LabelElement nameLabel = $(LabelElement.class).caption(messages.getMessage("summaryStep.name.label")).first();
        LabelElement sexLabel = $(LabelElement.class).caption(messages.getMessage("summaryStep.sex.label")).first();
        LabelElement ageLabel = $(LabelElement.class).caption(messages.getMessage("summaryStep.age.label")).first();
        LabelElement weightLabel = $(LabelElement.class).caption(messages.getMessage("summaryStep.weight.label")).first();
        LabelElement heightLabel = $(LabelElement.class).caption(messages.getMessage("summaryStep.height.label")).first();
        LabelElement alignmentLabel = $(LabelElement.class).caption(messages.getMessage("summaryStep.alignment.label")).first();
        LabelElement regionLabel = $(LabelElement.class).caption(messages.getMessage("summaryStep.region.label")).first();
        LabelElement backgroundLabel = $(LabelElement.class).caption(messages.getMessage("summaryStep.background.label")).first();

        assertEquals(name, getText(nameLabel));
        assertEquals(HOMME, getText(sexLabel));
        assertEquals("20", getText(ageLabel));
        assertEquals("200 lbs", getText(weightLabel));
        assertEquals("6'", getText(heightLabel));
        assertEquals(NEUTRE_BON, getText(alignmentLabel));
        assertEquals(RASHEMEN, getText(regionLabel));
        assertEquals(ACOLYTE, getText(backgroundLabel));

        newCharacterPO.clickFinish();

        //Character view
        MenuBarElement menuBar = $(MenuBarElement.class).first();
        menuBar.clickItem("Personnage");

        LabelElement cName = $(CustomFieldElement.class).caption("Nom").$$(LabelElement.class).first();
        assertEquals(name, cName.getText());
        LabelElement cSex = $(CustomFieldElement.class).caption("Genre").$$(LabelElement.class).first();
        assertEquals(HOMME, cSex.getText());
        LabelElement cAlignment = $(CustomFieldElement.class).caption("Alignement").$$(LabelElement.class).first();
        assertEquals(NEUTRE_BON, cAlignment.getText());
        LabelElement cRegion = $(CustomFieldElement.class).caption("Région").$$(LabelElement.class).first();
        assertEquals(RASHEMEN, cRegion.getText());
        LabelElement cAge = $(CustomFieldElement.class).caption("Âge").$$(LabelElement.class).first();
        assertEquals("20", cAge.getText());
        LabelElement cWeight = $(CustomFieldElement.class).caption("Poids").$$(LabelElement.class).first();
        assertEquals("200", cWeight.getText());
        LabelElement cHeight = $(CustomFieldElement.class).caption("Taille").$$(LabelElement.class).first();
        assertEquals("6'", cHeight.getText());
    }

}
