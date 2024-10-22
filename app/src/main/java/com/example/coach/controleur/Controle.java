package com.example.coach.controleur;

import com.example.coach.modele.Profil;

/**
 * Classe controleur
 */
public final class Controle {

    /**
     * Instanciation d'un controleur et d'un profil
     */
    private static Controle instance = null;
    private static Profil profil = null;

    /**
     * Constructeur
     */
    private Controle() {
        super();
    }

    /**
     * Singleton
     * @return instance unique de la classe controleur
     */
    public static Controle getInstance() {
        if(Controle.instance == null) {
            Controle.instance = new Controle();
        }
        return Controle.instance;
    }

    /**
     * Création d'un profil
     * @param poids en kg
     * @param taille en cm
     * @param age
     * @param sexe 0 pour une femme, 1 pour un homme
     */
    public void creerProfil(Integer poids, Integer taille, Integer age, Integer sexe) {
        profil = new Profil(poids, taille, age, sexe);
    }

    /**
     * Méthode pour obtenir l'img d'un profil
     * @return img indice de masse graisseuse (%)
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
     * Méthode pour obtenir le message correspondant à l'img
     * @return message affiché à l'écran
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
