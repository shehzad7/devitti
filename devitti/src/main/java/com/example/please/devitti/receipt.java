package com.example.please.devitti;

/**
 * Created by please on 4/6/2015.
 */
public class Receipt {
    String RpId;
    String LDFCId;
    String transationId;
    String status;
    public Receipt()
    {
        this.RpId = "";
        this.LDFCId = "";
        this.transationId = "";
        this.status = "";

    }
    public Receipt(String _RpId, String _LDFCId, String _transationId, String _status)
    {
        this.RpId = _RpId;
        this.LDFCId = _LDFCId;
        this.transationId = _transationId;
        this.status = _status;
    }


}
