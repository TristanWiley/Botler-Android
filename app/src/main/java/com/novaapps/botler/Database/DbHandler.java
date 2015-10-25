package com.novaapps.botler.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tristan on 10/24/2015.
 */
public class DbHandler extends SQLiteOpenHelper {

        // All Static variables
        // Database Version
        private static final int DATABASE_VERSION = 1;

        // Database Name
        private static final String DATABASE_NAME = "searchDatabase";

        // Contracts table name
        private static final String TABLE_CONTACTS = "searches";

        // Contracts Table Columns names
        private static final String KEY_ID = "id";
        private static final String KEY_NAME = "name";

        public DbHandler(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        // Creating Tables
        @Override
        public void onCreate(SQLiteDatabase db) {
            String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                    + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT" + ")";
            db.execSQL(CREATE_CONTACTS_TABLE);
        }

        // Upgrading database
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // Drop older table if existed
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

            // Create tables again
            onCreate(db);
        }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new contract
    void addContract(Contract contract) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contract.getName()); // Contract Name

        // Inserting Row
        db.insert(TABLE_CONTACTS, null, values);
        db.close(); // Closing database connection
    }

    // Getting single contract
    Contract getContract(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CONTACTS, new String[] { KEY_ID,
                        KEY_NAME}, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Contract contract = new Contract(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1));
        // return contract
        return contract;
    }

    // Getting All Contracts
    public List<Contract> getAllContracts() {
        List<Contract> contractList = new ArrayList<Contract>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Contract contract = new Contract();
                contract.setID(Integer.parseInt(cursor.getString(0)));
                contract.setName(cursor.getString(1));
                // Adding contract to list
                contractList.add(contract);
            } while (cursor.moveToNext());
        }

        // return contract list
        return contractList;
    }

    // Updating single contract
    public int updateContract(Contract contract) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contract.getName());

        // updating row
        return db.update(TABLE_CONTACTS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(contract.getID()) });
    }

    // Deleting single contract
    public void deleteContract(Contract contract) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS, KEY_ID + " = ?",
                new String[] { String.valueOf(contract.getID()) });
        db.close();
    }


    // Getting contracts Count
    public int getContractsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_CONTACTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

}
