package com.example.libraryproject;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;

public class MemBarcode extends AppCompatActivity {
    String USER_SESSION_KEY = "USER_INFO";
    SharedPreferences sharedPreferences;
    private String memNo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mem_barcode);

        sharedPreferences = this.getSharedPreferences(USER_SESSION_KEY, this.MODE_PRIVATE);
        memNo = sharedPreferences.getString("MemNo", "");
        ImageView barcode = findViewById(R.id.imageView);

        String barcode_data = memNo;

// barcode image
        try {
            Bitmap bitmap = Barcode.encodeAsBitmap(barcode_data , BarcodeFormat.CODE_128 , 600 , 300);
            barcode.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }
        
    }

}
