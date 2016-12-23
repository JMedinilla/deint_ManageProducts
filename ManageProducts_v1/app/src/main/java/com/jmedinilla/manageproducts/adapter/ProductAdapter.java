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
public class ProductAdapter extends ArrayAdapter<Product> {
    public ProductAdapter(Context context) {
        super(context, R.layout.manage_product_activity, ((ProductApplication)context.getApplicationContext()).getProducts());
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        ProductHolder productHolder;
        LayoutInflater layoutInflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (view == null) {
            //LayoutInflater layoutInflater = LayoutInflater.from(context);
            productHolder = new ProductHolder();
        }
        else {
            productHolder = (ProductHolder)view.getTag();
        }

        if (position % 2 == 0) {
            view = layoutInflater.inflate(R.layout.adapter_image_first, parent, false);

            productHolder.img = (ImageView)view.findViewById(R.id.product_image);
            productHolder.title = (TextView) view.findViewById(R.id.product_title);
            productHolder.subtitle = (TextView) view.findViewById(R.id.product_subtitle);
            productHolder.description = (TextView) view.findViewById(R.id.product_description);
            view.setTag(productHolder);
        }
        else {
            view = layoutInflater.inflate(R.layout.adapter_image_second, parent, false);

            productHolder.img = (ImageView)view.findViewById(R.id.product_image);
            productHolder.title = (TextView) view.findViewById(R.id.product_title);
            productHolder.subtitle = (TextView) view.findViewById(R.id.product_subtitle);
            productHolder.description = (TextView) view.findViewById(R.id.product_description);
            view.setTag(productHolder);
        }

        Product prd = getItem(position);
        if (prd != null) {
            productHolder.img.setBackgroundResource(prd.getImage());
            productHolder.title.setText(prd.getName() + " (" + prd.getConcentration() + ")");
            productHolder.subtitle.setText(prd.getBrand() + " (" + prd.getFormatedPrice() + ")");
            productHolder.description.setText(prd.getDescription());
        }

        return view;
    }

    private class ProductHolder {
        ImageView img;
        TextView title;
        TextView subtitle;
        TextView description;
    }
}
