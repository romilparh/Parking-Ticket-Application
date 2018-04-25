package com.romilparh.shadybond.shadyparkingsystem.database.model;

import com.romilparh.shadybond.shadyparkingsystem.validation.EMailValidator;

import java.util.ArrayList;

/**
 * Created by shadybond on 2018-04-17.
 */

public class UserInfoDBModel {
    String eMail, password, dob, name;
    char gender;
    EMailValidator validator = new EMailValidator();

    ArrayList<UserInfoDBModel> usersDB = new ArrayList<>();

    public UserInfoDBModel(String eMail, String name, String password, String dob, char gender){
        if(validator.isValidEmailAddress(eMail)){
            this.eMail = eMail;
            this.password = password;
            this.dob = dob;
            this.gender = gender;
            this.name = name;
        } else{
            // Throw Exception Here
        }
    }

    public UserInfoDBModel(){

    }

    public void setName(String name){
        this.name = name;
    }

    public void seteMail(String eMail){
        this.eMail = eMail;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void setDob(String dob){
        this.dob = dob;
    }

    public void setGender(char gender){
        this.gender = gender;
    }

    public String getEMail(){
        return this.eMail;
    }

    public String getPassword(){
        return this.password;
    }

    public String getDob(){
        return this.dob;
    }

    public char getGender(){
        return this.gender;
    }

    public String getName(){
        return this.name;
    }
}
