package com.amador.manageProduct.view;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.amador.manageProduct.R;
import com.amador.manageProduct.interfaces.ManagePresenter;
import com.amador.manageProduct.interfaces.ManageProductListener;
import com.amador.manageProduct.model.Product;
import com.amador.manageProduct.presenter.ManagePresenterImpl;

import java.util.List;

public class ManageProductFragment extends Fragment implements ManagePresenter.View {



    private EditText edtName, edtBrand, edtDescrition, edtDosage, edtPrice, edtStock;
    private TextInputLayout tinName, tinBrand, tinDescription, tinDosage, tinPrice, tinStock;
    private ImageView imvProduct;
    private FloatingActionButton flt;
    public static final String MANAGE_KEY = "manage";
    public static final int ADD_PRODUCT = 1;
    public static final int EDIT_PRODUCT = 2;
    private int modeOpen;
    private Product product;
    private ManageProductListener mCallBack;
    private ManagePresenterImpl presenter;

    public static ManageProductFragment newInstance(Bundle arguments) {


        ManageProductFragment fragment = new ManageProductFragment();
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        mCallBack = (ManageProductListener)activity;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_register_product, null);

        if(getArguments() != null){

            product = getArguments().getParcelable(ListProductFragment.PRODUCT_KEY);
            modeOpen = EDIT_PRODUCT;

        }else {

            modeOpen = ADD_PRODUCT;

        }

        presenter = new ManagePresenterImpl(this);
        tinName = (TextInputLayout)rootView.findViewById(R.id.tinNameProduct);
        tinDescription  = (TextInputLayout)rootView.findViewById(R.id.tinDescriptionProduct);
        tinDosage = (TextInputLayout)rootView.findViewById(R.id.tinDosageProduct);
        tinBrand = (TextInputLayout)rootView.findViewById(R.id.tinBrandProduct);
        tinPrice = (TextInputLayout)rootView.findViewById(R.id.tinSaleProduct);
        tinStock = (TextInputLayout)rootView.findViewById(R.id.tinStockProduct);
        edtName = (EditText)rootView.findViewById(R.id.edtNameProduct);
        edtDescrition = (EditText)rootView.findViewById(R.id.edtDescriptionProduct);
        edtDosage = (EditText)rootView.findViewById(R.id.edtDosageProduct);
        edtBrand = (EditText)rootView.findViewById(R.id.edtBrandProduct);
        edtPrice = (EditText)rootView.findViewById(R.id.edtSaleProduct);
        edtStock = (EditText)rootView.findViewById(R.id.edtStockProduct);
        flt = (FloatingActionButton)rootView.findViewById(R.id.floatBtnSaveProduct);

        flt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(modeOpen == ADD_PRODUCT){

                    product = new Product(edtName.getText().toString(), edtDescrition.getText().toString(),
                            edtBrand.getText().toString(), edtDosage.getText().toString(), Double.parseDouble(edtPrice.getText().toString()),
                            Integer.parseInt(edtStock.getText().toString()), R.drawable.pill);
                    presenter.addProduct(product);
                    mCallBack.onListProductListener(getString(R.string.add_ok));

                }else {

                    product.setName(edtName.getText().toString());
                    product.setBrand(edtBrand.getText().toString());
                    product.setDescription(edtDescrition.getText().toString());
                    product.setStock(Integer.parseInt(edtStock.getText().toString()));
                    product.setPrice(Double.parseDouble(edtPrice.getText().toString()));
                    product.setDosage(edtDosage.getText().toString());
                    presenter.updateProduct(product);
                    mCallBack.onListProductListener(getString(R.string.update_ok));


                }

            }
        });


        try {

            if (product != null) {
                edtName.setText(product.getName());
                edtDescrition.setText(product.getDescription());
                edtBrand.setText(product.getDosage());
                edtBrand.setText(product.getBrand());
                edtPrice.setText(String.valueOf(product.getPrice()));
                edtStock.setText(String.valueOf(product.getStock()));
            }
        }
        catch (NullPointerException e) {
            e.printStackTrace();
        }

        return rootView;
    }

    private void saveProduct(){}

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void showProducts(List<Product> products) {

    }

    @Override
    public void showMessageDelete(Product product) {

    }
}
