package com.amador.manageProduct.interfaces;

import com.amador.manageProduct.model.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amador on 7/12/16.
 */
public interface ProductPresenter {

     List<Product> allProduct = new ArrayList<Product>();

    void deleteProductSnack(Product product);

    void deleteSelectedProducts();

    public interface View{

        void showProducts(List<Product> products);

        void showEmptyState();

        void showMessage(String message);
    }

     void loadProduct();

    void onAddProductButtonClicked();

    void onAddToCardButtonClicked();

    Product getProduct(long id);

    void AddProduct(Product product);

    void onDeleteProductButtonClicked(Product product);

    void deleteProduct(Product product);

    void onEditProductButtonClicked(Product product);

    void updateProduct(Product product);

    void onDestroy();
}
