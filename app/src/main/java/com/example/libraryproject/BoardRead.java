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

public class BoardRead extends AppCompatActivity {

    String USER_SESSION_KEY = "USER_INFO";
    SharedPreferences sharedPreferences;
    TextView board_title;
    TextView board_book_title;
    TextView board_content;
    Button btn_board_up;
    Button btn_board_d;
    private String MemNo;
    private String MFlag;
    private String boardNo;
    private String BMemNo;
    Intent RIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_read);

        RIntent = getIntent();
        boardNo = RIntent.getStringExtra("BOARD_NO");
        BMemNo = RIntent.getStringExtra("MEM_NO");
//        Log.d("BOARD_NO", boardNo.toString());

        board_title = (TextView)findViewById(R.id.board_title);
        board_book_title = (TextView) findViewById(R.id.board_book_title);
        board_content = (TextView)findViewById(R.id.board_content);
        sharedPreferences = this.getSharedPreferences(USER_SESSION_KEY, this.MODE_PRIVATE);
        MFlag = sharedPreferences.getString("MemFlag", "");
        MemNo = sharedPreferences.getString("MemNo", "");
        btn_board_up = (Button) findViewById(R.id.btn_board_up);
        btn_board_d = (Button) findViewById(R.id.btn_board_d);
        Log.d("MEMNO", MemNo.toString());
        Log.d("BMemNo", boardNo.toString());
        if (MFlag.equals("1")) {
            btn_board_d.setVisibility(View.VISIBLE);
            Log.d("BUTTON_TEST_ADMIN", MFlag);
        } else if(MemNo.equals(BMemNo) && MFlag.equals("0")){
            btn_board_d.setVisibility(View.VISIBLE);
            btn_board_up.setVisibility(View.VISIBLE);
            Log.d("BUTTON_TEST_MEMNO", MemNo);
//            Log.d("BUTTON_TEST_MEMNO", BMemNo);
        }else {
            btn_board_d.setVisibility(View.GONE);
            btn_board_up.setVisibility(View.GONE);
            Log.d("BUTTON_TEST_MEM", MFlag);
        }
        RetrofitService iservice = new RetrofitCom().retrofit.create(RetrofitService.class);
        Call<BoardDTO> call = iservice.readBoard(boardNo);
        Log.d("URL_TEST", call.request().url().toString());
        call.enqueue(new Callback<BoardDTO>() {
            @Override
            public void onResponse(Call<BoardDTO> call, Response<BoardDTO> response) {
                if (response.isSuccessful()) {
                    board_title.setText(response.body().getBoardTitle());
                    board_book_title.setText(response.body().getBoardBookTitle());
                    board_content.setText(response.body().getBoardContent());
                    Log.d("RETROFIT_SUC", response.body().toString());

                } else {
                    Log.d("RETROFIT_FAIL", response.errorBody().toString());
                }
            }

            @Override
            public void onFailure (Call<BoardDTO> call, Throwable t){
                Log.d("RETROFIT_TEST", t.getLocalizedMessage());
            }
        });
    }
    public void btn_board_d(View v){
         RetrofitService iservice = new RetrofitCom().retrofit.create(RetrofitService.class);
        Call<BoardDTO> call = iservice.deleteBoard(boardNo);
        Log.d("URL_TEST", call.request().url().toString());
        call.enqueue(new Callback<BoardDTO>() {
            @Override
            public void onResponse(Call<BoardDTO> call, Response<BoardDTO> response) {
                if (response.isSuccessful()) {
                    finish();
                } else {
                    Log.d("RETROFIT_FAIL", response.errorBody().toString());
                }
            }

            @Override
            public void onFailure (Call<BoardDTO> call, Throwable t){
                Log.d("RETROFIT_TEST", t.getLocalizedMessage());
            }
        });
    }
    public void btn_board_up(View v){
        Intent intent = new Intent(getApplicationContext(),BoardUpdate.class);
        intent.putExtra("BOARD_NO",boardNo);
        startActivity(intent);
    }
}
