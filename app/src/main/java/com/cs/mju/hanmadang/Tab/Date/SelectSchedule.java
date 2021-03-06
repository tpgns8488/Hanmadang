package com.cs.mju.hanmadang.Tab.Date;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.cs.mju.hanmadang.Constants;
import com.cs.mju.hanmadang.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Joguk_1 on 2016-01-13.
 */
public class SelectSchedule extends ActionBarActivity {
    public static final int REQUEST_CODE_SELECTED = 1002;
    public static final int REQUEST_CODE_MODIFIED = 1003;
    final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy년 MM월 dd일");
    private ListView listView1;
    private ScheduleListAdapter adapter;
    private TextView textView1;
    private Button deleteButton, writeButton, backButton;
    private String[] curData;
    private String date;

    int selectedYear, selectedMonth, selectedDay;
    ScheduleJSONParser jsonParser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_select);

        Log.e("delete entry", getIntent().getExtras().getString("detele", "nothing"));
        if(getIntent().getStringExtra("delete")=="delete"){
            Log.e("delete ok", "delete ok");
            adapter.notifyDataSetChanged();
            Intent intent = new Intent(getApplicationContext(), SelectSchedule.class);
            intent.putExtra("selectedYear", getIntent().getIntExtra("selectedYear", 0));
            intent.putExtra("selectedMonth", getIntent().getIntExtra("selectedMonth", 0));
            intent.putExtra("selectedDay", getIntent().getIntExtra("selectedDay", 0));

            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
        }

        // 리스트뷰 객체 참조
        listView1 = (ListView) findViewById(R.id.listView1);
        // 텍스트뷰 객체 참조
        textView1 = (TextView) findViewById(R.id.textView1);
        backButton = (Button) findViewById(R.id.backButton);
        writeButton = (Button) findViewById(R.id.writeButton);
//        deleteButton = (Button) findViewById(R.id.deleteButton);

        jsonParser = new ScheduleJSONParser();

        //날짜 세팅
        setDate();

        // 어댑터 객체 생성
        adapter = new ScheduleListAdapter(this);

        // DB에서 글목록 갖고오기
        loadData();

        // 리스트뷰에 어댑터 설정
        listView1.setAdapter(adapter);

        // 새로 정의한 리스너로 객체를 만들어 설정
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ScheduleItem curItem = (ScheduleItem) adapter.getItem(position);
                curData = curItem.getData();

                Intent intent = new Intent(getApplicationContext(), AddSchedule.class);

                intent.putExtra("selectedYear", getIntent().getIntExtra("selectedYear", 0));
                intent.putExtra("selectedMonth", getIntent().getIntExtra("selectedMonth", 0));
                intent.putExtra("selectedDay", getIntent().getIntExtra("selectedDay", 0));
                intent.putExtra("title", curData[0]);
                intent.putExtra("place", curData[1]);
                intent.putExtra("content", curData[2]);
                intent.putExtra("timestamp", curData[3]);
                intent.putExtra("position", position);

                startActivityForResult(intent, REQUEST_CODE_MODIFIED);
            }
        });

        // 백버튼 이벤트 처리
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setResult(3);
                finish();
            }
        });

        // 글쓰기 버튼 이벤트 처리
        writeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddSchedule.class);

                intent.putExtra("selectedYear", getIntent().getIntExtra("selectedYear", 0));
                intent.putExtra("selectedMonth", getIntent().getIntExtra("selectedMonth", 0));
                intent.putExtra("selectedDay", getIntent().getIntExtra("selectedDay", 0));
                startActivityForResult(intent, REQUEST_CODE_SELECTED);
            }
        });
    }


    private void loadData() {
        jsonParser.parseJSONFromURL(Constants.SCHEDULE_URL);

        String title;
        String place;
        String content;
        String date;
        String timestamp;
        // 10월 전이면 월 앞에 0을 붙이는 작업, 10일 전 일 경우 0을 붙이는 작업
        if (getIntent().getIntExtra("selectedMonth", 0) + 1 < 10) {
            if(getIntent().getIntExtra("selectedDay", 0)<10)
                date = getIntent().getIntExtra("selectedYear", 0) + "-" + "0" + (getIntent().getIntExtra("selectedMonth", 0) + 1) + "-" + "0" + getIntent().getIntExtra("selectedDay", 0);
            else
                date = getIntent().getIntExtra("selectedYear", 0) + "-" + "0" + (getIntent().getIntExtra("selectedMonth", 0) + 1) + "-" + getIntent().getIntExtra("selectedDay", 0);
        } else {
            if(getIntent().getIntExtra("selectedDay", 0)<10)
                date = getIntent().getIntExtra("selectedYear", 0) + "-" + (getIntent().getIntExtra("selectedMonth", 0) + 1) + "-" + "0" + getIntent().getIntExtra("selectedDay", 0);
            else
                date = getIntent().getIntExtra("selectedYear", 0) + "-" + (getIntent().getIntExtra("selectedMonth", 0) + 1) + "-" + getIntent().getIntExtra("selectedDay", 0);
        }

        for (int i = 0; i < jsonParser.object.size(); i++) {
            String[] str = jsonParser.object.get(i).getTimestamp().split(" ");
            if (str[0].equals(date)) {
                title = jsonParser.object.get(i).getTitle();
                place = jsonParser.object.get(i).getPlace();
                content = jsonParser.object.get(i).getContent();
                timestamp = jsonParser.object.get(i).getTimestamp();

                adapter.addItem(new ScheduleItem(title, place, content, timestamp));
            }
        }
    }

    private void setDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(getIntent().getIntExtra("selectedYear", 0), getIntent().getIntExtra("selectedMonth", 0), getIntent().getIntExtra("selectedDay", 0));
        textView1.setText(dateFormat.format(calendar.getTime()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1) {
            super.onResume();
            Intent intent = new Intent(getApplicationContext(), SelectSchedule.class);
            intent.putExtra("selectedYear", getIntent().getIntExtra("selectedYear", 0));
            intent.putExtra("selectedMonth", getIntent().getIntExtra("selectedMonth", 0));
            intent.putExtra("selectedDay", getIntent().getIntExtra("selectedDay", 0));

            String title = data.getStringExtra("title");
            String place = data.getStringExtra("place");
            String content = data.getStringExtra("content");
            String timestamp = data.getStringExtra("timestamp");
            int position = data.getIntExtra("position", 002);

            if(position != 002) {
                adapter.deleteItem(position);
                createListView(title, place, content, timestamp);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }else {
                createListView(title, place, content, timestamp);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        } else if (resultCode == 2) {
            Toast.makeText(this, "취소되었습니다.", Toast.LENGTH_LONG).show();
        }
    }

    private void createListView(String title, String place, String content, String timestamp){
        String date;
        // 아이템 등록
        if(title == null) {
            Toast.makeText(this, "제목을 입력해주세요.", Toast.LENGTH_LONG).show();
        }else {
            // 10월 전이면 월 앞에 0을 붙이는 작업 , 10일 전일 경우 0을 붙이는 작업
            adapter.addItem(new ScheduleItem(title, place, content, timestamp));
            adapter.notifyDataSetChanged();
        }
    }
}