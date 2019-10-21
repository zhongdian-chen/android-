package com.example.community;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mob.MobSDK;

import java.util.HashMap;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

public class register extends AppCompatActivity {

    private DBAdapter dbAdapter;
    private EditText NameText;
    private EditText NumText;
    private EditText PwdText;
    private EditText PwdCheckText;
    private EditText PhoText;
    private EditText checkCode;
    private Button ResButton;
    private Button SendButton;
    private ProgressDialog dialog;
    private String Pho;
    private String checkcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        MobSDK.init(this,"2cbc60dab5480","784ac7a301b994656848216b3646543a");

        //注册短信回调
        SMSSDK.registerEventHandler(ev);
        NameText = findViewById(R.id.nametext);
        NumText = findViewById(R.id.numtext);
        PwdText = findViewById(R.id.pwdtext);
        PwdCheckText = findViewById(R.id.pwd_checktext);
        PhoText = findViewById(R.id.photext);
        ResButton = findViewById(R.id.resbtn);
        checkCode = findViewById(R.id.val);
        ResButton.setOnClickListener(ResButtonListener);
        SendButton = findViewById(R.id.send);
        SendButton.setOnClickListener(SendButtonListener);
        TextView info1 = findViewById(R.id.info1);
        String str = "注册即代表阅读并同意<font color='black'>服务协议</font>和<font color='black'>隐私政策</font>";
        info1.setText(Html.fromHtml(str));
        dbAdapter = new DBAdapter(this);
        dbAdapter.open();
    }
    /**
     * 短信验证的回调监听
     */
    private EventHandler ev = new EventHandler() {
        @Override
        public void afterEvent(int event, int result, Object data) {
            if (result == SMSSDK.RESULT_COMPLETE) { //回调完成
                //提交验证码成功,如果验证成功会在data里返回数据。data数据类型为HashMap<number,code>
                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                    Log.e("TAG", "提交验证码成功" + data.toString());
                    HashMap<String, Object> mData = (HashMap<String, Object>) data;
                    String country = (String) mData.get("country");//返回的国家编号
                    String phone = (String) mData.get("phone");//返回用户注册的手机号

                    Log.e("TAG", country + "====" + phone);

                    if (phone.equals(Pho)) {
                        runOnUiThread(new Runnable() {//更改ui的操作要放在主线程，实际可以发送hander
                            @Override
                            public void run() {
                                showDailog("恭喜你！通过验证");
                                dialog.dismiss();
                            }
                        });
                    } else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                showDailog("验证失败");
                                dialog.dismiss();
                            }
                        });
                    }

                } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {//获取验证码成功
                    Log.e("TAG", "获取验证码成功");
                } else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {//返回支持发送验证码的国家列表

                }
            } else {
                ((Throwable) data).printStackTrace();
            }
        }
    };

    private void showDailog(String text) {
        new AlertDialog.Builder(this)
                .setTitle(text)
                .setPositiveButton("确定", null)
                .show();
    }

    View.OnClickListener ResButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            People people = new People();
            checkcode = checkCode.getText().toString();
            people.Name = NameText.getText().toString();
            people.Num = NumText.getText().toString();
            people.Pwd = MD5Util.encrypBy(PwdText.getText().toString());
            people.Pho = PhoText.getText().toString();
            long colunm = dbAdapter.insert(people);
            if (TextUtils.isEmpty(NameText.getText())){
                Toast.makeText(register.this,"姓名不能为空",Toast.LENGTH_SHORT).show();
            }else if (TextUtils.isEmpty(NumText.getText())){
                Toast.makeText(register.this,"学号不能为空",Toast.LENGTH_SHORT).show();
            }else if (TextUtils.isEmpty(PwdText.getText())){
                Toast.makeText(register.this,"密码不能为空",Toast.LENGTH_SHORT).show();
            }else if (TextUtils.isEmpty(PwdCheckText.getText())){
                Toast.makeText(register.this,"确认密码不能为空",Toast.LENGTH_SHORT).show();
            }else if (!PwdText.getText().toString().equals(PwdCheckText.getText().toString())){
                Toast.makeText(register.this,"两次密码不一致",Toast.LENGTH_SHORT).show();
            }else if (TextUtils.isEmpty(PhoText.getText())) {
                Toast.makeText(register.this, "手机号不能为空", Toast.LENGTH_SHORT).show();
            }else if (colunm == -1){
                Toast.makeText(register.this,"该学号已注册",Toast.LENGTH_SHORT).show();
            }else if (TextUtils.isEmpty(checkcode)) {
                Toast.makeText(register.this, "验证码不能为空", Toast.LENGTH_SHORT).show();
            } else {
                /**
                 * 向服务器提交验证码，在监听回调中监听是否验证
                 */
                dialog = ProgressDialog.show(register.this, null, "正在验证...", false, true);
                //提交短信验证码
                SMSSDK.submitVerificationCode("+86", Pho, checkcode);//国家号，手机号码，验证码
                Toast.makeText(register.this,"注册成功，请登录",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(register.this, login.class);
                startActivity(intent);
            }
        }
    };

    View.OnClickListener SendButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Boolean flag = false;
            Pho = PhoText.getText().toString();
            People[] peoples = dbAdapter.queryAllData();
            if (peoples != null)
                for (int i = 0; i < peoples.length; i++){
                    if (Pho.equals(peoples[i].Pho)) {
                        flag = true;
                        break;
                    }
                }

            Pho = PhoText.getText().toString().trim();
            //发送短信，传入国家号和电话号码
            if (TextUtils.isEmpty(Pho)) {
                toast("号码不能为空！");
            }else if (flag){
                Toast.makeText(register.this, "该手机号已绑定学号", Toast.LENGTH_SHORT).show();
            }else {
                SMSSDK.getVerificationCode("+86", Pho);
                toast("发送成功!");
            }
        }
    };

    public void toast(String info){
        Toast.makeText(register.this, info, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        SMSSDK.unregisterEventHandler(ev);
        super.onDestroy();
    }
}
