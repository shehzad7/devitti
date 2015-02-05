package com.example.please.devitti;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by please on 12/21/2014.
 */

public class   NeedyExistingCauses extends Activity implements AdapterView.OnItemClickListener{


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


    ListView mainList;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.needy_existing_causes);

        mainList = (ListView) findViewById(R.id.mainList);


        //By Custom Adapter
        myOwnCustomAdapter adapter = new myOwnCustomAdapter(this,status, type, percentageCompleted, description);
        mainList.setAdapter(adapter);

        mainList.setOnItemClickListener(this);


        //By Simple default Adapter
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.single_row_list_needy_existing_causes,
//                R.id.NECLISTStatusOfCause,status);
//        mainList.setAdapter(adapter);
//        mainList.isClickable();



//        mainList.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });


//        categorySpinner = (Spinner) findViewById(R.id.catagory_spinner);
//        ArrayAdapter <String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,categroyData);
//        categorySpinner.setAdapter(adapter);




    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        TextView t = (TextView) view.findViewById(R.id.NECLISTStatusOfCause);
        Toast.makeText(this,t.getText(),Toast.LENGTH_SHORT).show();



    }
}

class myOwnCustomAdapter extends ArrayAdapter<String>
{
    Context cont;
    String [] statuss;
    String [] typee;
    String [] percentageCompletedd;
    String [] descriptionn;




    myOwnCustomAdapter(Context con,String[] status,String [] type,String [] percentageCompleted,String [] description )
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
        myView = myInflator.inflate(R.layout.single_row_list_needy_existing_causes,parent,false);

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





