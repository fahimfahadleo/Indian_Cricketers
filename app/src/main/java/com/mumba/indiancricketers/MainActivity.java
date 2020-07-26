package com.mumba.indiancricketers;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView playersinfo,sportsnews,chooselanguage;
    AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playersinfo = findViewById(R.id.playersinfo);
        sportsnews = findViewById(R.id.sportsnews);
        chooselanguage = findViewById(R.id.chooseLanguage);
        if(Variables.isEnglish){
            playersinfo.setText("Indian Players");
            sportsnews.setText("Sports News");
            chooselanguage.setText("Choose Language");

        }else {
            playersinfo.setText("ইন্ডিয়ান খেলোয়াড়");
            sportsnews.setText("খেলার সংবাদ");
            chooselanguage.setText("ভাষা নির্বাচন করুন");
        }

        playersinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,PlayerProfile.class));
            }
        });
        sportsnews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,showSportsNews.class));
            }
        });



        chooselanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLanguageDialogue();
            }
        });
    }


    void showLanguageDialogue(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        View vi = getLayoutInflater().inflate(R.layout.dialogue,null,false);
        TextView titletext = vi.findViewById(R.id.languagechooserdialogue);
        Button englishlanguage = vi.findViewById(R.id.englishbutton);
        Button banglalanguage = vi.findViewById(R.id.banglabutton);
        if(Variables.isEnglish){
            titletext.setText("Choose Language");
        }else {
            titletext.setText("ভাষা নির্বাচন করুন");
        }
        englishlanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Variables.isEnglish = true;
                dialog.dismiss();
                finish();
                Intent i = new Intent(MainActivity.this,MainActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });
        banglalanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Variables.isEnglish = false;
                dialog.dismiss();
                finish();
                Intent i = new Intent(MainActivity.this,MainActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });
        builder.setView(vi);
        dialog = builder.create();
        dialog.show();
    }
}