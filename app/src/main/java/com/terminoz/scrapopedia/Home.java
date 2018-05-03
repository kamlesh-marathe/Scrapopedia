package com.terminoz.scrapopedia;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.net.URI;
import java.util.ArrayList;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initialisation();
    }

    ImageView imageView;
    Button cam_btn;
    Button up_btn;
    Bitmap bitmap;
    ArrayList<Bitmap> bitmaparray;

    int cnt;

    private void initialisation() {
        cnt=0;
        bitmaparray = new ArrayList<Bitmap>();
        imageView = findViewById(R.id.imageview);
        cam_btn = findViewById(R.id.open_camera);
        up_btn = findViewById(R.id.upload);
        cam_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,0);
            }
        });

        up_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bitmaparray.add(bitmap);
                Toast.makeText(getBaseContext(), "Image Uploaded", Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        bitmap = (Bitmap)data.getExtras().get("data");
        imageView.setImageBitmap(bitmap);
        if (resultCode == RESULT_OK) {

        }
        else if (resultCode == RESULT_CANCELED) {
            Toast.makeText(this, "Cancelled by User", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Sorry Failed to Capture Image", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id= item.getItemId();
        if (id==R.id.action_next) {
            Intent intent = new Intent(Home.this,Analysis.class);

            ByteArrayOutputStream bStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100,bStream);
            byte[] byteArray = bStream.toByteArray();
            intent.putExtra("image", byteArray);

            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.home, menu);
        return true;
    }

}
