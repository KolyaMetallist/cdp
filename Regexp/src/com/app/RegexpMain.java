package com.app;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class RegexpMain {
	
    //PATTERN   (\b\w+\b)(?:\s*\1)+
    public static final String DUPLICATE_PATTERN = "(?s)\\b(\\w+)\\b(?=.*\\b(\\1)+)";
	
	
	public static void main(String[] args) throws IOException {
		String text = Files.lines(Paths.get("textFile.txt")).collect(Collectors.joining());
        List<String> foundText = new ArrayList<String>();
        Pattern p = Pattern.compile(DUPLICATE_PATTERN);
        Matcher m = p.matcher(text);
        String matchedWord = null;
        Set<String> matchersSet = new HashSet<String>();
        while (m.find()) {
        	if (!m.group(m.groupCount() - 1).equals(matchedWord)) {
    			if (matchedWord != null && !matchersSet.contains(matchedWord)) {
    				foundText.add(matchedWord);
    				matchersSet.add(matchedWord);
    			}
    			matchedWord = m.group(m.groupCount() - 1);			
    		}
            for(int i = 0; i < m.groupCount() - 1; i++){
            	foundText.add(m.group(i));
            }
        }
        
        foundText.forEach(System.out::println);
	}
}
