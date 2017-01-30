package com.jsw.MngProductDatabase;

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

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class SplashScreen_Activity extends AppCompatActivity {
    // Spalsh Duration
    private static final long SPLASH_SCREEN_DELAY = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash_screen);

        /**
         * New Timer Task.
         */
        TimerTask task = new TimerTask() {
            @Override
            public void run() {

                // Launch the next Activity
                Intent mainIntent = new Intent().setClass(
                        SplashScreen_Activity.this, Login_Activity.class);
                startActivity(mainIntent);

                // And close the Splash
                finish();
            }
        };

        //Launch the Splash
        Timer timer = new Timer();
        timer.schedule(task, SPLASH_SCREEN_DELAY);
    }
}