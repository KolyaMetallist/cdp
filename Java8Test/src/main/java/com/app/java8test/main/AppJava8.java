package com.app.java8test.main;

import com.app.java8test.jcommander.JCommanderParameters;
import com.app.java8test.processor.TextAnalyzerProcessor;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;

/**
 * Main Class
 *
 */
public class AppJava8 {
	
    public static void main( String[] args ) {
        JCommanderParameters parameters = new JCommanderParameters();
        JCommander jCommander = new JCommander(parameters);
        jCommander.setProgramName("Java 8 Text Analyzer");
        try {
        	jCommander.parse(args);
	        if (parameters.isHelp()) {
	        	jCommander.usage();
	        } else {
	        	//System.out.println("work");
	        	TextAnalyzerProcessor pr = new TextAnalyzerProcessor(parameters.getFile(),
	        														parameters.getTask(),
	        														parameters.isParallel(),
	        														parameters.getApproach());
	        	pr.execute();
	        }
        } catch (ParameterException e) {
        	System.out.println(e.getMessage());
        	jCommander.usage();
        }
    }
}
