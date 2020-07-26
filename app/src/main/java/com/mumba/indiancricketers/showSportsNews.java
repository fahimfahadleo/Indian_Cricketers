package com.mumba.indiancricketers;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static java.security.AccessController.getContext;

public class showSportsNews extends AppCompatActivity {
    ListView listView;
    ImageView backbutton;
    TextView titletext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_sports_news);
        listView = findViewById(R.id.mListView);
        backbutton = findViewById(R.id.backbutton);
        titletext = findViewById(R.id.sportsnews);

        if(Variables.isEnglish){
            titletext.setText("Sports News");
        }else {
            titletext.setText("খেলার খবর");
        }

        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSportsNews.super.onBackPressed();
            }
        });
        getSingleNews();
    }


    class mListViewAdapter extends ArrayAdapter<HashMap<String, String>> {
        ArrayList<HashMap<String, String>> mylist;
        public mListViewAdapter(@NonNull Context context, int resource, ArrayList<HashMap<String, String>> list) {
            super(context, resource, list);
            mylist = list;
        }
        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View vi = getLayoutInflater().inflate(R.layout.singlenews, parent, false);
            TextView titleen, descriptionen;
            ImageView thumbnil;
            titleen = vi.findViewById(R.id.titleen);
            descriptionen = vi.findViewById(R.id.descriptionEn);
            thumbnil = vi.findViewById(R.id.thumbnil);

            if(mylist.size()!=0){

                Glide
                        .with(showSportsNews.this)
                        .load(mylist.get(position).get("thumbnail"))
                        .centerCrop()
                        .into(thumbnil);
                String descriptionconverteren;
                if(Variables.isEnglish){
                    titleen.setText(mylist.get(position).get("titleEn"));
                    descriptionconverteren = Jsoup.parse(mylist.get(position).get("descriptionEn")).text();
                }else {
                    titleen.setText(mylist.get(position).get("titleBn"));
                    descriptionconverteren = Jsoup.parse(mylist.get(position).get("descriptionBn")).text();
                }

                Log.e("eng",descriptionconverteren);
                descriptionen.setText(descriptionconverteren);

            }

            return vi;
        }
    }
    ArrayList<HashMap<String, String>> map = new ArrayList<>();


    private void getSingleNews() {
        map = new ArrayList<>();

        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        Request request = new Request.Builder().get().url("https://news.onlineappupdate.com/api/v1/category/9").build();
        //  Functions.Show_loader(getContext(), false, false);
        okHttpClient.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String responsestr = response.body().string();

                Log.e("response2", responsestr);

                JSONObject firstjsonObject;
                JSONObject secondJSonOBject;
                try {
                    firstjsonObject = new JSONObject(responsestr);
                    secondJSonOBject = firstjsonObject.getJSONObject("data");

                    JSONArray array = secondJSonOBject.getJSONArray("news");
                    HashMap<String, String> yourHashMap = null;
                    for (int i = 0; i < array.length(); i++) {
                        yourHashMap = new Gson().fromJson(array.getJSONObject(i).toString(), HashMap.class);
                        map.add(yourHashMap);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                showSportsNews.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mListViewAdapter adapter = new mListViewAdapter(showSportsNews.this,R.layout.singlenews,map);
                        listView.setAdapter(adapter);
                    }
                });


            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.e("error", e.getMessage());
            }
        });

    }


}