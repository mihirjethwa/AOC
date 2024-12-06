package com.mihir.AOC24;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

public class Day3Part1 {
	String inputTest = "resource/2024/Day3Test.txt";
	String input = "resource/2024/Day3.txt";
	
	private long compute(String s) {
		String[] sub = s.substring(4, s.length()-1).split(",");;
		return Integer.parseInt(sub[0])*Integer.parseInt(sub[1]);
	}
	
	public long getResult(List<String> in) {
		long result = 0;
		for(String x: in) {
			String regex  = "mul\\(\\d+\\,\\d+\\)";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(x);
			while(matcher.find()) {
				result += compute(matcher.group());
			}
		}
		return result;
	}
	
    public static void main( String[] args ) throws IOException
    {
        Day3Part1 solution = new Day3Part1();
        solution.run();
    }
    
    public void run() throws IOException {
    	List<String> lines = Resources.readLines(ClassLoader.getSystemResource(input), Charsets.UTF_8);
    	long result = getResult(lines);
    	System.out.println(result);
    }
}
