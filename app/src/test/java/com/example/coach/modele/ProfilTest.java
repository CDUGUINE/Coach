package com.example.coach.modele;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.Date;

public class ProfilTest {
    Date dateMesure = new Date();
    // création d'un profil : femme de 67 kg, 1m65, 35 ans
    private Profil profil = new Profil(new Date(),72, 168, 35, 0);
    // résultat de l'img correspondant
    private float img = (float)32.2;
    // message correspondant
    private String message = "trop de graisse";

    @Test
    public void getImg() {
        assertEquals(img, profil.getImg(), (float)0.1);
    }

    @Test
    public void getMessage() {
        assertEquals(message, profil.getMessage());
    }
}