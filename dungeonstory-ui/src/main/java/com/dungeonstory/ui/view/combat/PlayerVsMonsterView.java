package com.dungeonstory.ui.view.combat;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

import com.dungeonstory.backend.data.Ability;
import com.dungeonstory.backend.data.Monster;
import com.dungeonstory.backend.service.Services;
import com.dungeonstory.ui.component.DSLabel;
import com.dungeonstory.ui.util.ViewConfig;
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

@ViewConfig(uri="pvm", displayName="Arena")
public class PlayerVsMonsterView extends HorizontalLayout implements View {

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
            
            Label nameLabel = new Label(monster.getName());
            nameLabel.addStyleName(ValoTheme.LABEL_H2);
            
            StringBuilder builder = new StringBuilder();
            builder.append(monster.getCreatureType().getName());
            builder.append(", ").append(monster.getSize().toString());
            if (StringUtils.isNoneBlank(monster.getTag())) {
                builder.append(", ").append("(").append(monster.getTag()).append(")");
            }
            builder.append(", ").append(monster.getAlignment().getName());
            Label typeSizeAlignmentLabel = new Label(builder.toString());
            typeSizeAlignmentLabel.addStyleName(ValoTheme.LABEL_SMALL);
            
            VerticalLayout infoLayout1 = new VerticalLayout(nameLabel, typeSizeAlignmentLabel);
            infoLayout1.setSpacing(false);
            infoLayout.addComponent(new Panel(infoLayout1));
            
            Label armorClassLabel = new DSLabel("Classe d'armure", String.valueOf(monster.getArmorClass()));
            Label hitPointLabel = new DSLabel("Points de vie", monster.getHitPoints());
            builder = new StringBuilder();
            if (monster.getGroundSpeed() > 0) {
                builder.append("Sol ").append(monster.getGroundSpeed()).append("pi");
            }
            if (monster.getFlySpeed() > 0) {
                if (builder.length() > 0) {
                    builder.append(", ");
                }
                builder.append("Vol ").append(monster.getFlySpeed()).append("pi");
            }
            if (monster.getBurrowSpeed() > 0) {
                if (builder.length() > 0) {
                    builder.append(", ");
                }
                builder.append("Sous-terre ").append(monster.getBurrowSpeed()).append("pi");
            }
            if (monster.getSwimSpeed() > 0) {
                if (builder.length() > 0) {
                    builder.append(", ");
                }
                builder.append("Nage ").append(monster.getSwimSpeed()).append("pi");
            }
            if (monster.getClimbSpeed() > 0) {
                if (builder.length() > 0) {
                    builder.append(", ");
                }
                builder.append("Grimpe ").append(monster.getClimbSpeed()).append("pi");
            }
            Label speedLabel = new DSLabel("Vitesse", builder.toString());
            FormLayout infoLayout2 = new FormLayout(armorClassLabel, hitPointLabel, speedLabel);
            infoLayout2.setSpacing(false);
            infoLayout.addComponent(new Panel(infoLayout2));
            
            GridLayout statsLayout = new GridLayout(6, 2);
            statsLayout.setWidth(100, Unit.PERCENTAGE);
            List<Ability> abilities = Services.getAbilityService().findAll();
            int column = 0;
            for (Ability ability : abilities) {
                statsLayout.addComponent(new Label(ability.getAbbreviation().toUpperCase()), column++, 0);
            }
            statsLayout.addComponent(new DSLabel(monster.getStrength()), 0, 1);
            statsLayout.addComponent(new DSLabel(monster.getStrength()), 1, 1);
            statsLayout.addComponent(new DSLabel(monster.getStrength()), 2, 1);
            statsLayout.addComponent(new DSLabel(monster.getStrength()), 3, 1);
            statsLayout.addComponent(new DSLabel(monster.getStrength()), 4, 1);
            statsLayout.addComponent(new DSLabel(monster.getStrength()), 5, 1);
            infoLayout.addComponent(new Panel(statsLayout));
            
            
            attackButton.setVisible(true);
            
        } else {
            infoLayout.removeAllComponents();
            attackButton.setVisible(false);
        }

    }

}
