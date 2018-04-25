package com.romilparh.shadybond.shadyparkingsystem.database.model;

import com.romilparh.shadybond.shadyparkingsystem.validation.EMailValidator;

/**
 * Created by shadybond on 2018-04-17.
 */

public class ParkingTicketDBModel {
    double price;
    int car_make, parking_number;
    String car_color, time_ticket, parking_lane, card_number, eMail, car_number;
    EMailValidator validator = new EMailValidator();

    public ParkingTicketDBModel(String eMail, double price, int car_make, int parking_number, String car_color, String time_ticket, String parking_lane, String card_number, String car_number){
            if(validator.isValidEmailAddress(eMail)){
                this.eMail = eMail;
                this.price = price;
                this.car_make = car_make;
                this.parking_number = parking_number;
                this.car_color = car_color;
                this.time_ticket = time_ticket;
                this.parking_lane = parking_lane;
                this.card_number = card_number;
                this.car_number = car_number;
            } else {
                // Throw Exception here
            }
        }
    public ParkingTicketDBModel(){

    }

    public void setPrice(double price){
        this.price = price;
    }

    public void setCar_Make(int car_make){
        this.car_make = car_make;
    }

    public void setParking_number(int parking_number){
        this.parking_number = parking_number;
    }

    public void setCar_color(String color){
        this.car_color = color;
    }

    public void setTime_ticket(String time_ticket){
        this.time_ticket = time_ticket;
    }

    public void setCard_Number(String card_number) {
        this.card_number = card_number;
    }

    public void seteMail(String eMail){
        this.eMail = eMail;
    }

    public void setParking_lane(String parking_lane){
        this.parking_lane = parking_lane;
    }

    public void setCar_number(String car_number){
        this.car_number = car_number;
    }

    public double getPrice(){
        return this.price;
    }

    public int getCar_make(){
        return this.car_make;
    }

    public int getParking_number(){
        return this.parking_number;
    }

    public String getCar_color(){
        return this.car_color;
    }

    public String getTime_ticket(){
        return this.time_ticket;
    }

    public String getParking_lane(){
        return this.parking_lane;
    }

    public String getCard_number(){
        return this.card_number;
    }

    public String geteMail(){
        return this.eMail;
    }

    public String getCar_number(){
        return this.car_number;
    }
}
