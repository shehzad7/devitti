package com.example.please.devitti;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


/**
 * A simple {@link android.app.Fragment} subclass.
 */
public class HelperMainGUIFragmentCFeed extends Fragment {

    ListView HFeedList;

    String [] feed = {"__________________Please confirm RS 1000 sent back to you by usman232\n" +
            "Rs: 4000",
            "Please confirm the amount sent back to you by akram424\n" +
                    "Rs: 4000",
            "Please confirm the amount sent back to you by  akram424\nRs: 4000",
            "Please confirm RS 1000 sent back to you by uzair545\n" +
                    "Rs: 4000",
            "Please confirm RS 1000 sent back to you by Fawad654\n" +
                    "Rs: 4000",
            "Please confirm the amount sent back to you by Salman543\nRs: 4500",
            "Please confirm the amount sent back to you by Anas231\n" +
                    "Rs: 4000",
            "Please confirm the amount sent back to you by Younis3434\n" +
                    "Rs: 4000",
            "Please confirm the amount sent back to you by akram424\n" +
                    "Rs: 4000",
            "Please confirm the amount sent back to you by akram424\nRs: 4000",
            "Please confirm the amount sent back to you by uzair545\n" +
                    "Rs: 4000",
            "Please confirm the amount sent back to you by Fawad654\n" +
                    "Rs: 4000",
            "Please confirm the amount sent back to you by Salman543\nRs: 4500",
            "Please confirm the amount sent back to you by Anas231\n" +
                    "Rs: 4000",
            "Please confirm the amount sent back to you by Younis3434\n" +
                    "Rs: 4000"};


    public HelperMainGUIFragmentCFeed() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.helper_feed, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        HFeedList = (ListView) getActivity().findViewById(R.id.HFeedList);
        ArrayAdapter<String> at =  new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,feed);
        HFeedList.setAdapter(at);

    }



}
