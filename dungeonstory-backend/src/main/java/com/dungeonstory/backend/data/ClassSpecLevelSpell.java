package com.dungeonstory.backend.data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@IdClass(ClassSpecLevelSpellId.class)
@Table(name = "ClassSpecLevelSpell")
public class ClassSpecLevelSpell {
    
    @Id
    @ManyToOne
    @JoinColumn(name = "classSpecId")
    private ClassSpecialization classSpec;

    @Id
    @ManyToOne
    @JoinColumn(name = "levelId")
    private Level level;

    @Id
    @ManyToOne
    @JoinColumn(name = "spellId")
    private Spell spell;
    
    public ClassSpecLevelSpell() {
        super();
    }

    public ClassSpecialization getClassSpec() {
        return classSpec;
    }

    public void setClassSpec(ClassSpecialization classSpec) {
        this.classSpec = classSpec;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public Spell getSpell() {
        return spell;
    }

    public void setSpell(Spell spell) {
        this.spell = spell;
    }

        
}
