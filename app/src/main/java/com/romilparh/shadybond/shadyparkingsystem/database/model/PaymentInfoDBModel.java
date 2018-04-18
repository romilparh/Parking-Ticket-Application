package com.romilparh.shadybond.shadyparkingsystem.database.model;

import com.romilparh.shadybond.shadyparkingsystem.validation.EMailValidator;

/**
 * Created by shadybond on 2018-04-17.
 */

public class PaymentInfoDBModel {
    char card_type;
    String card_number, cvv, eMail;
    EMailValidator validator  = new EMailValidator();

    PaymentInfoDBModel(char card_type, String card_number, String cvv, String eMail){
        if(validator.isValidEmailAddress(eMail)){
            this.card_type = card_type;
            this.card_number = card_number;
            this.cvv = cvv;
            this.eMail = eMail;
        }else{
            // Throw Exception
        }
    }
}
