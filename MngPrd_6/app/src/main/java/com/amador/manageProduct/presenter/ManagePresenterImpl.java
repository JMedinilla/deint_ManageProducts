package com.amador.manageProduct.presenter;

import com.amador.manageProduct.interfaces.ManagePresenter;
import com.amador.manageProduct.model.Product;
import com.amador.manageProduct.repository.ProductRepositoryimpl;
import com.amador.manageProduct.view.ManageProductFragment;

/**
 * Created by amador on 7/12/16.
 */

public class ManagePresenterImpl implements ManagePresenter {

    private View view;
    private ProductRepositoryimpl repository;

    public ManagePresenterImpl(ManageProductFragment view) {

        this.view = view;
        this.repository = ProductRepositoryimpl.getInstance();
    }

    @Override
    public void addProduct(Product product) {

        repository.addProduct(product);

    }

    @Override
    public void updateProduct(Product product) {

        repository.updateProduct(product);

    }

    @Override
    public void onDestroy() {

    }
}
