package com.jmedinilla.manageproducts.interfaces;

import java.util.regex.Pattern;

/**
 * Interface created by JMedinilla on 2016-10-06
 */
public interface LoginPresenter {

    /**
     * Interface for the view
     * <p>
     * setMessageError shows an error in the correct TextInputLayout
     * startActivity starts a new Activity
     */
    interface View {
        void setMessageError(String error, int errorCode);

        void startActivity();
    }

    /**
     * Interface for the presenter
     * <p>
     * The method validates the user and password
     */
    interface Presenter {
        //ILogin.View mView;
        Pattern p1 = Pattern.compile("[a-zA-Z0-9]{8,30}");
        Pattern p2 = Pattern.compile("[A-Z]");
        Pattern p3 = Pattern.compile("[0-9]");

        int validateUser(String User);

        int validatePassword(String Password);

        /*
        *
        * JAVA 8 METHOD INSIDE THE INTERFACE
        *
        static int validateUser(String User) {
            int idOut = 0;

            if(User.isEmpty()) {//If User is null
                idOut = Error.USER_EMPTY;
            }

            return idOut;
        }
        */

        /*
        *
        * JAVA 8 METHOD INSIDE THE INTERFACE
        *
        static int validatePassword(String Password) {
            int idOut = 0;

            if (Password.isEmpty())//If Password is null
                idOut = Error.PASS_EMPTY;
            else if (!(Password.matches(".*" + p1 + ".*")))
                idOut = Error.PASS_LENGTH;
            else if (!(Password.matches(".*" + p2 + ".*")))
                idOut = Error.PASS_CASE;
            else if (((Password.matches(".*" + p3 + ".*"))))
                idOut = Error.PASS_DIGIT;

            return idOut;
        }
        */
    }
}
