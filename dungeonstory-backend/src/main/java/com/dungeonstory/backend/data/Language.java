package com.dungeonstory.backend.data;

import static javax.persistence.LockModeType.READ;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Language")
@NamedQueries({

        @NamedQuery(name = Language.LANGUAGES_NOT_IN_RACE, query = "SELECT lang FROM Language lang WHERE lang.id NOT IN (SELECT l.id FROM Race r JOIN r.languages l WHERE r.id = :raceId)", lockMode = READ),

        @NamedQuery(name = Language.UNASSIGNED_LANGUAGES, lockMode = READ, query = "SELECT lang FROM Language lang WHERE lang.id NOT IN (SELECT l.id FROM Character c JOIN c.languages l WHERE c.id = :characterId)") })
public class Language extends AbstractTimestampEntity implements Serializable {

    private static final long serialVersionUID = 5355483080995035841L;

    public static final String LANGUAGES_NOT_IN_RACE = "getLanguagesNotInRace";
    public static final String UNASSIGNED_LANGUAGES  = "getUnassignedLanguages";

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
