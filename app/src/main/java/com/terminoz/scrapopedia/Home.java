package com.terminoz.scrapopedia;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.ByteArrayOutputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initialisation();
    }

    ImageView imageView;
    Button cam_btn;
    Bitmap bitmap;
    String url="http://scrapopedia.ml/upload.php";
    int cnt;


    private void initialisation() {
        cnt=1;
        imageView = findViewById(R.id.imageview);
        cam_btn = findViewById(R.id.open_camera);
        cam_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,0);
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            bitmap = (Bitmap)data.getExtras().get("data");
            imageView.setImageBitmap(bitmap);
            UploadImage();
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

    public String getStringImage(Bitmap bitmap){
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
        byte[] imgBytes = stream.toByteArray();
        String endcode = Base64.encodeToString(imgBytes,Base64.DEFAULT);
        return endcode;
    }

    private void UploadImage() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String s = response.trim();
                if (s.equalsIgnoreCase("success")){
                    Toast.makeText(Home.this, "Uploaded...", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(Home.this, "Not Uploaded", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                String ten = "./pred/"+cnt+"a.png";
                cnt++;
                String image = getStringImage(bitmap);
                Map<String,String> params = new HashMap<String, String>();

                params.put("TEN",ten);
                params.put("HINH",image);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

}
