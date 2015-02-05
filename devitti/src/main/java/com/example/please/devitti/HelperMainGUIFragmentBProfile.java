package com.example.please.devitti;


import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


/**
 * A simple {@link android.app.Fragment} subclass.
 */
public class HelperMainGUIFragmentBProfile extends Fragment {


    String[] type = {"Donation", "Loan", "Loan", "Donation", "Donation",
            "Donation", "Loan", "Donation", "Loan", "Loan",
            "Loan", "Donation", "Loan", "Loan", "Loan",
            "Donation", "Loan", "Loan", "Loan", "Loan"};

    String[] percentageCompleted = {"90%", "100%", "100%", "40%", "100%",
            "43%", "23%", "21%", "100%", "100%",
            "56%", "76%", "87%", "100%", "100%",
            "6%", "7%", "8%", "100%", "100%",};

    String[] status = {"Not Completed", "Completed", "Completed", "Not Completed", "Completed",
            "Not Completed", "Closed by needy", "Not Completed", "Completed", "Completed",
            "Not Completed", "Not Completed", "Not Completed", "Matured", "Completed",
            "Not Completed", "Not Completed", "Not Completed", "Completed", "Completed"};

    String[] description = {"Yeh man i need it 1", "Absolutely do need this money", "You can't say no to education, can you", "Hmmmm All i needy is a little money to expand", "Need to buy new books man",
            "Some dummy description.. some text here ", "Some dummy description.. some text here ", "Some dummy description.. some text here ", "Some dummy description.. some text here ", "Some dummy description.. some text here ",
            "Some dummy description.", "Some dummy description.", "Some dummy description.", "Some dummy description.", "Some dummy description.",
            "Some dummy description.", "Some dummy description.", "Some dummy description.", "Some dummy description.", "Some dummy description....end"};


    ListView list;
    ArrayAdapter ar;

    public HelperMainGUIFragmentBProfile() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.helper_own_profile, container, false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        list = (ListView)getActivity().findViewById(R.id.HOFList);



        MyListAdapter myListAdapter =
                new MyListAdapter(getActivity(), R.layout.single_row_list_temp_2,type,percentageCompleted, status,description);
        list.setAdapter(myListAdapter);


    }


    public class MyListAdapter extends ArrayAdapter<String> {

        Context myContext;
        String[] status;
        String[] type;
        String[] percentageCompleted;
        String[] description;

        public MyListAdapter(Context context, int textViewResourceId, String[] tpe,String [] percentCmpltd, String [] sts, String [] descrip) {
            super(context, textViewResourceId, sts);
            myContext = context;
            this.status = sts;
            this.type = tpe;
            this.percentageCompleted = percentCmpltd;
            this.description = descrip;

        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //return super.getView(position, convertView, parent);



            LayoutInflater inflater =
                    (LayoutInflater)myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


            View myView = inflater.inflate(R.layout.single_row_list_temp_2, parent, false);

            TextView myStatus = (TextView) myView.findViewById(R.id.NECLISTStatusOfCause);
            myStatus.setText(status[position]);

            //Customize your icon here
//            TextView myStatus = (TextView) myView.findViewById(R.id.NECLISTStatusOfCause);
            TextView myCatagory = (TextView) myView.findViewById(R.id.NECLISTCatagoryOfCause);
            TextView myPercentage = (TextView) myView.findViewById(R.id.NECLISTPercentageCompletedValue);
            TextView myDescription = (TextView) myView.findViewById(R.id.NECLISTDescription);
//
//            myStatus.setText(statuss[position]);
            myCatagory.setText(type[position]);
            myPercentage.setText(percentageCompleted[position]);
            myDescription.setText(description[position]);







            return myView;
        }

    }

}
