package com.amador.manageProduct.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.amador.manageProduct.interfaces.IProducto;

import java.util.Comparator;
import java.util.UUID;

/**
 * @author Amador Fernandez Gonzalez
 * Clase POJO para almacenar los datos referentes a los productos que se
 * pueden crear en la aplicación. Implementa la interfaz:
 * @see Comparable<Product> para su ordenacion.
 */
public class Product implements Comparable<Product>, IProducto, Parcelable {

    //Campos
    private String id;
    private String name;
    private String description;
    private String dosage;
    private String brand;
    private double price;
    private int stock;
    private int image;

    //Comparadores
    public static final Comparator<Product> PRICE_COMPARATOR = new Comparator<Product>() {
        @Override
        public int compare(Product lhs, Product rhs) {
            return Double.compare(lhs.getPrice(), rhs.getPrice());
        }
    };
    public static final Comparator<Product> STOCK_COMPARATOR = new Comparator<Product>() {
        @Override
        public int compare(Product lhs, Product rhs) {
            return lhs.getStock() - rhs.getStock();
        }
    };

    public static final Comparator<Product> NAME_COMPARATOR = new Comparator<Product>() {
        @Override
        public int compare(Product o1, Product o2) {
            return o1.getName().compareTo(o2.getName());
        }
    };




    /**
     * Constructor de instancia de la clase
     *
     * @param name        Nombre
     * @param description Descripcion
     * @param brand       Marca
     * @param dosage      Dosis
     * @param price       precio del producto
     * @param stock       Cantidad de producto de la que se dispone
     * @param image       Imagen
     **/
    public Product(String name, String description, String brand, String dosage, double price, int stock, int image) {

        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
        this.brand = brand;
        this.price = price;
        this.stock = stock;
        this.image = image;
        this.dosage = dosage;
    }


    /**
     * Dos productos son iguales cuando tienen la misma dosis, nombre y marca
     * @param o Obejto con el que se va a comparar
     * */
    @Override
    public boolean equals(Object o) {

        boolean result = false;
        Product p;

        if(o != null){

            if(o instanceof Product){

                p = (Product)o;

                if(this.name.equals(p.name) && this.dosage.equals(p.dosage) && this.brand.equals(p.brand)){

                    result = true;
                }
            }

        }

        return result;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + dosage.hashCode();
        result = 31 * result + brand.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return this.name + " " + this.dosage;
    }

    //Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    //Name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //Description
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

    //Brand
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    //Price
    public double getPrice() {
        return price;
    }

    public String getPriceFormat(){return String.format("$%s", price);}

    public void setPrice(double price) {
        this.price = price;
    }

    //Stock
    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    //Image
    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    //Comparadores
    @Override
    public int compareTo(Product another) {

        int result = 0;

        //If two name are equals
        if(this.getName().compareTo(another.getName()) == 0){

            //Order by Brand
            result = this.getBrand().compareTo(another.getBrand());

        }else{

            //Order by name
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
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.description);
        dest.writeString(this.dosage);
        dest.writeString(this.brand);
        dest.writeDouble(this.price);
        dest.writeInt(this.stock);
        dest.writeInt(this.image);
    }

    protected Product(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.description = in.readString();
        this.dosage = in.readString();
        this.brand = in.readString();
        this.price = in.readDouble();
        this.stock = in.readInt();
        this.image = in.readInt();
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel source) {
            return new Product(source);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };
}
