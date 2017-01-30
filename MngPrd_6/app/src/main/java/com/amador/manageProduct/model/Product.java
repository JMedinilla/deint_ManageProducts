package com.amador.manageProduct.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.util.Comparator;
import java.util.UUID;

public class Product implements Comparable<Product>, Parcelable {
    private String id;
    private String name;
    private String description;
    private String dosage;
    private String brand;
    private double price;
    private int stock;
    private int image;
    private int category;

    public static final Comparator<Product> NAME_COMPARATOR = new Comparator<Product>() {
        @Override
        public int compare(Product o1, Product o2) {
            return o1.getName().compareTo(o2.getName());
        }
    };

    public Product(String name, String description, String brand, String dosage, double price, int stock, int image, int category) {

        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
        this.brand = brand;
        this.price = price;
        this.stock = stock;
        this.image = image;
        this.dosage = dosage;
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {

        boolean result = false;
        Product p;

        if (o != null) {

            if (o instanceof Product) {

                p = (Product) o;

                if (this.name.equals(p.name) && this.dosage.equals(p.dosage) && this.brand.equals(p.brand)) {

                    result = true;
                }
            }

        }

        return result;
    }

    @Override
    public String toString() {
        return this.name + " " + this.dosage;
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

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
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

    public String getPriceFormat() {
        return String.format("$%s", price);
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

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    @Override
    public int compareTo(@NonNull Product another) {
        int result;
        if (this.getName().compareTo(another.getName()) == 0) {
            result = this.getBrand().compareTo(another.getBrand());

        } else {
            result = this.getName().compareTo(another.getName());
        }
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(dosage);
        dest.writeString(brand);
        dest.writeDouble(price);
        dest.writeInt(stock);
        dest.writeInt(image);
        dest.writeInt(category);
    }

    protected Product(Parcel in) {
        id = in.readString();
        name = in.readString();
        description = in.readString();
        dosage = in.readString();
        brand = in.readString();
        price = in.readDouble();
        stock = in.readInt();
        image = in.readInt();
        category = in.readInt();
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
}
