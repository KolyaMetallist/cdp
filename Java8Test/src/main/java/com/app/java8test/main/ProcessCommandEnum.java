package com.app.java8test.main;

import java.util.Arrays;

import com.beust.jcommander.ParameterException;

/**
 * Class holds the task options: frequency, length, duplicates
 *
 */
public enum ProcessCommandEnum {
	
	frequency,
	length,
	duplicates;
	
	public static ProcessCommandEnum fromString(String code) {
		return Arrays.stream(ProcessCommandEnum.values())
				.filter(e -> e.toString().equalsIgnoreCase(code))
				.findFirst()
				.orElseThrow(() -> new ParameterException(
							String.format("Value %s can not be converted to ProcessCommandEnum", code)));
	}

}
