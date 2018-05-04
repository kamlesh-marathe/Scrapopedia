package com.terminoz.scrapopedia;

import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class BackgroundWork extends AsyncTask<String,Void,String> {

    Context context;
    BackgroundWork(Context mcontext) {
        context=mcontext;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {

        String task = params[0];
        String login_url;
        String ip;
        ip="scrapopedia.ml";

        if (task.equals("analyse"))
        {
            try
            {
                login_url="http://"+ip+"/";
                URL url=new URL(login_url);
                HttpURLConnection httpURLConnection=(HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

//                OutputStream outputStream=httpURLConnection.getOutputStream();
//                BufferedWriter bufferedWriter= new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
//                String postdata = URLEncoder.encode("exampleInputEmail1","UTF-8")+"="+URLEncoder.encode(params[1],"UTF-8")+"&"+
//                        URLEncoder.encode("exampleInputPassword1","UTF-8")+"="+URLEncoder.encode(params[2],"UTF-8");
//
//                bufferedWriter.write(postdata);
//                bufferedWriter.flush();
//                bufferedWriter.close();
//                outputStream.close();

                InputStream inputStream=httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line="";
                while ((line=bufferedReader.readLine())!=null) {
                    result+=line;
                }

                bufferedReader.close();
                inputStream.close();
                return result;
            }
            catch (MalformedURLException e){e.printStackTrace();}
            catch (IOException e){e.printStackTrace();}
        }
        else if (task.equals("veg"))
        {
            try
            {
                login_url="http://"+ip+"/table.php";
                URL url=new URL(login_url);
                HttpURLConnection httpURLConnection=(HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                OutputStream outputStream=httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter= new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String postdata = URLEncoder.encode("vegname","UTF-8")+"="+URLEncoder.encode(params[1],"UTF-8");

                bufferedWriter.write(postdata);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream=httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line="";
                while ((line=bufferedReader.readLine())!=null) {
                    result+=line;
                }

                bufferedReader.close();
                inputStream.close();
                return result;
            }
            catch (MalformedURLException e){e.printStackTrace();}
            catch (IOException e){e.printStackTrace();}
        }

        return null;
    }

    @Override
    protected void onPostExecute(String aVoid) {
        super.onPostExecute(aVoid);
        if (this._task_finished_event != null) {
            this._task_finished_event.onTaskExecutionFinished(aVoid);
        }
    }

    private onTaskExecutionFinished _task_finished_event;

    public interface onTaskExecutionFinished {
        public void onTaskExecutionFinished(String Result);
    }

    public void setOnTaskFinishEvent(onTaskExecutionFinished _event) {
        if (_event != null) {
            this._task_finished_event = _event;
        }
    }

}
