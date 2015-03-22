package com.app.Java8Test.jcommander.converters;

import com.app.Java8Test.main.ProcessCommandEnum;
import com.beust.jcommander.IStringConverter;

public class ProcessCommandConverter implements
		IStringConverter<ProcessCommandEnum> {

	@Override
	public ProcessCommandEnum convert(String code) {
		ProcessCommandEnum convertedValue = ProcessCommandEnum.fromString(code);
		return convertedValue;
	}

}
