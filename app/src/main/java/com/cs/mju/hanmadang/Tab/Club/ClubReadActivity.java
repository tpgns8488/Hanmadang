package com.cs.mju.hanmadang.Tab.Club;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cs.mju.hanmadang.Constants;
import com.cs.mju.hanmadang.R;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by park on 2016-01-18.
 */
public class ClubReadActivity extends AppCompatActivity implements View.OnClickListener  {
    private EditText content;
    private Button okButton;
    private TextView title;
    private TextView writer;
    private String password;
    private Button delButton;
    private Button modifyButton;
    private EditText inputKey;
    private Button keyButton;
    int pos;
    String contextString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club_read);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // 초기화, 이벤트 등록
        initViews();
        addListenersToView();

        // 저장된 게시글 데이터를 가져옴
        loadData();
    }

    private void initViews() {
        content = (EditText)findViewById(R.id.content);
        content.setMaxLines(12);
        content.setVerticalScrollBarEnabled(true);
        content.setHorizontalScrollBarEnabled(true);
        content.setMovementMethod(new ScrollingMovementMethod());
        content.setFocusableInTouchMode(false);

        okButton = (Button)findViewById(R.id.okButton);
        delButton = (Button)findViewById(R.id.delButton);
        delButton.setEnabled(false);
        delButton.setFocusable(false);

        modifyButton = (Button)findViewById(R.id.modifyButton);
        modifyButton.setEnabled(false);
        modifyButton.setFocusable(false);

        title = (TextView)findViewById(R.id.inputTitle);
        writer = (TextView)findViewById(R.id.inputWriter);

        inputKey = (EditText)findViewById(R.id.inputKey);
        keyButton = (Button)findViewById(R.id.keyButton);

        contextString = null;
    }

    private void addListenersToView() {
        okButton.setOnClickListener(this);
        delButton.setOnClickListener(this);
        keyButton.setOnClickListener(this);
        modifyButton.setOnClickListener(this);
    }

    public void onClick(View v) {
        if(v.getId() == R.id.okButton) {
            finish();
        }else if(v.getId() == R.id.keyButton) {
            matchingKey();
        }else if(v.getId() == R.id.delButton) {
            deleteData();
            sendDataToClubActivity();
        }else if(v.getId() == R.id.modifyButton) {
            modifyData();
            sendDataToClubActivity();
        }
    }

    private void matchingKey() {
        switch(writer.getText().toString()) {
            case "AO":
                if (inputKey.getText().toString().equals(password)) {
                    Toast.makeText(getApplicationContext(), "비밀번호 일치", Toast.LENGTH_LONG).show();

                    delButton.setEnabled(true);
                    delButton.setFocusable(true);
                    modifyButton.setEnabled(true);
                    modifyButton.setFocusable(true);
                    content.setFocusableInTouchMode(true);
                } else {
                    Toast.makeText(getApplicationContext(), "AO에 맞는 비밀번호가 아닙니다!", Toast.LENGTH_LONG).show();
                }

                break;
            case "인클루드":
                if (inputKey.getText().toString().equals(password)) {
                    Toast.makeText(getApplicationContext(), "비밀번호 일치", Toast.LENGTH_LONG).show();

                    delButton.setEnabled(true);
                    delButton.setFocusable(true);
                    modifyButton.setEnabled(true);
                    modifyButton.setFocusable(true);
                    content.setFocusableInTouchMode(true);

                } else {
                    Toast.makeText(getApplicationContext(), "인클루드에 맞는 비밀번호가 아닙니다!", Toast.LENGTH_LONG).show();
                }

                break;
            case "한울":
                if (inputKey.getText().toString().equals(password)) {
                    Toast.makeText(getApplicationContext(), "비밀번호 일치", Toast.LENGTH_LONG).show();

                    delButton.setEnabled(true);
                    delButton.setFocusable(true);
                    modifyButton.setEnabled(true);
                    modifyButton.setFocusable(true);
                    content.setFocusableInTouchMode(true);

                } else {
                    Toast.makeText(getApplicationContext(), "한울에 맞는 비밀번호가 아닙니다!", Toast.LENGTH_LONG).show();
                }

                break;
            case "SAT":
                if (inputKey.getText().toString().equals(password)) {
                    Toast.makeText(getApplicationContext(), "비밀번호 일치", Toast.LENGTH_LONG).show();

                    delButton.setEnabled(true);
                    delButton.setFocusable(true);
                    modifyButton.setEnabled(true);
                    modifyButton.setFocusable(true);
                    content.setFocusableInTouchMode(true);

                } else {
                    Toast.makeText(getApplicationContext(), "SAT에 맞는 비밀번호가 아닙니다!", Toast.LENGTH_LONG).show();
                }

                break;
            case "ICUNIX":
                if (inputKey.getText().toString().equals(password)) {
                    Toast.makeText(getApplicationContext(), "비밀번호 일치", Toast.LENGTH_LONG).show();

                    delButton.setEnabled(true);
                    delButton.setFocusable(true);
                    modifyButton.setEnabled(true);
                    modifyButton.setFocusable(true);
                    content.setFocusableInTouchMode(true);

                } else {
                    Toast.makeText(getApplicationContext(), "ICUNIX에 맞는 비밀번호가 아닙니다!", Toast.LENGTH_LONG).show();
                }

                break;
            case "NEXT":
                if (inputKey.getText().toString().equals(password)) {
                    Toast.makeText(getApplicationContext(), "비밀번호 일치", Toast.LENGTH_LONG).show();

                    delButton.setEnabled(true);
                    delButton.setFocusable(true);
                    modifyButton.setEnabled(true);
                    modifyButton.setFocusable(true);
                    content.setFocusableInTouchMode(true);
                } else {
                    Toast.makeText(getApplicationContext(), "NEXT에 맞는 비밀번호가 아닙니다!", Toast.LENGTH_LONG).show();
                }

                break;
            case "MAP":
                if (inputKey.getText().toString().equals(password)) {
                    Toast.makeText(getApplicationContext(), "비밀번호 일치", Toast.LENGTH_LONG).show();

                    delButton.setEnabled(true);
                    delButton.setFocusable(true);
                    modifyButton.setEnabled(true);
                    modifyButton.setFocusable(true);
                    content.setFocusableInTouchMode(true);

                } else {
                    Toast.makeText(getApplicationContext(), "MAP에 맞는 비밀번호가 아닙니다!", Toast.LENGTH_LONG).show();
                }

                break;
            case "그리고":
                if (inputKey.getText().toString().equals(password)) {
                    Toast.makeText(getApplicationContext(), "비밀번호 일치", Toast.LENGTH_LONG).show();

                    delButton.setEnabled(true);
                    delButton.setFocusable(true);
                    modifyButton.setEnabled(true);
                    modifyButton.setFocusable(true);
                    content.setFocusableInTouchMode(true);

                } else {
                    Toast.makeText(getApplicationContext(), "그리고에 맞는 비밀번호가 아닙니다!", Toast.LENGTH_LONG).show();
                }

                break;
            case "COSCI":
                if (inputKey.getText().toString().equals(password)) {
                    Toast.makeText(getApplicationContext(), "비밀번호 일치", Toast.LENGTH_LONG).show();

                    delButton.setEnabled(true);
                    delButton.setFocusable(true);
                    modifyButton.setEnabled(true);
                    modifyButton.setFocusable(true);
                    content.setFocusableInTouchMode(true);

                } else {
                    Toast.makeText(getApplicationContext(), "COSCI에 맞는 비밀번호가 아닙니다!", Toast.LENGTH_LONG).show();
                }

                break;
            case "OS":
                if (inputKey.getText().toString().equals(password)) {
                    Toast.makeText(getApplicationContext(), "비밀번호 일치", Toast.LENGTH_LONG).show();

                    delButton.setEnabled(true);
                    delButton.setFocusable(true);
                    modifyButton.setEnabled(true);
                    modifyButton.setFocusable(true);
                    content.setFocusableInTouchMode(true);

                } else {
                    Toast.makeText(getApplicationContext(), "OS에 맞는 비밀번호가 아닙니다!", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    private void loadData() {
        // 파싱할 URL에서 JSON을 객체로
        JSONParser jsonParser = new JSONParser();
        jsonParser.parseJSONFromURL(Constants.CLUB_URL);

        Intent intent = getIntent();
        pos = intent.getExtras().getInt("pos");

        title.setText(jsonParser.object.get(pos).getB_title());
        content.setText(jsonParser.object.get(pos).getB_content());
        writer.setText(jsonParser.object.get(pos).getB_writer());
        password = jsonParser.object.get(pos).getB_password(); // 입력된 key와 비교
    }

    private void modifyData() {
                try {
                    URL url = new URL(Constants.CLUB_MOD_URL);
                    URLConnection conn = url.openConnection();

                    conn.setDoOutput(true);

                    /* 삭제 시에 필요한 정보인 글 number 를 보낸다. */
                    OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
                    contextString = content.getText().toString();

                    pos = pos+1;
                    String num = String.valueOf(pos);

                    out.write(contextString);
                    out.write("&*&");
                    out.write(num);
                    out.close();

                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                }catch(Exception e) {
                    Log.d("Exception", e.toString());
                }
    }

    private void deleteData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(Constants.CLUB_DEL_URL);
                    URLConnection conn = url.openConnection();

                    conn.setDoOutput(true);

                    /* 삭제 시에 필요한 정보인 글 number 를 보낸다. */
                    OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
                    pos = pos+1;
                    String num = String.valueOf(pos);
                    Log.d("test", num);

                    out.write(num);
                    out.close();

                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                }catch(Exception e) {
                    Log.d("Exception", e.toString());
                }
            }
        }).start();
    }

    private void sendDataToClubActivity() {
        Intent intent = getIntent();
        this.setResult(RESULT_OK, intent);

        this.finish();
    }
}
