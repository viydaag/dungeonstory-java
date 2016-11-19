package com.dungeonstory.backend.repository;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.dungeonstory.backend.data.Spell;
import com.dungeonstory.backend.repository.impl.SpellRepository;

public class SpellRepositoryTest {

    static SpellRepository repo;

    @BeforeClass
    public static void init() {
        repo = new SpellRepository();

    }

    @Test
    public void findAllSpellsByLevelTest() {
        repo.findAllSpellsByLevel(0);
    }

    @Test
    public void findAllClassSpellsByLevelTest() {
        List<Spell> spells = repo.findAllClassSpellsByLevel(0, 1L);
    }

    @Test
    public void findAllUnknownClassSpellsByLevelTest() {
        repo.findAllUnknownClassSpellsByLevel(0, 1L, 1L);
    }

    @Test
    public void findAllKnownClassSpellsByLevelTest() {
        repo.findAllKnownClassSpellsByLevel(0, 1L, 1L);
    }

    @Test
    public void findAllSpellsSortedByLevelAndName() {
        repo.findAllSpellsSortedByLevelAndName();
    }

}
