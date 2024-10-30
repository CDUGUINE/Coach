package com.example.coach.vue;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.coach.R;
import com.example.coach.controleur.Controle;
import com.example.coach.modele.Profil;

import java.util.ArrayList;
import java.util.Collections;

public class HistoActivity extends AppCompatActivity {

    private Controle controle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_histo);
        init();
    }

    /**
     * initialisation à l'ouverture
     * - récupération des objets graphiques
     * - création du controleur
     * - demande d'écoute (événements sur objets graphiques)
     */
    public void init() {
        controle = Controle.getInstance();
        creerListe();
    }

    private void creerListe() {
        ArrayList<Profil> lesProfils  = controle.getLesProfils();
        if(lesProfils != null) {
            Collections.sort(lesProfils, Collections.<Profil>reverseOrder());
            RecyclerView lstHisto = (RecyclerView) findViewById(R.id.lstHisto);
            HistoListAdapter adapter = new HistoListAdapter(HistoActivity.this, lesProfils);
            lstHisto.setAdapter(adapter);
            lstHisto.setLayoutManager(new LinearLayoutManager(HistoActivity.this));
            }
        }

    /**
     * Demande d'affichage des informations du profil, dans CalculActivity
     * @param profil
     */
    public void afficheProfil(Profil profil) {
        Intent intent = new Intent(HistoActivity.this, CalculActivity.class);
        intent.putExtra("profil", profil);
        startActivity(intent);
        }
    }