package com.jmedinilla.manageproducts.presenter;

import android.content.Context;

import com.jmedinilla.manageproducts.interfaces.LoginPresenter;
import com.jmedinilla.manageproducts.R;
import com.jmedinilla.manageproducts.model.Error;
import com.jmedinilla.manageproducts.utils.ErrorMapResource;

/**
 * Class creater by JMedinilla on 2016-10-19
 * <p>
 * Login presenter
 */
public class LoginPresenterImpl implements LoginPresenter.Presenter {

    private LoginPresenter.View view;
    private Context context;

    /**
     * Peresenter constructor
     *
     * @param view The view he's watching
     */
    public LoginPresenterImpl(LoginPresenter.View view) {
        this.view = view;
        this.context = (Context) view;
    }

    /**
     * Method that checks if all fields are correct
     * <p>
     * If everything is correct a new Activity starts
     *
     * @param user     User name
     * @param password User password
     */
    public void validateCredentialsLogin(String user, String password) {
        int validateUser = validateUser(user);
        int validatePassword = validatePassword(password);

        if (validateUser == Error.OK) {
            if (validatePassword == Error.OK) {
                view.startActivity();
            } else {
                String nameIdMessage = ErrorMapResource.getErrorMapResource(this.context).get(String.valueOf(validatePassword));
                view.setMessageError(nameIdMessage, R.id.tilPass);
            }
        } else {
            String nameIdMessage = ErrorMapResource.getErrorMapResource(this.context).get(String.valueOf(validateUser));
            view.setMessageError(nameIdMessage, R.id.tilUser);
        }
    }

    /**
     * Method that validates the user name
     *
     * @param user User mail
     * @return Error code
     */
    public int validateUser(String user) {
        int idOut = 0;

        if (user.isEmpty()) {//If User is null
            idOut = Error.USER_EMPTY;
        }

        return idOut;
    }

    /**
     * Method that validates the user password
     *
     * @param password User mail
     * @return Error code
     */
    public int validatePassword(String password) {
        int idOut = 0;

        if (password.isEmpty())
            idOut = Error.PASS_EMPTY;
        else if (!(password.matches(".*" + p1 + ".*")))
            idOut = Error.PASS_LENGTH;
        else if (!(password.matches(".*" + p2 + ".*")))
            idOut = Error.PASS_CASE;
        else if ((password.matches(".*" + p3 + ".*")))
            idOut = Error.PASS_DIGIT;

        return idOut;
    }
}
