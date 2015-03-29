package com.app.java8test.jcommander.converters;

import java.io.File;

import com.beust.jcommander.IStringConverter;

/**
 * Converter to transform the string command line to
 * the File object
 *
 */
public class FileConverter implements IStringConverter<File> {

	public File convert(String value) {
		return new File(value);
	}

}
