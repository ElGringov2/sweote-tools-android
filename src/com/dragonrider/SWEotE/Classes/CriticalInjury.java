package com.dragonrider.SWEotE.Classes;

public class CriticalInjury
{


    public int Id ;



    public CriticalInjury(CriticalIDs Id) {

        this.Id = Id.ordinal();


    }

    @Override
    public String toString()
    {
        return getCriticalNameByID(Id) + " (" + getDescriptionById(Id) + ")";
    }


    public enum CriticalIDs
    {
        petite_coupure,
        ralenti,
        secoue,
        distrait,
        desequilibre,
        blessure_decourageante,
        etourdi,
        ca_pique,
        renverse,
        sonne,
        blessure_impressionnante,
        blessure_angoissante,
        legerement_etourdi,
        sens_satures,
        paralyse,
        domine,
        essoufle,
        compromis,
        au_bord_du_goufre,
        estropie,
        mutile,
        blessure_terrible,
        boiteux,
        aveugle,
        assomme,
        blessure_macabre,
        saigne,
        la_fin_est_proche,
        mort,

    }





    public static CriticalIDs ID (int CriticalScore)
    {

            if (CriticalScore >= 1 && CriticalScore <= 5) return CriticalIDs.petite_coupure;
            if (CriticalScore >= 6 && CriticalScore <= 10) return CriticalIDs.ralenti;
            if (CriticalScore >= 11 && CriticalScore <= 15) return CriticalIDs.secoue;
            if (CriticalScore >= 16 && CriticalScore <= 20) return CriticalIDs.distrait;
            if (CriticalScore >= 21 && CriticalScore <= 25) return CriticalIDs.desequilibre;
            if (CriticalScore >= 26 && CriticalScore <= 30) return CriticalIDs.blessure_decourageante;
            if (CriticalScore >= 31 && CriticalScore <= 35) return CriticalIDs.etourdi;
            if (CriticalScore >= 36 && CriticalScore <= 40) return CriticalIDs.ca_pique;
            if (CriticalScore >= 41 && CriticalScore <= 45) return CriticalIDs.renverse;
            if (CriticalScore >= 46 && CriticalScore <= 50) return CriticalIDs.sonne;
            if (CriticalScore >= 51 && CriticalScore <= 55) return CriticalIDs.blessure_impressionnante;
            if (CriticalScore >= 56 && CriticalScore <= 60) return CriticalIDs.blessure_angoissante;
            if (CriticalScore >= 61 && CriticalScore <= 65) return CriticalIDs.legerement_etourdi;
            if (CriticalScore >= 66 && CriticalScore <= 70) return CriticalIDs.sens_satures;
            if (CriticalScore >= 71 && CriticalScore <= 75) return CriticalIDs.paralyse;
            if (CriticalScore >= 76 && CriticalScore <= 80) return CriticalIDs.domine;
            if (CriticalScore >= 81 && CriticalScore <= 85) return CriticalIDs.essoufle;
            if (CriticalScore >= 86 && CriticalScore <= 90) return CriticalIDs.compromis;
            if (CriticalScore >= 91 && CriticalScore <= 95) return CriticalIDs.au_bord_du_goufre;
            if (CriticalScore >= 96 && CriticalScore <= 100) return CriticalIDs.estropie;
            if (CriticalScore >= 101 && CriticalScore <= 105) return CriticalIDs.mutile;
            if (CriticalScore >= 106 && CriticalScore <= 110) return CriticalIDs.blessure_terrible;
            if (CriticalScore >= 111 && CriticalScore <= 115) return CriticalIDs.boiteux;
            if (CriticalScore >= 116 && CriticalScore <= 120) return CriticalIDs.aveugle;
            if (CriticalScore >= 121 && CriticalScore <= 125) return CriticalIDs.assomme;
            if (CriticalScore >= 126 && CriticalScore <= 130) return CriticalIDs.blessure_macabre;
            if (CriticalScore >= 131 && CriticalScore <= 135) return CriticalIDs.saigne;
            if (CriticalScore >= 136 && CriticalScore <= 140) return CriticalIDs.saigne;
            if (CriticalScore >= 141 && CriticalScore <= 145) return CriticalIDs.la_fin_est_proche;
            if (CriticalScore >= 146 && CriticalScore <= 150) return CriticalIDs.la_fin_est_proche;

            return CriticalIDs.mort;


    }

    public static String getCriticalNameByID(int id) {
        if (id == 0) return "Petite coupure";
        if (id == 1) return "Ralenti";
        if (id == 2) return "Secoué";
        if (id == 3) return "Distrait";
        if (id == 4) return "Déséquilibré";
        if (id == 5) return "Blessure décourageante";
        if (id == 6) return "Etourdi";
        if (id == 7) return "Ca pique";
        if (id == 8) return "Renversé";
        if (id == 9) return "Sonné";
        if (id == 10) return "Blessure impressionnante";
        if (id == 11) return "Blessure angoissante";
        if (id == 12) return "Légèrement étourdi";
        if (id == 13) return "Sens saturés";
        if (id == 14) return "Paralysé";
        if (id == 15) return "Dominé";
        if (id == 16) return "Essouflé";
        if (id == 17) return "Compromis";
        if (id == 18) return "Au bord du gouffre";
        if (id == 19) return "Estropié";
        if (id == 20) return "Mutilé";
        if (id == 21) return "Blessure terrible";
        if (id == 22) return "Boiteux";
        if (id == 23) return "Aveuglé";
        if (id == 24) return "Assommé";
        if (id == 25) return "Blessure macabre";
        if (id == 26) return "Saigné";
        if (id == 27) return "La fin est proche";
        if (id == 28) return "Mort";

        return "";
    }


    public static String getCriticalName(int CriticalScore)
    {


            if (CriticalScore >= 1 && CriticalScore <= 5) return "Petite coupure";
            if (CriticalScore >= 6 && CriticalScore <= 10) return "Ralenti";
            if (CriticalScore >= 11 && CriticalScore <= 15) return "Secoué";
            if (CriticalScore >= 16 && CriticalScore <= 20) return "Distrait";
            if (CriticalScore >= 21 && CriticalScore <= 25) return "Déséquilibré";
            if (CriticalScore >= 26 && CriticalScore <= 30) return "Blessure décourageante";
            if (CriticalScore >= 31 && CriticalScore <= 35) return "Etourdi";
            if (CriticalScore >= 36 && CriticalScore <= 40) return "Ca pique";
            if (CriticalScore >= 41 && CriticalScore <= 45) return "Renversé";
            if (CriticalScore >= 46 && CriticalScore <= 50) return "Sonné";
            if (CriticalScore >= 51 && CriticalScore <= 55) return "Blessure impressionnante";
            if (CriticalScore >= 56 && CriticalScore <= 60) return "Blessure angoissante";
            if (CriticalScore >= 61 && CriticalScore <= 65) return "Légèrement étourdi";
            if (CriticalScore >= 66 && CriticalScore <= 70) return "Sens saturés";
            if (CriticalScore >= 71 && CriticalScore <= 75) return "Paralysé";
            if (CriticalScore >= 76 && CriticalScore <= 80) return "Dominé";
            if (CriticalScore >= 81 && CriticalScore <= 85) return "Essouflé";
            if (CriticalScore >= 86 && CriticalScore <= 90) return "Compromis";
            if (CriticalScore >= 91 && CriticalScore <= 95) return "Au bord du gouffre";
            if (CriticalScore >= 96 && CriticalScore <= 100) return "Estropié";
            if (CriticalScore >= 101 && CriticalScore <= 105) return "Mutilé";
            if (CriticalScore >= 106 && CriticalScore <= 110) return "Blessure terrible";
            if (CriticalScore >= 111 && CriticalScore <= 115) return "Boiteux";
            if (CriticalScore >= 116 && CriticalScore <= 120) return "Aveuglé";
            if (CriticalScore >= 121 && CriticalScore <= 125) return "Assommé";
            if (CriticalScore >= 126 && CriticalScore <= 130) return "Blessure macabre";
            if (CriticalScore >= 131 && CriticalScore <= 135) return "Saigné";
            if (CriticalScore >= 136 && CriticalScore <= 140) return "Saigné";
            if (CriticalScore >= 141 && CriticalScore <= 145) return "La fin est proche";
            if (CriticalScore >= 146 && CriticalScore <= 150) return "La fin est proche";
            if (CriticalScore >= 151) return "Mort";




            return "";



    }

    public static String getCriticalDescription(int CriticalScore)
    {


            if (CriticalScore >= 1 && CriticalScore <= 5) return "La cible subit un point de stress";
            if (CriticalScore >= 6 && CriticalScore <= 10) return "Lors de son prochain tour, la cible devra agir à la dernière position d'initiative alliée";
            if (CriticalScore >= 11 && CriticalScore <= 15) return "La cible lâche immédiatement ce qu'elle tient en main";
            if (CriticalScore >= 16 && CriticalScore <= 20) return "Pas de manoeuvre gratuite lors du prochain tour";
            if (CriticalScore >= 21 && CriticalScore <= 25) return "Ajouter [S] au prochain jet";
            if (CriticalScore >= 26 && CriticalScore <= 30) return "Retournez un marqueur de Destin vers le coté obscur.";
            if (CriticalScore >= 31 && CriticalScore <= 35) return "La cible est Stupéfaite pendant jusqu'a la fin de son prochain tour";
            if (CriticalScore >= 36 && CriticalScore <= 40) return "Augmentez la difficulté du prochain jet";
            if (CriticalScore >= 41 && CriticalScore <= 45) return "La cible tombe à terre, et subit 1 stress";
            if (CriticalScore >= 46 && CriticalScore <= 50) return "La cible augmente la difficulté de tous les jets d'Intellect et de Ruse jusqu'a la fin de la rencontre";
            if (CriticalScore >= 51 && CriticalScore <= 55) return "La cible augmente la difficulté de tous les jets de Présence et de Volonté jusqu'a la fin de la rencontre";
            if (CriticalScore >= 56 && CriticalScore <= 60) return "La cible augmente la difficulté de tous les jets d'Agilité et de Vigueur jusqu'a la fin de la rencontre";
            if (CriticalScore >= 61 && CriticalScore <= 65) return "La cible est Désorienté jusqu'à la fin de la rencontre";
            if (CriticalScore >= 66 && CriticalScore <= 70) return "La cible enleve tout [B] jusqu'a la fin de la rencontre";
            if (CriticalScore >= 71 && CriticalScore <= 75) return "La cible perd sa manoeuvre gratuite jusqu'à la fin de la rencontre";
            if (CriticalScore >= 76 && CriticalScore <= 80) return "La cible est ouverte, l'attaquant peut immédiatement relancer une attaque en utilisant le même jet de dé";
            if (CriticalScore >= 81 && CriticalScore <= 85) return "La cible ne peux subir volontairement du stress jusqu'a la fin de la recontre";
            if (CriticalScore >= 86 && CriticalScore <= 90) return "Augmentez la difficulté de tous les jets jusqu'a la fin de la recontre";
            if (CriticalScore >= 91 && CriticalScore <= 95) return "La cible subit un point de stress pour chaque action.";
            if (CriticalScore >= 96 && CriticalScore <= 100) return "Un membre de la cible est estropié jusqu'a remplacement/soins.";
            if (CriticalScore >= 101 && CriticalScore <= 105) return "Un membre de la cible est arraché. ";
            if (CriticalScore >= 106 && CriticalScore <= 110) return "Une caractéristique au hasard est réduite d'un point (1-3 Vigueur, 4-6 Agilité, 7 Intellect, 8 Ruse, 9 Présence, 10 Volonté)";
            if (CriticalScore >= 111 && CriticalScore <= 115) return "La cible ne peux plus effectuer plus d'une manoeuvre par tour.";
            if (CriticalScore >= 116 && CriticalScore <= 120) return "La cible est aveugle. Améliorez la difficulté de tous les jets deux fois, trois pour les jets de Perception et de Vigilance.";
            if (CriticalScore >= 121 && CriticalScore <= 125) return "La cible est Stupéfaite jusqu'à la fin de la rencontre";
            if (CriticalScore >= 126 && CriticalScore <= 130) return "Réduisez de manière permanante une caractéristique (1-3 Vigueur, 4-6 Agilité, 7 Intellect, 8 Ruse, 9 Présence, 10 Volonté)";
            if (CriticalScore >= 131 && CriticalScore <= 135) return "Chaque round, la cible gagne une blessure et un stress. Pour chaque tranche de 5 blessures au dessus du seuil de Blessure, la cible gagne une nouvelle blessure critique.";
            if (CriticalScore >= 136 && CriticalScore <= 140) return "Chaque round, la cible gagne une blessure et un stress. Pour chaque tranche de 5 blessures au dessus du seuil de Blessure, la cible gagne une nouvelle blessure critique.";
            if (CriticalScore >= 141 && CriticalScore <= 145) return "La cible meurt a la fin du prochain round.";
            if (CriticalScore >= 146 && CriticalScore <= 150) return "La cible meurt a la fin du prochain round.";
            if (CriticalScore >= 151) return "La cible meurt.";



            return "";

    }


    public static String getDescriptionById(int id) {
        if (id == 0) return "La cible subit un point de stress";
        if (id == 1) return "Lors de son prochain tour, la cible devra agir à la dernière position d'initiative alliée";
        if (id == 2) return "La cible lâche immédiatement ce qu'elle tient en main";
        if (id == 3) return "Pas de manoeuvre gratuite lors du prochain tour";
        if (id == 4) return "Ajouter [S] au prochain jet";
        if (id == 5) return "Retournez un marqueur de Destin vers le coté obscur.";
        if (id == 6) return "La cible est Stupéfaite pendant jusqu'a la fin de son prochain tour";
        if (id == 7) return "Augmentez la difficulté du prochain jet";
        if (id == 8) return "La cible tombe à terre, et subit 1 stress";
        if (id == 9) return "La cible augmente la difficulté de tous les jets d'Intellect et de Ruse jusqu'a la fin de la rencontre";
        if (id == 10) return "La cible augmente la difficulté de tous les jets de Présence et de Volonté jusqu'a la fin de la rencontre";
        if (id == 11) return "La cible augmente la difficulté de tous les jets d'Agilité et de Vigueur jusqu'a la fin de la rencontre";
        if (id == 12) return "La cible est Désorienté jusqu'à la fin de la rencontre";
        if (id == 13) return "La cible enleve tout [B] jusqu'a la fin de la rencontre";
        if (id == 14) return "La cible perd sa manoeuvre gratuite jusqu'à la fin de la rencontre";
        if (id == 15) return "La cible est ouverte, l'attaquant peut immédiatement relancer une attaque en utilisant le même jet de dé";
        if (id == 16) return "La cible ne peux subir volontairement du stress jusqu'a la fin de la recontre";
        if (id == 17) return "Augmentez la difficulté de tous les jets jusqu'a la fin de la recontre";
        if (id == 18) return "La cible subit un point de stress pour chaque action.";
        if (id == 19) return "Un membre de la cible est estropié jusqu'a remplacement/soins.";
        if (id == 20) return "Un membre de la cible est arraché. ";
        if (id == 21) return "Une caractéristique au hasard est réduite d'un point (1-3 Vigueur, 4-6 Agilité, 7 Intellect, 8 Ruse, 9 Présence, 10 Volonté)";
        if (id == 22) return "La cible ne peux plus effectuer plus d'une manoeuvre par tour.";
        if (id == 23) return "La cible est aveugle. Améliorez la difficulté de tous les jets deux fois, trois pour les jets de Perception et de Vigilance.";
        if (id == 24) return "La cible est Stupéfaite jusqu'à la fin de la rencontre";
        if (id == 25) return "Réduisez de manière permanante une caractéristique (1-3 Vigueur, 4-6 Agilité, 7 Intellect, 8 Ruse, 9 Présence, 10 Volonté)";
        if (id == 26) return "Chaque round, la cible gagne une blessure et un stress. Pour chaque tranche de 5 blessures au dessus du seuil de Blessure, la cible gagne une nouvelle blessure critique.";
        if (id == 27) return "La cible meurt a la fin du prochain round.";
        if (id == 28) return "La cible meurt.";

        return "";
    }

    public static int getSeverity(int CriticalScore)
    {

            if (CriticalScore < 41) return 1;
            if (CriticalScore < 91) return 2;
            if (CriticalScore < 126) return 3;
            return 4;


    }
}