package mobile.tugas;

import com.mobile.learning.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class pilihTugas extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pilihantugas);
        Bundle bundle = this.getIntent().getExtras();
        final String param1 = bundle.getString("namauser");
        
        Button entryTugas = (Button) findViewById(R.id.entryTugas);
        Button updateTugas = (Button) findViewById(R.id.updateTugas);
        
        entryTugas.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String username = param1;
	            Intent newIntent = new Intent(pilihTugas.this, tugas.class);
	            String txtnama = String.valueOf(username);
	            
	            //membuat Bundle
	            Bundle bundle = new Bundle();
	            
	            //menentukan parameter Bundle (id,isi) --> id=nama dan isinya adalah variabel dari txtnama
	            bundle.putString("username", txtnama);
	            
	            //menambahkan bundle pada intent
	            newIntent.putExtras(bundle);
	            startActivityForResult(newIntent, 0);
			}
		});
        
        updateTugas.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String username = param1;
	            Intent newIntent = new Intent(pilihTugas.this, tugasUp.class);
	            String txtnama = String.valueOf(username);
	            
	            //membuat Bundle
	            Bundle bundle = new Bundle();
	            
	            //menentukan parameter Bundle (id,isi) --> id=nama dan isinya adalah variabel dari txtnama
	            bundle.putString("username", txtnama);
	            
	            //menambahkan bundle pada intent
	            newIntent.putExtras(bundle);
	            startActivityForResult(newIntent, 0);
			}
		});
        
    }
}
