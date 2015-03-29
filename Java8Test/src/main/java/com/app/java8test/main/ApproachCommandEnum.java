package com.app.java8test.main;

import java.util.Arrays;

import com.beust.jcommander.ParameterException;

/**
 * Class holds the approach options: Java8 and NonJava8
 *
 */
public enum ApproachCommandEnum {
	
	Java8,
	NonJava8;
	
	public static ApproachCommandEnum fromString(String code) {
		return Arrays.stream(ApproachCommandEnum.values())
				.filter(e -> e.toString().equalsIgnoreCase(code))
				.findFirst()
				.orElseThrow(() -> new ParameterException(
							String.format("Value %s can not be converted to ApproachCommandEnum", code)));
	}

}
