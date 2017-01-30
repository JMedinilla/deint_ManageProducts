package com.amador.manageProduct.interfaces;

import com.amador.manageProduct.view.SignUpActivity;

/**
 * @author Amador Fernandez Gonzalez
 * Interfaz para el presentador de la vista:
 * @see SignUpActivity que extiende de:
 * @see com.amador.manageProduct.interfaces.IValidateAccount
 * */
public interface ISignUp extends IValidateAccount {

    interface PresenterUser {

        int validateEmail(String email);

        /*
        static int validateEmail(String email){
            int result = 0;

            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
                result = Error.INVALID_EMAIL;
            else
                result = Error.OK;

            return 0;
        }

*/
    }
}
