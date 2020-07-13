package com.biz.score.config;

public class Lines {
	
	public static String d_line = "";
	public static String s_line = "";
	
	static {
		
		for(int i = 0; i <80; i ++) {
			d_line += "=";
			s_line += "-";
		}
		
	}

}
