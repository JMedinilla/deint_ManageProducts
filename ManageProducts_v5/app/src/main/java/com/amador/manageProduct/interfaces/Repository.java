package com.amador.manageProduct.interfaces;

import com.amador.manageProduct.model.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amador on 7/12/16.
 */

public interface Repository {

    List<Product> allProduct = new ArrayList<Product>();

    void deleteProduct(Product product);

    void addProduct(Product product);

    void updateProduct(Product product);
}
