package mobile.config;

public class koneksi {
	
	String urlMobileLearning= "http://192.168.43.24/moodle_mobile";
	public koneksi(String urlml){
		urlMobileLearning = urlMobileLearning+urlml;
	}
	public String getUrl(){
		
		return urlMobileLearning;
	}
	
	
}
