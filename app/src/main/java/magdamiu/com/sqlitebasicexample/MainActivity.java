package magdamiu.com.sqlitebasicexample;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

import magdamiu.com.sqlitebasicexample.model.Country;


public class MainActivity extends Activity {
    private TextView contentDb;
    private DatabaseHandler db;
    private StringBuilder stringBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contentDb = (TextView) findViewById(R.id.contentDB);
        db = new DatabaseHandler(this);
        stringBuilder = new StringBuilder();
        /**
         * CRUD Operations
         * */
        // Inserting Countries
        int countriesCount = db.getCountriesCount();
        if (countriesCount == 0) {
            Log.d("Insert: ", "Inserting ..");
            db.addCountry(new Country(0, "Romania", "Bucuresti"));//1
            db.addCountry(new Country(0, "Franta", "Paris"));//2
            db.addCountry(new Country(0, "Marea Britanie", "Londra"));//3
            db.addCountry(new Country(0, "Tara1", "Capitala1"));//4
        }

        // Reading all Countries
        List<Country> countries = db.getAllCountries();

        //add the current content of the Country table
        stringBuilder.append("SELECT ALL INITIAL: " + countries.toString() + "\n\n\n");

        //Update Country
        Country taraMB = new Country(3, "Moldova", "Chisinau");
        db.updateCountry(taraMB);

        countries = db.getAllCountries();
        //add the current content of the Country table
        stringBuilder.append("SELECT ALL AFTER UPDATE: " + countries.toString() + "\n\n\n");

        //Delete Country
        db.deleteCountry(countries.get(1));

        // Reading all Countries
        countries = db.getAllCountries();

        //add the current content of the Country table
        stringBuilder.append("SELECT ALL AFTER DELETE: " + countries.toString() + "\n\n\n");

        //Display the current content of the Country table
        contentDb.setText(stringBuilder.toString());
    }
}