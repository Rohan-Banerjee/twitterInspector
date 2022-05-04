package moderation;

import java.net.URI;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ModerateContent {

	public String moderateImageOld(String img) {
		
		String result;
		
HttpClient httpclient = HttpClients.createDefault();
		
		try {
		URIBuilder builder = new URIBuilder("https://api.moderatecontent.com/moderate/?key=7edb43a5bcb0d7ee61ceff1d65e983a6&url="+img);
		
		URI uri = builder.build();
		HttpPost request= new HttpPost(uri);
		 
		 //get response
		 HttpResponse response = httpclient.execute(request);
		 HttpEntity entity = response.getEntity();
		 
		 if(entity!=null)
		 {
			 String s = EntityUtils.toString(entity);
			 JSONObject object = new JSONObject(s);
			 String rating_label = object.getString("rating_label");
			 if(rating_label.toString().equalsIgnoreCase("teen"))
			 {
				 return "Partial Nudity";
			 }
			 else if(rating_label.toString().equalsIgnoreCase("adult"))
			 {
				 return "Raw Nudity";
			 }
			 else
			 {
				 return "Safe";
			 }
		 }
		}
		catch(Exception e)
		{
			return "error";
		}
		 
	return "error";
	}
	
	public String moderateImage(String img) {
		try {
			String api_key = "7edb43a5bcb0d7ee61ceff1d65e983a6";
			String url = "https://api.moderatecontent.com/moderate/";
			    OkHttpClient client = new OkHttpClient();
			            RequestBody requestBody = new MultipartBody.Builder()
			            .setType(MultipartBody.FORM)
			            .addFormDataPart("key", api_key)
			            .addFormDataPart("url", img)
			            .build();
			            Request request = new Request.Builder()
			              .url(url)
			              .post(requestBody)
			              .addHeader("cache-control", "no-cache")
			              .build();
			Response response = client.newCall(request).execute();
			String s = response.body().string();
			
		 System.out.println(s);
		 if(s!=null)
		 {
			 JSONObject object = new JSONObject(s);
			 String rating_label = object.getString("rating_label");
			 if(rating_label.toString().equalsIgnoreCase("teen"))
			 {
				 return "Partial Nudity";
			 }
			 else if(rating_label.toString().equalsIgnoreCase("adult"))
			 {
				 return "Raw Nudity";
			 }
			 else
			 {
				 return "Safe";
			 }
		 }
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
			return "error";
		}
		 
	return "error";
	}
	
	
	
	
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ModerateContent m = new ModerateContent();
		System.out.println(m.moderateImage("https://purecelebs.org/wp-content/uploads/2020-05-17-171250/melissa-barrera-nude-sex.jpg"));
	}

}
