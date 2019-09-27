package com.example.community;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class dianfei2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dianfei2);
        final Spinner gongyu=(Spinner)findViewById(R.id.gongyu);
        final Spinner louhao=(Spinner)findViewById(R.id.louhao);
        final Spinner louceng=(Spinner)findViewById(R.id.louceng);
        final Spinner sushehao=(Spinner)findViewById(R.id.sushehao);
        Button next=(Button)findViewById(R.id.next);

        final String[] louhao1 = getResources().getStringArray(R.array.louhao1);
        final String[] louhao2 = getResources().getStringArray(R.array.louhao2);
        final String[] louceng1 = getResources().getStringArray(R.array.louceng1);
        final String[] qinshihao1 = getResources().getStringArray(R.array.qinshihao1);
        final String[] qinshihao2 = getResources().getStringArray(R.array.qinshihao2);
        final String[] qinshihao3 = getResources().getStringArray(R.array.qinshihao3);

        List<String> listspinner4=new ArrayList<String>();
        listspinner4.add("请选择公寓");
        listspinner4.add("梁林公寓1");
        listspinner4.add("越秀校区2");
        listspinner4.add("商铺收费");
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
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        next.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(dianfei2.this,dianfei1.class);
                String str1=(String)gongyu.getSelectedItem();
                String str2=(String)louhao.getSelectedItem();
                String str3=(String)louceng.getSelectedItem();
                String str4=(String)sushehao.getSelectedItem();
                intent.putExtra("公寓",str1);
                intent.putExtra("楼号",str2);
                intent.putExtra("楼层",str3);
                intent.putExtra("寝室号",str4);
                startActivity(intent);
            }
        });
    }
}