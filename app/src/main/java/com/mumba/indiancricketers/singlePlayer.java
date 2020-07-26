package com.mumba.indiancricketers;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;

import de.hdodenhof.circleimageview.CircleImageView;

public class singlePlayer extends AppCompatActivity {
    HashMap<String,String>mainmap;
    TabLayout tabLayout;
    ViewPager viewPager;
    HashMap<String,String> bangla;
    HashMap<String,String>english;
    CircleImageView playerimage;
    TextView playername;
    TextView currentteam;
    String gallery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_player);
        playerimage = findViewById(R.id.profileimageview);
        playername = findViewById(R.id.playername);
        currentteam = findViewById(R.id.playercountry);

        english = new HashMap<>();
        bangla = new HashMap<>();

        Intent i = getIntent();
        mainmap = (HashMap<String, String>)i.getSerializableExtra("data");

        String englishmapstr = mainmap.get("english");
        String banglamapstr = mainmap.get("bangla");
        String currentteamstr = mainmap.get("current_team");
        gallery = mainmap.get("gallery");
        String playerimagestr = mainmap.get("player_image");


        currentteam.setText("Indian "+currentteamstr);
        Glide
                .with(singlePlayer.this)
                .load(playerimagestr)
                .centerCrop()
                .placeholder(R.drawable.person)
                .into(playerimage);

        JSONObject enjsonobject,bnjsonobject;
        try {
            enjsonobject = new JSONObject(englishmapstr);
            bnjsonobject = new JSONObject(banglamapstr);
            Iterator<String> iter = enjsonobject.keys(); //This should be the iterator you want.
            while(iter.hasNext()){
                String key = iter.next();
                english.put(key,enjsonobject.getString(key));
            }
            Iterator<String> iter2 = bnjsonobject.keys(); //This should be the iterator you want.
            while(iter2.hasNext()){
                String key = iter2.next();
                bangla.put(key,bnjsonobject.getString(key));
            }
            if(Variables.isEnglish){
                playername.setText(english.get("full_name"));
            }else {
                playername.setText(bangla.get("bn_full_name"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }






        Log.e("totaldata",mainmap.toString());
        Log.e("data",english.toString());
        Log.e("data2",bangla.toString());

        viewPager = findViewById(R.id.viewpager);
        tabLayout = findViewById(R.id.tableLayout);


        Pager pager = new Pager(getSupportFragmentManager(),3);
        viewPager.setAdapter(pager);
        tabLayout.setupWithViewPager(viewPager);

        View root = tabLayout.getChildAt(0);
        if (root instanceof LinearLayout) {

            ((LinearLayout) root).setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
            GradientDrawable drawable = new GradientDrawable();
            drawable.setColor(getResources().getColor(R.color.primaryDark));
            drawable.setSize(2, 1);
            ((LinearLayout) root).setDividerPadding(10);
            ((LinearLayout) root).setDividerDrawable(drawable);
        }


    }

    public class Pager extends FragmentStatePagerAdapter {

        //integer to count number of tabs
        int tabCount;

        //Constructor to the class
        public Pager(FragmentManager fm, int tabCount) {
            super(fm);
            //Initializing tab count
            this.tabCount= tabCount;
        }

        //Overriding method getItem
        @NotNull
        @Override
        public Fragment getItem(int position) {
            //Returning the current tabs
            switch (position) {
                case 0:
                    TabOne tabOne = new TabOne();
                    Bundle bundle = new Bundle();
                    if(Variables.isEnglish){
                        bundle.putSerializable("data",english);
                    }else {
                        bundle.putSerializable("data",bangla);
                    }
                    tabOne.setArguments(bundle);
                    return tabOne;
                case 1:
                    TabTwo tabTwo = new TabTwo();
                    Bundle bundle2 = new Bundle();
                    if(Variables.isEnglish){
                        bundle2.putSerializable("data",english);
                    }else {
                        bundle2.putSerializable("data",bangla);
                    }
                    tabTwo.setArguments(bundle2);
                    return tabTwo;
                case 2:
                    TabThree tabThree = new TabThree();
                    Bundle bundle3 = new Bundle();
                    bundle3.putString("data",gallery);
                    tabThree.setArguments(bundle3);
                    return tabThree;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return tabCount;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    if(Variables.isEnglish){
                        return "Bio";
                    }else {
                        return "জীবন বৃত্তান্ত";
                    }
                case 1:
                    if(Variables.isEnglish){
                        return "Career";
                    }else {
                        return "পেশা";
                    }
                case 2:
                    if(Variables.isEnglish){
                        return "Gallery";
                    }else {
                        return "গ্যলারি";
                    }
                default:
                    return null;
            }
        }
    }
}