package com.dungeonstory.backend.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Language")
public class Language extends AbstractTimestampEntity implements Serializable {

    private static final long serialVersionUID = 5355483080995035841L;
    
    @NotNull
    @Column(name = "name", nullable = false, unique = true)
    private String name;
    
    @Column(name = "script")
    private String script;

    public Language() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }
    
    @Override
    public String toString() {
        return getName();
    }

}
