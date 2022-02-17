package com.mateosgarciapablo.task2_masfilmography;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {

    //Elements of the layout

    private TextView titleTextView;
    private TextView infoTextView;
    private TextView sectionTitleTextView;
    private TextView sectionTextView;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        //Set up controls

        setUpControls();
    }

    private void setUpControls() {

        //Link variables with their layout elements

        titleTextView = findViewById(R.id.title_text_view);
        infoTextView = findViewById(R.id.info_text_view);
        sectionTitleTextView = findViewById(R.id.section_title_text_view);
        sectionTextView = findViewById(R.id.section_text_view);
        backButton = findViewById(R.id.back_button_a);

        //Set the texts

        titleTextView.setText("About this app");
        infoTextView.setText("This app shows you the whole filmography of the actor Miguel Ángel Silvestre, including his films, series and shorts.\n\n\n" +
                "Besides that, you are able to add new records, modify the current ones or delete records if you want to.\n\n\n" +
                "If you have any doubt about the app, please click on the help button.");

        sectionTitleTextView.setText("About Miguel Ángel Silvestre");
        sectionTextView.setText("Miguel Ángel Silvestre Rambla (born 6 April 1982) is a Spanish actor.\n\n\n" +
                "He is best known for playing the roles of Lito Rodríguez and Franklin Jurado in the Netflix original series 'Sense8' and 'Narcos' respectively and El Duque in the Spanish television series 'Sin tetas no hay paraíso'.\n\n\n");

        //Set button

        backButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){

                finish();
            }
        });
    }
}