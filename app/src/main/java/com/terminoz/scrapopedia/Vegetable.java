package com.terminoz.scrapopedia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

public class Vegetable extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vegetable);
        init();
    }

    String Vegname;
    String vege;
    Button button;

    private void init() {
        Intent intent = getIntent();
        Vegname = intent.getStringExtra("Veg");
        button = findViewById(R.id.veg_name);
        button.setText(Vegname);

//        Toast.makeText(this, "Vegname "+Vegname, Toast.LENGTH_SHORT).show();
        BackgroundWork backgroundWork = new BackgroundWork(this);
        backgroundWork.execute("veg",Vegname);
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
