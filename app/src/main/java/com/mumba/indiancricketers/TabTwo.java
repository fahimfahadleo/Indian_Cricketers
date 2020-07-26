package com.mumba.indiancricketers;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.HashMap;

public class TabTwo extends Fragment {
    HashMap<String,String> mainmap;
    TextView battingtitle,battingstyle,battingstylevalue,totalmatch,totalmatchvalue,totalinngs,totalinngsvalue,
            totalruns,totalrunsvalue,ballingtitle,ballingstyle,ballingstylevalue,totalmatchballs,totalmatchballsvalue,
            totalwicketballs,totalwicketballsvalue,totalrunsballs,totalrunsballsvalue,team,teamvalue;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Returning the layout file after inflating
        //Change R.layout.tab1 in you classes
        View vi = inflater.inflate(R.layout.tab2,container,false);
        Bundle b = getArguments();
        mainmap = (HashMap<String, String>) b.getSerializable("data");
        init(vi);
        return vi;
    }
    void init(View vi){
        battingtitle = vi.findViewById(R.id.battingtitle);
        battingstyle = vi.findViewById(R.id.battingstyle);
        battingstylevalue = vi.findViewById(R.id.battingstylevalue);
        totalmatch = vi.findViewById(R.id.totalmatch);
        totalmatchvalue = vi.findViewById(R.id.totalmatchvalue);
        totalinngs = vi.findViewById(R.id.totalinngs);
        totalinngsvalue = vi.findViewById(R.id.totalinngsvalue);
        totalruns = vi.findViewById(R.id.totalruns);
        ballingtitle = vi.findViewById(R.id.ballingtitle);
        totalrunsvalue = vi.findViewById(R.id.totalrunsvalue);
        ballingstyle = vi.findViewById(R.id.ballingstyle);
        ballingstylevalue = vi.findViewById(R.id.ballingstylevalue);
        totalmatchballs = vi.findViewById(R.id.totalmatchballs);
        totalmatchballsvalue = vi.findViewById(R.id.totalmatchballsvalue);
        totalwicketballs = vi.findViewById(R.id.totalwicket);
        totalwicketballsvalue = vi.findViewById(R.id.totalwicketvalue);
        totalrunsballs = vi.findViewById(R.id.totalrunsballs);
        totalrunsballsvalue = vi.findViewById(R.id.totalrunsballsvalue);
        team = vi.findViewById(R.id.team);
        teamvalue = vi.findViewById(R.id.teamvalue);


        if(Variables.isEnglish){
            battingtitle.setText("Batting: ");
            battingstyle.setText("Batting Style: ");
            totalmatch.setText("Total Match: ");
            totalinngs.setText("Total Inngs: ");
            totalruns.setText("Total Runs: ");
            ballingtitle.setText("Balling: ");
            totalmatchballs.setText("Total match: ");
            ballingstyle.setText("Balling Style: ");
            totalwicketballs.setText("Total Wicket: ");
            totalrunsballs.setText("Total Run: ");
            team.setText("Team");

            battingstylevalue.setText(mainmap.get("batting_style"));
            totalmatchvalue.setText(mainmap.get("total_match"));
            totalinngsvalue.setText(mainmap.get("total_inngs"));
            totalrunsvalue.setText(mainmap.get("total_run"));

            ballingstylevalue.setText(mainmap.get("bowling_style"));
            totalrunsballsvalue.setText(mainmap.get("total_run_bowl"));
            totalwicketballsvalue.setText(mainmap.get("total_wicket"));
            totalmatchballsvalue.setText(mainmap.get("total_match_bowl"));
            teamvalue.setText(mainmap.get("team"));

        }else {
            battingtitle.setText("ব্যটিংঃ  ");
            battingstyle.setText("ব্যটিং ধরণঃ ");
            totalmatch.setText("মোট খেলাঃ ");
            totalinngs.setText("মোট ইনিংসঃ ");
            totalruns.setText("মোট রানঃ ");
            ballingtitle.setText("বোলিং");
            totalmatchballs.setText("মোট খেলাঃ ");
            ballingstyle.setText("বোলিং ধরণঃ ");
            totalwicketballs.setText("মোট উইকেটঃ ");
            totalrunsballs.setText("মোট রানঃ ");
            team.setText("দল");

            battingstylevalue.setText(mainmap.get("bn_batting_style"));
            totalmatchvalue.setText(mainmap.get("bn_total_match"));
            totalinngsvalue.setText(mainmap.get("bn_total_inngs"));
            totalrunsvalue.setText(mainmap.get("bn_total_run"));

            ballingstylevalue.setText(mainmap.get("bn_bowling_style"));
            totalrunsballsvalue.setText(mainmap.get("bn_total_run_bowl"));
            totalwicketballsvalue.setText(mainmap.get("bn_total_wicket"));
            totalmatchballsvalue.setText(mainmap.get("bn_total_match_bowl"));
            teamvalue.setText(mainmap.get("bn_team"));

        }





    }


}















