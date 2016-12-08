package com.dungeonstory.backend.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "ChallengeRating")
public class ChallengeRating extends AbstractTimestampEntity implements Serializable {

    private static final long serialVersionUID = 6341505407552743877L;

    @NotNull
    @Pattern(regexp = "(^\\d)(\\/\\d)?")
    @Column(name = "challengeRating", nullable = false, unique = true)
    private String challengeRating;

    @NotNull
    @Column(name = "experience", nullable = false)
    private long experience;

    @NotNull
    @Column(name = "proficiencyBonus", nullable = false)
    private int proficiencyBonus;

    public ChallengeRating() {
        super();
    }

    public String getChallengeRating() {
        return challengeRating;
    }

    public void setChallengeRating(String challengeRating) {
        this.challengeRating = challengeRating;
    }

    public long getExperience() {
        return experience;
    }

    public void setExperience(long experience) {
        this.experience = experience;
    }

    public int getProficiencyBonus() {
        return proficiencyBonus;
    }

    public void setProficiencyBonus(int proficiencyBonus) {
        this.proficiencyBonus = proficiencyBonus;
    }

}
