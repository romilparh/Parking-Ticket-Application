package com.romilparh.shadybond.shadyparkingsystem.database.model;

import com.romilparh.shadybond.shadyparkingsystem.validation.EMailValidator;

/**
 * Created by shadybond on 2018-04-17.
 */

public class ParkingTicketDBModel {
    double price;
    int car_make, parking_number;
    String car_color, time_ticket, parking_lane, card_number, eMail;
    EMailValidator validator = new EMailValidator();

        ParkingTicketDBModel(String eMail, double price, int car_make, int parking_number, String car_color, String time_ticket, String parking_lane, String card_number){
            if(validator.isValidEmailAddress(eMail)){
                this.eMail = eMail;
                this.price = price;
                this.car_make = car_make;
                this.parking_number = parking_number;
                this.car_color = car_color;
                this.time_ticket = time_ticket;
                this.parking_lane = parking_lane;
                this.card_number = card_number;
            } else {
                // Throw Exception here
            }
        }
}
