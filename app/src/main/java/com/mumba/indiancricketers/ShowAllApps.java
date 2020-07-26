package com.mumba.indiancricketers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ShowAllApps extends AppCompatActivity {
    TextView titletext;
    ImageView backbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_apps);
        titletext = findViewById(R.id.titletext);
        backbutton = findViewById(R.id.backbutton);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowAllApps.super.onBackPressed();
                finish();
            }
        });
        if(Variables.isEnglish){
            titletext.setText("More Apps");
        }else {
            titletext.setText("অন্যান্য অ্যাপ");
        }
    }
}