package com.dungeonstory.backend.data;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.eclipse.persistence.annotations.PrivateOwned;

@Entity
@Table(name = "ClassSpecialization")
public class ClassSpecialization extends AbstractTimestampEntity implements Serializable {

    private static final long serialVersionUID = -2465166155999242520L;
    
    @NotNull
    @Column(name = "name", unique = true, nullable = false)
    private String name;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name = "classId", nullable = false)
    private DSClass parentClass;
    
    @OneToMany(mappedBy = "classSpec", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @PrivateOwned
    private List<ClassSpecLevelFeature> classSpecFeatures;
    
    public ClassSpecialization() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DSClass getParentClass() {
        return parentClass;
    }

    public void setParentClass(DSClass parentClass) {
        this.parentClass = parentClass;
    }

    public List<ClassSpecLevelFeature> getClassSpecFeatures() {
        return classSpecFeatures;
    }

    public void setClassSpecFeatures(List<ClassSpecLevelFeature> classSpecFeatures) {
        this.classSpecFeatures = classSpecFeatures;
    }

}
