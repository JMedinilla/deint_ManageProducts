package com.jmedinilla.manageproducts.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jmedinilla.manageproducts.ProductApplication;
import com.jmedinilla.manageproducts.R;
import com.jmedinilla.manageproducts.model.Product;

/**
 * Class created by JMedinilla on 2016-10-21
 *
 * My personal adapter for the products ListView (B -> Faster than A)
 */
public class
ProductAdapter_B extends ArrayAdapter<Product> {
    public ProductAdapter_B(Context context) {
        super(context, R.layout.adapter_image_first, ((ProductApplication)context.getApplicationContext()).getProducts());
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            //LayoutInflater layoutInflater = LayoutInflater.from(context);
            LayoutInflater layoutInflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view = layoutInflater.inflate(R.layout.adapter_image_first, parent, false);
        }

        //The adaptar has all the Products
        Product prd = getItem(position);
        if (prd != null) {
            //Components of each row
            ImageView img = (ImageView)view.findViewById(R.id.product_image);
            TextView title = (TextView)view.findViewById(R.id.product_title);
            TextView subtitle = (TextView)view.findViewById(R.id.product_subtitle);
            TextView description = (TextView)view.findViewById(R.id.product_description);

            if (img != null && title != null && subtitle != null && description != null) {
                img.setBackgroundResource(prd.getImage());
                title.setText(prd.getName() + " (" + prd.getConcentration() + ")");
                subtitle.setText(prd.getBrand() + " (" + prd.getFormatedPrice() + ")");
                description.setText(prd.getDescription());
            }

        }
        return view;
    }
}
