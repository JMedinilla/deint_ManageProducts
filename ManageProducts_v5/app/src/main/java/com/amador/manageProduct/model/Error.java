package com.amador.manageProduct.model;

/**
 * @author Amador Fernandez Gonzalez
 * Clase que implementa los codigos de error para las validaciones
 * de los datos relacionados con el usuario
 */
public class Error {
    public static final int OK = 0;
    public static final int PWD_DIGIT = 10;
    public static final int PWD_CASE = 11;
    public static final int PWD_MIN_LENGTH = 12;
    public static final int DATA_EMPTY = 13;
    public static final int INVALID_EMAIL = 14;
    public static int errorCode;
    public static String errorMessage;

    static {

    }
}