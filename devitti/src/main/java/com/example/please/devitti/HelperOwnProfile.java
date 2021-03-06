package com.example.please.devitti;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by please on 12/21/2014.
 */
public class HelperOwnProfile extends Activity implements AdapterView.OnItemClickListener {

    ListView list;

    int scrollypos = 0;

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


    ScrollView mainScroll;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.helper_own_profile);
        list = (ListView) findViewById(R.id.HOFList);

        //mainScroll = (ScrollView) findViewById(R.id.helperownprofilemainscroll);


        //By Custom Adapter
        myOwnCustomAdapter3 adapter = new myOwnCustomAdapter3(this,status, type, percentageCompleted, description);
        list.setAdapter(adapter);

        list.setOnItemClickListener(this);


        list.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if(view.getLastVisiblePosition()==(totalItemCount-1)){
                    list.setLayoutParams(new LinearLayout.LayoutParams(RadioGroup.LayoutParams.FILL_PARENT, 900));
                }
            }
        });





    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        Intent openCauseFullDetailViewPage = new Intent(NeedyProfile.this, CauseFullDetailView.class);
//        startActivity(openCauseFullDetailViewPage);

        Toast.makeText(getApplicationContext(), "DETAILS YAHHHHH",
                Toast.LENGTH_SHORT).show();

//        RadioGroup.LayoutParams prm = (RadioGroup.LayoutParams) list.getLayoutParams();
//        prm.height = 600;//set the height acc to you;//like int  200
//        list.setLayoutParams(prm);


        list.setLayoutParams(new LinearLayout.LayoutParams(RadioGroup.LayoutParams.FILL_PARENT, 700));
//
//        list.setLayoutParams(new LinearLayout.LayoutParams(
//                LinearLayout.LayoutParams.WRAP_CONTENT,
//                LinearLayout.LayoutParams.WRAP_CONTENT));
    }
}






class myOwnCustomAdapter3 extends ArrayAdapter<String>
{
    Context cont;
    String [] statuss;
    String [] typee;
    String [] percentageCompletedd;
    String [] descriptionn;




    myOwnCustomAdapter3(Context con,String[] status,String [] type,String [] percentageCompleted,String [] description )
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
