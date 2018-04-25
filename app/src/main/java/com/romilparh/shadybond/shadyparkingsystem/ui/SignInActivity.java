package com.romilparh.shadybond.shadyparkingsystem.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.romilparh.shadybond.shadyparkingsystem.R;
import com.romilparh.shadybond.shadyparkingsystem.database.DBHelper;
import com.romilparh.shadybond.shadyparkingsystem.database.model.UserInfoDBModel;
import com.romilparh.shadybond.shadyparkingsystem.validation.EMailValidator;
import com.romilparh.shadybond.shadyparkingsystem.validation.PasswordValidator;

import java.util.List;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edEmail, edPassword;
    private Button registerButton, loginButton;
    String name;

    PasswordValidator pV = new PasswordValidator();
    EMailValidator eV = new EMailValidator();

    List<UserInfoDBModel> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        registerButton = findViewById(R.id.buttonRegister);
        loginButton = findViewById(R.id.buttonLogin);

        userList = new DBHelper(this).getDataFromUserInfo();

        edEmail = (EditText) findViewById(R.id.emailLogin);
        edPassword = (EditText) findViewById(R.id.passwordLogin);

        loginButton.setOnClickListener(this);
        registerButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();


        switch(id){
            case R.id.buttonLogin:

                if(!eV.isValidEmailAddress(edEmail.getText().toString())){
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
                    builder1.setMessage("EMail Not Valid");
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
                } else if (!pV.checkPassword(edPassword.getText().toString())){
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
                    builder1.setMessage("Password can't be less than length 8");
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
                else if(login(edEmail.getText().toString().toLowerCase(),edPassword.getText().toString())){

                        Intent i = new Intent(SignInActivity.this, Home.class);
                        i.putExtra("email",edEmail.getText().toString());
                        i.putExtra("name",this.name);
                        startActivity(i);
                        finish();
                    }

                else{
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
                    builder1.setMessage("EMail or Password either not valid \n\nor\n\n EMail not registered");
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
                break;
            case R.id.buttonRegister:
                startActivity(new Intent(SignInActivity.this, RegistrationActivity.class));
                break;
        }
    }

    /*
    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
     */

    public boolean login(String eMail, String password){
        boolean returnValue = false;
        for(int i=0;i<userList.size();i++){
            if(eMail.equals(userList.get(i).getEMail()) && password.equals(userList.get(i).getPassword())){
                returnValue = true;
                this.name = userList.get(i).getName();
                break;
            }
        }
        return returnValue;
    }

}
