package com.example.please.devitti;


import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


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

    String [] dataFromSignIn = null;
    String  [] ctIds= null ;
    String  [] ctNms = null;

    _DATABASEManager dM;

    Cause [] profileCauses;

    ProgressDialog dialog;
    public HelperMainGUIFragmentBProfile() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        dataFromSignIn = getArguments().getStringArray("searchAndMore");
        ctIds = getArguments().getStringArray("ctIDs");
        ctNms = getArguments().getStringArray("ctNms");

//        dialog = new ProgressDialog(getActivity());
//        dialog.setMessage("wait man!!!!!!!");
//        dialog.setIndeterminate(false);
//        dialog.show();

//
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.helper_own_profile, container, false);

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        list = (ListView)getActivity().findViewById(R.id.HOFList);
//        new getCausesForSearch().execute();
//        dM.getCausesByHelperId(dataFromSignIn[1]);


        MyListAdapter myListAdapter =
                new MyListAdapter(getActivity(), R.layout.single_row_list_temp_2,type,percentageCompleted, status,description);
        list.setAdapter(myListAdapter);

//        new getCausesForProfile().execute();


    }

//    public class getCausesForProfile extends AsyncTask<Integer, Integer, Cause[]>
//    {
//
//        protected Cause[] doInBackground(Integer... params) {
//            //            Cause caus = new Cause(causeId,needyId,moneyAskedFor,descrip,status,type,dateOfRequest,dateOfCompletion,dateOfMaturity,lati,longi,null,catagory);
////            return dM.getLendingDetailsForACause(new Cause("2","","","","","","","","","","",null,""));
//        System.out.println("okaaaaaaaaaaaaaaaaa I am here for helper Id: " + dataFromSignIn[1]);
//
////        Cause [] causesForProfile  =
//
//
////            Cause [] d =/* new Cause[1];
////            d[0]  =new Cause("","","","","","","","","","","",null,"","","");
////            return  (d);*/
//
//            return dM.getCausesByNeedyId("2");
//    }
//
//    @Override
//    protected void onPostExecute(Cause[] st) {
//        super.onPostExecute(st);
//
//        System.out.println("causes for helperId got the result");
////        System.out.println("new causes for helperID are "  + st.length+ "  long");
//
//        if (st!=null)
//        {
//            Cause [] cuSr  = st;
//
//            System.out.println("<<<<<<<<>>>>>>>>>> Its not null, the length is : "+ st.length);
//            String text = "";
//            for (int  i =  0 ; i <st.length ; i ++)
//            {
//                text += "\n " + " id ========= " +st[i].causeId  + " Amount: " + st[i].moneyAskedFor ;
//                System.out.println("id ========  " +st[i].causeId  + " Amount: " + st[i].moneyAskedFor);
//            }
////            Toast.makeText(getActivity(), text,
////                    Toast.LENGTH_LONG).show();
//
//        }
//        else
//        {
//            System.out.println("khota");
//        }
//
//
//
////        list = (ListView)getActivity().findViewById(R.id.HOFList);
////
////        MyListAdapter myListAdapter =
////                new MyListAdapter(getActivity(), R.layout.single_row_list_temp_2,type,percentageCompleted, status,description);
////        list.setAdapter(myListAdapter);
//
//
//
//    }
//}
//


    public class getCausesForProfile extends AsyncTask<String, Integer, Cause[]>
    {

        @Override
        protected Cause[] doInBackground(String... params) {
            return dM.getCausesByNeedyId("2");
        }

        @Override
        protected void onPostExecute(Cause[] s) {
            super.onPostExecute(s);

//            profileCauses =s;
//            new getAllCausesAndDetailsInBagrd().execute();

            System.out.println("askjfhasdjklfsfasdklhasdjklfhasdjklfhsdjklfhasdjkfhasdjkf" +
                    "asdlfkjasklfhasdfjklasdhfasdjklfhasjklfasdhf" +
                    "asdfklashfjk");
//            list = (ListView)getActivity().findViewById(R.id.HOFList);
//


        }
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


            View myView = inflater.inflate(R.layout.single_row_list_temp_3, parent, false);

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
