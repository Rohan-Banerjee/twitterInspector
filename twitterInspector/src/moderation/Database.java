package moderation;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public class Database {

	public int checkLogin(String user, String password) {
		int response = 0;
		try {
		MongoClient mongo = new MongoClient("localhost", 27017);
		MongoDatabase db = mongo.getDatabase("ContentModeration");
		MongoCollection<Document> collection = db.getCollection("Users");

		List<Document> documents = (List<Document>) collection.find().into(new ArrayList<Document>());

		for (Document document : documents) {

			if (document.getString("user").equals(user) && document.getString("password").equals(password)) {
				response =1;
			}

		}
		mongo.close();
		}
		catch(Exception e)
		{
			response = -1;
		}
		return response;
	}
	
	public JSONArray readcontentfromJSON() {
		JSONArray response = null;
		JSONParser parser = new JSONParser();
		try {
			Object obj = parser.parse(new FileReader("C:\\json/dummy-data.json"));
			org.json.simple.JSONArray ar = (org.json.simple.JSONArray) obj;
			String s = ar.toString();
			response = new JSONArray(s);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	public int saveTwitterResults(ArrayList<String> original, ArrayList<ArrayList<String>> moderateText,
			ArrayList<String> intent, ArrayList<String> sentiment, ArrayList<String> emotion,
			ArrayList<String> image_res, ArrayList<String> toxicity, String tag,ArrayList<String> tId) {
		try {
			MongoClient mongo = new MongoClient("localhost", 27017);
			MongoDatabase db = mongo.getDatabase("ContentModeration");
			MongoCollection<Document> collection = db.getCollection("TwitterResults");
			
			Date d = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			int i = 0;
			System.out.println(original.size());
			while (i < original.size()) {
				Document doc = new Document();
				doc.append("tweetId", tId.get(i).toString() );
				doc.append("tweet", original.get(i).toString());
				doc.append("bad words", moderateText.get(i).toString());
				doc.append("intent", intent.get(i).toString());
				doc.append("sentiment", sentiment.get(i).toString());
				doc.append("emotion", emotion.get(i).toString());
				doc.append("review", toxicity.get(i).toString());
				doc.append("Date", sdf.format(d));
				doc.append("tag", tag);
				collection.insertOne(doc);
				i++;
			}
			mongo.close();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}

	}

	public int saveDB(ArrayList<String> name, ArrayList<String> content, ArrayList<Double> toxicity) {

		MongoClient mongo = new MongoClient("localhost", 27017);
		MongoDatabase db = mongo.getDatabase("ContentModeration");
		MongoCollection<Document> collection = db.getCollection("Tweets");
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		int i = 0;
		while (i < name.size()) {
			Document doc = new Document();
			doc.append("name", name.get(i).toString());
			doc.append("content", content.get(i).toString());
			doc.append("toxicity", toxicity.get(i).toString());
			doc.append("date", sdf.format(d));
			collection.insertOne(doc);
			i++;
		}
		mongo.close();
		return 1;

	}

	public JSONArray getTweetsfromDB() {

		JSONArray array = new JSONArray();

		MongoClient mongo = new MongoClient("localhost", 27017);
		MongoDatabase db = mongo.getDatabase("ContentModeration");
		MongoCollection<Document> collection = db.getCollection("TwitterResults");

		FindIterable<Document> iterDoc = collection.find();
		List<Document> documents = (List<Document>) collection.find().into(new ArrayList<Document>());
		
		for (Document document : documents) {

			JSONObject data = new JSONObject();
			data.put("Tweet", document.getString("tweet"));
			data.put("Words", document.getString("bad words"));
			data.put("Intent", document.getString("intent"));
			data.put("Emotion", document.getString("emotion"));
			data.put("Sentiment", document.getString("sentiment"));
			data.put("Image", document.getString("image"));
			data.put("Review", document.getString("review"));

			array.put(data);
		}

		mongo.close();
		System.out.println(array);
		return array;

	}

	public JSONArray getTweetsBasedOnTag() {

		JSONArray array = new JSONArray();

		MongoClient mongo = new MongoClient("localhost", 27017);
		MongoDatabase db = mongo.getDatabase("ContentModeration");
		MongoCollection<Document> collection = db.getCollection("TwitterResults");

		FindIterable<Document> iterDoc = collection.find();
		List<Document> documents = (List<Document>) collection.find().into(new ArrayList<Document>());

		for (Document document : documents) {

		
				JSONObject data = new JSONObject();
				data.put("Tweet", document.getString("tweet"));
				data.put("Words", document.getString("bad words"));
				data.put("Intent", document.getString("intent"));
				data.put("Emotion", document.getString("emotion"));
				data.put("Sentiment", document.getString("sentiment"));
				data.put("Image", document.getString("image"));
				data.put("Review", document.getString("review"));
				array.put(data);
		

		}

		mongo.close();
		System.out.println(array);
		return array;

	}

	public JSONArray fetchDataUsingFilterPosts(String sentiment, String intent, String platform, String queryTag)
			throws ParseException, FileNotFoundException, IOException, org.json.simple.parser.ParseException {

		System.out.println("Sentiment"+sentiment);
		System.out.println("intent"+intent);
		
		JSONArray array = new JSONArray();
		int casing = 0;
		if (!sentiment.equalsIgnoreCase("none") && !intent.equalsIgnoreCase("none")) {
			casing = 1;
		} else if (sentiment.equalsIgnoreCase("none") && intent.equalsIgnoreCase("none")) {
			casing = 2;
		} else if ((!intent.equalsIgnoreCase("none")) && sentiment.equalsIgnoreCase("none")) {
			casing = 3;
		} else {
			casing = 4;
		}
		System.out.println("casing"+casing);
		MongoClient mongo = new MongoClient("localhost", 27017);
		MongoDatabase db = mongo.getDatabase("ContentModeration");
		MongoCollection<Document> collection = db.getCollection(platform);

		List<Document> documents = (List<Document>) collection.find().into(new ArrayList<Document>());

		if (casing == 1) {
			// case-1 all 3 given
			for (Document document : documents) {
				JSONObject obj = new JSONObject();
				if (sentiment.toString().equalsIgnoreCase(document.getString("sentiment"))
						&& intent.toString().equalsIgnoreCase(document.getString("intent"))) {
					System.out.println("matching cases");
					if (queryTag.equalsIgnoreCase(document.getString("tag"))) {
						obj.put("Intent", document.getString("intent"));
						obj.put("Emotion", document.getString("emotion"));
						obj.put("Sentiment", document.getString("sentiment"));
						obj.put("Review", document.getString("review"));
						obj.put("Date", document.getString("Date"));
						array.put(obj);
					}
				}

			}
		} else if (casing == 2) {
			// case- 2 only company given
			for (Document document : documents) {
				JSONObject obj = new JSONObject();
				if (queryTag.toString().equalsIgnoreCase(document.getString("tag"))) {
					System.out.println("matching cases");
					obj.put("Intent", document.getString("intent"));
					obj.put("Emotion", document.getString("emotion"));
					obj.put("Sentiment", document.getString("sentiment"));
					obj.put("Review", document.getString("review"));
					obj.put("Date", document.getString("Date"));
					array.put(obj);
				}
			}

		} else if (casing == 3) {
			// case- 3 company and intent given

			for (Document document : documents) {
				JSONObject obj = new JSONObject();
				if (queryTag.toString().equalsIgnoreCase(document.getString("tag"))
						&& intent.toString().equalsIgnoreCase(document.getString("intent"))) {
					System.out.println("matching cases");
					obj.put("Intent", document.getString("intent"));
					obj.put("Emotion", document.getString("emotion"));
					obj.put("Sentiment", document.getString("sentiment"));
					obj.put("Review", document.getString("review"));
					obj.put("Date", document.getString("Date"));
					array.put(obj);
				}

			}
		} else // only company and sentiment given
		{
			for (Document document : documents) {
				JSONObject obj = new JSONObject();
				if (queryTag.toString().equalsIgnoreCase(document.getString("tag"))
						&& sentiment.toString().equalsIgnoreCase(document.getString("sentiment"))) {
					System.out.println("matching cases");
					obj.put("Intent", document.getString("intent"));
					obj.put("Emotion", document.getString("emotion"));
					obj.put("Sentiment", document.getString("sentiment"));
					obj.put("Review", document.getString("review"));
					obj.put("Date", document.getString("Date"));
					array.put(obj);
				}

			}
		}
		if(array.length()==0)
		{
			JSONObject obj =new JSONObject();
			obj.put("responses", "empty");
			
			array.put(obj);
		}

		mongo.close();
		return array;
	}

	public JSONArray fetchDataUsingFilterPosts1(String sentiment, String intent, String platform)
			throws ParseException, FileNotFoundException, IOException, org.json.simple.parser.ParseException {

		
		MongoClient mongo = new MongoClient("localhost", 27017);
		MongoDatabase db = mongo.getDatabase("ContentModeration");
		MongoCollection<Document> collection = db.getCollection(platform);

		List<Document> documents = (List<Document>) collection.find().into(new ArrayList<Document>());

		
			JSONArray array = new JSONArray();
			for (Document document : documents) {
				JSONObject obj = new JSONObject();
				if (sentiment.toString().equalsIgnoreCase(document.getString("sentiment"))
						|| intent.toString().equalsIgnoreCase(document.getString("intent"))) {
					System.out.println("matching cases");
					
						obj.put("Intent", document.getString("intent"));
						obj.put("Emotion", document.getString("emotion"));
						obj.put("Sentiment", document.getString("sentiment"));
						obj.put("Review", document.getString("review"));
						array .put(obj);
					
				}

			}
			if(array.length()==0)
			{
				JSONObject obj =new JSONObject();
				obj.put("responses", "empty");
				
				array.put(obj);
			}
	
		mongo.close();
		return array;
	}
	public JSONArray getRange(String start, String end, JSONArray ar) throws ParseException {
		try {
		JSONArray array = new JSONArray();
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
		Date startDate = s.parse(start);
		System.out.println("starttt="+startDate);
		Date endDate = s.parse(end);
		System.out.println(endDate);
		
		int i=0 ;
		while(i<ar.length())
		{
			System.out.println("Hi");
			JSONObject ob = ar.getJSONObject(i);
			String postDate = ob.getString("Date");
			System.out.println("Post ka date1"+postDate);
			
			Date post_date = s.parse(postDate);
			System.out.println("Post ka date"+post_date);
			
			
			if(post_date.getTime() >= startDate.getTime() && post_date.getTime() <= endDate.getTime())
			{
				array.put(ob);
			}
			i++;
		}
		if(array.length()==0)
		{
			System.out.println("empty list[]");
			JSONObject obj =new JSONObject();
			obj.put("responses", "empty");
			
			array.put(obj);
		}
		
		return array;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			JSONArray array = new JSONArray();
			return array;
		}
		}

	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
		Database d = new Database();
		JSONArray a = new JSONArray();

	}

}
