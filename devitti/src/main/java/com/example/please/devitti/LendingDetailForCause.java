package com.example.please.devitti;

import java.io.Serializable;

/**
 * Created by please on 1/1/2015.
 */
public class LendingDetailForCause  implements Serializable{

    int LDFCId;
    int causeId;
    int helperId;
    int needyId;
    int amountLended;
    String status;

    LendingDetailForCause(int LDFC_Id, int cause_Id, int helper_Id, int needy_Id, int amount_Lended, String status_ )
    {
        this.LDFCId= LDFC_Id;
        this.causeId = cause_Id;
        this.helperId = helper_Id;
        this.needyId = needy_Id;
        this.amountLended = amount_Lended;
        this.status = status_;

    }
    LendingDetailForCause()
    {
        this.LDFCId= -1;
        this.causeId = -1;
        this.helperId = -1;
        this.needyId = -1;
        this.amountLended = -1;
        this.status = "-1";

    }




}
