package com.example.libraryproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchBook extends AppCompatActivity {

    TextView book_title;
    TextView book_author;
    TextView book_publish;
    TextView book_no;
    TextView book_flag;
    Intent intent;
    private String search;
    String log;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_book);


        ListView listview;
        final SearchBookAdapter adapter;
        adapter = new SearchBookAdapter();
        listview = (ListView) findViewById(R.id.searchBook);
        intent = getIntent();
        search = intent.getStringExtra("search");
        final RetrofitService service = new RetrofitCom().retrofit.create(RetrofitService.class);
        Call<List<BookDTO>> call = service.searchBook(search);
        Log.d("URL_TEST", call.request().url().toString());
        call.enqueue(new Callback<List<BookDTO>>() {
            @Override
            public void onResponse(Call<List<BookDTO>> call, Response<List<BookDTO>> response) {
                if (response.isSuccessful()) {
                    for (BookDTO book_row : response.body()) {
                        adapter.addItem(book_row.getBookTitle().toString(), book_row.getBookAuthor().toString(),
                                book_row.getBookPublish().toString(), book_row.getBookNo().toString(), book_row.getBookFlag().toString(), book_row.getRESULT_CODE().toString());
                        adapter.notifyDataSetChanged();
                    }
                    Log.d("RETROFIT_SUC", response.body().toString());
//                    if(response.body().get(0))
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

    public class SearchBookAdapter extends BaseAdapter {
        private ArrayList<BookDTO> bookDTOArrayList = new ArrayList<BookDTO>();

        //hopeadapter 생성자
        public SearchBookAdapter() {
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
                convertView = inflater.inflate(R.layout.activity_search_book_list, parent, false);
            }

            book_title = (TextView) convertView.findViewById(R.id.book_title);
            book_author = (TextView) convertView.findViewById(R.id.book_author);
            book_publish = (TextView) convertView.findViewById(R.id.book_publish);
            book_no = (TextView) convertView.findViewById(R.id.book_no);
            book_flag = (TextView) convertView.findViewById(R.id.book_flag);

            BookDTO bookDTO = bookDTOArrayList.get(position);


            if(bookDTO.getRESULT_CODE().equals("Success")) {
                book_title.setText(bookDTO.getBookTitle());
                book_author.setText(bookDTO.getBookAuthor());
                book_publish.setText(bookDTO.getBookPublish());
                book_no.setText(bookDTO.getBookNo());

                // flag값 처리
                if (bookDTO.getBookFlag().equals("1")) {
                    book_flag.setText("대출중");
                } else if (bookDTO.getBookFlag().equals("0")) {
                    book_flag.setText("대출가능");
                } else {
                    book_flag.setText("예약");
                }
                Log.d("SEARCH_TESTT", bookDTO.getRESULT_CODE().toString());
            }else{
                Toast.makeText(getApplicationContext(),"도서정보가 없습니다", Toast.LENGTH_SHORT).show();
                Log.d("SEARCH_TESTT5", bookDTO.getRESULT_CODE().toString());
            }
            return convertView;
        }

        public void addItem(String booktitle, String bookAuthor, String bookPublish, String bookNo, String bookFlag, String code) {
            BookDTO item = new BookDTO();
            item.setBookTitle(booktitle);
            item.setBookAuthor(bookAuthor);
            item.setBookPublish(bookPublish);
            item.setBookNo(bookNo);
            item.setBookFlag(bookFlag);
            item.setRESULT_CODE(code);
            bookDTOArrayList.add(item);
        }
    }

}

