package com.jmedinilla.manageproducts.interfaces;

/**
 * Interface created by JMedinilla on 2016-10-20
 */
public interface IProductMvp {

    /**
     * Interface for the view
     * <p>
     * The method shows the error in the correct TextInputLayout
     */
    interface View {
        void setProductMessageError(String message, int idView);
    }

    /**
     * Interface for the presenter
     * <p>
     * The method validates a whole product
     */
    interface Presenter {
        boolean validateProduct(String name, String description, String concentration, String brand, double price, int stock, int image);
    }
}
