package com.example.community;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class login extends AppCompatActivity {
    private DBAdapter dbAdapter;
    private EditText NumText;
    private EditText PwdText;
    private Button LoginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        NumText = findViewById(R.id.numtext);
        PwdText = findViewById(R.id.pwdtext);
        LoginButton = findViewById(R.id.loginbtn);
        LoginButton.setOnClickListener(LoginButtonListener);
        TextView forget=findViewById(R.id.forgetpwd);
        forget.setOnClickListener(forgetListener);
        TextView info = findViewById(R.id.info);
        TextView res=findViewById(R.id.res);
        res.setOnClickListener(resListener);
        String str = "登录即代表阅读并同意<font color='black'>服务协议</font>";
        info.setText(Html.fromHtml(str));
        dbAdapter = new DBAdapter(this);
        dbAdapter.open();
    }

    View.OnClickListener forgetListener=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
           Intent intent1=new Intent(login.this,forgetpwd.class);
           startActivity(intent1);
        }
    };

    View.OnClickListener resListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent=new Intent(login.this,register.class);
            startActivity(intent);
        }
    };


    View.OnClickListener LoginButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            People[] peoples = dbAdapter.queryAllData();
            String Num = NumText.getText().toString();
            String Pwd = PwdText.getText().toString();
            Boolean flag = false;
            String Pho;
            String Name;
            if (peoples == null){
                Toast.makeText(login.this,"错误",Toast.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(NumText.getText())){
                Toast.makeText(login.this,"请输入学号",Toast.LENGTH_SHORT).show();
            }else if (TextUtils.isEmpty(PwdText.getText())){
                Toast.makeText(login.this,"请输入密码",Toast.LENGTH_SHORT).show();
            }else{
                for (int i = 0; i < peoples.length; i++){
                    if (Num.equals(peoples[i].Num) && Pwd.equals(peoples[i].Pwd)) {
                        Intent intent=new Intent(login.this,main.class);
                        Pho = peoples[i].Pho;
                        Name = peoples[i].Name;
                        intent.putExtra("name",Name);
                        intent.putExtra("pho",Pho);
                        intent.putExtra("num",Num);
                        startActivity(intent);
                        Toast.makeText(login.this, "登录成功", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    if (i+1 == peoples.length){
                        Toast.makeText(login.this, "账号密码错误", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    };

}
