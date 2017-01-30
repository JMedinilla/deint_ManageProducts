package com.amador.manageProduct.presenter;

import android.os.Bundle;
import android.preference.PreferenceActivity;

import com.amador.manageProduct.R;

/**
 * Created by usuario on 2/11/16.
 */

public class Preference extends PreferenceActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.setting_general);

    }
}
