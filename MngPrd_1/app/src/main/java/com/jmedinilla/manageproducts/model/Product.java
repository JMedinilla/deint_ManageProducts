package com.jmedinilla.manageproducts.model;

import java.util.Currency;
import java.util.Locale;
import java.util.UUID;

/**
 * Class created by JMedinilla on 2016-10-19
 *
 * Model for the Product MVP
 */
public class Product {
    private String id;
    private String name;
    private String description;
    private String concentration;
    private String brand;
    private double price;
    private int stock;
    private int image;

    //Contructor
    public Product(String name, String description, String concentration, String brand, double price, int stock, int image) {
        //The ID will be an UUID Random
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
        this.concentration = concentration;
        this.brand = brand;
        this.price = price;
        this.stock = stock;
        //For now, the image will be the same for every Product
        this.image = image;
    }

    @Override
    public String toString() {
        return name + " (" + concentration + ")";
    }

    //Getter and Setter for -> ID
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    //Getter and Setter for -> NAME
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //Getter and Setter for -> DESCRIPTION
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    //Getter and Setter for -> CONCENTRATION
    public String getConcentration() {
        return concentration;
    }

    public void setConcentration(String concentration) {
        this.concentration = concentration;
    }

    //Getter and Setter for -> BRAND
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    //Getter and Setter for -> PRICE
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    //Getter and Setter for -> STOCK
    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    //Getter and Setter for -> IMAGE
    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getFormatedPrice() {
        Locale defaultLocale = Locale.getDefault();
        Currency currency = Currency.getInstance(defaultLocale);
        return String.format(currency.getCurrencyCode() + " %s", price);
    }

    public String getFormatedUnitsInStock() {
        return String.format(Locale.getDefault(), "%d u.", stock);
    }

    /**
     * Equal Override to compare an object with a Product
     * A product will be equal to another one when they have the same Name, Brand and Concentration
     * @param obj Object to compare with a Product
     * @return True of False depending of the Name, Brand and Concentration of the Product
     */
    @Override
    public boolean equals(Object obj) {
        boolean result = false;
        //The object can't be null
        if (obj != null) {
            //The object has to be a Product
            if (obj instanceof Product) {
                Product product = (Product)obj;
                //If the Name, Brand and Concentration have the same value, they are equal Products
                if (this.name.equals(product.name) && this.brand.equals(product.brand) && this.concentration.equals(product.concentration)) {
                    result = true;
                }
            }
        }
        return result;
    }
}
