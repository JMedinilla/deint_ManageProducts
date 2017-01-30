package com.jmedinilla.manageproducts.interfaces;

/**
 * Interface created by JMedinilla on 2016-10-06
 */
public interface ILoginMvp {

    /**
     * Interface that implements the method for the view
     * The method recieves an error message for the View from the Presenter
     */
    interface View {
        void setLoginMessageError(String message, int idView);
    }

    /**
     * Interface that implements the method for the presenter
     * The method validates the user and the password in the EditTexts
     */
    interface Presenter {
        boolean validateCredentials(String user, String pass);
    }
}
