package com.jmedinilla.manageproducts.preferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.jmedinilla.manageproducts.interfaces.PreferencePresenter;

/**
 * Class created by JMedinilla on 2016-11-10
 */
public class AccountPreferencesImpl implements PreferencePresenter {
    private static final int MODE = PreferencePresenter.MODE;
    private static final String FILE = PreferencePresenter.FILE;

    private static final String USER = "user";
    private static final String PASSWORD = "password";
    private static final String MAIL = "mail";
    private static final String BUSINESS = "businessname";
    private static final String REMEMBER = "rememberuser";

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    /**
     * Preferences constructor
     *
     * @param context Activity context
     */
    public AccountPreferencesImpl(Context context) {
        sharedPreferences = context.getSharedPreferences(FILE, MODE);
        editor = sharedPreferences.edit();
    }

    public void setUser(String value) {
        editor.putString(USER, value).commit();
    }

    public void setPassword(String value) {
        editor.putString(PASSWORD, value).commit();
    }

    public void setMail(String value) {
        editor.putString(MAIL, value).commit();
    }

    public void setBusiness(String value) {
        editor.putString(BUSINESS, value).commit();
    }

    public void setRemember(boolean value) {
        editor.putBoolean(REMEMBER, value).commit();
    }

    public String getUser() {
        return sharedPreferences.getString(USER, "defValue");
    }

    public String getPassword() {
        return sharedPreferences.getString(PASSWORD, "defValue");
    }

    public String getMail() {
        return sharedPreferences.getString(MAIL, "defValue");
    }

    public String getBusiness() {
        return sharedPreferences.getString(BUSINESS, "defValue");
    }

    public boolean getRemember() {
        return sharedPreferences.getBoolean(REMEMBER, false);
    }
}
