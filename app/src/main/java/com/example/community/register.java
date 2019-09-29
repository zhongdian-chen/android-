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

public class register extends AppCompatActivity {

    private DBAdapter dbAdapter;
    private EditText NameText;
    private EditText NumText;
    private EditText PwdText;
    private EditText PhoText;
    private Button ResButton;
    private Button SendButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        NameText = findViewById(R.id.nametext);
        NumText = findViewById(R.id.numtext);
        PwdText = findViewById(R.id.pwdtext);
        PhoText = findViewById(R.id.photext);
        ResButton = findViewById(R.id.resbtn);
        ResButton.setOnClickListener(ResButtonListener);
        SendButton = findViewById(R.id.send);
        SendButton.setOnClickListener(SendButtonListener);
        TextView info1 = findViewById(R.id.info1);
        String str = "注册即代表阅读并同意<font color='black'>服务协议</font>和<font color='black'>隐私政策</font>";
        info1.setText(Html.fromHtml(str));
        dbAdapter = new DBAdapter(this);
        dbAdapter.open();
    }

    View.OnClickListener ResButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Boolean flag = false;
            People people = new People();
            String Pho = PhoText.getText().toString();
            People[] peoples = dbAdapter.queryAllData();
            if (peoples != null)
                for (int i = 0; i < peoples.length; i++){
                    if (Pho.equals(peoples[i].Pho)) {
                        flag = true;
                        break;
                    }
                }
            people.Name = NameText.getText().toString();
            people.Num = NumText.getText().toString();
            people.Pwd = PwdText.getText().toString();
            people.Pho = PhoText.getText().toString();
            long colunm = dbAdapter.insert(people);
            if (TextUtils.isEmpty(NameText.getText())){
                Toast.makeText(register.this,"姓名不能为空",Toast.LENGTH_SHORT).show();
            }else if (TextUtils.isEmpty(NumText.getText())){
                Toast.makeText(register.this,"学号不能为空",Toast.LENGTH_SHORT).show();
            }else if (TextUtils.isEmpty(PwdText.getText())){
                Toast.makeText(register.this,"密码不能为空",Toast.LENGTH_SHORT).show();
            }else if (TextUtils.isEmpty(PhoText.getText())) {
                Toast.makeText(register.this, "手机号不能为空", Toast.LENGTH_SHORT).show();
            }else if(flag){
                Toast.makeText(register.this, "该手机号已绑定学号", Toast.LENGTH_SHORT).show();
            }else if (colunm == -1){
                Toast.makeText(register.this,"该学号已注册",Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(register.this,"注册成功，请登录",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(register.this, login.class);
                startActivity(intent);
            }
        }
    };

    View.OnClickListener SendButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };
}
