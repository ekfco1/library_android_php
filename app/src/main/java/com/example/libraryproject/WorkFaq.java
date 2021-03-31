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

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WorkFaq extends AppCompatActivity {

    TextView faqno;
    TextView faq;
    String USER_SESSION_KEY = "USER_INFO";
    String FAQ_SESSION_KEY = "FAQ_INFO";
    SharedPreferences sharedPreferences;
    private String MFlag;
    private String MemNo;
    Button btnfaqwrite;
    private String FNo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_faq);
        //버튼
        sharedPreferences = this.getSharedPreferences(USER_SESSION_KEY, this.MODE_PRIVATE);
        MFlag = sharedPreferences.getString("MemFlag", "");
        MemNo = sharedPreferences.getString("MemNo", "");
        btnfaqwrite = (Button) findViewById(R.id.btnfaqwrite);
    //    Log.d("MFLAG_TEST", MFlag);
        if (MFlag.equals("1")) {
            btnfaqwrite.setVisibility(View.VISIBLE);
//            Log.d("BUTTON_TEST_ADMIN", MFlag);
        } else {
            btnfaqwrite.setVisibility(View.GONE);
      //      Log.d("BUTTON_TEST_MEM", MFlag);
        }


        ListView listview;
        final FaqAdapter adapter;
        adapter = new FaqAdapter();
        listview = (ListView) findViewById(R.id.faqList);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                FaqDTO item = (FaqDTO) parent.getItemAtPosition(position);
                RetrofitService iservice = new RetrofitCom().retrofit.create(RetrofitService.class);
                Call<FaqDTO> call = iservice.readFaq(item.getFaqNo().toString());
                Log.d("URL_TEST", call.request().url().toString());
                call.enqueue(new Callback<FaqDTO>() {
                    @Override
                    public void onResponse(Call<FaqDTO> call, Response<FaqDTO> response) {
                        if (response.isSuccessful()) {
                            if (response.body().getRESULT_CODE().equals("Success")) {
                                Intent Rintent = new Intent(getApplicationContext(), FaqRead.class);
                                Rintent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                Rintent.putExtra("FAQ_NO", response.body().getFaqNo());
                                // startActivity(Rintent);
                                startActivityForResult(Rintent, 0);
                            } else {
                                Log.d("RETROFIT_FAIL", response.errorBody().toString());
                            }
                        }
                    }
                        @Override
                        public void onFailure (Call<FaqDTO> call, Throwable t) {
                            Log.d("RETROFIT_TEST", t.getLocalizedMessage());
                        }
                    });
                };

        });

        RetrofitService service = new RetrofitCom().retrofit.create(RetrofitService.class);
        Call<List<FaqDTO>> call = service.getFaq();
        Log.d("URL_TEST", call.request().url().toString());
        call.enqueue(new Callback<List<FaqDTO>>() {
            @Override
            public void onResponse(Call<List<FaqDTO>> call, Response<List<FaqDTO>> response) {
                if (response.isSuccessful()) {
                    // List<FaqDTO> contributors = response.body();
                    // 받아온 리스트를 순회하면서
                    for (FaqDTO FAQ_row : response.body()) {
//                        // 텍스트 뷰에 login 정보를 붙임
                        adapter.addItem(FAQ_row.getFaqNo().toString(), FAQ_row.getFaqTitle().toString());
                        adapter.notifyDataSetChanged();
                    }
                    Log.d("RETROFIT_SUC", response.body().toString());
//                    }
                } else {
                    Log.d("RETROFIT_FAIL", response.errorBody().toString());
                }
            }
            @Override
            public void onFailure(Call<List<FaqDTO>> call, Throwable t) {
                Log.d("RETROFIT_TEST", t.getLocalizedMessage());
            }
        });
        listview.setAdapter(adapter);
    }

    public class FaqAdapter extends BaseAdapter {
        private ArrayList<FaqDTO> faqDTOArrayList = new ArrayList<FaqDTO>();

        //hopeadapter 생성자
        public FaqAdapter() {
        }
        // adapter에 사용되는 데이터의 개수 리턴
        @Override
        public int getCount() {
            return faqDTOArrayList.size();
        }

        //position에 위치한 데이터를 화면서 출력하는데 사용될 view를 리턴
        @Override
        public Object getItem(int position) {
            return faqDTOArrayList.get(position);
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
                convertView = inflater.inflate(R.layout.activity_faq_list, parent, false);
            }
            TextView faq = (TextView) convertView.findViewById(R.id.faq);
            TextView faqno = (TextView) convertView.findViewById(R.id.faqno);

            FaqDTO faqDTO = faqDTOArrayList.get(position);
            faqno.setText(faqDTO.getFaqNo());
            faq.setText(faqDTO.getFaqTitle());

            return convertView;
        }

        public void addItem(String faqno, String faqtitle) {
            FaqDTO item = new FaqDTO();
            item.setFaqNo(faqno);
            item.setFaqTitle(faqtitle);
            faqDTOArrayList.add(item);
        }
    }

    public void btnfaqwrite(View v){
        Intent Lintent = new Intent(getApplicationContext(),FaqWrite.class);
        startActivity(Lintent);
    }

}