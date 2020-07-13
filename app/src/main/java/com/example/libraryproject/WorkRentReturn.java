package com.example.libraryproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class WorkRentReturn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_rent_return);
    }
    public void btnRent(View v){
        Intent Lintent = new Intent(getApplicationContext(),WorkRent.class);
        startActivity(Lintent);
    }
    public void btnReturn(View v){
        Intent Lintent = new Intent(getApplicationContext(),WorkReturn.class);
        startActivity(Lintent);
    }
}
