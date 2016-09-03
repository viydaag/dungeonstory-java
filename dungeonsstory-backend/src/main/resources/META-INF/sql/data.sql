/* AccessRole */
insert into accessrole(id, name, roletype, version, created, updated) values (1, 'Administrateur', 0, 1, now(), now());
insert into accessrole(id, name, roletype, version, created, updated) values (2, 'Modérateur', 1, 1, now(), now());
insert into accessrole(id, name, roletype, version, created, updated) values (3, 'Joueur', 2, 1, now(), now());

/* User */
insert into user(id, username, password, name, email, status, roleId, version, created, updated) values(1, 'admin', '21232f297a57a5a743894a0e4a801fc3', 'JC Fortier', 'fortier.jc@gmail.com', 1, 1, 1, now(), now());

/* Ability */
insert into ability(id, abbreviation, name, description, version, created, updated) values(1, 'For', 'Force', 'Mesure le pouvoir physique', 1, now(), now());
insert into ability(id, abbreviation, name, description, version, created, updated) values(2, 'Dex', 'Dextérité', 'Mesure l''agilité', 1, now(), now());
insert into ability(id, abbreviation, name, description, version, created, updated) values(3, 'Con', 'Constitution', 'Mesure l''endurance', 1, now(), now());
insert into ability(id, abbreviation, name, description, version, created, updated) values(4, 'Int', 'Intelligence', 'Mesure le raisonnement et la mémoire', 1, now(), now());
insert into ability(id, abbreviation, name, description, version, created, updated) values(5, 'Sag', 'Sagesse', 'Mesure la perception et la perspicacité', 1, now(), now());
insert into ability(id, abbreviation, name, description, version, created, updated) values(6, 'Cha', 'Charisme', 'Mesure la force de la personnalité', 1, now(), now());

/* Alignment */
insert into alignment(id, name, abbreviation, shortDescription, description, version, created, updated) values(1, 'Loyal Bon', 'LB', 'Fait la bonne chose attendue de la société', '', 1, now(), now());
insert into alignment(id, name, abbreviation, shortDescription, description, version, created, updated) values(2, 'Neutre Bon', 'NB', 'Fait de son mieux pour aider les autres selon ses besoins', '', 1, now(), now());
insert into alignment(id, name, abbreviation, shortDescription, description, version, created, updated) values(3, 'Chaotique Bon', 'CB', 'Agit selon sa conscience sans tenir compte des autres', '', 1, now(), now());
insert into alignment(id, name, abbreviation, shortDescription, description, version, created, updated) values(4, 'Loyal Neutre', 'LN', 'Agit en accord avec la loi, les traditions ou les codes personnels', '', 1, now(), now());
insert into alignment(id, name, abbreviation, shortDescription, description, version, created, updated) values(5, 'Neutre strict', 'N', 'Fait de son mieux en temps voulu sans prendre parti', '', 1, now(), now());
insert into alignment(id, name, abbreviation, shortDescription, description, version, created, updated) values(6, 'Chaotique Neutre', 'CN', 'Agit selon ses caprices et sa liberté personnelle', '', 1, now(), now());
insert into alignment(id, name, abbreviation, shortDescription, description, version, created, updated) values(7, 'Loyal Mauvais', 'LM', 'Prend ce qu''il veut dans les limites de la loi et des traditions', '', 1, now(), now());
insert into alignment(id, name, abbreviation, shortDescription, description, version, created, updated) values(8, 'Neutre Mauvais', 'NM', 'Fait ce qu''il peut sans compassion ni scrupule', '', 1, now(), now());
insert into alignment(id, name, abbreviation, shortDescription, description, version, created, updated) values(9, 'Chaotique Mauvais', 'CM', 'Agit avec violence stimulé par l''avidité, la haine et la soif de sang', '', 1, now(), now());
insert into alignment(id, name, abbreviation, shortDescription, description, version, created, updated) values(10, 'Aucun', 'A', 'Sans capacité de raisonnement ou de choix moral', '', 1, now(), now());

/* DamageType */
insert into damagetype(id, name, description, version, created, updated) values(1, 'Acide', 'Le souffle corrosif d''un dragon noir ou les enzymes disolvantes d''un estomac de troll.', 1, now(), now());
insert into damagetype(id, name, description, version, created, updated) values(2, 'Contandant', 'La force de frappe d''un marteau ou la chute d''une construction.', 1, now(), now());
insert into damagetype(id, name, description, version, created, updated) values(3, 'Feu', 'Le souffle brulant d''un dragon rouge ou les flammes d''un sort.', 1, now(), now());
insert into damagetype(id, name, description, version, created, updated) values(4, 'Froid', 'Le souffle gelé d''un dragon blanc ou la pointe de lance d''un diable des glaces.', 1, now(), now());
insert into damagetype(id, name, description, version, created, updated) values(5, 'Force', 'Pure énergie magique concentré pour faire des dégâts. Plusieurs sorts comme missiles magiques utilisent la force.', 1, now(), now());
insert into damagetype(id, name, description, version, created, updated) values(6, 'Électricité', 'Le souffle électrisant d''un dragon bleu ou un sort d''éclair.', 1, now(), now());
insert into damagetype(id, name, description, version, created, updated) values(7, 'Nécrotique', 'Dommage causé par les morts-vivants et certains sorts de nécromancie.', 1, now(), now());
insert into damagetype(id, name, description, version, created, updated) values(8, 'Perforant', 'Attaque de perforation et empalement incluant les lances et morsures.', 1, now(), now());
insert into damagetype(id, name, description, version, created, updated) values(9, 'Poison', 'Dards venimeux et le souffle toxique d''un dragon noir.', 1, now(), now());
insert into damagetype(id, name, description, version, created, updated) values(10, 'Psychique', 'Abileté mentale comme les flagelleurs mentaux qui utilisent la salve psionique.', 1, now(), now());
insert into damagetype(id, name, description, version, created, updated) values(11, 'Radiant', 'Pouvoir divin comme l''épée d''un ange ou la colonne de flamme d''un clerc.', 1, now(), now());
insert into damagetype(id, name, description, version, created, updated) values(12, 'Tranchant', 'Épées, haches et griffes causent des dommages tranchants.', 1, now(), now());
insert into damagetype(id, name, description, version, created, updated) values(13, 'Tonnerre', 'Éclat violent de son.', 1, now(), now());

/* Level */
insert into level(id, maxExperience, proficiencyBonus, version, created, updated) values(1, 300, 2, 1, now(), now());
insert into level(id, maxExperience, proficiencyBonus, version, created, updated) values(2, 900, 2, 1, now(), now());
insert into level(id, maxExperience, proficiencyBonus, version, created, updated) values(3, 2700, 2, 1, now(), now());
insert into level(id, maxExperience, proficiencyBonus, version, created, updated) values(4, 6500, 2, 1, now(), now());
insert into level(id, maxExperience, proficiencyBonus, version, created, updated) values(5, 14000, 3, 1, now(), now());
insert into level(id, maxExperience, proficiencyBonus, version, created, updated) values(6, 23000, 3, 1, now(), now());
insert into level(id, maxExperience, proficiencyBonus, version, created, updated) values(7, 34000, 3, 1, now(), now());
insert into level(id, maxExperience, proficiencyBonus, version, created, updated) values(8, 48000, 3, 1, now(), now());
insert into level(id, maxExperience, proficiencyBonus, version, created, updated) values(9, 64000, 4, 1, now(), now());
insert into level(id, maxExperience, proficiencyBonus, version, created, updated) values(10, 85000, 4, 1, now(), now());

/* Language */
insert into language (id, name, script, version, created, updated) VALUES (1, 'Commun', 'Commun', 1, now(), now());
insert into language (id, name, script, version, created, updated) VALUES (2, 'Nain', 'Nain', 1, now(), now());
insert into language (id, name, script, version, created, updated) VALUES (3, 'Elfe', 'Elfe', 1, now(), now());
insert into language (id, name, script, version, created, updated) VALUES (4, 'Géant', 'Nain', 1, now(), now());
insert into language (id, name, script, version, created, updated) VALUES (5, 'Gnome', 'Nain', 1, now(), now());
insert into language (id, name, script, version, created, updated) VALUES (6, 'Gobelin', 'Nain', 1, now(), now());
insert into language (id, name, script, version, created, updated) VALUES (7, 'Halfelin', 'Commun', 1, now(), now());
insert into language (id, name, script, version, created, updated) VALUES (8, 'Orc', 'Nain', 1, now(), now());
insert into language (id, name, script, version, created, updated) VALUES (9, 'Abyssal', 'Infernal', 1, now(), now());
insert into language (id, name, script, version, created, updated) VALUES (10, 'Céleste', 'Céleste', 1, now(), now());
insert into language (id, name, script, version, created, updated) VALUES (11, 'Draconique', 'Draconique', 1, now(), now());
insert into language (id, name, script, version, created, updated) VALUES (12, 'Profondeur', 'Elfe', 1, now(), now());
insert into language (id, name, script, version, created, updated) VALUES (13, 'Infernal', 'Infernal', 1, now(), now());
insert into language (id, name, script, version, created, updated) VALUES (14, 'Primordial', 'Nain', 1, now(), now());
insert into language (id, name, script, version, created, updated) VALUES (15, 'Sylvain', 'Elfe', 1, now(), now());

/* Skill */
insert into skill(id, name, keyAbilityId, shortDescription, description, version, created, updated) values (1, 'Athlétisme', 1, 'Utiliser dans les situations difficiles pour grimper, sauter ou nager.', '', 1, now(), now());
insert into skill(id, name, keyAbilityId, shortDescription, description, version, created, updated) values (2, 'Acrobatie', 2, 'Rester sure ses pieds dans les situation difficiles.', '', 1, now(), now());
insert into skill(id, name, keyAbilityId, shortDescription, description, version, created, updated) values (3, 'Passe-passe', 2, 'Utiliser sa dextérité pour cacher, déplacer ou voler quelque chose sans être repéré.', '', 1, now(), now());
insert into skill(id, name, keyAbilityId, shortDescription, description, version, created, updated) values (4, 'Furtivité', 2, 'Se cacher de ses ennemis ou se déplacer sans être vu.', '', 1, now(), now());
insert into skill(id, name, keyAbilityId, shortDescription, description, version, created, updated) values (5, 'Arcane', 4, 'Connaitre un sort, un plan d''existence, un symbole ou un objet magique.', '', 1, now(), now());
insert into skill(id, name, keyAbilityId, shortDescription, description, version, created, updated) values (6, 'Histoire', 4, 'Connaitre l''histoire des royaumes et ses habitants.', '', 1, now(), now());
insert into skill(id, name, keyAbilityId, shortDescription, description, version, created, updated) values (7, 'Investigation', 4, 'Recherche d''indices pour trouver quelque chose.', '', 1, now(), now());
insert into skill(id, name, keyAbilityId, shortDescription, description, version, created, updated) values (8, 'Nature', 4, 'Connaitre les plantes, animaux, terrains et climats.', '', 1, now(), now());
insert into skill(id, name, keyAbilityId, shortDescription, description, version, created, updated) values (9, 'Religion', 4, 'Connaitre les dieux, rites, prières, pratiques et symboles.', '', 1, now(), now());
insert into skill(id, name, keyAbilityId, shortDescription, description, version, created, updated) values (10, 'Dressage des animaux', 5, 'Utiliser pour calmer ou monter un animal.', '', 1, now(), now());
insert into skill(id, name, keyAbilityId, shortDescription, description, version, created, updated) values (11, 'Perspicacité', 5, 'Déterminer les intentions, prédire la prochaine action', '', 1, now(), now());
insert into skill(id, name, keyAbilityId, shortDescription, description, version, created, updated) values (12, 'Soin', 5, 'Stabiliser l''état de quelqu''un ou diagnostiquer une maladie.', '', 1, now(), now());
insert into skill(id, name, keyAbilityId, shortDescription, description, version, created, updated) values (13, 'Perception', 5, 'Voir, entendre ou détecter une présence', '', 1, now(), now());
insert into skill(id, name, keyAbilityId, shortDescription, description, version, created, updated) values (14, 'Survie', 5, 'Pister, chasser, guider dans un endroit peu familier.', '', 1, now(), now());
insert into skill(id, name, keyAbilityId, shortDescription, description, version, created, updated) values (15, 'Tromperie', 6, 'Cacher la vérité, tromper quelqu''un.', '', 1, now(), now());
insert into skill(id, name, keyAbilityId, shortDescription, description, version, created, updated) values (16, 'Intimidation', 6, 'Influencer par l''hostilité.', '', 1, now(), now());
insert into skill(id, name, keyAbilityId, shortDescription, description, version, created, updated) values (17, 'Performance', 6, 'Capter l''audience par le divertissement.', '', 1, now(), now());
insert into skill(id, name, keyAbilityId, shortDescription, description, version, created, updated) values (18, 'Persuasion', 6, 'Influencer par le tact, les bonnes manières.', '', 1, now(), now());

/* ArmorType */
insert into armortype (id, baseArmorClass, baseWeight, description, maxDexBonus, minStrength, name, proficiencyType, stealthDisavantage, version, created, updated) values (1, 1, 8, '', -1, 1, 'Matelassé', 'LIGHT', 1, 1, now(), now());
insert into armortype (id, baseArmorClass, baseWeight, description, maxDexBonus, minStrength, name, proficiencyType, stealthDisavantage, version, created, updated) values (2, 1, 10, '', -1, 1, 'Cuir', 'LIGHT', 0, 1, now(), now());
insert into armortype (id, baseArmorClass, baseWeight, description, maxDexBonus, minStrength, name, proficiencyType, stealthDisavantage, version, created, updated) values (3, 2, 13, '', -1, 1, 'Clouté','LIGHT', 0, 1, now(), now());
insert into armortype (id, baseArmorClass, baseWeight, description, maxDexBonus, minStrength, name, proficiencyType, stealthDisavantage, version, created, updated) values (4, 2, 12, '', 2, 1, 'Peau','MEDIUM', 0, 1, now(), now());
insert into armortype (id, baseArmorClass, baseWeight, description, maxDexBonus, minStrength, name, proficiencyType, stealthDisavantage, version, created, updated) values (5, 3, 20, '', 2, 1, 'Chemise de cote de maille', 'MEDIUM', 0, 1, now(), now());
insert into armortype (id, baseArmorClass, baseWeight, description, maxDexBonus, minStrength, name, proficiencyType, stealthDisavantage, version, created, updated) values (6, 4, 45, '', 2, 1, 'Feuilleté', 'MEDIUM', 1, 1, now(), now());
insert into armortype (id, baseArmorClass, baseWeight, description, maxDexBonus, minStrength, name, proficiencyType, stealthDisavantage, version, created, updated) values (7, 4, 20, '', 2, 1, 'Plastron de plate', 'MEDIUM', 0, 1, now(), now());
insert into armortype (id, baseArmorClass, baseWeight, description, maxDexBonus, minStrength, name, proficiencyType, stealthDisavantage, version, created, updated) values (8, 5, 40, '', 2, 1, 'Demie-plate', 'MEDIUM', 1, 1, now(), now());
insert into armortype (id, baseArmorClass, baseWeight, description, maxDexBonus, minStrength, name, proficiencyType, stealthDisavantage, version, created, updated) values (9, 4, 40, '', 0, 1, 'Annelé', 'HEAVY', 1, 1, now(), now());
insert into armortype (id, baseArmorClass, baseWeight, description, maxDexBonus, minStrength, name, proficiencyType, stealthDisavantage, version, created, updated) values (10, 6, 55, '', 0, 13, 'Cote de maille', 'HEAVY', 1, 1, now(), now());
insert into armortype (id, baseArmorClass, baseWeight, description, maxDexBonus, minStrength, name, proficiencyType, stealthDisavantage, version, created, updated) values (11, 7, 60, '', 0, 15, 'Éclisse', 'HEAVY', 1, 1, now(), now());
insert into armortype (id, baseArmorClass, baseWeight, description, maxDexBonus, minStrength, name, proficiencyType, stealthDisavantage, version, created, updated) values (12, 8, 65, '', 0, 15, 'Plate complète', 'HEAVY', 1, 1, now(), now());
insert into armortype (id, baseArmorClass, baseWeight, description, maxDexBonus, minStrength, name, proficiencyType, stealthDisavantage, version, created, updated) values (13, 2, 6, '', 0, 1, 'Bouclier', 'SHIELD', 0, 1, now(), now());

/* Race */