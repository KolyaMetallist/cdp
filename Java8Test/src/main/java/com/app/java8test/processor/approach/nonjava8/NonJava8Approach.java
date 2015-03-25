package com.app.java8test.processor.approach.nonjava8;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import com.app.java8test.main.CommonConstants;
import com.app.java8test.processor.approach.Approach;

public interface NonJava8Approach extends Approach {
	
	default List<String> readWordsFromText(File file, boolean parallel) throws IOException{
		BufferedReader bufferReader = Files.newBufferedReader(file.toPath());
		List<String> words = new ArrayList<>();
		String line = null;
		while ((line = bufferReader.readLine()) != null){
			String[] lineWords = line.split(CommonConstants.SPLIT_TEXT_REGEX); 
			for(String word : lineWords) {
				if (word.length() > 0) {
					words.add(word.toLowerCase());
				}
			}
		}
		return words;
	}

}
