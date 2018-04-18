package com.romilparh.shadybond.shadyparkingsystem.database.model;

import com.romilparh.shadybond.shadyparkingsystem.validation.EMailValidator;

/**
 * Created by shadybond on 2018-04-17.
 */

public class UserInfoDBModel {
    String eMail, password, dob;
    char gender;
    EMailValidator validator = new EMailValidator();

    UserInfoDBModel(String eMail, String password, String dob, char gender){
        if(validator.isValidEmailAddress(eMail)){
            this.eMail = eMail;
            this.password = password;
            this.dob = dob;
            this.gender = gender;
        } else{
            // Throw Exception Here
        }
    }
}
