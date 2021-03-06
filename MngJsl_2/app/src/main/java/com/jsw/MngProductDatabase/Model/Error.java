package com.jsw.MngProductDatabase.Model;

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

/**
 * Created by usuario on 14/11/16.
 */

public class Error {
    public static final int OK = 0;
    public static final int PASSDIGIT = 10;
    public static final int PASSCASE = 11;
    public static final int PASSMINLENGTH = 12;
    public static final int DATAEMPTY = 13;
    public static final int INVALID_MAIL = 14;
    public static int errCode;
    public static String errMessage;

    static {
        //Aqui va a venir un constructor
    }
}
