package com.romilparh.shadybond.shadyparkingsystem.validation;

/**
 * Created by shadybond on 2018-04-24.
 */

public class CardValidation {

    public boolean isValidCardNumber(String cardNumber){
        if(cardNumber.length() == 16){
            return true;
        } else return false;
    }

    public boolean isValidCardCvv(String cvv){
        if(cvv.length() == 3){
            return true;
        } else return false;
    }

    public boolean isValidExpiryMonth(String month){
        int m = Integer.parseInt(month);
        if(m>0 && m<13){
            return true;
        }
        else return false;
    }

    public boolean isValidExpiryYear(String year){
        int yearE = Integer.parseInt(year);
        if(yearE>2017){
            return true;
        } else return false;
    }

}
