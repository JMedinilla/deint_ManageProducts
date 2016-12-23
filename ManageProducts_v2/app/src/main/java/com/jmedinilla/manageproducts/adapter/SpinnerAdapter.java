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

/**
 * Class created by JMedinilla on 2016-10-21
 * <p>
 * My personal adapter for the products ListView
 */
public class SpinnerAdapter extends ArrayAdapter<String> {
    private Context ctxt;
    private String[] texts;
    private Integer[] images;

    /**
     * Adapter constructor
     *
     * @param context Application's context
     * @param texts   String array with the text content of each row
     * @param images  Int array with the images of each row
     */
    public SpinnerAdapter(Context context, String[] texts, Integer[] images) {
        super(context, R.layout.adapter_spinner, R.id.spinner_title, texts);
        this.ctxt = context;
        this.texts = texts;
        this.images = images;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        return getCustomView(position, parent);
    }

    @Override
    public View getDropDownView(int position, View convertView, @NonNull ViewGroup parent) {
        return getCustomView(position, parent);
    }

    private View getCustomView(int position, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) ctxt.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.adapter_spinner, parent, false);

        TextView textView = (TextView) row.findViewById(R.id.spinner_title);
        textView.setText(texts[position]);

        ImageView imageView = (ImageView) row.findViewById(R.id.spinner_image);
        imageView.setImageResource(images[position]);

        return row;
    }
}
