package com.jsw.MngProductDatabase.Presenter;

/*
 * Copyright (c) 2016 José Luis del Pino Gallardo.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 *  jose.gallardo994@gmail.com
 */

import android.content.Context;
import android.util.Patterns;

import com.jsw.MngProductDatabase.Model.Error;
import com.jsw.MngProductDatabase.Prefs.AccountPrefsImpl;
import com.jsw.MngProductDatabase.utils.ErrorMapUtils;
import com.jsw.MngProductDatabase.R;
import com.jsw.MngProductDatabase.interfaces.ISignUp;

/**
 * Created by usuario on 16/11/16.
 */

public class SignUpPresenter implements ISignUp.IPresenterUser, ISignUp.IPresenter{


    //region Private Variables
    private int validateUser;
    private int validatePassword;
    private int validateMail;
    private ISignUp.View view;
    private Context context;
    private String nameIdMessage;
    private String successful;
    //endregion


    /**
     * Presenter Constructor.
     * @param view View
     */
    public SignUpPresenter(ISignUp.View view){
        this.view = view;
        this.context = (Context)view;
    }


    /**
     * Validate Credentials Method.
     * Checks the user, the pass and the mail calling his own methods.
     * On each case, it call the view with an ERRCode and a View.
     * If it is correct View is 0.
     * @param username Username String
     * @param pass Password String
     * @param mail Mail String
     */
    public void validateCredentials(String username, String pass, String mail){

        validateUser = this.validateUser(username);
        validatePassword = this.validatePassword(pass);
        validateMail = this.validateEmail(mail);
        successful = "successful";


        if(validateUser == Error.OK) {
            if (validatePassword == Error.OK) {
                if (validateMail == Error.OK) {
                    successful = ErrorMapUtils.getErrorMap(this.context).get(String.valueOf(0));
                    view.setMessageError(successful, 0);
                    this.savePreferences(username, pass, mail);
                }
                else{
                    nameIdMessage = ErrorMapUtils.getErrorMap(this.context).get(String.valueOf(validateMail));
                    view.setMessageError(nameIdMessage, R.id.til_email);
                }
            }
            else{
                nameIdMessage = ErrorMapUtils.getErrorMap(this.context).get(String.valueOf(validatePassword));
                view.setMessageError(nameIdMessage, R.id.til_password);
            }
        }
        else{
            nameIdMessage = ErrorMapUtils.getErrorMap(this.context).get(String.valueOf(validateUser));
            view.setMessageError(nameIdMessage, R.id.til_user);
        }
    }

    private void savePreferences(String user, String password, String email){
        AccountPrefsImpl accountPrefs = (AccountPrefsImpl) AccountPrefsImpl.getInstance(this.context);
        accountPrefs.putUser(user);
        accountPrefs.putPassword(password);
        accountPrefs.putMail(email);
    }

    /**
     * Test email syntax failures.
     * Use the android Email Pattern
     * @param email Email String
     * @return 0 if correct. ERRCode if invalid.
     */
    @Override
    public int validateEmail(String email) {
        int result = 0;

        if(!(Patterns.EMAIL_ADDRESS.matcher(email).matches()))
            result = Error.INVALID_MAIL;

        return result;
    }

    /**
     * Test user syntax failures.
     * Checks if user is null.
     * @param User User String
     * @return 0 if correct. ErrCode if invalid.
     */
    @Override
    public int validateUser(String User) {
        int idOut = 0;

        if(User.isEmpty()) {//If User is null
            idOut = Error.DATAEMPTY;
        }

        return idOut;
    }

    /**
     * Test password syntax failures.
     * Checks:
     * If is empty
     * If haven't got one upper case at least
     * If haven't got one digit at least
     * If lenght is more than 8
     * @param Password
     * @return 0 if correct. ErrCode if invalid
     */
    @Override
    public int validatePassword(String Password) {
        int idOut = 0;

        if (Password.isEmpty())
            idOut = Error.DATAEMPTY;
        else if (!(Password.matches(".*" + p1 + ".*")))
            idOut = Error.PASSMINLENGTH;
        else if (!(Password.matches(".*" + p2 + ".*")))
            idOut = Error.PASSCASE;
        else if (!(Password.matches(".*" + p3 + ".*")))
            idOut = Error.PASSDIGIT;

        return idOut;
    }
}
