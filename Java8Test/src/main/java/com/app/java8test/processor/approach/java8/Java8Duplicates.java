package com.app.java8test.processor.approach.java8;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

public class Java8Duplicates implements Java8Approach {

	@Override
	public void taskExecution(File file, boolean parallel, PrintStream printer) {
		try {
			if (parallel) {
				
			} else {
				this.readWordsFromText(file, parallel)
					.stream(); // Stream<String>
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
