package com.jsw.MngProductDatabase.Model;

/**
 * Created by joselu on 11/01/17.
 */

public class Category {
    int id;
    String name;

    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
