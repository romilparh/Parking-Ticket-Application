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
import android.widget.EditText;
import android.widget.RadioGroup;

import com.romilparh.shadybond.shadyparkingsystem.R;
import com.romilparh.shadybond.shadyparkingsystem.database.DBHelper;
import com.romilparh.shadybond.shadyparkingsystem.database.model.PaymentInfoDBModel;
import com.romilparh.shadybond.shadyparkingsystem.validation.CardValidation;

import java.util.Calendar;

public class AddPayment extends AppCompatActivity {

    String email,name;
    EditText cardNo, cardCvv,cardExpiryMonth, cardExpiryYear;
    RadioGroup cardType;

    DBHelper db;
    PaymentInfoDBModel model = new PaymentInfoDBModel();
    CardValidation cV = new CardValidation();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_payment);

        Intent intent = getIntent();

        this.email = intent.getStringExtra("email");
        this.name = intent.getStringExtra("name");

        this.cardNo = findViewById(R.id.cardNumber);
        this.cardCvv = findViewById(R.id.cardCVV);
        this.cardExpiryMonth = findViewById(R.id.cardExpiryMonth);
        this.cardExpiryYear = findViewById(R.id.cardExpiryYear);
        this.cardType = findViewById(R.id.cardTypeSelection);

        this.db = new DBHelper(this);
        setTitle("Add Payment Method");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.saveFloatButton);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addMethodPayment();
                onBackPressed();
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

    public void addMethodPayment(){
        if(!cV.isValidCardNumber(this.cardNo.getText().toString())){
            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setMessage("Card Number Not Valid");
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
        } else if(!cV.isValidCardCvv(this.cardCvv.getText().toString())){
            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setMessage("Card CVV Not Valid");
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
        }else if (this.cardType.getCheckedRadioButtonId() == -1)
        {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setMessage("No Card Type Selected");
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
        } else if(!cV.isValidExpiryMonth(this.cardExpiryMonth.getText().toString())){
            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setMessage("Expiry Month can only be from 1 to 12");
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
        } else if(!cV.isValidExpiryYear(this.cardExpiryYear.getText().toString())){
            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setMessage("Expiry Year Not Valid");
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
        } else if(cV.isValidCardNumber(this.cardNo.getText().toString()) && !cV.isValidCardCvv(this.cardCvv.getText().toString()) && cV.isValidExpiryMonth(this.cardExpiryMonth.getText().toString())&& !cV.isValidExpiryYear(this.cardExpiryYear.getText().toString())){
            char type;
            if(this.cardType.getCheckedRadioButtonId() == R.id.creditRadio){
                type = 'c';
            }
            else{
                type = 'd';
            }
            this.model.setCard_number(this.cardNo.getText().toString());
            this.model.setCvv(this.cardCvv.getText().toString());
            this.model.setDateExpiry(this.cardExpiryMonth.getText().toString()+" "+this.cardExpiryYear.getText().toString());
            this.model.setCard_type(type);
            this.model.seteMail(this.email);
            this.db.insertIntoPaymentDB(this.model);

            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setMessage("Payment Method Added");
            builder1.setCancelable(true);

            builder1.setPositiveButton(
                    "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Intent intent = new Intent(AddPayment.this, PaymentMethodDetails.class);
                            startActivity(intent);
                            finish();
                        }
                    });

            AlertDialog alert11 = builder1.create();
            alert11.show();
        }
    }
}
