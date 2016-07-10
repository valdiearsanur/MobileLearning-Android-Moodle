package mobile.course;

import mobile.config.koneksi;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mobile.learning.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class courseDetailJs extends Activity  {
	public koneksi linkurl;
   TextView chapNum, judul, isi;
   String SERVER_URL ;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showdetailcourse);

 
        chapNum = (TextView) findViewById(R.id.ChapNum);
        judul = (TextView) findViewById(R.id.Title);
        isi = (TextView) findViewById(R.id.Content);
      
        
        Bundle bundle = this.getIntent().getExtras();
	      
        String keyBookId = bundle.getString("keyBookId");
        String keyTitle = bundle.getString("keyTitle");
        String keyChapNum = bundle.getString("keyChapNum");
        String keyContent = bundle.getString("keyContent");

        chapNum.setText(keyChapNum);
        judul.setText(keyTitle);
        isi.setText(keyContent);
        
    }
         
}


