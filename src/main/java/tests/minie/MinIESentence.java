package tests.minie;

import java.io.IOException;
import java.io.InputStream;

import de.uni_mannheim.minie.MinIE;
import de.uni_mannheim.minie.annotation.AnnotatedProposition;
import de.uni_mannheim.utils.coreNLP.CoreNLPUtils;
import de.uni_mannheim.utils.Dictionary;

import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.semgraph.SemanticGraph;

/**
 * @author Kiril Gashteovski
 */
public class MinIESentence {
    public static void main(String input) throws IOException {
    	String DictDir= "/home/isabella/Documents/FYP/minie/minie-resources/";
    	String DictDir2 = "/home/isabella/Documents/FYP/minie/src/main/resources/minie-resources";
    	
    	// Dependency parsing pipeline initialization
        StanfordCoreNLP parser = CoreNLPUtils.StanfordDepNNParser();
        
        //sunny added this later
        SemanticGraph sg = CoreNLPUtils.parse(parser, input);
        // Input sentence
        String sentence = input;
        // Generate the extractions (With SAFE mode)
      MinIE minie = new MinIE(sentence, parser, MinIE.Mode.AGGRESSIVE);
        // Initialize dictionaries
		
		/*
		 * String filenames = new String() ; // filenames = DictDir +
		 * "wiki-freq-args-mw.txt"; filenames = DictDir2 + "wiktionary-mw-titles.txt";
		 * 
		 * Dictionary collocationsDict ; collocationsDict = new Dictionary(filenames);
		 * 
		 * // Use MinIE MinIE minie = new MinIE(sentence, parser, MinIE.Mode.DICTIONARY,
		 * collocationsDict);
		 */

        
        // Print the extractions
        System.out.println("\nInput sentence: " + sentence);
        System.out.println("=============================");
        System.out.println("Extractions:");
        for (AnnotatedProposition ap: minie.getPropositions()) {
            System.out.println("\tTriple: " + ap.getTripleAsString());
            System.out.print("\tFactuality: " + ap.getFactualityAsString());
            if (ap.getAttribution().getAttributionPhrase() != null) 
                System.out.print("\tAttribution: " + ap.getAttribution().toStringCompact());
            else
                System.out.print("\tAttribution: NONE");
            System.out.println("\n\t----------");
        }
        
        System.out.println("\n\nDONE!");
    }
}
