package com.romilparh.shadybond.shadyparkingsystem.ui;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.romilparh.shadybond.shadyparkingsystem.R;
import com.romilparh.shadybond.shadyparkingsystem.database.DBHelper;
import com.romilparh.shadybond.shadyparkingsystem.database.model.ParkingTicketDBModel;
import com.romilparh.shadybond.shadyparkingsystem.database.model.PaymentInfoDBModel;

import java.util.List;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    String email, name;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    List<ParkingTicketDBModel> myDataset;
    TextView name_user, email_user;
    DBHelper db;
    PaymentInfoDBModel payment = new PaymentInfoDBModel();
    ParkingTicketDBModel ticket = new ParkingTicketDBModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Intent intent = getIntent();

        this.email = intent.getStringExtra("email");
        this.name = intent.getStringExtra("name");

        db = new DBHelper(this);

        myDataset = db.getDataFromParkingTicketDB(this.email);

        mRecyclerView = findViewById(R.id.recyclerViewHome);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new MyAdapter(myDataset);
        mRecyclerView.setAdapter(mAdapter);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, AddTicket.class);
                intent.putExtra("email", email);
                intent.putExtra("name",name);
                startActivity(intent);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View hView =  navigationView.getHeaderView(0);

        name_user = (TextView)hView.findViewById(R.id.nameNav);
        name_user.setText(this.name);

        email_user = (TextView)hView.findViewById(R.id.eMailNav);
        email_user.setText(this.email);


        setTitle("Total Tickets:"+String.valueOf(db.getDataFromParkingTicketDB(this.email).size()));

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            System.out.print("Back Pressed");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            Intent intent = new Intent(Home.this, SignInActivity.class);
            startActivity(intent);
            finish();  // This call is missing.
            return true;
        } else if(id == R.id.action_instructions){
            Intent intent = new Intent(Home.this, InstructionActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_profile) {
            Intent intent = new Intent(Home.this, EditProfile.class);
            intent.putExtra("email",this.email);
            intent.putExtra("name",this.name);
            startActivity(intent);
        } else if (id == R.id.nav_location) {
            Intent intent = new Intent(Home.this, MapsActivity.class);
            startActivity(intent);
        } else if(id == R.id.payment_method){
            Intent intent = new Intent(Home.this, PaymentMethodDetails.class);
            intent.putExtra("email",this.email);
            intent.putExtra("name",this.name);
            startActivity(intent);
        }
        else if (id == R.id.nav_about) {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setMessage("About the team: \nRomil (All Rounder)");
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
        } else if (id == R.id.emailUs){
            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setMessage("EMail Us");
            builder1.setCancelable(true);

            builder1.setPositiveButton(
                    "Yes",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + "romilparhwal007@gmail.com"));
                            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "About Shady Parking System");

                            startActivity(Intent.createChooser(emailIntent, "Chooser Title"));
                        }
                    });
            builder1.setNegativeButton(
                    "No",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });

            AlertDialog alert11 = builder1.create();
            alert11.show();
        } else if(id == R.id.callUs){
            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setMessage("Call Us?\nWorking Hours: 9am to 5 pm");
            builder1.setCancelable(true);

            builder1.setPositiveButton(
                    "Yes",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel","+16479152405",null));
                            startActivity(intent);
                        }
                    });
            builder1.setNegativeButton(
                    "No",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });

            AlertDialog alert11 = builder1.create();
            alert11.show();
        } else if (id == R.id.textUs){
            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setMessage("Text Us?");
            builder1.setCancelable(true);

            builder1.setPositiveButton(
                    "Yes",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Uri uri = Uri.parse("smsto:6479152405");
                            Intent it = new Intent(Intent.ACTION_SENDTO, uri);
                            it.putExtra("sms_body", "Shady Parking System Contact Us\n");
                            startActivity(it);
                        }
                    });
            builder1.setNegativeButton(
                    "No",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });

            AlertDialog alert11 = builder1.create();
            alert11.show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
