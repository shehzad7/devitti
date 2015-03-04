package com.example.please.devitti;


import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
public class HelperProfile2 extends Fragment {


//    Cause causes = null;


    LendingDetailForCause[] lendingDetailsForCause;

    MyListAdapter myListAdapter;

    CauseCatagory[] causeCatagoryDetails;

    _DATABASEManager dM = new _DATABASEManager();

    TextView amountLended;
    TextView amountDonated;

    int[] imagesForDonationType = {R.drawable.donationimage, R.drawable.donationimage, R.drawable.lendingimage, R.drawable.lendingimage,
            R.drawable.donationimage, R.drawable.donationimage, R.drawable.lendingimage, R.drawable.lendingimage,
            R.drawable.donationimage, R.drawable.donationimage, R.drawable.lendingimage, R.drawable.lendingimage,
            R.drawable.donationimage, R.drawable.donationimage, R.drawable.lendingimage, R.drawable.lendingimage};


    String[] catagory = {"catDonation", "catLoan", "catLoan", "catDonation", "catDonation",
            "catDonation", "catLoan", "catDonation", "catLoan", "catLoan",
            "catLoan", "catDonation", "catLoan", "catLoan", "catLoan",
            "catDonation", "catLoan", "catLoan", "catLoan", "catLoan"};

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


    ListView mainList;
    ArrayAdapter ar;


    TextView checking;

    String[] dataFromSignIn;


    Cause[] causesByThisUser;

    String needyId = null;
    String[] ctIds;
    String[] ctNms;
    String helperId;


    public HelperProfile2() {
        // Required empty public constructor
    }


    public void onCreate(Bundle savedInstanceState) {
//        mainList = (ListView) findViewById(R.id.mainList);
        super.onCreate(savedInstanceState);


//        Mode.setStrictThreadPolicy(new StrictMode.ThreadPolicy.Builder()
//                .detectDiskReads().detectDiskWrites().detectNetwork() // StrictMode is most commonly used to catch accidental disk or network access on the application's main thread
//                .penaltyLog().build());


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        mainList = (ListView)container.findViewById(R.id.mainList);


        //By Custom Adapter
//        myOwnCustomAdapter adapter = new myOwnCustomAdapter(getActivity().getApplicationContext(), status, type, percentageCompleted, description);
//        mainList.setAdapter(adapter);

        dataFromSignIn = getArguments().getStringArray("searchAndMore");
        ctIds = getArguments().getStringArray("ctIDs");
        ctNms = getArguments().getStringArray("ctNms");

        causeCatagoryDetails = new CauseCatagory[ctIds.length];
        for (int i = 0; i < ctIds.length; i++) {
            System.out.println(ctNms[i] + "$$$$$$$$$$$$$$$$$$$$$");
//            causeCatagoryDetails[i].catagoryId = ctIds[i];
//            causeCatagoryDetails[i].catagoryName = ctNms[i];
//
        }


        return inflater.inflate(R.layout.helper_own_profile, container, false);


//        return inflater.inflate(R.layout.fragment, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        mainList = (ListView) getActivity().findViewById(R.id.HOFList);
        TextView name = (TextView) getActivity().findViewById(R.id.HOPName);


        name.setText(dataFromSignIn[2]);

//        checking.setText("ID: " +dataFromSignIn[1]+"\n"+
//                "NAME: "+dataFromSignIn[2]+"\n"+
//                "USERNAME: "+dataFromSignIn[3]+"\n"+
//                "PASSWORD: "+dataFromSignIn[4]+"\n"+
//                "TYPE: "+dataFromSignIn[5]+"\n"+
//                "EMAIL: "+dataFromSignIn[6]+"\n"+
//                "ADDRESS: "+dataFromSignIn[7]+"\n"+
//                "CITY: "+dataFromSignIn[8]+"\n"+
//                "COUNTRY: "+dataFromSignIn[9]+"\n"+
//                "BANKNAME: "+dataFromSignIn[10]+"\n"+
//                "BRANCHCODE: "+dataFromSignIn[11]+"\n"+
//                "ACCOUNTNO: "+dataFromSignIn[12]+"\n"

        //here i have to get the data from the database i.e. all the four arrays
        //type,percentageCompleted,status and description (for this specific needy account)
        //i.e. needyId
        helperId = dataFromSignIn[1];
        Log.i("Helper ID: is  ", ">" + helperId + "<");

//        new getCatagoryDataInBagrd().execute();
        new InBagrd().execute();


    }


    public class getAllHelperCausesDetailInBg extends AsyncTask<String, Integer, Cause[]> {

        @Override
        protected Cause[] doInBackground(String... params) {

            return dM.getAllCausesDetailsForAllCauses(causesByThisUser);
//            return dM.getAllCausesDetailsForHelperCauses(causesByThisUser, helperId);


        }

        @Override
        protected void onPostExecute(Cause[] s) {
            super.onPostExecute(s);


            int length = s.length;

            System.out.println("shehzad");
            causesByThisUser = s;
            System.out.println("shehzad " + s.length);
            System.out.println("shehzad");

            catagory = new String[length];
            percentageCompleted = new String[length];
            status = new String[length];
            description = new String[length];
            imagesForDonationType = new int[length];

            type = new String[length];

            int totalAmountLended  = 0;
            int totalAmountDonated = 0;

            for (int i = 0; i < s.length; i++) {
//                System.out.println("{{{{{||||||||cause id of the cause by the helper is: " + s[i].causeId);

                //to get catagory names for the catagory IDs from the causes retrieved
                for (int cat = 0; cat < ctNms.length; cat++) {
                    if (s[i].catagory.contains(ctIds[cat])) {
                        System.out.println("Trueeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
                        catagory[i] = ctNms[cat];
                    }
                }

                status[i] = s[i].status;


//                    Log.i(causesByThisUser[p].type, ">>>>>>>>>>>>>>");

                if (causesByThisUser[i].type.contains("donation")) {
//                        System.out.println("theeeeeeeeeeee donation");
                    imagesForDonationType[i] = R.drawable.dummydonation;

                } else if (causesByThisUser[i].type.contains("loan")) {
                    imagesForDonationType[i] = R.drawable.dummylending;
//                        System.out.println("theeeeeeeeeeee lending");
                }




//                int ii = ( Integer.parseInt(causesByThisUser[i].catagory) ) -1;

//                System.out.println(i+" Catagory ID: " + ii);
//                System.out.println(i+" Catagory name: " + causeCatagoryDetails[ii].catagoryName);
//                catagory[i] = causeCatagoryDetails[ii].catagoryName;

                type[i] = "";

                percentageCompleted[i] = "per%";

                description[i] = s[i].description;

                if(causesByThisUser[i].lendingDetails!=null)
                {
                    System.out.println("Lending details for cause: " + causesByThisUser[i].causeId+ " ");
                    System.out.println();

                    int countMoney = 0;
                    for (int j   = 0 ; j< causesByThisUser[i].lendingDetails.length ; j++)
                    {
                        System.out.println(causesByThisUser[i].lendingDetails[j].amountLended);
                        countMoney += causesByThisUser[i].lendingDetails[j].amountLended;

                    }

                    int moneyAsked  =Integer.parseInt(causesByThisUser[i].moneyAskedFor.toString());

                    System.out.println("This is total given: " + countMoney);
                    System.out.println("This is total demanded: "  + moneyAsked);

//                    System.out.println("This is total demanded: "  + causesByThisUser[i].moneyAskedFor.toString());

                    float ask = moneyAsked;
                    float given = countMoney;
                    float result = (given/ask)*100  ;

                    if (causesByThisUser[i].type.contains("loan"))
                    {
                        totalAmountLended+=countMoney;
                    }
                    else
                    {
                        totalAmountDonated+=countMoney;
                    }

                    System.out.println("!!!!!!!!!!!!This is percentage: " +(given/ask)*100  );
                    System.out.println();
//                    int moneyAsked  =Integer.parseInt(causesByThisUser[i].moneyAskedFor.toString());


                    percentageCompleted[i] = Integer.toString((int) result) +"%";

//
//
                }
                else {percentageCompleted[i] = "0%";
                System.out.println("the cause seeem to have no donation at all 0 % so");}




            }

//            amountLended.setText(totalAmountLended);
//            amountDonated.setText(totalAmountDonated);

//            list = (ListView)getActivity().findViewById(R.id.HOFList);
//

            amountLended = (TextView) getActivity().findViewById(R.id.HOPAmountLendedValue);
            amountDonated = (TextView) getActivity().findViewById(R.id.HOPAmountDonatedValue);
            System.out.println("this user has doanted the total amount of :" + totalAmountDonated);
            System.out.println("this user has lended the total amount of :" + totalAmountLended);

            amountLended.setText((String.valueOf(totalAmountLended)));
            amountDonated.setText((String.valueOf(totalAmountDonated)));


            MyListAdapter myListAdapter =
                    new MyListAdapter(getActivity(), R.layout.single_row_list_temp_3, catagory, percentageCompleted, status, description,imagesForDonationType);
            mainList.setAdapter(myListAdapter);

            mainList.setOnItemClickListener(new listListener());

        }
    }

    public class   listListener implements AdapterView.OnItemClickListener {


        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            TextView itemNo = (TextView) view.findViewById(R.id.dummyNoForListNoStorage2);

            int item = Integer.parseInt(itemNo.getText().toString());

            Toast.makeText(getActivity(),

                    causesByThisUser[item].causeId

                    , Toast.LENGTH_SHORT).show();


            //////////////////ORIGNAL ONE////////////////////////
//            new getLendingDetailForACauseInBagrd().execute();//
            /////////////////////////////////////////////////////

//            new getAllCausesByUserAndDetailsForAllCausesInBagrd().execute();




            Bundle bndl=new Bundle();






            Cause ii  = causesByThisUser[item];
            Intent gettotheneedyGUI =new Intent( getActivity(), NeedyClickOnOwnCause.class );
            gettotheneedyGUI.putExtra("ldArray" ,ii );
//            gettotheneedyGUI.putExtra("causeId", causesByThisUser[item].causeId);

            startActivity( gettotheneedyGUI );


//            causesByThisUser[]

        }
    }


    public class InBagrd extends AsyncTask<String, Integer, Cause[]> {

        @Override
        protected Cause[] doInBackground(String... params) {
            return dM.getCausesByHelperId(helperId);


        }

        @Override
        protected void onPostExecute(Cause[] s) {
            super.onPostExecute(s);
            causesByThisUser = s;

            new getAllHelperCausesDetailInBg().execute();



//            MyListAdapter myListAdapter =
//                    new MyListAdapter(getActivity(),R.layout.single_row_list_needy_existing_causes,type,percentageCompleted,status,description,imagesForDonationType) ;
//            mainList.setAdapter(myListAdapter);
//            new getAllCausesAndDetailsInBagrd().execute();


//

//
//            Log.i("AYEEEEEEE:   " , (new Integer(causesByThisUser.length)).toString());
//
//            int length = causesByThisUser.length;
////
//            catagory =  new String[length];
//            percentageCompleted =  new String[length];
//            status =  new String[length];
//            description =  new String[length];
//
//            imagesForDonationType  = new int[length];
//
////            type = new String[length];
//
//
//            for(int i = 0 ; i<length;i++ )
//            {
////                Integer it = Integer.getInteger(causesByThisUser[i].typeId);\
//
//                int ii = ( Integer.parseInt(causesByThisUser[i].catagory) ) -1;
//                catagory[i] = causeCatagoryDetails[ii].catagoryName;
//
////                type[i]= causesByThisUser[i].typeId;
//
//                if(causesByThisUser[i].lendingDetails!=null)
//                {
//                    System.out.println("Lending details for cause: " + causesByThisUser[i].causeId+ " ");
//                    System.out.println();
//
//                    int countMoney = 0;
//                    for (int j   = 0 ; j< causesByThisUser[i].lendingDetails.length ; j++)
//                    {
//                        System.out.println(causesByThisUser[i].lendingDetails[j].amountLended);
//                        countMoney += causesByThisUser[i].lendingDetails[j].amountLended;
//
//                    }
//
//                    int moneyAsked  =Integer.parseInt(causesByThisUser[i].moneyAskedFor.toString());
//
//                    System.out.println("This is total given: " + countMoney);
//                    System.out.println("This is total demanded: "  + moneyAsked);
//
////                    System.out.println("This is total demanded: "  + causesByThisUser[i].moneyAskedFor.toString());
//
//                    float ask = moneyAsked;
//                    float given = countMoney;
//                    float result = (given/ask)*100  ;
//
//                    System.out.println("!!!!!!!!!!!!This is percentage: " +(given/ask)*100  );
//                    System.out.println();
////                    int moneyAsked  =Integer.parseInt(causesByThisUser[i].moneyAskedFor.toString());
//
//
//                    percentageCompleted[i] = Integer.toString((int) result) +"%";
//
//
//
//                }
//                else {percentageCompleted[i] = "0%";}
//
//
////                percentageCompleted[i] = "100%";
//                status[i] = causesByThisUser[i].status;
//                description[i] = causesByThisUser[i].description;
//
////                type[i] = causesByThisUser[i].type;
//
//            }
//
//
//            for (int i = 0; i<length; i++) {
////                Log.i(causesByThisUser[i].type,">>>>>>>>>>>>>>" );
//
//                if (causesByThisUser[i].type.contains("donation"))
//                {
//                    Log.i("DONAAAAAA", causesByThisUser[i].type);
//                    imagesForDonationType[i] = R.drawable.donationimage;
//
//                }
//                else if(causesByThisUser[i].type.contains("loan"))
//                {
//                    imagesForDonationType[i]= R.drawable.lendingimage;
//                    Log.i("LENDDDDDDAaa", causesByThisUser[i].type);
//
//                }
//
//            }
//
//            myListAdapter = new MyListAdapter(getActivity(), R.layout.single_row_list_needy_existing_causes,catagory,percentageCompleted, status,description,imagesForDonationType);
//            mainList.setAdapter(myListAdapter);

//            mainList.setOnItemClickListener(new listListener());

            //the catagory data must be retrieved prior to displaying the list as here
//            new needydoInBagrd().execute(needyId);


        }
    }


    /////////////////////////
    public class getAllCausesAndDetailsInBagrd extends AsyncTask<String, Integer, Cause[]> {

        @Override
        protected Cause[] doInBackground(String... params) {

            return dM.getAllCausesByUserAndDetailsForAllCauses("2", causesByThisUser);
        }

        @Override
        protected void onPostExecute(Cause[] s) {
            super.onPostExecute(s);

            causesByThisUser = s;

            Log.i("AYEEEEEEE:   ", (new Integer(causesByThisUser.length)).toString());

            int length = causesByThisUser.length;

            catagory = new String[length];
            percentageCompleted = new String[length];
            status = new String[length];
            description = new String[length];

            imagesForDonationType = new int[length];

//            type = new String[length];


            for (int i = 0; i < length; i++) {
//                Integer it = Integer.getInteger(causesByThisUser[i].typeId);\

                int ii = (Integer.parseInt(causesByThisUser[i].catagory)) - 1;
                catagory[i] = causeCatagoryDetails[ii].catagoryName;

//                type[i]= causesByThisUser[i].typeId;

                if (causesByThisUser[i].lendingDetails != null) {
                    System.out.println("Lending details for cause: " + causesByThisUser[i].causeId + " ");
                    System.out.println();

                    int countMoney = 0;
                    for (int j = 0; j < causesByThisUser[i].lendingDetails.length; j++) {
                        System.out.println(causesByThisUser[i].lendingDetails[j].amountLended);
                        countMoney += causesByThisUser[i].lendingDetails[j].amountLended;

                    }

                    int moneyAsked = Integer.parseInt(causesByThisUser[i].moneyAskedFor.toString());

                    System.out.println("This is total given: " + countMoney);
                    System.out.println("This is total demanded: " + moneyAsked);

//                    System.out.println("This is total demanded: "  + causesByThisUser[i].moneyAskedFor.toString());

                    float ask = moneyAsked;
                    float given = countMoney;
                    float result = (given / ask) * 100;

                    System.out.println("!!!!!!!!!!!!This is percentage: " + (given / ask) * 100);
                    System.out.println();
//                    int moneyAsked  =Integer.parseInt(causesByThisUser[i].moneyAskedFor.toString());


                    percentageCompleted[i] = Integer.toString((int) result) + "%";


                } else {
                    percentageCompleted[i] = "0%";
                }


//                percentageCompleted[i] = "100%";
                status[i] = causesByThisUser[i].status;
                description[i] = causesByThisUser[i].description;

//                type[i] = causesByThisUser[i].type;

            }


            for (int i = 0; i < length; i++) {
//                Log.i(causesByThisUser[i].type,">>>>>>>>>>>>>>" );

                if (causesByThisUser[i].type.contains("donation")) {
                    Log.i("DONAAAAAA", causesByThisUser[i].type);
                    imagesForDonationType[i] = R.drawable.donationimage;

                } else if (causesByThisUser[i].type.contains("loan")) {
                    imagesForDonationType[i] = R.drawable.lendingimage;
                    Log.i("LENDDDDDDAaa", causesByThisUser[i].type);

                }

            }

//            myListAdapter = new MyListAdapter(getActivity(), R.layout.single_row_list_needy_existing_causes,catagory,percentageCompleted, status,description,imagesForDonationType);
//            mainList.setAdapter(myListAdapter);
//
//            mainList.setOnItemClickListener(new listListener());

            //the catagory data must be retrieved prior to displaying the list as here
//            new needydoInBagrd().execute(needyId);


//from helper profile activity


//            new getCausesForProfile().execute();

        }
    }

    public class getCausesForProfile extends AsyncTask<String, Integer, Cause[]> {


        @Override
        protected Cause[] doInBackground(String... params) {
            return dM.getCausesByHelperId("8");
        }

        @Override
        protected void onPostExecute(Cause[] s) {
            super.onPostExecute(s);

//            profileCauses =s;
//            new getAllCausesAndDetailsInBagrd().execute();

            System.out.println("askjfhasdjklfsfasdklhasdjklfhasdjklfhsdjklfhasdjkfhasdjkf" +
                    "asdlfkjasklfhasdfjklasdhfasdjklfhasjklfasdhf" +
                    "asdfklashfjk");
            System.out.println("Length of helper profile causes: " + s.length);

            for (int i = 0; i < s.length; i++) {
                System.out.println("Causes ID be: " + s[i].causeId);
            }

//            list = (ListView)getActivity().findViewById(R.id.HOFList);
//
//            MyListAdapter myListAdapter =
//                    new MyListAdapter(getActivity(), R.layout.single_row_list_temp_2,type,percentageCompleted, status,description);
//            list.setAdapter(myListAdapter);

        }
    }

    public class MyListAdapter extends ArrayAdapter<String> {

        Context myContext;
        String[] status;
        String[] type;
        String[] percentageCompleted;
        String[] description;
        int [] imagesForDonationType;

        public MyListAdapter(Context context, int textViewResourceId, String[] tpe, String[] percentCmpltd, String[] sts, String[] descrip, int [] imgs) {
            super(context, textViewResourceId, sts);
            myContext = context;
            this.status = sts;
            this.type = tpe;
            this.percentageCompleted = percentCmpltd;
            this.description = descrip;
            this.imagesForDonationType = imgs;

        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //return super.getView(position, convertView, parent);


            LayoutInflater inflater =
                    (LayoutInflater) myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


            View myView = inflater.inflate(R.layout.single_row_list_temp_3, parent, false);

            TextView myStatus = (TextView) myView.findViewById(R.id.NECLISTStatusOfCause);
            myStatus.setText(status[position]);

            //Customize your icon here
//            TextView myStatus = (TextView) myView.findViewById(R.id.NECLISTStatusOfCause);
            TextView myCatagory = (TextView) myView.findViewById(R.id.NECLISTCatagoryOfCause);
            TextView myPercentage = (TextView) myView.findViewById(R.id.NECLISTPercentageCompletedValue);
            TextView myDescription = (TextView) myView.findViewById(R.id.NECLISTDescription);
            ImageView myImgtype =  (ImageView) myView.findViewById(R.id.typeImage);

            TextView myItemNo = (TextView ) myView.findViewById(R.id.dummyNoForListNoStorage2);

            myItemNo.setText((String.valueOf( position)));

//            typeImage

//
//            myStatus.setText(statuss[position]);
            myImgtype.setImageResource(imagesForDonationType[position]);
            myCatagory.setText(type[position]);
            myPercentage.setText(percentageCompleted[position]);
            myDescription.setText(description[position]);
//            myImgtype.setImageResource(img[]);


            return myView;
        }


    }

}


//test for LDFCIds




