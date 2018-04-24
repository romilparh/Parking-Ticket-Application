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
        if(month.equals("1")||month.equals("2")||month.equals("3")||month.equals("4")||month.equals("5")||month.equals("6")||month.equals("7")||month.equals("8")||month.equals("9")||month.equals("10")||month.equals("12")||month.equals("12")){
            return true;
        }
        else return false;
    }

    public boolean isValidExpiryYear(String year){
        if(toString().length()==4){
            return true;
        } else return false;
    }
}
