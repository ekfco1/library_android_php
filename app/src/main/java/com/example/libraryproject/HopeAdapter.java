package com.example.libraryproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class HopeAdapter extends BaseAdapter {
    private Context mContext;
    private TextView hbooktitle;
    private TextView haurthor;
    private TextView hpublish;
    //Adapter에 추가된 데이처를 저장하기 위한 arraylist
    private ArrayList<HBookDTO> hBookDTOArrayList = new ArrayList<HBookDTO>();
    //private RecyclerView.ViewHolder mViewHolder;

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

//        if (convertView == null) {
//            convertView = LayoutInflater.from(mContext).inflate(R.layout.activity_hope_book, parent, false);
//        }
        final Context context = parent.getContext();

        // "listview_item" Layout을 inflate하여 convertView 참조 획득.
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.activity_hope_book, parent, false);
        }
        TextView hbooktitle = (TextView)convertView .findViewById(R.id.hbooktitle);
        TextView haurthor = (TextView)convertView.findViewById(R.id.haurthor);
        TextView hpublish = (TextView) convertView.findViewById(R.id.hpublish);

        HBookDTO hBookDTO = hBookDTOArrayList.get(position);
        hbooktitle.setText(hBookDTO.getHbookTitle());
        haurthor.setText(hBookDTO.getHbookAuthor());
        hpublish.setText(hBookDTO.getHbookPublish());

        return convertView;
    }
    public void addItem(String hbtitle, String hauthor, String hpublish) {
        HBookDTO item = new HBookDTO();

        item.setHbookTitle(hbtitle);
        item.setHbookAuthor(hauthor);
        item.setHbookPublish(hpublish);
        hBookDTOArrayList.add(item);
    }
}
