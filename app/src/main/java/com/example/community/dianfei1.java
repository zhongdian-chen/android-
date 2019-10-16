package com.example.community;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class dianfei1 extends AppCompatActivity {
    private DianfeiInfoDBAapter dianfeiInfoDBAapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dianfei1);
        Button button1=findViewById(R.id.addbtn);
        final Button chongzhi1=findViewById(R.id.chongzhi1);
        final TextView show1=findViewById(R.id.show1);
        final TextView delete1=findViewById(R.id.delete1);

        final Button chongzhi2=findViewById(R.id.chongzhi2);
        final TextView show2=findViewById(R.id.show2);
        final TextView delete2=findViewById(R.id.delete2);

        final Button chongzhi3=findViewById(R.id.chongzhi3);
        final TextView show3=findViewById(R.id.show3);
        final TextView delete3=findViewById(R.id.delete3);

        Intent intent=this.getIntent();
        final String num = intent.getStringExtra("num");
        final String addr = intent.getStringExtra("addr");
        String gongyu = intent.getStringExtra("gongyu");
        String addr1 = intent.getStringExtra("show1");
        String addr2 = intent.getStringExtra("show2");
        String addr3 = intent.getStringExtra("show3");


        dianfeiInfoDBAapter = new DianfeiInfoDBAapter(this);
        dianfeiInfoDBAapter.open();
        final DianfeiInfo[] dianfeiInfos = dianfeiInfoDBAapter.queryAllData();

        if (dianfeiInfos == null){
            chongzhi1.setVisibility(View.GONE);
            delete1.setVisibility(View.GONE);
            chongzhi2.setVisibility(View.GONE);
            delete2.setVisibility(View.GONE);
            chongzhi3.setVisibility(View.GONE);
            delete3.setVisibility(View.GONE);
        } else{
            for (int i=0;i<dianfeiInfos.length;i++){
                if (i+1 == dianfeiInfos.length){
                    chongzhi1.setVisibility(View.GONE);
                    delete1.setVisibility(View.GONE);
                    chongzhi2.setVisibility(View.GONE);
                    delete2.setVisibility(View.GONE);
                    chongzhi3.setVisibility(View.GONE);
                    delete3.setVisibility(View.GONE);
                    if (!show1.getText().toString().equals("")){
                        chongzhi1.setVisibility(View.VISIBLE);
                        delete1.setVisibility(View.VISIBLE);
                    }
                    if (!show2.getText().toString().equals("")){
                        chongzhi2.setVisibility(View.VISIBLE);
                        delete2.setVisibility(View.VISIBLE);
                    }
                    if (!show3.getText().toString().equals("")){
                        chongzhi3.setVisibility(View.VISIBLE);
                        delete3.setVisibility(View.VISIBLE);
                    }
                }
                if (dianfeiInfos[i].Num.equals(num)){
                    if (show1.getText().toString().equals("")){
                        chongzhi1.setVisibility(View.VISIBLE);
                        delete1.setVisibility(View.VISIBLE);
                        show1.setText(dianfeiInfos[i].Addr);
                        chongzhi2.setVisibility(View.GONE);
                        delete2.setVisibility(View.GONE);
                        chongzhi3.setVisibility(View.GONE);
                        delete3.setVisibility(View.GONE);
                    }else if (!show1.getText().toString().equals("") && show2.getText().toString().equals("")){
                        chongzhi1.setVisibility(View.VISIBLE);
                        delete1.setVisibility(View.VISIBLE);
                        chongzhi2.setVisibility(View.VISIBLE);
                        delete2.setVisibility(View.VISIBLE);
                        show2.setText(dianfeiInfos[i].Addr);
                        chongzhi3.setVisibility(View.GONE);
                        delete3.setVisibility(View.GONE);
                    } else if (!show1.getText().toString().equals("") && !show2.getText().toString().equals("") && show3.getText().toString().equals("")){
                        chongzhi1.setVisibility(View.VISIBLE);
                        delete1.setVisibility(View.VISIBLE);
                        chongzhi2.setVisibility(View.VISIBLE);
                        delete2.setVisibility(View.VISIBLE);
                        chongzhi3.setVisibility(View.VISIBLE);
                        delete3.setVisibility(View.VISIBLE);
                        show3.setText(dianfeiInfos[i].Addr);
                    }else if(!show1.getText().toString().equals("") && !show2.getText().toString().equals("") && !show3.getText().toString().equals("")) {
                        chongzhi1.setVisibility(View.VISIBLE);
                        delete1.setVisibility(View.VISIBLE);
                        chongzhi2.setVisibility(View.VISIBLE);
                        delete2.setVisibility(View.VISIBLE);
                        chongzhi3.setVisibility(View.VISIBLE);
                        delete3.setVisibility(View.VISIBLE);
                        break;
                    }
                }
            }
        }


        if (!show1.getText().toString().equals("")) {
            if (show1.getText().toString().substring(0, 5).equals(gongyu))
                show1.setText(addr);
            if(addr1!=null)
                if (!addr1.equals(""))
                    show1.setText(addr1);
        }
        if (!show2.getText().toString().equals("")) {
            if (show2.getText().toString().substring(0, 5).equals(gongyu))
                show2.setText(addr);
            if(addr2!=null)
                if (!addr2.equals(""))
                    show2.setText(addr2);
        }
        if (!show3.getText().toString().equals("")) {
            if (show3.getText().toString().substring(0, 5).equals(gongyu))
                show3.setText(addr);
            if(addr3!=null)
                if (!addr3.equals(""))
                    show3.setText(addr3);
        }

        button1.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(dianfei1.this,dianfei2.class);
                intent.putExtra("num",num);
                intent.putExtra("show1",show1.getText().toString());
                intent.putExtra("show2",show2.getText().toString());
                intent.putExtra("show3",show3.getText().toString());
                if (!show1.getText().toString().equals("")) {
                    intent.putExtra("gongyu1", show1.getText().toString().substring(0, 5));
                }
                if (!show2.getText().toString().equals("")) {
                    intent.putExtra("gongyu2", show2.getText().toString().substring(0, 5));
                }
                if (!show3.getText().toString().equals("")) {
                    intent.putExtra("gongyu3", show3.getText().toString().substring(0, 5));
                }
                startActivity(intent);
            }
        });

        delete1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String address = show1.getText().toString();
                show1.setText("");
                chongzhi1.setVisibility(View.GONE);
                delete1.setVisibility(View.GONE);
            }
        });

        delete2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String address = show2.getText().toString();
                show2.setText("");
                chongzhi2.setVisibility(View.GONE);
                delete2.setVisibility(View.GONE);
            }
        });

        delete3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String address = show3.getText().toString();
                show3.setText("");
                chongzhi3.setVisibility(View.GONE);
                delete3.setVisibility(View.GONE);
            }
        });
        chongzhi1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(dianfei1.this,dianfei3.class);
                intent.putExtra("show1",show1.getText().toString());
                intent.putExtra("num",num);
                intent.putExtra("addr1",show1.getText().toString());
                startActivity(intent);
            }
        });
        chongzhi2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(dianfei1.this,dianfei3.class);
                intent.putExtra("show2",show2.getText().toString());
                intent.putExtra("num",num);
                intent.putExtra("addr2",show2.getText().toString());
                startActivity(intent);
            }
        });
        chongzhi3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(dianfei1.this,dianfei3.class);
                intent.putExtra("show3",show3.getText().toString());
                intent.putExtra("num",num);
                intent.putExtra("addr3",show3.getText().toString());
                startActivity(intent);
            }
        });
    }
}

