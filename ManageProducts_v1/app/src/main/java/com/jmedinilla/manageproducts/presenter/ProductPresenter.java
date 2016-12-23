package com.jmedinilla.manageproducts.presenter;

import android.content.Context;
import android.text.TextUtils;
import com.jmedinilla.manageproducts.interfaces.IProductMvp;
import com.jmedinilla.manageproducts.R;

/**
 * Class created by JMedinilla on 2016-10-20
 *
 * Presenter for the Product MVP
 */
public class ProductPresenter implements IProductMvp.Presenter {

    private IProductMvp.View view;

    public ProductPresenter(IProductMvp.View view) {
        this.view = view;
    }

    /**
     * Method that verifies if the product meets the requirements
     * @param name Name of the product
     * @param description Description for the product
     * @param concentration Concentration of the product
     * @param brand Brand of the product
     * @param price Price of the product
     * @param stock How many products are available
     * @return True of False if it's a valid product
     */
    @Override
    public boolean validateProduct(String name, String description, String concentration, String brand, double price, int stock, int image) {
        boolean result = false;
        //Name cannot be empty
        if (TextUtils.isEmpty(name)) {
            view.setProductMessageError(((Context)view).getResources().getString(R.string.producterror_empty_name), R.id.edtProductName);
        }
        //Name too long
        else if (name.length() > 40) {
            view.setProductMessageError(((Context)view).getResources().getString(R.string.producterror_long_name), R.id.edtProductName);
        }
        //Description cannot be empty
        else if (TextUtils.isEmpty(description)) {
            view.setProductMessageError(((Context)view).getResources().getString(R.string.producterror_empty_description), R.id.edtProductDescription);
        }
        //Description too long
        else if (description.length() > 200) {
            view.setProductMessageError(((Context)view).getResources().getString(R.string.producterror_long_description), R.id.edtProductDescription);
        }
        //Concentration cannot be empty
        else if (TextUtils.isEmpty(concentration)) {
            view.setProductMessageError(((Context)view).getResources().getString(R.string.producterror_empty_concentration), R.id.edtProductConcentration);
        }
        //Concentration too long
        else if (concentration.length() > 40) {
            view.setProductMessageError(((Context)view).getResources().getString(R.string.producterror_long_concentration), R.id.edtProductConcentration);
        }
        //Brand cannot be empty
        else if (TextUtils.isEmpty(brand)) {
            view.setProductMessageError(((Context)view).getResources().getString(R.string.producterror_empty_brand), R.id.edtProductBrand);
        }
        //Brand too long
        else if (brand.length() > 40) {
            view.setProductMessageError(((Context)view).getResources().getString(R.string.producterror_long_brand), R.id.edtProductBrand);
        }
        //Price cannot be empty
        else if (TextUtils.isEmpty(String.valueOf(price))) {
            view.setProductMessageError(((Context)view).getResources().getString(R.string.producterror_empty_price), R.id.edtProductPrice);
        }
        //Stock cannot be empty
        else if (TextUtils.isEmpty(String.valueOf(stock))) {
            view.setProductMessageError(((Context)view).getResources().getString(R.string.producterror_empty_stock), R.id.edtProductStock);
        }
        //The Product meets the requirements
        else {
            result = true;
        }
        return result;
    }
}
