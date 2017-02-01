package com.afg.MngProductDatabase.Presenter;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.app.LoaderManager;
import android.content.Loader;
import com.afg.MngProductDatabase.cursor.PharmacyCursorLoader;
import com.afg.MngProductDatabase.interfaces.IPharmacyPresenter;

public class PharmacyPresenter implements IPharmacyPresenter, LoaderManager.LoaderCallbacks<Cursor> {

    private IPharmacyPresenter.View view;
    private final static int PHARMACY = 1;
    private Context context;

    public PharmacyPresenter(IPharmacyPresenter.View view) {
        this.view = view;
        this.context = view.getContext();
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new PharmacyCursorLoader(this.context);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        view.setCursorPharmacy(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        view.setCursorPharmacy(null);
    }

    @Override
    public void getAllPharmacies() {
        ((Activity)context).getLoaderManager().initLoader(PHARMACY, null, this);
    }
}
