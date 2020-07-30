package com.example.libraryproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FaqWrite extends AppCompatActivity {

    Button btn_faq_write;
    Button btn_faq_c;
    EditText et_faq_title;
    EditText et_faq_content;
    private String MemNo;
    String USER_SESSION_KEY = "USER_INFO";
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq_write);
        btn_faq_c = (Button) findViewById(R.id.btn_faq_c);
        btn_faq_write = (Button) findViewById(R.id.btn_faq_write);
        et_faq_content = (EditText) findViewById(R.id.et_faq_content);
        et_faq_title = (EditText) findViewById(R.id.et_faq_title);
        sharedPreferences = this.getSharedPreferences(USER_SESSION_KEY, this.MODE_PRIVATE);
        MemNo = sharedPreferences.getString("MemNo", "");
        Log.d("faq_mem_no", MemNo);
    }

    public void btn_faq_c(View v){
        Intent intent = new Intent(getApplicationContext(),WorkMain.class);
        startActivity(intent);

    }
    public void btn_faq_write(View v){
        if(et_faq_title.getText().length() == 0){
            et_faq_title.setError("제목을 입력하세요");
            return; //
        }
        if(et_faq_content.getText().length() == 0){
            et_faq_content.setError("내용을 입력하세요");
            return; //
        }
        final RetrofitService service = new RetrofitCom().retrofit.create(RetrofitService.class);
        Call<FaqDTO> call = service.insertFaq(et_faq_title.getText().toString(),et_faq_content.getText().toString(),MemNo);

        Log.d("URL_TEST", call.request().url().toString());
        try {
            call.enqueue(new Callback<FaqDTO>() {
                @Override
                public void onResponse(Call<FaqDTO> call, Response<FaqDTO> response) {
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
                public void onFailure(Call<FaqDTO> call, Throwable t) {
                    Log.d("RETROFIT_TEST", t.getLocalizedMessage());

                }
            });
        } catch (Exception exception) {
            Log.d("ERROR_M", exception.getMessage());
        }
    }
}
