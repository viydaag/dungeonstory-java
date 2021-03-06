package com.dungeonstory.backend.data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.eclipse.persistence.annotations.PrivateOwned;

@Entity
@Table(name = "Adventure")
public class Adventure extends AbstractTimestampEntity {

    private static final long serialVersionUID = 2976001308437054432L;

    public enum AdventureStatus {
        OPENED, STARTED, CLOSED, CANCELLED
    }

    @NotNull
    @Column(name = "name", nullable = false)
    private String          name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String          description;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "creatorId")
    private User            creator;

    @NotNull
    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private AdventureStatus status = AdventureStatus.OPENED;

    @OneToMany(mappedBy = "adventure", cascade = CascadeType.PERSIST)
    @PrivateOwned // means that a message will be deleted if not attached to an adventure
    private List<Message>   messages;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "challengeRating")
    private Level           challengeRating;

    public Adventure() {
        super();
        messages = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public AdventureStatus getStatus() {
        return status;
    }

    public void setStatus(AdventureStatus status) {
        this.status = status;
    }

    public Level getChallengeRating() {
        return challengeRating;
    }

    public void setChallengeRating(Level challengeRating) {
        this.challengeRating = challengeRating;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public void addMessage(Message message) {
        this.messages.add(message);
    }

    public void removeMessage(Message message) {
        this.messages.remove(message);
    }

    public boolean isCancelledOrClosed() {
        return getStatus() == AdventureStatus.CANCELLED || getStatus() == AdventureStatus.CLOSED;
    }

    public boolean isOpened() {
        return getStatus() == AdventureStatus.OPENED;
    }

}
