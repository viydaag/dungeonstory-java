package com.dungeonstory.backend.data;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@DiscriminatorValue("TOOL")
@PrimaryKeyJoinColumn(name = "id", referencedColumnName = "id")
@Table(name = "Tool")
public class Tool extends Equipment {

    private static final long serialVersionUID = 4291593041863782823L;

    public enum ToolType {
        ARTISAN("Artisan"),
        DISGUISE("Déguisement"),
        FORGERY("Contre-façon"),
        GAMING("Jeu"),
        HERBALISM("Herborisme"),
        MUSICAL("Musique"),
        NAVIGATOR("Navigation"),
        POISONER("Poison"),
        THIEVE("Voleur"),
        VEHICLES("Véhicule");
        
        private String name;
        
        private ToolType(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
        
        @Override
        public String toString() {
            return getName();
        }
    }

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "toolType", nullable = false)
    private ToolType toolType;

    public Tool() {
        super();
    }

    public ToolType getToolType() {
        return toolType;
    }

    public void setToolType(ToolType toolType) {
        this.toolType = toolType;
    }

    @Override
    public String toString() {
        return getName();
    }

}
