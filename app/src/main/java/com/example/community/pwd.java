package com.example.community;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class pwd extends AppCompatActivity {
    private DBAdapter  dbAdapter;
    EditText newpwd;
    EditText checkpwd;
    Button checkbtn;
    String pho;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pwd);
        Intent intent = getIntent();
        pho = intent.getStringExtra("pho");
        newpwd = findViewById(R.id.newpwd);
        checkpwd = findViewById(R.id.checkpwd);
        checkbtn = findViewById(R.id.check);
        checkbtn.setOnClickListener(checkbtnListener);
        dbAdapter = new DBAdapter(this);
        dbAdapter.open();
    }

    View.OnClickListener checkbtnListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            People people = new People();
            people.Pwd = MD5Util.encrypBy(newpwd.getText().toString());
            if((newpwd.getText().toString()).equals(checkpwd.getText().toString())){
                dbAdapter.updatepwd(people,pho);
                Toast.makeText(pwd.this,"密码重置成功，请登录",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(pwd.this, login.class);
                startActivity(intent);
            }else if(TextUtils.isEmpty(newpwd.getText())){
                Toast.makeText(pwd.this,"请输入新密码",Toast.LENGTH_SHORT).show();
            }else if (TextUtils.isEmpty(checkpwd.getText())){
                Toast.makeText(pwd.this,"请确认密码",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(pwd.this,"两次密码不一致",Toast.LENGTH_SHORT).show();
            }
        }
    };
}
