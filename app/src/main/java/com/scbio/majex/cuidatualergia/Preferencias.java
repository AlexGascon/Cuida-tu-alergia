package com.scbio.majex.cuidatualergia;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by Alex on 15/05/2015.
 */
public class Preferencias extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferencias);
    }
}
