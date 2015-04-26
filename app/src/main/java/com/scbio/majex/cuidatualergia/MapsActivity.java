package com.scbio.majex.cuidatualergia;

import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class MapsActivity extends FragmentActivity {
    //private final String TAG = getString(R.string.app_name);
    private final String TAG = "CUIDATUALERGIA";
    private final String ERRORMSG = "ERROR";//getString(R.string.ERRORMSG);


    private GoogleMap mMap; // Might be null if Google Play services APK is not available.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();

        CameraUpdate center=
                CameraUpdateFactory.newLatLng(new LatLng(39.15293169992044, 0.2598180484771729));
        CameraUpdate zoom= CameraUpdateFactory.zoomTo(15);

        mMap.moveCamera(center);
        mMap.animateCamera(zoom);

        new HTTPTask("http://mapas.valencia.es/lanzadera/opendata/Polen-casuarina/JSON").execute();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
        mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
    }




    //public class [NOMBRE] extends AsyncTask <PARAMETROS, PROGRESO, RESULTADOS>
    public class HTTPTask extends AsyncTask<Void, Void, String> {
        URL url;
        URLConnection connection;

        public HTTPTask (String url){

            super();
            //Intentamos parsear la URL
            try{
                this.url = new URL(url);
            }catch(MalformedURLException MUe){
                Log.e(TAG, MUe.toString());
            }
        }

        @Override
        //protected RESULTADOS doInBackground (PARAMETROS ... params)
        protected String doInBackground(Void ... params) {

            String JSONstring = "";
            try{
                //Abrimos la conexión con la dirección web
                this.connection = this.url.openConnection();

                //Obtenemos los elementos necesarios para leer de la conexión URL
                InputStreamReader isr = new InputStreamReader(connection.getInputStream());
                BufferedReader in = new BufferedReader(isr);

                //Leemos el objeto que nos devuelva la conexión y lo almacenamos en una String
                String inputLine;

                while ((inputLine = in.readLine()) != null){

                    JSONstring = JSONstring + inputLine;
                }

                //Cerramos los objetos que ya no vamos a necesitar
                in.close(); isr.close();


            }catch(IOException IOe){
                JSONstring = ERRORMSG;
                Log.e(TAG, IOe.toString());
            }


            Log.d(TAG, "Petición JSON realizada correctamente");
            Log.d(TAG, "INICIO cadena: " + JSONstring.substring(0, 100));
            return JSONstring;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        //protected void onPostExecute (RESULTADOS resultados)
        protected void onPostExecute(String JSONString) {
            super.onPostExecute(JSONString);

            //Aquí mostramos un toast que indique que hemos leído bien o mal la JSONString
            //Error en la lectura
            if(JSONString.equals(ERRORMSG))
                (Toast.makeText(getApplicationContext(), ERRORMSG, Toast.LENGTH_LONG)).show();
            //Éxito en la lectura
            else{
                (Toast.makeText(getApplicationContext(), getString(R.string.JSON_read_success), Toast.LENGTH_LONG)).show();
            }
        }

        @Override
        //protected void onProgressUpdate (PROGRESO ... prog)
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }

    //public class [NOMBRE] extends AsyncTask <PARAMETROS, PROGRESO, RESULTADOS>
    public class JSONparsingTask extends AsyncTask<String, Integer, Void>{

        public JSONparsingTask() {
            super();
        }

        @Override
        protected Void doInBackground(String... params) {
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }
    }

}