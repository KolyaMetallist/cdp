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
}
