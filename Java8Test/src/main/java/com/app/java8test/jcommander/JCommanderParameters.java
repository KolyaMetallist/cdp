package com.app.java8test.jcommander;

import java.io.File;

import com.app.java8test.jcommander.converters.ApproachCommandConverter;
import com.app.java8test.jcommander.converters.FileConverter;
import com.app.java8test.jcommander.converters.ProcessCommandConverter;
import com.app.java8test.main.ApproachCommandEnum;
import com.app.java8test.main.ProcessCommandEnum;
import com.beust.jcommander.Parameter;

/**
 * Provides the command line parameters and their mapping to the objects
 *
 */
public class JCommanderParameters {
	@Parameter(names = {"-i", "--input"}, 
			description = "Input file", 
			converter = FileConverter.class, 
			required = true)
	private File file;
	
	@Parameter(names = {"-t", "--task"},
			description = "Process task",
			converter = ProcessCommandConverter.class,
			required = true)
	private ProcessCommandEnum task;
	
	@Parameter(names = {"-p", "--parallel"},
			description = "Parallel")
	private boolean isParallel = false;
	
	@Parameter(names = {"-a", "--approach"},
			description = "Approach: Java8 or NonJava8",
			converter = ApproachCommandConverter.class,
			required = false)
	private ApproachCommandEnum approach = ApproachCommandEnum.Java8;
	
	@Parameter(names = "--help", help = true)
	private boolean help;

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public ProcessCommandEnum getTask() {
		return task;
	}

	public void setTask(ProcessCommandEnum task) {
		this.task = task;
	}

	public boolean isParallel() {
		return isParallel;
	}

	public void setParallel(boolean isParallel) {
		this.isParallel = isParallel;
	}

	public ApproachCommandEnum getApproach() {
		return approach;
	}

	public void setApproach(ApproachCommandEnum approach) {
		this.approach = approach;
	}

	public boolean isHelp() {
		return help;
	}

	public void setHelp(boolean help) {
		this.help = help;
	}
}
