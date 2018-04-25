package com.romilparh.shadybond.shadyparkingsystem.validation;

/**
 * Created by shadybond on 2018-04-24.
 */

public class CarValidation {

    public boolean isValidCarNumber(String carNumber){
        if(carNumber.length()>0){
            return true;
        } else return false;
    }

    public boolean isValidCarMake(String carMake){
        if(carMake.length()==4 && checkYear(carMake)){
            return true;
        } else return false;
    }

    public boolean checkYear(String year){
        int a = Integer.parseInt(year);
        if(a>1990 && a<2019){
            return true;
        } else return false;
    }

}
