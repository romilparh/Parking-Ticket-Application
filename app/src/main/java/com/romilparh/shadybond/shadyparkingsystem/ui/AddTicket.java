package com.romilparh.shadybond.shadyparkingsystem.ui;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.romilparh.shadybond.shadyparkingsystem.R;

public class AddTicket extends AppCompatActivity {

    String name,email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Add Ticket");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ticket);

        Intent intent = getIntent();

        this.email = intent.getStringExtra("email");
        this.name = intent.getStringExtra("name");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.saveFloatButton);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Save to Parking Ticket Code Here
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



}

// Add Back Button