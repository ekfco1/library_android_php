package com.example.libraryproject;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HopeBook extends AppCompatActivity {


    TextView hbooktitle;
    TextView hauthor;
    TextView hpublish;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hope_book);

        ListView listview;
        final HopeAdapter adapter;
        adapter = new HopeAdapter();

        listview = (ListView) findViewById(R.id.hopelist);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                HBookDTO item = (HBookDTO) parent.getItemAtPosition(position);
                Log.d("LIST_TEST", item.getHbookPublish().toString());
            }
        });

        RetrofitService service = new RetrofitCom().retrofit.create(RetrofitService.class);
        Call<List<HBookDTO>> call = service.gethbook();
        Log.d("URL_TEST", call.request().url().toString());
        call.enqueue(new Callback<List<HBookDTO>>() {
            @Override
            public void onResponse(Call<List<HBookDTO>> call, Response<List<HBookDTO>> response) {
                if (response.isSuccessful()) {
                    List<HBookDTO> contributors = response.body();
                    // 받아온 리스트를 순회하면서
                    for (HBookDTO contributor : contributors) {
//                        // 텍스트 뷰에 login 정보를 붙임
                        adapter.addItem(contributor.getHbookTitle().toString(), contributor.getHbookAuthor().toString(), contributor.getHbookPublish().toString());
                        adapter.notifyDataSetChanged();
                    }
                        Log.d("RETROFIT_SUC", response.body().toString());
//                    }
                } else {
                    Log.d("RETROFIT_FAIL", response.errorBody().toString());
                }

            }

            @Override
            public void onFailure(Call<List<HBookDTO>> call, Throwable t) {
                Log.d("RETROFIT_TEST", t.getLocalizedMessage());

            }
        });
        listview.setAdapter(adapter);
//
    }

    public class HopeAdapter extends BaseAdapter {
        private ArrayList<HBookDTO> hBookDTOArrayList = new ArrayList<HBookDTO>();
        //hopeadapter 생성자
        public HopeAdapter() {

        }

        // adapter에 사용되는 데이터의 개수 리턴
        @Override
        public int getCount() {
            return hBookDTOArrayList.size();
        }

        //position에 위치한 데이터를 화면서 출력하는데 사용될 view를 리턴
        @Override
        public Object getItem(int position) {
            return hBookDTOArrayList.get(position);
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
                convertView = inflater.inflate(R.layout.activity_hope_list, parent, false);
            }
            TextView hbooktitle = (TextView) convertView.findViewById(R.id.hbooktitle);
            TextView haurthor = (TextView) convertView.findViewById(R.id.haurthor);
            TextView hpublish = (TextView) convertView.findViewById(R.id.hpublish);

            HBookDTO hBookDTO = hBookDTOArrayList.get(position);
            hbooktitle.setText(hBookDTO.getHbookTitle());
            haurthor.setText(hBookDTO.getHbookAuthor());
            hpublish.setText(hBookDTO.getHbookPublish());

            return convertView;
        }

        public void addItem(String htitle, String hauthor, String hpublish) {
            HBookDTO item = new HBookDTO();
            item.setHbookTitle(htitle);
            item.setHbookAuthor(hauthor);
            item.setHbookPublish(hpublish);
            hBookDTOArrayList.add(item);
        }
    }
}
