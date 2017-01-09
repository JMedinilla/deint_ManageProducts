package com.amador.manageProduct.interfaces;

import android.content.Intent;

import java.util.regex.Pattern;

/**
 * @author Amador Fernandez Gonzalez
 * Interfaz padre para contener las interfaces de las vistas y sus
 * respectivos presentadores
 * @see IValidateAccount.View y
 * @see IValidateAccount.IPresenter
 */

public interface IValidateAccount {

    /**
     * Interfaz que contiene las firmas de los metodos que tendran que implementar
     * las clases que hagan uso de su implementacion.
     */
    interface View{
        public void setMessageError(String error, int errCode);
        public void startActivity(Intent i);
    }


    /**
     * Interfaz que contiene las firmas de los metodos que tendran que implementar
     * las clases que hagan uso de su implementacion.
     */
    interface IPresenter{

        Pattern p1 = Pattern.compile("[a-zA-Z0-9]{8,30}");
        Pattern p2 = Pattern.compile("[A-Z]");
        Pattern p3 = Pattern.compile("[0-9]");


         int validateUser(String User);
        int validatePassword(String Password);

        /*
        static int validateUser(String User) {
            int resutCode = 0;

            if(User.isEmpty()) {
                resutCode = Error.DATA_EMPTY;
            }

            return resutCode;
        }

        static int validatePassword(String Password) {
            int resultCode = 0;

            if (Password.isEmpty())
                resultCode = Error.DATA_EMPTY;
            else if (!(Password.matches(".*" + p1 + ".*")))
                resultCode = Error.PWD_MIN_LENGTH;
            else if (!(Password.matches(".*" + p2 + ".*")))
                resultCode = Error.PWD_CASE;
            else if (((Password.matches(".*" + p3 + ".*"))))
                resultCode = Error.PWD_DIGIT;

            return resultCode;
        }

        */
    }


}