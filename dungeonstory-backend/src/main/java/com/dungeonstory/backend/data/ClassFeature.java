package com.dungeonstory.backend.data;

import static javax.persistence.LockModeType.READ;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.eclipse.persistence.annotations.BatchFetch;
import org.eclipse.persistence.annotations.BatchFetchType;
import org.eclipse.persistence.annotations.JoinFetch;
import org.eclipse.persistence.annotations.JoinFetchType;

import com.dungeonstory.backend.repository.DescriptiveEntity;

@Entity
@Table(name = "ClassFeature")
@NamedQueries({
        @NamedQuery(name = ClassFeature.FIND_ALL_CLASS_FEATURES_WITHOUT_PARENT, query = "SELECT e FROM ClassFeature e WHERE e.parent IS NULL ORDER BY e.name ASC", lockMode = READ),
        @NamedQuery(name = ClassFeature.FIND_ALL_CLASS_FEATURE_EXCEPT, query = "SELECT e FROM ClassFeature e WHERE e.id != :featId", lockMode = READ) })
public class ClassFeature extends AbstractTimestampEntity implements DescriptiveEntity {

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
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "featUsage", nullable = false)
    private ClassFeatureUsage usage;

    @ManyToOne(optional = true, cascade = { CascadeType.ALL })
    @JoinFetch(JoinFetchType.OUTER)
    @JoinColumn(name = "parentId")
    private ClassFeature parent;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parent")
    @BatchFetch(value = BatchFetchType.JOIN)
    private List<ClassFeature> children;

    @Column(name = "nbUse")
    Integer nbUse;

    @Enumerated(EnumType.STRING)
    @Column(name = "restType")
    private RestType restType;

    @Column(name = "pointCost")
    Integer pointCost;

    @OneToOne
    @JoinFetch(JoinFetchType.OUTER)
    @JoinColumn(name = "replaceFeatId")
    private ClassFeature replacement;

    @ManyToOne
    @JoinFetch(JoinFetchType.OUTER)
    @JoinColumn(name = "requiredLevelId")
    // When the ClassFeature is a child (a choice), sometimes a class level is required to choose it.
    private Level requiredLevel;

    @OneToMany(mappedBy = "feature", cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
    @BatchFetch(value = BatchFetchType.JOIN)
    private List<ClassLevelFeature> classLevels;

    @OneToMany(mappedBy = "feature", cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
    @BatchFetch(value = BatchFetchType.JOIN)
    private List<ClassSpecLevelFeature> classSpecLevels;

    public ClassFeature() {
        super();
        this.children = new ArrayList<>();
        this.classLevels = new ArrayList<>();
        this.classSpecLevels = new ArrayList<>();
    }

    public ClassFeature(String name, String description, ClassFeatureUsage type) {
        this();
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

    public boolean isAParent() {
        return children != null && !children.isEmpty();
    }

    public boolean isAChild() {
        return parent != null;
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

    public Integer getPointCost() {
        return pointCost;
    }

    public void setPointCost(Integer pointCost) {
        this.pointCost = pointCost;
    }

    public ClassFeature getReplacement() {
        return replacement;
    }

    public void setReplacement(ClassFeature replacement) {
        this.replacement = replacement;
    }

    public Level getRequiredLevel() {
        return requiredLevel;
    }

    public void setRequiredLevel(Level requiredLevel) {
        this.requiredLevel = requiredLevel;
    }

    public List<ClassLevelFeature> getClassLevels() {
        return classLevels;
    }

    public void setClassLevels(List<ClassLevelFeature> classLevels) {
        this.classLevels = classLevels;
    }

    public List<ClassSpecLevelFeature> getClassSpecLevels() {
        return classSpecLevels;
    }

    public void setClassSpecLevels(List<ClassSpecLevelFeature> classSpecLevels) {
        this.classSpecLevels = classSpecLevels;
    }

    @Override
    public String toString() {
        return getName();
    }

}
