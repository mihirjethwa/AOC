package com.mihir.AOC24;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

public class Day2Part1 {

		String inputTest = "resource/2024/Day2Test.txt";
		String input = "resource/2024/Day2.txt";
		
		public long getReportCount(List<String> in) {
			long count = 0;
			for(String s: in) {
				String[] sArr = s.split(" ");
				boolean decreasing = false;
				if(Integer.parseInt(sArr[0])>Integer.parseInt(sArr[1])) decreasing = true;
				int i=1;
				for(;i<sArr.length;i++) {
					if(decreasing) {
						if(Integer.parseInt(sArr[i]) > Integer.parseInt(sArr[i-1])) break;
					}else if(Integer.parseInt(sArr[i]) < Integer.parseInt(sArr[i-1])) {
						break;
					}
					int difference = Math.abs(Integer.parseInt(sArr[i]) - Integer.parseInt(sArr[i-1]));
					if(difference > 3 || difference < 1 ) break;
				}
				if(i==sArr.length) count++;
			}
			
			return count;
		}
		
	    public static void main( String[] args ) throws IOException
	    {
	        Day2Part1 solution = new Day2Part1();
	        solution.run();
	    }
	    
	    public void run() throws IOException {
	    	List<String> lines = Resources.readLines(ClassLoader.getSystemResource(input), Charsets.UTF_8);
	    	long result = getReportCount(lines);
	    	System.out.println(result);
	    }

}
