package com.jmedinilla.manageproducts.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jmedinilla.manageproducts.R;
import com.jmedinilla.manageproducts.application.ProductApplication;
import com.jmedinilla.manageproducts.model.Product;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Class created by JMedinilla on 2016-10-24
 * <p>
 * Adapter for the RecyclerView
 *
 * No es necesario llamar al método notifyDataSetChanged() después de add(), insert(),
 * remove(), clear(), sort()... porque estos métodos los llaman automáticamente
 * setNotifyOnChange=true y se utiliza la copia local
 * Si se utilizara la lista del DAO o Application entonces sí que habría que utilizarlo
 */
public class AdapterList extends ArrayAdapter<Product> {

    private static boolean ASC_NAME;
    private static boolean ASC_PRICE;
    private static boolean ASC_STOCK;
    private static boolean ASC_BRAND;
    private static final int TYPE_NAME = 1;
    private static final int TYPE_BRAND = 2;
    private static final int TYPE_PRICE = 3;
    private static final int TYPE_STOCK = 4;

    /**
     * We use as third parameter a new ArrayList with the Application's element
     * <p>
     * The local copy is different from the origin
     *
     * @param context Activity's context
     */
    public AdapterList(Context context) {
        super(context, R.layout.adapter_image,
                new ArrayList<>(((ProductApplication) context.getApplicationContext()).getProducts()));

        ASC_NAME = true;
        ASC_PRICE = true;
        ASC_STOCK = true;
        ASC_BRAND = true;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        ProductHolder productHolder;
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (view == null) {
            productHolder = new ProductHolder();
            view = layoutInflater.inflate(R.layout.adapter_image, parent, false);
            view.setTag(productHolder);

            productHolder.img = (ImageView) view.findViewById(R.id.product_image);
            productHolder.title = (TextView) view.findViewById(R.id.product_title);
            productHolder.subtitle = (TextView) view.findViewById(R.id.product_subtitle);
            productHolder.description = (TextView) view.findViewById(R.id.product_description);
        } else {
            productHolder = (ProductHolder) view.getTag();
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

    public void addProduct(Product product) {
        add(product);
        notifyDataSetChanged();
    }

    public void addProductPosition(Product product, int position) {
        insert(product, position);
        notifyDataSetChanged();
    }

    public void removeProduct(Product product) {
        remove(product);
        notifyDataSetChanged();
    }

    public void removePosition(int position) {
        remove(getItem(position));
        notifyDataSetChanged();
    }

    /**
     * Holder for the product list's items
     */
    private class ProductHolder {
        ImageView img;
        TextView title;
        TextView subtitle;
        TextView description;
    }

    /**
     * @param type Int that says what is the point of view for the list to sort the elements
     */
    public void sortAlph(int type) {
        switch (type) {
            case TYPE_NAME:
                if (ASC_NAME) {
                    sort(Product.NAME_COMPARATOR_ASC);
                    ASC_NAME = !ASC_NAME;
                    ASC_PRICE = true;
                    ASC_STOCK = true;
                    ASC_BRAND = true;
                }
                else {
                    sort(Collections.reverseOrder());
                }
                break;
            case TYPE_PRICE:
                if (ASC_PRICE) {
                    sort(Product.PRICE_COMPARATOR_ASC);
                    ASC_PRICE = !ASC_PRICE;
                    ASC_NAME = true;
                    ASC_STOCK = true;
                    ASC_BRAND = true;
                }
                else {
                    sort(Collections.<Product>reverseOrder());
                }
                break;
            case TYPE_STOCK:
                if (ASC_STOCK) {
                    sort(Product.STOCK_COMPARATOR_ASC);
                    ASC_STOCK = !ASC_STOCK;
                    ASC_PRICE = true;
                    ASC_NAME = true;
                    ASC_BRAND = true;
                }
                else {
                    sort(Collections.<Product>reverseOrder());
                }
                break;
            case TYPE_BRAND:
                if (ASC_BRAND) {
                    sort(Product.BRAND_COMPARATOR_ASC);
                    ASC_BRAND = !ASC_BRAND;
                    ASC_PRICE = true;
                    ASC_STOCK = true;
                    ASC_NAME = true;

                }
                else {
                    sort(Collections.<Product>reverseOrder());
                }
                break;
        }
    }
}
