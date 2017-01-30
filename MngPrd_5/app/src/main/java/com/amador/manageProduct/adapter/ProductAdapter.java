package com.amador.manageProduct.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.amador.manageProduct.R;
import com.amador.manageProduct.model.Product;
import com.amador.manageProduct.repository.ProductRepositoryimpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by usuario on 18/11/16.
 */

public class ProductAdapter extends ArrayAdapter<Product> {

    private Context context;
    ProductRepositoryimpl repositoryimpl;

    /**
     * Se pasa como parametro en la llamada al super el context, el id del item_layout y
     * una copia local DIFERENTE del origen.
     * @param context pos el contexto
     * */
    public ProductAdapter(Context context) {

        super(context, R.layout.item_list_product,
                new ArrayList<Product>(ProductRepositoryimpl.getInstance().getAllProduct()));
        repositoryimpl = ProductRepositoryimpl.getInstance();
        this.context = context;
    }

    /**
     * Metodo para ordenar los productos segun el tipo de orden que se desee
     *
     * @param type tipo de orden que se desea, true sera ascendente y false descendente
     **/
    public void orderBy(boolean type){

        if(type){

            sort(Product.NAME_COMPARATOR);

        }else {

            sort(Collections.<Product>reverseOrder());

        }

    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        ProductHolder p = new ProductHolder();

        if(view == null){

            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.item_list_product, null);
            p.imv = (ImageView)view.findViewById(R.id.imvProduct);
            p.texNameProduct = (TextView)view.findViewById(R.id.textProductName);
            p.texPriceProduct = (TextView)view.findViewById(R.id.txvPricePro);
            p.texBrandProduct = (TextView)view.findViewById(R.id.txvStockPro);

            view.setTag(p);

        }else{


            p = (ProductHolder)view.getTag();
        }

        p.imv.setImageResource(R.drawable.pill);
        p.texNameProduct.setText(getItem(position).getName());
        p.texPriceProduct.setText(getItem(position).getPriceFormat());
        p.texBrandProduct.setText(String.valueOf(getItem(position).getStock()));

        return view;

    }

    public void addProduct(Product p) {
        repositoryimpl.addProduct(p);
        add(p);
    }

    public void editProduct(Product oldProduct, Product newProduct) {

        remove(oldProduct);
        add(newProduct);


    }

    public void updateProduct(List<Product> products) {
        clear();
        addAll(products);
    }

    class ProductHolder{

        ImageView imv;
        TextView texNameProduct;
        TextView texBrandProduct;
        TextView texPriceProduct;

    }
}
