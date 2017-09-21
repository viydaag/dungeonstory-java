package com.dungeonstory.backend.data;

import static javax.persistence.LockModeType.READ;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Language")
@NamedQueries({
        @NamedQuery(name = Language.LANGUAGES_NOT_IN_RACE, lockMode = READ, query = "SELECT lang FROM Language lang WHERE lang.id NOT IN (SELECT l.id FROM Race r JOIN r.languages l WHERE r.id = :raceId) AND lang.playable = 1"),
        @NamedQuery(name = Language.UNASSIGNED_LANGUAGES, lockMode = READ, query = "SELECT lang FROM Language lang WHERE lang.id NOT IN (SELECT l.id FROM Character c JOIN c.languages l WHERE c.id = :characterId) AND lang.playable = 1") })
public class Language extends AbstractTimestampEntity {

    private static final long serialVersionUID = 5355483080995035841L;

    public static final String LANGUAGES_NOT_IN_RACE = "getLanguagesNotInRace";
    public static final String UNASSIGNED_LANGUAGES  = "getUnassignedLanguages";

    @NotNull
    @Size(min = 1)
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "script")
    private String script;

    @Column(name = "playable")
    private boolean playable;

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

    public boolean getPlayable() {
        return playable;
    }

    public void setPlayable(boolean playable) {
        this.playable = playable;
    }

    @Override
    public String toString() {
        return getName();
    }

}
