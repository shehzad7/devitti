package com.example.please.devitti;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.PagerTitleStrip;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;


public class HelperMainGui extends FragmentActivity {

    ViewPager viewPager = null;
    PagerTitleStrip titleStrip;
    //    android.app.ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_act);


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

    }

    class myPageAdapter extends FragmentPagerAdapter {

        public myPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {

            Fragment fragment = null;
            if (i == 0) {
                fragment = new HelperMainGUIFragmentASearch();
            } else if (i == 1) {
                fragment = new HelperMainGUIFragmentBProfile();
            }
            else if (i==2)
            {
                fragment = new HelperMainGUIFragmentCFeed();
            }


            return fragment;
        }

        @Override
        public int getCount() {
            return 3;
        }

        public CharSequence getPageTitle(int position){
            String title =  new String();
            if (position==0)
            {
                return "Search";
            }
            if(position==1)
            {
                return  "Profile";
            }
            if(position==2)
            {
                return  "Feed";
            }

            return null;
        }
    }


}
