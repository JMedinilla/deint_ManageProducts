package com.jmedinilla.manageproducts.presenter;

import com.jmedinilla.manageproducts.application.Repository;
import com.jmedinilla.manageproducts.interfaces.ProductPresenter;
import com.jmedinilla.manageproducts.model.Product;


public class ProductPresenterImpl implements ProductPresenter {
    private ProductPresenter.View view;
    private Repository repository;

    public ProductPresenterImpl(ProductPresenter.View view) {
        this.view = view;
        this.repository = Repository.getInstance();
    }

    @Override
    public void loadProducts() {
        if (this.repository.getProducts().isEmpty()) {
            view.showEmptyText(true);
        }
        else {
            view.showProducts(repository.getProducts());
        }
    }

    @Override
    public Product getProduct(String id) {
        return null;
        //return repository.getProduct(id);
    }

    @Override
    public void deleteProduct(Product product) {
        repository.deleteProduct(product);
        loadProducts();
    }

    @Override
    public void onDestroy() {
        this.view = null;
    }
}
