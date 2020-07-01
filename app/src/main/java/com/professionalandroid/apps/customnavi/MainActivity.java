package com.professionalandroid.apps.customnavi;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;


import java.text.SimpleDateFormat;
import java.util.Date;

@RequiresApi(api = Build.VERSION_CODES.N)
public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private View drawerView;
    private Button notepad;
    private Button calendar;
    private Button eclass;

    private TextClock nowtime;
    long now = System.currentTimeMillis();
    Date date = new Date(now);
    SimpleDateFormat formatnow = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초");
    String Date = formatnow.format(date);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nowtime=(TextClock)findViewById(R.id.nowtime);
        nowtime.setText(Date);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerView = (View)findViewById(R.id.drawer);


        calendar=findViewById(R.id.calendar);
        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CalendarActivity.class);
                startActivity(intent);
            }
        });

        eclass=findViewById(R.id.eclass);
        eclass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, eclassActivity.class);
                startActivity(intent);
            }
        });



        Button btn_open = (Button)findViewById(R.id.btn_open);
        btn_open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(drawerView);

            }
        });


        Button calendar = (Button)findViewById(R.id.calendar);
        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1  = new Intent(MainActivity.this, CalendarActivity.class);
                startActivity(intent1);

            }
        });
        Button eclass = (Button)findViewById(R.id.eclass);
        eclass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2  = new Intent(MainActivity.this, eclassActivity.class);
                startActivity(intent2);

            }
        });




        drawerLayout.setDrawerListener(listener);
        drawerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });


    }
    DrawerLayout.DrawerListener listener = new DrawerLayout.DrawerListener() {
        @Override
        public void onDrawerSlide(@NonNull View view, float v) {

        }

        @Override
        public void onDrawerOpened(@NonNull View view) {

        }

        @Override
        public void onDrawerClosed(@NonNull View view) {

        }

        @Override
        public void onDrawerStateChanged(int i) {

        }
    };

    private long time =0;

    @Override
    public void onBackPressed(){
        if(System.currentTimeMillis() - time >= 2000)
        {
            time = System.currentTimeMillis();
            Toast.makeText(getApplicationContext(), "뒤로가기 버튼을 한 번 더 누르면 종료합니다", Toast.LENGTH_LONG).show();
        }
        else if(System.currentTimeMillis() - time < 2000)
            finish();
    }
}