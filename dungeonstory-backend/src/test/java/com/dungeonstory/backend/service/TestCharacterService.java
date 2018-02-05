package com.dungeonstory.backend.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.List;
import java.util.Random;

import org.junit.Test;

import com.dungeonstory.backend.data.Character;
import com.dungeonstory.backend.data.Character.Gender;
import com.dungeonstory.backend.data.CharacterBackground;
import com.dungeonstory.backend.data.CharacterClass;
import com.dungeonstory.backend.data.CharacterEquipment;
import com.dungeonstory.backend.data.DSClass;
import com.dungeonstory.backend.data.Feat;
import com.dungeonstory.backend.data.Feats;
import com.dungeonstory.backend.data.util.ClassUtil;
import com.dungeonstory.backend.service.impl.AlignmentService;
import com.dungeonstory.backend.service.impl.BackgroundService;
import com.dungeonstory.backend.service.impl.CharacterService;
import com.dungeonstory.backend.service.impl.ClassService;
import com.dungeonstory.backend.service.impl.EquipmentService;
import com.dungeonstory.backend.service.impl.FeatService;
import com.dungeonstory.backend.service.impl.RaceService;
import com.dungeonstory.backend.service.impl.RegionService;

public class TestCharacterService {

    @Test
    public void testCharacterService() throws Exception {
        CharacterService service = CharacterService.getInstance();

        Character c = createDummyCharacter(service);

        //        CharacterBackground cBackground = new CharacterBackground();
        //        cBackground.setBackground(BackgroundService.getInstance().read(1L));
        //        c.setBackground(cBackground);

        service.saveOrUpdate(c);

        String name = c.getName();
        List<Character> list = service.findAllBy("name", name);
        assertEquals(1, list.size());

        Character saved = list.get(0);
        assertNotNull(saved.getId());
        assertEquals(name, saved.getName());

        service.delete(saved);

    }

    @Test
    public void testCharacterService2() throws Exception {
        CharacterService service = CharacterService.getInstance();

        Character c = createDummyCharacter(service);

        CharacterBackground cBackground = new CharacterBackground();
        cBackground.setBackground(BackgroundService.getInstance().read(1L));
        cBackground.setCharacter(c);
        c.setBackground(cBackground);

        CharacterEquipment equip = new CharacterEquipment();
        equip.setCharacter(c);
        equip.setEquipment(EquipmentService.getInstance().read(2L));
        //        equip.setQuantity(1);
        equip.setSellableValue(2);
        c.getEquipment().add(equip);

        CharacterClass classe = new CharacterClass();
        classe.setCharacter(c);
        DSClass theClass = ClassService.getInstance().read(2L);
        classe.setClasse(theClass);
        classe.setClassLevel(1);
        c.getClasses().add(classe);

        service.saveOrUpdate(c);

        List<Character> list = service.findAllBy("name", c.getName());
        assertEquals(1, list.size());

        Character saved = list.get(0);
        assertNotNull(saved.getId());
        assertEquals(c.getName(), saved.getName());

        for (CharacterEquipment e : c.getEquipment()) {
            assertNotNull(e);
        }

        //        for (CharacterClass e : c.getClasses()) {
        //            assertNotNull(e);
        //        }
        CharacterClass assignedClass = ClassUtil.getCharacterClass(c, theClass);
        assertNotNull(assignedClass);

        service.delete(saved);

    }

    @Test
    public void testHasFeat() {
        CharacterService service = CharacterService.getInstance();

        Character c = createDummyCharacter(service);

        Feat feat1 = FeatService.getInstance().read(Feats.ATHLETE.getId());
        HashSet<Feat> feats = new HashSet<>();
        feats.add(feat1);
        c.setFeats(feats);

        service.saveOrUpdate(c);

        assertTrue(service.hasFeat(c, Feats.ATHLETE));

        service.delete(c);

    }

    private Character createDummyCharacter(CharacterService service) {
        Random r = new Random();
        String name = "Character" + r.ints(0, (10000 + 1)).findFirst().getAsInt();

        Character c = service.create();
        c.setName(name);
        c.setGender(Gender.M);
        c.setHeight("5'7\"");
        c.setWeight(150);
        c.setAlignment(AlignmentService.getInstance().read(1L));
        c.setRegion(RegionService.getInstance().read(1L));
        c.setRace(RaceService.getInstance().read(2L));
        c.setImage("/male/abeirL.bmp");
        return c;
    }

}
