package com.scbio.majex.cuidatualergia;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;

/**
 * Created by Alex on 15/05/2015.
 */
public class MainActivity extends Activity{

    Button buttonLoc, buttonConsejos, buttonSalir, buttonSaber;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonLoc = (Button) findViewById(R.id.Boton1);
        buttonSaber = (Button) findViewById(R.id.Boton2);
        buttonConsejos = (Button) findViewById(R.id.Boton3);
        buttonSalir = (Button) findViewById(R.id.Boton4);




        buttonLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lanzarMapa();
            }
        });



        buttonSaber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lanzarSaberMas();
            }
        });


        buttonConsejos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lanzarConsejos();
            }
        });

    }







    public void lanzarMapa(){
        Intent i = new Intent(this, MapsActivity.class);
        startActivity(i);
    }

    public void lanzarSaberMas(){
        Intent i = new Intent(this, MapsActivity.class);
        startActivity(i);
    }

    public void lanzarConsejos(){
        Intent i = new Intent(this, MapsActivity.class);
        startActivity(i);
    }







}
