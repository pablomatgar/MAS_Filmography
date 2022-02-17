//Pablo Mateos García

package com.mateosgarciapablo.task2_masfilmography;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{

    private static final String DATABASE_PATH =
            "/data/data/com.mateosgarciapablo.task2_masfilmography/databases/";
    private static final String DATABASE_PATH2 =
            "/data/data/com.mateosgarciapablo.task2_masfilmography/databases";
    private static final String DATABASE_NAME = "mas_filmography.db";

    //Context and elements of the database

    Context ctx;
    OpenDatabase sqh;
    SQLiteDatabase sqdb;

    //Elements of the layout

    BottomNavigationView navigationView;
    EditText searchEditText;
    Spinner spinner;
    Button searchButton;
    ArrayList<String> list;
    ListView listView;
    ArrayAdapter adapterList;
    ArrayAdapter<CharSequence> adapterSpinner;
    Button aboutButton;
    Button helpButton;
    Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Prepare database

        setUpDatabase();
        initDataBase();

        //Prepare controls

        setUpControls();
    }

    private void setUpControls(){

        //Link variables with their layout elements

        listView = findViewById(R.id.list_view);
        navigationView = findViewById(R.id.navigation_view);
        spinner = findViewById(R.id.spinner);
        searchEditText = findViewById(R.id.search_edit_text);
        searchButton = findViewById(R.id.search_button);
        aboutButton = findViewById(R.id.about_button);
        helpButton = findViewById(R.id.help_button);
        addButton = findViewById(R.id.add_button);

        list = new ArrayList<>();

        //Set menus and buttons

        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem){
                setUpAdapters(menuItem.getItemId());
                return true;
            }
        });

        navigationView.setSelectedItemId(R.id.filmsBt);

        searchButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                String by = translateIntoDB(spinner.getSelectedItem().toString());
                String table = "";
                list.clear();
                switch(navigationView.getSelectedItemId()){
                    case R.id.filmsBt:
                        table = "films";
                        break;
                    case R.id.seriesBt:
                        table = "series";
                        break;
                    case R.id.shortsBt:
                        table = "shorts";
                        break;
                }
                list.addAll(sqh.searchData(sqdb, searchEditText.getText().toString(),by,table));
                if(list.isEmpty()){
                    notFound(table);
                }
                adapterList = new ArrayAdapter(ctx, android.R.layout.simple_list_item_1, list);
                listView.setAdapter(adapterList);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id){
                String item = (String) adapterView.getItemAtPosition(position);
                String table ="";
                switch(navigationView.getSelectedItemId()){
                    case R.id.filmsBt:
                        table = "films";
                        break;
                    case R.id.seriesBt:
                        table = "series";
                        break;
                    case R.id.shortsBt:
                        table = "shorts";
                        break;
                }
                Intent intent = new Intent(getBaseContext(), ModifyActivity.class);
                intent.putExtra("item",item);
                intent.putExtra("table",table);
                startActivityForResult(intent, 1); //To be able to update when coming back
            }
        });

        addButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                String table ="";
                switch(navigationView.getSelectedItemId()){
                    case R.id.filmsBt:
                        table = "films";
                        break;
                    case R.id.seriesBt:
                        table = "series";
                        break;
                    case R.id.shortsBt:
                        table = "shorts";
                        break;
                }
                Intent intent = new Intent(getBaseContext(), AddActivity.class);
                intent.putExtra("table",table);
                startActivityForResult(intent, 2); //To be able to update when coming back
            }
        });

        aboutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), AboutActivity.class);
                startActivity(intent);
            }
        });

        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), HelpActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setUpAdapters(int choice){

        //Set up adapters

        list.clear();
        String table = "";
        switch (choice){
            case R.id.filmsBt:
                table = "films";
                adapterSpinner = ArrayAdapter.createFromResource(ctx, R.array.film_fields, android.R.layout.simple_spinner_item);
                break;
            case R.id.seriesBt:
                table = "series";
                adapterSpinner = ArrayAdapter.createFromResource(ctx,
                        R.array.series_fields, android.R.layout.simple_spinner_item);
                break;
            case R.id.shortsBt:
                table = "shorts";
                adapterSpinner = ArrayAdapter.createFromResource(ctx,
                        R.array.short_fields, android.R.layout.simple_spinner_item);
                break;
        }
        list.addAll(sqh.listAll(sqdb, table));
        if(list.isEmpty()){
            notFound(table);
        }
        adapterList = new ArrayAdapter(ctx, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapterList);
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapterSpinner);
    }

    private void notFound(String table){

        //Message for the user in case the list is empty / the search return no values

        Toast.makeText(ctx, "There are not " + table + " found.", Toast.LENGTH_SHORT).show();
    }

    private String translateIntoDB(String str){

        //Translates the 'user-friendly' strings into the database field names

        switch (str){
            case "Film ID": return "idFilm";
            case "Film Name": return "nameFilm";
            case "Original Film Name": return "originalNameFilm";
            case "Character Name": return "nameCharacter";
            case "Release Date": return "dateRelease";
            case "Director": return "director";
            case "Duration": return "duration";
            case "Language": return "language";
            case "Genre": return "genre";
            case "Series ID": return "idSeries";
            case "Series Name": return "nameSeries";
            case "Original Series Name": return "originalNameSeries";
            case "Seasons": return "seasons";
            case "Episodes": return "episodes";
            case "Episodes of Appareance of the Character": return "episodesAppareancesCharacter";
            case "Runtime": return "runtime";
            case "Creator": return "creator";
            case "End Date": return "dateEnd";
            case "Short ID": return "idShort";
            case "Short Name": return "nameShort";
            case "Original Short Name": return "originalNameShort";
        }
        return "";
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){

        //When coming back from an add activity or a modify activity, the adapter is reset

        super.onActivityResult(requestCode, resultCode, data);
        setUpAdapters(navigationView.getSelectedItemId());
    }

    public void setUpDatabase(){

        //Set up database

        ctx = this.getBaseContext();
        try{
            copyDataBaseFromAsset();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public void copyDataBaseFromAsset() throws IOException{

        //Copy database if necessary

        InputStream in = ctx.getAssets().open(DATABASE_NAME);
        String outputFileName = DATABASE_PATH + DATABASE_NAME;
        File databaseFolder = new File( DATABASE_PATH2 );

        if (!databaseFolder.exists()){
            databaseFolder.mkdir();
            OutputStream out = new FileOutputStream(outputFileName);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = in.read(buffer)) > 0){
                out.write(buffer,0,length);
            }
            out.flush();
            out.close();
            in.close();

        }
    }

    public void initDataBase(){

        //Init database

        sqh = new OpenDatabase(this);
        sqdb = sqh.getWritableDatabase();
    }
}
