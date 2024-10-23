package com.example.coach.controleur;

import android.content.Context;

import com.example.coach.modele.Profil;
import com.example.coach.outils.Serializer;

/**
 * Classe singleton Controle : répond aux attentes de l'activity
 */
public final class Controle {

    // instanciation d'un controleur et d'un profil
    private static Controle instance = null;
    private static Profil profil = null;

    // nom du fichier de sauvegarde
    private static String nomFic = "saveprofil";

    // constructeur
    private Controle(Context context) {
        recupSerialize(context);
    }

    /**
     * Création d'une instance unique de la classe
     * @return l'instance unique
     */
    public static final Controle getInstance(Context context) {
        if(instance == null) {
            instance = new Controle(context);
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
    public void creerProfil(Integer poids, Integer taille, Integer age, Integer sexe, Context context) {
        profil = new Profil(poids, taille, age, sexe);
        Serializer.serialize(nomFic, profil, context);
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

    /**
     * récupère un profil
     * @param context
     */
    private static void recupSerialize(Context context) {
        profil = (Profil)Serializer.deSerialize(nomFic, context);
    }

    /**
     * getter sur la taille du profil
     * @return taille du profil
     */
    public Integer getTaille() {
        if(profil != null) {
            return profil.getTaille();
        }
        else {
            return null;
        }
    }

    /**
     * getter sur le poids du profil
     * @return poids du profil
     */
    public Integer getPoids() {
        if(profil != null) {
            return profil.getPoids();
        }
        else {
            return null;
        }
    }

    /**
     * getter sur l'âge du profil
     * @return âge du profil
     */
    public Integer getAge() {
        if(profil != null) {
            return profil.getAge();
        }
        else {
            return null;
        }
    }

    /**
     * getter sur le sexe du profil
     * @return sexe du profil
     */
    public Integer getSexe() {
        if(profil != null) {
            return profil.getSexe();
        }
        else {
            return null;
        }
    }
}
