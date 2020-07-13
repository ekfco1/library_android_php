package com.example.libraryproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Find extends AppCompatActivity {


    EditText et_mem_name;
    EditText et_mem_phone;
    private String mToastStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find);
        et_mem_name = (EditText)findViewById(R.id.et_mem_name);
        et_mem_phone = (EditText)findViewById(R.id.et_mem_phone);
        Toast mtoast = null;
        String mToastStr;
    }
        public void btnFind(View v){

            if(et_mem_name.getText().toString().equals("")){
                et_mem_name.setError("이름을 입력하세요.");
                return;
            }
            if(et_mem_phone.getText().toString().equals("")){
                et_mem_phone.setError("전화번호를 입력하세요.");
                return;
            }
            RetrofitService service = new RetrofitCom().retrofit.create(RetrofitService.class);
            Call<MemDTO> call = service.find(et_mem_name.getText().toString(),Integer.parseInt(et_mem_phone.getText().toString()));
            Log.d("URL_TEST", call.request().url().toString());
            call.enqueue(new Callback<MemDTO>() {
                @Override
                public void onResponse(Call<MemDTO> call, Response<MemDTO> response) {
                    if (response.isSuccessful()) {
                        if(response.body().getRESULT_CODE().equals("Success")){
                            Log.d("RETROFIT_SUC", response.body().getMemPw());
                            mToastStr = "아이디 : "+ response.body().getMemId() +" 비밀번호 : " + response.body().getMemPw();
                           if(mToastStr != null){
                                Toast.makeText(getApplicationContext(),mToastStr,Toast.LENGTH_LONG).show();
                                Intent Lintent = new Intent(getApplicationContext(),MainActivity.class);
                               Lintent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(Lintent);
                           }
                    }else if (response.body().getRESULT_CODE().equals("Fail")) {
                        Toast.makeText(getApplicationContext(),"이름 또는 전화번호가 잘못되었습니다.",Toast.LENGTH_SHORT).show();
                        Log.d("RETROFIT_SUC", response.body().getRESULT_CODE());
                    } else {
                            Log.d("RETROFIT_SUC", response.body().getRESULT_CODE());
                        Toast.makeText(getApplicationContext(),"처리할 수 없는 오류입니다.",Toast.LENGTH_SHORT).show();//이거 떠요 이거 떠오오오~? 귀여오오오오오오
                    }
                        MemDTO result = response.body();
                    } else {
                        Log.d("RETROFIT_FAIL", response.errorBody().toString());
                    }

                }

                @Override
                public void onFailure(Call<MemDTO> call, Throwable t) {
                    Log.d("RETROFIT_TEST", t.getLocalizedMessage());

                }
            });
    }
//    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
//    startActivity(intent);
}
