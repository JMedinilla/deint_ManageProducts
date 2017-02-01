package com.afg.MngProductDatabase.Adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.afg.MngProductDatabase.Model.Pharmacy;
import com.afg.MngProductDatabase.R;

public class PharmacyAdapter extends CursorAdapter {

    public PharmacyAdapter(Context context) {
        super(context, null, 1);
    }

    public class PharmacyHolder {
        TextView txvNamePhamacy;
        TextView txvEmailPhamacy;
        TextView txvPhonePhamacy;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_list_pharmacy, parent, false);
        PharmacyHolder pharmacyHolder = new PharmacyHolder();
        pharmacyHolder.txvNamePhamacy = (TextView) view.findViewById(R.id.txvNamePhamacy);
        pharmacyHolder.txvEmailPhamacy = (TextView) view.findViewById(R.id.txvEmailPhamacy);
        pharmacyHolder.txvPhonePhamacy = (TextView) view.findViewById(R.id.txvPhonePhamacy);
        view.setTag(pharmacyHolder);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        PharmacyHolder pharmacyHolder = (PharmacyHolder) view.getTag();
        pharmacyHolder.txvNamePhamacy.setText(cursor.getString(1));
        pharmacyHolder.txvPhonePhamacy.setText(cursor.getString(4));
        pharmacyHolder.txvEmailPhamacy.setText(cursor.getString(5));
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
