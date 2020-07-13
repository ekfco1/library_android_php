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

public class BoardUpdate extends AppCompatActivity {

    Button btn_board_c;
    Button btn_board_up;
    EditText board_title;
    EditText board_book_title;
    EditText board_content;
    private String boardNo;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_update);
        btn_board_c = (Button) findViewById(R.id.btn_board_c);
        btn_board_up = (Button) findViewById(R.id.btn_board_up);
        board_title = (EditText) findViewById(R.id.board_title);
        board_book_title = (EditText) findViewById(R.id.board_book_title) ;
        board_content = (EditText) findViewById(R.id.board_content);
        intent = getIntent();
        boardNo = intent.getStringExtra("BOARD_NO");
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
    public void btn_board_c(View v){
        Intent intent = new Intent(getApplicationContext(),Board.class);
        startActivity(intent);
    }
    public void btn_board_up(View v){
        if(board_title.getText().length() == 0){
            board_title.setError("제목을 입력하세요");
            return; //
        }
        if(board_book_title.getText().length() == 0){
            board_book_title.setError("제목을 입력하세요");
            return; //
        }
        if(board_content.getText().length() == 0){
            board_content.setError("내용을 입력하세요");
            return; //
        }
        RetrofitService iservice = new RetrofitCom().retrofit.create(RetrofitService.class);
        Call<BoardDTO> call = iservice.updateBoard(boardNo,board_title.getText().toString(), board_book_title.getText().toString(), board_content.getText().toString());
        Log.d("URL_TEST", call.request().url().toString());
        call.enqueue(new Callback<BoardDTO>() {
            @Override
            public void onResponse(Call<BoardDTO> call, Response<BoardDTO> response) {
                if (response.isSuccessful()) {
                    Log.d("RETROFIT_SUC", response.body().toString());
                    Intent intent = new Intent(getApplicationContext(),Board.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
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

}
