package com.dungeonstory.backend.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Message")
public class Message extends AbstractTimestampEntity {

    private static final long serialVersionUID = 5957535520695031224L;

    @Column(name = "title")
    private String title;

    @NotNull
    @Size(min = 1)
    @Column(name = "text", columnDefinition = "TEXT")
    private String text;

    @ManyToOne
    @JoinColumn(name = "adventureId")
    private Adventure adventure;
    
    @ManyToOne
    @JoinColumn(name = "userId")
    private User creator;

    @ManyToOne
    @JoinColumn(name = "characterId")
    private Character character;

    @Column(name = "isXpGiven")
    private boolean isXpGiven;

    public Message() {
        super();
    }
    
    public Message(String text) {
        this();
        setText(text);
    }
    
    public Message(String text, User creator) {
        this(text);
        setCreator(creator);
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

    public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public boolean isXpGiven() {
        return isXpGiven;
    }

    public void setXpGiven(boolean isXpGiven) {
        this.isXpGiven = isXpGiven;
    }

}
