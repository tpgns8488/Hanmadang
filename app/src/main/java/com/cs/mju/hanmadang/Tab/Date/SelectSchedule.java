package com.cs.mju.hanmadang.Tab.Date;

import android.content.Intent;
import android.content.res.Resources;
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
import com.cs.mju.hanmadang.Tab.Club.JSONParser;
import com.cs.mju.hanmadang.Tab.Club.WriteItem;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Joguk_1 on 2016-01-13.
 */
public class SelectSchedule extends ActionBarActivity {
    public static final int REQUEST_CODE_SELECTADD = 1002;
    final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy년 MM월 dd일");
    ListView listView1;
    ScheduleListAdapter adapter;
    TextView textView1;
    int selectedYear, selectedMonth, selectedDay;
    ScheduleJSONParser jsonParser = new ScheduleJSONParser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_select);

        // 리스트뷰 객체 참조
        listView1 = (ListView) findViewById(R.id.listView1);
        // 텍스트뷰 객체 참조
        textView1 = (TextView)findViewById(R.id.textView1);

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
                String[] curData = curItem.getData();

                Toast.makeText(getApplicationContext(), "Selected : " + curData[0], Toast.LENGTH_LONG).show();
            }
        });

        // 백버튼 이벤트 처리
        Button backButton = (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setResult(3);
                finish();
            }
        });

        // 글쓰기 버튼
        Button writeButton = (Button) findViewById(R.id.writeButton);
        writeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddSchedule.class);

                intent.putExtra("selectedYear", getIntent().getIntExtra("selectedYear",0));
                intent.putExtra("selectedMonth", getIntent().getIntExtra("selectedMonth",0));
                intent.putExtra("selectedDay", getIntent().getIntExtra("selectedDay",0));
                startActivityForResult(intent, REQUEST_CODE_SELECTADD);
            }
        });
    }

    private void loadData() {
        jsonParser.parseJSONFromURL(Constants.SCHEDULE_URL);

        String title;
        String place;
        String content;
        String date;
        // 10월 전이면 월 앞에 0을 붙이는 작업
        if(getIntent().getIntExtra("selectedMonth", 0)+1<10){
            date = getIntent().getIntExtra("selectedYear", 0) + "-" + "0"+(getIntent().getIntExtra("selectedMonth", 0)+1) + "-" + getIntent().getIntExtra("selectedDay", 0);
        }else{
            date = getIntent().getIntExtra("selectedYear", 0) + "-" + (getIntent().getIntExtra("selectedMonth", 0)+1) + "-" + getIntent().getIntExtra("selectedDay", 0);
        }
        Log.e("date ", date);

        for(int i=0; i<jsonParser.object.size(); i++) {
            String[] str = jsonParser.object.get(i).getTimestamp().split(" ");
            Log.e("str[0] ", str[0]);
            if(str[0].equals(date)){
                Log.e("일치, addItem ", date + "=" + str[0]);
                title = jsonParser.object.get(i).getTitle();
                place = jsonParser.object.get(i).getPlace();
                content = jsonParser.object.get(i).getContent();

                adapter.addItem(new ScheduleItem(title, place, content));
            }
        }
    }

    private void setDate(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(getIntent().getIntExtra("selectedYear",0), getIntent().getIntExtra("selectedMonth",0), getIntent().getIntExtra("selectedDay",0));
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

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == 1){
//            Toast.makeText(this, "응답으로 전달된 년도" + year, Toast.LENGTH_LONG).show();
            Toast.makeText(this, "데이터베이스에 저장.", Toast.LENGTH_LONG).show();
        }else if(resultCode == 2){
            Toast.makeText(this, "취소되었습니다.", Toast.LENGTH_LONG).show();
        }
    }
}