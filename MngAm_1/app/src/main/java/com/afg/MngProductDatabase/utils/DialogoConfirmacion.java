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

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import com.afg.MngProductDatabase.Model.Product;
import com.afg.MngProductDatabase.Presenter.ProductPresenter;
import com.afg.MngProductDatabase.interfaces.IProductPresenter;

/**
 * Created by usuario on 21/11/16.
 */

public class DialogoConfirmacion extends DialogFragment {
    Product p;
    IProductPresenter mPresenter;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        p = (Product)getArguments().getParcelable("product");
        return crear(p.getName());
    }

    public Dialog crear(String name){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("¿Desea eliminar el elemento " + name + "?")
                .setTitle("Confirmación")
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        mPresenter.deleteProduct(p);
                        dialog.cancel();
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.cancel();
                    }
                });

        return builder.create();
    }

    public void setPresenter(ProductPresenter presenter){
        this.mPresenter = presenter;
    }
}
