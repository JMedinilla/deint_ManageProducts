package com.jmedinilla.manageproducts.interfaces;

/**
 * Interface created by JMedinilla on 2016-10-20
 */
public interface IProductMvp {

    /**
     * Interface that implements the method for the view
     * The method recieves an error message for the View from the Presenter
     */
    interface View {
        void setProductMessageError(String message, int idView);
    }

    /**
     * Interface that implements the method for the presenter
     * The method validates the added product in the form
     */
    interface Presenter {
        boolean validateProduct(String name, String description, String concentration, String brand, double price, int stock, int image);
    }
}
