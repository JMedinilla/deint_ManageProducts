package com.amador.manageProduct.repository;

import com.amador.manageProduct.R;
import com.amador.manageProduct.interfaces.Repository;
import com.amador.manageProduct.model.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amador on 7/12/16.
 */

public class ProductRepositoryimpl implements Repository {


    private ArrayList<Product> products;
    private static ProductRepositoryimpl repository;


    private ProductRepositoryimpl(){

        products = new ArrayList<Product>();
        products.add(new Product("Iboprofeno", "Telo quitato", "Telva", "No te cueles que te acostumbras", 12.95, 20, R.drawable.pill ));
        products.add(new Product("Hemoal", "Alivia que no veas", "Hemoal", "No te cueles que te acostumbras", 20.95, 20, R.drawable.pill ));
        products.add(new Product("Nolotil", "Pa las muelas lo meho", "Trigo limpio", "No te cueles que te acostumbras", 95.95, 20, R.drawable.pill ));
        products.add(new Product("Buscapina", "Te da un sueñesito mu rico", "Quita dolores", "No te cueles que te acostumbras", 122.95, 20, R.drawable.pill ));
    }

    public static ProductRepositoryimpl getInstance(){

        if(repository == null){

            repository = new ProductRepositoryimpl();
        }

        return repository;
    }

    public List<Product> getAllProduct(){

        return  products;
    }

    public Product getProductById(String id){

        for(Product tmp: products){

            if(tmp.getId().equals(id)){

                return tmp;
            }
        }

        return null;
    }


    @Override
    public void deleteProduct(Product product) {

        products.remove(product);
    }

    @Override
    public void addProduct(Product product) {

        products.add(product);
    }

    @Override
    public void updateProduct(Product product) {

        int index = products.indexOf(getProductById(product.getId()));
        products.remove(index);
        products.add(index, product);

    }
}
