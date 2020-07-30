package com.example.libraryproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;

public class MemMain extends AppCompatActivity {
    String USER_SESSION_KEY = "USER_INFO";
    SearchView searchView;
    String search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mem_main);

        searchView = findViewById(R.id.search);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //검색 버튼이 눌러졌을 때 이벤트 처리
                //Toast.makeText(MemMain.this, "검색 처리됨 : " + query, Toast.LENGTH_SHORT).show();
                search = searchView.getQuery().toString();
                Log.d("SEARCH_VAL", search.toString());
                Intent Lintent = new Intent(getApplicationContext(),SearchBook.class);
                Lintent.putExtra("search",search);
                startActivity(Lintent);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //검색어가 변경되었을 때 이벤트 처리
                return false;
            }
        });
    }

    public void btnidcard(View v){
        //TODO 대출증
        Intent Lintent = new Intent(getApplicationContext(),MemBarcode.class);
        Lintent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(Lintent);
    }
    public void btnmypage(View v){
        Intent Lintent = new Intent(getApplicationContext(),MyPage.class);
        Lintent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(Lintent);
    }
    public void btnmfaq(View v){
        Intent Lintent = new Intent(getApplicationContext(),WorkFaq.class);
        Lintent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(Lintent);
    }
    public void btnmhopebook(View v){
        Intent Lintent = new Intent(getApplicationContext(),MemHopeBook.class);
        Lintent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(Lintent);
    }
    public void btnmboard(View v){
        Intent Lintent = new Intent(getApplicationContext(),Board.class);
        Lintent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(Lintent);
    }

}
