package com.terminoz.scrapopedia;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Analysis extends AppCompatActivity {

    RecyclerView recyclerView;
    ProductAdapter adapter;
    String predict="";
    List<Product> productList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis);
//        getSupportActionBar().setDisplayShowTitleEnabled(false);

        productList = new ArrayList<>();
        recyclerView = findViewById(R.id.recycle_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        Intent intent=getIntent();
//        Bitmap bitmap;
//        byte[] byteArray = getIntent().getByteArrayExtra("image");
//        bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);

        BackgroundWork backgroundWork = new BackgroundWork(Analysis.this);
        backgroundWork.execute("analyse");
        backgroundWork.setOnTaskFinishEvent(new BackgroundWork.onTaskExecutionFinished() {
            @Override
            public void onTaskExecutionFinished(String Result) {
                Log.d("Analyse","Result "+Result);
                Toast.makeText(Analysis.this, "Result "+Result, Toast.LENGTH_SHORT).show();
                predict = Result;
                String [] items = predict.split(",");
                Log.d("Analyse","items "+items.length);
                for (int i=0;i<items.length;i++) {
                    Log.d("Analysis","Val "+items[i]);
                }
            }
        });


        productList.add(
                new Product(
                        1,
                        "Onion",
                        " Also known as bulb onions or common onions, they are vegetables and the most widely cultivated species of the genus Allium.",
                         R.drawable.onion
                )
        );

        productList.add(
                new Product(
                        2,
                        "Potato",
                        "The potato is a starchy, tuberous crop from the perennial nightshade Solanum tuberosum. Potato may be applied to both the plant and the edible tuber.",
                         R.drawable.potato
                )
        );

        productList.add(
                new Product(
                        3,
                        "Tomato",
                        "Tomato: The tomato is the edible, often red, fruit/berry of the plant Solanum lycopersicum, commonly known as a tomato plant.",
                         R.drawable.tomato
                )
        );

        productList.add(
                new Product(
                        4,
                        "Egg",
                        "Eggs are a very good source of inexpensive, high quality protein.",
                         R.drawable.egg
                )
        );




        Log.d("Debug","Product List "+productList.get(0));

        adapter = new ProductAdapter(this,productList);
        recyclerView.setAdapter(adapter);

    }
}
