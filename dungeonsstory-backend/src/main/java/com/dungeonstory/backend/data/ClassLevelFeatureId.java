package com.dungeonstory.backend.data;

import java.io.Serializable;

public class ClassLevelFeatureId implements Serializable {

    private static final long serialVersionUID = 6085535553525670809L;

    private Long classe;

    private Long level;

    private Long feat;

    public ClassLevelFeatureId() {

    }

    public Long getClasse() {
        return classe;
    }

    public void setClass(Long classe) {
        this.classe = classe;
    }

    public Long getLevel() {
        return level;
    }

    public void setLevel(Long level) {
        this.level = level;
    }

    public Long getFeat() {
        return feat;
    }

    public void setFeat(Long feat) {
        this.feat = feat;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((classe == null) ? 0 : classe.hashCode());
        result = prime * result + ((feat == null) ? 0 : feat.hashCode());
        result = prime * result + ((level == null) ? 0 : level.hashCode());
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
        if (!(obj instanceof ClassLevelFeatureId)) {
            return false;
        }
        ClassLevelFeatureId other = (ClassLevelFeatureId) obj;
        if (classe == null) {
            if (other.classe != null) {
                return false;
            }
        } else if (!classe.equals(other.classe)) {
            return false;
        }
        if (feat == null) {
            if (other.feat != null) {
                return false;
            }
        } else if (!feat.equals(other.feat)) {
            return false;
        }
        if (level == null) {
            if (other.level != null) {
                return false;
            }
        } else if (!level.equals(other.level)) {
            return false;
        }
        return true;
    }

}
