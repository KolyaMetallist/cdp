package com.app.java8test.processor.approach.nonjava8;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.app.java8test.processor.approach.Approach;

public interface NonJava8Approach extends Approach {
	
	default List<String> readWordsFromText(File file, boolean parallel) throws IOException{
		
		return null;
	}

}
