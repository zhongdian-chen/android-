package com.example.community;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class dianfei3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dianfei3);
        TextView info=findViewById(R.id.info);
        Intent intent=this.getIntent();
        String show1 = intent.getStringExtra("show1");
        info.setText(show1);
        String show2 = intent.getStringExtra("show2");
        String show3= intent.getStringExtra("show3");
    }
}
