package com.afg.MngProductDatabase.Fragments;


import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;

import com.afg.MngProductDatabase.Model.Category;
import com.afg.MngProductDatabase.Model.Product;
import com.afg.MngProductDatabase.Presenter.CategoryPresenter;
import com.afg.MngProductDatabase.R;
import com.afg.MngProductDatabase.database.ManageProductContract;
import com.afg.MngProductDatabase.interfaces.ICategoryPresenter;
import com.afg.MngProductDatabase.interfaces.IProduct;

import java.util.Random;

public class ManageProduct_Fragment extends Fragment implements ICategoryPresenter.View {

    TextInputLayout mName, mTrademark, mDosage, mStock, mPrice, mDescription, mUrl;
    ImageView mImage;
    Spinner mCategory;
    Product p;
    boolean update = false;
    SimpleCursorAdapter adapter;
    private CategoryPresenter presenter;

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
        presenter = new CategoryPresenter(this);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String[] from = {ManageProductContract.CategoryEntry.COLUMN_NAME};
        int[] to = {android.R.id.text1};

        adapter = new SimpleCursorAdapter(getContext(),android.R.layout.simple_spinner_dropdown_item,
                null, from, to, 0);
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mCategory.setAdapter(adapter);


        if(p != null){;
            //mImage.setImageResource(p.getImage());
            mName.getEditText().setText(p.getName());
            mTrademark.getEditText().setText(p.getBrand());
            mDosage.getEditText().setText(p.getDosage());
            mStock.getEditText().setText(p.getStock());
            mPrice.getEditText().setText(String.valueOf(p.getPrice()));
            mUrl.getEditText().setText(p.getImage());
            mCategory.setSelection(0);
            mDescription.getEditText().setText(p.getDescription());
            update = true;
        }

        mFabSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save();
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.getAllCategoies();

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mCallBack = (IManageListener)activity;
    }

    private void save(){

        Cursor cursor = ((SimpleCursorAdapter)mCategory.getAdapter()).getCursor();
        cursor.moveToPosition(mCategory.getSelectedItemPosition());
        int i = cursor.getInt(0);

        mCallBack.saveProduct(p, new Product(

                mName.getEditText().getText().toString(),
                mDescription.getEditText().getText().toString(),
                mTrademark.getEditText().getText().toString(),
                mDosage.getEditText().getText().toString(),
                Double.valueOf(mPrice.getEditText().getText().toString()),
                mStock.getEditText().getText().toString(),
                mUrl.getEditText().getText().toString(),i
                ));
    }

    @Override
    public void setCursorCategory(Cursor cursor) {

      //  adapter.swapCursor(cursor);
        //Cierra el anterior cursor y abre el nuevo.

        if(cursor != null)
            adapter.swapCursor(cursor);

    }

    @Override
    public void onDetach() {
        adapter = null;
        super.onDetach();

    }

    public interface IManageListener{
        void saveProduct(Product oldProduct, Product newProduct);
    }
}
