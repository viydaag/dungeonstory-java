package com.dungeonstory.backend.service;

import java.util.List;

import com.dungeonstory.backend.data.Spell;

public interface SpellDataService extends DataService<Spell, Long> {

    public List<Spell> findAllSpellsByLevel(int level);

    public List<Spell> findAllUnknownClassSpellsByLevel(int level, Long characterId, Long classId);

    public List<Spell> findAllKnownClassSpellsByLevel(int level, Long characterId, Long classId);

    public List<Spell> findAllSpellsSortedByLevelAndName();

}
