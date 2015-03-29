package com.app.java8test.processor;

import java.io.File;
import java.io.PrintStream;
import java.util.Collections;

import com.app.java8test.main.ApproachCommandEnum;
import com.app.java8test.main.ProcessCommandEnum;
import com.app.java8test.processor.approach.AbstractApproachFactory;

public class TextAnalyzerProcessor {
	
	private File file;
	
	private ProcessCommandEnum command;
	
	private boolean parallel;
	
	private ApproachCommandEnum approach;

	public TextAnalyzerProcessor(File file, ProcessCommandEnum command,
			boolean parallel, ApproachCommandEnum approach) {
		this.file = file;
		this.command = command;
		this.parallel = parallel;
		this.approach = approach;
	}

	public void execute() {
		PrintStream printer = System.out;
		StringBuffer str = new StringBuffer()
			.append("Command: " + command)
			.append("\nApproach: " + approach)
			.append("\nParallel: " + parallel + "\n");
		printer.println(str);
		
		long tStart = System.currentTimeMillis();
		
		if (parallel) {
			Collections.synchronizedList(
				AbstractApproachFactory
					.getInstance(approach)
					.getApproachTask(command)
					.taskExecution(file, parallel))
				.parallelStream()
				.forEachOrdered(printer::println);
		} else {
			AbstractApproachFactory
				.getInstance(approach)
				.getApproachTask(command)
				.taskExecution(file, parallel)
				.stream()
				.forEach(printer::println);
		}
		
		long elapsedTime = System.currentTimeMillis() - tStart;
		printer.println("\nElapsed time: " + elapsedTime + " ms");
	}

}
