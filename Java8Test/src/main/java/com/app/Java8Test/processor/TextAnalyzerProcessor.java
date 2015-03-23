package com.app.Java8Test.processor;

import java.io.File;

import com.app.Java8Test.main.ProcessCommandEnum;

public class TextAnalyzerProcessor {
	
	private File file;
	
	private ProcessCommandEnum command;
	
	private boolean parallel;

	public TextAnalyzerProcessor(File file, ProcessCommandEnum command,
			boolean parallel) {
		this.file = file;
		this.command = command;
		this.parallel = parallel;
	}

	public void execute() {
		switch (command) {
			case frequency:
				ApproachFactory.getApproach("Java8").frequenceTask(file, parallel);
				break;
			case duplicates:
				break;
			case length:
				break;
			default:
				break;
		}	
	}

}
