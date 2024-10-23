package com.example.coach.modele;

import java.io.Serializable;

/**
 * Classe métier profil
 * contient les informations du profil
 */
public class Profil implements Serializable {

    // constantes
    private static final Integer minFemme = 15; // maigre si en dessous
    private static final Integer maxFemme = 30; // gros si au dessus
    private static final Integer minHomme = 10; // maigre si en dessous
    private static final Integer maxHomme = 25; // gros si au dessus

    // variables
    private Integer poids;
    private Integer taille;
    private Integer age;
    private Integer sexe;
    private float img = 0;
    private String message = "";

    /**
     * Constructeur : valorise directement les proriétés poids, taille, age, sexe
     * @param poids en kg
     * @param taille en cm
     * @param age en années
     * @param sexe 0 pour une femme, 1 pour un homme
     */
    public Profil(Integer poids, Integer taille, Integer age, Integer sexe) {
        this.poids = poids;
        this.taille = taille;
        this.age = age;
        this.sexe = sexe;
    }

    /**
     * retourne le poids du profil
     * @return poids en kg
     */
    public Integer getPoids() {
        return poids;
    }

    /**
     * retourne la taille du profil
     * @return taille en cm
     */
    public Integer getTaille() {
        return taille;
    }

    /**
     * retourne l'âge du profil
     * @return age
     */
    public Integer getAge() {
        return age;
    }

    /**
     * retourne le sexe du profil
     * @return sexe (0 pour une femme, 1 pour un homme)
     */
    public Integer getSexe() {
        return sexe;
    }

    /**
     * Méthode pour obtenir l'img d'un profil
     * @return img du profil
     */
    public float getImg() {
        if(img == 0) {
            float taillem = ((float)taille)/100;
            img = (float)(1.2*poids/(taillem*taillem)+(0.23*age)-(10.83*sexe)-5.4);
        }
        return img;
    }

    /**
     * Méthode pour obtenir le message correspondant à l'img
     * @return message affiché à l'écran
     */
    public String getMessage() {
        if(message == "") {
            message = "normal";
            Integer min = minFemme, max = maxFemme;
            if(sexe == 1) {
                min = minHomme;
                max = maxHomme;
            }
            img = getImg();
            if(img < min) {
                message = "trop maigre";
            } else if (img > max) {
                message = "trop de graisse";
            }
        }
        return message;
    }
}
