package tests.minie;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import edu.stanford.nlp.ling.*;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;
import edu.stanford.nlp.pipeline.*;


public class TextPrep {
	
	private static String InputCSV = "NYT.csv";
	private static String InputDir= "/home/isabella/Documents/FYP/WebScrape/";
	private static List<String> DocumentList = new ArrayList<>();
	
	public static void main() throws IOException{
		
		GetListDocument(InputDir + InputCSV);
		
		for(String Document:DocumentList){
			String Text = GetDocument(InputDir + Document + ".txt");
			List<String> Sentences = GetSentences (Text);
			
			for(String Sentence:Sentences){
				MinIESentence.main(Sentence);
			}
        }
		return;
	}

   public static void GetListDocument(String input) throws IOException{
	 
	BufferedReader csvReader = new BufferedReader(new FileReader(input));

	String row = csvReader.readLine();
	while ((row = csvReader.readLine()) != null) {
	       String[] data = row.split(",");
	       DocumentList.add(data[1]);
	   }
	   csvReader.close();
	return ;
    }
   
   public static String GetDocument(String input) throws IOException{
	    File file = new File(input); 
	    Scanner sc = new Scanner(file); 
	  
	    sc.useDelimiter("\\Z"); 
	    
		return sc.next(); 
    }

	

   public static List<String> GetSentences(String input){
        List<String> sentenceList = new ArrayList<>();
        // creates a StanfordCoreNLP object
        Properties props = new Properties();
        props.put("annotators", "tokenize, ssplit");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
        // create an empty Annotation just with the given text
		Annotation document = new Annotation(input);
        // run all Annotators on this text
        pipeline.annotate(document);
        // these are all the sentences in this document
        // a CoreMap is essentially a Map that uses class objects as keys and has values with custom types
        List<CoreMap> sentences = document.get(CoreAnnotations.SentencesAnnotation.class);
        for(CoreMap sentence:sentences){
            List<CoreLabel> labels = sentence.get(CoreAnnotations.TokensAnnotation.class);
            String originalString = edu.stanford.nlp.ling.SentenceUtils.listToOriginalTextString(labels);
            sentenceList.add(originalString);
        }
        return sentenceList;
    }
}
