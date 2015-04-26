import android.util.JsonReader;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

//public static final String TAG = "CuidaTuAlergia";

/**
 * Created by Alex on 23/04/2015.
 */
public class JsonParser {

    public void getFeatures(String jsonString){
        try{
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray featuresArray = jsonObject.getJSONArray("features");

        }catch(Exception e){
            Log.e("CuidaTuAlergia", e.toString());
        }
    }



}
