//Pablo Mateos García

package com.mateosgarciapablo.task2_masfilmography;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.app.AlertDialog;
import java.util.ArrayList;

public class ModifyActivity extends AppCompatActivity{

    //Elements of the layout

    TableLayout layout;
    TextView idTextView;
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
    EditText idEditText;
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
    Button deleteButton;

    //Elements of the database

    OpenDatabase sqh;
    SQLiteDatabase sqdb;

    Context ctx = this;

    String table;
    String by;
    String id;
    ArrayList<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);

        //Prepare database

        initDataBase();

        //Collect data of intent

        Bundle bundle = getIntent().getExtras();
        String item = bundle.getString("item");
        id = item.split(" - ")[0];
        table = bundle.getString("table");

        //Set up controls

        setUpControls();

    }

    private void setUpControls(){

        //Link variables with their layout elements

        layout = findViewById(R.id.layout_modify);
        idTextView = findViewById(R.id.id_text_view_m);
        nameTextView = findViewById(R.id.name_text_view_m);
        originalNameTextView = findViewById(R.id.original_name_text_view_m);
        nameCharacterTextView = findViewById(R.id.name_character_text_view_m);
        dateReleaseTextView = findViewById(R.id.date_release_text_view_m);
        directorTextView = findViewById(R.id.director_text_view_m);
        durationTextView = findViewById(R.id.duration_text_view_m);
        languageTextView = findViewById(R.id.language_text_view_m);
        genreTextView = findViewById(R.id.genre_text_view_m);
        dateEndTextView = findViewById(R.id.date_end_text_view_m);
        seasonsTextView = findViewById(R.id.seasons_text_view_m);
        episodesTextView = findViewById(R.id.episodes_text_view_m);
        episodesCharacterTextView = findViewById(R.id.episodes_character_text_view_m);
        idEditText = findViewById(R.id.id_edit_text_m);
        nameEditText = findViewById(R.id.name_edit_text_m);
        originalNameEditText = findViewById(R.id.original_name_edit_text_m);
        nameCharacterEditText = findViewById(R.id.name_character_edit_text_m);
        dateReleaseEditText = findViewById(R.id.date_release_edit_text_m);
        directorEditText = findViewById(R.id.director_edit_text_m);
        durationEditText = findViewById(R.id.duration_edit_text_m);
        languageEditText = findViewById(R.id.language_edit_text_m);
        genreEditText = findViewById(R.id.genre_edit_text_m);
        dateEndEditText = findViewById(R.id.date_end_edit_text_m);
        seasonsEditText = findViewById(R.id.seasons_edit_text_m);
        episodesEditText = findViewById(R.id.episodes_edit_text_m);
        episodesCharacterEditText = findViewById(R.id.episodes_character_edit_text_m);
        backButton = findViewById(R.id.back_button_m);
        saveButton = findViewById(R.id.save_button_m);
        deleteButton = findViewById(R.id.delete_button);
        row1 = findViewById(R.id.row1_m);
        row2 = findViewById(R.id.row2_m);
        row3 = findViewById(R.id.row3_m);
        row4 = findViewById(R.id.row4_m);

        //Define textViews and editTexts, depending on the section (films, series or shorts)

        switch (table){
            case "films":
                by ="idFilm";
                idTextView.setText(getResources().getStringArray(R.array.film_fields)[0]);
                nameTextView.setText(getResources().getStringArray(R.array.film_fields)[1]);
                originalNameTextView.setText(getResources().getStringArray(R.array.film_fields)[2]);
                nameCharacterTextView.setText(getResources().getStringArray(R.array.film_fields)[3]);
                dateReleaseTextView.setText(getResources().getStringArray(R.array.film_fields)[4]);
                directorTextView.setText(getResources().getStringArray(R.array.film_fields)[5]);
                durationTextView.setText(getResources().getStringArray(R.array.film_fields)[6]);
                languageTextView.setText(getResources().getStringArray(R.array.film_fields)[7]);
                genreTextView.setText(getResources().getStringArray(R.array.film_fields)[8]);
                break;

            case "series":
                by ="idSeries";
                idTextView.setText(getResources().getStringArray(R.array.series_fields)[0]);
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
                break;

            case "shorts":
                by ="idShort";
                idTextView.setText(getResources().getStringArray(R.array.short_fields)[0]);
                nameTextView.setText(getResources().getStringArray(R.array.short_fields)[1]);
                originalNameTextView.setText(getResources().getStringArray(R.array.short_fields)[2]);
                nameCharacterTextView.setText(getResources().getStringArray(R.array.short_fields)[3]);
                dateReleaseTextView.setText(getResources().getStringArray(R.array.short_fields)[4]);
                directorTextView.setText(getResources().getStringArray(R.array.short_fields)[5]);
                durationTextView.setText(getResources().getStringArray(R.array.short_fields)[6]);
                languageTextView.setText(getResources().getStringArray(R.array.short_fields)[7]);
                genreTextView.setText(getResources().getStringArray(R.array.short_fields)[8]);
                break;
        }

        list = sqh.findByID(sqdb,id,table, by);
        idEditText.setText(list.get(0));
        nameEditText.setText(list.get(1));
        originalNameEditText.setText(list.get(2));
        nameCharacterEditText.setText(list.get(3));
        dateReleaseEditText.setText(list.get(4));
        if(table.equals("series")){
            dateEndEditText.setText(list.get(5));
            seasonsEditText.setText(list.get(6));
            episodesEditText.setText(list.get(7));
            episodesCharacterEditText.setText(list.get(8));
            directorEditText.setText(list.get(9));
            durationEditText.setText(list.get(10));
            languageEditText.setText(list.get(11));
            genreEditText.setText(list.get(12));
        }
        else{
            layout.removeView(row1);
            layout.removeView(row2);
            layout.removeView(row3);
            layout.removeView(row4);
            directorEditText.setText(list.get(5));
            durationEditText.setText(list.get(6));
            languageEditText.setText(list.get(7));
            genreEditText.setText(list.get(8));
        }

        //Set buttons

        backButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                finish();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(checkVoids()){
                    error();
                }
                else{
                    if(table.equals("series")){
                        sqh.updateData(sqdb, ctx, id, nameEditText.getText().toString(),
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
                        sqh.updateData(sqdb,table, ctx, id, nameEditText.getText().toString(),
                                originalNameEditText.getText().toString(),
                                nameCharacterEditText.getText().toString(),
                                dateReleaseEditText.getText().toString(),
                                directorEditText.getText().toString(),
                                durationEditText.getText().toString(),
                                languageEditText.getText().toString(),
                                genreEditText.getText().toString());
                    }
                    Toast.makeText(ctx, "Updated succesfully.", Toast.LENGTH_SHORT).show();
                }

            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){

                String name = "";
                if(table.equals("series")){
                    name = table;
                }
                else{
                    name = table.substring(0,table.length()-1);
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(ctx).setTitle("Delete " + name)
                        .setMessage("Are you sure you want to delete this " + name + "?");

                final String finalName = name;
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int idOk){
                        sqh.deleteData(sqdb,id,table,ctx);
                        Toast.makeText(ctx, "The " + finalName + " has been deleted successfully.", Toast.LENGTH_SHORT).show();
                        setResult( Activity.RESULT_CANCELED );
                        finish();
                    }
                });
                builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int idCancel){
                        dialog.cancel();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

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

    private boolean checkVoids(){

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
}
