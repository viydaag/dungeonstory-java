package com.dungeonstory.backend.data;

import java.io.Serializable;

public class ClassSpecLevelFeatureId implements Serializable {

    private static final long serialVersionUID = 2271889209733833232L;
    
    private Long classSpec;

    private Long level;

    private Long feat;

    public ClassSpecLevelFeatureId() {

    }

    public Long getClassSpec() {
        return classSpec;
    }

    public void setClassSpec(Long classSpec) {
        this.classSpec = classSpec;
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
        result = prime * result + ((classSpec == null) ? 0 : classSpec.hashCode());
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
        if (!(obj instanceof ClassSpecLevelFeatureId)) {
            return false;
        }
        ClassSpecLevelFeatureId other = (ClassSpecLevelFeatureId) obj;
        if (classSpec == null) {
            if (other.classSpec != null) {
                return false;
            }
        } else if (!classSpec.equals(other.classSpec)) {
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
