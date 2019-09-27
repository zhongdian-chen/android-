package com.example.community;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class fix extends AppCompatActivity {
    Button browse;
    ImageView image;
    private FixInfoDBAdapter fixInfoDBAdapter;
    private String Item;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fix);
        Intent intent = getIntent();
        final String Name = intent.getStringExtra("name");
        final String Num = intent.getStringExtra("num");
        final String Pho = intent.getStringExtra("pho");
        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
        final EditText briefinfo = (EditText) findViewById(R.id.briefinfo);
        final EditText addr = (EditText) findViewById(R.id.addr);
        Button sumbit=findViewById(R.id.submit);
        Button cancle=findViewById(R.id.cancel);
        //设置自动换行
        briefinfo.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
        briefinfo.setGravity(Gravity.TOP);
        briefinfo.setSingleLine(false);
        briefinfo.setHorizontallyScrolling(false);

        addr.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
        addr.setGravity(Gravity.TOP);
        addr.setSingleLine(false);
        addr.setHorizontallyScrolling(false);

        List<String> listspinner = new ArrayList<String>();
        listspinner.add("请选择报修项目");
        listspinner.add("水管类");
        listspinner.add("电管类");
        listspinner.add("木金、五金类");
        listspinner.add("综合类（含电焊、油漆涂料粉刷等）");
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listspinner);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter1);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Item = spinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        init();
        browse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image:/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, 1);
            }
        });



        fixInfoDBAdapter= new FixInfoDBAdapter(this);
        fixInfoDBAdapter.open();
        sumbit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FixInfo fixInfo = new FixInfo();
                fixInfo.Num = Num;
                fixInfo.BriefInfo = briefinfo.getText().toString();
                fixInfo.Item = Item;
                fixInfo.Addr = addr.getText().toString();
                long colunm = fixInfoDBAdapter.insert(fixInfo);
                if(TextUtils.isEmpty(briefinfo.getText())){
                    Toast.makeText(fix.this,"报修情况不为空",Toast.LENGTH_SHORT).show();
                }else if(Item.equals("请选择报修项目")){
                    Toast.makeText(fix.this,"请选择报修项目",Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(addr.getText())){
                    Toast.makeText(fix.this,"报修地址不为空",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(fix.this, "请等待工作人员前来维修", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(fix.this, main.class);
                    intent.putExtra("name",Name);
                    intent.putExtra("pho",Pho);
                    intent.putExtra("num",Num);
                    startActivity(intent);
                }
            }
        });

        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(fix.this, main.class);
                intent.putExtra("name",Name);
                intent.putExtra("pho",Pho);
                intent.putExtra("num",Num);
                startActivity(intent);
            }
        });

    }
    private void init(){
        browse = findViewById(R.id.browse);
        image = findViewById(R.id.image);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode==RESULT_OK){
            Uri uri=data.getData();
            ContentResolver cr=this.getContentResolver();
            try{
                Bitmap mBitmap= BitmapFactory.decodeStream(cr.openInputStream(uri));
                image.setImageBitmap(mBitmap);
            }catch (FileNotFoundException e){
                    e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode,resultCode,data);
    }
}
