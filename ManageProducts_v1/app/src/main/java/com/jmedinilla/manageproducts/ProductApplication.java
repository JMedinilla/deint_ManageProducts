package com.jmedinilla.manageproducts;

import android.app.Application;
import com.jmedinilla.manageproducts.model.Product;
import java.util.ArrayList;
import java.util.List;

/**
 * Class created by JMedinilla on 2016-10-20
 */
public class ProductApplication extends Application {
    //Products list for the application
    private ArrayList<Product> products;

    @Override
    public void onCreate() {
        super.onCreate();

        products = new ArrayList<>();

        saveProducts(new Product("Ibuprofeno", "Antiinflamatorio no esteroideo", "1g", "Farmalider", 4.95, 400, R.drawable.pill_3));
        saveProducts(new Product("Desenfriol", "Granulado, alivia los síntomas de la gripe", "200mg", "MSD", 8.50, 100, R.drawable.powder_11));
        saveProducts(new Product("Frenadol", "Frenadol te ayuda a frenar los síntomas de la gripe y el resfriado", "1g", "Frenadol", 6.95, 200, R.drawable.powder_11));
        saveProducts(new Product("Eutirox", "Levotiroxina de sodio", "88mg", "MERCK", 2.95, 60, R.drawable.pill_3));
        saveProducts(new Product("Nolotil", "Nolotil se utiliza para el tratamiento del dolor agudo", "500mg", "Normon S.A.", 6.50, 160, R.drawable.capsule_4));
        saveProducts(new Product("Gelocatil", "Paracetamol", "1g", "Ferrer", 4.25, 80, R.drawable.pill_3));
        saveProducts(new Product("Ventolín", "Ventolin ayuda a aliviar los problemas respiratorios asociados al asma", "10mg", "GaxoSmithKline", 5.95, 340, R.drawable.inhaler_10));
    }

    /**
     * Method that adds products to the ArrayList
     * @param product Product to add
     */
    public void saveProducts(Product product) {
        products.add(product);
    }

    /**
     * Method that returns the products list
     * @return Products list
     */
    public List<Product> getProducts() {
        return products;
    }
}
