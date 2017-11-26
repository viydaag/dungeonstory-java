package com.dungeonstory.backend.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import org.eclipse.persistence.annotations.Customizer;
import org.eclipse.persistence.annotations.JoinFetch;
import org.eclipse.persistence.annotations.JoinFetchType;

import com.dungeonstory.backend.data.util.OrderCustomizer;

@Entity
@IdClass(ClassLevelFeatureId.class)
@Table(name = "ClassLevelFeature")
@Customizer(OrderCustomizer.class)
public class ClassLevelFeature implements Serializable {

    private static final long serialVersionUID = 5198775399118036404L;

    @Id
    @NotNull
    @ManyToOne
    @JoinFetch(JoinFetchType.INNER)
    @JoinColumn(name = "levelId", nullable = false)
    private Level level;

    @Id
    @ManyToOne
    @JoinFetch(JoinFetchType.INNER)
    @JoinColumn(name = "classId", nullable = false)
    private DSClass classe;

    @Id
    @NotNull
    @ManyToOne
    @JoinFetch(JoinFetchType.INNER)
    @JoinColumn(name = "featureId", nullable = false)
    private ClassFeature feature;

    @Digits(integer = 1, fraction = 0)
    @Column(name = "nbToChoose")
    private Integer nbToChoose = null;

    public ClassLevelFeature() {
        super();
    }

    public ClassLevelFeature(DSClass classe, Level level, ClassFeature feature) {
        this();
        this.classe = classe;
        this.level = level;
        this.feature = feature;
        this.nbToChoose = null;
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

    public Integer getNbToChoose() {
        return nbToChoose;
    }

    public void setNbToChoose(Integer nbToChoose) {
        this.nbToChoose = nbToChoose;
    }

}
