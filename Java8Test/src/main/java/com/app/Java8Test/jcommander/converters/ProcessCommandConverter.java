package com.app.java8test.jcommander.converters;

import com.app.java8test.main.ProcessCommandEnum;
import com.beust.jcommander.IStringConverter;

public class ProcessCommandConverter implements
		IStringConverter<ProcessCommandEnum> {

	@Override
	public ProcessCommandEnum convert(String code) {
		ProcessCommandEnum convertedValue = ProcessCommandEnum.fromString(code);
		return convertedValue;
	}

}
