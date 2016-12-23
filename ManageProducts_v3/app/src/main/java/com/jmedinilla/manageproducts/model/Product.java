package com.jmedinilla.manageproducts.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Currency;
import java.util.Locale;
import java.util.UUID;

/**
 * Class created by JMedinilla on 2016-10-19
 * <p>
 * Product POJO
 */
public class Product implements Comparable<Product>, Parcelable {
    private String id;
    private String name;
    private String description;
    private String concentration;
    private String brand;
    private double price;
    private int stock;
    private int image;

    /**
     * POJO constructor
     * <p>
     * The ID is random
     *
     * @param name          Product's name
     * @param description   Product's description
     * @param concentration Product's concentration
     * @param brand         Product's brand
     * @param price         Product's price
     * @param stock         Product's stock
     * @param image         Product's image
     */
    public Product(String name, String description, String concentration, String brand, double price, int stock, int image) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
        this.concentration = concentration;
        this.brand = brand;
        this.price = price;
        this.stock = stock;
        this.image = image;
    }

    /**
     * Method that return a written product
     *
     * @return Product's name and concentration
     */
    @Override
    public String toString() {
        return name + " (" + concentration + ")";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getConcentration() {
        return concentration;
    }

    public void setConcentration(String concentration) {
        this.concentration = concentration;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    /**
     * Method that returns the price formated to the local coin
     *
     * @return Formated price
     */
    public String getFormatedPrice() {
        Locale defaultLocale = Locale.getDefault();
        Currency currency = Currency.getInstance(defaultLocale);
        return String.format(currency.getCurrencyCode() + " %s", price);
    }

    /*
    *
    * FORMATED STOCK UNITS
    *
    public String getFormatedUnitsInStock() {
        return String.format(Locale.getDefault(), "%d u.", stock);
    }
    */

    /**
     * Equal Override to compare an object with a Product
     * A product will be equal to another one when they have the same Name, Brand and Concentration
     *
     * @param obj Object to compare with a Product
     * @return True of False depending of the Name, Brand and Concentration of the Product
     */
    @Override
    public boolean equals(Object obj) {
        boolean result = false;
        if (obj != null) {
            if (obj instanceof Product) {
                Product product = (Product) obj;
                if (this.name.equals(product.name)
                        && this.brand.equals(product.brand)
                        && this.concentration.equals(product.concentration)) {
                    result = true;
                }
            }
        }
        return result;
    }

    @Override
    public int compareTo(@NonNull Product another) {
        if (this.getName().compareTo(another.getName()) == 0) {
            if (this.getConcentration().compareTo(another.getConcentration()) == 0) {
                return this.getBrand().compareTo(another.getBrand());
            } else {
                return this.getConcentration().compareTo(another.getConcentration());
            }
        } else {
            return this.getName().compareTo(another.getName());
        }
    }

    /**
     * Name comparator
     */
    public static final Comparator<Product> NAME_COMPARATOR_ASC = new Comparator<Product>() {
        @Override
        public int compare(Product p1, Product p2) {
            return p1.getName().toUpperCase().compareTo(p2.getName().toUpperCase());
        }
    };

    /**
     * Brand comparator
     */
    public static final Comparator<Product> BRAND_COMPARATOR_ASC = new Comparator<Product>() {
        @Override
        public int compare(Product p1, Product p2) {
            return p1.getBrand().toUpperCase().compareTo(p2.getBrand().toUpperCase());
        }
    };

    /**
     * Price comparator
     */
    public static final Comparator<Product> PRICE_COMPARATOR_ASC = new Comparator<Product>() {
        @Override
        public int compare(Product p1, Product p2) {
            return Double.compare(p1.getPrice(), p2.getPrice());
        }
    };

    /**
     * Stock comparator
     */
    public static final Comparator<Product> STOCK_COMPARATOR_ASC = new Comparator<Product>() {
        @Override
        public int compare(Product p1, Product p2) {
            return p1.getStock() - p2.getStock();
        }
    };

    private Product(Parcel in) {
        id = in.readString();
        name = in.readString();
        description = in.readString();
        concentration = in.readString();
        brand = in.readString();
        price = in.readDouble();
        stock = in.readInt();
        image = in.readInt();
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(concentration);
        dest.writeString(brand);
        dest.writeDouble(price);
        dest.writeInt(stock);
        dest.writeInt(image);
    }
}
