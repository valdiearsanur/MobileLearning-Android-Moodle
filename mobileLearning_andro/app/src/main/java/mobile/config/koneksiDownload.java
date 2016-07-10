package mobile.config;

public class koneksiDownload {
	
	String urlMobileLearning= "http://192.168.43.24/moodle_mobile";
	public koneksiDownload(String urlml){
		urlMobileLearning = urlMobileLearning+urlml;
	}
	public String getUrl(){
		
		return urlMobileLearning;
	}
	
	
}

