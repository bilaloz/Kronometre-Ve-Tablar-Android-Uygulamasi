package com.kronometreuygulamasi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button t_yeni,b_start,b_stop;
    TextView tSonuc;
    TabHost tabHost;
    int i=4,miliSaniye=0;
    long start=0,stop=0,sonuc=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t_yeni = (Button)findViewById(R.id.btn3);
        t_yeni.setOnClickListener(this);
        b_start=(Button)findViewById(R.id.bStart);
        b_stop=(Button)findViewById(R.id.bStop);
        tSonuc=(TextView)findViewById(R.id.tSonuc);
        b_start.setOnClickListener(this);
        b_stop.setOnClickListener(this);

        tabHost=(TabHost)findViewById(R.id.tabhost);
        tabHost.setup();

        TabHost.TabSpec tabOzellikleri;
        tabOzellikleri=tabHost.newTabSpec("tag1");
        tabOzellikleri.setContent(R.id.tab1);
        tabOzellikleri.setIndicator("Kronometre");
        tabHost.addTab(tabOzellikleri);


        tabOzellikleri=tabHost.newTabSpec("tag2");
        tabOzellikleri.setContent(R.id.tab2);
        tabOzellikleri.setIndicator("2.TAP");
        tabHost.addTab(tabOzellikleri);

        tabOzellikleri=tabHost.newTabSpec("tag3");
        tabOzellikleri.setContent(R.id.tab3);
        tabOzellikleri.setIndicator("TAB EKLE");
        tabHost.addTab(tabOzellikleri);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.btn3:
                TabHost.TabSpec yeniTab= tabHost.newTabSpec("Tag4");
                yeniTab.setContent(new TabHost.TabContentFactory() {
                    @Override
                    public View createTabContent(String s) {
                        TextView text = new TextView(MainActivity.this);
                        text.setText("Yeni tab oluşturuldu");
                        i++;
                        return text;
                    }
                });
                yeniTab.setIndicator(i+" ninci tab");
                tabHost.addTab(yeniTab);
                break;
            case R.id.bStart:
                start=System.currentTimeMillis();
                tSonuc.setText("0 : 00 : 00 : 00");
                break;
            case R.id.bStop:
                stop= System.currentTimeMillis();

                if(start!=0){
                    sonuc=stop-start;
                    miliSaniye=(int)sonuc;
                    int saniye=(int)sonuc/1000;
                    int dakika=saniye/60;
                    int saat=dakika/60;
                    miliSaniye=miliSaniye%100;
                    dakika=dakika%60;
                    tSonuc.setText(String.format("%d : %02d : %02d : %02d ",saat,dakika,saniye,miliSaniye));
                    start=0;
                }
                else
                    tSonuc.setText("0 : 00 : 00 : 00");
                break;

        }

    }
}
