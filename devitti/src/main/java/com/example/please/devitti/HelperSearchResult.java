package com.example.please.devitti;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

/**
 * Created by please on 12/24/2014.
 */
public class HelperSearchResult extends Activity implements AdapterView.OnItemClickListener {

    SparseArray<Parcelable> causesGot  =null;
    ListView list;

    int  []imagesForDonationType   = null;

    _DATABASEManager dM  =new _DATABASEManager();

    String [] dataFromSearchFields;

    String [] type = {"Donation","Loan","Loan","Donation","Donation",
            "Donation","Loan","Donation","Loan","Loan",
            "Loan","Donation","Loan","Loan","Loan",
            "Donation","Loan","Loan","Loan","Loan"};

    String [] percentageCompleted =  {"90%","100%","100%","40%","100%",
            "43%","23%","21%","100%","100%",
            "56%","76%","87%","100%","100%",
            "6%","7%","8%","100%","100%",};

    String [] status ={"Not Completed","Completed","Completed","Not Completed","Completed",
            "Not Completed","Closed by needy","Not Completed","Completed","Completed",
            "Not Completed","Not Completed","Not Completed","Matured","Completed",
            "Not Completed","Not Completed","Not Completed","Completed","Completed"};

    String [] description ={"Lorem ipsum dolor sit amet, consectetur adipiscing elit. elit. Etiam vitae accumsan leo, "
            ,"Absolutely do need this money","You can't say no to education, can you","Hmmmm All i needy is a little money to expand","Need to buy new books man",
            "Etiam finibus bibendum eros, eget pulvinar nunc auctor blandit. Fusce consectetur tortor lectus, eget laoreet augue porta non. Nulla risus turpis, vestibulum vitae pretium ut, pellentesque eu puru","Some dummy description.. some text here ","Some dummy description.. some text here ","Some dummy description.. some text here ","Some dummy description.. some text here ",
            "Some dummy description.","Some dummy description.","Some dummy description.","Some dummy description.","Some dummy description.",
            "Some dummy description.","Some dummy description.","Some dummy description.","Some dummy description.","Some dummy description....end"};

    String [] catagory  ={"Education","Education","Education","Education","Education",
            "Donation","Loan","Donation","Loan","Loan",
            "Loan","Donation","Loan","Loan","Loan",
            "Donation","Loan","Loan","Loan","Loan"};

    CauseCatagory [] causeCatagoryDetails;

     ProgressDialog dialog ;

    Cause [] causesSearhed  = null;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.helper_search_result);

        dataFromSearchFields = (String [] ) getIntent().getSerializableExtra("dataFromSearchFields");

//        new getCausesForSearch.execute(3);

        dialog = new ProgressDialog(HelperSearchResult.this);
        dialog.setMessage("wait man!!!!!!!");
        dialog.setIndeterminate(false);
        dialog.show();


        new getCatagoryDataInBagrd().execute();
//        new getCausesForSearch().execute();

//        Bundle b = getIntent().getExtras();
//        if(b!=null)
//        {
//            Parcelable[] parcelableArray =
//                    Parcel.readParcelableArray(Cause.class.getClassLoader());
//            MyClass[] resultArray = null;
//            if (parcelableArray != null) {
//                resultArray = Arrays.copyOf(parcelableArray, parcelableArray.length, MyClass[].class);
//            }




//            causesGot =  b.getSparseParcelableArray("searchCauses");
//            causesGot =
//            ArrayList<Parliament> as = (ArrayList<Parliament>)b.getSerializable("tasklist");
//        }

//        causesGot = (Cause []) b.getSerializableExtra("searchCauses");



        /*
        list = (ListView) findViewById(R.id.HSRList);

        //By Custom Adapter
        myOwnCustomAdapter4 adapter = new myOwnCustomAdapter4(this,status, type, percentageCompleted, description);
        list.setAdapter(adapter);

        list.setOnItemClickListener(this);
        */
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

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

            new getCausesForSearch().execute();

            //the catagory data must be retrieved prior to displaying the list as here
//            new needydoInBagrd().execute(needyId);


        }
    }




    public class getCausesForSearch extends AsyncTask<Integer, Integer, Cause[]>
    {


        protected Cause[] doInBackground(Integer... params) {

//            Cause caus = new Cause(causeId,needyId,moneyAskedFor,descrip,status,type,dateOfRequest,dateOfCompletion,dateOfMaturity,lati,longi,null,catagory);
//            return dM.getLendingDetailsForACause(new Cause("2","","","","","","","","","","",null,""));
            System.out.println("<<<<<<<<>>>>>>>>>> I am here");


//            return  dM.getSearchResultForHelper (dataFromSearchFields);

            causesSearhed  =    dM.getSearchResultForHelper(dataFromSearchFields);

            return causesSearhed;
        }

        @Override
        protected void onPostExecute(Cause[] st) {
            super.onPostExecute(st);










///HERE YOU SHOULD REMOVE COMENT FOR LENDING DETAILS
            new getCausesPlusLendingDetailsForSearch().execute();

        }
    }

    public class getCausesPlusLendingDetailsForSearch extends AsyncTask<Integer, Integer, Cause[]>
    {


        protected Cause[] doInBackground(Integer... params) {

//            Cause caus = new Cause(causeId,needyId,moneyAskedFor,descrip,status,type,dateOfRequest,dateOfCompletion,dateOfMaturity,lati,longi,null,catagory);
//            return dM.getLendingDetailsForACause(new Cause("2","","","","","","","","","","",null,""));
            System.out.println("<<<<<<<<>>>>>>>>>> I am here");


//             dM.getAllCausesDetailsForAllCauses(causesSearhed);

//            return   dM.;

            return  dM.getAllCausesDetailsForAllCauses(causesSearhed);

        }

        @Override
        protected void onPostExecute(Cause[] st) {
            super.onPostExecute(st);

            /***********************************////////////////////////////////////////////////////////////////////////////////////////////////////////////

            dialog.dismiss();
            System.out.println("<<<<<<<<>>>>>>>>>> got the result");
            if (st!=null)
            {
                Cause [] cuSr  = st;
                // going to the next Search Result window
                //   Intent intnt = new Intent(HelperSearchResult.this, HelperSearchResult.class);
                //  intnt.putExtra("searchCauses" ,cuSr );

                //   startActivity(intnt);


                System.out.println("<<<<<<<<>>>>>>>>>> Its not null, the length is : "+ st.length);
                String text = "";
                for (int  i =  0 ; i <st.length ; i ++)
                {
                    int ii = ( Integer.parseInt(cuSr[i].catagory) ) -1;

//                    text += "\n " + "Cause ID =  " +st[i].causeId  + " Amount: " + st[i].moneyAskedFor + " catagory: "+ causeCatagoryDetails[ii].catagoryName;
                    System.out.println("Cause ID =  " +st[i].causeId  + " Amount: " + st[i].moneyAskedFor+ " catagory: "+ causeCatagoryDetails[ii].catagoryName
                    );
                    if (st[i].lendingDetails!=null)
                    {
                        System.out.println( " no of lending details : " + st[i].lendingDetails.length);
                    }
                }

//             Toast.makeText(HelperSearchResult.this, text,
//                        Toast.LENGTH_LONG).show();





                String []sts   = {"......","......"};
                String []tp   = {"......","......"};
                String []prc   = {"......","......"};
                String []des   = {"......","......"};
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                ///here
                int a = cuSr.length;
                int length = cuSr.length;
                System.out.println("jklahsfjkhfjkhasdjkfhsdjkh");

                catagory =  new String[length];
                percentageCompleted =  new String[length];
                status =  new String[length];
                description =  new String[length];

                imagesForDonationType  = new int[length];

//            type = new String[length];


                for(int i = 0 ; i<length;i++ )
                {
//                Integer it = Integer.getInteger(causesByThisUser[i].typeId);\

                    int ii = ( Integer.parseInt(cuSr[i].catagory) ) -1;
                    catagory[i] = causeCatagoryDetails[ii].catagoryName;


//                type[i]= causesByThisUser[i].typeId;

                    if(cuSr[i].lendingDetails!=null)
                    {
                        System.out.println("Lending details for cause: " + cuSr[i].causeId+ " ");
                        System.out.println();

                        int countMoney = 0;
                        for (int j   = 0 ; j< cuSr[i].lendingDetails.length ; j++)
                        {
                            System.out.println(cuSr[i].lendingDetails[j].amountLended);
                            countMoney += cuSr[i].lendingDetails[j].amountLended;

                        }

                        int moneyAsked  =Integer.parseInt(cuSr[i].moneyAskedFor.toString());

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

                        System.out.println("LLLLLLLLLLLLLLLLLLLLLLLLLLL "+percentageCompleted[i]);


                    }
                    else {percentageCompleted[i] = "0%";}


//                percentageCompleted[i] = "100%";
                    status[i] = cuSr[i].status;
                    description[i] = cuSr[i].description;

//                type[i] = causesByThisUser[i].type;

                }


                int count = 0;
                for (int i = 0; i<length; i++) {
//                Log.i(causesByThisUser[i].type,">>>>>>>>>>>>>>" );


                    if (cuSr[i].type.contains("donation"))
                    {
                        Log.i("DONAAAAAA", cuSr[i].type);
                        imagesForDonationType[i] = R.drawable.dummydonation;
                        count++;
                    }
                    else if(cuSr[i].type.contains("loan"))
                    {
                        imagesForDonationType[i]= R.drawable.dummylending;
                        Log.i("LENDDDDDDAaa", cuSr[i].type);

                        count++;
                    }

                }
                System.out.println("  " +count+" >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");



////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


                //By Custom Adapter
                list = (ListView) findViewById(R.id.HSRList);

                Log.e("OoOoOOOOOOOoooooooo" , "Status length: " +status.length  +
                        "type length: " +type.length  +
                        "percentage length: " +percentageCompleted.length  +
                        "desc length: " +description.length  +
                        "images length: " +imagesForDonationType.length   );




                for (int  i  =  0 ; i <percentageCompleted.length ; i ++) {

                    System.out.println("????????????????? "+percentageCompleted[i]);

                }
                myOwnCustomAdapter4 adapter = new myOwnCustomAdapter4(getApplicationContext(),status,catagory, percentageCompleted, description ,imagesForDonationType);
                list.setAdapter(adapter);

                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    }
                });






            }


/*********************************////////////////////////////////////////////////////////////////////////////////////////////////////////////



        }
    }



}
class myOwnCustomAdapter4 extends ArrayAdapter<String>
{
    Context cont;
    String [] statuss;
    String [] typee;
    String [] percentageCompletedd;
    String [] descriptionn;

    String[] catagory;
    int [] typeImages;





    myOwnCustomAdapter4(Context con,String[] status,String [] catagory ,String [] percentageCompleted,String [] description , int [] typeImages)
    {
        super(con,R.layout.single_row_list_needy_existing_causes,R.id.NECLISTStatusOfCause,status);
        this.cont=con;
        this.statuss= status;
        this.catagory= catagory;
        this.percentageCompletedd= percentageCompleted;
        this.descriptionn = description;
        this.typeImages  = typeImages;

    }

    public View getView(int position,View toBeConverted, ViewGroup parent){

        LayoutInflater myInflator = (LayoutInflater) cont.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View myView = null;
        myView = myInflator.inflate(R.layout.single_row_list_temp_2,parent,false);

        TextView myStatus = (TextView) myView.findViewById(R.id.NECLISTStatusOfCause2);
        TextView myCatagory= (TextView) myView.findViewById(R.id.NECLISTCatagoryOfCause2);
        TextView myPercentage= (TextView) myView.findViewById(R.id.NECLISTPercentageCompletedValue2);
        TextView myDescription= (TextView) myView.findViewById(R.id.NECLISTDescription2);
        ImageView myImage = (ImageView) myView.findViewById(R.id.typeImage2);


        TextView myItemNo = (TextView ) myView.findViewById(R.id.dummyNoForListNoStorage2);

        myItemNo.setText((String.valueOf( position)));


        myStatus.setText(statuss[position]);
        myCatagory.setText(catagory[position]);
        myPercentage.setText(percentageCompletedd[position]);
        myDescription.setText(descriptionn[position]);
        myImage.setImageResource(typeImages[position]);

        return myView;
    }











}




