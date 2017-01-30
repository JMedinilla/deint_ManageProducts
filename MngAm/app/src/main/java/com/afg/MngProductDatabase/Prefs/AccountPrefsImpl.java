package com.afg.MngProductDatabase.Prefs;

/*
 * Copyright (c) 2016 José Luis del Pino Gallardo.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 *  jose.gallardo994@gmail.com
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.afg.MngProductDatabase.interfaces.IPreferences;

/**
 * Created by usuario on 10/11/16.
 */

public class AccountPrefsImpl implements IPreferences {
    private static AccountPrefsImpl accountPrefs;
    private static final String USER = "USER";
    private static final String PASSWORD = "PASSWORD";
    private static final String MAIL = "MAIL";
    private SharedPreferences sharedPreferences;

    private AccountPrefsImpl(Context context){
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static IPreferences getInstance(Context context){
        if (accountPrefs == null) {
            accountPrefs = new AccountPrefsImpl(context);

        }

        return accountPrefs;
    }

    public void putUser(String User){
        getEditor().putString(USER, User).apply();
    }

    public void putPassword(String Password){
        getEditor().putString(PASSWORD, Password).apply();
    }

    public void putMail(String Mail){
        getEditor().putString(MAIL, Mail).apply();
    }

    private SharedPreferences.Editor getEditor(){
        return sharedPreferences.edit();
    }

}
