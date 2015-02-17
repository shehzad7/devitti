package com.example.please.devitti;

import android.app.Activity;
import android.content.Context;
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

import java.util.Arrays;

/**
 * Created by please on 12/24/2014.
 */
public class HelperSearchResult extends Activity implements AdapterView.OnItemClickListener {

    SparseArray<Parcelable> causesGot  =null;
    ListView list;

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

    String [] description ={"Yeh man i need it 1","Absolutely do need this money","You can't say no to education, can you","Hmmmm All i needy is a little money to expand","Need to buy new books man",
            "Some dummy description.. some text here ","Some dummy description.. some text here ","Some dummy description.. some text here ","Some dummy description.. some text here ","Some dummy description.. some text here ",
            "Some dummy description.","Some dummy description.","Some dummy description.","Some dummy description.","Some dummy description.",
            "Some dummy description.","Some dummy description.","Some dummy description.","Some dummy description.","Some dummy description....end"};

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.helper_search_result);

        Bundle b = getIntent().getExtras();
        if(b!=null)
        {
//            Parcelable[] parcelableArray =
//                    Parcel.readParcelableArray(Cause.class.getClassLoader());
//            MyClass[] resultArray = null;
//            if (parcelableArray != null) {
//                resultArray = Arrays.copyOf(parcelableArray, parcelableArray.length, MyClass[].class);
//            }




//            causesGot =  b.getSparseParcelableArray("searchCauses");
//            causesGot =
//            ArrayList<Parliament> as = (ArrayList<Parliament>)b.getSerializable("tasklist");
        }

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

        myStatus.setText(statuss[position]);
        myType.setText(typee[position]);
        myPercentage.setText(percentageCompletedd[position]);
        myDescription.setText(descriptionn[position]);

        return myView;
    }


}


