package com.example.please.devitti;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    Button signIn;
    Button signUp;
    Button helperSearch;
    Button needyMakeACause;
    Button needyExistingCauses;

    Button helperOwnProfile;
    Button helperFeed;
    Button helperSearchResult;
    Button mapSearch;
    Button needyClickOnOwnCause;
    Button needyProfile;
    Button needyOthersClickOnCause;

    Button causeFullDetailView;

    Button needyMainGui;
    Button helperMainGui;

    Button jSon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signIn= (Button) findViewById(R.id.testSignIn);
        signUp= (Button) findViewById(R.id.testSignUp);
        helperSearch= (Button) findViewById(R.id.testhelperSearch);
        needyMakeACause = (Button) findViewById(R.id.testNeedyMakeACause);
        needyExistingCauses = (Button) findViewById(R.id.testNeedExistingCauses);
        helperOwnProfile = (Button) findViewById(R.id.testHelperOwnProfile);
        helperFeed  = (Button) findViewById(R.id.testHelperFeed);
        helperSearchResult  = (Button) findViewById(R.id.testHelperSearchResult);
        mapSearch  = (Button) findViewById(R.id.testMapSearch);
        needyClickOnOwnCause  = (Button) findViewById(R.id.testNeedyClickOnOwnCause);
        needyProfile  = (Button) findViewById(R.id.testNeedyProfile);
        needyOthersClickOnCause  = (Button) findViewById(R.id.testNeedyOthersClickOnCause);

        causeFullDetailView  = (Button) findViewById(R.id.testCauseFullDetailView);
        needyMainGui = (Button) findViewById(R.id.testNeedyMainGui);
        helperMainGui = (Button) findViewById(R.id.testhelperMainGui);

        jSon = (Button) findViewById(R.id.testJson);


        //Setting listeners to all the buttons for the demo view
        setListenersToButtons();






    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void setListenersToButtons()
    {
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openSignInPage = new Intent(MainActivity.this, SignIn.class);
                startActivity(openSignInPage);
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openSignUpPage = new Intent(MainActivity.this, SignUp.class);
                startActivity(openSignUpPage);
            }
        });

        helperSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openHelperSearchPage = new Intent(MainActivity.this, HelperSearch.class);
                startActivity(openHelperSearchPage);
            }
        });


        needyMakeACause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openNeedyMakeACausePage = new Intent(MainActivity.this, NeedyMakeACause.class);
                startActivity(openNeedyMakeACausePage);

//                return false;
            }
        });


        needyExistingCauses.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent openNeedyExistingCausesPage = new Intent(MainActivity.this, NeedyExistingCauses.class);
                startActivity(openNeedyExistingCausesPage);

//                return false;
            }
        });


        helperOwnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent openHelperOwnProfilePage = new Intent(MainActivity.this, HelperOwnProfile.class);
                startActivity(openHelperOwnProfilePage);
            }
        });


        helperFeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent openHelperFeedPage = new Intent(MainActivity.this, HelperFeed.class);
                startActivity(openHelperFeedPage);
            }
        });

        helperSearchResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent openHelperSearchResultPage = new Intent(MainActivity.this, HelperSearchResult.class);
                startActivity(openHelperSearchResultPage);
            }
        });


        mapSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent openMapSearchPage = new Intent(MainActivity.this, Maps.class);
                startActivity(openMapSearchPage);
            }
        });



        needyClickOnOwnCause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent openNeedyClickOnOwnCausePage = new Intent(MainActivity.this, NeedyClickOnOwnCause.class);
                startActivity(openNeedyClickOnOwnCausePage);
            }
        });

        needyProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent openNeedyProfilePage = new Intent(MainActivity.this, NeedyProfile.class);
                startActivity(openNeedyProfilePage);
            }
        });


        needyOthersClickOnCause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent openNeedyOthersClickOnCausePage = new Intent(MainActivity.this, NeedyOthersClickOnCause.class);
                startActivity(openNeedyOthersClickOnCausePage);
            }
        });

        causeFullDetailView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent openCauseFullDetailViewPage = new Intent(MainActivity.this, CauseFullDetailView.class);
                startActivity(openCauseFullDetailViewPage);
            }
        });

        needyMainGui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent openCauseFullDetailViewPage = new Intent(MainActivity.this, NeedyMainGui.class);
            startActivity(openCauseFullDetailViewPage);
        }
        });

        helperMainGui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent openCauseFullDetailViewPage = new Intent(MainActivity.this, HelperMainGui.class);
                startActivity(openCauseFullDetailViewPage);
            }
        });




        jSon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openCauseFullDetailViewPage = new Intent(MainActivity.this, JSONUseActivity.class);
                startActivity(openCauseFullDetailViewPage);
            }

        });


    }




}





