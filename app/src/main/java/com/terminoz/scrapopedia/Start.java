package com.terminoz.scrapopedia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Start extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        initialisation();
    }

    private void initialisation() {
        Intent intent = new Intent(Start.this,Home.class);
        startActivity(intent);
        finish();
    }
}
