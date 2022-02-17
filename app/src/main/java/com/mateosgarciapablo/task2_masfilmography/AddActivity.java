//Pablo Mateos García

package com.mateosgarciapablo.task2_masfilmography;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity{

    //Elements of the layout

    TableLayout layout;
    TextView nameTextView;
    TextView originalNameTextView;
    TextView nameCharacterTextView;
    TextView dateReleaseTextView;
    TextView directorTextView;
    TextView durationTextView;
    TextView languageTextView;
    TextView genreTextView;
    TextView dateEndTextView;
    TextView seasonsTextView;
    TextView episodesTextView;
    TextView episodesCharacterTextView;
    EditText nameEditText;
    EditText originalNameEditText;
    EditText nameCharacterEditText;
    EditText dateReleaseEditText;
    EditText directorEditText;
    EditText durationEditText;
    EditText languageEditText;
    EditText genreEditText;
    EditText dateEndEditText;
    EditText seasonsEditText;
    EditText episodesEditText;
    EditText episodesCharacterEditText;
    TableRow row1;
    TableRow row2;
    TableRow row3;
    TableRow row4;
    Button backButton;
    Button saveButton;

    //Elements of the database

    OpenDatabase sqh;
    SQLiteDatabase sqdb;

    String table;
    String by;
    Context ctx = this;

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        //Prepare database

        initDataBase();

        //Collect data of intent

        Bundle bundle = getIntent().getExtras();
        table = bundle.getString("table");

        //Set up controls

        setUpControls();
    }

    private void setUpControls(){

        //Link variables with their layout elements

        layout = findViewById(R.id.layout_add);
        nameTextView = findViewById(R.id.name_text_view_a);
        originalNameTextView = findViewById(R.id.original_name_text_view_a);
        nameCharacterTextView = findViewById(R.id.name_character_text_view_a);
        dateReleaseTextView = findViewById(R.id.date_release_text_view_a);
        directorTextView = findViewById(R.id.director_text_view_a);
        durationTextView = findViewById(R.id.duration_text_view_a);
        languageTextView = findViewById(R.id.language_text_view_a);
        genreTextView = findViewById(R.id.genre_text_view_a);
        dateEndTextView = findViewById(R.id.date_end_text_view_a);
        seasonsTextView = findViewById(R.id.seasons_text_view_a);
        episodesTextView = findViewById(R.id.episodes_text_view_a);
        episodesCharacterTextView = findViewById(R.id.episodes_character_text_view_a);
        nameEditText = findViewById(R.id.name_edit_text_a);
        originalNameEditText = findViewById(R.id.original_name_edit_text_a);
        nameCharacterEditText = findViewById(R.id.name_character_edit_text_a);
        dateReleaseEditText = findViewById(R.id.date_release_edit_text_a);
        directorEditText = findViewById(R.id.director_edit_text_a);
        durationEditText = findViewById(R.id.duration_edit_text_a);
        languageEditText = findViewById(R.id.language_edit_text_a);
        genreEditText = findViewById(R.id.genre_edit_text_a);
        dateEndEditText = findViewById(R.id.date_end_edit_text_a);
        seasonsEditText = findViewById(R.id.seasons_edit_text_a);
        episodesEditText = findViewById(R.id.episodes_edit_text_a);
        episodesCharacterEditText = findViewById(R.id.episodes_character_edit_text_a);
        backButton = findViewById(R.id.back_button_a);
        saveButton = findViewById(R.id.save_button_a);
        row1 = findViewById(R.id.row1_a);
        row2 = findViewById(R.id.row2_a);
        row3 = findViewById(R.id.row3_a);
        row4 = findViewById(R.id.row4_a);

        //Define textViews and editTexts, depending on the section (films, series or shorts)

        switch (table){
            case "films":
                by ="idFilm";
                nameTextView.setText(getResources().getStringArray(R.array.film_fields)[1]);
                originalNameTextView.setText(getResources().getStringArray(R.array.film_fields)[2]);
                nameCharacterTextView.setText(getResources().getStringArray(R.array.film_fields)[3]);
                dateReleaseTextView.setText(getResources().getStringArray(R.array.film_fields)[4]);
                directorTextView.setText(getResources().getStringArray(R.array.film_fields)[5]);
                durationTextView.setText(getResources().getStringArray(R.array.film_fields)[6]);
                languageTextView.setText(getResources().getStringArray(R.array.film_fields)[7]);
                genreTextView.setText(getResources().getStringArray(R.array.film_fields)[8]);
                nameEditText.setHint(getResources().getStringArray(R.array.film_fields)[1]);
                originalNameEditText.setHint(getResources().getStringArray(R.array.film_fields)[2]);
                nameCharacterEditText.setHint(getResources().getStringArray(R.array.film_fields)[3]);
                dateReleaseEditText.setHint(getResources().getStringArray(R.array.film_fields)[4]);
                directorEditText.setHint(getResources().getStringArray(R.array.film_fields)[5]);
                durationEditText.setHint(getResources().getStringArray(R.array.film_fields)[6]);
                languageEditText.setHint(getResources().getStringArray(R.array.film_fields)[7]);
                genreEditText.setHint(getResources().getStringArray(R.array.film_fields)[8]);
                layout.removeView(row1);
                layout.removeView(row2);
                layout.removeView(row3);
                layout.removeView(row4);
                break;

            case "series":
                by ="idSeries";
                nameTextView.setText(getResources().getStringArray(R.array.series_fields)[1]);
                originalNameTextView.setText(getResources().getStringArray(R.array.series_fields)[2]);
                nameCharacterTextView.setText(getResources().getStringArray(R.array.series_fields)[3]);
                dateReleaseTextView.setText(getResources().getStringArray(R.array.series_fields)[4]);
                dateEndTextView.setText(getResources().getStringArray(R.array.series_fields)[5]);
                seasonsTextView.setText(getResources().getStringArray(R.array.series_fields)[6]);
                episodesTextView.setText(getResources().getStringArray(R.array.series_fields)[7]);
                episodesCharacterTextView.setText(getResources().getStringArray(R.array.series_fields)[8]);
                directorTextView.setText(getResources().getStringArray(R.array.series_fields)[9]);
                durationTextView.setText(getResources().getStringArray(R.array.series_fields)[10]);
                languageTextView.setText(getResources().getStringArray(R.array.series_fields)[11]);
                genreTextView.setText(getResources().getStringArray(R.array.series_fields)[12]);
                nameEditText.setHint(getResources().getStringArray(R.array.series_fields)[1]);
                originalNameEditText.setHint(getResources().getStringArray(R.array.series_fields)[2]);
                nameCharacterEditText.setHint(getResources().getStringArray(R.array.series_fields)[3]);
                dateReleaseEditText.setHint(getResources().getStringArray(R.array.series_fields)[4]);
                dateEndEditText.setHint(getResources().getStringArray(R.array.series_fields)[5]);
                seasonsEditText.setHint(getResources().getStringArray(R.array.series_fields)[6]);
                episodesEditText.setHint(getResources().getStringArray(R.array.series_fields)[7]);
                episodesCharacterEditText.setHint(getResources().getStringArray(R.array.series_fields)[8]);
                directorEditText.setHint(getResources().getStringArray(R.array.series_fields)[9]);
                durationEditText.setHint(getResources().getStringArray(R.array.series_fields)[10]);
                languageEditText.setHint(getResources().getStringArray(R.array.series_fields)[11]);
                genreEditText.setHint(getResources().getStringArray(R.array.series_fields)[12]);
                break;

            case "shorts":
                by ="idShort";
                nameTextView.setText(getResources().getStringArray(R.array.short_fields)[1]);
                originalNameTextView.setText(getResources().getStringArray(R.array.short_fields)[2]);
                nameCharacterTextView.setText(getResources().getStringArray(R.array.short_fields)[3]);
                dateReleaseTextView.setText(getResources().getStringArray(R.array.short_fields)[4]);
                directorTextView.setText(getResources().getStringArray(R.array.short_fields)[5]);
                durationTextView.setText(getResources().getStringArray(R.array.short_fields)[6]);
                languageTextView.setText(getResources().getStringArray(R.array.short_fields)[7]);
                genreTextView.setText(getResources().getStringArray(R.array.short_fields)[8]);
                nameEditText.setHint(getResources().getStringArray(R.array.short_fields)[1]);
                originalNameEditText.setHint(getResources().getStringArray(R.array.short_fields)[2]);
                nameCharacterEditText.setHint(getResources().getStringArray(R.array.short_fields)[3]);
                dateReleaseEditText.setHint(getResources().getStringArray(R.array.short_fields)[4]);
                directorEditText.setHint(getResources().getStringArray(R.array.short_fields)[5]);
                durationEditText.setHint(getResources().getStringArray(R.array.short_fields)[6]);
                languageEditText.setHint(getResources().getStringArray(R.array.short_fields)[7]);
                genreEditText.setHint(getResources().getStringArray(R.array.short_fields)[8]);
                layout.removeView(row1);
                layout.removeView(row2);
                layout.removeView(row3);
                layout.removeView(row4);
                break;
        }

        //Set buttons

        backButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                finish();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){

                if(checkEmpty()){
                    error();
                }
                else{
                    if(table.equals("series")){
                        sqh.addData(sqdb, ctx, nameEditText.getText().toString(),
                                originalNameEditText.getText().toString(),
                                nameCharacterEditText.getText().toString(),
                                dateReleaseEditText.getText().toString(),
                                dateEndEditText.getText().toString(),
                                seasonsEditText.getText().toString(),
                                episodesEditText.getText().toString(),
                                episodesCharacterEditText.getText().toString(),
                                directorEditText.getText().toString(),
                                durationEditText.getText().toString(),
                                languageEditText.getText().toString(),
                                genreEditText.getText().toString());
                    }
                    else{
                        sqh.addData(sqdb,table, ctx, nameEditText.getText().toString(),
                                originalNameEditText.getText().toString(),
                                nameCharacterEditText.getText().toString(),
                                dateReleaseEditText.getText().toString(),
                                directorEditText.getText().toString(),
                                durationEditText.getText().toString(),
                                languageEditText.getText().toString(),
                                genreEditText.getText().toString());
                    }
                    Toast.makeText(ctx, "Added succesfully.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private boolean checkEmpty(){

        //Check if any of the editTexts is empty

        if(table.equals("series")){
            if(dateEndEditText.getText().toString().equals("") ||
                    seasonsEditText.getText().toString().equals("") ||
                    episodesEditText.getText().toString().equals("") ||
                    episodesCharacterEditText.getText().toString().equals("")){
                return true;
            }
        }
        if(nameEditText.getText().toString().equals("") ||
                originalNameEditText.getText().toString().equals("") ||
                nameCharacterEditText.getText().toString().equals("") ||
                dateReleaseEditText.getText().toString().equals("") ||
                directorEditText.getText().toString().equals("") ||
                durationEditText.getText().toString().equals("") ||
                languageEditText.getText().toString().equals("") ||
                genreEditText.getText().toString().equals("")){
            return true;
        }
        return false;
    }

    private void error(){

        //Message for the user in case there are empty fields

        Toast.makeText(ctx, "All the fields must be completed", Toast.LENGTH_SHORT).show();
    }

    public void initDataBase(){

        //Init database

        sqh = new OpenDatabase(this);
        sqdb = sqh.getWritableDatabase();
    }
}
