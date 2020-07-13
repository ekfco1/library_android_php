package com.example.libraryproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FaqUpdate extends AppCompatActivity {

    Button btn_faq_c;
    Button btn_faq_up;
    EditText et_faq_title;
    EditText et_faq_content;
    private String FNo;
    Intent RIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq_update);
        btn_faq_c = (Button)findViewById(R.id.btn_faq_c);
        btn_faq_up = (Button)findViewById(R.id.btn_faq_up);
        et_faq_title = (EditText)findViewById(R.id.et_faq_title);
        et_faq_content = (EditText)findViewById(R.id.et_faq_content);

        RIntent = getIntent();
        FNo = RIntent.getStringExtra("FAQ_NO");
        Log.d("FAQ_NO", FNo.toString());
        RetrofitService iservice = new RetrofitCom().retrofit.create(RetrofitService.class);
        Call<FaqDTO> call = iservice.readFaq(FNo);
        Log.d("URL_TEST", call.request().url().toString());
        call.enqueue(new Callback<FaqDTO>() {
            @Override
            public void onResponse(Call<FaqDTO> call, Response<FaqDTO> response) {
                if (response.isSuccessful()) {
                    Log.d("TEST_FAQ", response.body().getFaqTitle().toString());
                    et_faq_title.setText(response.body().getFaqTitle());
                    et_faq_content.setText(response.body().getFaqContent());
                    Log.d("RETROFIT_SUC", response.body().toString());
                } else {
                    Log.d("RETROFIT_FAIL", response.errorBody().toString());
                }
            }

            @Override
            public void onFailure (Call<FaqDTO> call, Throwable t){
                Log.d("RETROFIT_TEST", t.getLocalizedMessage());
            }
        });
    }
    public void btn_faq_c(View v){
        Intent intent = new Intent(getApplicationContext(),WorkFaq.class);
        startActivity(intent);
    }
    public void btn_faq_up(View v){
        if(et_faq_title.getText().length() == 0){
            et_faq_title.setError("제목을 입력하세요");
            return; //
        }
        if(et_faq_content.getText().length() == 0){
            et_faq_content.setError("내용을 입력하세요");
            return; //
        }
        RetrofitService iservice = new RetrofitCom().retrofit.create(RetrofitService.class);
        Call<FaqDTO> call = iservice.updateFaq(FNo,et_faq_title.getText().toString(), et_faq_content.getText().toString());
        Log.d("URL_TEST", call.request().url().toString());
        call.enqueue(new Callback<FaqDTO>() {
            @Override
            public void onResponse(Call<FaqDTO> call, Response<FaqDTO> response) {
                if (response.isSuccessful()) {
                    Log.d("RETROFIT_SUC", response.body().toString());
                    Intent intent = new Intent(getApplicationContext(),WorkFaq.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                } else {
                    Log.d("RETROFIT_FAIL", response.errorBody().toString());
                }
            }

            @Override
            public void onFailure (Call<FaqDTO> call, Throwable t){
                Log.d("RETROFIT_TEST", t.getLocalizedMessage());
            }
        });
    }
}
