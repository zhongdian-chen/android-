package com.example.community;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Button fix=(Button)findViewById(R.id.fix);
        Button water=(Button)findViewById(R.id.water);
        Button fee=(Button)findViewById(R.id.fee);
        Button expect=findViewById(R.id.expect);
        expect.setClickable(false);
        final TextView info=(TextView)findViewById(R.id.info);
        Intent intent = getIntent();
        final String Name = intent.getStringExtra("name");
        final String Num = intent.getStringExtra("num");
        final String Pho = intent.getStringExtra("pho");
        info.setText(Name+"\n"+Pho);
        fix.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(main.this, fix.class);
                intent.putExtra("name",Name);
                intent.putExtra("pho",Pho);
                intent.putExtra("num",Num);
                startActivity(intent);
            }
        });

        water.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(main.this, water.class);
                intent.putExtra("name",Name);
                intent.putExtra("pho",Pho);
                intent.putExtra("num",Num);
                startActivity(intent);
            }
        });

        fee.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(main.this,dianfei1.class);
                intent.putExtra("name",Name);
                intent.putExtra("pho",Pho);
                intent.putExtra("num",Num);
                startActivity(intent);
            }
        });
    }
}
