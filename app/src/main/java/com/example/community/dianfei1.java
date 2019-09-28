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
        final Button chongzhi1=findViewById(R.id.chongzhi1);
        final TextView show1=findViewById(R.id.show1);
        final TextView delete1=findViewById(R.id.delete1);


        Intent intent=this.getIntent();
        final String gongyu = intent.getStringExtra("公寓");
        final String louhao = intent.getStringExtra("楼号");
        final String louceng = intent.getStringExtra("楼层");
        final String qinshihao = intent.getStringExtra("寝室号");
        String zong=gongyu+louhao+louceng+qinshihao;
        if ((gongyu == null) || (louhao == null) || (louceng == null) || (qinshihao == null)) {
            zong="";
            chongzhi1.setVisibility(View.GONE);
            delete1.setVisibility(View.GONE);;
        }
        show1.setText(zong);






        button1.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(dianfei1.this,dianfei2.class);
                startActivity(intent);
            }
        });
        delete1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show1.setText("");
                chongzhi1.setVisibility(View.GONE);
                delete1.setVisibility(View.GONE);
            }
        });


    }
}

