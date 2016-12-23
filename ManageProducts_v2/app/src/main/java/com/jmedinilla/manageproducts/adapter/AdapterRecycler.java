package com.jmedinilla.manageproducts.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jmedinilla.manageproducts.application.ProductApplication;
import com.jmedinilla.manageproducts.R;
import com.jmedinilla.manageproducts.model.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Class created by JMedinilla on 2016-10-24
 * <p>
 * Adapter for the RecyclerView
 */
public class AdapterRecycler extends RecyclerView.Adapter<AdapterRecycler.ProductViewHolder> {

    private List<Product> products;
    private Context ctxt;

    private static final int TYPE_NAME = 1;
    private static final int TYPE_BRAND = 2;
    private static final int TYPE_PRICE = 3;
    private static final int TYPE_STOCK = 4;

    private static boolean ASC_NAME = true;
    private static boolean ASC_BRAND = true;
    private static boolean ASC_PRICE = true;
    private static boolean ASC_STOCK = true;

    /**
     * Adapter's constructor
     *
     * @param context Activity's context
     */
    public AdapterRecycler(Context context) {
        this.ctxt = context;
        this.products = new ArrayList<>(((ProductApplication) ctxt.getApplicationContext()).getProducts());
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_image, parent, false);
        return new ProductViewHolder(item);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        holder.prdImage.setBackgroundResource(products.get(position).getImage());
        holder.prdTitle.setText(products.get(position).getName() + " (" + products.get(position).getConcentration() + ")");
        holder.prdSubtitle.setText(products.get(position).getBrand() + " (" + products.get(position).getFormatedPrice() + ")");
        holder.prdDescription.setText(products.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    /**
     * @param type Int that says what is the point of view for the list to sort the elements
     */
    public void sortAlph(int type) {
        boolean ASC = false;

        if (type == TYPE_NAME) {
            ASC = ASC_NAME;
            ASC_NAME = !ASC_NAME;
        } else if (type == TYPE_BRAND) {
            ASC = ASC_BRAND;
            ASC_BRAND = !ASC_BRAND;
        } else if (type == TYPE_PRICE) {
            ASC = ASC_PRICE;
            ASC_PRICE = !ASC_PRICE;
        } else if (type == TYPE_STOCK) {
            ASC = ASC_STOCK;
            ASC_STOCK = !ASC_STOCK;
        }

        getAllProducts(((ProductApplication) ctxt.getApplicationContext()).getProducts(ASC, type));
    }

    /**
     * Personal ViewHolder for the product's list
     *
     * @see android.support.v7.widget.RecyclerView.ViewHolder
     */
    static class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView prdImage;
        TextView prdTitle;
        TextView prdSubtitle;
        TextView prdDescription;

        ProductViewHolder(View item) {
            super(item);

            prdImage = (ImageView) item.findViewById(R.id.product_image);
            prdTitle = (TextView) item.findViewById(R.id.product_title);
            prdSubtitle = (TextView) item.findViewById(R.id.product_subtitle);
            prdDescription = (TextView) item.findViewById(R.id.product_description);
        }
    }

    /**
     * Method that gives the user the list cleared and filled again with the sorted elements
     *
     * @param productList Replacement for the last list
     */
    private void getAllProducts(List<Product> productList) {
        this.products.clear();
        this.products.addAll(productList);
        notifyDataSetChanged();
    }

    /**
     * Method to notify that the user created a new element for the list
     */
    public void productAddedNotify() {
        this.products = new ArrayList<>(((ProductApplication) ctxt.getApplicationContext()).getProducts());
        notifyDataSetChanged();
    }
}
