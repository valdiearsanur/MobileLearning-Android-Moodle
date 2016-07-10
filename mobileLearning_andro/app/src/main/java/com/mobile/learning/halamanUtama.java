package com.mobile.learning;

import mobile.course.*;
import mobile.berita.*;
import mobile.download.*;
import mobile.tugas.*;
import mobile.nilai.*;
import mobile.about.*;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class halamanUtama extends Activity {
   public void onCreate(Bundle savedInstanceState){
      super.onCreate(savedInstanceState);
      setContentView(R.layout.welcome);
      
      Button ibCourse = (Button) findViewById(R.id.ibKonten);
      Button ibDonlot = (Button) findViewById(R.id.ibDonlot);
      Button ibTugas = (Button) findViewById(R.id.ibTugas);
      Button ibNilai = (Button) findViewById(R.id.ibNilai);
      Button ibBerita = (Button) findViewById(R.id.ibBerita);
      Button ibAbout = (Button) findViewById(R.id.ibAbout);
      Bundle bundle = this.getIntent().getExtras();
      
      //Mengambil parameter 1 yaitu kode = nama
      final String param1 = bundle.getString("nama");
      TextView tv = (TextView)findViewById(R.id.welText);
      TextView tv_logOut = (TextView) findViewById(R.id.logOut);
      
      tv.setText("Selamat Datang -" +param1+ "- di M-Learning");
      
      tv_logOut.setOnClickListener(new OnClickListener() {
		
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent logout = new Intent(halamanUtama.this, login.class);
			startActivity(logout);
		}
	});
      ibCourse.setOnClickListener(new OnClickListener() {
		
		public void onClick(View v) {
			// TODO Auto-generated method stub
			String namauser = param1;
            Intent newIntent = new Intent(halamanUtama.this, course.class);
            String txtnama = String.valueOf(namauser);
            
            //membuat Bundle
            Bundle bundle = new Bundle();
            
            //menentukan parameter Bundle (id,isi) --> id=nama dan isinya adalah variabel dari txtnama
            bundle.putString("namauser", txtnama);
            
            //menambahkan bundle pada intent
            newIntent.putExtras(bundle);
            startActivityForResult(newIntent, 0);
		}
	});
    
      
      ibDonlot.setOnClickListener(new OnClickListener() {
		
		public void onClick(View v) {
			// TODO Auto-generated method stub
			String namauser = param1;
            Intent newIntent = new Intent(halamanUtama.this, download.class);
            String txtnama = String.valueOf(namauser);
            
            //membuat Bundle
            Bundle bundle = new Bundle();
            
            //menentukan parameter Bundle (id,isi) --> id=nama dan isinya adalah variabel dari txtnama
            bundle.putString("namauser", txtnama);
            
            //menambahkan bundle pada intent
            newIntent.putExtras(bundle);
            startActivityForResult(newIntent, 0);
		}
	});
      
      ibTugas.setOnClickListener(new OnClickListener() {
		
		public void onClick(View v) {
			// TODO Auto-generated method stub
			String namauser = param1;
            Intent newIntent = new Intent(halamanUtama.this, pilihTugas.class);
            String txtnama = String.valueOf(namauser);
            
            //membuat Bundle
            Bundle bundle = new Bundle();
            
            //menentukan parameter Bundle (id,isi) --> id=nama dan isinya adalah variabel dari txtnama
            bundle.putString("namauser", txtnama);
            
            //menambahkan bundle pada intent
            newIntent.putExtras(bundle);
            startActivityForResult(newIntent, 0);
		}
	});
      
      ibNilai.setOnClickListener(new OnClickListener() {
		
		public void onClick(View v) {
			// TODO Auto-generated method stub
			String namauser = param1;
            Intent newIntent = new Intent(halamanUtama.this, nilai.class);
            String txtnama = String.valueOf(namauser);
            
            //membuat Bundle
            Bundle bundle = new Bundle();
            
            //menentukan parameter Bundle (id,isi) --> id=nama dan isinya adalah variabel dari txtnama
            bundle.putString("namauser", txtnama);
            
            //menambahkan bundle pada intent
            newIntent.putExtras(bundle);
            startActivityForResult(newIntent, 0);
		}
	});
      
      ibBerita.setOnClickListener(new OnClickListener() {
		
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intentBerita = new Intent(halamanUtama.this, berita.class);
			startActivity(intentBerita);
		}
	});
      
      ibAbout.setOnClickListener(new OnClickListener() {
		
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intentAbout = new Intent(halamanUtama.this, about.class);
			startActivity(intentAbout);
		}
	});
   }
}
