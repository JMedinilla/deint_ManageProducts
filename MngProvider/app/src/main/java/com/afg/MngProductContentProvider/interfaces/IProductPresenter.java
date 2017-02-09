package com.afg.MngProductContentProvider.interfaces;

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
import android.database.Cursor;

import com.afg.MngProductContentProvider.Model.Product;

/**
 * Created by usuario on 9/12/16.
 */

public interface IProductPresenter {

    void loadProducts();
    void addProduct(Product p);
    Product getProduct(int id);
    void deleteProduct(Product product);
    void onDestroy();

    interface View{
        void showProduct();
        void showEmptyState(boolean show);
        void showMessage(String message, Product product);
        void showProgressDialog();
        void dismissProgressDialog();

        Context getContext();

        void setCursorPharmacy(Cursor cursor);
    }
}
