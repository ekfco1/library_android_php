package com.example.libraryproject;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WorkBook extends AppCompatActivity {

    TextView mem_no;
    TextView book_title;
    TextView book_author;
    TextView book_publish;
    TextView book_no;
    TextView book_rent;
    TextView book_flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_book);


        ListView listview;
        final WorkBookAdapter adapter;
        adapter = new WorkBookAdapter();
        listview = (ListView) findViewById(R.id.bookList);

        final RetrofitService service = new RetrofitCom().retrofit.create(RetrofitService.class);
        Call<List<BookDTO>> call = service.workBook();
          Log.d("URL_TEST", call.request().url().toString());
        call.enqueue(new Callback<List<BookDTO>>() {
            @Override
            public void onResponse(Call<List<BookDTO>> call, Response<List<BookDTO>> response) {
                if (response.isSuccessful()) {
                    for (BookDTO book_row : response.body()) {
                        if (response.body() == null) {
                            Log.v ( "RESPONSE_BODY", "RESPONSE_BODY_IS_NULL");
                        }
                        adapter.addItem(book_row.getMemNo().toString(),book_row.getBookTitle().toString(), book_row.getBookAuthor().toString(),
                                book_row.getBookPublish().toString(), book_row.getBookNo().toString(),
                                book_row.getBRent().toString(),
                                book_row.getBookReturn().toString(),
                                book_row.getBookFlag().toString());
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

    public class WorkBookAdapter extends BaseAdapter {
        private ArrayList<BookDTO> bookDTOArrayList = new ArrayList<BookDTO>();

        //hopeadapter 생성자
        public WorkBookAdapter() {
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
                convertView = inflater.inflate(R.layout.activity_work_book_list, parent, false);
            }

            mem_no = (TextView) convertView.findViewById(R.id.mem_no);
            book_title = (TextView) convertView.findViewById(R.id.book_title);
            book_author = (TextView) convertView.findViewById(R.id.book_author);
            book_publish = (TextView) convertView.findViewById(R.id.book_publish);
            book_no = (TextView) convertView.findViewById(R.id.book_no);
            book_rent = (TextView) convertView.findViewById(R.id.book_rent);
            book_flag = (TextView) convertView.findViewById(R.id.book_flag);

            BookDTO bookDTO = bookDTOArrayList.get(position);
            mem_no.setText(bookDTO.getMemNo());
            book_title.setText(bookDTO.getBookTitle());
            book_author.setText(bookDTO.getBookAuthor());
            book_publish.setText(bookDTO.getBookPublish());
            book_no.setText(bookDTO.getBookNo());

            // flag값 처리
            if (bookDTO.getBookFlag().equals("1")) {
                mem_no.setVisibility(View.VISIBLE);
                book_rent.setVisibility(View.VISIBLE);
                book_flag.setText("대출중");
                book_rent.setText(bookDTO.getBRent() + " ~ " + bookDTO.getBookReturn());
            } else if (bookDTO.getBookFlag().equals("0")) {
                mem_no.setVisibility(View.GONE);
                book_rent.setVisibility(View.GONE);
                book_flag.setText("대출가능");
            }else{
                mem_no.setVisibility(View.VISIBLE);
                book_rent.setVisibility(View.VISIBLE);
                book_rent.setText(bookDTO.getBRent() + " ~ " + bookDTO.getBookReturn());
                book_flag.setText("연체");
            }
            return convertView;
        }

        public void addItem(String memNo, String booktitle, String bookAuthor, String bookPublish, String bookNo,String bookRent,String bookReturn,String bookFlag) {
            BookDTO item = new BookDTO();
            item.setMemNo(memNo);
            item.setBookTitle(booktitle);
            item.setBookAuthor(bookAuthor);
            item.setBookPublish(bookPublish);
            item.setBookNo(bookNo);
            item.setBRent(bookRent);
            item.setBookReturn(bookReturn);
            item.setBookFlag(bookFlag);
            bookDTOArrayList.add(item);
        }
    }

}
