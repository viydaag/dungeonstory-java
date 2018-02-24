package com.dungeonstory.ui.view.combat;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

import com.dungeonstory.backend.data.Monster;
import com.dungeonstory.backend.data.enums.Ability2;
import com.dungeonstory.backend.rules.Rules;
import com.dungeonstory.backend.service.Services;
import com.dungeonstory.ui.component.DSLabel;
import com.dungeonstory.ui.converter.CollectionToStringConverter;
import com.dungeonstory.ui.i18n.Messages;
import com.dungeonstory.ui.util.ViewConfig;
import com.vaadin.data.ValueContext;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@ViewConfig(uri=PlayerVsMonsterListView.URI, displayName="Arena")
public class PlayerVsMonsterListView extends HorizontalLayout implements View {

    public static final String URI = "pvmList";

    private static final long serialVersionUID = -6027449112006294889L;

    private VerticalLayout   leftLayout;
    private VerticalLayout   rightLayout;
    private VerticalLayout   infoLayout;
    private Button           attackButton;

    @Override
    public void enter(ViewChangeEvent event) {

        leftLayout = new VerticalLayout();
        rightLayout = new VerticalLayout();
        infoLayout = new VerticalLayout();
        infoLayout.setSpacing(false);
        infoLayout.setMargin(false);
        
        attackButton = new Button("Attaquer", VaadinIcons.SWORD);
        attackButton.addStyleName(ValoTheme.BUTTON_LARGE);
        attackButton.addStyleName(ValoTheme.BUTTON_PRIMARY);
        attackButton.setVisible(false);
        
        Grid<Monster> monsterGrid = new Grid<>();
        monsterGrid.setSelectionMode(SelectionMode.SINGLE);
        monsterGrid.addColumn(Monster::getChallengeRating).setCaption("DD").setId("challengeRating");
        monsterGrid.addColumn(Monster::getName).setCaption("Nom").setId("name");
        monsterGrid.setItems(Services.getMonsterService().findAll());

        monsterGrid.addSelectionListener(selection -> {
            showMonsterInfo(selection.getFirstSelectedItem());
        });

        leftLayout.addComponent(monsterGrid);
        rightLayout.addComponents(infoLayout, attackButton);
        addComponents(leftLayout, rightLayout);
        
        setWidth(100, Unit.PERCENTAGE);
        setExpandRatio(leftLayout, 1);
        setExpandRatio(rightLayout, 1);
    }

    private void showMonsterInfo(Optional<Monster> selectedItem) {
        if (selectedItem.isPresent()) {
            Monster monster = selectedItem.get();
            Messages messages = Messages.getInstance();
            
            Label nameLabel = new Label(monster.getName());
            nameLabel.addStyleName(ValoTheme.LABEL_H2);
            
            StringBuilder builder = new StringBuilder();
            builder.append(monster.getCreatureType().getName());
            if (StringUtils.isNoneBlank(monster.getTag())) {
                builder.append(" (").append(monster.getTag()).append(")");
            }
            builder.append(", ").append(monster.getSize().toString());
            
            builder.append(", ").append(monster.getAlignment().getName());
            Label typeSizeAlignmentLabel = new Label(builder.toString());
            typeSizeAlignmentLabel.addStyleName(ValoTheme.LABEL_SMALL);
            
            VerticalLayout infoLayout1 = new VerticalLayout(nameLabel, typeSizeAlignmentLabel);
            infoLayout1.setSpacing(false);
            infoLayout1.setMargin(false);
            infoLayout.addComponent(new Panel(infoLayout1));
            
            Label armorClassLabel = new DSLabel("Classe d'armure", String.valueOf(monster.getArmorClass()));
            Label hitPointLabel = new DSLabel("Points de vie", monster.getHitPoints());
            builder = new StringBuilder();
            if (monster.getGroundSpeed() > 0) {
                builder.append("Sol ").append(monster.getGroundSpeed()).append(" pi");
            }
            if (monster.getFlySpeed() > 0) {
                if (builder.length() > 0) {
                    builder.append(", ");
                }
                builder.append("Vol ").append(monster.getFlySpeed()).append(" pi");
            }
            if (monster.getBurrowSpeed() > 0) {
                if (builder.length() > 0) {
                    builder.append(", ");
                }
                builder.append("Sous-terre ").append(monster.getBurrowSpeed()).append(" pi");
            }
            if (monster.getSwimSpeed() > 0) {
                if (builder.length() > 0) {
                    builder.append(", ");
                }
                builder.append("Nage ").append(monster.getSwimSpeed()).append(" pi");
            }
            if (monster.getClimbSpeed() > 0) {
                if (builder.length() > 0) {
                    builder.append(", ");
                }
                builder.append("Grimpe ").append(monster.getClimbSpeed()).append(" pi");
            }
            Label speedLabel = new DSLabel("Vitesse", builder.toString());
            FormLayout infoLayout2 = new FormLayout(armorClassLabel, hitPointLabel, speedLabel);
            infoLayout2.setSpacing(false);
            infoLayout2.setMargin(false);
            infoLayout.addComponent(new Panel(infoLayout2));
            
            GridLayout statsLayout = new GridLayout(6, 2);
            statsLayout.setWidth(100, Unit.PERCENTAGE);
            int column = 0;
            for (Ability2 ability : Ability2.values()) {
                statsLayout.addComponent(new Label(messages.getMessage(ability.getAbbreviationKey()).toUpperCase()), column++, 0);
            }
            statsLayout.addComponent(new DSLabel(monster.getStrength()), 0, 1);
            statsLayout.addComponent(new DSLabel(monster.getDexterity()), 1, 1);
            statsLayout.addComponent(new DSLabel(monster.getConstitution()), 2, 1);
            statsLayout.addComponent(new DSLabel(monster.getIntelligence()), 3, 1);
            statsLayout.addComponent(new DSLabel(monster.getWisdom()), 4, 1);
            statsLayout.addComponent(new DSLabel(monster.getCharisma()), 5, 1);
            infoLayout.addComponent(new Panel(statsLayout));
            
            FormLayout infoLayout3 = new FormLayout();
            infoLayout3.setSpacing(false);
            infoLayout3.setMargin(false);
            CollectionToStringConverter listConverter = new CollectionToStringConverter();
            Label savingThrowsLabel = new DSLabel("Jets de sauvegarde", listConverter.convertToPresentation(Rules.getMonsterSavingThrows(monster), new ValueContext()));
            Label skillsLabel = new DSLabel("Compétences", listConverter.convertToPresentation(monster.getSkills(), new ValueContext()));
            Label damageVulnerabilityLabel = new DSLabel("Vulnérabilités", listConverter.convertToPresentation(monster.getDamageVulnerabilities(), new ValueContext()));
            Label damageResistanceLabel = new DSLabel("Résistances", listConverter.convertToPresentation(monster.getDamageResistances(), new ValueContext()));
            Label damageImmunityLabel = new DSLabel("Immunités aux dégâts", listConverter.convertToPresentation(monster.getDamageImmunities(), new ValueContext()));
            Label conditionImmunityLabel = new DSLabel("Immunités aux conditions", listConverter.convertToPresentation(monster.getConditionImmunities(), new ValueContext()));
            Label sensesLabel = new DSLabel("Sens", listConverter.convertToPresentation(monster.getSenses(), new ValueContext()));
            Label perceptionLabel = new DSLabel("Perception passive", monster.getPassivePerception());
            Label languageLabel = new DSLabel("Langage(s)", monster.getLanguages().isEmpty() ? "---" : listConverter.convertToPresentation(monster.getLanguages(), new ValueContext()));
            Label challengeRatingLabel = new DSLabel("Difficulté", monster.getChallengeRating().getValueWithExperience());
            
            if (!savingThrowsLabel.getValue().isEmpty()) {
                infoLayout3.addComponent(savingThrowsLabel);
            }
            if (!skillsLabel.getValue().isEmpty()) {
                infoLayout3.addComponent(skillsLabel);
            }
            if (!damageVulnerabilityLabel.getValue().isEmpty()) {
                infoLayout3.addComponent(damageVulnerabilityLabel);
            }
            if (!damageResistanceLabel.getValue().isEmpty()) {
                infoLayout3.addComponent(damageResistanceLabel);
            }
            if (!damageImmunityLabel.getValue().isEmpty()) {
                infoLayout3.addComponent(damageImmunityLabel);
            }
            if (!conditionImmunityLabel.getValue().isEmpty()) {
                infoLayout3.addComponent(conditionImmunityLabel);
            }
            if (!sensesLabel.getValue().isEmpty()) {
                infoLayout3.addComponent(sensesLabel);
            }
            infoLayout3.addComponent(perceptionLabel);
            infoLayout3.addComponent(languageLabel);
            infoLayout3.addComponent(challengeRatingLabel);
            infoLayout.addComponent(new Panel(infoLayout3));
            
            
            attackButton.setVisible(true);
            
        } else {
            infoLayout.removeAllComponents();
            attackButton.setVisible(false);
        }

    }

}
