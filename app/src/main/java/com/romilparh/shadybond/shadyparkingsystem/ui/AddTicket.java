package com.romilparh.shadybond.shadyparkingsystem.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.romilparh.shadybond.shadyparkingsystem.R;
import com.romilparh.shadybond.shadyparkingsystem.database.DBHelper;
import com.romilparh.shadybond.shadyparkingsystem.database.model.ParkingTicketDBModel;
import com.romilparh.shadybond.shadyparkingsystem.database.model.PaymentInfoDBModel;
import com.romilparh.shadybond.shadyparkingsystem.validation.CarValidation;
import com.romilparh.shadybond.shadyparkingsystem.validation.CardValidation;

import java.util.List;

public class AddTicket extends AppCompatActivity{

    String name,email;
    double priceTicket = 0;
    double timeTicketValue = 0;
    ParkingTicketDBModel model = new ParkingTicketDBModel();
    List<PaymentInfoDBModel> list;
    DBHelper db;
    CarValidation cV = new CarValidation();

    String [] cardNumbers;

    RadioGroup timeTicket;
    Spinner cardSpinner,car_color, parking_lane, parking_number;
    EditText car_make, car_number;
    TextView price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Add Ticket");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ticket);

        Intent intent = getIntent();

        this.email = intent.getStringExtra("email");
        this.name = intent.getStringExtra("name");

        db = new DBHelper(this);
        list = db.getDataFromPaymentInfo(this.email);

        this.timeTicket = findViewById(R.id.timeRGAddTicket);
        this.car_color = findViewById(R.id.carColorAddTicket);
        this.car_make = findViewById(R.id.carMakeAddTicket);
        this.car_number = findViewById(R.id.carNumberAddTicket);
        this.parking_lane = findViewById(R.id.parkingLaneAddTicket);
        this.parking_number = findViewById(R.id.parkingNumberAddTicket);
        this.price = findViewById(R.id.priceAddTicket);

        cardNumbers = new String[list.size()];
        for(int i=0;i<list.size();i++){
            this.cardNumbers[i] = new String(list.get(i).getCard_number());
        }
        this.cardSpinner = findViewById(R.id.cardNumberAddTicket);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, cardNumbers);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cardSpinner.setAdapter(adapter);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.saveFloatButton);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addTicket();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu. back_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.back:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void addTicket(){
        if(!cV.isValidCarNumber(this.car_number.getText().toString())){
            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setMessage("Car Number Not Valid");
            builder1.setCancelable(true);

            builder1.setPositiveButton(
                    "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });

            AlertDialog alert11 = builder1.create();
            alert11.show();
        } else if(!cV.isValidCarMake(this.car_make.getText().toString())){
            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setMessage("Car Make Year Not Valid");
            builder1.setCancelable(true);

            builder1.setPositiveButton(
                    "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });

            AlertDialog alert11 = builder1.create();
            alert11.show();
        } else if(this.timeTicket.getCheckedRadioButtonId() == -1){
            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setMessage("Select Time");
            builder1.setCancelable(true);

            builder1.setPositiveButton(
                    "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });

            AlertDialog alert11 = builder1.create();
            alert11.show();
        }else if(this.cardSpinner.getCount()==0){
            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setMessage("Please Add Payment Method First");
            builder1.setCancelable(true);

            builder1.setPositiveButton(
                    "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });

            AlertDialog alert11 = builder1.create();
            alert11.show();
        }
        else if(cV.isValidCarNumber(this.car_number.getText().toString())&&cV.isValidCarMake(this.car_make.getText().toString())&&this.timeTicket.getCheckedRadioButtonId() != -1){
            this.model.seteMail(this.email);
            this.model.setPrice(this.priceTicket);
            this.model.setCar_Make(Integer.parseInt(this.car_make.getText().toString()));
            this.model.setCar_color(this.car_color.getSelectedItem().toString());
            this.model.setCar_number(this.car_number.getText().toString());
            this.model.setCard_Number(this.cardSpinner.getSelectedItem().toString());
            this.model.setParking_lane(this.parking_lane.getSelectedItem().toString());
            this.model.setTime_ticket(String.valueOf(this.timeTicketValue));
            this.model.setParking_number(Integer.parseInt(this.parking_number.getSelectedItem().toString()));

            db.insertIntoTicketDB(this.model);

            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setMessage("Ticket Added Successfully");
            builder1.setCancelable(true);

            builder1.setPositiveButton(
                    "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Intent intent = new Intent(AddTicket.this, Home.class);
                            intent.putExtra("email", email);
                            intent.putExtra("name",name);
                            startActivity(intent);
                            finish();
                        }
                    });

            AlertDialog alert11 = builder1.create();
            alert11.show();
        }
    }

    public void onClick(View v){
        int id = v.getId();

        if(id == R.id.half_HourAddTicket){
            this.timeTicketValue = 30;
            this.priceTicket = 5;
            this.price.setText("Price: $"+String.valueOf(this.priceTicket));
        } else if(id == R.id.one_hourAddTicket){
            this.timeTicketValue = 60;
            this.priceTicket = 10;
            this.price.setText("Price: $"+String.valueOf(this.priceTicket));
        } else if (id == R.id.two_hourAddTicket){
            this.timeTicketValue = 120;
            this.priceTicket = 20;
            this.price.setText("Price: $"+String.valueOf(this.priceTicket));
        } else if(id == R.id.dayAddTicket){
            this.timeTicketValue = 24*60;
            this.priceTicket = 50;
            this.price.setText("Price: $"+String.valueOf(this.priceTicket));
        } else{
            this.timeTicketValue = 0;
            this.price.setText("Price: $"+String.valueOf(this.priceTicket));
        }
    }

}

// Add Back Button