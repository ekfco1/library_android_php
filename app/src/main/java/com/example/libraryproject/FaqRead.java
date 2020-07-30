package com.example.libraryproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FaqRead extends AppCompatActivity {

    String USER_SESSION_KEY = "USER_INFO";
    SharedPreferences sharedPreferences;
    String FAQ_SESSION_KEY = "FAQ_INFO";
    private String MemNo;
    private String MFlag;
    private String FNo;
    Button btn_faq_d;
    Button btn_faq_up;
    TextView faqtitle;
    TextView faqcontent;
    Intent RIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq_read);

        RIntent = getIntent();
        FNo = RIntent.getStringExtra("FAQ_NO");
        Log.d("FAQ_NO", FNo.toString());
        faqtitle = (TextView)findViewById(R.id.faqtitle);
        faqcontent = (TextView) findViewById(R.id.faqcontent);
        sharedPreferences = this.getSharedPreferences(USER_SESSION_KEY, this.MODE_PRIVATE);
        MFlag = sharedPreferences.getString("MemFlag", "");
        MemNo = sharedPreferences.getString("MemNo", "");
        btn_faq_d = (Button) findViewById(R.id.btn_faq_d);
        btn_faq_up = (Button) findViewById(R.id.btn_faq_up);
        Log.d("MFLAG_TEST", MFlag);
        if (MFlag.equals("1")) {
            btn_faq_d.setVisibility(View.VISIBLE);
            btn_faq_up.setVisibility(View.VISIBLE);
            Log.d("BUTTON_TEST_ADMIN", MFlag);
        } else {
            btn_faq_d.setVisibility(View.GONE);
            btn_faq_up.setVisibility(View.GONE);
            Log.d("BUTTON_TEST_MEM", MFlag);
        }
        String FAQ_SESSION_KEY = "FAQ_INFO";
        SharedPreferences sharedPreferences = this.getSharedPreferences(FAQ_SESSION_KEY, this.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
                RetrofitService iservice = new RetrofitCom().retrofit.create(RetrofitService.class);
                Call<FaqDTO> call = iservice.readFaq(FNo);
                Log.d("URL_TEST", call.request().url().toString());
                call.enqueue(new Callback<FaqDTO>() {
                    @Override
                    public void onResponse(Call<FaqDTO> call, Response<FaqDTO> response) {
                        if (response.isSuccessful()) {
                                faqtitle.setText(response.body().getFaqTitle());
                                faqcontent.setText(response.body().getFaqContent());
                                Log.d("FAQFLAG",response.body().getFaqFlag().toString());
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

    public void btnfaqd(View v){
        RetrofitService iservice = new RetrofitCom().retrofit.create(RetrofitService.class);
        Call<FaqDTO> call = iservice.deleteFaq(FNo);
        Log.d("URL_TEST", call.request().url().toString());
        call.enqueue(new Callback<FaqDTO>() {
            @Override
            public void onResponse(Call<FaqDTO> call, Response<FaqDTO> response) {
                if (response.isSuccessful()) {
                    finish();
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
    public void btnfaqup(View v){
        Intent intent = new Intent(getApplicationContext(),FaqUpdate.class);
        intent.putExtra("FAQ_NO",FNo);
        startActivity(intent);
    }
}
