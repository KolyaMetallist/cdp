package com.app.java8test.processor.approach;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface Approach<T> {
	
	List<?> taskExecution(File file, boolean parallel);
	
	List<String> readWordsFromText(File file, boolean parallel) throws IOException;

}
