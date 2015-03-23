package com.app.Java8Test.processor;

public class ApproachFactory {
	
	public static Approach getApproach(String type) {
		return type.equals("Java8") ? new Java8Approach() : null;
	}

}
