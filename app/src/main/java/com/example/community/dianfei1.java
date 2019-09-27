package com.example.community;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class dianfei1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dianfei1);
        Button button1=findViewById(R.id.addbtn);
        TextView show=findViewById(R.id.show);
        Intent intent=this.getIntent();
        final String gongyu = intent.getStringExtra("公寓");
        final String louhao = intent.getStringExtra("楼号");
        final String louceng = intent.getStringExtra("楼层");
        final String qinshihao = intent.getStringExtra("寝室号");
        String zong=gongyu+louhao+louceng+qinshihao;
        if((gongyu==null)||(louhao==null)||(louceng==null)||(qinshihao==null)){
            zong="";
        }
        show.setText(zong);
        button1.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(dianfei1.this,dianfei2.class);
                startActivity(intent);
            }
        });


    }
}
