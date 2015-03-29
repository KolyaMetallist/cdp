package com.app.java8test.jcommander.converters;

import com.app.java8test.main.ApproachCommandEnum;
import com.beust.jcommander.IStringConverter;

/**
 * Converter to transform the string command line to
 * the ApproachCommandEnum object
 *
 */
public class ApproachCommandConverter implements
		IStringConverter<ApproachCommandEnum> {

	@Override
	public ApproachCommandEnum convert(String value) {
		ApproachCommandEnum convertedValue = ApproachCommandEnum.fromString(value);
		return convertedValue;
	}

}
