package com.mateosgarciapablo.task2_masfilmography;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HelpActivity extends AppCompatActivity {

    //Elements of the layout

    private TextView titleTextView;
    private TextView infoTextView;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        //Set up controls

        setUpControls();
    }

    private void setUpControls(){

        //Link variables with their layout elements

        titleTextView = findViewById(R.id.title_text_view_h);
        infoTextView = findViewById(R.id.info_text_view_h);
        backButton = findViewById(R.id.back_button_h);

        //Set the texts

        titleTextView.setText("Help");
        infoTextView.setText("To use this app, you should move between the three sections of the main screen and the lists of records will be shown.\n\n\n" +
                "Then, with writing on the 'Search Engine', selecting by what field you want to do the search in the list and clicking on the 'Search' button, you will be able to see the records that matches your search.\n\n\n" +
                "You can modify any of the records clicking on them, making the changes and clicking on the 'Save' button in the bottom right corner of the screen. In this screen, you are able to delete the record you are in by clicking in the 'Delete' button in the bottom center of the screen.\n\n\n" +
                "Besides, you can add new records clicking in the 'Add' button in the bottom center of the main screen, filling the data and clicking on the 'Save' button in the bottom right corner of the screen, as well.\n\n\n" +
                "If you have more question, you can contact me at:\n\n\n" +
                "S19001531@mail.glyndwr.ac.uk");

        //Set button

        backButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){

                finish();
            }
        });
    }
}