package com.amador.manageProduct.presenter;

import com.amador.manageProduct.interfaces.ManagePresenter;
import com.amador.manageProduct.interfaces.ProductPresenter;
import com.amador.manageProduct.model.Product;
import com.amador.manageProduct.repository.ProductRepositoryimpl;

/**
 * Created by amador on 7/12/16.
 */

public class ProductPresenterimpl implements ProductPresenter {


    private ManagePresenter.View view;
    private ProductRepositoryimpl repository;


    public ProductPresenterimpl(ManagePresenter.View view) {

        this.view = view;
        this.repository = ProductRepositoryimpl.getInstance();
    }


    @Override
    public void deleteProductSnack(Product product) {

    }

    @Override
    public void deleteSelectedProducts() {

    }

    @Override
    public void loadProduct() {

    }

    @Override
    public void onAddProductButtonClicked() {

    }

    @Override
    public void onAddToCardButtonClicked() {

    }

    @Override
    public Product getProduct(long id) {
        return null;
    }

    @Override
    public void AddProduct(Product product) {
        repository.addProduct(product);
    }

    @Override
    public void onDeleteProductButtonClicked(Product product) {

    }


    @Override
    public void deleteProduct(Product product) {
        repository.deleteProduct(product);
        loadProducts();
        view.showMessageDelete(product);
    }

    private void loadProducts() {

            view.showProducts(repository.getAllProduct());

    }

    @Override
    public void onEditProductButtonClicked(Product product) {

    }

    @Override
    public void updateProduct(Product product) {

    }

    @Override
    public void onDestroy() {

    }
}
