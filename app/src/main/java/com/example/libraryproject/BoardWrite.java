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

public class BoardWrite extends AppCompatActivity {

    Button btn_board_c;
    Button btn_board_write;
    EditText et_board_title;
    EditText et_board_book_title;
    EditText et_board_content;
    private String MemNo;
    String USER_SESSION_KEY = "USER_INFO";
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_write);
        sharedPreferences = this.getSharedPreferences(USER_SESSION_KEY, this.MODE_PRIVATE);
        MemNo = sharedPreferences.getString("MemNo", "");
        btn_board_c = (Button) findViewById(R.id.btn_board_c);
        btn_board_write = (Button) findViewById(R.id.btn_board_write);
        et_board_book_title = (EditText) findViewById(R.id.et_board_book_title);
        et_board_content = (EditText) findViewById(R.id.et_board_content);
        et_board_title = (EditText) findViewById(R.id.et_board_title);
    }
    public void btn_board_c(View v){
        Intent intent = new Intent(getApplicationContext(),MemMain.class);
        startActivity(intent);
    }
    public void btn_board_write(View v){
        if(et_board_title.getText().length() == 0){
            et_board_title.setError("제목을 입력하세요");
            return; //
        }
        if(et_board_book_title.getText().length() == 0){
            et_board_book_title.setError("제목을 입력하세요");
            return; //
        }
        if(et_board_content.getText().length() == 0){
            et_board_content.setError("내용을 입력하세요");
            return; //
        }
        final RetrofitService service = new RetrofitCom().retrofit.create(RetrofitService.class);
        Call<BoardDTO> call = service.insertboard(et_board_title.getText().toString(),et_board_book_title.getText().toString()
                ,et_board_content.getText().toString(),MemNo);
        Log.d("URL_TEST", call.request().url().toString());
        try {
            call.enqueue(new Callback<BoardDTO>() {
                @Override
                public void onResponse(Call<BoardDTO> call, Response<BoardDTO> response) {
                    if (response.isSuccessful() ) {
                        Log.d("RETROFIT_SUC", response.body().toString());
                        Intent intent = new Intent(getApplicationContext(),BoardRead.class);
                        intent.putExtra("MEM_NO",MemNo);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                    else {
                        Log.d("RETROFIT_FAIL", response.errorBody().toString());
                    }
                }
                @Override
                public void onFailure(Call<BoardDTO> call, Throwable t) {
                    Log.d("RETROFIT_TEST", t.getLocalizedMessage());

                }
            });
        } catch (Exception exception) {
            Log.d("ERROR_M", exception.getMessage());
        }

    }
}
