package com.dungeonstory.backend.data;

public enum DivineDomainEnum {

    KNOWLEDGE("Connaissance",
            "Les dieux de la connaissance valorisent l'apprentissage et la compr?hension avant tout. Certains enseignent que la connaissance doit ?tre recueillie et partag?e dans les biblioth?ques et les universit?s, ou de promouvoir la connaissance pratique de l'artisanat et de l'invention. Certaines divinit?s amassent des connaissances et gardent leurs secrets pour eux-m?mes. Et certains promettent leurs partisans qu'ils gagneront un pouvoir ?norme s'ils d?couvrent les secrets du multivers. Les adeptes de ces dieux ?tudient la connaissance ?sot?rique, recueillent des anciens tomes, plongent dans le lieux secrets et apprennent tout ce qu'ils peuvent."),
    LIFE("Vie",
            "Le domaine de la vie se concentre sur la dynamique d'?nergie positive, une des forces fondamentales de l'univers, qui soutient toute la vie. Les dieux de la vie favorisent la vitalit? et la sant? gr?ce ? la gu?rison des malades et des bless?s, prendre soin des personnes dans le besoin et chassant les forces de la mort et de la mort-vie. Presque toute divinit? non-mauvaise peut pr?tendre influence sur ce domaine."),
    LIGHT("Lumi?re",
            "Les dieux de la lumi?re promeuvent les id?aux de la renaissance et du renouveau, de la v?rit?, la vigilance et la beaut?, souvent en utilisant le symbole du soleil. Certains de ces dieux sont d?peints comme le soleil lui-m?me ou comme un chariot qui guide le soleil ? travers le ciel. D'autres sont des sentinelles infatigables dont les yeux percent chaque ombre et voient ? travers toutes les tromperies. Certains sont des divinit?s de la beaut? et de l'art, qui enseignent que l'art est un v?hicule pour l'am?lioration de l'?me. Les clercs d'un dieu de la lumi?re sont des ?mes ?clair?es infus?s avec ?clat de la puissance de la vision perspicace de leurs dieux, charg? de chasser les mensonges et br?lant les t?n?bres."),
    NATURE("Nature",
            "Les dieux de la nature sont aussi vari?s que le monde naturel lui-m?me, des dieux imp?n?trables des for?ts profondes aux divinit?s amicales associ?s ? des oasis et des bosquets. Les druides v?n?rent la nature dans son ensemble et pourraient servir une de ces divinit?s, pratiquant des rites myst?rieux et r?citer des pri?res oubli?es dans leur langue secr?te. Mais beaucoup de ces dieux ont des clercs aussi, champions qui prennent un r?le plus actif pour faire avancer les int?r?ts d'un dieu de la nature. Ces clercs chassent les monstruosit?s du mal qui d?pouillent les for?ts, b?nissent la r?colte des fid?les, ou d?p?rissent les cultures de ceux qui engendrent la col?re de leur dieu."),
    TEMPEST("Temp?te",
            "Les dieux de ce domaine r?gissent les temp?tes, la mer et le ciel. Ils comprennent des dieux de la foudre et le tonnerre, des tremblements de terre, du feu, de la violence, de la force physique et du courage. Les dieux du domaine de la temp?te envoient leurs clercs pour inspirer la peur dans les gens du commun, que ce soit pour garder les gens sur le chemin de la justice ou de les encourager ? offrir des sacrifices pour conjurer la col?re divine."),
    TRICKERY("Duperie",
            "Les dieux de la duperie sont corrupteurs et les instigateurs qui se tiennent comme un d?fi constant ? l'ordre accept? parmi les les dieux et les mortels. Ils sont les patrons de voleurs, canailles, joueurs, rebelles et lib?rateurs. Leurs clercs sont une force perturbatrice dans le monde, pr?nant la fiert?, se moquant des tyrans, volant les riches, lib?rant les captifs et m?prisant les traditions. Ils pr?f?rent les subterfuges, farces, la tromperie et le vol plut?t que la confrontation directe."),
    WAR("Guerre",
            "La guerre a de nombreuses manifestations. Elle peut faire des h?ros des gens ordinaires. Elle peut ?tre d?sesp?r?e et terrible, avec actes de cruaut? et de l?chet? ?clipsant les instances d'excellence et courage. Dans les deux cas, les dieux de la guerre veillent sur les guerriers et les r?compensent pour leur grand actes. Les clercs de ces dieux excellent dans la bataille, inspirant les autres pour combattre  ou offrant des actes de violence comme pri?res. Les dieux de la guerre incluent des champions d'honneur et de la chevalerie ainsi que les dieux de la destruction et le pillage et dieux de conqu?te et de domination. D'autres dieux de guerre prennent une position plus neutre, promeuvent la guerre dans toutes ses manifestations et supportent les guerriers dans toute circonstance."),
    DEATH("Mort",
            "Le domaine de la mort est concern? par les forces qui causent la mort et l'?nergie n?gative qui cr?e les cr?atures mortes-vivantes. Les dieux du domaine de la mort sont les patrons des n?cromanciens, liches et vampires. Ils promeuvent la meurtre, la douleur, la maladie et le poison. D'autre dieux sont neutres et consid?rent la mort comme une ?tape ? franchir.");

    private String name;
    private String description;

    private DivineDomainEnum(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return getName();
    }
}
