package com.dungeonstory.backend.data;

import java.io.Serializable;

public class DivineDomainSpellId implements Serializable {

    private static final long serialVersionUID = 8142676466101919471L;

    private Long domain;
    
    private Long spell;
    
    public DivineDomainSpellId() {
        super();
    }

    public Long getDomain() {
        return domain;
    }

    public void setDomain(Long domain) {
        this.domain = domain;
    }

    public Long getSpell() {
        return spell;
    }

    public void setSpell(Long spell) {
        this.spell = spell;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((domain == null) ? 0 : domain.hashCode());
        result = prime * result + ((spell == null) ? 0 : spell.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof DivineDomainSpellId)) {
            return false;
        }
        DivineDomainSpellId other = (DivineDomainSpellId) obj;
        if (domain == null) {
            if (other.domain != null) {
                return false;
            }
        } else if (!domain.equals(other.domain)) {
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
