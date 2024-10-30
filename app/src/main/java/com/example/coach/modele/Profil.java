package com.example.coach.modele;

import android.util.Log;

import com.example.coach.outils.MesOutils;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.Serializable;
import java.util.Date;

/**
 * Classe métier profil
 * contient les informations du profil
 */
public class Profil implements Serializable, Comparable {

    // constantes
    private static final Integer minFemme = 15; // maigre si en dessous
    private static final Integer maxFemme = 30; // gros si au dessus
    private static final Integer minHomme = 10; // maigre si en dessous
    private static final Integer maxHomme = 25; // gros si au dessus

    private Integer poids;
    private Integer taille;
    private Integer age;
    private Integer sexe;

    private Date dateMesure;
    private float img = 0;
    private String message = "";

    /**
     * Constructeur : valorise directement les proriétés poids, taille, age, sexe
     * @param poids en kg
     * @param taille en cm
     * @param age en années
     * @param sexe 0 pour une femme, 1 pour un homme
     */
    public Profil(Date dateMesure, Integer poids, Integer taille, Integer age, Integer sexe) {
        this.dateMesure = dateMesure;
        this.poids = poids;
        this.taille = taille;
        this.age = age;
        this.sexe = sexe;
    }

    public Integer getPoids() {
        return poids;
    }

    public Integer getTaille() {
        return taille;
    }

    public Integer getAge() {
        return age;
    }

    public Integer getSexe() {
        return sexe;
    }

    public Date getDateMesure() {
        return dateMesure;
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
        if(message.equals("")) {
            message = "normal";
            Integer min = minFemme, max = maxFemme;
            if(sexe == 1) {
                min = minHomme;
                max = maxHomme;
            }
            img = getImg();
            if(img < min) {
                message = "trop maigre";
            }else{
                if (img > max) {
                    message = "trop de graisse";
                }
            }
        }
        return message;
    }

    /**
     * conversion du profil au format JSONObject
     * @return
     */
    public JSONObject convertToJSONObject() {
        JSONObject jsonProfil = new JSONObject();
        try {
            jsonProfil.put("datemesure", MesOutils.convertDateToString(dateMesure));
            jsonProfil.put("poids", poids);
            jsonProfil.put("taille", taille);
            jsonProfil.put("age", age);
            jsonProfil.put("sexe", sexe);
        } catch (JSONException e) {
            Log.d("erreur", "************ classe Profil, méthode convertToJSONObject, erreur de conversion");
        }
        return jsonProfil;
    }

    /**
     * Comparaison des profils sur dateMesure
     * @param o
     * @return le résultat de la comparaison
     */
    @Override
    public int compareTo(Object o) {
        return dateMesure.compareTo(((Profil)o).getDateMesure());
    }
}

