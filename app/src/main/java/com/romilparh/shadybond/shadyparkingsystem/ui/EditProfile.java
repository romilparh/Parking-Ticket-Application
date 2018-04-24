package com.romilparh.shadybond.shadyparkingsystem.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.romilparh.shadybond.shadyparkingsystem.R;
import com.romilparh.shadybond.shadyparkingsystem.database.DBHelper;
import com.romilparh.shadybond.shadyparkingsystem.database.model.UserInfoDBModel;

import org.w3c.dom.Text;

import java.util.List;

public class EditProfile extends AppCompatActivity implements View.OnClickListener  {

    private EditText password, cPassword, oldPassword;
    Button updatePassword;
    String email,name,passwordString;
    TextView nameView, eMailView;
    DBHelper db;
    List<UserInfoDBModel> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Edit Profile");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        Intent intent = getIntent();

        this.email = intent.getStringExtra("email");
        this.name = intent.getStringExtra("name");

        db = new DBHelper(this);
        userList = db.getDataFromUserInfo();

        nameView = (TextView) findViewById(R.id.userName);
        eMailView = (TextView) findViewById(R.id.userEmail);
        password = (EditText) findViewById(R.id.password);
        cPassword = (EditText) findViewById(R.id.passwordConfirm);
        oldPassword = (EditText)findViewById(R.id.oldPassword);

        updatePassword = findViewById(R.id.buttonUpdate);

        updatePassword.setOnClickListener(this);

        nameView.setText(this.name);
        eMailView.setText(this.email);

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
        // Update Password
        // Alert
        this.passwordString = getPassword(this.email);
        if(password.getText().toString().equals(cPassword.getText().toString()) && !oldPassword.equals("") && oldPassword.getText().toString().equals(passwordString)){

            db.updateUserPassword(email,password.getText().toString());
            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setMessage("Password Updated");
            builder1.setCancelable(true);

            builder1.setPositiveButton(
                    "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                            onBackPressed();
                            finish();
                        }
                    });

            AlertDialog alert11 = builder1.create();
            alert11.show();
        }
        else{
            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setMessage("Try Again");
            builder1.setCancelable(true);

            builder1.setPositiveButton(
                    "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            startActivity(new Intent(EditProfile.this, Home.class));
                            finish();
                        }
                    });

            AlertDialog alert11 = builder1.create();
            alert11.show();
        }

    }

    public String getPassword(String email){
        String s = "";
        for(int i=0;i<userList.size();i++){
            if(email.equals(userList.get(i).getEMail())){
                s = userList.get(i).getPassword();
                break ;
            }
        }
        return s;
    }

}



// Get EMail, Old Password, Update Password
// Back Button