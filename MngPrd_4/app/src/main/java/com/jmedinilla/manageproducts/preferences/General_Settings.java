package com.jmedinilla.manageproducts.preferences;

import android.os.Bundle;
import android.preference.PreferenceActivity;

import com.jmedinilla.manageproducts.R;

/**
 * Preferences class created by JMedinilla on 2016-11-02
 */
public class General_Settings extends PreferenceActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.general_setting);
    }
}
