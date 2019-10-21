package com.example.community;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class dianfei3 extends AppCompatActivity {private String content;//二维码内容
    private int width=650, height=650;//宽度，高度
    private String error_correction_level="H", margin="1";//容错率，空白边距
    private int color_black=Color.BLACK, color_white=Color.WHITE;//黑色色块，白色色块
    private Bitmap qrcode_bitmap;//生成的二维码
    private ImageView iv_qrcode;

    private DianfeiInfoDBAapter dianfeiInfoDBAapter;
    private EditText money;
    private TextView info;
    private TextView elec;
    private Button charge;
    private String preelec;
    private String num,show1,show2,show3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dianfei3);
        iv_qrcode = findViewById(R.id.iv_qrcode);
        iv_qrcode.setOnLongClickListener(iv_listener);
        info=findViewById(R.id.info);
        elec=findViewById(R.id.elec);
        money=findViewById(R.id.money);
        charge=findViewById(R.id.charge);
        charge.setOnClickListener(chargeListener);
        Intent intent=this.getIntent();
        show1 = intent.getStringExtra("show1");
        show2 = intent.getStringExtra("show2");
        show3= intent.getStringExtra("show3");
        num = intent.getStringExtra("num");
        if(show1!=null)
            info.setText(show1);
        else if(show2!=null)
            info.setText(show2);
        else
            info.setText(show3);



        dianfeiInfoDBAapter = new DianfeiInfoDBAapter(this);
        dianfeiInfoDBAapter.open();

        DianfeiInfo[] dianfeiInfos = dianfeiInfoDBAapter.queryAllData();
        for (int i = 0; i < dianfeiInfos.length; i++)
            if (info.getText().toString().equals(dianfeiInfos[i].Addr)){
                preelec = dianfeiInfos[i].Elec;
                elec.setText(dianfeiInfos[i].Elec);
                break;
            }
    }


    View.OnLongClickListener iv_listener = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {DianfeiInfo dianfeiInfo = new DianfeiInfo();
            dianfeiInfo.Elec = String.valueOf(Integer.parseInt(money.getText().toString()) * 8 + Integer.parseInt(preelec));
            dianfeiInfoDBAapter.updateelec(dianfeiInfo, info.getText().toString());
            Toast.makeText(dianfei3.this,"充值成功",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(dianfei3.this, dianfei1.class);
            intent.putExtra("num",num);
            intent.putExtra("addr1",show1);
            intent.putExtra("addr1",show2);
            intent.putExtra("addr1",show3);
            startActivity(intent);
            return true;
        }
    };


    View.OnClickListener chargeListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            content = money.getText().toString();
            qrcode_bitmap = QRCodeUtil.createQRCodeBitmap(content, width, height, "UTF-8",
                    error_correction_level, margin, color_black, color_white);
            iv_qrcode.setImageBitmap(qrcode_bitmap);
        }
    };
}
