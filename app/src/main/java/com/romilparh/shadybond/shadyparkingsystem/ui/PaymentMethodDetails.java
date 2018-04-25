package com.romilparh.shadybond.shadyparkingsystem.ui;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.romilparh.shadybond.shadyparkingsystem.R;
import com.romilparh.shadybond.shadyparkingsystem.database.DBHelper;
import com.romilparh.shadybond.shadyparkingsystem.database.model.PaymentInfoDBModel;

import java.util.List;

public class PaymentMethodDetails extends AppCompatActivity {

    String email,name;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    List<PaymentInfoDBModel> paymentList;

    DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_method_details);

        Intent intent = getIntent();

        this.email = intent.getStringExtra("email");
        this.name = intent.getStringExtra("name");

        db = new DBHelper(this);
        this.paymentList = db.getDataFromPaymentInfo(this.email);
        mRecyclerView = findViewById(R.id.recyclerViewPayment);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new RVAdapterPayment(paymentList);
        mRecyclerView.setAdapter(mAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PaymentMethodDetails.this, AddPayment.class);
                intent.putExtra("email", email);
                intent.putExtra("name",name);
                startActivity(intent);
                finish();
            }
        });
        setTitle(this.name.toUpperCase()+"'s Payment Methods");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();

        this.email = intent.getStringExtra("email");
        this.name = intent.getStringExtra("name");

        db = new DBHelper(this);
        this.paymentList = db.getDataFromPaymentInfo(this.email);
        mRecyclerView = findViewById(R.id.recyclerViewPayment);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new RVAdapterPayment(paymentList);
        mRecyclerView.setAdapter(mAdapter);

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
                Intent intent = new Intent(PaymentMethodDetails.this, Home.class);
                intent.putExtra("email",email);
                intent.putExtra("name",name);
                startActivity(intent);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
