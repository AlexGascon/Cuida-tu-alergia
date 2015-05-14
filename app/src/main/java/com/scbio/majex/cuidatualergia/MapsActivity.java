package com.scbio.majex.cuidatualergia;

import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;


import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class MapsActivity extends FragmentActivity {
    //private final String TAG = getString(R.string.app_name);
    private final String TAG = "CUIDATUALERGIA_TAG";
    private final String ERRORMSG = "ERROR";//getString(R.string.ERRORMSG);


    private GoogleMap mMap; // Might be null if Google Play services APK is not available.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();


        mMap.setMyLocationEnabled(true);
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        String bestProvider = locationManager.getBestProvider(criteria, true);
        Location myLocation = locationManager.getLastKnownLocation(bestProvider);

        /*LatLng myLatLng = new LatLng(myLocation.getLatitude(), myLocation.getLongitude() );
        CameraUpdate cameraMyLocation = CameraUpdateFactory.newLatLng(myLatLng);
        CameraUpdate zoom = CameraUpdateFactory.zoomTo(1000);


        mMap.moveCamera(cameraMyLocation);
        mMap.animateCamera(zoom);*/

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
            Log.d("Entrar en HTTPTask", TAG);
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
                File file = new File(getApplicationContext().getFilesDir(), JSONString);
                (Toast.makeText(getApplicationContext(), getString(R.string.JSON_read_success), Toast.LENGTH_LONG)).show();
                new JSONparsingTask(JSONString).execute();
                
            }
        }

        @Override
        //protected void onProgressUpdate (PROGRESO ... prog)
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }

    //public class [NOMBRE] extends AsyncTask <PARAMETROS, PROGRESO, RESULTADOS>
    public class JSONparsingTask extends AsyncTask<Void, String, ArrayList<Geometria>>{

        String JSONString;
        public JSONparsingTask(String jss) {
            super();
            this.JSONString = jss;
        }

        @Override
        protected ArrayList<Geometria> doInBackground(Void... params){
            ArrayList<Geometria> poligonos = new ArrayList<Geometria>();

            try {
                publishProgress("JSONTask se ejecuta");
                JSONObject jsObject = new JSONObject(this.JSONString), geometry;
                JSONArray features = jsObject.getJSONArray("features"), coordinates;


               /*IMPORTANTÍSIMO: de momento, hemos puesto para dibujar sólo un polígono. Cuando esto
                * funcione bien, ya lo cambiaremos como sea. Pero de momento, hay un 1
                * donde debería haber un "features.length()"
                *
                *
                * IMPORTANTE NO OLVIDARNOS DE CAMBIARLO
                *  IMPORTANTE NO OLVIDARNOS DE CAMBIARLO
                *   IMPORTANTE NO OLVIDARNOS DE CAMBIARLO
                *    IMPORTANTE NO OLVIDARNOS DE CAMBIARLO
                *     IMPORTANTE NO OLVIDARNOS DE CAMBIARLO
                *      IMPORTANTE NO OLVIDARNOS DE CAMBIARLO
                *       IMPORTANTE NO OLVIDARNOS DE CAMBIARLO
                *        IMPORTANTE NO OLVIDARNOS DE CAMBIARLO
                *
                *  */
                Log.d(TAG, "features length: " + features.length());
                 for(int i = 0; i < features.length(); i++){
                    JSONObject jsonEntry = features.getJSONObject(i);
                    String densidadTemp = ((JSONObject) jsonEntry.get("properties")).getString("densidad");
                    Geometria poligonoTemp = new Geometria();
                    poligonoTemp.setDensidad(densidadTemp);

                    //Obtenemos el vector con las coordenadas y lo casteamos a String
                    geometry = (JSONObject) jsonEntry.get("geometry");
                    coordinates = (JSONArray) geometry.get("coordinates");
                    String coordString = coordinates.toString();
                    //Log.d(TAG, "COORDSTRING: " + coordString);


                    //Eliminamos los corchetes de principio y final de polígono
                    coordString = coordString.substring(3, coordString.length() - 3);

                    //Obtenemos cada par lat-lon de coordenadas
                    String splitter = "],\\[";

                    String[] parCoords = coordString.split(splitter);


                    //Separamos latitud y longitud y las guardamos en diferentes arrays
                    ArrayList<Double> latitudTemp = new ArrayList<Double>(), longitudTemp = new ArrayList<Double>();
                    String LatLonSplitter = ",", LatLonTemp[];
                    for(int j = 0; j < parCoords.length; j++){

                        LatLonTemp = parCoords[j].split(LatLonSplitter);
                        if(LatLonTemp[0].charAt(0) == '[') LatLonTemp[0] = LatLonTemp[0].substring(1);
                        longitudTemp.add(Double.parseDouble(LatLonTemp[0]));
                        latitudTemp.add(Double.parseDouble(LatLonTemp[1].substring(0,LatLonTemp[1].length()-1)));
                    }

                    //Las añadimos al polígono que dibujaremos
                    poligonoTemp.setLatitud(latitudTemp);
                    poligonoTemp.setLongitud(longitudTemp);


                    if(i % 50 == 0 || i == 1){
                        publishProgress("Iteración " + i);
                    }


                    /*
                    DIBUJO DEL POLÍGONO. MIRAR BIEN CÓMO Y DÓNDE METERLO PARA QUE FUNCIONE

                    */


                poligonos.add(poligonoTemp);

                }
                Log.d(TAG, "Salimos!");


            }catch (Exception e){

                Log.e(TAG, e.toString());
            }
            Log.d(TAG, "Llega a antes del return");
            return poligonos;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(ArrayList<Geometria> poligonos) {
            try{
            super.onPostExecute(poligonos);
            Log.d(TAG, "Llegamos a onPostExe");
            Log.d(TAG, "Poligo-length: " + poligonos.size());
            PolygonOptions[] polyOptArray = new PolygonOptions[poligonos.size()-1];
            Polygon[] polygonArray = new Polygon[poligonos.size()-1];


            for(int i = 0; i < poligonos.size(); i++){
                Geometria polTemp =  poligonos.get(i);
                polyOptArray[i] = new PolygonOptions();
                ArrayList<Double> lat = polTemp.getLatitud();
                ArrayList<Double> lon = polTemp.getLongitud();

                Log.d(TAG, "Casi juega con PolyOpt");

                polyOptArray[i].strokeColor(Color.BLACK);
                polyOptArray[i].fillColor(Color.RED);
                polyOptArray[i].strokeWidth(3);

                Log.d(TAG, "Juega con PolyOpt");


                Log.d(TAG, "Puntos (LAT) = " + lat.size() + " Puntos (LON) = " + lon.size());
                for(int j = 0; j < lat.size(); j++){
                    //if(j==0) Log.d(TAG, "Entra a añadir puntos");
                    polyOptArray[i].add(new LatLng(lat.get(j), lon.get(j)));
                    //Log.d(TAG, "Lat = " + lat.get(j) + " ----- Lon = " + lon.get(j));
                    //if(j==0) Log.d(TAG, "Añade bien un punto");
                }

                //Log.d(TAG, "Añade bien TODOS los puntos");
                polygonArray[i] = mMap.addPolygon(polyOptArray[i]);
                polygonArray[i].setFillColor(Color.RED);
                //Log.d(TAG, "Dibuja el polígono");



                LatLng myPos = new LatLng(lat.get(0),lon.get(0));
                CameraUpdate cameraMyLocation = CameraUpdateFactory.newLatLng(myPos);
                CameraUpdate zoom = CameraUpdateFactory.zoomTo(500);
                //Log.d(TAG, "Lat = " + lat.get(0) + " ----- Lon = " + lon.get(0));


                mMap.moveCamera(cameraMyLocation);
                mMap.animateCamera(zoom);
                //Log.d(TAG, "Actualiza la posición");
                /*mMap.addMarker(new MarkerOptions()
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                        .position(myPos)
                        .title("Tipo de polen: Casuarina")
                        .snippet("Densidad: " + polTemp.getDensidad()));*/

            Log.d(TAG, "Dibujado polígono " + i);

            //lat.clear(); lon.clear(); //polTemp.clear();
            Log.d(TAG, "Terminando de dibujar polígono " + i);
            //lat.clear(); lon.clear();
            }


        }catch(Exception e){
            Log.e(TAG, e.toString());
        }
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            (Toast.makeText(getApplicationContext(), values[0], Toast.LENGTH_LONG)).show();
            Log.d(TAG,values[0]);

        }
    }

}
