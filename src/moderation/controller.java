package moderation;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mongodb.util.JSON;


@Controller
public class controller {
	
	//global lists for twitter
	ArrayList<String> toxicity = new ArrayList<>();
	ArrayList<Double> toxicity_value = new ArrayList<>();
	ArrayList<String> image_res = new ArrayList<>();
	ArrayList<String> intents = new ArrayList<String>();
	ArrayList<String> sentiments = new ArrayList<String>();
	ArrayList<String> emotions = new ArrayList<String>();
	ArrayList<ArrayList<String>> moderatedText2= new ArrayList<>(); 
	ArrayList<ArrayList<String>> type = new ArrayList<>();
	
	
	ArrayList<String> list = new ArrayList<String>();
	ArrayList<String> list1 = new ArrayList<String>();
	ArrayList<String> list2= new ArrayList<String>();
	ArrayList<String> list3= new ArrayList<String>();
	ArrayList<String> list4= new ArrayList<String>();
	
	
	//global lists for checking used resources status.
	JSONArray sight_engine = new JSONArray();
	JSONArray moderate_content = new JSONArray();
	JSONArray parallel_dots = new JSONArray();
	
	String queryTag = "";
	@RequestMapping("/gotoHome")
	public String redirect1(Model model) {
		
		return "home";
	}	

	
	@RequestMapping("/gotoSearchQuery")
	public String redirect3(Model model) {
		
		return "twitterSearch";
	}
	
	
	
	@RequestMapping("/createTwitterGraph")
	public String createTwitterGraph(Model model, JSONArray doc) throws IOException {
		try {
		ArrayList<Double> tox_dbvalue = new ArrayList<>();
		int marketing=0,spam=0,news=0,query=0,feedback=0,complaint=0;
		int bored=0, angry=0, sad=0 , fear=0, happy=0, excited=0;
		int negative=0, neutral=0, positive=0; 
		int count_yes=0, count_no=0;
		JSONArray response = new JSONArray();
		
		Database db = new Database();
		response = db.getTweetsfromDB();
		//response = doc;
		//response = db.readcontentfromJSON();
		if(!(doc.toString().equalsIgnoreCase("[]")))
		{
			
			System.out.println("You are here");
			System.out.println(doc);
			
			response=doc;
		}
	
		
		
		int k,count=0;
		Double tox_val=null;
		for(k=0; k<response.length();k++)
		{
			String s = response.get(k).toString();
			System.out.println("s= "+s);
			JSONObject ob = new JSONObject(s);
			if(ob.has("error"))
			{
				//throw error
			}
			else
			{
				count++;
				
				//intent cal 
				
				String intent = ob.getString("Intent");
				if(intent.toString().equalsIgnoreCase("marketing"))
				{
					marketing++;
				}
				else if(intent.toString().equalsIgnoreCase("news"))
				{
					news++;
				}
				else if(intent.toString().equalsIgnoreCase("query"))
				{
					query++;
				}
				else if(intent.toString().equalsIgnoreCase("complaint"))
				{
					complaint++;
				}
				else if(intent.toString().equalsIgnoreCase("spam"))
				{
					spam++;
				}
				else
				{
					feedback++;
				}
				//calculating emotion
				String emotion = ob.getString("Emotion");
				if(emotion.toString().equalsIgnoreCase("bored"))
				{
					bored++;
				}
				else if(emotion.toString().equalsIgnoreCase("angry"))
				{
					angry++;
				}
				else if(emotion.toString().equalsIgnoreCase("sad"))
				{
					sad++;
				}
				else if(emotion.toString().equalsIgnoreCase("fear"))
				{
					fear++;
				}
				else if(emotion.toString().equalsIgnoreCase("happy"))
				{
					happy++;
				}
				else
				{
					excited++;
				}
				
				//calculating sentiment
				String sentiment = ob.getString("Sentiment");
				if(sentiment.toString().equalsIgnoreCase("negative"))
				{
					negative++;
				}
				else if(sentiment.toString().equalsIgnoreCase("neutral"))
				{
					neutral++;
				}
				else
				{
					positive++;
				}
				//calculating review
				String review = ob.getString("Review");
				if(review.toString().equalsIgnoreCase("yes"))
				{
					count_yes++;
				}
				else
				{
					count_no++;
				}
				
			}
			tox_dbvalue.add(tox_val);
		}
		
	
		JSONArray intent = new JSONArray();
		
		JSONObject intent_data1 = new JSONObject();
		intent_data1.put("category", "News");
		intent_data1.put("value", news);
		intent.put(intent_data1);
		
		JSONObject intent_data2 = new JSONObject();
		intent_data2.put("category", "Query");
		intent_data2.put("value", query);
		intent.put(intent_data2);

		JSONObject intent_data3 = new JSONObject();
		intent_data3.put("category", "Spam");
		intent_data3.put("value", spam);
		intent.put(intent_data3);
		
		JSONObject intent_data4 = new JSONObject();
		intent_data4.put("category", "Feedback");
		intent_data4.put("value", feedback);
		intent.put(intent_data4);
		
		JSONObject intent_data5 =  new JSONObject();
		intent_data5.put("category", "Marketing");
		intent_data5.put("value", marketing);
		intent.put(intent_data5);
		
		JSONObject intent_data6 =  new JSONObject();
		intent_data6.put("category", "Complaint");
		intent_data6.put("value", complaint);
		intent.put(intent_data6);
		
JSONArray emotion = new JSONArray();
		
		JSONObject emotion_data1 = new JSONObject();
		emotion_data1.put("category", "Bored");
		emotion_data1.put("value", bored);
		emotion.put(emotion_data1);
		
		JSONObject emotion_data2 = new JSONObject();
		emotion_data2.put("category", "Angry");
		emotion_data2.put("value", angry);
		emotion.put(emotion_data2);
		
		JSONObject emotion_data3 = new JSONObject();
		emotion_data3.put("category", "Sad");
		emotion_data3.put("value", sad);
		emotion.put(emotion_data3);
		
		JSONObject emotion_data4 = new JSONObject();
		emotion_data4.put("category", "Fear");
		emotion_data4.put("value", fear);
		emotion.put(emotion_data4);
		
		JSONObject emotion_data5 = new JSONObject();
		emotion_data5.put("category", "Happy");
		emotion_data5.put("value", happy);
		emotion.put(emotion_data5);
		
		JSONObject emotion_data6 = new JSONObject();
		emotion_data6.put("category", "Excited");
		emotion_data6.put("value", excited++);
		emotion.put(emotion_data6);
		
JSONArray sentiment = new JSONArray();
		
		JSONObject sentiment_data1 = new JSONObject();
		sentiment_data1.put("category", "Negative");
		sentiment_data1.put("value", negative);
		sentiment.put(sentiment_data1);
		
		JSONObject sentiment_data2 = new JSONObject();
		sentiment_data2.put("category", "Positive");
		sentiment_data2.put("value", positive);
		sentiment.put(sentiment_data2);
		
		JSONObject sentiment_data3 = new JSONObject();
		sentiment_data3.put("category", "Neutral");
		sentiment_data3.put("value", neutral);
		sentiment.put(sentiment_data3);
		
JSONArray review= new JSONArray();
		
		JSONObject review_data1 = new JSONObject();
		review_data1.put("category", "Review Recommended");
		review_data1.put("value", count_yes);
		review.put(review_data1);
		
		JSONObject review_data2 = new JSONObject();
		review_data2.put("category", "Safe Content");
		review_data2.put("value", count_no);
		review.put(review_data2);
		
		model.addAttribute("intent", intent);
		model.addAttribute("sentiment", sentiment);
		model.addAttribute("emotion", emotion);
		model.addAttribute("review", review);
		
		}
		catch(Exception e)
		{
			return "twitterCharts";
		}
		
		return "twitterCharts";
	}
	
	@RequestMapping("/gotoTwittercc")
	public void getTwittercc(Model model, JSONArray doc) throws IOException {
		try {
		ArrayList<Double> tox_dbvalue = new ArrayList<>();
		int marketing=0,spam=0,news=0,query=0,feedback=0,complaint=0;
		int bored=0, angry=0, sad=0 , fear=0, happy=0, excited=0;
		int negative=0, neutral=0, positive=0; 
		int count_yes=0, count_no=0;
		JSONArray response = new JSONArray();
		
		Database db = new Database();
		response = db.getTweetsfromDB();

		//response = db.readcontentfromJSON();
		if(!(doc.toString().equalsIgnoreCase("[]")))
		{
			
			System.out.println("You are here");
			System.out.println(doc);
			response=doc;
		}
		
		int k,count=0;
		Double tox_val=null;
		for(k=0; k<response.length();k++)
		{
			String s = response.get(k).toString();
			System.out.println("s= "+s);
			JSONObject ob = new JSONObject(s);
			if(ob.has("error"))
			{
				//throw error
			}
			else
			{
				count++;
								
				//intent cal 
				
				String intent = ob.getString("Intent");
				if(intent.toString().equalsIgnoreCase("marketing"))
				{
					marketing++;
				}
				else if(intent.toString().equalsIgnoreCase("news"))
				{
					news++;
				}
				else if(intent.toString().equalsIgnoreCase("query"))
				{
					query++;
				}
				else if(intent.toString().equalsIgnoreCase("complaint"))
				{
					complaint++;
				}
				else if(intent.toString().equalsIgnoreCase("spam"))
				{
					spam++;
				}
				else
				{
					feedback++;
				}
				
				//calculating emotion
				String emotion = ob.getString("Emotion");
				if(emotion.toString().equalsIgnoreCase("bored"))
				{
					bored++;
				}
				else if(emotion.toString().equalsIgnoreCase("angry"))
				{
					angry++;
				}
				else if(emotion.toString().equalsIgnoreCase("sad"))
				{
					sad++;
				}
				else if(emotion.toString().equalsIgnoreCase("fear"))
				{
					fear++;
				}
				else if(emotion.toString().equalsIgnoreCase("happy"))
				{
					happy++;
				}
				else
				{
					excited++;
				}
				
				//calculating sentiment
				String sentiment = ob.getString("Sentiment");
				if(sentiment.toString().equalsIgnoreCase("negative"))
				{
					negative++;
				}
				else if(sentiment.toString().equalsIgnoreCase("neutral"))
				{
					neutral++;
				}
				else
				{
					positive++;
				}
				//calculating review
				String review = ob.getString("Review");
				if(review.toString().equalsIgnoreCase("yes"))
				{
					count_yes++;
				}
				else
				{
					count_no++;
				}
				
			}
			tox_dbvalue.add(tox_val);
		}
				
		JSONArray intent = new JSONArray();
		
		JSONObject intent_data1 = new JSONObject();
		intent_data1.put("category", "News");
		intent_data1.put("value", news);
		intent.put(intent_data1);
		
		JSONObject intent_data2 = new JSONObject();
		intent_data2.put("category", "Query");
		intent_data2.put("value", query);
		intent.put(intent_data2);

		JSONObject intent_data3 = new JSONObject();
		intent_data3.put("category", "Spam");
		intent_data3.put("value", spam);
		intent.put(intent_data3);
		
		JSONObject intent_data4 = new JSONObject();
		intent_data4.put("category", "Feedback");
		intent_data4.put("value", feedback);
		intent.put(intent_data4);
		
		JSONObject intent_data5 =  new JSONObject();
		intent_data5.put("category", "Marketing");
		intent_data5.put("value", marketing);
		intent.put(intent_data5);
		
		JSONObject intent_data6 =  new JSONObject();
		intent_data5.put("category", "Complaint");
		intent_data5.put("value", complaint);
		intent.put(intent_data6);
		
JSONArray emotion = new JSONArray();
		
		JSONObject emotion_data1 = new JSONObject();
		emotion_data1.put("category", "Bored");
		emotion_data1.put("value", bored);
		emotion.put(emotion_data1);
		
		JSONObject emotion_data2 = new JSONObject();
		emotion_data2.put("category", "Angry");
		emotion_data2.put("value", angry);
		emotion.put(emotion_data2);
		
		JSONObject emotion_data3 = new JSONObject();
		emotion_data3.put("category", "Sad");
		emotion_data3.put("value", sad);
		emotion.put(emotion_data3);
		
		JSONObject emotion_data4 = new JSONObject();
		emotion_data4.put("category", "Fear");
		emotion_data4.put("value", fear);
		emotion.put(emotion_data4);
		
		JSONObject emotion_data5 = new JSONObject();
		emotion_data5.put("category", "Happy");
		emotion_data5.put("value", happy);
		emotion.put(emotion_data5);
		
		JSONObject emotion_data6 = new JSONObject();
		emotion_data1.put("category", "Excited");
		emotion_data1.put("value", excited++);
		emotion.put(emotion_data6);
		
JSONArray sentiment = new JSONArray();
		
		JSONObject sentiment_data1 = new JSONObject();
		sentiment_data1.put("category", "Negative");
		sentiment_data1.put("value", negative);
		sentiment.put(sentiment_data1);
		
		JSONObject sentiment_data2 = new JSONObject();
		sentiment_data2.put("category", "Positive");
		sentiment_data2.put("value", positive);
		sentiment.put(sentiment_data2);
		
		JSONObject sentiment_data3 = new JSONObject();
		sentiment_data3.put("category", "Neutral");
		sentiment_data3.put("value", neutral);
		sentiment.put(sentiment_data3);
		
JSONArray review= new JSONArray();
		
		JSONObject review_data1 = new JSONObject();
		review_data1.put("category", "Review Recommended");
		review_data1.put("value", count_yes);
		review.put(review_data1);
		
		JSONObject review_data2 = new JSONObject();
		review_data2.put("category", "Safe Content");
		review_data2.put("value", count_no);
		review.put(review_data2);
		
		model.addAttribute("twitterintent", intent);
		model.addAttribute("twittersentiment", sentiment);
		model.addAttribute("emotion", emotion);
		model.addAttribute("review", review);
		}
		catch(Exception e)
		{
			
		}
		
	}
	
	@RequestMapping("/checkLogin")
	public String checkLogin(@RequestParam String email,String pass, Model model) throws java.lang.Exception {
		
		Database db = new Database();
		int response = db.checkLogin(email,pass);
		if(response==1)
		{
			return "home";
		}
		else
		{
			return "tryAgain";
			
		}
	}
	
	@RequestMapping("/moderateTwitterText")
	public String moderateTwittertext(Model model,ArrayList<String> listone,ArrayList<String> listtwo, ArrayList<String> listthree) throws Exception{
	try {
		toxicity.clear();
		image_res.clear();
		toxicity_value.clear();
		intents.clear();
		sentiments.clear();
		emotions.clear();
		moderatedText2.clear();
		
		int length_list;
		
		ArrayList<String> child = new ArrayList<>();
		ArrayList<ArrayList<String>> type = new ArrayList<>();
		if(listone.size()>=5)
		{
			length_list=5;
		}
		else
		{
			length_list = listone.size();
		}
		int i =0 ; 
		while(i!=length_list)
		{
			String result = "Safe";
			//sentiment analysis
			ParallelDots pd = new ParallelDots();
			String emo= pd.emotionDetect(listtwo.get(i).toString());
			emotions.add(emo);
			String intent = pd.intentAnalysisOld(listtwo.get(i).toString());
			intents.add(intent);
			String sentiment = pd.evalSentiment(listtwo.get(i).toString());
			sentiments.add(sentiment);


			  //text moderate
			  BadWords bd = new BadWords();
				String msg = listtwo.get(i).toString(); 
				child = bd.moderateText(msg, "en");
				ArrayList<String> type_child = new ArrayList<>();
				if(child.get(0).equalsIgnoreCase("No Bad Words"))
				{
					toxicity.add("No");
					type_child.add("Appropriate");
					
				}
				else
				{
					toxicity.add("Yes");
					int iter=1;
					while(iter<child.size())
					{
						type_child.add(child.get(iter).toString());
						iter= iter +2;
					}
					
				}
				type.add(type_child);
				moderatedText2.add(child);			
			
			i++;
		}
		Database db = new Database();
		model.addAttribute("intent", intents);
		model.addAttribute("emotion", emotions);
		model.addAttribute("sentiment", sentiments);
		  model.addAttribute("moderateText",moderatedText2);
			model.addAttribute("original", listtwo);
			model.addAttribute("toxicity", toxicity);
	}
	catch(Exception e)
	{
		model.addAttribute("err", "Server overload !");
		return "twitterDashboard";
	}

		return "twitterDashboard";
	}
	
	@RequestMapping("/search")
	public String searchTwitter(Model model, String query) throws java.lang.Exception {
			if(query.isEmpty())
			{
				model.addAttribute("err", "No String present");
				return "twitterSearch";
			}
			queryTag = query;
			list.clear();
			list1.clear();
			list2.clear();
			list3.clear();
			list4.clear();
			TwitterClass f = new TwitterClass();
			list = f.searchtweets(query);
			int i=0;
			if(list.get(0).equalsIgnoreCase("error"))
			{
			model.addAttribute("err", "Server overloaded!");
		    return "twitterSearch";
			}
			while(i<list.size())
			{
				list4.add(list.get(i));
				list1.add(list.get(i+1));
				list2.add(list.get(i+2));
				list3.add(list.get(i+3));
				i=i+4;
			}
			model.addAttribute("message",list1);
			model.addAttribute("message1", list2);
			model.addAttribute("message2", list3);
			model.addAttribute("tweetId", list4);
			
			moderateTwittertext(model,list1,list2,list3);
			
			return "twitterSearch";
		
		
	}
		
	@RequestMapping("/fetchDataUsingFiltersTwitter")
	public String fetchDataUsingFiltersTwitter(Model model, String intent, String sentiment, String queryTag, String start, String end) throws ParseException, IOException, org.json.simple.parser.ParseException{
			try {
			JSONArray ar = new JSONArray();
			Database db = new Database();
			ar = db.fetchDataUsingFilterPosts(sentiment, intent, "TwitterResults", queryTag);
			if(!(start.isEmpty() && end.isEmpty()))
			{
				ar=db.getRange(start, end, ar);

			}
			createTwitterGraph(model,ar);
			return "twitterCharts";
			}
			catch(Exception e)
			{
				return "twitterCharts";
			}
		} 
		
		@RequestMapping("/saveResultsTwitter")
	public String saveResultsTwitter(Model model) throws Exception{
			
			int response;
			Database db = new Database();
			response = db.saveTwitterResults(list2, moderatedText2, intents, sentiments, emotions, image_res, toxicity, queryTag, list4);
			if(response==1)
			{
				model.addAttribute("response","Save Successful.");
			}
			
			return "twitterSearch";
		}
		
		@RequestMapping("/populateAllTweets")
	public String populateAutodeskTweets(Model model){
			
			try {
			JSONArray response;
			ArrayList<String> content = new ArrayList<>();
			ArrayList<String> words = new ArrayList<>();
			ArrayList<String> intent = new ArrayList<>();
			ArrayList<String> emotion = new ArrayList<>();
			ArrayList<String> review = new ArrayList<>();
			ArrayList<String> image = new ArrayList<>();
			ArrayList<String> sentiment = new ArrayList<>();
			
			Database db = new Database();
			response = db.getTweetsBasedOnTag();
			int i ;
			for(i=0; i < response.length();i++)
			{
				JSONObject data = response.getJSONObject(i);
				content.add(data.getString("Tweet"));
				String s = data.getString("Words");
				s= s.replace("[", "");
				s= s.replace("]", "");
				words.add(s);
				intent.add(data.getString("Intent"));
				emotion.add(data.getString("Emotion"));
				review.add(data.getString("Review"));
				sentiment.add(data.getString("Sentiment"));
				
			}
		
			model.addAttribute("moderateText",words);
			model.addAttribute("intent", intent);
			model.addAttribute("emotion", emotion);
			model.addAttribute("sentiment", sentiment);
			model.addAttribute("original", content);
			model.addAttribute("toxicity", review);
			model.addAttribute("res", "1");
			}
			catch(Exception e)
			{
				return "twitterDashboard";
			}
			return "twitterDashboard";
		}
	
}
