package com.novaapps.botler.Database;

/**
 * Created by Tristan on 10/24/2015.
 */
public class Contract {

    //private variables
    int _id;
    String _name;

    // Empty constructor
    public Contract() {

    }

    // constructor
    public Contract(int id, String name) {
        this._id = id;
        this._name = name;
    }

    // constructor
    public Contract(String name) {
        this._name = name;
    }

    // getting ID
    public int getID() {
        return this._id;
    }

    // setting id
    public void setID(int id) {
        this._id = id;
    }

    // getting name
    public String getName() {
        return this._name;
    }

    // setting name
    public void setName(String name) {
        this._name = name;
    }
}
