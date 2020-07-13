package com.example.libraryproject;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RentBook extends AppCompatActivity {

    String USER_SESSION_KEY = "USER_INFO";
    SharedPreferences sharedPreferences;
    private final int MY_PERMISSIONS_REQUEST_CAMERA=1001;
    private String memNo;
    private String rMemNo;
    Intent RIntent;
    private String bookNo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rent_b);
        RIntent = getIntent();
        rMemNo = RIntent.getStringExtra("MEM_NO");
        sharedPreferences = this.getSharedPreferences(USER_SESSION_KEY, this.MODE_PRIVATE);
        memNo = sharedPreferences.getString("MemNo", "");
        new IntentIntegrator(this).initiateScan();

        int permssionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);

        if (permssionCheck!= PackageManager.PERMISSION_GRANTED) {

            Toast.makeText(this,"권한 승인이 필요합니다",Toast.LENGTH_LONG).show();

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.CAMERA)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CAMERA},
                        MY_PERMISSIONS_REQUEST_CAMERA);

            }
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CAMERA: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(this,"승인이 허가되어 있습니다.",Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(this,"아직 승인받지 않았습니다.",Toast.LENGTH_LONG).show();
                }
                return;
            }

        }
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // QR코드/바코드를 스캔한 결과 값을 가져옵니다.
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        // 결과값 출력
        Toast.makeText(this, "출력 ISBN:" + result.getContents(), Toast.LENGTH_SHORT).show();
        //레트로핏 구현해야함
        bookNo = result.getContents();
        RetrofitService iservice = new RetrofitCom().retrofit.create(RetrofitService.class);
        Call<BookDTO> call = iservice.rentBook(memNo,bookNo,rMemNo);
        Log.d("URL_TEST", call.request().url().toString());
        call.enqueue(new Callback<BookDTO>() {
            @Override
            public void onResponse(Call<BookDTO> call, Response<BookDTO> response) {
                if (response.isSuccessful()) {
                    Log.d("RETROFIT_SUC", response.body().toString());
                    Intent intent = new Intent(getApplicationContext(),WorkMain.class);
                    startActivity(intent);
                } else {
                    Log.d("RETROFIT_FAIL", response.errorBody().toString());
                }
            }

            @Override
            public void onFailure (Call<BookDTO> call, Throwable t){
                Log.d("RETROFIT_TEST", t.getLocalizedMessage());
            }
        });
    }
}


