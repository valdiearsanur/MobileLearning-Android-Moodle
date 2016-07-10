package mobile.download;

import java.util.regex.Pattern;

import com.mobile.learning.R;


import mobile.config.koneksiDownload;
import android.app.Activity;
import android.os.Bundle;
import android.text.util.Linkify;
import android.widget.TextView;

public class downText extends Activity {
	public koneksiDownload linkurl;
	String url;
	String SERVER_URL;
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linktext);
        
        TextView mTextLink = (TextView) findViewById(R.id.TextLink);
        Bundle bundle = this.getIntent().getExtras();
	      
        String param1 = bundle.getString("keyIdc");
        String param2 = bundle.getString("keyReference");
        linkurl = new koneksiDownload("moodledata/"+param1+"/"+param2);
		SERVER_URL = linkurl.getUrl();
     
        mTextLink.setText(SERVER_URL);
        Pattern pattern = Pattern.compile(SERVER_URL);
     
       Linkify.addLinks(mTextLink, pattern, "");
       
       
   
    }
}

