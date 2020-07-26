package com.mumba.indiancricketers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class ShowAbout extends AppCompatActivity {
    ImageView backbutton;
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_about);
        backbutton = findViewById(R.id.backbutton);
        title = findViewById(R.id.titletext);
        if(Variables.isEnglish){
            title.setText("About Us");
        }else {
            title.setText("আমাদের সম্পর্কে");
        }
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowAbout.super.onBackPressed();
                finish();
            }
        });
    }
}