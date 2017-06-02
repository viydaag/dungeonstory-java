package com.dungeonstory.backend.data;

import java.io.Serializable;

public class ClassSpecLevelFeatureId implements Serializable {

    private static final long serialVersionUID = 2271889209733833232L;
    
    private Long classSpec;

    private Long level;

    private Long feature;

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

    public Long getFeature() {
        return feature;
    }

    public void setFeature(Long feature) {
        this.feature = feature;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((classSpec == null) ? 0 : classSpec.hashCode());
        result = prime * result + ((feature == null) ? 0 : feature.hashCode());
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
        if (feature == null) {
            if (other.feature != null) {
                return false;
            }
        } else if (!feature.equals(other.feature)) {
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
