package com.jmedinilla.manageproducts.presenter;

import android.content.Context;
import android.text.TextUtils;

import com.jmedinilla.manageproducts.interfaces.FormPresenter;
import com.jmedinilla.manageproducts.R;

/**
 * Class created by JMedinilla on 2016-10-20
 * <p>
 * Product presenter
 */
public class FormPresenterImpl implements FormPresenter.Presenter {

    private FormPresenter.View view;

    /**
     * Presenter constructor
     *
     * @param view View he's watching
     */
    public FormPresenterImpl(FormPresenter.View view) {
        this.view = view;
    }

    /**
     * Method that verifies the product requirements
     *
     * @param name          Name of the product
     * @param description   Description for the product
     * @param concentration Concentration of the product
     * @param brand         Brand of the product
     * @param price         Price of the product
     * @param stock         How many products are available
     * @return Valid product
     */
    @Override
    public boolean validateProduct(String name, String description, String concentration, String brand, double price, int stock, int image) {
        boolean result = false;

        if (TextUtils.isEmpty(name)) {
            view.setProductMessageError(((Context) view).getResources().getString(R.string.producterror_empty_name), R.id.edtProductName);
        } else if (name.length() > 40) {
            view.setProductMessageError(((Context) view).getResources().getString(R.string.producterror_long_name), R.id.edtProductName);
        } else if (TextUtils.isEmpty(description)) {
            view.setProductMessageError(((Context) view).getResources().getString(R.string.producterror_empty_description), R.id.edtProductDescription);
        } else if (description.length() > 250) {
            view.setProductMessageError(((Context) view).getResources().getString(R.string.producterror_long_description), R.id.edtProductDescription);
        } else if (TextUtils.isEmpty(concentration)) {
            view.setProductMessageError(((Context) view).getResources().getString(R.string.producterror_empty_concentration), R.id.edtProductConcentration);
        } else if (concentration.length() > 90) {
            view.setProductMessageError(((Context) view).getResources().getString(R.string.producterror_long_concentration), R.id.edtProductConcentration);
        } else if (TextUtils.isEmpty(brand)) {
            view.setProductMessageError(((Context) view).getResources().getString(R.string.producterror_empty_brand), R.id.edtProductBrand);
        } else if (brand.length() > 90) {
            view.setProductMessageError(((Context) view).getResources().getString(R.string.producterror_long_brand), R.id.edtProductBrand);
        } else if (TextUtils.isEmpty(String.valueOf(price))) {
            view.setProductMessageError(((Context) view).getResources().getString(R.string.producterror_empty_price), R.id.edtProductPrice);
        } else if (TextUtils.isEmpty(String.valueOf(stock))) {
            view.setProductMessageError(((Context) view).getResources().getString(R.string.producterror_empty_stock), R.id.edtProductStock);
        } else {
            result = true;
        }
        return result;
    }
}
