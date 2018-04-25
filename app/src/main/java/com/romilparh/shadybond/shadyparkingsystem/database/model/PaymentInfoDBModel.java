package com.romilparh.shadybond.shadyparkingsystem.database.model;

import com.romilparh.shadybond.shadyparkingsystem.validation.EMailValidator;

/**
 * Created by shadybond on 2018-04-17.
 */

public class PaymentInfoDBModel {
    char card_type;
    String card_number, cvv, eMail, dateExpiry;
    EMailValidator validator  = new EMailValidator();

    public PaymentInfoDBModel(char card_type, String card_number, String cvv, String eMail, String dateExpiry){
        if(validator.isValidEmailAddress(eMail)){
            this.card_type = card_type;
            this.card_number = card_number;
            this.cvv = cvv;
            this.eMail = eMail;
            this.dateExpiry = dateExpiry;
        }
    }

    public PaymentInfoDBModel(){

    }

    public void setCard_type(char card_type){
        this.card_type = card_type;
    }

    public void setCard_number(String card_number){
        this.card_number = card_number;
    }

    public void setCvv(String cvv){
        this.cvv = cvv;
    }

    public void seteMail(String eMail){
        this.eMail = eMail;
    }

    public void setDateExpiry(String dateExpiry){
        this.dateExpiry = dateExpiry;
    }

    public String getCard_Type(){
        String s = String.valueOf(this.card_type);
        return s;
    }

    public String getCard_number(){
        return this.card_number;
    }

    public String getCvv(){
        return this.cvv;
    }

    public String geteMail(){
        return this.eMail;
    }

    public String getDateExpiry(){ return this.dateExpiry;
    }
}
