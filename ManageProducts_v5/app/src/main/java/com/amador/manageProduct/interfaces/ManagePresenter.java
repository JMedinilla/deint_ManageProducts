package com.amador.manageProduct.interfaces;

import com.amador.manageProduct.model.Product;

import java.util.List;

/**
 * Created by amador on 7/12/16.
 */

public interface ManagePresenter {

    interface View{

        void showMessage(String message);

        void showProducts(List<Product> products);

        void showMessageDelete(Product product);
    }

    void addProduct(Product product);

    void updateProduct(Product product);

    void onDestroy();
}
