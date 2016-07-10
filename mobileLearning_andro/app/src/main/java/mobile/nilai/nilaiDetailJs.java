package mobile.nilai;

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

public class nilaiDetailJs extends Activity  {
	public koneksi linkurl;
   TextView judulNilai, isiNilai;
   String SERVER_URL ;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showdetailnilai);

 
        judulNilai = (TextView) findViewById(R.id.JudulNilai);
        isiNilai = (TextView) findViewById(R.id.isiNilai);
        
        Bundle bundle = this.getIntent().getExtras();
	      
        final String param1 = bundle.getString("keyUserId");
        final String param2 = bundle.getString("keyAssignId");
        final String param3 = bundle.getString("keySubmisId");
        
      
        
        linkurl = new koneksi("/nilaiDetailJson.php?idu="+param1+"&ida="+param2+"&idsub="+param3);
		SERVER_URL = linkurl.getUrl();
     
        
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(SERVER_URL);
        
        //parameter
        ArrayList<NameValuePair> param = new ArrayList<NameValuePair>();{
        try {
           //add parameter
            httpPost.setEntity(new UrlEncodedFormEntity(param));
           
          HttpResponse httpRespose = httpClient.execute(httpPost);
          HttpEntity httpEntity = httpRespose.getEntity();
          
          //read content
          InputStream in = httpEntity.getContent();
          BufferedReader read = new BufferedReader(new InputStreamReader(in));
          
          String content = "";
          String line = "";
          
          while((line = read.readLine())!=null){
             content += line;
          }
          
          Log.d("ADBUG", "content: "+content);
          
          
          //json
          if(!content.equals("null")){
          
             try {
                JSONArray jArr = new JSONArray(content);
                String namaTugas="";
                String nilai="";
                
                for(int i=0;i<jArr.length();i++){
                   JSONObject jObj = jArr.getJSONObject(i);
                  
                   String name = jObj.getString("name");
                   String grade = jObj.getString("grade");
                   
                   namaTugas += name+"\n";
                   nilai += grade+"\n";
                }
                
                
                judulNilai.setText(namaTugas);
                isiNilai.setText(nilai);
                
             } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
             }
             
          }else{
             Toast.makeText(this, "Error", Toast.LENGTH_LONG).show();
          }
          
       } catch (ClientProtocolException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
       } catch (IOException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
       }
   // }
        }
        
    }
   

      
}



