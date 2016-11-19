package com.dungeonstory.backend.data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@IdClass(ClassSpecLevelFeatureId.class)
@Table(name = "ClassSpecLevelFeature")
public class ClassSpecLevelFeature {
    
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
    @JoinColumn(name = "featId")
    private Feat feat;
    
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

    public Feat getFeat() {
        return feat;
    }

    public void setFeat(Feat feat) {
        this.feat = feat;
    }
        
}