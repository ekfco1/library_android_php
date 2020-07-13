package com.example.libraryproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Date;
import java.text.SimpleDateFormat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InsertBook extends AppCompatActivity {

    EditText et_btitle;
    EditText et_bauthor;
    EditText et_bpublish;
    String USER_SESSION_KEY = "USER_INFO";
    SharedPreferences sharedPreferences;
    private String BFlag;
    Date date = new Date(System.currentTimeMillis());
    String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
    private String MemNo;
    private String BDate;// = currentDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_book);
        sharedPreferences = this.getSharedPreferences(USER_SESSION_KEY, this.MODE_PRIVATE);
        MemNo = sharedPreferences.getString("MemNo", "");
        et_btitle = (EditText)findViewById(R.id.et_btitle);
        et_bauthor = (EditText)findViewById(R.id.et_bauthor);
        et_bpublish = (EditText)findViewById(R.id.et_bpublish);
       // BDate = DateFormat.getDateInstance(DateFormat.MEDIUM).toString();
        BDate = currentDate;
        BFlag = "0";
//        Bdate = date.;
    }
    public void btnCan(View v){
        Intent Lintent = new Intent(getApplicationContext(),WorkMain.class);
        startActivity(Lintent);
    }
    public void btnRbook(View v){

        if(et_btitle.getText().toString().equals("")){
            et_btitle.setError("도서제목을 입력하세요.");
            return;
        }
        if(et_bauthor.getText().toString().equals("")){
            et_bauthor.setError("도서저자를 입력하세요.");
            return;
        }

        if(et_bpublish.getText().toString().equals("")){
            et_bpublish.setError("출판사를 입력하세요.");
            return;
        }

        final RetrofitService service = new RetrofitCom().retrofit.create(RetrofitService.class);
        Call<BookDTO> call = service.insertbook(et_btitle.getText().toString(),et_bauthor.getText().toString(),et_bpublish.getText().toString(),BDate,BFlag,MemNo);

        Log.d("URL_TEST", call.request().url().toString());
        try {
            call.enqueue(new Callback<BookDTO>() {
                @Override
                public void onResponse(Call<BookDTO> call, Response<BookDTO> response) {
                    if (response.isSuccessful() ) {
                        Log.d("RETROFIT_SUC", response.body().toString());
                        Intent intent = new Intent(getApplicationContext(),WorkMain.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                    else {
                        Log.d("RETROFIT_FAIL", response.errorBody().toString());
                    }
                }
                @Override
                public void onFailure(Call<BookDTO> call, Throwable t) {
                    Log.d("RETROFIT_TEST", t.getLocalizedMessage());
                }
            });
        } catch (Exception exception) {
            Log.d("ERROR_M", exception.getMessage());
        }
    }
}
