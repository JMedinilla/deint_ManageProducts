package com.afg.MngProductContentProvider.cursor;

/*
 * Copyright (c) 2017 José Luis del Pino Gallardo.
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
import android.content.CursorLoader;
import android.database.Cursor;

import com.afg.MngProductContentProvider.database.DataBaseManager;

/**
 * Created by usuario on 27/01/17.
 */

public class CategoryCursorLoader extends CursorLoader {


    public CategoryCursorLoader(Context context) {
        super(context);
    }

    @Override
    public Cursor loadInBackground() {
        return DataBaseManager.getInstance().loadCategories();
    }
}
