package com.app.Java8Test.main;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import com.app.Java8Test.jcommander.JCommanderParameters;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;

/**
 * Hello world!
 *
 */
public class AppJava8 {
	
    public static void main( String[] args ) {
        JCommanderParameters parameters = new JCommanderParameters();
        JCommander jCommander = new JCommander(parameters, args);
        try {
	        if (parameters.isHelp()) {
	        	jCommander.usage();
	        } else {
	        	
	        }
        } catch (ParameterException e) {
        	System.out.println(e.getMessage());
        	jCommander.usage();
        }
    }
}
