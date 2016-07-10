package mobile.tugas;

import mobile.config.*;
import mobile.upload.upload;
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
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mobile.learning.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class tugasDetailJs extends Activity  {
	public koneksi linkurl;
   TextView judulTugas, isiTugas;
   EditText jawaban;
   String SERVER_URL ;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showdetailtugas);

        jawaban = (EditText) findViewById(R.id.jawaban);
        judulTugas = (TextView) findViewById(R.id.JudulTugas);
        isiTugas = (TextView) findViewById(R.id.isiTugas);
        
        Bundle bundle = this.getIntent().getExtras();
	      
        final String param1 = bundle.getString("keyCourseId");
        final String param2 = bundle.getString("keyAssignId");
        final String param3 = bundle.getString("keyUserId");
        
        
        
        
        
        Button upload = (Button) findViewById(R.id.uploadT);
        upload.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String courseId = param1;
				String tugasId = param2;
				String userId = param3;
				
				Intent iUpload = new Intent(tugasDetailJs.this, upload.class);
				
				String txtCourseId = String.valueOf(courseId);
				String txtTugasId = String.valueOf(tugasId);
				String txtUserId = String.valueOf(userId);
				
				Bundle bundle = new Bundle();
				
				bundle.putString("idc", txtCourseId);
				bundle.putString("idtgs", txtTugasId);
				bundle.putString("idu", txtUserId);
				iUpload.putExtras(bundle);
				startActivity(iUpload);
				
				
			}
		});
        
        Button bt_jawaban = (Button) findViewById(R.id.kirim);
        bt_jawaban.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
	            postParameters.add(new BasicNameValuePair("jawab", jawaban.getText().toString()));

	            String response = null;

	            try {
	            	linkurl = new koneksi("/tugasKirimTeks.php?idtgs="+param2+"&idu="+param3);
					SERVER_URL = linkurl.getUrl();
	               response = tugasHttpClient.executeHttpPost(SERVER_URL, postParameters);

	               String res = response.toString();

	               res = res.trim();

	               res = res.replaceAll("\\s+","");
	               if(res.equals("1")){
	            	   createDialog("Selamat", "Jawaban Anda Berhasil Dikirim ^_^");
	               }else
	               {
	            	   createDialog("Maaf", "Jawaban Anda Gagal Terkirim , Anda Telat mengirim Tugas");
	               }
	            }

	            catch (Exception e) {

	               jawaban.setText(e.toString());

	            }

	         }

			
		});
        
        linkurl = new koneksi("/tugasDetailJson.php?idc="+param1+"&ida="+param2+"&idu="+param3);
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
                String detailTugas="";
                
                for(int i=0;i<jArr.length();i++){
                   JSONObject jObj = jArr.getJSONObject(i);
                  
                   String name = jObj.getString("name");
                   String alltext = jObj.getString("description");
                   
                   namaTugas += name+"\n";
                   detailTugas += alltext+"\n";
                }
                
                
                judulTugas.setText(namaTugas);
                isiTugas.setText(detailTugas);
                
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
    
	private void createDialog(String title, String text) {
        AlertDialog ad = new AlertDialog.Builder(this)
        .setPositiveButton("Ok", null)
        .setTitle(title)
        .setMessage(text)
        .create();
        ad.show();
    }

      
}



