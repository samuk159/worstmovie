package com.samuk159.worstmovie.util;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class StringUtils {

	public static List<String> splitByComma(String str) {
		List<String> list = new LinkedList<>();
		
		if (str != null) {
			String[] split = str.split(", ");		
			
			for (int i = 0; i < split.length - 1; i++) {
				list.add(split[i]);
			}
			
			if (split.length > 0) {
				String[] andSplit = split[split.length - 1].split(" and ");
				
				for (String s : andSplit) {
					list.add(s);
				}
			}			
		}
		
		return list;
	}
	
}
