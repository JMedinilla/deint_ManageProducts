package com.jsw.MngProductDatabase.Fragments;

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

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Spinner;

import com.jsw.MngProductDatabase.Model.Product;
import com.jsw.MngProductDatabase.R;
import com.jsw.MngProductDatabase.interfaces.IProduct;
import com.squareup.picasso.Picasso;

import java.util.Random;

public class ManageProduct_Fragment extends Fragment {

    TextInputLayout mName, mTrademark, mDosage, mStock, mPrice, mDescription, mUrl;
    ImageView mImage;
    Spinner mCategory;
    Product p;

    FloatingActionButton mFabSave;
    IManageListener mCallBack;

    public static ManageProduct_Fragment getInstance(Bundle args){
        ManageProduct_Fragment fragment = new ManageProduct_Fragment();
        if(args != null)
            fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null)
            this.p = getArguments().getParcelable(IProduct.PRODUCT_KEY);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle args) {
        super.onCreateView(inflater, container,  args);
        View rootView = inflater.inflate(R.layout.fragment_add_product, container, false);
        mImage = (ImageView) rootView.findViewById(R.id.ib_imagen);
        mName = (TextInputLayout) rootView.findViewById(R.id.til_nombre);
        mTrademark = (TextInputLayout) rootView.findViewById(R.id.til_marca);
        mDosage = (TextInputLayout) rootView.findViewById(R.id.til_dosage);
        mStock = (TextInputLayout) rootView.findViewById(R.id.til_stock);
        mPrice = (TextInputLayout) rootView.findViewById(R.id.til_price);
        mUrl = (TextInputLayout) rootView.findViewById(R.id.til_imageurl);
        mDescription = (TextInputLayout) rootView.findViewById(R.id.til_descripcion);
        mFabSave = (FloatingActionButton)rootView.findViewById(R.id.fab_guardar);
        mCategory = (Spinner)rootView.findViewById(R.id.spinner);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(p != null){;
            //mImage.setImageResource(p.getImage());
            mName.getEditText().setText(p.getName());
            mTrademark.getEditText().setText(p.getBrand());
            mDosage.getEditText().setText(p.getDosage());
            mStock.getEditText().setText(String.valueOf(p.getStock()));
            mPrice.getEditText().setText(String.valueOf(p.getPrice()));
            mUrl.getEditText().setText(p.getImage());
            mCategory.setSelection(0);
            mDescription.getEditText().setText(p.getDescription());
        }

        mFabSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save();
            }
        });

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mCallBack = (IManageListener)activity;
    }

    private void save(){

        mCallBack.saveProduct(p, new Product(
                new Random().nextInt(),
                mName.getEditText().getText().toString(),
                mDescription.getEditText().getText().toString(),
                mTrademark.getEditText().getText().toString(),
                mDosage.getEditText().getText().toString(),
                Double.valueOf(mPrice.getEditText().getText().toString()),
                Integer.valueOf(mStock.getEditText().getText().toString()),
                mUrl.getEditText().getText().toString(),
                1));
    }

    public interface IManageListener{
        void saveProduct(Product oldProduct, Product newProduct);
    }
}
