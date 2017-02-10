package com.afg.MngProductContentProvider.Fragments;


import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;

import com.afg.MngProductContentProvider.Model.Product;
import com.afg.MngProductContentProvider.Presenter.CategoryPresenter;
import com.afg.MngProductContentProvider.R;

import com.afg.MngProductContentProvider.database.DataBaseContract;
import com.afg.MngProductContentProvider.interfaces.ICategoryPresenter;
import com.afg.MngProductContentProvider.interfaces.IProduct;
import com.afg.MngProductContentProvider.utils.ImageResource;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class ManageProduct_Fragment extends Fragment implements ICategoryPresenter.View {

    TextInputLayout mName, mTrademark, mDosage, mStock, mPrice, mDescription, mUrl;
    ImageButton imageButton;
    Spinner mCategory;
    Product p;
    boolean update = false;
    SimpleCursorAdapter adapter;
    private CategoryPresenter presenter;
    Bitmap bitmap;

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
        imageButton = (ImageButton)rootView.findViewById(R.id.button2);
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
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 123);
            }
        });

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String[] from = {DataBaseContract.CategoryEntry.COLUMN_NAME};
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
            //mUrl.getEditText().setText(p.getImage());
            imageButton.setImageBitmap(ImageResource.getBitmap(p.getImage()));
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
                //mUrl.getEditText().getText().toString(),
                ImageResource.getByte(((BitmapDrawable)imageButton.getDrawable()).getBitmap()),
                i));
    }

    @Override
    public void setCursorCategory(Cursor cursor) {

      //  adapter.swapCursor(cursor);
        //Cierra el anterior cursor y abre el nuevo.

        if(cursor != null)
            adapter.swapCursor(cursor);

    }

    @Override
    public Cursor getCursor() {
        return adapter.getCursor();
    }

    @Override
    public void showMsg(String message) {

    }

    @Override
    public void onDetach() {
        adapter = null;
        super.onDetach();

    }

    public interface IManageListener{
        void saveProduct(Product oldProduct, Product newProduct);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case 123:
                if (resultCode == Activity.RESULT_OK) {
                    Uri selectedImg = data.getData();
                    InputStream inputStream = null;
                    try {
                        Bitmap bitmap = ImageResource.decodeUri(selectedImg);
                        imageButton.setImageBitmap(bitmap);
                    }
                    catch (FileNotFoundException exception) {
                        exception.printStackTrace();
                    }
                }
                break;
            default:
                break;
        }
    }
}
