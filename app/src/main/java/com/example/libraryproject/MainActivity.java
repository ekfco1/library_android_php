package com.example.libraryproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    EditText et_mem_id;
    EditText et_mem_pw;
//    private String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_mem_id = (EditText) findViewById(R.id.et_mem_id) ;
        et_mem_pw = (EditText)findViewById(R.id.et_mem_pw);
    };


    //boolean et_mem_id = jsonObject.getBoolean( "success" );
    public void btnLogin(View v){

        if(et_mem_id.getText().toString().equals("")){
            et_mem_id.setError("아이디을 입력하세요.");
            return;
        }
        if(et_mem_pw.getText().toString().equals("")){
            et_mem_pw.setError("비밀번호를 입력하세요.");
            return;
        }
        final RetrofitService service = new RetrofitCom().retrofit.create(RetrofitService.class);
        Call<MemDTO> call = service.getlogin(et_mem_id.getText().toString(),et_mem_pw.getText().toString());
        String USER_SESSION_KEY = "USER_INFO";
        SharedPreferences sharedPreferences = this.getSharedPreferences(USER_SESSION_KEY, this.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        try {
            Log.d("URL_TEST", call.request().url().toString());
            call.enqueue(new Callback<MemDTO>() {
                @Override
                public void onResponse(Call<MemDTO> call, Response<MemDTO> response) {
                    if (response.isSuccessful() ) {
                        MemDTO result = response.body();
                        Log.d("LOGIN_RESULT", response.body().getRESULT_CODE());
                        if (response.body().getRESULT_CODE().equals("Success")) {
                            Log.d("PHP_MEM_NO", String.valueOf(response.body().getMemNo()));
                            editor.putString("MemNo", String.valueOf(response.body().getMemNo()));
                            editor.putString("MemFlag", String.valueOf(response.body().getMemFlag()));
                            editor.apply();
                            Log.d("LOGIN_RESULT", String.valueOf(response.body().getMemNo()));
                            // 로그인 성공할 경우
                            if (response.body().getMemFlag().equals("1")) {
                                // 관리자
                                Intent Rintent = new Intent(getApplicationContext(), WorkMain.class);
                                startActivity(Rintent);
                            } else {
                                Intent Rintent = new Intent(getApplicationContext(), MemMain.class);
                                startActivity(Rintent);
                            }
                        } else if (response.body().getRESULT_CODE().equals("Fail")) {
                            Toast.makeText(getApplicationContext(),"아이디 또는 비밀번호가 틀렸습니다.",Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(),"처리할 수 없는 오류입니다.",Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Log.d("RETROFIT_FAIL", response.errorBody().toString());
                    }
                }
                @Override
                public void onFailure(Call<MemDTO> call, Throwable t) {
                    Toast toast = Toast.makeText(getApplicationContext(),"아이디 또는 비밀번호가 잘못 되었습니다",Toast.LENGTH_SHORT);
                    toast.show();
                    Log.d("RETROFIT_TEST", t.getLocalizedMessage());
                    return;
                }
            });
        } catch (Exception exception) {
            Log.d("ERROR_M", exception.getMessage());
        }


    }
    public void btnRegister(View v){
        Intent Rintent = new Intent(getApplicationContext(), Register.class);
        startActivity(Rintent);
    }
    public void btnIdPwFind(View v){
        Intent Fintent = new Intent(getApplicationContext(), Find.class);
        startActivity(Fintent);

    }

}
