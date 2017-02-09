package com.afg.MngProductContentProvider.Model;

/**
 * Created by joselu on 11/01/17.
 */

public class InvoiceLine {

    int idInvoice;

    public void setIdInvoice(int idInvoice) {
        this.idInvoice = idInvoice;
    }

    public void setOrderProduct(int orderProduct) {
        this.orderProduct = orderProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    int orderProduct;
    int idProduct;
    int amount;
    double price;

    public InvoiceLine(int idInvoice, int orderProduct, int idProduct, int amount, double price) {
        this.idInvoice = idInvoice;
        this.orderProduct = orderProduct;
        this.idProduct = idProduct;
        this.amount = amount;
        this.price = price;
    }

    public int getIdInvoice() {
        return idInvoice;
    }

    public int getOrderProduct() {
        return orderProduct;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public int getAmount() {
        return amount;
    }

    public double getPrice() {
        return price;
    }
}
