package com.dungeonstory.backend.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@IdClass(DivineDomainSpellId.class)
@Table(name = "DivineDomainSpell")
public class DivineDomainSpell implements Serializable {

    private static final long serialVersionUID = 5721754394708129623L;

    @Id
    @ManyToOne
    @JoinColumn(name = "domainId")
    private DivineDomain domain;
    
    @Id
    @ManyToOne
    @JoinColumn(name = "spellId")
    private Spell spell;
    
    @Column(name = "level")
    private int level;
    
    public DivineDomainSpell() {
        super();
    }

    public DivineDomain getDomain() {
        return domain;
    }

    public void setDomain(DivineDomain domain) {
        this.domain = domain;
    }

    public Spell getSpell() {
        return spell;
    }

    public void setSpell(Spell spell) {
        this.spell = spell;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

}
