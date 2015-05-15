package com.scbio.majex.cuidatualergia;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by Alex on 15/05/2015.
 */
public class MainActivity extends Activity{

    Button buttonLoc, buttonConsejos, buttonConfig, buttonSaber;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonLoc = (Button) findViewById(R.id.Boton1);
        buttonSaber = (Button) findViewById(R.id.Boton2);
        buttonConsejos = (Button) findViewById(R.id.Boton3);
        buttonConfig = (Button) findViewById(R.id.Boton4);




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
                //llamadaEmergencia();
            }
        });



        buttonConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lanzarConfig();
            }
        });

    }







    public void lanzarMapa(){
        Intent i = new Intent(this, MapsActivity.class);
        startActivity(i);
    }

    public void lanzarSaberMas(){
        Intent i = new Intent(this, AcercaDe.class);
        startActivity(i);
    }

    public void lanzarConsejos(){
        Intent i = new Intent(this, Consejos.class);
        startActivity(i);
    }

    public void lanzarConfig(){
        Intent i = new Intent(this, Preferencias.class);
        startActivity(i);
    }

    public void lanzarSalir(){
        onDestroy();
    }

    public void llamadaEmergencia(){


        SharedPreferences prefs = getSharedPreferences("com.scbio.majex.cuidatualergia_preferences", Context.MODE_PRIVATE);
        boolean permitted = prefs.getBoolean("habilitarLlamada", false);

        if(permitted) {
            String phone = prefs.getString("telfEmergencia", "112");
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:" + phone));
            startActivity(callIntent);
        }
        else{
            (Toast.makeText(getApplicationContext(), "Para realizar llamadas, habilite la opción en Preferencias", Toast.LENGTH_LONG)).show();
        }



    }







}
