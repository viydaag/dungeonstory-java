package com.dungeonstory.backend.data;

import static javax.persistence.LockModeType.READ;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.dungeonstory.backend.repository.DescriptiveEntity;

@Entity
@Table(name = "ClassFeature")
@NamedQueries({
        @NamedQuery(name = ClassFeature.FIND_ALL_CLASS_FEATURES_WITHOUT_PARENT, query = "SELECT e FROM ClassFeature e WHERE e.parent IS NULL ORDER BY e.name ASC", lockMode = READ),
        @NamedQuery(name = ClassFeature.FIND_ALL_CLASS_FEATURE_EXCEPT, query = "SELECT e FROM ClassFeature e WHERE e.id != :featId", lockMode = READ) })
public class ClassFeature extends AbstractTimestampEntity implements DescriptiveEntity, Serializable {

    public static final String FIND_ALL_CLASS_FEATURE_EXCEPT          = "findAllClassFeatureExcept";
    public static final String FIND_ALL_CLASS_FEATURES_WITHOUT_PARENT = "findAllClassFeaturesWithoutParent";

    private static final long serialVersionUID = 8584761126218855898L;
    
    public enum ClassFeatureUsage {
        PASSIVE, ACTION, ACTION_BONUS, REACTION
    }

    public enum RestType {
        SHORT("Court"), LONG("Long");

        private String value;

        private RestType(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }

        @Override
        public String toString() {
            return getValue();
        }
    }

    @NotNull
    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "featUsage", nullable = false)
    private ClassFeatureUsage usage;

    @ManyToOne(optional = true, cascade = { CascadeType.ALL })
    @JoinColumn(name = "parentId")
    private ClassFeature parent;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "parent")
    private List<ClassFeature> children;

    @Column(name = "nbUse")
    Integer nbUse;

    @Enumerated(EnumType.STRING)
    @Column(name = "restType")
    private RestType restType;

    @OneToOne
    @JoinColumn(name = "replaceFeatId")
    private ClassFeature replacement;

    @OneToMany(mappedBy = "feature", cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
    private List<ClassLevelFeature> classLevels;

    public ClassFeature() {
        super();
    }

    public ClassFeature(String name, String description, ClassFeatureUsage type) {
        super();
        this.name = name;
        this.description = description;
        this.usage = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    public ClassFeatureUsage getUsage() {
        return usage;
    }

    public void setUsage(ClassFeatureUsage usage) {
        this.usage = usage;
    }

    public ClassFeature getParent() {
        return parent;
    }

    public void setParent(ClassFeature parent) {
        this.parent = parent;
    }

    public List<ClassFeature> getChildren() {
        return children;
    }

    public void setChildren(List<ClassFeature> children) {
        this.children = children;
    }

    public Integer getNbUse() {
        return nbUse;
    }

    public void setNbUse(Integer nbUse) {
        this.nbUse = nbUse;
    }

    public RestType getRestType() {
        return restType;
    }

    public void setRestType(RestType restType) {
        this.restType = restType;
    }

    public ClassFeature getReplacement() {
        return replacement;
    }

    public void setReplacement(ClassFeature replacement) {
        this.replacement = replacement;
    }

    public List<ClassLevelFeature> getClassLevels() {
        return classLevels;
    }

    public void setClassLevels(List<ClassLevelFeature> classLevels) {
        this.classLevels = classLevels;
    }

    @Override
    public String toString() {
        return getName();
    }

}
