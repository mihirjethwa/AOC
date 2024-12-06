package com.mihir.AOC24;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

public class Day1Part1 
{
	String input = "resource/2024/Day1.txt";
	
	public long getResult(List<String> in) {
		List<Integer> list1 = new ArrayList<>();
		List<Integer> list2 = new ArrayList<>();
		for(String s: in) {
			
			String[] sArr = s.split("\\s+");
			list1.add(Integer.parseInt(sArr[0]));
			list2.add(Integer.parseInt(sArr[1]));
		}
		Collections.sort(list1);
		Collections.sort(list2);
		long result = 0;
		for(int i=0;i<list1.size();i++) {
			result += Math.abs(list1.get(i) - list2.get(i));
		}
		return result;
	}
	
    public static void main( String[] args ) throws IOException
    {
        Day1Part1 solution = new Day1Part1();
        solution.run();
    }
    
    public void run() throws IOException {
    	List<String> lines = Resources.readLines(ClassLoader.getSystemResource(input), Charsets.UTF_8);
    	long result = getResult(lines);
    	System.out.println(result);
    }
    
    
}
