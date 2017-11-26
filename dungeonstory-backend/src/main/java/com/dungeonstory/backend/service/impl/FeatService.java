package com.dungeonstory.backend.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.dungeonstory.backend.data.Character;
import com.dungeonstory.backend.data.CharacterClass;
import com.dungeonstory.backend.data.Feat;
import com.dungeonstory.backend.data.util.ModifierUtil;
import com.dungeonstory.backend.repository.impl.FeatRepository;
import com.dungeonstory.backend.service.AbstractDataService;
import com.dungeonstory.backend.service.FeatDataService;

public class FeatService extends AbstractDataService<Feat, Long> implements FeatDataService {

    private static final long serialVersionUID = -904004337605184211L;

    private static FeatService instance = null;
    private FeatRepository repository = null;

    public static synchronized FeatService getInstance() {
        if (instance == null) {
            instance = new FeatService();
        }
        return instance;
    }

    private FeatService() {
        super();
        repository = new FeatRepository();
        setEntityFactory(() -> new Feat());
        setRepository(repository);
    }


    @Override
    public List<Feat> findAllFeatsExcept(Feat feat) {
        return repository.findAllFeatsExcept(feat);
    }

    @Override
    public List<Feat> findAllUnassignedFeats(Character character) {
        List<Feat> feats = repository.findAllUnassignedFeats(character);
        return feats.stream().filter(feat -> isFeatAvailable(feat, character)).collect(Collectors.toList());
    }

    /**
     * Check if a character has the right to assign a feat to itself
     * according to feat prerequisite.
     * @param feat
     * @param character
     * @return
     */
    private boolean isFeatAvailable(Feat feat, Character character) {
        boolean isAvailable = true;
        switch (feat.getPrerequisiteType()) {
            case ABILITY:
                int score = ModifierUtil.getAbilityScore(character, feat.getPrerequisiteAbility());
                if (score < feat.getPrerequisiteAbilityScore()) {
                    isAvailable = false;
                }
                break;

            case ARMOR_PROFICIENCY:
                if (!character.getArmorProficiencies().contains(feat.getPrerequisiteArmorProficiency())) {
                    isAvailable = false;
                }
                break;

            case CAST_SPELL:
                isAvailable = character.getClasses().stream().map(CharacterClass::getClasse).anyMatch(
                        classe -> classe.getIsSpellCasting());
                break;

            case NONE:
            default:
                break;
        }
        return isAvailable;
    }


}
