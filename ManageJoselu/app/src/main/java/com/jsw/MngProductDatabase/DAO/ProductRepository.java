package com.jsw.MngProductDatabase.DAO;

/*
 * Copyright (c) 2016 José Luis del Pino Gallardo.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 *  jose.gallardo994@gmail.com
 */

import com.jsw.MngProductDatabase.Model.Product;
import com.jsw.MngProductDatabase.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by usuario on 9/12/16.
 */
public class ProductRepository {
    private static ArrayList<Product> products;

    private static ProductRepository ourInstance = new ProductRepository();

    public static ProductRepository getInstance() {
        return ourInstance;
    }

    private ProductRepository() {
        products = new ArrayList<Product>();
        Random rnd = new Random();

        products.add(new Product(rnd.nextInt(), "Ibuprofeno", "Ibuprofeno Generico 1gr",  "Generico", "1gr", 4.70, "2", "https://www.laproff.com/img/product/Ibuprofeno.jpg", 1));
        products.add(new Product(rnd.nextInt(), "Albendazol", "Albendazol Generico 1gr",  "Laproff", "1gr", 5.70, "2", "https://www.laproff.com/img/product/albendazol.jpg", 1));
        products.add(new Product(rnd.nextInt(), "Deflazacort", "Deflazacort Generico 1gr",  "Generico", "1gr", 7.0, "2", "https://www.laproff.com/img/product/Deflazacort%206mg%20x103d%20copia.jpg", 1));
        products.add(new Product(rnd.nextInt(), "Flouxetina", "Flouxetina Generico 1gr",  "Generico", "1gr", 45.70, "2", "https://www.laproff.com/img/product/Flouxetina-20mg-x300-3d-capsulas.jpg", 1));
        products.add(new Product(rnd.nextInt(), "Hioscina", "Hioscina Generico 1gr",  "Generico", "1gr", 45.70, "2", "https://www.laproff.com/img/product/Hioscina%2010mg%20x20%203d.jpg", 1));
        products.add(new Product(rnd.nextInt(), "Levofloxacina", "Levofloxacina Generico 1gr",  "Generico", "1gr", 45.70, "2", "https://www.laproff.com/img/product/Levofloxacina-500mg-x10-3d.png", 1));
        products.add(new Product(rnd.nextInt(), "Lorazepam", "Lorazepam Generico 1gr",  "Generico", "1gr", 45.70, "2", "https://www.laproff.com/img/product/Lorazepam-2mg-x30-3d-copia.png", 1));
        products.add(new Product(rnd.nextInt(), "Piridoxina", "Piridoxina Generico 1gr",  "Generico", "1gr", 45.70, "2", "https://www.laproff.com/img/product/Piridoxina-50mg-x100-3d.png", 1));
        products.add(new Product(rnd.nextInt(), "Propranolol", "Propranolol Generico 1gr",  "Generico", "1gr", 45.70, "2", "https://www.laproff.com/img/product/Propranolol-40mg-x20-3d.psd.jpg", 1));
        products.add(new Product(rnd.nextInt(), "Tinidazol", "Tinidazol Generico 1gr",  "Generico", "1gr", 45.70, "2", "https://www.laproff.com/img/product/Tinidazol-500mg-x240-3d.jpg", 1));
        products.add(new Product(rnd.nextInt(), "Tiamina", "Tiamina Generico 1gr",  "Generico", "1gr", 45.70, "2", "https://www.laproff.com/img/product/Tiamina.png", 1));
        products.add(new Product(rnd.nextInt(), "LSD", "LSD Del Bueno 1gr",  "Generico", "1gr", 45.70, "2", "https://assets.digitalocean.com/other_images/sammy.png", 1));
    }

    public static void add(Product p){
            products.add(p);
    }

    public static List<Product> getProducts(){
        return products;
    }

    public static void deleteProduct(Product product){
        products.remove(product);
    }
}
