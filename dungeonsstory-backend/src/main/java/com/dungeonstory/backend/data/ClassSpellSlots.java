package com.dungeonstory.backend.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;

@Entity
@IdClass(ClassSpellSlotsId.class)
@Table(name = "ClassSpellSlots")
public class ClassSpellSlots {

    @Id
    @ManyToOne
    @JoinColumn(name = "classId")
    private DSClass classe;

    @Id
    @ManyToOne
    @JoinColumn(name = "levelId")
    private Level level;

    @Min(value = 0)
    @Column(name = "cantripsKnown")
    private Integer cantripsKnown;

    @Min(value = 0)
    @Column(name = "spellsKnown")
    private Integer spellsKnown;

    @Min(value = 0)
    @Column(name = "spellSlots1")
    private Integer spellSlots1;

    @Min(value = 0)
    @Column(name = "spellSlots2")
    private Integer spellSlots2;

    @Min(value = 0)
    @Column(name = "spellSlots3")
    private Integer spellSlots3;

    @Min(value = 0)
    @Column(name = "spellSlots4")
    private Integer spellSlots4;

    @Min(value = 0)
    @Column(name = "spellSlots5")
    private Integer spellSlots5;

    @Min(value = 0)
    @Column(name = "spellSlots6")
    private Integer spellSlots6;

    @Min(value = 0)
    @Column(name = "spellSlots7")
    private Integer spellSlots7;

    @Min(value = 0)
    @Column(name = "spellSlots8")
    private Integer spellSlots8;

    @Min(value = 0)
    @Column(name = "spellSlots9")
    private Integer spellSlots9;

    public ClassSpellSlots() {

    }

    public DSClass getClasse() {
        return classe;
    }

    public void setClasse(DSClass classe) {
        this.classe = classe;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public Integer getCantripsKnown() {
        return cantripsKnown;
    }

    public void setCantripsKnown(Integer cantripsKnown) {
        this.cantripsKnown = cantripsKnown;
    }

    public Integer getSpellsKnown() {
        return spellsKnown;
    }

    public void setSpellsKnown(Integer spellsKnown) {
        this.spellsKnown = spellsKnown;
    }

    public Integer getSpellSlots1() {
        return spellSlots1;
    }

    public void setSpellSlots1(Integer spellSlots1) {
        this.spellSlots1 = spellSlots1;
    }

    public Integer getSpellSlots2() {
        return spellSlots2;
    }

    public void setSpellSlots2(Integer spellSlots2) {
        this.spellSlots2 = spellSlots2;
    }

    public Integer getSpellSlots3() {
        return spellSlots3;
    }

    public void setSpellSlots3(Integer spellSlots3) {
        this.spellSlots3 = spellSlots3;
    }

    public Integer getSpellSlots4() {
        return spellSlots4;
    }

    public void setSpellSlots4(Integer spellSlots4) {
        this.spellSlots4 = spellSlots4;
    }

    public Integer getSpellSlots5() {
        return spellSlots5;
    }

    public void setSpellSlots5(Integer spellSlots5) {
        this.spellSlots5 = spellSlots5;
    }

    public Integer getSpellSlots6() {
        return spellSlots6;
    }

    public void setSpellSlots6(Integer spellSlots6) {
        this.spellSlots6 = spellSlots6;
    }

    public Integer getSpellSlots7() {
        return spellSlots7;
    }

    public void setSpellSlots7(Integer spellSlots7) {
        this.spellSlots7 = spellSlots7;
    }

    public Integer getSpellSlots8() {
        return spellSlots8;
    }

    public void setSpellSlots8(Integer spellSlots8) {
        this.spellSlots8 = spellSlots8;
    }

    public Integer getSpellSlots9() {
        return spellSlots9;
    }

    public void setSpellSlots9(Integer spellSlots9) {
        this.spellSlots9 = spellSlots9;
    }

}
