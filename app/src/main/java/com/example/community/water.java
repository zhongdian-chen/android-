package com.example.community;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class water extends AppCompatActivity {
    private WaterInfoDBAdapter waterInfoDBAdapter;
    private String XiaoQuItem;
    private String LouItem;
    private String WaterItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.water);
        final TextView price = findViewById(R.id.price);
        final TextView sum = findViewById(R.id.sum);
        final Spinner spinner1=(Spinner)findViewById(R.id.spinner1);
        final Spinner spinner2=(Spinner)findViewById(R.id.spinner2);
        final Spinner spinner3=(Spinner)findViewById(R.id.spinner3);
        final EditText room=(EditText) findViewById(R.id.room);
        final EditText count=(EditText)findViewById(R.id.count);
        final String[] loudong1 = getResources().getStringArray(R.array.loudong1);
        final String[] loudong2 = getResources().getStringArray(R.array.loudong2);
        Button check=findViewById(R.id.check);
        Button cancle = findViewById(R.id.cancel);
        final TextView name = findViewById(R.id.name);
        final TextView pho = findViewById(R.id.pho);
        Intent intent = getIntent();
        final String Name = intent.getStringExtra("name");
        final String Pho = intent.getStringExtra("pho");
        final String Num = intent.getStringExtra("num");
        name.setText(Name);
        pho.setText(Pho);


        List<String> listspinner=new ArrayList<String>();
        listspinner.add("请选择校区");
        listspinner.add("越秀校区南区");
        listspinner.add("越秀校区北区");
        XiaoQuItem = "请选择校区";
        ArrayAdapter<String> adapter1=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, listspinner);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(l==1){
                    XiaoQuItem = spinner1.getSelectedItem().toString();
                    final ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(water.this, android.R.layout.simple_spinner_item, loudong1);
                    adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner2.setAdapter(adapter2);
                    spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            LouItem = spinner2.getSelectedItem().toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }
                if(l==2){
                    XiaoQuItem = spinner1.getSelectedItem().toString();
                    final ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(water.this, android.R.layout.simple_spinner_item, loudong2);
                    adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    adapter3.notifyDataSetChanged();
                    spinner2.setAdapter(adapter3);
                    spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            LouItem = spinner2.getSelectedItem().toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        List<String> listspinner2=new ArrayList<String>();
        listspinner2.add("请选择桶装水");
        listspinner2.add("山泉水（小桶）");
        listspinner2.add("山泉水（大桶）");
        listspinner2.add("纯净水");
        listspinner2.add("12L农夫山泉（一次性桶）");
        ArrayAdapter<String> adapter3=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, listspinner2);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adapter3);
        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                WaterItem = spinner3.getSelectedItem().toString();
                if (WaterItem.equals("山泉水（小桶）"))
                    price.setText("6元/桶");
                else if(WaterItem.equals("山泉水（大桶）"))
                    price.setText("9元/桶");
                else if(WaterItem.equals("纯净水"))
                    price.setText("15元/桶");
                else if(WaterItem.equals("12L农夫山泉（一次性桶）"))
                    price.setText("25元/桶");
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        count.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                String oneprice = price.getText().toString();
                Pattern p=Pattern.compile("[a-zA-Z]");
                Matcher m=p.matcher(count.getText().toString());
                if (actionId == EditorInfo.IME_ACTION_DONE){
                    if( !count.getText().toString().equals("") ){
                        if (WaterItem.equals("请选择桶装水"))
                            Toast.makeText(water.this,"请先选择桶装水",Toast.LENGTH_SHORT).show();
                        else{
                            if (m.matches() || Integer.parseInt(count.getText().toString())>5 || Integer.parseInt(count.getText().toString())<1 )
                                Toast.makeText(water.this,"桶装水数量应为1-2桶",Toast.LENGTH_SHORT).show();
                            else{
                                String sumprice = String.valueOf(Integer.parseInt(count.getText().toString()) * Integer.parseInt(oneprice.substring(0, oneprice.indexOf("元"))));
                                sum.setText(sumprice);
                            }
                        }
                    }
                }
                return false;
            }
        });



        waterInfoDBAdapter = new WaterInfoDBAdapter(this);
        waterInfoDBAdapter.open();
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                WaterInfo waterInfo = new WaterInfo();
                waterInfo.Num = Num;
                waterInfo.XiaoQuItem = XiaoQuItem;
                waterInfo.LouItem = LouItem;
                waterInfo.RoomNum = room.getText().toString();
                waterInfo.WaterItem = WaterItem;
                waterInfo.Count = count.getText().toString();
                waterInfoDBAdapter.insert(waterInfo);
                if(XiaoQuItem.equals("请选择校区")){
                    Toast.makeText(water.this,"请选择校区",Toast.LENGTH_SHORT).show();
                }else if(LouItem.equals("请选择楼号")){
                    Toast.makeText(water.this,"请选择楼号",Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(room.getText())){
                    Toast.makeText(water.this,"房间号不能为空",Toast.LENGTH_SHORT).show();
                }else if(WaterItem.equals("请选择桶装水")){
                    Toast.makeText(water.this,"请选择桶装水",Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(count.getText())){
                    Toast.makeText(water.this,"请输入桶装水数量",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(water.this, "工作人员正在加急处理中", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(water.this, main.class);
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
                Intent intent = new Intent(water.this, main.class);
                intent.putExtra("name",Name);
                intent.putExtra("pho",Pho);
                intent.putExtra("num",Num);
                startActivity(intent);
            }
        });

    }



}
