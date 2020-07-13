package com.example.libraryproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MemHopeBook extends AppCompatActivity {

    EditText et_hbooktitle;
    EditText et_hbookauthor;
    EditText et_hbookpublish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mem_hope_book);
        et_hbooktitle = (EditText)findViewById(R.id.et_hbooktitle);
        et_hbookauthor = (EditText)findViewById(R.id.et_hbookaurthor);
        et_hbookpublish = (EditText)findViewById(R.id.et_hbookpublish);
    }

    public void btnhc(View v){
        Intent Lintent = new Intent(getApplicationContext(),MemMain.class);
        startActivity(Lintent);
    }
    public void btnhr(View v){

        if(et_hbooktitle.getText().toString().equals("")){
            et_hbooktitle.setError("도서제목을 입력하세요.");
            return;
        }
        if(et_hbookauthor.getText().toString().equals("")){
            et_hbookauthor.setError("도서저자를 입력하세요.");
            return;
        }

        if(et_hbookpublish.getText().toString().equals("")){
            et_hbookpublish.setError("출판사를 입력하세요.");
            return;
        }
        final RetrofitService service = new RetrofitCom().retrofit.create(RetrofitService.class);
        Call<HBookDTO> call = service.inserthbook(et_hbooktitle.getText().toString(),et_hbookauthor.getText().toString(),et_hbookpublish.getText().toString());
        Log.d("URL_TEST", call.request().url().toString());
        try {
            call.enqueue(new Callback<HBookDTO>() {
                @Override
                public void onResponse(Call<HBookDTO> call, Response<HBookDTO> response) {
                    if (response.isSuccessful() ) {
                        HBookDTO result = response.body();
                        Log.d("RETROFIT_SUC", response.body().toString());
                        Intent intent = new Intent(getApplicationContext(),MemMain.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }

                 else {
                    Log.d("RETROFIT_FAIL", response.errorBody().toString());
                }
            }
            @Override
            public void onFailure(Call<HBookDTO> call, Throwable t) {
                Log.d("RETROFIT_TEST", t.getLocalizedMessage());

            }
        });
    } catch (Exception exception) {
        Log.d("ERROR_M", exception.getMessage());
    }
}
    }

