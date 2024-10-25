package com.example.coach.modele;

import android.content.Context;
import android.util.Log;

import com.example.coach.controleur.Controle;
import com.example.coach.outils.AccesHTTP;
import com.example.coach.outils.AsyncResponse;
import com.example.coach.outils.MesOutils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

/**
 * Classe gérant les demandes d'envoi et de réception par rapport au serveur distant
 * (page PHP qui exploite la BDD au format MySQL)
 */
public class AccesDistant implements AsyncResponse {

    // constantes
    private static final String SERVERAADDR = "http://192.168.56.1/coach/serveurcoach.php";
    private static AccesDistant instance;
    private static Controle controle;

    /**
     * constructeur
     */
    private AccesDistant(){
        controle = Controle.getInstance(null);
    }

    /**
     * Création d'une instance unique de la classe
     * @return l'instance unique
     */
    public static AccesDistant getInstance() {
        if(instance == null) {
            instance = new AccesDistant();
        }
        return instance;
    }

    /**
     * gère le retour asynchone du serveur
     * @param output
     */
    @Override
    public void processFinish(String output) {
        Log.d("serveur", "************" + output);
        try {
            JSONObject retour = new JSONObject(output);
            String code = retour.getString("code");
            String message = retour.getString("message");
            String result = retour.getString("result");
            if(!code.equals("200")){
                Log.d("erreur", "************ retour serveur code="+code+" result="+result);
            }else{
                if(message.equals("dernier")) {
                    JSONObject info = new JSONObject(result);
                    Integer poids = info.getInt("poids");
                    Integer taille = info.getInt("taille");
                    Integer age = info.getInt("age");
                    Integer sexe = info.getInt("sexe");
                    Date dateMesure = MesOutils.convertStringToDate(info.getString("datemesure"), "yyyy-MM-dd hh:mm:ss");
                    Profil profil = new Profil(dateMesure, poids, taille, age, sexe);
                    controle.setProfil(profil);
                }
            }
        } catch (JSONException e) {
            Log.d("erreur", "************ outpout n'est pas au format JSON");
        }
    }

    public void envoi(String operation, JSONObject lesDonneesJSON){
        AccesHTTP accesDonnees = new AccesHTTP();
        accesDonnees.delegate =this;
        accesDonnees.addParam("operation", operation);
        accesDonnees.addParam("lesdonnees", lesDonneesJSON.toString());
        accesDonnees.execute(SERVERAADDR);
    }
}
