package com.example.please.devitti;

import android.os.Parcelable;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by please on 1/1/2015.
 */
public class Cause implements Serializable {

    String causeId;
    String needyId;
    String moneyAskedFor;
    String description;
    String status;
    String dateOfRequest;
    String dateOfCompletion;
    String dateOfMaturity;
    String latitude;
    String longitude;
    String type;


    String catagory;

    String noOfEndorsements;
    String noOfLendingDetails;

    LendingDetailForCause lendingDetails [];

    Cause (String cause_Id, String needy_Id, String money_Asked_For, String description_, String status_,
           String type_ ,String date_Of_Request ,String date_Of_Completion,String date_Of_Maturity,
           String latitude_ , String longitude_  , LendingDetailForCause ld [] , String catagory_,
            String noOfEndorsements_ ,String noOfLendingDetails_) {

        this.causeId = cause_Id;
        this.needyId = needy_Id;
        this.moneyAskedFor = money_Asked_For;
        this.description = description_;
        this.status = status_;
        this.type = type_;
        this.dateOfRequest = date_Of_Request;
        this.dateOfCompletion = date_Of_Completion;
        this.dateOfMaturity =  date_Of_Maturity;
        this.latitude = latitude_;
        this.longitude =longitude_ ;

        this.catagory = catagory_;


        this.lendingDetails = ld;

        this.noOfEndorsements = noOfEndorsements_;
        this.noOfLendingDetails = noOfLendingDetails_;

    }




}
