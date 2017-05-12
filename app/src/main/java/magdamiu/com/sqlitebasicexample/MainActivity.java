package magdamiu.com.sqlitebasicexample;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

import magdamiu.com.sqlitebasicexample.model.Country;


public class MainActivity extends AppCompatActivity {
    private TextView contentDb;
    private DatabaseHandler databaseHandler;
    private StringBuilder stringBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contentDb = (TextView) findViewById(R.id.contentDB);

        // Get singleton instance of database
        databaseHandler = DatabaseHandler.getInstance(this);

        stringBuilder = new StringBuilder();
        /**
         * CRUD Operations
         * */
        // Inserting Countries
        int countriesCount = databaseHandler.getCountriesCount();
        if (countriesCount == 0) {
            Log.d("Insert: ", "Inserting ..");
            databaseHandler.addCountry(new Country(0, "Romania", "Bucuresti"));//1
            databaseHandler.addCountry(new Country(0, "Franta", "Paris"));//2
            databaseHandler.addCountry(new Country(0, "Marea Britanie", "Londra"));//3
            databaseHandler.addCountry(new Country(0, "Tara1", "Capitala1"));//4
        }

        // Reading all Countries
        List<Country> countries = databaseHandler.getAllCountries();

        //add the current content of the Country table
        stringBuilder.append("SELECT ALL INITIAL: " + countries.toString() + "\n\n\n");

        //Update Country
        Country taraMB = new Country(3, "Moldova", "Chisinau");
        databaseHandler.updateCountry(taraMB);

        countries = databaseHandler.getAllCountries();
        //add the current content of the Country table
        stringBuilder.append("SELECT ALL AFTER UPDATE: " + countries.toString() + "\n\n\n");

        //Delete Country
        databaseHandler.deleteCountry(countries.get(1));

        // Reading all Countries
        countries = databaseHandler.getAllCountries();

        //add the current content of the Country table
        stringBuilder.append("SELECT ALL AFTER DELETE: " + countries.toString() + "\n\n\n");

        //Display the current content of the Country table
        contentDb.setText(stringBuilder.toString());
    }
}