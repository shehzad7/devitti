package com.example.please.devitti;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

     ProgressDialog dialog ;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.helper_search_result);

        dataFromSearchFields = (String [] ) getIntent().getSerializableExtra("dataFromSearchFields");

//        new getCausesForSearch.execute(3);

        dialog = new ProgressDialog(HelperSearchResult.this);
        dialog.setMessage("wait man!!!!!!!");
        dialog.setIndeterminate(false);
        dialog.show();


        new getCausesForSearch().execute();

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

        list = (ListView) findViewById(R.id.HSRList);





        //By Custom Adapter
        myOwnCustomAdapter4 adapter = new myOwnCustomAdapter4(this,status, type, percentageCompleted, description);
        list.setAdapter(adapter);

        list.setOnItemClickListener(this);

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }




    public class getCausesForSearch extends AsyncTask<Integer, Integer, Cause[]>
    {


        protected Cause[] doInBackground(Integer... params) {

//            Cause caus = new Cause(causeId,needyId,moneyAskedFor,descrip,status,type,dateOfRequest,dateOfCompletion,dateOfMaturity,lati,longi,null,catagory);
//            return dM.getLendingDetailsForACause(new Cause("2","","","","","","","","","","",null,""));
            System.out.println("<<<<<<<<>>>>>>>>>> I am here");


//            return  dM.getSearchResultForHelper (dataFromSearchFields);

            return   dM.getSearchResultForHelper(dataFromSearchFields);

        }

        @Override
        protected void onPostExecute(Cause[] st) {
            super.onPostExecute(st);

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
                    text += "\n " + "Cause ID =  " +st[i].causeId  + " Amount: " + st[i].moneyAskedFor ;
                    System.out.println("Cause ID =  " +st[i].causeId  + " Amount: " + st[i].moneyAskedFor);
                }
             Toast.makeText(HelperSearchResult.this, text,
                        Toast.LENGTH_LONG).show();

            }


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




    myOwnCustomAdapter4(Context con,String[] status,String [] type,String [] percentageCompleted,String [] description )
    {
        super(con,R.layout.single_row_list_needy_existing_causes,R.id.NECLISTStatusOfCause,status);
        this.cont=con;
        this.statuss= status;
        this.typee= type;
        this.percentageCompletedd= percentageCompleted;
        this.descriptionn = description;

    }

    public View getView(int position,View toBeConverted, ViewGroup parent){

        LayoutInflater myInflator = (LayoutInflater) cont.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View myView = null;
        myView = myInflator.inflate(R.layout.single_row_list_temp_2,parent,false);

        TextView myStatus = (TextView) myView.findViewById(R.id.NECLISTStatusOfCause);
        TextView myType= (TextView) myView.findViewById(R.id.NECLISTCatagoryOfCause);
        TextView myPercentage= (TextView) myView.findViewById(R.id.NECLISTPercentageCompletedValue);
        TextView myDescription= (TextView) myView.findViewById(R.id.NECLISTDescription);

        TextView myItemNo = (TextView ) myView.findViewById(R.id.dummyNoForListNoStorage2);

        myItemNo.setText((String.valueOf( position)));


        myStatus.setText(statuss[position]);
        myType.setText(typee[position]);
        myPercentage.setText(percentageCompletedd[position]);
        myDescription.setText(descriptionn[position]);

        return myView;
    }











}




