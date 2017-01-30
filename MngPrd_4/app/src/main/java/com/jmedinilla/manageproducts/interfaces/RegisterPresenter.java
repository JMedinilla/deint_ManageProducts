package com.jmedinilla.manageproducts.interfaces;

/**
 * Class created by JMedinilla on 2016-11-16
 */
public interface RegisterPresenter extends LoginPresenter {

    /**
     * Interface for the presenter
     * <p>
     * The method validates the mail
     */
    interface PresenterUser {
        int validateEmail(String email);

        /*
        *
        * JAVA 8 METHOD INSIDE THE INTERFACE
        *
        static int validateEmail(String email){
            int result = Error.OK;

            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
                result = Error.EMAIL_INVALID;

            return result;
        }
        */
    }
}