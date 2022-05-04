package moderation;

import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class TextModerate {

	public ArrayList<String> moderateText(String msg) throws Exception {
		// TODO Auto-generated method stub
		ArrayList<String> ar = new ArrayList<>();
		try {
		HttpClient httpclient = HttpClients.createDefault();
		
		URIBuilder builder = new URIBuilder("https://api.moderatecontent.com/text/?key=7edb43a5bcb0d7ee61ceff1d65e983a6&msg="+msg);
		
		URI uri = builder.build();
		HttpPost request= new HttpPost(uri);
		 
		 //get response
		 HttpResponse response = httpclient.execute(request);
		 HttpEntity entity = response.getEntity();
		 
		 if(entity!=null)
		 {
			 String s = EntityUtils.toString(entity);
			 JSONObject object = new JSONObject(s);
			 JSONObject badwords = object.getJSONObject("bad_words");
			 String clean = object.getString("clean");
			 ar.add(clean);
			 String original = object.getString("origional_msg");
			 ar.add(original);
			 Iterator keys = badwords.keys();
			 int size=0;
			 while(keys.hasNext()) {
				    // loop to get the dynamic key
				    String currentDynamicKey = (String)keys.next();
				    ar.add(currentDynamicKey);
				    // get the value of the dynamic key
				    int word = badwords.getInt(currentDynamicKey);
				    String w = Integer.toString(word);
				    ar.add(w);
				}
			 return ar;
			 
		 } 
		}
		catch(java.net.UnknownHostException j)
		{
			ar.add("no internet");
			return ar;
		}
		catch(java.net.NoRouteToHostException n)
		{
			ar.add("no internet");
			return ar;
		}
		catch(Exception e)
		{
			//e.printStackTrace();
			ar.add("|empty|");
			return ar;
		}
		 
		return ar;

	}
	public static void main(String args[]) throws Exception
	{
		TextModerate tm = new TextModerate();
		System.out.println(tm.moderateText(""));
	}
}
