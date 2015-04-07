package com.example.please.devitti;

/**
 * Created by please on 4/7/2015.
 */
public class User {

    String userId;
    String name;
    String username;
    String password;
    String type;
    String email;
    String address;
    String city;
    String country;
    String bankName;
    String branchCode;
    String accountNo;


    public User(String userId, String name, String username, String password, String type ,String email, String address, String city,
                String country, String  bankName, String branchCode, String accountNo)
    {
        this.userId = userId;
        this.name = name;
        this.username = username;
        this.password = password;
        this.type = type;
        this.email = email;
        this.address = address;
        this.city = city;
        this.country = country;
        this.bankName = bankName;
        this.branchCode = branchCode;
        this.accountNo = accountNo;
    }

    public User()
    {
        this.userId = "";
        this.name = "";
        this.username = "";
        this.password = "";
        this.type = "";
        this.email = "";
        this.address = "";
        this.city = "";
        this.country = "";
        this.bankName = "";
        this.branchCode = "";
        this.accountNo = "";

    }


}
