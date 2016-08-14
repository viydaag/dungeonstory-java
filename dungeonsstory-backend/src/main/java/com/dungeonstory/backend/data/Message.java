package com.dungeonstory.backend.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Message")
public class Message extends AbstractTimestampEntity implements Serializable {

    private static final long serialVersionUID = 5957535520695031224L;

    @NotNull
    @Column(name = "title", nullable = false)
    private String title;

    @NotNull
    @Column(name = "text", columnDefinition = "TEXT")
    private String text;

    @ManyToOne
    @JoinColumn(name = "adventureId")
    private Adventure adventure;

    @ManyToOne
    @JoinColumn(name = "characterId")
    private Character character;

    @Column(name = "isXpGiven")
    private boolean isXpGiven;

    public Message() {
        super();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Adventure getAdventure() {
        return adventure;
    }

    public void setAdventure(Adventure adventure) {
        this.adventure = adventure;
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    public boolean isXpGiven() {
        return isXpGiven;
    }

    public void setXpGiven(boolean isXpGiven) {
        this.isXpGiven = isXpGiven;
    }

}
