package com.afg.MngProductContentProvider.Adapter;

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
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.afg.MngProductContentProvider.Model.Pharmacy;
import com.afg.MngProductContentProvider.R;

/**
 * Created by usuario on 26/01/17.
 */

public class ListPharmacyAdapter extends CursorAdapter {

    private int layout;

    class PharmacyHolder{

        TextView name, email, phone;
    }

    public ListPharmacyAdapter(Context context, int layout) {
        super(context, null,0);
        this.layout = layout;

    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View rootView = inflater.inflate(layout, viewGroup, false);
        PharmacyHolder pharmacyHolder = new PharmacyHolder();
        pharmacyHolder.name = (TextView)rootView.findViewById(R.id.txvNamePhamacy);
        pharmacyHolder.email = (TextView)rootView.findViewById(R.id.txvEmailPhamacy);
        pharmacyHolder.phone = (TextView)rootView.findViewById(R.id.txvPhonePhamacy);
        rootView.setTag(pharmacyHolder);
        return rootView;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        PharmacyHolder pharmacyHolder = (PharmacyHolder)view.getTag();

        pharmacyHolder.name.setText(cursor.getString(1));
        pharmacyHolder.phone.setText(cursor.getString(4));
        pharmacyHolder.email.setText(cursor.getString(5));

    }

    @Override
    public Object getItem(int position) {

        getCursor().moveToPosition(position);
        Pharmacy pharmacy = new Pharmacy();
        pharmacy.setId(getCursor().getLong(0));
        pharmacy.setName(getCursor().getString(1));
        pharmacy.setCif(getCursor().getString(2));
        pharmacy.setAddress(getCursor().getString(3));
        pharmacy.setPhone(getCursor().getString(4));
        pharmacy.setEmail(getCursor().getString(5));
        return pharmacy;
    }
}
