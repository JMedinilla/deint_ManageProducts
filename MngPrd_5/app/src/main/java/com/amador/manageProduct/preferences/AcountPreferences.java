package com.amador.manageProduct.preferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.amador.manageProduct.interfaces.IPreferences;


/**
 * @author Amador Fernandez Gonzalez
 * Clase para manipular las preferencias de usuario que implementa:
 * @see IPreferences
 * */
public class AcountPreferences implements IPreferences {


    public static final String USER_NAME_KEY = "user_name";
    public static final String USER_EMAIL_KEY = "user_email";
    public static final String USER_PASSWORD_KEY = "user_password";
    public static final String USER_LOCALITY_KEY = "user_locality";
    public static final String USER_PROVINCE_KEY = "user_province";
    public static final String USER_REMEMBER_KEY = "user_remember";
    public static final String USER_BUSINESS_KEY = "user_business";
    public static final String USER_SEND_UPDATE_KEY = "user_updates";
    private static AcountPreferences instance;
    private SharedPreferences preferences;
    private Context context;


    /**
     * Metodo para la optencion de la instacia unica
     *
     * @param context Contexto de la actividad que lo usa.
     */
    public static IPreferences getInstance(Context context){

        if(instance == null){

            instance = new AcountPreferences(context);
        }


        return instance;
    }

    /**
     * Constructor privado para la instancia unica
     * @param context Contexto de la actividad que se optiene con:
     * {@link #getInstance(Context)}
     * */
    private AcountPreferences(Context context){

        this.context = context;
        this.preferences = context.getSharedPreferences(ACOUNT_PREFERENCES_FILE_NAME, MODE);

    }

    /**
     * Obtiene el editor para el aceso al fichero de preferencias
     * @see android.content.SharedPreferences.Editor
     * */
    private SharedPreferences.Editor getEditor(){

        return preferences.edit();
    }

    /**
     * Metodo para guardar el nombre de usuario en las preferencias
     * @param userName Nombre del usuario
     * */
    public void setUserName(String userName){

        getEditor().putString(USER_NAME_KEY, userName).commit();

    }

    /**
     * Metodo para guardar el Email de usuario en las preferencias
     * @param userEmail Email del usuario
     * */
    public void setUserEmail(String userEmail){

        getEditor().putString(USER_EMAIL_KEY, userEmail).commit();

    }

    /**
     * Metodo para guardar la contraseña de usuario en las preferencias
     * @param userPassword Contraseña del usuario
     * */
    public void setUserPassword(String userPassword){

        getEditor().putString(USER_PASSWORD_KEY, userPassword).commit();

    }

    /**
     * Metodo para guardar la localidad del usuario en las preferencias
     * @param userLocality Nombre del usuario
     * */
    public void setUserLocality(String userLocality){

        getEditor().putString(USER_LOCALITY_KEY, userLocality).commit();

    }

    /**
     * Metodo para guardar la provincia de usuario en las preferencias
     * @param userProvince Provincia del usuario
     * */
    public void setUserProvince(String userProvince){

        getEditor().putString(USER_PROVINCE_KEY, userProvince).commit();

    }

    /**
     * Metodo para guardar si el usuario desea que sus credenciales sean recordadas en el Login
     * @param remember Indica si el usuario desea que sus datos sean recordados
     *                 en el Login
     * */
    public void setUserRemember(boolean remember){

        getEditor().putBoolean(USER_REMEMBER_KEY, remember).commit();

    }

    /**
     * Metodo para guardar si el usuario desea recibir actualizaciones
     * @param option Indica si el usuario desea recibir actualizaciones
     * */
    public void setUserSendUpdates(boolean option){

        getEditor().putBoolean(USER_SEND_UPDATE_KEY, option).commit();

    }

    /**
     * Metodo para guardar el nombre de usuario en las preferencias
     * @param businessName Nombre de la empresa del usuario si la tiene
     * */
    public void setUserBusinesName(String businessName){

        getEditor().putString(USER_BUSINESS_KEY, businessName).commit();

    }

    /**
     * Obtiene el nombre del usuario
     *
     * */
    public String getUserName(){

       return preferences.getString(USER_NAME_KEY,"");
    }

    /**
     * Obtiene el correo del usuario
     *
     * */
    public String getUserEmail(){

       return preferences.getString(USER_EMAIL_KEY,"");
    }

    /**
     * Obtiene la contraseña
     *
     * */
    public String getUserPassword(){

        return preferences.getString(USER_PASSWORD_KEY,"");
    }

    /**
     * Obtiene la localidad
     *
     * */
    public String getUserLocality(){

        return preferences.getString(USER_LOCALITY_KEY,"");

    }

    /**
     * Obtiene la provincia
     *
     * */
    public String getUserProvince(){

       return preferences.getString(USER_PROVINCE_KEY, "");
    }

    /**
     * Obtiene si el usuario desea que sus credenciales sean recordadas
     *
     * */
    public boolean setUserRemember(){

        return preferences.getBoolean(USER_REMEMBER_KEY, false);
    }

    /**
     * Obtiene si el usuario desea recibir notificaciones
     *
     * */
    public boolean getUserSendUpdates(){

       return preferences.getBoolean(USER_SEND_UPDATE_KEY, false);
    }

    /**
     * Obtiene el nombre del negocio del usuario
     *
     * */
    public String setUserBusinesName(){

        return preferences.getString(USER_BUSINESS_KEY, "");
    }






}
