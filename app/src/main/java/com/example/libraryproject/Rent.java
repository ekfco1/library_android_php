package com.example.libraryproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Rent extends AppCompatActivity {

    private String memNo;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rent);
        intent = getIntent();
        memNo = intent.getStringExtra("MEM_NO");
    }
    public void btn_check(View v){
        if(memNo!= null){
            Intent intent = new Intent(getApplicationContext(),RentBook.class);
            intent.putExtra("MEM_NO",memNo);
            startActivity(intent);
        }else{
            Intent intent = new Intent(getApplicationContext(),WorkMain.class);
            startActivity(intent);
        }


    }
}
