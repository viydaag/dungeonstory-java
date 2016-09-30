package com.dungeonstory.util.field;

import java.util.stream.IntStream;

import org.vaadin.viritin.fields.ElementCollectionField;
import org.vaadin.viritin.fields.IntegerField;
import org.vaadin.viritin.fields.MTextField;
import org.vaadin.viritin.fields.TypedSelect;

import com.dungeonstory.backend.data.ClassLevelBonus;
import com.dungeonstory.backend.data.Level;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.TextField;

public class LevelBonusCollectionField extends ElementCollectionField<ClassLevelBonus>{

    private static final long serialVersionUID = 2580712139242043824L;

    private boolean martialArts = false;
    private boolean sorcery     = false;
    private boolean rage        = false;
    private boolean invocation  = false;
    private boolean hunter      = false;
    private boolean sneak       = false;

    private final int[] HUNTER_COLUMNS       = { 3, 4 };
    private final int[] MARTIAL_ARTS_COLUMNS = { 5, 6, 7 };
    private final int[] SORCERY_COLUMNS      = { 8 };
    private final int[] RAGE_COLUMNS         = { 9, 10 };
    private final int[] INVOCATION_COLUMNS   = { 11 };
    private final int[] SNEAK_COLUMNS        = { 12 };

    public static class ClassLevelBonusRow {
        public TypedSelect<Level> level                      = new TypedSelect<Level>();
        public CheckBox           hasAbilityScoreImprovement = new CheckBox();
        public CheckBox           chooseClassSpecialization  = new CheckBox();
        public CheckBox           favoredEnemy               = new CheckBox();
        public CheckBox           naturalExplorer            = new CheckBox();
        public IntegerField       kiPoints                   = new IntegerField().withWidth("50px");
        public MTextField         martialArtsDamage          = new MTextField();
        public IntegerField       movementBonus              = new IntegerField().withWidth("50px");
        public IntegerField       sorceryPoints              = new IntegerField().withWidth("50px");
        public IntegerField       ragePoints                 = new IntegerField().withWidth("50px");
        public IntegerField       rageDamageBonus            = new IntegerField().withWidth("50px");
        public IntegerField       invocationsKnown           = new IntegerField().withWidth("50px");
        public MTextField         sneakAttackDamage          = new MTextField();
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
        getLayout().setHideEmptyRowsAndColumns(true);
    }

    @Override
    public void onElementAdded() {
        super.onElementAdded();
        refreshLevelBonusVisibility(this.hunter, HUNTER_COLUMNS);
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

    private void refreshLevelBonusVisibility(boolean visible, int... columns) {
        final int nbRows = getLayout().getRows();
        final int nbColumns = getLayout().getColumns();
        for (int row = 0; row < nbRows; row++) {
            for (int column = 0; column < nbColumns; column++) {
                Component c = getLayout().getComponent(column, row);
                if (c != null) {
                    final int visibleColumn = column;
                    boolean match = IntStream.of(columns).anyMatch(x -> x == visibleColumn);
                    if (match) {
                        if (c instanceof IntegerField && !visible) {
                            ((IntegerField) c).setValue(null);
                        }
                        if (c instanceof TextField && !visible) {
                            ((TextField) c).setValue(null);
                        }
                        if (c instanceof CheckBox && !visible) {
                            ((CheckBox) c).setValue(null);
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
        onElementAdded();
    }

    public void clearForExisting() {
        setMartialArts(true);
        setRage(true);
        setInvocation(true);
        setHunter(true);
        setSneak(true);
        setSorcery(true);
    }

}
