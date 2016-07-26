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
@IdClass(ClassLevelBonusFeatId.class)
@Table(name = "ClassLevelBonusFeat")
public class ClassLevelBonusFeat implements Serializable {

    private static final long serialVersionUID = 5198775399118036404L;

    @Id
    @Column(name = "classId")
    private Long classId;

    @Id
    @Column(name = "levelId")
    private Long levelId;

    @Id
    @Column(name = "featId")
    private Long featId;

    @ManyToOne
    @JoinColumn(name = "classId", updatable = false, insertable = false)
    private DSClass classe;

    @ManyToOne
    @JoinColumn(name = "levelId", updatable = false, insertable = false)
    private Level level;

    @ManyToOne
    @JoinColumn(name = "featId", updatable = false, insertable = false)
    private Feat feat;

    public ClassLevelBonusFeat() {
        // TODO Auto-generated constructor stub
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public Long getLevelId() {
        return levelId;
    }

    public void setLevelId(Long levelId) {
        this.levelId = levelId;
    }

    public Long getFeatId() {
        return featId;
    }

    public void setFeatId(Long featId) {
        this.featId = featId;
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
