package moderation;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bson.Document;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class BadWords {
	
	public ArrayList<String> moderateText(String sentence, String lang)
	{
		ArrayList<String> words = new ArrayList<String>();
		 ArrayList<String> bad_words = new ArrayList<String>();
		 MongoClient mongo = new MongoClient( "localhost" , 27017 );
	      MongoDatabase db = mongo.getDatabase("ContentModeration");
	      MongoCollection<Document> collection = db.getCollection("BadWords");
	     
	      FindIterable<Document> iterDoc = collection.find();
	      List<Document> documents = (List<Document>) collection.find().into(new ArrayList<Document>());
	      
	               for(Document document : documents){
	                   
	            	   
	            	   bad_words.add(document.getString("word"));
	            	   
	               }
	               mongo.close();
	               sentence = sentence.toLowerCase();
	               sentence = sentence.replace("*", "@");
	               sentence = sentence.replace("@ ","@-nil");
	               sentence = sentence.replace("@?","@-nil");
	               sentence = sentence.replace("@!","@-nil");
	               sentence = sentence.replace("@.","@-nil");
	               int j=0 ; 
	               while(j<bad_words.size())
	               {		
	            	   
	            	  Pattern p = Pattern.compile("\\b"+bad_words.get(j)+"\\b");
	            	  Matcher m = p.matcher(sentence);
	            	  if(m.find())
	            	  {
	            		  words.add(bad_words.get(j));
	            	  }
	            	  j++;
	            	  
	               }
	               if(words.size()==0)
	               {
	            	   words.add("No Bad Words");
	               }
	               else
	               {
	            	   j=0;
	            	   while(j<words.size())
	            	   {
	            		   if(words.get(j).contains("@"))
	            		   {
	            			   String newString = words.get(j).replace("@-nil", "*");
	            			   words.set(j, newString);       			   
	            			   
	            			   newString = words.get(j).replace("@", "*");
	            			   words.set(j, newString);
	            		   }
	            		   j++;
	            	   }
	               }
	               return words;

	      
	      
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BadWords bd = new BadWords();
		String msg="She fucking sucks and shes so stiff on stage and half time she dont actually sing her shit";
		System.out.println(bd.moderateText(msg, "en"));
	}

}
