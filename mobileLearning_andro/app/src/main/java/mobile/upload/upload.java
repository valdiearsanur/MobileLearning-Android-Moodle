package mobile.upload;

import mobile.config.koneksi;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.mobile.learning.R;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class upload extends ListActivity {
	public koneksi linkurl;
	String SERVER_URL;
	private List<String> item = null;
	private List<String> path = null;
	private String root="/";
	private EditText myPathText;
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upload);
        Bundle bundle = this.getIntent().getExtras();
        final String param1 = bundle.getString("idc");
        final String param2 = bundle.getString("idtgs");
        final String param3 = bundle.getString("idu");
       
        
        Button up = (Button) findViewById(R.id.uploading);
        myPathText = (EditText) findViewById(R.id.alamat);
  
        getDir(root);
        
        up.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try
	        	{
	        		linkurl = new koneksi("/uploadProses.php?idc="+param1+"&idtgs="+param2+"&idu="+param3);
	    			SERVER_URL = linkurl.getUrl();
	        		Uploader uploader = new Uploader();
    	        	uploader.setUrlAndFile(SERVER_URL, ((EditText)findViewById(R.id.alamat)).getText().toString(),(TextView)(findViewById(R.id.info)));
    	        	uploader.execute();
	        	}
	        	catch(Exception e)
	        	{
	        		((EditText)findViewById(R.id.alamat)).setText(e.toString());
	        	}    
			}
		});
    }
    
    private void getDir(String dirPath)
    {
    	myPathText.setText(dirPath);
    	
    	item = new ArrayList<String>();
    	path = new ArrayList<String>();
    	
    	File f = new File(dirPath);
    	File[] files = f.listFiles();
    	
    	if(!dirPath.equals(root))
    	{

    		item.add(root);
    		path.add(root);
    		
    		item.add("../");
    		path.add(f.getParent());
            
    	}
    	
    	for(int i=0; i < files.length; i++)
    	{
    			File file = files[i];
    			path.add(file.getPath());
    			if(file.isDirectory())
    				item.add(file.getName() + "/");
    			else
    				item.add(file.getName());
    			
    	}
    		
    	ArrayAdapter<String> fileList =
    		new ArrayAdapter<String>(this, R.layout.row, item);
    	setListAdapter(fileList);
    }

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		
		File file = new File(path.get(position));
		
		if (file.isDirectory())
		{
			if(file.canRead())
				getDir(path.get(position));
			else
			{
				new AlertDialog.Builder(this)
				
				.setTitle("[" + file.getName() + "] folder can't be read!")
				.setPositiveButton("OK", 
						new DialogInterface.OnClickListener() {
							
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
							}
						}).show();
			}
		}
		else	
		{
				myPathText.setText(file.getPath());
		}
	}
}