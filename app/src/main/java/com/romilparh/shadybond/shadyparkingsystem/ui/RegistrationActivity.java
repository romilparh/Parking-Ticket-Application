package com.romilparh.shadybond.shadyparkingsystem.ui;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.romilparh.shadybond.shadyparkingsystem.R;
import com.romilparh.shadybond.shadyparkingsystem.database.DBHelper;
import com.romilparh.shadybond.shadyparkingsystem.database.model.UserInfoDBModel;
import com.romilparh.shadybond.shadyparkingsystem.validation.EMailValidator;
import com.romilparh.shadybond.shadyparkingsystem.validation.PasswordValidator;
import com.romilparh.shadybond.shadyparkingsystem.validation.StringValidator;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener {

    EditText email, password, birthdate,name;
    Calendar myCalendar;
    private Button registerButton, loginButton;
    RadioGroup genderGroup;
    UserInfoDBModel model = new UserInfoDBModel();
    String dob;
    char gender;

    DBHelper db;

    EMailValidator eV = new EMailValidator();
    PasswordValidator pV = new PasswordValidator();
    StringValidator sV = new StringValidator();

    private void updateLabel() {
        String myFormat = "dd/MM/YYYY"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        birthdate.setText(sdf.format(myCalendar.getTime()));
        this.dob = sdf.format(myCalendar.getTime());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        db = new DBHelper(this);
        registerButton = findViewById(R.id.buttonRegister);
        loginButton = findViewById(R.id.buttonLogin);
        loginButton.setOnClickListener(this);
        registerButton.setOnClickListener(this);

        myCalendar = Calendar.getInstance();

        email = (EditText) findViewById(R.id.emailRegister);
        password = (EditText) findViewById(R.id.passwordRegister);
        genderGroup = (RadioGroup) findViewById(R.id.genderSelection);
        name = (EditText) findViewById(R.id.nameRegister);

        birthdate = (EditText) findViewById(R.id.birthDate);
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        birthdate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(RegistrationActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
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

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.buttonLogin:
                if (true) {
                    Intent i = new Intent(RegistrationActivity.this, SignInActivity.class);
                    startActivity(i);
                    finish();
                }
                break;
            case R.id.buttonRegister:
                // Registration Code Here
                if (eV.isValidEmailAddress(this.email.getText().toString()) && sV.checkString(this.name.getText().toString()) && pV.checkPassword(this.password.getText().toString()) && !this.dob.isEmpty() && !String.valueOf(gender).isEmpty()) {
                    UserInfoDBModel model = new UserInfoDBModel(this.email.getText().toString().toLowerCase(), this.name.getText().toString(), this.password.getText().toString(), this.dob, 'm');
                    db.insertIntoUserDB(model);
                    // Intent to Home Screen at clicking on alert ok of successfully registered
                } else { // Use else if to check which thing is not correct
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
                    builder1.setMessage("Kindly Check if all fields are entered correctly");
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

        }
    }
}

// Radio Group Get Value and Done and Add Name field
