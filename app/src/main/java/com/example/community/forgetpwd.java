package com.example.community;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class forgetpwd extends AppCompatActivity {

    private DBAdapter dbAdapter;
    private Button nextbtn;
    private Button sendbtn;
    private EditText phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgetpwd);
        phone = findViewById(R.id.phone);
        sendbtn = findViewById(R.id.send);
        sendbtn.setOnClickListener(sendbtnListener);
        nextbtn = findViewById(R.id.nextbtn);
        nextbtn.setOnClickListener(nextbtnListener);
        dbAdapter = new DBAdapter(this);
        dbAdapter.open();
    }

    View.OnClickListener nextbtnListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            People[] peoples = dbAdapter.queryAllData();
            String Pho = phone.getText().toString();
            if (TextUtils.isEmpty(phone.getText()))
                Toast.makeText(forgetpwd.this,"请输入手机号",Toast.LENGTH_SHORT).show();
            if (peoples == null){
                Toast.makeText(forgetpwd.this,"错误",Toast.LENGTH_SHORT).show();
                return;
            }
            for (int i = 0; i < peoples.length; i++){
                if (Pho.equals(peoples[i].Pho)) {
                    Intent intent = new Intent(forgetpwd.this, pwd.class);
                    intent.putExtra("pho",Pho);
                    startActivity(intent);
                    break;
                }
                if (i+1 == peoples.length){
                    Toast.makeText(forgetpwd.this, "该手机号未注册", Toast.LENGTH_SHORT).show();
                }
            }
        }
    };


    View.OnClickListener sendbtnListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };
}
