package com.mumba.indiancricketers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class PlayerProfile extends AppCompatActivity {
    TextView titletext,navtitlebar,aboutus,moreapps;
    ImageView backbutton,optionbutton;
    DrawerLayout layout;
    NavigationView navigationView;
    CardView aboutusview,moreappsview;
    ListView listView;

    static ArrayList<HashMap<String,String>> mainmap = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_profile);
        titletext = findViewById(R.id.titletext);
        backbutton = findViewById(R.id.backbutton);
        optionbutton = findViewById(R.id.options);
        navigationView = findViewById(R.id.nav_view);
        layout = findViewById(R.id.drawer);
        navtitlebar = navigationView.findViewById(R.id.navbartitletext);
        aboutus = navigationView.findViewById(R.id.aboutus);
        moreapps = navigationView.findViewById(R.id.otherapps);
        aboutusview = navigationView.findViewById(R.id.aboutusview);
        moreappsview = navigationView.findViewById(R.id.otherappsview);
        listView = findViewById(R.id.listviwe);

        //http://onlineappupdate.com/api/player/2/1

        aboutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PlayerProfile.this,ShowAbout.class));
            }
        });



        if(Variables.isEnglish){
            titletext.setText("Players List");
            aboutus.setText("About Us");
            moreapps.setText("More Apps");
            navtitlebar.setText("Indian Cricketers");
        }else {
            titletext.setText("খেলোয়াড়দের তালিকা");
            aboutus.setText("আমাদের সম্পর্কে");
            moreapps.setText("আরো অ্যাপ");
            navtitlebar.setText("ইন্ডিয়ান ক্রিকেটার্স");
        }


        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlayerProfile.super.onBackPressed();
            }
        });

        optionbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layout.openDrawer(GravityCompat.END);
            }
        });


        LoadData();

    }

    private void LoadData() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        Request request = new Request.Builder().get().url("https://onlineappupdate.com/api/player/2/1").build();
        Variables.Show_loader(PlayerProfile.this, false, false);
        Log.e("requesting","request");
        okHttpClient.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                Variables.cancel_loader();
                String responsestr = response.body().string();
                Log.e("response2", responsestr);
                JSONObject jsonObject;
                JSONArray array;
                try {
                    jsonObject = new JSONObject(responsestr);
                    array = jsonObject.getJSONArray("players");

                    for(int i = 0;i<array.length();i++){
                        Log.e("array",array.get(i).toString());

                        HashMap<String,String> yourHashMap = new HashMap<>();

                        JSONObject jsonObject1 = array.getJSONObject(i);
                        Iterator<String> iter = jsonObject1.keys(); //This should be the iterator you want.
                        while(iter.hasNext()){
                            String key = iter.next();
                            yourHashMap.put(key,jsonObject1.getString(key));
                        }
                        if(!mainmap.contains(yourHashMap)){
                            mainmap.add(yourHashMap);
                        }
                    }
                    PlayerProfile.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ListViewAdapter adapter = new ListViewAdapter(PlayerProfile.this,R.layout.singleprofile,mainmap);
                            listView.setAdapter(adapter);
                        }
                    });

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.e("error", e.getMessage());
            }
        });



    }


    class ListViewAdapter extends ArrayAdapter<HashMap<String,String>> {
        ArrayList<HashMap<String,String>> mylist;
        public ListViewAdapter(@NonNull Context context, int resource, ArrayList<HashMap<String,String>> list) {
            super(context, resource, list);
            mylist = list;
        }
        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View vi = getLayoutInflater().inflate(R.layout.singleprofile, parent, false);
            TextView singletitlename;
            CardView singleecardView;
            CircleImageView circleImageView;
            singletitlename = vi.findViewById(R.id.singleplayername);
            singleecardView = vi.findViewById(R.id.singleplayerview);
            circleImageView = vi.findViewById(R.id.singleplayerimage);



            if(Variables.isEnglish){
                try {
                    JSONObject object = new JSONObject(mylist.get(position).get("english"));
                    singletitlename.setText(object.getString("full_name"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }else {
                try {
                    JSONObject object = new JSONObject(mylist.get(position).get("bangla"));
                    singletitlename.setText(object.getString("bn_full_name"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }


            Glide
                    .with(PlayerProfile.this)
                    .load(mylist.get(position).get("player_image"))
                    .centerCrop()
                    .placeholder(R.drawable.person)
                    .into(circleImageView);
            singletitlename.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i =new Intent(PlayerProfile.this,singlePlayer.class);
                    i.putExtra("data",mylist.get(position));
                    startActivity(i);
                }
            });


            singleecardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent i =new Intent(PlayerProfile.this,singlePlayer.class);
                    i.putExtra("data",mylist.get(position));
                    startActivity(i);
                }
            });
            return vi;
        }
    }

}