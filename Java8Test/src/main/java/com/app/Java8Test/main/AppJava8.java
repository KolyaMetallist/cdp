package com.app.Java8Test.main;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import com.app.Java8Test.jcommander.JCommanderParameters;
import com.app.Java8Test.processor.TextAnalyzerProcessor;
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
	        	System.out.println("work");
	        	TextAnalyzerProcessor pr = new TextAnalyzerProcessor();
	        }
        } catch (ParameterException e) {
        	System.out.println(e.getMessage());
        	jCommander.usage();
        }
    }
}
