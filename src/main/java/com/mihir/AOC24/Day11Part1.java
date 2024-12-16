package com.mihir.AOC24;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

public class Day11Part1 {
	String input = "resource/2024/Day11.txt";
	String inputTest = "resource/2024/Day11Test.txt";
	
	private long arrange(List<Long> initial) {
		for(int i=1;i<=25;i++) {
			List<Long> temp = new ArrayList<>();
			for(int j=0;j<initial.size();j++) {
				if(initial.get(j) == 0) {
					temp.add((long) 1);
				}else if(String.valueOf(initial.get(j)).length()%2 ==0) {
					String s = String.valueOf(initial.get(j));
					temp.add(Long.parseLong(s.substring(0, s.length()/2)));
					temp.add(Long.parseLong(s.substring((s.length()/2), s.length())));
				}else {
					temp.add(initial.get(j)*2024);
				}
			}
			initial = temp;
		}
		return initial.size();
	}
	
	public long getResult(List<String> in) {
		List<Long> list = new ArrayList<>();
		long result = 0;
		for(String s: in.get(0).split(" ")) {
			list.add(Long.parseLong(s));
		}
		result = arrange(list);
		return result;
	}
	
    public static void main( String[] args ) throws IOException
    {
        Day11Part1 solution = new Day11Part1();
        solution.run();
    }
    
    public void run() throws IOException {
    	List<String> lines = Resources.readLines(ClassLoader.getSystemResource(input), Charsets.UTF_8);
    	long result = getResult(lines);
    	System.out.println(result);
    }
}
