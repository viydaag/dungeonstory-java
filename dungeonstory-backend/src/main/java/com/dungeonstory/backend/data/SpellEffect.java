package com.dungeonstory.backend.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "SpellEffect")
public class SpellEffect extends AbstractTimestampEntity implements Serializable {

    private static final long serialVersionUID = -1874521533507413740L;
    
    public enum EffectType {
        DAMAGE,
        CURE,
        ADD_CONDITION,
        REMOVE_CONDITION,
        SUMMON,
        PROTECTION,
        RESISTANCE,
        OTHER
    }
    
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "effectType", nullable = false)
    private EffectType effectType;
    
    @Pattern(regexp = "^$|(\\d+d\\d+([\\+\\-]\\d+)*)") //NdN(+/-)N
    @Column(name = "damage")
    private String damage;                  //used in case of damage or cure
    
    @ManyToOne
    @JoinColumn(name = "damageTypeId")
    private DamageType damageType;          //used in case of damage or resistance
    
    @Column(name = "armorClass")
    private Integer armorClass;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "conditionName")
    private Condition condition;
    
    @ManyToOne
    @JoinColumn(name = "spellId", nullable = false)
    private Spell spell;

    public SpellEffect() {
        super();
    }
    
    public EffectType getEffectType() {
        return effectType;
    }

    public void setEffectType(EffectType effectType) {
        this.effectType = effectType;
    }

    public String getDamage() {
        return damage;
    }

    public void setDamage(String damage) {
        this.damage = damage;
    }

    public DamageType getDamageType() {
        return damageType;
    }

    public void setDamageType(DamageType damageType) {
        this.damageType = damageType;
    }

    public Integer getArmorClass() {
        return armorClass;
    }

    public void setArmorClass(Integer armorClass) {
        this.armorClass = armorClass;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public Spell getSpell() {
        return spell;
    }

    public void setSpell(Spell spell) {
        this.spell = spell;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((armorClass == null) ? 0 : armorClass.hashCode());
        result = prime * result + ((condition == null) ? 0 : condition.hashCode());
        result = prime * result + ((damage == null) ? 0 : damage.hashCode());
        result = prime * result + ((damageType == null) ? 0 : damageType.hashCode());
        result = prime * result + ((effectType == null) ? 0 : effectType.hashCode());
        result = prime * result + ((spell == null) ? 0 : spell.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (!(obj instanceof SpellEffect)) {
            return false;
        }
        SpellEffect other = (SpellEffect) obj;
        if (armorClass == null) {
            if (other.armorClass != null) {
                return false;
            }
        } else if (!armorClass.equals(other.armorClass)) {
            return false;
        }
        if (condition != other.condition) {
            return false;
        }
        if (damage == null) {
            if (other.damage != null) {
                return false;
            }
        } else if (!damage.equals(other.damage)) {
            return false;
        }
        if (damageType == null) {
            if (other.damageType != null) {
                return false;
            }
        } else if (!damageType.equals(other.damageType)) {
            return false;
        }
        if (effectType != other.effectType) {
            return false;
        }
        if (spell == null) {
            if (other.spell != null) {
                return false;
            }
        } else if (!spell.equals(other.spell)) {
            return false;
        }
        return true;
    }

}
