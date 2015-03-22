package com.app.Java8Test.jcommander;

import java.io.File;

import com.app.Java8Test.jcommander.converters.FileConverter;
import com.app.Java8Test.jcommander.converters.ProcessCommandConverter;
import com.beust.jcommander.Parameter;

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
	private ProcessCommandConverter command;
	
	@Parameter(names = {"-p", "--parallel"},
			description = "Parallel")
	private boolean isParallel = false;
	
	@Parameter(names = "--help", help = true)
	private boolean help;

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public ProcessCommandConverter getCommand() {
		return command;
	}

	public void setCommand(ProcessCommandConverter command) {
		this.command = command;
	}

	public boolean isParallel() {
		return isParallel;
	}

	public void setParallel(boolean isParallel) {
		this.isParallel = isParallel;
	}

	public boolean isHelp() {
		return help;
	}

	public void setHelp(boolean help) {
		this.help = help;
	}
}
