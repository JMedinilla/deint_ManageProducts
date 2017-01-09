package com.amador.manageProduct.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amador.manageProduct.R;
import com.amador.manageProduct.model.Product;
import com.amador.manageProduct.model.ProductApplication;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * @author Amador Fernandez Gonzalez
 * Clase adaptador para la lista de productos que implementa el
 * patron holder hereda de
 * @see android.support.v7.widget.RecyclerView.Adapter
 *
 * */
public class ProductAdapterRecicler extends RecyclerView.Adapter<ProductAdapterRecicler.ProductViewHolder> {

    private List<Product> data;
    private Context context;

    /**
     * Constructor de instancia de la clase
     *
     * @param context contexto de la activity que lo usa
     **/
    public ProductAdapterRecicler(Context context) {

        this.context = context;
        data = new ArrayList<Product>(((ProductApplication) context.getApplicationContext()).getProducts());

    }

    /**
     * Metodo para ordenar los productos segun el tipo de orden que se desee
     *
     * @param type tipo de orden que se desea, true sera ascendente y false descendente
     **/
    public void orderBy(boolean type){
        data.clear();
        if(type) {
            Collections.sort(((ProductApplication) context.getApplicationContext()).getProducts());
            data = new ArrayList<Product>(((ProductApplication) context.getApplicationContext()).getProducts());
        }else {

            Collections.sort(((ProductApplication) context.getApplicationContext()).getProducts(), Collections.<Product>reverseOrder());
            data = new ArrayList<Product>(((ProductApplication) context.getApplicationContext()).getProducts());
        }
        notifyDataSetChanged();
    }


    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View item = LayoutInflater.from(context).inflate(R.layout.item_list_product, null);
        return new ProductViewHolder(item);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {

        holder.imv.setImageResource(data.get(position).getImage());
        holder.texNameProduct.setText(data.get(position).getName());
        holder.texBrandProduct.setText(data.get(position).getBrand());
        holder.texPriceProduct.setText(data.get(position).getPriceFormat());


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    /**
     * Clase para implementar y usar el patron holder
     *
     * @see android.support.v7.widget.RecyclerView.ViewHolder
     **/
    public static class ProductViewHolder extends  RecyclerView.ViewHolder{

        ImageView imv;
        TextView texNameProduct;
        TextView texBrandProduct;
        TextView texPriceProduct;

        public ProductViewHolder(View item){
            super(item);
            texNameProduct = (TextView)item.findViewById(R.id.textProductName);
            texBrandProduct = (TextView)item.findViewById(R.id.txvStockPro);
            texPriceProduct = (TextView)item.findViewById(R.id.txvPricePro);
            imv = (ImageView)item.findViewById(R.id.imvProduct);

        }


    }




}
