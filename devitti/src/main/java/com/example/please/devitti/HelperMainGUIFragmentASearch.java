package com.example.please.devitti;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;


/**
 * A simple {@link android.app.Fragment} subclass.
 */
public class HelperMainGUIFragmentASearch extends Fragment {
    _DATABASEManager dM =  new _DATABASEManager();

    String [] dataFromSearchFields;

    Spinner categorySpinner;
    Spinner amountRangeSpinner;
    Spinner causeTypeSpinner;

    EditText email;
    EditText country;
    EditText city;

    ImageButton searchButton;

    String[] dataFromSignIn;
    String[] ctIds;
    String[] ctNms;

    public HelperMainGUIFragmentASearch() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        dataFromSignIn = getArguments().getStringArray("searchAndMore");
        ctIds = getArguments().getStringArray("ctIDs");
        ctNms = getArguments().getStringArray("ctNms");


        // Inflate the layout for this fragment
        View myView = inflater.inflate(R.layout.helper_search, container, false);

        searchButton = (ImageButton) myView.findViewById(R.id.HSSearchButton);
        email = (EditText) myView.findViewById(R.id.HSEmail);
        country = (EditText) myView.findViewById(R.id.HSCountry);
        city = (EditText) myView.findViewById(R.id.HSCity);


        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String [] data = getRelevantQuery();

                new getCausesForSearch().execute();

//            IT COULD BE USED TO GO TO THE NEXT CAUSES WINDOW
//               Intent  intn = new Intent(getActivity(), HelperSearchResult.class);
//                startActivity(intn);
            }
        });


//        setListenersToButtons();

        return myView;


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        email.setText("");
        country.setText("");
        city.setText("");

        String[] categroyData = ctNms;
        String temp []  = new String[ctNms.length+1];
        for (int i  = 0 ; i <temp.length-1 ; i ++)
        {
            temp[i+1] = ctNms[i];
        }
        temp[0]  = "select any";

        categroyData = temp;

        categorySpinner = (Spinner) getActivity().findViewById(R.id.catagory_spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, categroyData);
        categorySpinner.setAdapter(adapter);


        String[] amountRangeData = {"select any"," 500-1000", "1000-2000", "2000-5000", "5000-10000", "10000+"};
        amountRangeSpinner = (Spinner) getActivity().findViewById(R.id.amount_range_spinner);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, amountRangeData);
        amountRangeSpinner.setAdapter(adapter2);

        String[] CaueseTypeData = {"select any","loan", "donation"};
        causeTypeSpinner = (Spinner) getActivity().findViewById(R.id.cause_type_spinner);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, CaueseTypeData);
        causeTypeSpinner.setAdapter(adapter3);


    }

    private void setListenersToButtons() {
//        searchButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
////                Intent openCauseFullDetailViewPage = new Intent(getActivity().getClass(), HelperMainGui.class);
////                getActivity()startActivity(openCauseFullDetailViewPage);
//
//
//            }
//        });


    }


    boolean isEmpty(EditText text) {
        return text.getText().toString().trim().length() == 0;
    }

    boolean isNotEmpty(EditText text) {
        return text.getText().toString().trim().length() != 0;
    }


    public String [] getRelevantQuery() {

        dataFromSearchFields = new String[6];

//        String query  = "Select * from Causes"
        if (isNotEmpty(email)) {
            dataFromSearchFields [0]  = email.getText().toString();
        }
        else { dataFromSearchFields [0] = "non" ;}

        if (isNotEmpty(country)) {
            dataFromSearchFields [1]  = country.getText().toString();
        }
        else { dataFromSearchFields [1] = "non" ;}

        if (isNotEmpty(city)) {
            dataFromSearchFields [2]  = city.getText().toString();
        }
        else { dataFromSearchFields [2] = "non" ;}

        if (!(causeTypeSpinner.getSelectedItem().toString().contains("select any")))
        {
            dataFromSearchFields [3]  = causeTypeSpinner.getSelectedItem().toString();
        }
        else { dataFromSearchFields [3] = "non" ;}

        if (!(categorySpinner.getSelectedItem().toString().contains("select any")))
        {
            dataFromSearchFields [4]  = categorySpinner.getSelectedItem().toString();
        }
        else { dataFromSearchFields [4] = "non" ;}

        if (!(amountRangeSpinner.getSelectedItem().toString().contains("select any")))
        {
            dataFromSearchFields [5]  = amountRangeSpinner.getSelectedItem().toString();
        }
        else { dataFromSearchFields [5] = "non" ;}



        System.out.println(dataFromSearchFields[0] +"  "+ dataFromSearchFields[1] +"  "+ dataFromSearchFields[2]
                +"  "+ dataFromSearchFields[3] +"  "+ dataFromSearchFields[4] +"  "+ dataFromSearchFields[5]);

//        Toast.makeText(getActivity().getApplicationContext(), "Email: " + email.getText() + " Country: " + country.getText()
//                        + " City: " + city.getText() + " Cause Type: " + causeTypeSpinner.getSelectedItem().toString() + " Catagory: " + categorySpinner.getSelectedItem().toString()
//                        + " Amount Range: " + amountRangeSpinner.getSelectedItem().toString(),
//                Toast.LENGTH_SHORT).show();





        return dataFromSearchFields;

    }



    public class getCausesForSearch extends AsyncTask<Integer, Integer, Cause[]>
    {


        protected Cause[] doInBackground(Integer... params) {

//            Cause caus = new Cause(causeId,needyId,moneyAskedFor,descrip,status,type,dateOfRequest,dateOfCompletion,dateOfMaturity,lati,longi,null,catagory);
//            return dM.getLendingDetailsForACause(new Cause("2","","","","","","","","","","",null,""));
            System.out.println("<<<<<<<<>>>>>>>>>> I am here");
            return  dM.getSearchResultForHelper (dataFromSearchFields);


        }

        @Override
        protected void onPostExecute(Cause[] st) {
            super.onPostExecute(st);

            System.out.println("<<<<<<<<>>>>>>>>>> got the result");
            if (st!=null)
            {
                Cause [] cuSr  = st;
                // going to the next Search Result window
                Intent  intnt = new Intent(getActivity(), HelperSearchResult.class);
                intnt.putExtra("searchCauses" ,cuSr );

                startActivity(intnt);


                System.out.println("<<<<<<<<>>>>>>>>>> Its not null, the length is : "+ st.length);
                String text = "";
                for (int  i =  0 ; i <st.length ; i ++)
                {
                    text += "\n " + "Cause ID =  " +st[i].causeId  + " Amount: " + st[i].moneyAskedFor ;
                    System.out.println("Cause ID =  " +st[i].causeId  + " Amount: " + st[i].moneyAskedFor);
                }
                Toast.makeText(getActivity(),text,
                        Toast.LENGTH_LONG).show();

            }


        }
    }




}
