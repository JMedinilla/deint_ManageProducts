package com.jmedinilla.manageproducts.application;

import android.app.Application;

import com.jmedinilla.manageproducts.R;
import com.jmedinilla.manageproducts.model.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Class created by JMedinilla on 2016-10-20
 */
public class ProductApplication extends Application {
    //Products list for the application
    private ArrayList<Product> products;

    /**
     * onCreate method for the class
     * <p>
     * It fills the list with some products to see on the list
     */
    @Override
    public void onCreate() {
        super.onCreate();

        products = new ArrayList<>();

        saveProducts(
                new Product("Ibuprofeno", "Antiinflamatorio no esteroideo",
                        "1g", "Farmalider", 4.95, 400, R.drawable.pill_3));
        saveProducts(
                new Product("Frenadol", "Frenadol te ayuda a frenar los síntomas de la gripe y el resfriado",
                        "1g", "Frenadol", 6.95, 200, R.drawable.powder_11));
        saveProducts(
                new Product("Eutirox", "Levotiroxina de sodio",
                        "88mg", "MERCK", 2.95, 60, R.drawable.pill_3));
        saveProducts(
                new Product("Ventolín", "Ventolin ayuda a aliviar los problemas respiratorios asociados al asma",
                        "10mg", "GaxoSmithKline", 5.95, 340, R.drawable.inhaler_10));
    }

    /**
     * Method that adds products to the ArrayList
     *
     * @param product Product to add
     */
    public void saveProducts(Product product) {
        products.add(product);
    }

    /**
     * Return the list of products
     *
     * @return Product's list
     */
    public List<Product> getProducts() {
        return products;
    }
}
