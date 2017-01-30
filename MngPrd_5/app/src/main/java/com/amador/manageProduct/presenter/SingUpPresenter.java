package com.amador.manageProduct.presenter;

import android.content.Context;
import android.content.Intent;
import android.util.Patterns;

import com.amador.manageProduct.R;
import com.amador.manageProduct.interfaces.ISignUp;
import com.amador.manageProduct.interfaces.IValidateAccount;
import com.amador.manageProduct.model.Error;
import com.amador.manageProduct.preferences.AcountPreferences;
import com.amador.manageProduct.utils.ErrorMapUtils;
import com.amador.manageProduct.view.ListProductFragment;

/**
 * @author Amador Fernandez Gonzalez
 * Clase presentadora para la interfaz de registro de usuario que implementa
 * @see com.amador.manageProduct.interfaces.IValidateAccount.IPresenter y
 * @see com.amador.manageProduct.interfaces.ISignUp.PresenterUser
 * */
public class SingUpPresenter implements ISignUp.PresenterUser, ISignUp.IPresenter {


    private  IValidateAccount.View view;
    private int validateUser;
    private int validatePassword;
    private int validateEmail;
    private Context context;

    /**
     * Constructor de instancia de la clase
     *
     * @param view Vista que sera manejada por this
     */
    public SingUpPresenter(IValidateAccount.View view){

        this.view = view;
        this.context = (Context)view;
    }

    /**
     * Valida las credenciales de usuario
     * @param user nombre del usuario
     * @param email Email
     * @param password contraseña
     * */
    public void validateCredentials(String user, String password, String email){


        validateUser = validateUser(user);
        validatePassword = validatePassword(password);
        validateEmail = validateEmail(email);

        if(validateUser == Error.OK) {
            if (validatePassword == Error.OK) {
                if(validateEmail == Error.OK) {

                    Intent i = new Intent(context, ListProductFragment.class);
                    //Llamada al metodo startActivity de la vista, para ahorrarnos implementar un metodo
                    savePreferences(user, password, email);
                    view.startActivity(i);

                }else{

                    String nameIdMessage = ErrorMapUtils.getErrorMap(this.context).get(String.valueOf(validateEmail));
                    view.setMessageError(nameIdMessage, R.id.tilEmail);

                }
            }
            else{

                String nameIdMessage = ErrorMapUtils.getErrorMap(this.context).get(String.valueOf(validatePassword));
                view.setMessageError(nameIdMessage, R.id.tilPass);
            }
        }
        else{

            String nameIdMessage = ErrorMapUtils.getErrorMap(this.context).get(String.valueOf(validateUser));
            view.setMessageError(nameIdMessage, R.id.tilUser);
        }

    }

    private void savePreferences(String user, String pwd, String email){

        AcountPreferences preferences = (AcountPreferences)AcountPreferences.getInstance(context);
        preferences.setUserName(user);
        preferences.setUserPassword(pwd);
        preferences.setUserEmail(email);


    }

    /**
     * Valida el email del usuario
     * @param email Email
     * */
    @Override
    public int validateEmail(String email) {

        int result = 0;

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
            result = Error.INVALID_EMAIL;
        else
            result = Error.OK;

        return 0;
    }

    /**
     * Valida el nombre del usuario
     * @param user nombre
     * */
    @Override
    public int validateUser(String user) {

        int resutCode = 0;

        if (user.isEmpty()) {
            resutCode = Error.DATA_EMPTY;
        }

        return resutCode;
    }

    /**
     * Valida la contraseña del usuario
     * @param password contraseña
     * */
    @Override
    public int validatePassword(String password) {

        int resultCode = 0;

        if (password.isEmpty())
            resultCode = Error.DATA_EMPTY;
        else if (!(password.matches(".*" + p1 + ".*")))
            resultCode = Error.PWD_MIN_LENGTH;
        else if (!(password.matches(".*" + p2 + ".*")))
            resultCode = Error.PWD_CASE;
        else if (!(password.matches(".*" + p3 + ".*")))
            resultCode = Error.PWD_DIGIT;

        return resultCode;
    }
}
