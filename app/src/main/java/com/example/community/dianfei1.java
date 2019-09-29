package com.example.community;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class dianfei1 extends AppCompatActivity {

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
        final String gongyu = intent.getStringExtra("公寓");
        final String louhao = intent.getStringExtra("楼号");
        final String louceng = intent.getStringExtra("楼层");
        final String qinshihao = intent.getStringExtra("寝室号");
        final String show11 = intent.getStringExtra("show1");
        final String show22 = intent.getStringExtra("show2");
        final String show33 = intent.getStringExtra("show3");
        final String gongyu1 = intent.getStringExtra("gongyu1");
        final String gongyu2 = intent.getStringExtra("gongyu2");
        final String gongyu3 = intent.getStringExtra("gongyu3");
        final String zong=gongyu+louhao+louceng+qinshihao;


        if (gongyu == null){
            chongzhi1.setVisibility(View.GONE);
            delete1.setVisibility(View.GONE);
            chongzhi2.setVisibility(View.GONE);
            delete2.setVisibility(View.GONE);
            chongzhi3.setVisibility(View.GONE);
            delete3.setVisibility(View.GONE);
        }else{
            if ( show11.equals("")|| gongyu1.equals(gongyu) ){
                if (gongyu1 !=null && gongyu1.equals(gongyu)){
                    if ( !show33.equals("")){
                        chongzhi1.setVisibility(View.VISIBLE);
                        delete1.setVisibility(View.VISIBLE);
                        show1.setText(zong);
                        chongzhi2.setVisibility(View.VISIBLE);
                        delete2.setVisibility(View.VISIBLE);
                        show2.setText(show22);
                        chongzhi3.setVisibility(View.VISIBLE);
                        delete3.setVisibility(View.VISIBLE);
                        show3.setText(show33);
                        Toast.makeText(dianfei1.this,"1",Toast.LENGTH_SHORT).show();
                    }else if ( show33.equals("") && !show22.equals("")){
                        chongzhi1.setVisibility(View.VISIBLE);
                        delete1.setVisibility(View.VISIBLE);
                        show1.setText(zong);
                        chongzhi2.setVisibility(View.VISIBLE);
                        delete2.setVisibility(View.VISIBLE);
                        show2.setText(show22);
                        chongzhi3.setVisibility(View.GONE);
                        delete3.setVisibility(View.GONE);
                        Toast.makeText(dianfei1.this,"2",Toast.LENGTH_SHORT).show();
                    }else{
                        chongzhi1.setVisibility(View.VISIBLE);
                        delete1.setVisibility(View.VISIBLE);
                        show1.setText(zong);
                        chongzhi2.setVisibility(View.GONE);
                        delete2.setVisibility(View.GONE);
                        chongzhi3.setVisibility(View.GONE);
                        delete3.setVisibility(View.GONE);
                        Toast.makeText(dianfei1.this,"2.5",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    chongzhi1.setVisibility(View.VISIBLE);
                    delete1.setVisibility(View.VISIBLE);
                    show1.setText(zong);
                    chongzhi2.setVisibility(View.GONE);
                    delete2.setVisibility(View.GONE);
                    chongzhi3.setVisibility(View.GONE);
                    delete3.setVisibility(View.GONE);
                    Toast.makeText(dianfei1.this,"3",Toast.LENGTH_SHORT).show();
                }
            }else if ( !show11.equals("") && show22.equals("") || gongyu2.equals(gongyu)){
                if( gongyu1!=null && gongyu2!=null &&  gongyu2.equals(gongyu)){
                    if( show33.equals("") ){
                        chongzhi1.setVisibility(View.VISIBLE);
                        delete1.setVisibility(View.VISIBLE);
                        show1.setText(show11);
                        chongzhi2.setVisibility(View.VISIBLE);
                        delete2.setVisibility(View.VISIBLE);
                        show2.setText(zong);
                        chongzhi3.setVisibility(View.GONE);
                        delete3.setVisibility(View.GONE);
                        Toast.makeText(dianfei1.this,"10",Toast.LENGTH_SHORT).show();
                    }else if( !show33.equals("") ){
                        chongzhi1.setVisibility(View.VISIBLE);
                        delete1.setVisibility(View.VISIBLE);
                        show1.setText(show11);
                        chongzhi2.setVisibility(View.VISIBLE);
                        delete2.setVisibility(View.VISIBLE);
                        show2.setText(zong);
                        chongzhi3.setVisibility(View.VISIBLE);
                        delete3.setVisibility(View.VISIBLE);
                        show3.setText(show33);
                    }
                }else {
                    chongzhi1.setVisibility(View.VISIBLE);
                    delete1.setVisibility(View.VISIBLE);
                    show1.setText(show11);
                    chongzhi2.setVisibility(View.VISIBLE);
                    delete2.setVisibility(View.VISIBLE);
                    show2.setText(zong);
                    chongzhi3.setVisibility(View.GONE);
                    delete3.setVisibility(View.GONE);
                    Toast.makeText(dianfei1.this,"8",Toast.LENGTH_SHORT).show();
                }
            }else if ( !show11.equals("") && !show22.equals("") && show33.equals("") || (gongyu3!=null && gongyu3.equals(gongyu))){
                chongzhi1.setVisibility(View.VISIBLE);
                delete1.setVisibility(View.VISIBLE);
                show1.setText(show11);
                chongzhi2.setVisibility(View.VISIBLE);
                delete2.setVisibility(View.VISIBLE);
                show2.setText(show22);
                chongzhi3.setVisibility(View.VISIBLE);
                delete3.setVisibility(View.VISIBLE);
                show3.setText(zong);
                Toast.makeText(dianfei1.this,"7",Toast.LENGTH_SHORT).show();
            }
        }


        button1.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(dianfei1.this,dianfei2.class);
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
                show1.setText("");
                chongzhi1.setVisibility(View.GONE);
                delete1.setVisibility(View.GONE);
            }
        });

        delete2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show2.setText("");
                chongzhi2.setVisibility(View.GONE);
                delete2.setVisibility(View.GONE);
            }
        });

        delete3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show3.setText("");
                chongzhi3.setVisibility(View.GONE);
                delete3.setVisibility(View.GONE);
            }
        });
    }
}

