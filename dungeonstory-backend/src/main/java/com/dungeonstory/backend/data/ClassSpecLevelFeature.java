package com.dungeonstory.backend.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

@Entity
@IdClass(ClassSpecLevelFeatureId.class)
@Table(name = "ClassSpecLevelFeature")
public class ClassSpecLevelFeature {
    
    @Id
    @ManyToOne
    @JoinColumn(name = "classSpecId", nullable = false)
    private ClassSpecialization classSpec;

    @Id
    @NotNull
    @ManyToOne
    @JoinColumn(name = "levelId", nullable = false)
    private Level level;

    @Id
    @NotNull
    @ManyToOne
    @JoinColumn(name = "featureId", nullable = false)
    private ClassFeature feature;
    
    @Digits(integer = 1, fraction = 0)
    @Column(name = "nbToChoose")
    private int nbToChoose = 1;

    public ClassSpecLevelFeature() {
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

    public ClassFeature getFeature() {
        return feature;
    }

    public void setFeature(ClassFeature feature) {
        this.feature = feature;
    }

    public int getNbToChoose() {
        return nbToChoose;
    }

    public void setNbToChoose(int nbToChoose) {
        this.nbToChoose = nbToChoose;
    }

    @Override
    public String toString() {
        return feature.getName() + " (niv. " + level.getId() + ")";
    }
        
}
