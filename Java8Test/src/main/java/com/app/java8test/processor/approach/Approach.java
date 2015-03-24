package com.app.java8test.processor.approach;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

public interface Approach {
	
	void taskExecution(File file, boolean parallel, PrintStream printer);
	
	List<String> readWordsFromText(File file, boolean parallel) throws IOException;

}
