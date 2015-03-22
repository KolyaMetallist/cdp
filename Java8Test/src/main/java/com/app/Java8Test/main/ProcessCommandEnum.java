package com.app.Java8Test.main;

import java.util.Arrays;
import com.beust.jcommander.ParameterException;

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
