package com.jmedinilla.manageproducts.interfaces;

import com.jmedinilla.manageproducts.model.Product;

import java.util.List;

public interface ProductPresenter {

    void loadProducts();

    Product getProduct(String id);

    void deleteProduct(Product product);

    void onDestroy();

    interface View {
        void showProducts(List<Product> products);

        void showEmptyText(boolean show);

        void showMessage(String message);
    }
}
