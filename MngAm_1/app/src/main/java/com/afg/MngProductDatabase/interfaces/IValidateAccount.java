package com.afg.MngProductDatabase.interfaces;

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

import java.util.regex.Pattern;

/**
 * Created by usuario on 11/11/16.
 */

public interface IValidateAccount {

    interface View{
        public void setMessageError(String error, int errCode);
        public void startActivity();
    }

    public interface IPresenter{
        //ILogin.View mView;
        Pattern p1 = Pattern.compile("[a-zA-Z0-9]{8,30}");
        Pattern p2 = Pattern.compile("[A-Z]");
        Pattern p3 = Pattern.compile("[0-9]");

        int validateUser(String User);
        int validatePassword(String Password);

        /*static int validateUser(String User) {
            int idOut = 0;

            if(User.isEmpty()) {//If User is null
                idOut = Error.DATAEMPTY;
            }

            return idOut;
        }

        static int validatePassword(String Password) {
            int idOut = 0;

            if (Password.isEmpty())//If Password is null
                idOut = Error.DATAEMPTY;
            else if (!(Password.matches(".*" + p1 + ".*")))
                idOut = Error.PASSMINLENGTH;
            else if (!(Password.matches(".*" + p2 + ".*")))
                idOut = Error.PASSCASE;
            else if (((Password.matches(".*" + p3 + ".*"))))
                idOut = Error.PASSDIGIT;

            return idOut;
        }*/
    }


}
