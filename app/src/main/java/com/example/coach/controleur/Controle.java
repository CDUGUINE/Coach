package com.example.coach.controleur;

import com.example.coach.modele.Profil;

/**
 * Classe singleton Controle : répond aux attentes de l'activity
 */
public final class Controle {

    // instanciation d'un controleur et d'un profil
    private static Controle instance = null;
    private static Profil profil = null;

    // constructeur
    private Controle() {
        super();
    }

    /**
     * Création d'une instance unique de la classe
     * @return l'instance unique
     */
    public final static Controle getInstance() {
        if(instance == null) {
            instance = new Controle();
        }
        return instance;
    }

    /**
     * Création du profil par rapport aux informations saisies
     * @param poids en kg
     * @param taille en cm
     * @param age en années
     * @param sexe 0 pour une femme, 1 pour un homme
     */
    public void creerProfil(Integer poids, Integer taille, Integer age, Integer sexe) {
        profil = new Profil(poids, taille, age, sexe);
    }

    /**
     * getter sur le résultat du calcul de l'IMG pour le profil
     * @return img du profil
     */
    public float getImg() {
        if(profil != null) {
            return profil.getImg();
        }
        else {
            return 0;
        }
    }

    /**
     * getter sur le message correspondant à l'img du profil
     * @return message du profil
     */
    public String getMessage() {
        if(profil != null) {
            return profil.getMessage();
        }
        else {
            return "";
        }
    }
}
