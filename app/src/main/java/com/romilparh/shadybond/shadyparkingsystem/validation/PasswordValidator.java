package com.romilparh.shadybond.shadyparkingsystem.validation;

/**
 * Created by shadybond on 2018-04-23.
 */

public class PasswordValidator {
    public boolean checkPassword(String string){
        if(string.length()<7){
            return false;
        } else{
            return true;
        }
    }
}
