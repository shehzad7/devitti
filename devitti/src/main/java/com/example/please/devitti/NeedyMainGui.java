package com.example.please.devitti;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class NeedyMainGui extends FragmentActivity {

    ViewPager viewPager = null;
//    android.app.ActionBar actionBar;

    NeedyMainGUIFragmentA existingCausesFragment;
    NeedyMainGUIFragmentB makeACauseFragment;

    PagerTabStrip titleStrip;

    String[] userDetailsArray = new String[13];


    String  [] ctIds= null ;
    String  [] ctNms = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_act);


        //getting the data from the sign In activity
        Bundle b=this.getIntent().getExtras();
        userDetailsArray = b.getStringArray("needy");
        ctIds = b.getStringArray("ctIDs");
        ctNms = b.getStringArray("ctNms");





        viewPager = (ViewPager) findViewById(R.id.pager);
        FragmentManager fragmentManager = getSupportFragmentManager();

        viewPager.setAdapter(new myPageAdapter(fragmentManager))    ;

        titleStrip = (PagerTabStrip) findViewById(R.id. titleStrip);


        for (int i = 0; i < titleStrip.getChildCount(); ++i) {
            View nextChild = titleStrip.getChildAt(i);
            if (nextChild instanceof TextView) {
                TextView textViewToConvert = (TextView) nextChild;
                textViewToConvert.setTypeface(Typeface.DEFAULT_BOLD);
            }
        }



//        Toast.makeText(getApplicationContext(), "Data given to this Needy Main GUI:  "+userDetailsArray[1]+" "+userDetailsArray[2] + " "+
//                userDetailsArray[3] + " "+
//                userDetailsArray[4]+ " "+
//                userDetailsArray[5]+ " "+
//                userDetailsArray[6]+ " "+
//                userDetailsArray[7]+ " "+
//                userDetailsArray[8]+ " "+
//                userDetailsArray[9]+ " "+
//                userDetailsArray[10]+ " "+
//                userDetailsArray[11]+ " "+
//                userDetailsArray[12],
//                Toast.LENGTH_LONG).show();
        //everything realted to the sub fragments, starts after this

//        existingCausesFragment.changeData("Data given to this Needy Main GUI:  ");






    }

        class myPageAdapter extends FragmentPagerAdapter {

            public myPageAdapter(FragmentManager fm) {
                super(fm);
            }

            @Override
            public Fragment getItem(int i) {

                Bundle bdl=new Bundle();
                bdl.putStringArray("existingCauses", userDetailsArray);
                bdl.putStringArray("ctIDs" ,ctIds);
                bdl.putStringArray("ctNms" ,ctNms);



                Fragment fragment = null;
                if (i == 0) {
//                    Bundle bdl=new Bundle();
//                    bdl.putStringArray("existingCauses", userDetailsArray);

                    fragment = new NeedyMainGUIFragmentA();
                    fragment.setArguments(bdl);

//                    existingCausesFragment = (NeedyMainGUIFragmentA) fragment;

                } else if (i == 1) {
                    fragment = new NeedyMainGUIFragmentB();
                    fragment.setArguments(bdl);


//                    makeACauseFragment = (NeedyMainGUIFragmentB) fragment;
                }


                return fragment;
            }

            @Override
            public int getCount() {
                return 2;
            }
            public CharSequence getPageTitle(int position){
                String title =  new String();
                if (position==0)
                {
                    return "Existing Causes";
                }
                if(position==1)
                {
                   return  "Make A Cause";
                }
                return null;
            }
        }


    }
