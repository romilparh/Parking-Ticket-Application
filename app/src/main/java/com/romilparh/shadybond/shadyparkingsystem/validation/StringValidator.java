package com.romilparh.shadybond.shadyparkingsystem.validation;

/**
 * Created by shadybond on 2018-04-23.
 */

public class StringValidator {
    public boolean checkString(String string){
        if(string.length()<1){
            return false;
        } else return true;
    }
}
