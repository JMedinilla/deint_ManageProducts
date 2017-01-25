package com.jsw.MngProductDatabase.Adapter;

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
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jsw.MngProductDatabase.Model.Product;
import com.jsw.MngProductDatabase.R;
import com.jsw.MngProductDatabase.database.DatabaseManager;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.Collections;

/**
 * Created by usuario on 18/11/16.
 */

public class ProductAdapter extends ArrayAdapter<Product> implements Serializable {

    private Context contexto;
    private boolean ASC = true;

    public ProductAdapter(Context context) {

        //Cuando aqui ponemos un tercer parametro, teneos que entener que el array interno es igual a este.
        //Por eso cuando hacia un clear se borraba el del DAO.
        //Para evitarlo o le hacemos un new ArayList (Lourdes) o a this le hacemos un addAll (Yo)
        super(context, R.layout.item_product);
        this.contexto = context;
        this.addAll(DatabaseManager.getInstance().getAllProducts());
        refreshView();
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View item = convertView;
        ProductHolder p;

        if (convertView == null){
            LayoutInflater inflater = LayoutInflater.from(contexto);

            item = inflater.inflate(R.layout.item_product, null);

            p = new ProductHolder();


            p.img = (ImageView)item.findViewById(R.id.iv_img);
            p.name = (TextView)item.findViewById(R.id.tv_nombre);
            p.stock = (TextView)item.findViewById(R.id.tv_stock);
            p.precio = (TextView)item.findViewById(R.id.tv_precio);

            item.setTag(p);

        }else
            p = (ProductHolder) item.getTag();

        Picasso.with(contexto).load(getItem(position).getImage()).into(p.img);
        p.name.setText(getItem(position).getName());
        p.stock.setText(String.valueOf(getItem(position).getStock()));
        p.precio.setText(String.valueOf(getItem(position).getPrice()));

        return item;
    }

    public void sortAlphabetically(){
        ASC = !ASC;

        if(ASC)
            sort(Product.NAME_COMPARATOR);
        else
            sort(Collections.reverseOrder());

        notifyDataSetChanged();
    }

    public void addProduct(Product product){
        add(product);
        refreshView();
    }

    public void removeProduct(Product product){
        remove(product);
        refreshView();
        notifyDataSetChanged();
    }

    public void updateListProduct(){
        this.clear();
        this.addAll(DatabaseManager.getInstance().getAllProducts());
        refreshView();
    }

    private void hideList(boolean hide){

    }

    public void addAt(Product product, int position){
        insert(product, position);
        notifyDataSetChanged();
        refreshView();
    }

    public void deleteProduct(Product product){
        remove(product);
        notifyDataSetChanged();
        refreshView();
    }

    private void refreshView(){
        ASC = !ASC;
        sortAlphabetically();
    }

    class ProductHolder{
        ImageView img;
        TextView name;
        TextView stock;
        TextView precio;
    }
}
