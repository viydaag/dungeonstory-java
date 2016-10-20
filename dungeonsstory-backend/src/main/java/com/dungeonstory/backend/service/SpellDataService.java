package com.dungeonstory.backend.service;

import java.util.List;

import com.dungeonstory.backend.data.Spell;

public interface SpellDataService extends DataService<Spell, Long> {

    public List<Spell> findAllSpellsByLevel(int level);

    public List<Spell> findAllUnknownSpellsByLevel(int level, Long characterId);

    public List<Spell> findAllKnownSpellsByLevel(int level, Long characterId);

    public List<Spell> findAllSpellsSortedByLevelAndName();

}
