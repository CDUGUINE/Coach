package com.example.coach.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coach.R;
import com.example.coach.controleur.Controle;

/**
 * Classe d'affichage (activity) MainActivity
 * Permet le calcul d'un IMG
 */
public class MainActivity extends AppCompatActivity {

    // Déclaration des propriétés et du controleur
    private EditText txtPoids;
    private EditText txtTaille;
    private EditText txtAge;
    private RadioButton rdHomme;
    private TextView lblIMG;
    private ImageView imgSmiley;
    private Button btnCalc;
    private Controle controle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    /**
     * initialisation à l'ouverture
     * - récupération des objets graphiques
     * - création du controleur
     * - demande d'écoute (événements sur objets graphiques)
     */
    private void init() {
        txtPoids = (EditText) findViewById(R.id.txtPoids);
        txtTaille = (EditText) findViewById(R.id.txtTaille);
        txtAge = (EditText) findViewById(R.id.txtAge);
        rdHomme = (RadioButton) findViewById(R.id.rdHomme);
        lblIMG = (TextView) findViewById(R.id.lblIMG);
        imgSmiley = (ImageView) findViewById(R.id.imgSmiley);
        btnCalc = (Button) findViewById(R.id.btnCalc);
        controle = Controle.getInstance();
        ecouteCalcul();
    }

    private void ecouteCalcul() {
        btnCalc.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Integer poids = 0;
                Integer taille = 0;
                Integer age = 0;
                try {
                    poids = Integer.parseInt(txtPoids.getText().toString());
                    taille = Integer.parseInt(txtTaille.getText().toString());
                    age = Integer.parseInt(txtAge.getText().toString());
                }catch(Exception e){}
                Integer sexe = 0;
                if(rdHomme.isChecked()) {
                    sexe = 1;
                }
                if(poids*taille*age == 0) {
                    Toast.makeText(MainActivity.this, "Veuillez saisir tous les champs", Toast.LENGTH_SHORT).show();
                }
                else {
                    retourneCalcul(poids, taille, age, sexe);
                }

            }
        });
    }

    private void retourneCalcul(Integer poids, Integer taille, Integer age, Integer sexe) {
        controle.creerProfil(poids, taille, age, sexe);
        String message = controle.getMessage();
        float img = controle.getImg();
        switch (message) {
            case "trop maigre":
                imgSmiley.setImageResource(R.drawable.maigre);
                lblIMG.setText(String.format("%.01f", img) + ": IMG trop faible");
                lblIMG.setTextColor(Color.RED);
                break;
            case  "normal":
                imgSmiley.setImageResource(R.drawable.normal);
                lblIMG.setText(String.format("%.01f",img) + ": IMG normal");
                lblIMG.setTextColor(Color.GREEN);
                break;
            default:
                imgSmiley.setImageResource(R.drawable.graisse);
                lblIMG.setText(String.format("%.01f",img) + ": IMG trop élevé");
                lblIMG.setTextColor(Color.RED);
                break;
        }
    }
}