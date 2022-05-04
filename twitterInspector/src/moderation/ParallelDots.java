package moderation;

import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;
import java.util.ArrayList;

import org.json.JSONObject;
import org.json.simple.JSONArray;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.paralleldots.paralleldots.App;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.simple.parser.JSONParser;

public class ParallelDots {
	
	public String emotionDetect(String content) throws IOException
	{
		try {
		ArrayList<String> ar = new ArrayList<>();
		String api_key = "DckW3PNsDOhP2tM1iuvD9pQthDfkwDOk8nvm7gQTZYE";
		String text = content;
		String url = "https://apis.paralleldots.com/v4/emotion";
		    OkHttpClient client = new OkHttpClient();
		            RequestBody requestBody = new MultipartBody.Builder()
		            .setType(MultipartBody.FORM)
		            .addFormDataPart("api_key", api_key)
		            .addFormDataPart("text", text)
		            .build();
		            Request request = new Request.Builder()
		              .url(url)
		              .post(requestBody)
		              .addHeader("cache-control", "no-cache")
		              .build();
	Response response = client.newCall(request).execute();
	System.out.println(response);
		String s = response.body().string();
		System.out.println("New response="+s);
		JSONObject object = new JSONObject(s);
		JSONObject emotion = object.getJSONObject("emotion");
		Double happy = emotion.getDouble("Happy");
		Double sad = emotion.getDouble("Sad");
		Double angry = emotion.getDouble("Angry");
		Double fear = emotion.getDouble("Fear");
		Double excited = emotion.getDouble("Excited");
		Double indifferent = 0.0;
		//Double indifferent = emotion.getDouble("Indifferent");
		
		if(happy>sad && happy > angry && happy > fear && happy > excited && happy > indifferent)
		{
			return "Happy";
		}
		else if(sad>happy && sad > angry && sad > fear && sad > excited && sad > indifferent)
		{
			return "Sad";
		}
		else if(angry > happy && angry > sad && angry > fear && angry > excited && angry > indifferent)
		{
			return "Angry";
		}
		else if(fear>sad && fear > angry && fear > happy && fear > excited && fear > indifferent)
		{
			return "Fear";
		
		}
		else if(excited >sad && excited > angry && excited > fear && excited > happy && excited > indifferent)
		{
			return "Excited";
		}
		else
		{
			return "Indifferent";
		}
		}
		catch(Exception e)
		{
//			int randomNum = ThreadLocalRandom.current().nextInt(0, 4 + 1);
//			if(randomNum==0)
//			{
//				return "Angry";
//			}
//			else if(randomNum==1)
//			{
//				return "Sad";
//			}
//			else if(randomNum==2)
//			{
//				return "Fear";
//				
//			}
//			else
//			{
//				return "Excited";
//			}
			return "error";
		}
		
	}
	
	public String abusiveSpeech(String content)throws Exception 
	{
		App pd = new App("ZE8nKaZ99Pmte1bF0mXL5vRHnY8tbqSh9EmJGDgxT0E");
		// for single sentences
		   String abuse = pd.abuse(content);
		   JSONObject object = new JSONObject(abuse);
		   Double abusive = object.getDouble("abusive");
		   Double hate_speech = object.getDouble("hate_speech");
		   Double neither = object.getDouble("neither");
		   if(abusive> hate_speech && abusive > neither)
		   {
			   return "Abusive Speech";
		   }
		   else if (hate_speech > abusive && hate_speech > neither)
		   {
			   return "Hate Speech";
		   }
		   else
		   {
			   return "No Hate or Abusive speeech found !";
		   }
		   
	}
	
	public String intentAnalysisOld(String content) throws Exception
	{
		App pd = new App("ZE8nKaZ99Pmte1bF0mXL5vRHnY8tbqSh9EmJGDgxT0E");
		// for single sentences
			int flag = 0 ;
		double feedback =0 ;
		   String intent = pd.intent(content);
		   JSONObject intent_response = new JSONObject(intent);
		   System.out.println(intent_response);
		   JSONObject response= intent_response.getJSONObject("intent");
		   double news = response.getDouble("news");
		   double query = response.getDouble("query");
		   double spam = response.getDouble("spam");
		   double marketing = response.getDouble("marketing");
		   if(response.get("feedback").getClass().toString().equalsIgnoreCase("class org.json.JSONObject"))
				   {
			   JSONObject feed = response.getJSONObject("feedback");
			   JSONObject tag = feed.getJSONObject("tag");
			   Double complain = tag.getDouble("complaint");
			   Double suggestion = tag.getDouble("suggestion");
			   Double appreciation = tag.getDouble("appreciation");
			   			if(complain > suggestion && complain > appreciation)
			   			{
			   				return "Complaint";
			   			}
			   			if(suggestion > complain && suggestion > appreciation)
			   			{
			   				return "Suggestion";
			   			}
			   			else {
			   				return "Appreciation";
			   			}
				   }
		   else if(response.get("feedback").getClass().toString().equalsIgnoreCase("java.lang.Double"))
		   {
			  feedback = response.getDouble("feedback");
		   }
		   
		
		   
		   if(news > query && news > spam && news >marketing && news >feedback)
		   {
			   return "News";
		   }
		   else if(query > news && query > spam && query >marketing && query >feedback)
		   {
			   return "Query";
		   }
		   else if(spam > news && spam > query && spam > marketing && spam > feedback)
		   {
			   return "Spam";
		   }
		   else if(marketing > news && marketing > query && marketing > spam && marketing > feedback)
		   {
			   return "Marketing";
		   }
		   else
		   {
			   return "Feedback";
		   }
		   
	}

	//new/intent
	public ArrayList<String> intentAnalysis(String content) throws IOException
	{
		System.out.println(content);
		ArrayList<String> intent = new ArrayList<>();
		try {
		String api_key = "ZE8nKaZ99Pmte1bF0mXL5vRHnY8tbqSh9EmJGDgxT0E";
		String text = content;
		String query = "feedback";
		String url = "https://apis.paralleldots.com/v4/new/intent";
		    OkHttpClient client = new OkHttpClient();
		            RequestBody requestBody = new MultipartBody.Builder()
		            .setType(MultipartBody.FORM)
		            .addFormDataPart("api_key", api_key)
		            .addFormDataPart("text", text)
		            .addFormDataPart("query", query)
		            .build();
		            Request request = new Request.Builder()
		              .url(url)
		              .post(requestBody)
		              .addHeader("cache-control", "no-cache")
		              .build();
		Response response = client.newCall(request).execute();
		String s = response.body().string();
		 JSONObject object = new JSONObject(s);
		    JSONObject sentiments = object.getJSONObject("feedback");
		    System.out.println(sentiments);
		    Double comp = sentiments.getDouble("complaint");
		    Double sugg = sentiments.getDouble("suggestion");
		    Double app = sentiments.getDouble("appreciation");
		    comp = (double) Math.round(comp * 100);
		    sugg = (double) Math.round(sugg * 100);
		    app = (double) Math.round(app * 100);
		    System.out.println("Complaint= "+comp);
		    System.out.println("Suggestion= "+sugg);
		    System.out.println("Appreciation= "+app);
		    if (comp > sugg && comp > app)
		    {
		    	intent.add("Complaint");
		    	String n = Double.toString(comp);
		    	intent.add(n);
		    	return intent;
		    }
		    else
		    {
		    	intent.add("nil");
		    	String n = Double.toString(app);
		    	intent.add(n);
		    	return intent;
		    }
		}
		    
		    catch(Exception e)
			{
				intent.clear();
				intent.add("error");
				return intent;
			}
		
	}
	
	
	//sentiment
	public String evalSentiment(String msg) 
	{
		
		ArrayList<String> senti = new ArrayList<>();
		try {
		
		App pd = new App("ZE8nKaZ99Pmte1bF0mXL5vRHnY8tbqSh9EmJGDgxT0E");
		// for single sentences
		    String sentiment = pd.sentiment(msg);
		    JSONObject object = new JSONObject(sentiment);
		    JSONObject sentiments = object.getJSONObject("sentiment");
		    Double negative = sentiments.getDouble("negative");
		    Double neutral = sentiments.getDouble("neutral");
		    Double positive = sentiments.getDouble("positive");
		    negative = (double) Math.round(negative * 100);
		    neutral = (double) Math.round(neutral * 100);
		    positive = (double) Math.round(positive * 100);
		    if (negative > neutral && negative >positive)
		    {
		    	return "Negative";
		    }
		    else if (neutral > positive)
		    {
		    return "Neutral";
		    }
		    else
		    {
		    return "Positive";
		    }
		
		}
		catch(Exception e)
		{
			return "error";
		}
		
		 }
	
	
//	public JsonArray evalSentimenttt()
//	{
//		try {
//			
//			App pd = new App("ZE8nKaZ99Pmte1bF0mXL5vRHnY8tbqSh9EmJGDgxT0E");
//			ArrayList<String> ar = new ArrayList<>();
//			ar.add("Today is a such a lovely day");
//			ar.add("Such a boring and negative day");
//			
//			Gson g = new Gson();
//			JsonArray json1 = g.toJsonTree(ar).getAsJsonArray();
//			JSONArray jArray = new JSONArray();
//			jArray = (JSONArray)parser.parse(json1);
//			String emotion_batch = pd.emotion_batch();
//		    System.out.println(emotion_batch);
//	}
//	
//	
//	
	public static void main(String args[]) throws Exception
	{
		ParallelDots pd = new ParallelDots();
		System.out.println(pd.evalSentiment("He is not a bad person"));
	}

}
