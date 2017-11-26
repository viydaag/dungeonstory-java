package com.dungeonstory.ui.field;

import java.util.stream.IntStream;

import com.dungeonstory.backend.data.ClassLevelBonus;
import com.dungeonstory.backend.data.Level;
import com.vaadin.data.HasValue;
import com.vaadin.fluent.ui.FComboBox;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.TextField;

public class LevelBonusCollectionField
        extends ElementCollectionField<ClassLevelBonus> {

    private static final long serialVersionUID = 2580712139242043824L;

    private boolean martialArts = false;
    private boolean sorcery     = false;
    private boolean rage        = false;
    private boolean invocation  = false;
    private boolean hunter      = false;
    private boolean sneak       = false;
    private boolean deity       = false;

    private final int[] HUNTER_COLUMNS       = { 3, 4 };
    private final int[] DEITY_COLUMNS        = { 5 };
    private final int[] MARTIAL_ARTS_COLUMNS = { 6, 7, 8 };
    private final int[] SORCERY_COLUMNS      = { 9 };
    private final int[] RAGE_COLUMNS         = { 10, 11 };
    private final int[] INVOCATION_COLUMNS   = { 12 };
    private final int[] SNEAK_COLUMNS        = { 13 };

    public static class ClassLevelBonusRow {
        public FComboBox<Level> level                      = new FComboBox<Level>().withEmptySelectionAllowed(false)
                                                                                   .withWidth("75px");;
        public CheckBox         hasAbilityScoreImprovement = new CheckBox();
        public CheckBox         chooseClassSpecialization  = new CheckBox();
        public CheckBox         favoredEnemy               = new CheckBox();
        public CheckBox         naturalExplorer            = new CheckBox();
        public CheckBox         deity                      = new CheckBox();
        public IntegerField     kiPoints                   = new DSIntegerField().withWidth("50px");
        public TextField        martialArtsDamage          = new TextField();
        public IntegerField     movementBonus              = new DSIntegerField().withWidth("50px");
        public IntegerField     sorceryPoints              = new DSIntegerField().withWidth("50px");
        public IntegerField     ragePoints                 = new DSIntegerField().withWidth("50px");
        public IntegerField     rageDamageBonus            = new DSIntegerField().withWidth("50px");
        public IntegerField     invocationsKnown           = new DSIntegerField().withWidth("50px");
        public TextField        sneakAttackDamage          = new TextField();
    }

    public LevelBonusCollectionField() {
        super(ClassLevelBonus.class, ClassLevelBonusRow.class);
        setPropertyHeader("level", "Niveau");
        setPropertyHeader("hasAbilityScoreImprovement", "Amélioration score/don");
        setPropertyHeader("chooseClassSpecialization", "Choix Specialisation");
        setPropertyHeader("favoredEnemy", "Ennemi favori");
        setPropertyHeader("naturalExplorer", "Explorateur");
        setPropertyHeader("kiPoints", "Points Ki");
        setPropertyHeader("martialArtsDamage", "Dégâts arts martiaux");
        setPropertyHeader("movementBonus", "Mouvement bonus");
        setPropertyHeader("sorceryPoints", "Points magie");
        setPropertyHeader("ragePoints", "Nb rage");
        setPropertyHeader("rageDamageBonus", "Dégât de rage");
        setPropertyHeader("invocationsKnown", "Nb invocation");
        setPropertyHeader("sneakAttackDamage", "Dégâts attaque furtive");
        setPropertyHeader("deity", "Choix dieu");
        gridLayout.setHideEmptyRowsAndColumns(true);
    }

    @Override
    public void onElementAdded() {
        super.onElementAdded();
        refreshLevelBonusVisibility(this.hunter, HUNTER_COLUMNS);
        refreshLevelBonusVisibility(this.deity, DEITY_COLUMNS);
        refreshLevelBonusVisibility(this.martialArts, MARTIAL_ARTS_COLUMNS);
        refreshLevelBonusVisibility(this.sorcery, SORCERY_COLUMNS);
        refreshLevelBonusVisibility(this.rage, RAGE_COLUMNS);
        refreshLevelBonusVisibility(this.invocation, INVOCATION_COLUMNS);
        refreshLevelBonusVisibility(this.sneak, SNEAK_COLUMNS);
    }

    public boolean isMartialArts() {
        return martialArts;
    }

    public void setMartialArts(boolean martialArts) {
        this.martialArts = martialArts;
        refreshLevelBonusVisibility(this.martialArts, MARTIAL_ARTS_COLUMNS);
    }

    public boolean isSorcery() {
        return sorcery;
    }

    public void setSorcery(boolean sorcery) {
        this.sorcery = sorcery;
        refreshLevelBonusVisibility(this.sorcery, SORCERY_COLUMNS);
    }

    public boolean isRage() {
        return rage;
    }

    public void setRage(boolean rage) {
        this.rage = rage;
        refreshLevelBonusVisibility(this.rage, RAGE_COLUMNS);
    }

    public boolean isInvocation() {
        return invocation;
    }

    public void setInvocation(boolean invocation) {
        this.invocation = invocation;
        refreshLevelBonusVisibility(this.invocation, INVOCATION_COLUMNS);
    }

    public boolean isHunter() {
        return hunter;
    }

    public void setHunter(boolean hunter) {
        this.hunter = hunter;
        refreshLevelBonusVisibility(this.hunter, HUNTER_COLUMNS);
    }

    public boolean isSneak() {
        return sneak;
    }

    public void setSneak(boolean sneak) {
        this.sneak = sneak;
        refreshLevelBonusVisibility(this.sneak, SNEAK_COLUMNS);
    }

    public boolean isDeity() {
        return deity;
    }

    public void setDeity(boolean deity) {
        this.deity = deity;
        refreshLevelBonusVisibility(this.deity, DEITY_COLUMNS);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private void refreshLevelBonusVisibility(boolean visible, int... columns) {
        final int nbRows = gridLayout.getRows();
        final int nbColumns = gridLayout.getColumns();
        for (int row = 0; row < nbRows; row++) {
            for (int column = 0; column < nbColumns; column++) {
                Component c = gridLayout.getComponent(column, row);
                if (c != null) {
                    final int visibleColumn = column;
                    boolean match = IntStream.of(columns).anyMatch(x -> x == visibleColumn);
                    if (match) {
                        if (c instanceof HasValue && !visible) {
                            ((HasValue) c).setValue(((HasValue) c).getEmptyValue());
                        }
                        c.setVisible(visible);
                    }
                }
            }
        }
    }

    public void clearForNew() {
        super.clear();
        this.martialArts = false;
        this.hunter = false;
        this.invocation = false;
        this.sneak = false;
        this.sorcery = false;
        this.rage = false;
        this.deity = false;
        onElementAdded();
    }

    public void clearForExisting() {
        setMartialArts(true);
        setRage(true);
        setInvocation(true);
        setHunter(true);
        setSneak(true);
        setSorcery(true);
        setDeity(true);
    }

}
