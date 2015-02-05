package com.example.please.devitti;


import android.app.Activity;
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
public class NeedyMainGUIFragmentA extends Fragment {




//    Cause causes = null;


    LendingDetailForCause[] lendingDetailsForCause;

    MyListAdapter myListAdapter;

    CauseCatagory [] causeCatagoryDetails;

    _DATABASEManager dM =  new _DATABASEManager();

    int [] imagesForDonationType = {R.drawable.donationimage,R.drawable.donationimage, R.drawable.lendingimage ,R.drawable.lendingimage,
            R.drawable.donationimage,R.drawable.donationimage, R.drawable.lendingimage ,R.drawable.lendingimage ,
            R.drawable.donationimage,R.drawable.donationimage, R.drawable.lendingimage ,R.drawable.lendingimage ,
            R.drawable.donationimage,R.drawable.donationimage, R.drawable.lendingimage ,R.drawable.lendingimage };


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

    String [] dataFromSignIn;



    Cause [] causesByThisUser;

    String needyId= null;



    public NeedyMainGUIFragmentA() {
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


        dataFromSignIn= getArguments().getStringArray("existingCauses");

        return inflater.inflate(R.layout.needy_existing_causes, container, false);



//        return inflater.inflate(R.layout.fragment, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        mainList = (ListView)getActivity().findViewById(R.id.mainList);

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
        needyId = dataFromSignIn[1];
        Log.i("Whaaaaaatttt:::  ", ">" + needyId + "<");

        new getCatagoryDataInBagrd().execute();


    }



    public class getCatagoryDataInBagrd extends AsyncTask<String, Integer, CauseCatagory[]>
    {

        @Override
        protected CauseCatagory[] doInBackground(String... params) {

            return dM.catagoryDetails();
        }

        @Override
        protected void onPostExecute(CauseCatagory[] s) {
            super.onPostExecute(s);

            causeCatagoryDetails =s;

            //the catagory data must be retrieved prior to displaying the list as here
            new needydoInBagrd().execute(needyId);


        }
    }


    public class needydoInBagrd extends AsyncTask<String, Integer, Cause[]>
    {

        @Override
        protected Cause[] doInBackground(String... params) {
            return dM.getCausesByNeedyId(needyId);
        }

        @Override
        protected void onPostExecute(Cause[] s) {
            super.onPostExecute(s);

            causesByThisUser =s;
            new getAllCausesAndDetailsInBagrd().execute();



        }
    }


/////////////////////////
    public class getAllCausesAndDetailsInBagrd extends AsyncTask<String, Integer, Cause[]>
    {

        @Override
        protected Cause [] doInBackground(String... params) {

            return dM.getAllCausesByUserAndDetailsForAllCauses(needyId,causesByThisUser);
        }

        @Override
        protected void onPostExecute(Cause[] s) {
            super.onPostExecute(s);

            causesByThisUser =s;

            Log.i("AYEEEEEEE:   " , (new Integer(causesByThisUser.length)).toString());

            int length = causesByThisUser.length;

            catagory =  new String[length];
            percentageCompleted =  new String[length];
            status =  new String[length];
            description =  new String[length];

            imagesForDonationType  = new int[length];

//            type = new String[length];


            for(int i = 0 ; i<length;i++ )
            {
//                Integer it = Integer.getInteger(causesByThisUser[i].typeId);\

                int ii = ( Integer.parseInt(causesByThisUser[i].catagory) ) -1;
                catagory[i] = causeCatagoryDetails[ii].catagoryName;

//                type[i]= causesByThisUser[i].typeId;

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

                    System.out.println("This is percentage: " +(given/ask)*100  );
                    System.out.println();
//                    int moneyAsked  =Integer.parseInt(causesByThisUser[i].moneyAskedFor.toString());


                    percentageCompleted[i] = Integer.toString((int) result) +"%";



                }
                else {percentageCompleted[i] = "0%";}


//                percentageCompleted[i] = "100%";
                status[i] = causesByThisUser[i].status;
                description[i] = causesByThisUser[i].description;

//                type[i] = causesByThisUser[i].type;

            }


            for (int i = 0; i<length; i++) {
//                Log.i(causesByThisUser[i].type,">>>>>>>>>>>>>>" );

                if (causesByThisUser[i].type.contains("donation"))
                {
                    Log.i("DONAAAAAA", causesByThisUser[i].type);
                    imagesForDonationType[i] = R.drawable.donationimage;

                }
                else if(causesByThisUser[i].type.contains("lending"))
                {
                    imagesForDonationType[i]= R.drawable.lendingimage;
                    Log.i("LENDDDDDDAaa", causesByThisUser[i].type);

                }

            }

            myListAdapter = new MyListAdapter(getActivity(), R.layout.single_row_list_needy_existing_causes,catagory,percentageCompleted, status,description,imagesForDonationType);
            mainList.setAdapter(myListAdapter);

            mainList.setOnItemClickListener(new listListener());

            //the catagory data must be retrieved prior to displaying the list as here
//            new needydoInBagrd().execute(needyId);




        }
    }

////////////////////////




    public void changeData(String aa) {

        checking.setText(aa);
    }

    public class MyListAdapter extends ArrayAdapter<String> {

        Context myContext;

        String[] itemNo;

        String[] status;
        String[] catagory;
        String[] percentageCompleted;
        String[] description;
        int [] typeImages;

        public MyListAdapter(Context context, int textViewResourceId, String[] tpe,String [] percentCmpltd, String [] sts, String [] descrip , int [] typeImg ) {
            super(context, textViewResourceId, sts);
            myContext = context;

            this.status = sts;
            this.catagory = tpe;
            this.percentageCompleted = percentCmpltd;
            this.description = descrip;
            this.typeImages  = typeImg;

        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //return super.getView(position, convertView, parent);



            LayoutInflater inflater =
                    (LayoutInflater)myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


            View myView = inflater.inflate(R.layout.single_row_list_needy_existing_causes, parent, false);


            TextView myStatus = (TextView) myView.findViewById(R.id.NECLISTStatusOfCause);
            myStatus.setText(status[position]);

            //Customize your icon here
//            TextView myStatus = (TextView) myView.findViewById(R.id.NECLISTStatusOfCause);
            TextView myCatagory = (TextView) myView.findViewById(R.id.NECLISTCatagoryOfCause);
            TextView myPercentage = (TextView) myView.findViewById(R.id.NECLISTPercentageCompletedValue);
            TextView myDescription = (TextView) myView.findViewById(R.id.NECLISTDescription);

            ImageView myDonType = (ImageView) myView.findViewById(R.id.typeImage);
//
//            myStatus.setText(statuss[position]);

            //here is the addition for the list item no
            TextView myItemNo = (TextView ) myView.findViewById(R.id.dummyNoForListNoStorage);
            myItemNo.setText((String.valueOf( position)));

            myCatagory.setText(catagory[position]);
            myPercentage.setText(percentageCompleted[position]);
            myDescription.setText(description[position]);
            myDonType.setImageResource(typeImages[position]);

//            myDonType.setImageResource(typeImages[position]);

            return myView;
        }

    }



    //test for LDFCIds
    public class getLendingDetailForACauseInBagrd extends AsyncTask<Cause, Integer, LendingDetailForCause[]>
    {

        @Override
        protected LendingDetailForCause[] doInBackground(Cause... params) {

//            Cause caus = new Cause(causeId,needyId,moneyAskedFor,descrip,status,type,dateOfRequest,dateOfCompletion,dateOfMaturity,lati,longi,null,catagory);
            return dM.getLendingDetailsForACause(new Cause("2","","","","","","","","","","",null,"","",""));


        }

        @Override
        protected void onPostExecute(LendingDetailForCause [] st) {
            super.onPostExecute(st);

            lendingDetailsForCause = st;

//            for(int i  = 0 ; i <st.length ; i ++)
//            {
//                System.out.println("LDFCIds for causeId: "+st[i].causeId+" are "+st[i].LDFCId);
//            }
//            Toast.makeText(getActivity().getApplicationContext(), "",
//                    Toast.LENGTH_SHORT).show();


        }
    }


    public class   listListener implements AdapterView.OnItemClickListener {


        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            TextView itemNo = (TextView) view.findViewById(R.id.dummyNoForListNoStorage);

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


    public class getAllCausesByUserAndDetailsForAllCausesInBagrd extends AsyncTask<Integer, Integer, Cause[]>
    {


        protected Cause[] doInBackground(Integer... params) {

//            Cause caus = new Cause(causeId,needyId,moneyAskedFor,descrip,status,type,dateOfRequest,dateOfCompletion,dateOfMaturity,lati,longi,null,catagory);
//            return dM.getLendingDetailsForACause(new Cause("2","","","","","","","","","","",null,""));
            return  dM.getAllCausesByUserAndDetailsForAllCauses (needyId, causesByThisUser);

            
        }

        @Override
        protected void onPostExecute(Cause[] st) {
            super.onPostExecute(st);

        }
    }





}
