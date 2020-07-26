package com.mumba.indiancricketers;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import java.util.HashMap;

public class TabOne extends Fragment {
    TextView personaltitle,fullname,fullnamevalue,nickname,nicknamevalue,profession,
            professionvalue,birthdaty,birthdayvalue,birthplace,birthplacevalue,height,
            heightvalue,zodiack,zodiackvalue,eyecolor,eyecolorvalue,haircolor,
            haircolorvalue,spouse,spousevalue,earlylifetitle,earlylifevalue;
    HashMap<String,String> mainmap;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Returning the layout file after inflating
        //Change R.layout.tab1 in you classes
        View vi = inflater.inflate(R.layout.tab1,container,false);
        Bundle b = getArguments();

        mainmap = (HashMap<String, String>) b.getSerializable("data");
        Log.e("data",mainmap.toString());
        init(vi);

        return vi;
    }
    void init(View vi){
        personaltitle = vi.findViewById(R.id.infortitle);
        nickname = vi.findViewById(R.id.nickname);
        fullname = vi.findViewById(R.id.fullname);
        profession = vi.findViewById(R.id.profession);
        birthdaty = vi.findViewById(R.id.birthdate);
        birthplace = vi.findViewById(R.id.birthplace);
        height = vi.findViewById(R.id.height);
        zodiack= vi.findViewById(R.id.zodiac);
        eyecolor= vi.findViewById(R.id.eyecolor);
        haircolor= vi.findViewById(R.id.haircolor);
        spouse= vi.findViewById(R.id.spouse);
        earlylifetitle= vi.findViewById(R.id.earlylife);
        nicknamevalue = vi.findViewById(R.id.nicknamevalue);
        fullnamevalue = vi.findViewById(R.id.fullnamevalue);
        professionvalue = vi.findViewById(R.id.professionvalue);
        birthdayvalue = vi.findViewById(R.id.birthdatevalue);
        birthplacevalue = vi.findViewById(R.id.birthplacevalue);
        heightvalue = vi.findViewById(R.id.heightvalue);
        zodiackvalue = vi.findViewById(R.id.zodiacvalue);
        eyecolorvalue = vi.findViewById(R.id.eyecolorvalue);
        haircolorvalue = vi.findViewById(R.id.haircolorvalue);
        spousevalue = vi.findViewById(R.id.spousevalue);
        earlylifevalue = vi.findViewById(R.id.earlylifevalue);

        if(Variables.isEnglish){
            personaltitle.setText("Personal Information");
            nickname.setText("Nick Name: ");
            fullname.setText("Full Name: ");
            profession.setText("Profession: ");
            birthdaty.setText("Birthday: ");
            birthplace.setText("Birth Place: ");
            height.setText("Height: ");
            zodiack.setText("Zodiac: ");
            eyecolor.setText("Eye Color: ");
            haircolor.setText("Hair Color: ");
            spouse.setText("Spouse: ");
            earlylifetitle.setText("Early Life: ");

            nicknamevalue.setText(mainmap.get("nick_name"));
            fullnamevalue.setText(mainmap.get("full_name"));
            professionvalue.setText(mainmap.get("profession"));
            birthdayvalue.setText(mainmap.get("birth_date"));
            birthplacevalue.setText(mainmap.get("birth_place"));
            heightvalue.setText(mainmap.get("height"));
            zodiackvalue.setText(mainmap.get("zodiac_sign"));
            eyecolorvalue.setText(mainmap.get("eye_color"));
            haircolorvalue.setText(mainmap.get("hair_color"));
            spousevalue.setText(mainmap.get("spouse"));
            earlylifevalue.setText(mainmap.get("early_life"));
        }else {
            personaltitle.setText("ব্যক্তিগত তথ্য");
            fullname.setText("পুর্ন নামঃ ");
            nickname.setText("ডাক নামঃ ");
            profession.setText("পেশাঃ ");
            birthdaty.setText("জন্ম তারিখঃ ");
            birthplace.setText("জন্মস্থানঃ ");
            height.setText("উচ্চতাঃ ");
            zodiack.setText("রাশিঃ ");
            eyecolor.setText("চোখের রংঃ ");
            haircolor.setText("চুলের রংঃ ");
            spouse.setText("পত্নিঃ ");
            earlylifetitle.setText("জিবনের প্রথমার্ধ");
            nicknamevalue.setText(mainmap.get("bn_nick_name"));
            fullnamevalue.setText(mainmap.get("bn_full_name"));
            professionvalue.setText(mainmap.get("bn_profession"));
            birthdayvalue.setText(mainmap.get("bn_birth_date"));
            birthplacevalue.setText(mainmap.get("bn_birth_place"));
            heightvalue.setText(mainmap.get("bn_height"));
            zodiackvalue.setText(mainmap.get("bn_zodiac_sign"));
            eyecolorvalue.setText(mainmap.get("bn_eye_color"));
            haircolorvalue.setText(mainmap.get("bn_hair_color"));
            spousevalue.setText(mainmap.get("bn_spouse"));
            earlylifevalue.setText(mainmap.get("bn_early_life"));
        }
    }
}
