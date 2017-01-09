package com.amador.manageProduct.presenter;


import android.content.Context;
import android.content.Intent;

import com.amador.manageProduct.R;
import com.amador.manageProduct.interfaces.IValidateAccount;
import com.amador.manageProduct.model.Error;
import com.amador.manageProduct.utils.ErrorMapUtils;
import com.amador.manageProduct.view.LoginActivity;

/**
 * @author Amador Fernandez Gonzalez
 *         Clase presentadora para el login que implementa:
 * @see com.amador.manageProduct.interfaces.IValidateAccount.IPresenter
 */
public class LoginPresenter implements IValidateAccount.IPresenter{

    private  IValidateAccount.View view;
    private int validateUser;
    private int validatePassword;
    private Context context;

    /**
     * Constructor de instancia de la clase
     * @param view Vista que sera manejada por this:
     *             @see com.amador.manageProduct.interfaces.IValidateAccount.View
     * */
    public LoginPresenter(IValidateAccount.View view){
        this.view = view;
        this.context = (Context)view;
    }


    /**
     * Valida las credenciales de usuario
     *
     * @param user     nombre del usuario
     * @param password contraseña para la cuenta
     */
    public void validateCredentialsLogin(String user, String password){

        validateUser = validateUser(user);
        validatePassword = validatePassword(password);

        if(validateUser == Error.OK) {
            if (validatePassword == Error.OK) {

                Intent i = new Intent(context, LoginActivity.class);
                view.startActivity(i);
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

    /**
     * Valida el nombre de usuario
     * @param user nombre del usuario
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
     * @param password contraseña del usuario
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
        else if (((password.matches(".*" + p3 + ".*"))))
            resultCode = Error.PWD_DIGIT;

        return resultCode;
    }
}