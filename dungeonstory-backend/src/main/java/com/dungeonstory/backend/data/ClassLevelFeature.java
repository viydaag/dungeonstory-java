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
@IdClass(ClassLevelFeatureId.class)
@Table(name = "ClassLevelFeature")
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
    @JoinColumn(name = "featureId")
    private ClassFeature feature;

    @Column(name = "nbToChoose")
    private int nbToChoose = 1;

    public ClassLevelFeature() {
        super();
    }

    public ClassLevelFeature(DSClass classe, Level level, ClassFeature feature) {
        this();
        this.classe = classe;
        this.level = level;
        this.feature = feature;
        this.nbToChoose = 1;
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

}
