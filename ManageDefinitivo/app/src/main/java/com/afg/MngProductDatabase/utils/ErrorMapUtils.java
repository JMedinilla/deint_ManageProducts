package com.afg.MngProductDatabase.utils;

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
import android.content.res.XmlResourceParser;
import android.util.Log;
import com.afg.MngProductDatabase.R;
import org.xmlpull.v1.XmlPullParser;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by lourdes on 11/11/16.
 */

public class ErrorMapUtils {

    private static Map<String, String> map = null;
    private static int hashMapResId= R.xml.error_map;

    public static Map<String, String> getErrorMap(Context c) {
        if (map == null) {
            XmlResourceParser parser = c.getResources().getXml(hashMapResId);

            String key = null, value = null;

            try {
                int eventType = parser.getEventType();

                while (eventType != XmlPullParser.END_DOCUMENT) {
                    if (eventType == XmlPullParser.START_DOCUMENT) {
                        Log.d("utils", "Start document");
                    } else if (eventType == XmlPullParser.START_TAG) {
                        if (parser.getName().equals("map")) {
                            boolean isLinked = parser.getAttributeBooleanValue(null, "linked", false);

                            map = isLinked
                                    ? new LinkedHashMap<String, String>()
                                    : new HashMap<String, String>();
                        } else if (parser.getName().equals("entry")) {
                            key = parser.getAttributeValue(null, "key");

                            if (null == key) {
                                parser.close();
                                return null;
                            }
                        }
                    } else if (eventType == XmlPullParser.END_TAG) {
                        if (parser.getName().equals("entry")) {
                            map.put(key, value);
                            key = null;
                            value = null;
                        }
                    } else if (eventType == XmlPullParser.TEXT) {
                        if (null != key) {
                            value = parser.getText();
                        }
                    }
                    eventType = parser.next();
                }
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        return map;
    }
}
