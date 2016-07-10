package com.mobile.learning;

import mobile.config.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login extends Activity {
    public koneksi linkurl;
    String SERVER_URL;
    private Button login;
    private EditText username, password;
    public ProgressDialog progressDialog;

    //private TextView notif;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin);

        login = (Button) findViewById(R.id.login);
        username = (EditText) findViewById(R.id.uname);
        password = (EditText) findViewById(R.id.passwd);

        //final TextView notif = (TextView) findViewById(R.id.tv_error);

        login.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {

                // TODO Auto-generated method stub

                String Re;
                String mUsername = username.getText().toString();
                String mPassword = password.getText().toString();

                Re=tryLogin(mUsername, mPassword);

                Log.d("Check","Here");
                Log.d("Re",Re);
                String temp_check=Re.trim();
                if(temp_check.equals("1"))
                {
                    String nama = username.getText().toString();
                    Intent newIntent = new Intent(login.this, halamanUtama.class);
                    String txtnama = String.valueOf(nama);

                    //membuat Bundle
                    Bundle bundle = new Bundle();

                    //menentukan parameter Bundle (id,isi) --> id=nama dan isinya adalah variabel dari txtnama
                    bundle.putString("nama", txtnama);

                    //menambahkan bundle pada intent
                    newIntent.putExtras(bundle);
                    startActivityForResult(newIntent, 0);
                    //notif.setText("SUKSES");
                }
                else
                {
                    createDialog("Maaf", "Username Atau Password Salah !");
                }
            }
        });
    }
    protected String tryLogin(String mUsername, String mPassword)
    {
        Log.d(" TryLoginCheck ","Here");
        HttpURLConnection connection;
        OutputStreamWriter request = null;

        URL url = null;
        String response = null;
        String temp=null;
        String parameters = "username="+mUsername+"&password="+mPassword;
        System.out.println("UserName"+mUsername+"\n"+"password"+mPassword);
        Log.d("Parameters",parameters);
        try
        {
            ;
            linkurl = new koneksi("/login.php");
            SERVER_URL = linkurl.getUrl();
            //"http://182.6.232.37:80/ta/login.php"
            url = new URL(SERVER_URL);
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestMethod("POST");

            request = new OutputStreamWriter(connection.getOutputStream());
            request.write(parameters);
            request.flush();
            request.close();
            String line = "";
            InputStreamReader isr = new InputStreamReader(connection.getInputStream());
            BufferedReader reader = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            while ((line = reader.readLine()) != null)
            {

                sb.append(line + "\n");
            }
            temp=sb.toString();
            Log.d("Temp",temp);
            // Response from server after login process will be stored in response variable.
            response = sb.toString();
            Log.d("Response",response);
            Log.d("Sb Value",sb.toString());
            isr.close();
            reader.close();


        }
        catch(IOException e)
        {
            Toast.makeText(this,e.toString(),Toast.LENGTH_LONG).show();


        }
        // Log.d("Response",response);
        return response;
    }

    private void createDialog(String title, String text) {
        AlertDialog ad = new AlertDialog.Builder(this)
                .setPositiveButton("Ok", null)
                .setTitle(title)
                .setMessage(text)
                .create();
        ad.show();
    }

}
