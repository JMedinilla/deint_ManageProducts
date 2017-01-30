package com.afg.MngProductDatabase.interfaces;

import android.content.Context;
import android.database.Cursor;

public interface IPharmacyPresenter {

    interface View{


        Context getContext();

        void setCursorPharmacy(Cursor cursor);
    }

    void getAllPharmacies();
}
