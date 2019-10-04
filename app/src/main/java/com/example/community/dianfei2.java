package com.example.community;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class dianfei2 extends AppCompatActivity {
    private DianfeiInfoDBAapter dianfeiInfoDBAapter;
    private String addr, Num;
    private Button next;
    private Spinner gongyu, louhao, louceng, sushehao;
    private String[] louhao1,louhao2,louhao3,louceng1,louceng2,qinshihao1,qinshihao2,qinshihao3,qinshihao4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dianfei2);
        gongyu = findViewById(R.id.gongyu);
        louhao = findViewById(R.id.louhao);
        louceng = findViewById(R.id.louceng);
        sushehao = findViewById(R.id.sushehao);
        next = findViewById(R.id.next);
        next.setOnClickListener(nextListener);
        Intent intent = getIntent();
        Num = intent.getStringExtra("num");

        louhao1 = getResources().getStringArray(R.array.louhao1);
        louhao2 = getResources().getStringArray(R.array.louhao2);
        louhao3 = getResources().getStringArray(R.array.louhao3);
        louceng1 = getResources().getStringArray(R.array.louceng1);
        louceng2 = getResources().getStringArray(R.array.louceng2);
        qinshihao1 = getResources().getStringArray(R.array.qinshihao1);
        qinshihao2 = getResources().getStringArray(R.array.qinshihao2);
        qinshihao3 = getResources().getStringArray(R.array.qinshihao3);
        qinshihao4 = getResources().getStringArray(R.array.qinshihao4);

        List<String> listspinner4=new ArrayList<String>();
        listspinner4.add("请选择公寓");
        listspinner4.add("梁林公寓1");
        listspinner4.add("越秀校区2");
        listspinner4.add("商铺的收费");
        ArrayAdapter<String> adapter1=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, listspinner4);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gongyu.setAdapter(adapter1);
        gongyu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, final long l) {
                if(l==1){
                    final ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(dianfei2.this, android.R.layout.simple_spinner_item, louhao1);
                    adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    louhao.setAdapter(adapter2);
                    louhao.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            if ((l == 1) ||(l == 2) || (l == 3)) {
                                ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(dianfei2.this, android.R.layout.simple_spinner_item, louceng1);
                                adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                louceng.setAdapter(adapter4);
                                louceng.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        if (l == 1) {
                                            ArrayAdapter<String> adapter5 = new ArrayAdapter<String>(dianfei2.this, android.R.layout.simple_spinner_item, qinshihao1);
                                            adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                            sushehao.setAdapter(adapter5);
                                        }
                                        if (l == 2) {
                                            ArrayAdapter<String> adapter5 = new ArrayAdapter<String>(dianfei2.this, android.R.layout.simple_spinner_item, qinshihao2);
                                            adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                            sushehao.setAdapter(adapter5);
                                        }
                                        if (l == 3) {
                                            ArrayAdapter<String> adapter5 = new ArrayAdapter<String>(dianfei2.this, android.R.layout.simple_spinner_item, qinshihao3);
                                            adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                            sushehao.setAdapter(adapter5);
                                        }

                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {

                                    }
                                });
                            }
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                }
                if(l==2){
                    ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(dianfei2.this, android.R.layout.simple_spinner_item, louhao2);
                    adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    louhao.setAdapter(adapter3);
                    louhao.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            if ((l == 1) ||(l == 2) || (l == 3)) {
                                final ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(dianfei2.this, android.R.layout.simple_spinner_item, louceng1);
                                adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                louceng.setAdapter(adapter4);
                                louceng.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        if (l == 1) {
                                            ArrayAdapter<String> adapter5 = new ArrayAdapter<String>(dianfei2.this, android.R.layout.simple_spinner_item, qinshihao1);
                                            adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                            sushehao.setAdapter(adapter5);
                                        }
                                        if (l == 2) {
                                            ArrayAdapter<String> adapter5 = new ArrayAdapter<String>(dianfei2.this, android.R.layout.simple_spinner_item, qinshihao2);
                                            adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                            sushehao.setAdapter(adapter5);
                                        }
                                        if (l == 3) {
                                            ArrayAdapter<String> adapter5 = new ArrayAdapter<String>(dianfei2.this, android.R.layout.simple_spinner_item, qinshihao3);
                                            adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                            sushehao.setAdapter(adapter5);
                                        }

                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {

                                    }
                                });
                            }
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                }if(l==3){
                    final ArrayAdapter<String> adapter6 = new ArrayAdapter<String>(dianfei2.this, android.R.layout.simple_spinner_item, louhao3);
                    adapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    louhao.setAdapter(adapter6);
                    louhao.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            if(l==1){
                                final ArrayAdapter<String> adapter7 = new ArrayAdapter<String>(dianfei2.this, android.R.layout.simple_spinner_item, louceng2);
                                adapter7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                louceng.setAdapter(adapter7);
                                louceng.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        if(l==1){
                                            final ArrayAdapter<String> adapter8 = new ArrayAdapter<String>(dianfei2.this, android.R.layout.simple_spinner_item, qinshihao4);
                                            adapter8.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                            sushehao.setAdapter(adapter8);
                                            sushehao.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                @Override
                                                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                                }

                                                @Override
                                                public void onNothingSelected(AdapterView<?> adapterView) {

                                                }
                                            });
                                        }
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {

                                    }
                                });
                            }

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });

                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        dianfeiInfoDBAapter = new DianfeiInfoDBAapter(this);
        dianfeiInfoDBAapter.open();
    }

    View.OnClickListener nextListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (gongyu.getSelectedItem().toString().equals("请选择公寓")){
                Toast.makeText(dianfei2.this,"请选择公寓",Toast.LENGTH_SHORT).show();
            }else if(louhao.getSelectedItem().toString().equals("请选择楼号")){
                Toast.makeText(dianfei2.this,"请选择楼号",Toast.LENGTH_SHORT).show();
            }else if(louceng.getSelectedItem().toString().equals("请选择楼层")){
                Toast.makeText(dianfei2.this,"请选择楼层",Toast.LENGTH_SHORT).show();
            }else if(sushehao.getSelectedItem().toString().equals("请选择寝室号")){
                Toast.makeText(dianfei2.this,"请选择寝室号",Toast.LENGTH_SHORT).show();
            }else {
                addr = gongyu.getSelectedItem().toString()+louhao.getSelectedItem().toString()+louceng.getSelectedItem().toString()+sushehao.getSelectedItem().toString();
                DianfeiInfo dianfeiInfo=new DianfeiInfo();
                DianfeiInfo[] dianfeiInfos = dianfeiInfoDBAapter.queryAllData();
                if ( dianfeiInfos!=null) {
                    for (int i = 0; i < dianfeiInfos.length; i++) {
                        if (Num.equals(dianfeiInfos[i].Num)){
                            if (addr.equals(dianfeiInfos[i].Addr)){
                                break;
                            }
                        }
                        if (i + 1 == dianfeiInfos.length) {
                            dianfeiInfo.Num = Num;
                            dianfeiInfo.Addr = addr;
                            dianfeiInfo.Elec = "0";
                            dianfeiInfoDBAapter.insert(dianfeiInfo);
                        }
                    }
                }else{
                    dianfeiInfo.Num = Num;
                    dianfeiInfo.Addr = addr;
                    dianfeiInfo.Elec = "0";
                    dianfeiInfoDBAapter.insert(dianfeiInfo);
                }
                Intent intent=new Intent(dianfei2.this,dianfei1.class);
                intent.putExtra("num",Num);
                intent.putExtra("gongyu",gongyu.getSelectedItem().toString());
                intent.putExtra("addr",addr);
                startActivity(intent);
            }
        }
    };
}