package com.dungeonstory.backend.repository;

import org.junit.BeforeClass;
import org.junit.Test;

import com.dungeonstory.backend.TestWithBackend;
import com.dungeonstory.backend.repository.impl.SpellRepository;

public class SpellRepositoryTest extends TestWithBackend {

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
        repo.findAllClassSpellsByLevel(0, 1L);
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
