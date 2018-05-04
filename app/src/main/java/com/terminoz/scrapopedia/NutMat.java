package com.terminoz.scrapopedia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import java.util.ArrayList;

public class NutMat extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nut_mat);
        init();
    }

    String vegs;
    String vege;
    Button button;

    private void init() {
        Intent intent = getIntent();
        ArrayList<String> arrayList = intent.getStringArrayListExtra("vegs");
        int bol=0;
        vegs="";
        for (String x:arrayList) {
            if (bol!=0) {
                vegs+=",";
            }
            vegs+=x;
            bol=1;
        }
        Log.d("Post Send ",vegs);
        BackgroundWork backgroundWork =new BackgroundWork(this);
        backgroundWork.execute("nutmin",vegs);
        backgroundWork.setOnTaskFinishEvent(new BackgroundWork.onTaskExecutionFinished() {
            @Override
            public void onTaskExecutionFinished(String Result) {
                vege = Result;
                Log.d("Result ",Result);
                String [] items = vege.split(",");
                button = findViewById(R.id.calories);
                button.setText(items[0]);
                button = findViewById(R.id.water);
                button.setText(items[1]+" %");
                button = findViewById(R.id.protein);
                button.setText(items[2]+" G");
                button = findViewById(R.id.carbs);
                button.setText(items[3]+" G");
                button = findViewById(R.id.sugar);
                button.setText(items[4]+" G");
                button = findViewById(R.id.fiber);
                button.setText(items[5]+" G");
                button = findViewById(R.id.Fat);
                button.setText(items[6]+" G");
                button = findViewById(R.id.Saturated_Fat);
                button.setText(items[7]+" G");
                button = findViewById(R.id.mono);
                button.setText(items[8]+" G");
            }
        });
    }
}
