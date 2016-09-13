package com.dungeonstory.backend.data;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@IdClass(ClassLevelFeatureId.class)
@Table(name = "ClassLevelBonusFeat")
public class ClassLevelFeature implements Serializable {

    private static final long serialVersionUID = 5198775399118036404L;

    @Id
    @ManyToOne
    @JoinColumn(name = "classId")
    private DSClass classe;

    @Id
    @ManyToOne
    @JoinColumn(name = "levelId")
    private Level level;

    @Id
    @ManyToOne
    @JoinColumn(name = "featId")
    private Feat feat;

    public ClassLevelFeature() {
        super();
    }

    public ClassLevelFeature(DSClass classe, Level level, Feat feat) {
        this();
        this.classe = classe;
        this.level = level;
        this.feat = feat;
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

    public Feat getFeat() {
        return feat;
    }

    public void setFeat(Feat feat) {
        this.feat = feat;
    }

}
