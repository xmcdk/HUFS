package com.professionalandroid.apps.customnavi;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

public class CalendarActivity extends Activity { //현재 날짜를 보라색으로 표시합니다.


    private TextView YearMonth;
    private GridAdapter gridAdapter;
    private ArrayList<String> day;
    private GridView gridView;

    /**
     * 캘린더 변수
     */
    private Calendar Cal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_calendar);

        YearMonth = (TextView)findViewById(R.id.yearmonth);
        gridView = (GridView)findViewById(R.id.gridview);


        long now = System.currentTimeMillis();
        final Date date = new Date(now);

        final SimpleDateFormat year = new SimpleDateFormat("yyyy", Locale.KOREA);
        final SimpleDateFormat month = new SimpleDateFormat("MM", Locale.KOREA);


        //현재 날짜 텍스트뷰에 뿌려줌
        YearMonth.setText(year.format(date) + "/" + month.format(date));

        //gridview 요일 표시
        day = new ArrayList<String>();
        day.add("일");
        day.add("월");
        day.add("화");
        day.add("수");
        day.add("목");
        day.add("금");
        day.add("토");

        Cal = Calendar.getInstance();

        Cal.set(Integer.parseInt(year.format(date)), Integer.parseInt(month.format(date)) - 1, 1);
        int dayNum = Cal.get(Calendar.DAY_OF_WEEK);
        for (int i = 1; i < dayNum; i++) {
            day.add("");
        }
        setCalendarDate(Cal.get(Calendar.MONTH) + 1);

        gridAdapter = new GridAdapter(getApplicationContext(), day);
        gridView.setAdapter(gridAdapter);

    }

    private void setCalendarDate(int month) {
        Cal.set(Calendar.MONTH, month - 1);

        for (int i = 0; i < Cal.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
            day.add("" + (i + 1));
        }

    }

    private class GridAdapter extends BaseAdapter {

        private final List<String> list;

        private final LayoutInflater inflater;

        public GridAdapter(Context context, List<String> list) {
            this.list = list;
            this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public String getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder = null;

            if (convertView == null) {
                convertView = inflater.inflate(R.layout.calendar_gridview, parent, false);
                holder = new ViewHolder();

                holder.GridView = (TextView)convertView.findViewById(R.id.tv_item_gridview);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder)convertView.getTag();
            }
            holder.GridView.setText("" + getItem(position));

            Cal = Calendar.getInstance();
            Integer today = Cal.get(Calendar.DAY_OF_MONTH);
            String sToday = String.valueOf(today);
            if (sToday.equals(getItem(position))) {
                holder.GridView.setTextColor(getResources().getColor(R.color.today));
            }
            return convertView;
        }
    }

    private class ViewHolder {
        TextView GridView;
    }

}