package com.dungeonstory.backend.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.dungeonstory.backend.data.enums.Background;

@Entity
@Table(name = "CharacterBackground")
public class CharacterBackground implements Serializable {

    private static final long serialVersionUID = -8743146924123153182L;

    @Id
    @OneToOne
    @JoinColumn(name = "characterId")
    private Character character;

    @NotNull
    @Column(name = "background", nullable = false)
    @Enumerated(EnumType.STRING)
    private Background background;
    
    @Column(name = "look", columnDefinition = "TEXT")
    private String look;

    @Column(name = "traits", columnDefinition = "TEXT")
    private String traits;

    @Column(name = "ideals", columnDefinition = "TEXT")
    private String ideals;

    @Column(name = "purposes", columnDefinition = "TEXT")
    private String purposes;

    @Column(name = "flaws", columnDefinition = "TEXT")
    private String flaws;

    public CharacterBackground() {
        super();
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    public Background getBackground() {
        return background;
    }

    public void setBackground(Background background) {
        this.background = background;
    }

    public String getLook() {
        return look;
    }

    public void setLook(String look) {
        this.look = look;
    }

    public String getTraits() {
        return traits;
    }

    public void setTraits(String traits) {
        this.traits = traits;
    }

    public String getIdeals() {
        return ideals;
    }

    public void setIdeals(String ideals) {
        this.ideals = ideals;
    }

    public String getPurposes() {
        return purposes;
    }

    public void setPurposes(String purposes) {
        this.purposes = purposes;
    }

    public String getFlaws() {
        return flaws;
    }

    public void setFlaws(String flaws) {
        this.flaws = flaws;
    }
    
}
