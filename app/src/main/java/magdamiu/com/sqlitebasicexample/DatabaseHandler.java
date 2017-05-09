package magdamiu.com.sqlitebasicexample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import magdamiu.com.sqlitebasicexample.model.Country;


public class DatabaseHandler extends SQLiteOpenHelper {
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "CountriesManager";

    // Countries table name
    private static final String TABLE_Countries = "Country";

    // Countries Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_TOWN = "capital";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_Countries_TABLE = "CREATE TABLE " + TABLE_Countries + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_TOWN + " TEXT" + ")";
        db.execSQL(CREATE_Countries_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Countries);

        // Create tables again
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new Country
    public void addCountry(Country Country) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, Country.getName()); // Country Name
        values.put(KEY_TOWN, Country.getTown()); // Country Capital

        // Inserting Row
        db.insert(TABLE_Countries, null, values);
        db.close(); // Closing database connection
    }

    // Getting single Country
    Country getCountry(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_Countries, new String[]{KEY_ID,
                        KEY_NAME, KEY_TOWN}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Country Country = new Country(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));
        // return Country
        return Country;
    }

    // Getting All Countries
    public List<Country> getAllCountries() {
        List<Country> CountryList = new ArrayList<Country>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_Countries;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Country Country = new Country();
                Country.setCountryId(Integer.parseInt(cursor.getString(0)));
                Country.setName(cursor.getString(1));
                Country.setTown(cursor.getString(2));
                // Adding Country to list
                CountryList.add(Country);
            } while (cursor.moveToNext());
        }

        db.close();
        // return Country list
        return CountryList;
    }

    // Updating single Country
    public void updateCountry(Country Country) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, Country.getName());
        values.put(KEY_TOWN, Country.getTown());

        // updating row
        db.update(TABLE_Countries, values, KEY_ID + " = ?",
                new String[]{String.valueOf(Country.getCountryId())});
        db.close();
    }

    // Deleting single Country
    public void deleteCountry(Country Country) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_Countries, KEY_ID + " = ?",
                new String[]{String.valueOf(Country.getCountryId())});
        db.close();
    }


    // Getting Countries Count
    public int getCountriesCount() {
        String countQuery = "SELECT  * FROM " + TABLE_Countries;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();

        return count;
    }
}
