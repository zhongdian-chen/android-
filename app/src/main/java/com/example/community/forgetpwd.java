package com.example.community;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mob.MobSDK;

import java.util.HashMap;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

public class forgetpwd extends AppCompatActivity {

    private DBAdapter dbAdapter;
    private Button nextbtn;
    private Button sendbtn;
    private EditText phoneedit;
    private EditText checkCode;
    private String checkcode;
    private String pho;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgetpwd);

        MobSDK.init(this,"2cbc60dab5480","784ac7a301b994656848216b3646543a");

        //注册短信回调
        SMSSDK.registerEventHandler(ev);
        phoneedit = findViewById(R.id.phone);
        checkCode = findViewById(R.id.val);
        sendbtn = findViewById(R.id.send);
        sendbtn.setOnClickListener(sendbtnListener);
        nextbtn = findViewById(R.id.nextbtn);
        nextbtn.setOnClickListener(nextbtnListener);
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

                    if (phone.equals(pho)) {
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

    View.OnClickListener nextbtnListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            People[] peoples = dbAdapter.queryAllData();
            String Pho = phoneedit.getText().toString();
            checkcode = checkCode.getText().toString();
            if (TextUtils.isEmpty(checkcode)) {
                Toast.makeText(forgetpwd.this, "验证码不能为空", Toast.LENGTH_SHORT).show();
            }else {
                for (int i = 0; i < peoples.length; i++) {
                    if (Pho.equals(peoples[i].Pho)) {
                        /**
                         * 向服务器提交验证码，在监听回调中监听是否验证
                         */
                        dialog = ProgressDialog.show(forgetpwd.this, null, "正在验证...", false, true);
                        //提交短信验证码
                        SMSSDK.submitVerificationCode("+86", Pho, checkcode);//国家号，手机号码，验证码
                        Toast.makeText(forgetpwd.this, "验证成功", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(forgetpwd.this, pwd.class);
                        intent.putExtra("pho", Pho);
                        startActivity(intent);
                        break;
                    }
                    if (i + 1 == peoples.length) {
                        Toast.makeText(forgetpwd.this, "该手机号未注册", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    };


    View.OnClickListener sendbtnListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            pho = phoneedit.getText().toString().trim();
            //发送短信，传入国家号和电话号码
            if (TextUtils.isEmpty(pho)) {
                toast("号码不能为空！");
            } else {
                SMSSDK.getVerificationCode("+86", pho);
                toast("发送成功!");
            }
        }
    };

    public void toast(String info){
        Toast.makeText(forgetpwd.this, info, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        SMSSDK.unregisterEventHandler(ev);
        super.onDestroy();
    }
}
