package com.example.libraryproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Board extends AppCompatActivity {

    String USER_SESSION_KEY = "USER_INFO";
    String MFlag;
    String MemNo;
    Button btnboardwrite;
    TextView board_title;
    TextView book_name;
    TextView mem_name;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);
        //버튼
        sharedPreferences = this.getSharedPreferences(USER_SESSION_KEY, this.MODE_PRIVATE);
        MFlag = sharedPreferences.getString("MemFlag", "");
        btnboardwrite = (Button) findViewById(R.id.btnboardwrite);
      //  Log.d("MFLAG_TEST", MFlag);
        if (MFlag.equals("1")) {
            btnboardwrite.setVisibility(View.GONE);
            Log.d("BUTTON_TEST_ADMIN", MFlag);
        } else {
            btnboardwrite.setVisibility(View.VISIBLE);
            Log.d("BUTTON_TEST_MEM", MFlag);
        }


        ListView listview;
        final BaordAdopter adapter;
        adapter = new BaordAdopter();
        listview = (ListView) findViewById(R.id.faqList);

        final RetrofitService service = new RetrofitCom().retrofit.create(RetrofitService.class);
        Call<List<BoardDTO>> call = service.getboard();
     //   Log.d("URL_TEST", call.request().url().toString());
        call.enqueue(new Callback<List<BoardDTO>>() {
            @Override
            public void onResponse(Call<List<BoardDTO>> call, Response<List<BoardDTO>> response) {
                if (response.isSuccessful()) {
                    // List<FaqDTO> contributors = response.body();
                    // 받아온 리스트를 순회하면서
                    for (BoardDTO board_row : response.body()) {
                        adapter.addItem(board_row.getBoardNo().toString(),board_row.getBoardTitle().toString(), board_row.getBoardBookTitle().toString(),board_row.getMemName().toString(), board_row.getMemNo().toString());
                        adapter.notifyDataSetChanged();
                        // adapter.getItem(0).toString();
                    }
                    Log.d("RETROFIT_SUC", response.body().toString());
//                    }
                  //  boardNo = response.body().getBoardNo().toString;
                } else {
                    Log.d("RETROFIT_FAIL", response.errorBody().toString());
                }
            }
            @Override
            public void onFailure(Call<List<BoardDTO>> call, Throwable t) {
                Log.d("RETROFIT_TEST", t.getLocalizedMessage());
            }
        });
        listview.setAdapter(adapter);

        //도서후기 읽기 화면
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                BoardDTO item = (BoardDTO) parent.getItemAtPosition(position);
                RetrofitService iservice = new RetrofitCom().retrofit.create(RetrofitService.class);
                Call<BoardDTO> call = iservice.readBoard(item.getBoardNo().toString());
               Log.d("URL_TEST", call.request().url().toString());
                call.enqueue(new Callback<BoardDTO>() {
                    @Override
                    public void onResponse(Call<BoardDTO> call, Response<BoardDTO> response) {
                        if (response.isSuccessful()) {
                            if (response.body().getRESULT_CODE().equals("Success")) {
                                Intent Rintent = new Intent(getApplicationContext(), BoardRead.class);
                                Rintent.putExtra("BOARD_NO", response.body().getBoardNo());
                                Rintent.putExtra("MEM_NO",response.body().getMemNo());
                                Log.d("TEST_MEMNO", response.body().getMemNo().toString());
                                startActivityForResult(Rintent, 0);
                                //               Log.d("FAQ_NO", response.body().getFaqNo().toString());
//                    }
                            } else {
                                Log.d("RETROFIT_FAIL", response.errorBody().toString());
                            }

                        }
                    }
                    @Override
                    public void onFailure (Call<BoardDTO> call, Throwable t) {
                        Log.d("RETROFIT_TEST", t.getLocalizedMessage());
                    }
                });
            };

        });
    }

    public class BaordAdopter extends BaseAdapter {
        private ArrayList<BoardDTO> boardDTOArrayList = new ArrayList<BoardDTO>();

        //hopeadapter 생성자
        public BaordAdopter() {
        }
        // adapter에 사용되는 데이터의 개수 리턴
        @Override
        public int getCount() {
            return boardDTOArrayList.size();
        }

        //position에 위치한 데이터를 화면서 출력하는데 사용될 view를 리턴
        @Override
        public Object getItem(int position) {
            return boardDTOArrayList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            final Context context = parent.getContext();

            // "listview_item" Layout을 inflate하여 convertView 참조 획득.
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.activity_board_list, parent, false);
            }
            TextView board_title = (TextView) convertView.findViewById(R.id.board_title);
            TextView book_name = (TextView) convertView.findViewById(R.id.book_name);
            TextView mem_name = (TextView) convertView.findViewById(R.id.mem_name);

            BoardDTO boardDTO = boardDTOArrayList.get(position);
            board_title.setText(boardDTO.getBoardTitle());
            book_name.setText(boardDTO.getBoardBookTitle());
            mem_name.setText(boardDTO.getMemName());

            return convertView;
        }

        public void addItem(String boardNo, String boardtitle, String bookname ,String memname, String memNo) {
            BoardDTO item = new BoardDTO();
            item.setBoardNo(boardNo);
            item.setBoardTitle(boardtitle);
            item.setBoardBookTitle(bookname);
            item.setMemName(memname);
            item.setMemNo(memNo);
            boardDTOArrayList.add(item);
        }
    }


    public void btnboardwrite(View v){
        Intent Lintent = new Intent(getApplicationContext(),BoardWrite.class);
        startActivity(Lintent);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 0) {
            this.recreate();
        }
    }
}
