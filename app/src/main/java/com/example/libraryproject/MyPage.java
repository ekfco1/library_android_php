package com.example.libraryproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyPage extends AppCompatActivity {

    TextView book_title;
    TextView book_rent;
    Button btndelay;

    String rentdate;// = new SimpleDateFormat("yyyy-MM-dd").format();

    //    SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
    SharedPreferences sharedPreferences;
    private String MemNo;
    String USER_SESSION_KEY = "USER_INFO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page);

        ListView listview;
        final MyPageAdapter adapter;
        adapter = new MyPageAdapter();
        listview = (ListView) findViewById(R.id.myPage);
        sharedPreferences = this.getSharedPreferences(USER_SESSION_KEY, MODE_PRIVATE);
        MemNo = sharedPreferences.getString("MemNo", "");
        Log.d("MYPAGE_MEMNO", MemNo);
        final RetrofitService service = new RetrofitCom().retrofit.create(RetrofitService.class);
        Call<List<BookDTO>> call = service.myPage(MemNo);
        Log.d("URL_TEST", call.request().url().toString());
        call.enqueue(new Callback<List<BookDTO>>() {
            @Override
            public void onResponse(Call<List<BookDTO>> call, Response<List<BookDTO>> response) {
                if (response.isSuccessful()) {
                    for (BookDTO book_row : response.body()) {
                        adapter.addItem(book_row.getMemNo().toString(), book_row.getBookTitle().toString(),
                                book_row.getBRent().toString(), book_row.getBookReturn().toString(), book_row.getBookNo().toString());
                        adapter.notifyDataSetChanged();

                    }
                    Log.d("RETROFIT_SUC", response.body().toString());
                    //                    }
                    //  boardNo = response.body().getBoardNo().toString;
                } else {
                    Log.d("RETROFIT_FAIL", response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<List<BookDTO>> call, Throwable t) {
                Log.d("RETROFIT_TEST", t.getLocalizedMessage());
            }
        });
        listview.setAdapter(adapter);


    }

    public class MyPageAdapter extends BaseAdapter {
        private ArrayList<BookDTO> bookDTOArrayList = new ArrayList<BookDTO>();
        //hopeadapter 생성자
        public MyPageAdapter() {
        }

        // adapter에 사용되는 데이터의 개수 리턴
        @Override
        public int getCount() {
            return bookDTOArrayList.size();
        }

        //position에 위치한 데이터를 화면서 출력하는데 사용될 view를 리턴
        @Override
        public Object getItem(int position) {
            return bookDTOArrayList.get(position);
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
                convertView = inflater.inflate(R.layout.activity_my_page_list, parent, false);
            }


            book_title = (TextView) convertView.findViewById(R.id.book_title);
            book_rent = (TextView) convertView.findViewById(R.id.book_rent);
            btndelay = (Button) convertView.findViewById(R.id.delay);

            final BookDTO bookDTO = bookDTOArrayList.get(position);
            book_title.setText(bookDTO.getBookTitle());
            book_rent.setText(bookDTO.getBRent() + " ~ " + bookDTO.getBookReturn());

            btndelay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final RetrofitService service = new RetrofitCom().retrofit.create(RetrofitService.class);
                    Call<BookDTO> call = service.delay(bookDTO.getMemNo(),bookDTO.getBookNo(),bookDTO.getBookReturn(),bookDTO.getExtendFlag());
                    Log.d("URL_TEST", call.request().url().toString());
                    call.enqueue(new Callback<BookDTO>() {
                        @Override
                        public void onResponse(Call<BookDTO> call, Response<BookDTO> response) {
                            if (response.isSuccessful()) {
                                if (response.body().getRESULT_CODE().equals("Success")) {
                                    Toast.makeText(getApplicationContext(),"1주일 연장되었습니다.",Toast.LENGTH_SHORT).show();
                                    finish();
                                    Intent Rintent = new Intent(getApplicationContext(), MyPage.class);
                                    startActivityForResult(Rintent, 0);
                                }else if (response.body().getRESULT_CODE().equals("Exist")) {
                                    Toast.makeText(getApplicationContext(),"연장이 불가합니다.",Toast.LENGTH_SHORT).show();
                                }
                            } else{
                         }
                    }
                        @Override
                        public void onFailure(Call<BookDTO> call, Throwable t) {
                            Log.d("RETROFIT_TEST", t.getLocalizedMessage());
                        }
                    });
                }
            });
            return convertView;
        }

        public void addItem(String memNo, String booktitle, String bookRent, String bookReturn, String bookNo) {
            BookDTO item = new BookDTO();
            item.setMemNo(memNo);
            item.setBookTitle(booktitle);
            item.setBRent(bookRent);
            item.setBookReturn(bookReturn);
            item.setBookNo(bookNo);
            bookDTOArrayList.add(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
            if (resultCode == 0) {
                this.recreate();
             }
        }
    }

