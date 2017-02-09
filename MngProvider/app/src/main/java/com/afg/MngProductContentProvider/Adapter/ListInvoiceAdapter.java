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

import com.afg.MngProductContentProvider.R;

/**
 * Created by amador on 4/02/17.
 */

public class ListInvoiceAdapter extends CursorAdapter {


    class InvoiceHolder{

        TextView txvNamePharmacy, txvDate, txvStatus;
    }

    public ListInvoiceAdapter(Context context) {
        super(context, null, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {

        View rootView = LayoutInflater.from(context).inflate(R.layout.item_list_invoice, null);
        InvoiceHolder holder = new InvoiceHolder();
        holder.txvNamePharmacy = (TextView)rootView.findViewById(R.id.txvNamePhamacy);
        holder.txvDate = (TextView)rootView.findViewById(R.id.txvDateInvoice);
        holder.txvStatus = (TextView)rootView.findViewById(R.id.txvStatusInvoice);
        rootView.setTag(holder);
        return rootView;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        InvoiceHolder holder = (InvoiceHolder)view.getTag();
        holder.txvNamePharmacy.setText(cursor.getString(0));
        holder.txvStatus.setText(cursor.getString(2));
        holder.txvDate.setText(cursor.getString(1));
    }
}
