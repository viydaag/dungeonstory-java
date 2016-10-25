package com.dungeonstory.backend.data.util;

import java.util.Optional;

import com.dungeonstory.backend.data.Character;
import com.dungeonstory.backend.data.CharacterClass;
import com.dungeonstory.backend.data.DSClass;

public class ClassUtil {

    private ClassUtil() {
        // TODO Auto-generated constructor stub
    }

    public static Optional<CharacterClass> getCharacterClass(Character character, DSClass dsClass) {
        Optional<CharacterClass> assignedClass = character.getClasses().stream()
                .filter(characterClass -> characterClass.getClasse().equals(dsClass)).findFirst();

        return assignedClass;
    }

}
