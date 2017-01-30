package com.jmedinilla.manageproducts.presenter;

import android.content.Context;
import android.util.Patterns;

import com.jmedinilla.manageproducts.R;
import com.jmedinilla.manageproducts.interfaces.IAccountValidation;
import com.jmedinilla.manageproducts.interfaces.IUserValidation;
import com.jmedinilla.manageproducts.model.Error;
import com.jmedinilla.manageproducts.preferences.AccountPreferences;
import com.jmedinilla.manageproducts.utils.ErrorMapResource;

import java.util.regex.Pattern;

/**
 * Class created by JMedinilla on 2016-11-16
 * <p>
 * Sign up presenter
 */
public class RegisterPresenter implements IUserValidation.PresenterUser, IUserValidation.Presenter {

    private Pattern p1 = Pattern.compile("[a-zA-Z0-9]{8,30}");
    private Pattern p2 = Pattern.compile("[A-Z]");
    private Pattern p3 = Pattern.compile("[0-9]");

    private IAccountValidation.View view;
    private Context context;

    /**
     * Peresenter constructor
     *
     * @param view The view he's watching
     */
    public RegisterPresenter(IAccountValidation.View view) {
        this.view = view;
        this.context = (Context) view;
    }

    /**
     * Method that checks if all fields are correct
     * <p>
     * If everything is correct, it saves the user fields in preferences and start a new Activity
     *
     * @param user User name
     * @param pass User password
     * @param mail User mail
     */
    public void validateCredentials(String user, String pass, String mail) {

        int validateUser = validateUser(user);
        int validatePassword = validatePassword(pass);
        int validateMail = validateEmail(mail);

        if (validateUser == Error.OK) {
            if (validatePassword == Error.OK) {
                if (validateMail == Error.OK) {
                    savePreferences(user, pass, mail);
                    view.startActivity();
                } else {
                    String nameIdMessage = ErrorMapResource.getErrorMapResource(this.context).get(String.valueOf(validateMail));
                    view.setMessageError(nameIdMessage, R.id.tilRegisterMail);
                }
            } else {
                String nameIdMessage = ErrorMapResource.getErrorMapResource(this.context).get(String.valueOf(validatePassword));
                view.setMessageError(nameIdMessage, R.id.tilRegisterPassword);
            }
        } else {
            String nameIdMessage = ErrorMapResource.getErrorMapResource(this.context).get(String.valueOf(validateUser));
            view.setMessageError(nameIdMessage, R.id.tilRegisterUser);
        }
    }

    /**
     * Method that validates the user mail
     *
     * @param mail User mail
     * @return Error code
     */
    public int validateEmail(String mail) {
        int result = Error.OK;

        if (!Patterns.EMAIL_ADDRESS.matcher(mail).matches())
            result = Error.EMAIL_INVALID;

        return result;
    }

    /**
     * Method that validates the user name
     *
     * @param user User name
     * @return Error code
     */
    public int validateUser(String user) {
        int idOut = 0;

        if (user.isEmpty()) {
            idOut = Error.USER_EMPTY;
        }

        return idOut;
    }

    /**
     * Method that validates the user password
     *
     * @param password User password
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
        else if (!(password.matches(".*" + p3 + ".*")))
            idOut = Error.PASS_DIGIT;

        return idOut;
    }

    /**
     * Method that saves the user fields
     *
     * @param user User's name
     * @param pass User's password
     * @param mail User's mail
     */
    private void savePreferences(String user, String pass, String mail) {
        AccountPreferences preferences = new AccountPreferences(this.context);
        preferences.setUser(user);
        preferences.setPassword(pass);
        preferences.setMail(mail);
    }
}
