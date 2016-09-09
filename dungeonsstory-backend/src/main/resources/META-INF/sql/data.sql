/* AccessRole */
insert into accessrole(id, name, roletype, version, created, updated) values (1, 'Administrateur', 0, 1, now(), now());
insert into accessrole(id, name, roletype, version, created, updated) values (2, 'Modérateur', 1, 1, now(), now());
insert into accessrole(id, name, roletype, version, created, updated) values (3, 'Joueur', 2, 1, now(), now());

/* User */
insert into user(id, username, password, name, email, status, roleId, version, created, updated) values (1, 'admin', '21232f297a57a5a743894a0e4a801fc3', 'JC Fortier', 'fortier.jc@gmail.com', 1, 1, 1, now(), now());

/* Ability */
insert into ability(id, abbreviation, name, description, version, created, updated) values (1, 'For', 'Force', 'Mesure le pouvoir physique', 1, now(), now());
insert into ability(id, abbreviation, name, description, version, created, updated) values (2, 'Dex', 'Dextérité', 'Mesure l''agilité', 1, now(), now());
insert into ability(id, abbreviation, name, description, version, created, updated) values (3, 'Con', 'Constitution', 'Mesure l''endurance', 1, now(), now());
insert into ability(id, abbreviation, name, description, version, created, updated) values (4, 'Int', 'Intelligence', 'Mesure le raisonnement et la mémoire', 1, now(), now());
insert into ability(id, abbreviation, name, description, version, created, updated) values (5, 'Sag', 'Sagesse', 'Mesure la perception et la perspicacité', 1, now(), now());
insert into ability(id, abbreviation, name, description, version, created, updated) values (6, 'Cha', 'Charisme', 'Mesure la force de la personnalité', 1, now(), now());

/* Alignment */
insert into alignment(id, name, abbreviation, shortDescription, description, version, created, updated) values (1, 'Loyal Bon', 'LB', 'Fait la bonne chose attendue de la société', '', 1, now(), now());
insert into alignment(id, name, abbreviation, shortDescription, description, version, created, updated) values (2, 'Neutre Bon', 'NB', 'Fait de son mieux pour aider les autres selon ses besoins', '', 1, now(), now());
insert into alignment(id, name, abbreviation, shortDescription, description, version, created, updated) values (3, 'Chaotique Bon', 'CB', 'Agit selon sa conscience sans tenir compte des autres', '', 1, now(), now());
insert into alignment(id, name, abbreviation, shortDescription, description, version, created, updated) values (4, 'Loyal Neutre', 'LN', 'Agit en accord avec la loi, les traditions ou les codes personnels', '', 1, now(), now());
insert into alignment(id, name, abbreviation, shortDescription, description, version, created, updated) values (5, 'Neutre strict', 'N', 'Fait de son mieux en temps voulu sans prendre parti', '', 1, now(), now());
insert into alignment(id, name, abbreviation, shortDescription, description, version, created, updated) values (6, 'Chaotique Neutre', 'CN', 'Agit selon ses caprices et sa liberté personnelle', '', 1, now(), now());
insert into alignment(id, name, abbreviation, shortDescription, description, version, created, updated) values (7, 'Loyal Mauvais', 'LM', 'Prend ce qu''il veut dans les limites de la loi et des traditions', '', 1, now(), now());
insert into alignment(id, name, abbreviation, shortDescription, description, version, created, updated) values (8, 'Neutre Mauvais', 'NM', 'Fait ce qu''il peut sans compassion ni scrupule', '', 1, now(), now());
insert into alignment(id, name, abbreviation, shortDescription, description, version, created, updated) values (9, 'Chaotique Mauvais', 'CM', 'Agit avec violence stimulé par l''avidité, la haine et la soif de sang', '', 1, now(), now());
insert into alignment(id, name, abbreviation, shortDescription, description, version, created, updated) values (10, 'Aucun', 'A', 'Sans capacité de raisonnement ou de choix moral', '', 1, now(), now());

/* DamageType */
insert into damagetype(id, name, description, version, created, updated) values (1, 'Acide', 'Le souffle corrosif d''un dragon noir ou les enzymes disolvantes d''un estomac de troll.', 1, now(), now());
insert into damagetype(id, name, description, version, created, updated) values (2, 'Contandant', 'La force de frappe d''un marteau ou la chute d''une construction.', 1, now(), now());
insert into damagetype(id, name, description, version, created, updated) values (3, 'Feu', 'Le souffle brulant d''un dragon rouge ou les flammes d''un sort.', 1, now(), now());
insert into damagetype(id, name, description, version, created, updated) values (4, 'Froid', 'Le souffle gelé d''un dragon blanc ou la pointe de lance d''un diable des glaces.', 1, now(), now());
insert into damagetype(id, name, description, version, created, updated) values (5, 'Force', 'Pure énergie magique concentré pour faire des dégâts. Plusieurs sorts comme missiles magiques utilisent la force.', 1, now(), now());
insert into damagetype(id, name, description, version, created, updated) values (6, 'Électricité', 'Le souffle électrisant d''un dragon bleu ou un sort d''éclair.', 1, now(), now());
insert into damagetype(id, name, description, version, created, updated) values (7, 'Nécrotique', 'Dommage causé par les morts-vivants et certains sorts de nécromancie.', 1, now(), now());
insert into damagetype(id, name, description, version, created, updated) values (8, 'Perforant', 'Attaque de perforation et empalement incluant les lances et morsures.', 1, now(), now());
insert into damagetype(id, name, description, version, created, updated) values (9, 'Poison', 'Dards venimeux et le souffle toxique d''un dragon noir.', 1, now(), now());
insert into damagetype(id, name, description, version, created, updated) values (10, 'Psychique', 'Abileté mentale comme les flagelleurs mentaux qui utilisent la salve psionique.', 1, now(), now());
insert into damagetype(id, name, description, version, created, updated) values (11, 'Radiant', 'Pouvoir divin comme l''épée d''un ange ou la colonne de flamme d''un clerc.', 1, now(), now());
insert into damagetype(id, name, description, version, created, updated) values (12, 'Tranchant', 'Épées, haches et griffes causent des dommages tranchants.', 1, now(), now());
insert into damagetype(id, name, description, version, created, updated) values (13, 'Tonnerre', 'Éclat violent de son.', 1, now(), now());

/* Level */
insert into level (id, maxExperience, proficiencyBonus, version, created, updated) values (1, 300, 2, 1, now(), now());
insert into level (id, maxExperience, proficiencyBonus, version, created, updated) values (2, 900, 2, 1, now(), now());
insert into level (id, maxExperience, proficiencyBonus, version, created, updated) values (3, 2700, 2, 1, now(), now());
insert into level (id, maxExperience, proficiencyBonus, version, created, updated) values (4, 6500, 2, 1, now(), now());
insert into level (id, maxExperience, proficiencyBonus, version, created, updated) values (5, 14000, 3, 1, now(), now());
insert into level (id, maxExperience, proficiencyBonus, version, created, updated) values (6, 23000, 3, 1, now(), now());
insert into level (id, maxExperience, proficiencyBonus, version, created, updated) values (7, 34000, 3, 1, now(), now());
insert into level (id, maxExperience, proficiencyBonus, version, created, updated) values (8, 48000, 3, 1, now(), now());
insert into level (id, maxExperience, proficiencyBonus, version, created, updated) values (9, 64000, 4, 1, now(), now());
insert into level (id, maxExperience, proficiencyBonus, version, created, updated) values (10, 85000, 4, 1, now(), now());
insert into level (id, maxExperience, proficiencyBonus, version, created, updated) values (11, 100000, 4, 2, now(), now());
insert into level (id, maxExperience, proficiencyBonus, version, created, updated) values (12, 120000, 4, 2, now(), now());
insert into level (id, maxExperience, proficiencyBonus, version, created, updated) values (13, 140000, 5, 2, now(), now());
insert into level (id, maxExperience, proficiencyBonus, version, created, updated) values (14, 165000, 5, 2, now(), now());
insert into level (id, maxExperience, proficiencyBonus, version, created, updated) values (15, 195000, 5, 2, now(), now());
insert into level (id, maxExperience, proficiencyBonus, version, created, updated) values (16, 225000, 5, 2, now(), now());
insert into level (id, maxExperience, proficiencyBonus, version, created, updated) values (17, 265000, 6, 2, now(), now());
insert into level (id, maxExperience, proficiencyBonus, version, created, updated) values (18, 305000, 6, 2, now(), now());
insert into level (id, maxExperience, proficiencyBonus, version, created, updated) values (19, 355000, 6, 2, now(), now());
insert into level (id, maxExperience, proficiencyBonus, version, created, updated) values (20, 999999, 6, 2, now(), now());

/* Language */
insert into language (id, name, script, version, created, updated) values (1, 'Commun', 'Commun', 1, now(), now());
insert into language (id, name, script, version, created, updated) values (2, 'Nain', 'Nain', 1, now(), now());
insert into language (id, name, script, version, created, updated) values (3, 'Elfe', 'Elfe', 1, now(), now());
insert into language (id, name, script, version, created, updated) values (4, 'Géant', 'Nain', 1, now(), now());
insert into language (id, name, script, version, created, updated) values (5, 'Gnome', 'Nain', 1, now(), now());
insert into language (id, name, script, version, created, updated) values (6, 'Gobelin', 'Nain', 1, now(), now());
insert into language (id, name, script, version, created, updated) values (7, 'Halfelin', 'Commun', 1, now(), now());
insert into language (id, name, script, version, created, updated) values (8, 'Orc', 'Nain', 1, now(), now());
insert into language (id, name, script, version, created, updated) values (9, 'Abyssal', 'Infernal', 1, now(), now());
insert into language (id, name, script, version, created, updated) values (10, 'Céleste', 'Céleste', 1, now(), now());
insert into language (id, name, script, version, created, updated) values (11, 'Draconique', 'Draconique', 1, now(), now());
insert into language (id, name, script, version, created, updated) values (12, 'Profondeur', 'Elfe', 1, now(), now());
insert into language (id, name, script, version, created, updated) values (13, 'Infernal', 'Infernal', 1, now(), now());
insert into language (id, name, script, version, created, updated) values (14, 'Primordial', 'Nain', 1, now(), now());
insert into language (id, name, script, version, created, updated) values (15, 'Sylvain', 'Elfe', 1, now(), now());

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

/* DivineDomain */
insert into divinedomain (id, name, description, version, created, updated) values (1,'Connaissance', 'Les dieux de la connaissance, y compris Oghma valorise l''apprentissage et la compréhension avant tout . Certains enseignent que la connaissance doit être recueillies et partagées dans les bibliothèques et les universités, ou de promouvoir la connaissance pratique de l''artisanat et de l''invention. Certaines divinités amassent des connaissances et gardent leurs secrets pour eux-mêmes . Et certains promettent leurs partisans qu''ils gagneront un pouvoir énorme s''ils découvrent les secrets du multivers . Les adeptes de ces dieux\nétudient la connaissance ésotérique, recueillent des anciens tomes, plongent dans le lieux secrets et apprennent tout ce qu''ils peuvent. ',1, now(), now());
insert into divinedomain (id, name, description, version, created, updated) values (2,'Vie', 'Le domaine de la vie se concentre sur la dynamique d''énergie positive, une des forces fondamentales de l''univers, qui soutient toute la vie . Les dieux de la vie favorisent la vitalité et la santé grâce à la guérison des malades et des blessés, prendre soin des personnes dans le besoin, et chassant les forces de la mort et de la mort-vie. Presque toute divinité non-mauvaise peut prétendre influence sur ce domaine.',1, now(), now());
insert into divinedomain (id, name, description, version, created, updated) values (3,'Lumière', 'Les dieux de la lumière promeuvent les idéaux de la renaissance et du renouveau, de la vérité, la vigilance et la beauté, souvent en utilisant le symbole du soleil. Certains de ces dieux sont dépeints comme le soleil lui-même ou comme un chariot qui guide le soleil à travers le ciel. D''autres sont des sentinelles infatigables dont les yeux percent chaque ombre et voient à travers toutes les tromperies. Certains sont des divinités de la beauté et de l''art, qui enseignent que l''art est un véhicule pour l''amélioration de l''âme . Les clercs d''un dieu de la lumière sont des âmes éclairées infusé avec éclat de la puissance de la vision perspicace de leurs dieux , chargé de chasser les mensonges et brûlant les ténèbres .',1, now(), now());
insert into divinedomain (id, name, description, version, created, updated) values (4,'Nature', 'Les dieux de la nature sont aussi variés que le monde naturel lui-même, des dieux impénétrables des forêts profondes aux divinités amicales associés à des oasis et des bosquets. Les druides vénèrent la nature dans son ensemble et pourraient servir une de ces divinités, pratiquant des rites mystérieux et réciter des prières oubliées dans leur langue secrète . Mais beaucoup de ces dieux ont des clercs aussi, champions qui prennent un rôle plus actif pour faire avancer les intérêts d''un dieu de la nature. Ces clercs chassent les monstruosités du mal qui dépouillent les forêts, bénissent la récolte des fidèles, ou dépérissent les cultures de ceux qui engendrent la colère de leur dieu.',1, now(), now());
insert into divinedomain (id, name, description, version, created, updated) values (5,'Tempête', 'Les dieux de ce domaine régissent les tempêtes, la mer et le ciel. Ils comprennent des dieux de la foudre et le tonnerre, des tremblements de terre, du feu, de la violence, de la force physique et du courage. Les dieux du domaine de la tempête envoient leurs clercs pour inspirer la peur dans les gens du commun, que ce soit pour garder les gens sur le chemin de la justice ou de les encourager à offrir des sacrifices pour conjurer la colère divine .',1, now(), now());
insert into divinedomain (id, name, description, version, created, updated) values (6,'Ruse', 'Dieux de la ruse sont corrupteurs et les instigateurs qui se tiennent comme un défi constant à l''ordre accepté parmi les les dieux et les mortels. Ils sont les patrons de voleurs, canailles, joueurs, rebelles et libérateurs. Leurs clercs sont une force perturbatrice dans le monde, prônant la fierté, se moquant des tyrans, volant les riches, libérant les captifs et les méprisant traditions. Ils préfèrent les subterfuges, farces, la tromperie et le vol plutôt que la confrontation directe .',1, now(), now());
insert into divinedomain (id, name, description, version, created, updated) values (7,'Guerre', 'La guerre a de nombreuses manifestations . Elle peut faire des héros des gens ordinaires. Elle peut être désespérée et terrible, avec\nactes de cruauté et de lâcheté éclipsant les instances d''excellence et courage. Dans les deux cas , les dieux de la guerre veillent sur les guerriers et les récompensent pour leur grand actes. Les clercs de ces dieux excellent dans la bataille, inspirant les autres pour combattre  ou offrant des actes de violence comme prières . Les dieux de la guerre incluent des champions d''honneur et de la chevalerie tels que Torm ainsi que les dieux de la destruction et le pillage comme Gruumsh et dieux conquête et de domination comme Bane. D''autres dieux de guerre Tempus prennent une position plus neutre , promeuvent la guerre dans toutes ses manifestations et supportent les guerriers dans toute circonstance.',1, now(), now());
insert into divinedomain (id, name, description, version, created, updated) values (8,'Mort', NULL,1, now(), now());

/* Deity */
insert into deity (id, name, description, alignmentId, symbol, version, created, updated) values (1,'Auril','Déesse de l''hiver',8,'Flocon de neige à six pointe',2, now(), now());
insert into deity (id, name, description, alignmentId, symbol, version, created, updated) values (2,'Azuth','Dieu des magiciens',4,'Main gauche pointée vers le haut en feu',1, now(), now());
insert into deity (id, name, description, alignmentId, symbol, version, created, updated) values (3,'Bane','Dieu de la tyrannie',7,'Main droite noire avec les doigts fermés sur pouce',1, now(), now());
insert into deity (id, name, description, alignmentId, symbol, version, created, updated) values (4,'Beshaba','Déesse du malheur',9,'Ramures noires',1, now(), now());
insert into deity (id, name, description, alignmentId, symbol, version, created, updated) values (5,'Bhaal','Dieu du meurtre',8,'Crâne entouré de larmes de sang',1, now(), now());
insert into deity (id, name, description, alignmentId, symbol, version, created, updated) values (6,'Chauntea','Déesse de l''agriculture',2,'Liasse de grain ou une rose sur du grain',1, now(), now());
insert into deity (id, name, description, alignmentId, symbol, version, created, updated) values (7,'Cyric','Dieu des mensonges',9,'Crâne sans mâchoire sur soleil noir ou pourpre',1, now(), now());
insert into deity (id, name, description, alignmentId, symbol, version, created, updated) values (8,'Deneir','Dieu de l''écriture',2,'Bougie allumée au dessus d''un oeil ouvert',1, now(), now());
insert into deity (id, name, description, alignmentId, symbol, version, created, updated) values (9,'Eldath','Déesse de la paix',2,'Chute d''eau plongeant dans l''eau stagnate',1, now(), now());
insert into deity (id, name, description, alignmentId, symbol, version, created, updated) values (10,'Gond','Dieu de l''invention',5,'Bouclier denté avec 4 pointes',1, now(), now());
insert into deity (id, name, description, alignmentId, symbol, version, created, updated) values (11,'Helm','Dieu de la protection',4,'Oeil ouvert sur un gantelet droit',1, now(), now());
insert into deity (id, name, description, alignmentId, symbol, version, created, updated) values (12,'Ilmater','Dieu de l''endurance',1,'Mains jointes aux poignets avec de la corde rouge',1, now(), now());
insert into deity (id, name, description, alignmentId, symbol, version, created, updated) values (13,'Kelemvor','Dieu des morts',4,'Bras droit squelettique tenant une balance',2, now(), now());
insert into deity (id, name, description, alignmentId, symbol, version, created, updated) values (14,'Lathandre','Dieu de la naissance et du renouveau',2,'Route menant au levé du soleil',1, now(), now());
insert into deity (id, name, description, alignmentId, symbol, version, created, updated) values (15,'Leira','Déesse de l''illusion',6,'Triangle vers le bas contenant un tourbillon de brouillard',1, now(), now());
insert into deity (id, name, description, alignmentId, symbol, version, created, updated) values (16,'Loviatar','Déesse de la douleur',7,'Fouet à 9 queues',1, now(), now());
insert into deity (id, name, description, alignmentId, symbol, version, created, updated) values (17,'Malar','Dieu de la chasse',9,'Main griffue',1, now(), now());
insert into deity (id, name, description, alignmentId, symbol, version, created, updated) values (18,'Mielikki','Déesse des forêts',2,'Tête de licorne',1, now(), now());
insert into deity (id, name, description, alignmentId, symbol, version, created, updated) values (19,'Myrkul','Dieu de la mort',8,'Crâne humain blanc',1, now(), now());
insert into deity (id, name, description, alignmentId, symbol, version, created, updated) values (20,'Mask','Dieu des voleurs',6,'Masque noir',1, now(), now());
insert into deity (id, name, description, alignmentId, symbol, version, created, updated) values (21,'Mystra','Déesse de la magie',2,'Cercle de 7 étoiles',1, now(), now());
insert into deity (id, name, description, alignmentId, symbol, version, created, updated) values (22,'Oghma','Dieu de la connaissance',5,'Parchemin vide',1, now(), now());
insert into deity (id, name, description, alignmentId, symbol, version, created, updated) values (23,'Seluné','Déesse le la lune',3,'Pair d''yeux surmontés de 7 étoiles',1, now(), now());
insert into deity (id, name, description, alignmentId, symbol, version, created, updated) values (24,'Shar','Déesse de la noirceur et de la perte',8,'Disque noir bordé de violet',1, now(), now());
insert into deity (id, name, description, alignmentId, symbol, image, version, created, updated) values (25,'Silvanus','Dieu de la nature sauvage',5,'Feuille de chêne',NULL,1,'2016-09-07 09:17:22','2016-09-07 09:17:22');
insert into deity (id, name, description, alignmentId, symbol, image, version, created, updated) values (26,'Sunie','Déesse de l''amour et de la beauté',3,'Visage d''une belle femme rousse',NULL,1,'2016-09-07 09:18:13','2016-09-07 09:18:13');
insert into deity (id, name, description, alignmentId, symbol, image, version, created, updated) values (27,'Talona','Déesse de la maladie et du poison',9,'3 larmes sur un triangle',NULL,1,'2016-09-07 09:20:32','2016-09-07 09:20:32');
insert into deity (id, name, description, alignmentId, symbol, image, version, created, updated) values (28,'Talos','Dieu des tempêtes',9,'3 éclairs partant du même point',NULL,1,'2016-09-07 09:21:54','2016-09-07 09:21:54');
insert into deity (id, name, description, alignmentId, symbol, image, version, created, updated) values (29,'Tempus','Dieu de la guerre',5,'Épée emflammée',NULL,1,'2016-09-07 09:22:38','2016-09-07 09:22:38');
insert into deity (id, name, description, alignmentId, symbol, image, version, created, updated) values (30,'Torm','Dieu du devoir et de la loyauté',1,'Gantelet droit',NULL,1,'2016-09-07 09:26:18','2016-09-07 09:26:18');
insert into deity (id, name, description, alignmentId, symbol, image, version, created, updated) values (31,'Tymora','Déesse de la chance et de la bonne fortune',3,'Pièce de monnaie coté face',NULL,1,'2016-09-07 09:27:18','2016-09-07 09:27:18');
insert into deity (id, name, description, alignmentId, symbol, image, version, created, updated) values (32,'Tyr','Dieu de la justice',1,'Balance reposant sur un marteau de guerre',NULL,1,'2016-09-07 09:31:40','2016-09-07 09:31:40');
insert into deity (id, name, description, alignmentId, symbol, image, version, created, updated) values (33,'Umberlee','Déesse des mers',9,'Vague courbant à gauche et à droite',NULL,1,'2016-09-07 09:32:33','2016-09-07 09:32:33');
insert into deity (id, name, description, alignmentId, symbol, image, version, created, updated) values (34,'Waukeen','Déesse du commerce',5,'Pièce de monnaie avec le profil de Waukeen regardant à gauche',NULL,1,'2016-09-07 09:33:58','2016-09-07 09:33:58');
INSERT INTO deity (id, name, description, alignmentId, symbol, image, version, created, updated) VALUES (35,'Abbathor','Dieu nain de la cupidité',8,'Dague incrustée de joyaux',NULL,2,'2016-09-07 11:11:09','2016-09-07 11:16:31');
INSERT INTO deity (id, name, description, alignmentId, symbol, image, version, created, updated) VALUES (36,'Berronar Purargent','Déesse naine de la sécurité, vérité, foyer et soin.',1,'2 anneaux d''argent entremêlés',NULL,2,'2016-09-07 11:19:56','2016-09-07 13:26:02');
INSERT INTO deity (id, name, description, alignmentId, symbol, image, version, created, updated) VALUES (37,'Clanggedin Barbedargent','Dieu nain de la bataille',1,'2 haches d''arme croisées',NULL,2,'2016-09-07 11:21:29','2016-09-07 13:24:31');
INSERT INTO deity (id, name, description, alignmentId, symbol, image, version, created, updated) VALUES (38,'Dumathoin','Dieu nain de l''exploration minière connu sous le nom de Gardien des secrets sous la montagne.',5,'Une gemme dans une montagne',NULL,1,'2016-09-07 11:26:35','2016-09-07 11:26:35');
INSERT INTO deity (id, name, description, alignmentId, symbol, image, version, created, updated) VALUES (39,'Moradin','Dieu nain de la création dit le Forgeur d''âme',1,'Une flamme sur une enclume',NULL,2,'2016-09-07 11:29:52','2016-09-07 13:24:52');
INSERT INTO deity (id, name, description, alignmentId, symbol, image, version, created, updated) VALUES (40,'Sharindlar','Déesse naine des soins, de la pitié, de l''amour et de la fertilité',3,'Cercle avec une cuillère en feu',NULL,1,'2016-09-07 11:33:28','2016-09-07 11:33:28');
INSERT INTO deity (id, name, description, alignmentId, symbol, image, version, created, updated) VALUES (41,'Corellon Larethian','Dieu elfe de l''art et de la magie',3,'Étoile à 8 branches',NULL,1,'2016-09-07 11:36:43','2016-09-07 11:36:43');
INSERT INTO deity (id, name, description, alignmentId, symbol, image, version, created, updated) VALUES (42,'Sashelas des Profondeurs','Dieu elfe de la mer',3,'Dauphin',NULL,2,'2016-09-07 11:37:27','2016-09-07 13:28:40');
INSERT INTO deity (id, name, description, alignmentId, symbol, image, version, created, updated) VALUES (43,'Angharradh','Déesse elfe du printemps, fertilité et sagesse',3,'3 cercles croisés dans un triangle vers le bas',NULL,1,'2016-09-07 11:43:14','2016-09-07 11:43:14');
INSERT INTO deity (id, name, description, alignmentId, symbol, image, version, created, updated) VALUES (44,'Garl Brilledor','Dieu gnome de la ruse, de l''humour et protection dit le Farceur',1,'Pépite d''or',NULL,3,'2016-09-07 11:45:53','2016-09-07 13:20:58');
INSERT INTO deity (id, name, description, alignmentId, symbol, image, version, created, updated) VALUES (45,'Baervan Ermiterrant','Dieu gnome des forêts, des voyages et de la nature dit la Feuille Masquée',2,'Tête de raton laveur',NULL,3,'2016-09-07 13:16:09','2016-09-07 13:21:30');
INSERT INTO deity (id, name, description, alignmentId, symbol, image, version, created, updated) VALUES (46,'Callarduran Doucemains','Dieu gnome de la terre et de la roche dit Frère des Profondeurs. Il est le patron des svirfneblins.',5,'Anneau doré orné d''un rubis étoilé',NULL,2,'2016-09-07 13:18:52','2016-09-07 13:22:28');
INSERT INTO deity (id, name, description, alignmentId, symbol, image, version, created, updated) VALUES (47,'Flandal Peaudacier','Dieu gnome des artisans, mineurs et forgerons.',2,'Maillet de forgeron enflammé',NULL,2,'2016-09-07 13:23:57','2016-09-07 13:27:27');
INSERT INTO deity (id, name, description, alignmentId, symbol, image, version, created, updated) VALUES (48,'Vergadain','Dieu nain de la chance et de la richesse, le patron des marchands.',5,'Pièce d''or',NULL,1,'2016-09-07 13:26:53','2016-09-07 13:26:53');
INSERT INTO deity (id, name, description, alignmentId, symbol, image, version, created, updated) VALUES (49,'Segojan Hanteterre','Dieu gnome de la terre et de la nature',2,'Gemme luisante en permanence de mille feux',NULL,1,'2016-09-07 13:31:40','2016-09-07 13:31:40');
INSERT INTO deity (id, name, description, alignmentId, symbol, image, version, created, updated) VALUES (50,'Urdlen','Dieu gnome de la cupidité et du sang',9,'Taupe blanche albinos',NULL,1,'2016-09-07 13:34:13','2016-09-07 13:34:13');
INSERT INTO deity (id, name, description, alignmentId, symbol, image, version, created, updated) VALUES (51,'Gruumsh','Dieu orque de la création surnommé surnommé N''a-qu''un-œil, Celui-qui-ne-dort-jamais ou Celui-qui-observe.',9,'Un oeil sans paupière',NULL,1,'2016-09-07 13:37:57','2016-09-07 13:37:57');
INSERT INTO deity (id, name, description, alignmentId, symbol, image, version, created, updated) VALUES (52,'Lloth','Déesse drow du chaos dite la Reine Araignée',9,'Araignée',NULL,1,'2016-09-07 13:41:31','2016-09-07 13:41:31');
INSERT INTO deity (id, name, description, alignmentId, symbol, image, version, created, updated) VALUES (53,'Eilistraée','Déesse du chant, la chasse, la lune et la beauté.',3,'Une épée décorée d''un croissant',NULL,1,'2016-09-07 13:45:06','2016-09-07 13:45:06');
INSERT INTO deity (id, name, description, alignmentId, symbol, image, version, created, updated) VALUES (54,'Ghaunadaur','Dieu drow des abominations, gelées, bannis et rebelles dit Celui qui Rôde',9,'Un oeil qui observe',NULL,1,'2016-09-07 13:49:21','2016-09-07 13:49:21');
INSERT INTO deity (id, name, description, alignmentId, symbol, image, version, created, updated) VALUES (55,'Vhaeraun','Dieu drow du chaos, le vol, la duperie et le mal. dit le Seigneur Masqué.',9,'Un masque',NULL,1,'2016-09-07 13:51:46','2016-09-07 13:51:46');
INSERT INTO deity (id, name, description, alignmentId, symbol, image, version, created, updated) VALUES (56,'Yondalla','Déesse halfeline de la création, de la fertilité et protection',1,'Bouclier',NULL,1,'2016-09-07 13:54:17','2016-09-07 13:54:17');
INSERT INTO deity (id, name, description, alignmentId, symbol, image, version, created, updated) VALUES (57,'Arvoreen','Dieu halfelin de la protection, vigilance et de la guerre dit le Défenseur.',1,'2 épées croisées',NULL,1,'2016-09-07 13:56:42','2016-09-07 13:56:42');
INSERT INTO deity (id, name, description, alignmentId, symbol, image, version, created, updated) VALUES (58,'Cyrrollalee','Déesse de l''amitié, confiance et de l''hospitalité dite la Main de Camaraderie.',1,'Une porte ouverte',NULL,1,'2016-09-07 13:59:10','2016-09-07 13:59:10');
INSERT INTO deity (id, name, description, alignmentId, symbol, image, version, created, updated) VALUES (59,'Sheela Peryroyl','Déesse halfeline de la nature et de l''agriculture dit la Soeur Verte. ',5,'Marguerite',NULL,1,'2016-09-07 14:01:38','2016-09-07 14:01:38');

/* DeityDomain */
INSERT INTO deitydomain (deityId, domainId) VALUES (2,1);
INSERT INTO deitydomain (deityId, domainId) VALUES (8,1);
INSERT INTO deitydomain (deityId, domainId) VALUES (10,1);
INSERT INTO deitydomain (deityId, domainId) VALUES (21,1);
INSERT INTO deitydomain (deityId, domainId) VALUES (22,1);
INSERT INTO deitydomain (deityId, domainId) VALUES (23,1);
INSERT INTO deitydomain (deityId, domainId) VALUES (34,1);
INSERT INTO deitydomain (deityId, domainId) VALUES (38,1);
INSERT INTO deitydomain (deityId, domainId) VALUES (39,1);
INSERT INTO deitydomain (deityId, domainId) VALUES (46,1);
INSERT INTO deitydomain (deityId, domainId) VALUES (47,1);
INSERT INTO deitydomain (deityId, domainId) VALUES (6,2);
INSERT INTO deitydomain (deityId, domainId) VALUES (9,2);
INSERT INTO deitydomain (deityId, domainId) VALUES (11,2);
INSERT INTO deitydomain (deityId, domainId) VALUES (12,2);
INSERT INTO deitydomain (deityId, domainId) VALUES (14,2);
INSERT INTO deitydomain (deityId, domainId) VALUES (23,2);
INSERT INTO deitydomain (deityId, domainId) VALUES (26,2);
INSERT INTO deitydomain (deityId, domainId) VALUES (36,2);
INSERT INTO deitydomain (deityId, domainId) VALUES (40,2);
INSERT INTO deitydomain (deityId, domainId) VALUES (43,2);
INSERT INTO deitydomain (deityId, domainId) VALUES (53,2);
INSERT INTO deitydomain (deityId, domainId) VALUES (56,2);
INSERT INTO deitydomain (deityId, domainId) VALUES (58,2);
INSERT INTO deitydomain (deityId, domainId) VALUES (11,3);
INSERT INTO deitydomain (deityId, domainId) VALUES (14,3);
INSERT INTO deitydomain (deityId, domainId) VALUES (26,3);
INSERT INTO deitydomain (deityId, domainId) VALUES (41,3);
INSERT INTO deitydomain (deityId, domainId) VALUES (58,3);
INSERT INTO deitydomain (deityId, domainId) VALUES (1,4);
INSERT INTO deitydomain (deityId, domainId) VALUES (9,4);
INSERT INTO deitydomain (deityId, domainId) VALUES (17,4);
INSERT INTO deitydomain (deityId, domainId) VALUES (18,4);
INSERT INTO deitydomain (deityId, domainId) VALUES (25,4);
INSERT INTO deitydomain (deityId, domainId) VALUES (42,4);
INSERT INTO deitydomain (deityId, domainId) VALUES (43,4);
INSERT INTO deitydomain (deityId, domainId) VALUES (45,4);
INSERT INTO deitydomain (deityId, domainId) VALUES (46,4);
INSERT INTO deitydomain (deityId, domainId) VALUES (49,4);
INSERT INTO deitydomain (deityId, domainId) VALUES (59,4);
INSERT INTO deitydomain (deityId, domainId) VALUES (1,5);
INSERT INTO deitydomain (deityId, domainId) VALUES (28,5);
INSERT INTO deitydomain (deityId, domainId) VALUES (33,5);
INSERT INTO deitydomain (deityId, domainId) VALUES (42,5);
INSERT INTO deitydomain (deityId, domainId) VALUES (51,5);
INSERT INTO deitydomain (deityId, domainId) VALUES (4,6);
INSERT INTO deitydomain (deityId, domainId) VALUES (7,6);
INSERT INTO deitydomain (deityId, domainId) VALUES (15,6);
INSERT INTO deitydomain (deityId, domainId) VALUES (20,6);
INSERT INTO deitydomain (deityId, domainId) VALUES (24,6);
INSERT INTO deitydomain (deityId, domainId) VALUES (31,6);
INSERT INTO deitydomain (deityId, domainId) VALUES (34,6);
INSERT INTO deitydomain (deityId, domainId) VALUES (35,6);
INSERT INTO deitydomain (deityId, domainId) VALUES (44,6);
INSERT INTO deitydomain (deityId, domainId) VALUES (48,6);
INSERT INTO deitydomain (deityId, domainId) VALUES (52,6);
INSERT INTO deitydomain (deityId, domainId) VALUES (55,6);
INSERT INTO deitydomain (deityId, domainId) VALUES (3,7);
INSERT INTO deitydomain (deityId, domainId) VALUES (29,7);
INSERT INTO deitydomain (deityId, domainId) VALUES (30,7);
INSERT INTO deitydomain (deityId, domainId) VALUES (32,7);
INSERT INTO deitydomain (deityId, domainId) VALUES (37,7);
INSERT INTO deitydomain (deityId, domainId) VALUES (50,7);
INSERT INTO deitydomain (deityId, domainId) VALUES (51,7);
INSERT INTO deitydomain (deityId, domainId) VALUES (57,7);
INSERT INTO deitydomain (deityId, domainId) VALUES (5,8);
INSERT INTO deitydomain (deityId, domainId) VALUES (13,8);
INSERT INTO deitydomain (deityId, domainId) VALUES (16,8);
INSERT INTO deitydomain (deityId, domainId) VALUES (19,8);
INSERT INTO deitydomain (deityId, domainId) VALUES (24,8);
INSERT INTO deitydomain (deityId, domainId) VALUES (27,8);
INSERT INTO deitydomain (deityId, domainId) VALUES (49,8);
INSERT INTO deitydomain (deityId, domainId) VALUES (54,8);

/* ArmorType */
INSERT INTO armortype (id, name, description, proficiencyType, maxDexBonus, baseArmorClass, stealthDisavantage, minStrength, baseWeight, basePrice, version, created, updated) VALUES (1,'Matelassé','L''armure matelassée se compose de couches matelassées de tissu et ouate.','LIGHT',-1,1,1,1,8,5,3,'2016-09-09 08:54:20','2016-09-09 08:59:28');
INSERT INTO armortype (id, name, description, proficiencyType, maxDexBonus, baseArmorClass, stealthDisavantage, minStrength, baseWeight, basePrice, version, created, updated) VALUES (2,'Cuir','Le plastron et les épaules protecteurs de cette armure sont faits de cuir qui a été solidifié en étant bouilli dans de l''huile. Le reste de l''armure est constituée de matériaux plus doux et plus souples.','LIGHT',-1,1,0,1,10,10,3,'2016-09-09 08:54:20','2016-09-09 09:18:38');
INSERT INTO armortype (id, name, description, proficiencyType, maxDexBonus, baseArmorClass, stealthDisavantage, minStrength, baseWeight, basePrice, version, created, updated) VALUES (3,'Clouté','Fabriqué à partir de cuir solide mais flexible, l''armure de cuir clouté est renforcée avec des rivets ou des crampons.','LIGHT',-1,2,0,1,13,45,3,'2016-09-09 08:54:20','2016-09-09 09:20:24');
INSERT INTO armortype (id, name, description, proficiencyType, maxDexBonus, baseArmorClass, stealthDisavantage, minStrength, baseWeight, basePrice, version, created, updated) VALUES (4,'Peau','Cette armure brute se compose de fourrures épaisses et de peaux. Il est communément porté par les tribus barbares , les humanoïdes mauvais et d''autres gens qui n''ont pas accès aux outils et les matériaux nécessaires pour créer une meilleure armure.','MEDIUM',2,2,0,1,12,10,3,'2016-09-09 08:54:20','2016-09-09 09:21:48');
INSERT INTO armortype (id, name, description, proficiencyType, maxDexBonus, baseArmorClass, stealthDisavantage, minStrength, baseWeight, basePrice, version, created, updated) VALUES (5,'Chemise de cote de maille','Fait d''anneaux métalliques entrelacées, une chemise de cotte de maille est portée entre les couches de vêtements ou de cuir . Cette armure offre une protection modeste supérieure au porteur et permet au son des mailles d''être étouffé par les couches extérieures.','MEDIUM',2,3,0,1,20,50,3,'2016-09-09 08:54:20','2016-09-09 09:24:36');
INSERT INTO armortype (id, name, description, proficiencyType, maxDexBonus, baseArmorClass, stealthDisavantage, minStrength, baseWeight, basePrice, version, created, updated) VALUES (6,'Feuilleté','Cette armure se compose d''un manteau et des jambières de cuir recouvert de chevauchement des morceaux de métal, un peu comme les écailles d''un poisson. Le costume comprend gantelets.','MEDIUM',2,4,1,1,45,50,3,'2016-09-09 08:54:20','2016-09-09 09:26:38');
INSERT INTO armortype (id, name, description, proficiencyType, maxDexBonus, baseArmorClass, stealthDisavantage, minStrength, baseWeight, basePrice, version, created, updated) VALUES (7,'Plastron de plate','Cette armure est constituée d''un métal ajusté à la poitrine porté avec cuir souple . Bien qu''elle laisse les jambes et les bras relativement non protégés, cette armure fournit une bonne protection pour les organes vitaux tout en laissant le porteur relativement non-encombré.','MEDIUM',2,4,0,1,20,400,3,'2016-09-09 08:54:20','2016-09-09 09:28:53');
INSERT INTO armortype (id, name, description, proficiencyType, maxDexBonus, baseArmorClass, stealthDisavantage, minStrength, baseWeight, basePrice, version, created, updated) VALUES (8,'Demie-plate','La demie-plate est constituée de plaques métalliques profilées qui couvrent la majeure partie du corps de l''utilisateur . Il ne comprend pas de protection aux jambes sauf des jambières simples qui sont attachées\navec des lanières de cuir .','MEDIUM',2,5,1,1,40,750,3,'2016-09-09 08:54:20','2016-09-09 09:31:53');
INSERT INTO armortype (id, name, description, proficiencyType, maxDexBonus, baseArmorClass, stealthDisavantage, minStrength, baseWeight, basePrice, version, created, updated) VALUES (9,'Annelé','Cette armure est une armure de cuir avec de lourdes anneaux cousus en elle. Les anneaux contribuent à renforcer l''armure contre les coups de sabres et des haches. L''armure annelée est inférieure à la cotte de maille et est généralement portée que par ceux qui ne peuvent pas se permettre une meilleure armure.','HEAVY',0,4,1,1,40,30,3,'2016-09-09 08:54:20','2016-09-09 09:34:13');
INSERT INTO armortype (id, name, description, proficiencyType, maxDexBonus, baseArmorClass, stealthDisavantage, minStrength, baseWeight, basePrice, version, created, updated) VALUES (10,'Cote de maille','Fait d''anneaux métalliques entrelacés , la cotte de maille contient une couche de tissu matelassé portée sous\nles mailles pour éviter les frottements et à amortir l''impact des coups . Le costume comprend gantelets.','HEAVY',0,6,1,13,55,75,3,'2016-09-09 08:54:20','2016-09-09 09:36:24');
INSERT INTO armortype (id, name, description, proficiencyType, maxDexBonus, baseArmorClass, stealthDisavantage, minStrength, baseWeight, basePrice, version, created, updated) VALUES (11,'Éclisse','Cette armure est faite de bandes verticales étroites de métal riveté sur un support de cuir qui est porté sur un\nrembourrage en tissu. Une cotte de mailles flexible protège les articulations.','HEAVY',0,7,1,15,60,200,3,'2016-09-09 08:54:20','2016-09-09 09:37:14');
INSERT INTO armortype (id, name, description, proficiencyType, maxDexBonus, baseArmorClass, stealthDisavantage, minStrength, baseWeight, basePrice, version, created, updated) VALUES (12,'Plate complète','L''armure de plate complète est formée de plaques de métal entremêlées pour couvrir l''ensemble du corps. Une armure de plate comprend gantelets, des bottes en cuir épais, un casque à visière et une épaisse couche de rembourrage sous l''armure . Boucles et sangles distribuent le poids sur le corps.','HEAVY',0,8,1,15,65,1500,3,'2016-09-09 08:54:20','2016-09-09 09:40:28');
INSERT INTO armortype (id, name, description, proficiencyType, maxDexBonus, baseArmorClass, stealthDisavantage, minStrength, baseWeight, basePrice, version, created, updated) VALUES (13,'Bouclier','Un bouclier est fabriqué à partir de bois ou de métal et est utilisé avec une main. Porter un bouclier augmente votre classe d''armure de 2. Vous pouvez bénéficier d''un seul bouclier à la fois.','SHIELD',0,2,0,1,6,10,3,'2016-09-09 08:54:20','2016-09-09 09:42:17');

/* Race */
insert into race (id, name, description, traits, strModifier, dexModifier, conModifier, intModifier, wisModifier, chaModifier, minAge, maxAge, size, averageHeight, averageWeight, speed, extraLanguage, damageTypeId, version, created, updated) values (1,'Nain d''écu','Intrépide et robuste, les nains sont connus pour être de talentueux guerriers, mineurs et travailleurs de la pierre et du métal. Malgré leur 5 pieds de haut, les nains sont si musclés et compact qu''ils pèsent autant qu''un humain près de 2 pieds plus grand. Les Nains d''écu ont les sens aiguisés, une bonne intuition et une remarquable résistance.','Vision dans le noir : peut voir à 60 pieds dans la lumière faible comme en plain jour et dans la noirceur comme dans la lumière faible\nSolidité des nains : 1 point de vie supplémentaire à chaque niveau',0,0,2,0,1,0,50,400,'MEDIUM','4''6\"',150,25,0,9,1, now(), now());
insert into race (id, name, description, traits, strModifier, dexModifier, conModifier, intModifier, wisModifier, chaModifier, minAge, maxAge, size, averageHeight, averageWeight, speed, extraLanguage, damageTypeId, version, created, updated) values (2,'Nain doré','Intrépide et robuste, les nains sont connus pour être de talentueux guerriers, mineurs et travailleurs de la pierre et du métal. Malgré leur 5 pieds de haut, les nains sont si musclés et compact qu''ils pèsent autant qu''un humain près de 2 pieds plus grand. Les nains dorés sont forts et accoutumés à la vie difficile des montagnes. Ils ont la peau un peu plus clair que les nains d''écu.','Vision dans le noir : peut voir à 60 pieds dans la lumière faible comme en plain jour et dans la noirceur comme dans la lumière faible',2,0,2,0,0,0,50,400,'MEDIUM','4''',150,25,0,9,2,'2016-09-04 11:34:38','2016-09-04 11:34:58');
insert into race (id, name, description, traits, strModifier, dexModifier, conModifier, intModifier, wisModifier, chaModifier, minAge, maxAge, size, averageHeight, averageWeight, speed, extraLanguage, damageTypeId, version, created, updated) values (3,'Elfe du soleil','Les elfes sont un peuple magique et d''une grande grâce. Ils vivent dans dans des endroits d''une grande beauté des anciennes forêts ou on peut entendre un douce musique. Les elfes aiment la nature, la magie, les arts, la musique, la poésie et les bonnes choses du monde. Ils sont plus petits que les humains en général pesant entre 100 et 145 lbs. Les elfes du soleil peuvent vivre passé 700 ans leur donnant une grande curiosité sur le monde qui les entourent plutôt que l''avarice. Leur peau a une teinte bronzée, et leur cheveux peuvent être cuivrés, noirs ou blonds. Ils ont un esprit aiguisé et sont maîtres des bases de la magie.','Vision dans le noir : peut voir à 60 pieds dans la lumière faible comme en plain jour et dans la noirceur comme dans la lumière faible.\nDextérité +2, Intelligence +1\nCompétence avec le talent Perception\nAvantage de jet de sauvegarde contre les charmes et le sommeil.',0,2,0,1,0,0,100,750,'MEDIUM','5''3\"',0,30,0,NULL,2,'2016-09-04 11:51:31','2016-09-04 11:53:23');
insert into race (id, name, description, traits, strModifier, dexModifier, conModifier, intModifier, wisModifier, chaModifier, minAge, maxAge, size, averageHeight, averageWeight, speed, extraLanguage, damageTypeId, version, created, updated) values (4,'Elfe vert','Les elfes vivent dans leur forêt natale, plus reculés de la société. Ils sont plus petits que les humains en général pesant entre 100 et 145 lbs. Les elfes verts peuvent vivre passé 700 ans leur donnant une grande curiosité sur le monde qui les entourent plutôt que l''avarice. Leur peau est cuivrée avec des teintes de vert et leur cheveux peuvent être bruns ou noirs. Ils ont les sens aiguisés, le pied léger et silencieux.','Vision dans le noir : peut voir à 60 pieds dans la lumière faible comme en plain jour et dans la noirceur comme dans la lumière faible.\nDextérité +2, Sagesse+1\nCompétence avec le talent Perception\nAvantage de jet de sauvegarde contre les charmes et le sommeil.\nPeut se cacher dans la nature même quand obstrué partiellement.',0,2,0,0,1,0,0,0,'MEDIUM','5''3\"',125,35,0,NULL,2,'2016-09-04 12:22:13','2016-09-04 12:23:57');

/* RaceArmorProficiency */
insert into racearmorproficiencies (raceId, proficiency) values (2,'MEDIUM');
insert into racearmorproficiencies (raceId, proficiency) values (2,'LIGHT');

/* RaceLanguage */
insert into racelanguage (raceId, languageId) values (1,1);
insert into racelanguage (raceId, languageId) values (2,1);
insert into racelanguage (raceId, languageId) values (3,1);
insert into racelanguage (raceId, languageId) values (4,1);
insert into racelanguage (raceId, languageId) values (1,2);
insert into racelanguage (raceId, languageId) values (2,2);
insert into racelanguage (raceId, languageId) values (3,3);
insert into racelanguage (raceId, languageId) values (4,3);

/* RaceSavingThrowProficiency */
insert into racesavingthrowproficiencies (raceId, conditionName) values (1,'POISONED');
insert into racesavingthrowproficiencies (raceId, conditionName) values (2,'POISONED');
insert into racesavingthrowproficiencies (raceId, conditionName) values (3,'CHARMED');
insert into racesavingthrowproficiencies (raceId, conditionName) values (3,'SLEEP');
insert into racesavingthrowproficiencies (raceId, conditionName) values (4,'CHARMED');
insert into racesavingthrowproficiencies (raceId, conditionName) values (4,'SLEEP');

/* RaceSkillProficiency */
insert into raceskillproficiencies (classId, skillId) values (1,6);
insert into raceskillproficiencies (classId, skillId) values (2,6);
insert into raceskillproficiencies (classId, skillId) values (3,13);
insert into raceskillproficiencies (classId, skillId) values (4,13);