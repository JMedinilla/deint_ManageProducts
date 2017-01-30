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

import com.jsw.MngProductDatabase.Model.Error;
import com.jsw.MngProductDatabase.utils.ErrorMapUtils;
import com.jsw.MngProductDatabase.R;
import com.jsw.MngProductDatabase.interfaces.IValidateAccount;

/**
 * Created by joselu on 19/10/16.
 * Login Presenter.
 * Checks user and password fields.
 */


public class LoginPresenter implements IValidateAccount.IPresenter{

    private  IValidateAccount.View view;
    private int validateUser;
    private int validatePassword;
    private Context context;

    public LoginPresenter(IValidateAccount.View view){
        this.view = view;
        this.context = (Context)view;
    }


    public void validateCredentialsLogin(String user, String Password){

        validateUser = this.validateUser(user);
        validatePassword = this.validatePassword(Password);

        if(validateUser == Error.OK) {
            if (validatePassword == Error.OK) {
                /*
                    Por logica los startActivity siempre en las activitys, si no queremos abrirlo
                    desde las activitys los hacemos con view.startActivity
                 */
            }
            else{
                String nameIdMessage = ErrorMapUtils.getErrorMap(this.context).get(String.valueOf(validatePassword));
                view.setMessageError(nameIdMessage, R.id.til_password);
            }
        }
        else{
            String nameIdMessage = ErrorMapUtils.getErrorMap(this.context).get(String.valueOf(validateUser));
            view.setMessageError(nameIdMessage, R.id.til_user);
        }


    }

    @Override
    public int validateUser(String User) {
        int idOut = 0;

        if(User.isEmpty()) {//If User is null
            idOut = Error.DATAEMPTY;
        }

        return idOut;
    }

    @Override
    public int validatePassword(String Password) {
        int idOut = 0;

        if (Password.isEmpty())//If Password is null
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
