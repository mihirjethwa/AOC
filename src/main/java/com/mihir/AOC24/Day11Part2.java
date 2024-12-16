package com.mihir.AOC24;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

public class Day11Part2 {
	String input = "resource/2024/Day11.txt";
	String inputTest = "resource/2024/Day11Test.txt";
	int count = 0;
	Map<String, Long> memo = new HashMap<>();

	
	private long arrange(Long digit, int cycleLeft) {
		if(cycleLeft == 0) {
			return 1;
		}
		//System.out.println(digit + " cycle = " + cycle);
		String key = digit + "," + cycleLeft;
		if (memo.containsKey(key)) {
		      return memo.get(key);
		}
		long result = 0;  
		if(digit == 0) {
			result = arrange(1L, cycleLeft-1);
		}else if(String.valueOf(digit).length()%2 ==0) {
			String s = String.valueOf(digit);
			long result1 = arrange(Long.parseLong(s.substring(0, s.length()/2)), cycleLeft-1);
			long result2 = arrange(Long.parseLong(s.substring((s.length()/2), s.length())), cycleLeft-1);
			result = result1+ result2;
		}else {
			result = arrange(digit*2024, cycleLeft-1);
		}
		memo.put(key, result);
		return memo.get(key);
	}
	
	public long getResult(List<String> in) {
		List<Long> list = new ArrayList<>();
		long result = 0;
		for(String s: in.get(0).split(" ")) {
			list.add(Long.parseLong(s));
		}
		for(Long x: list) {
			result += arrange(x, 75);
		}
		
		return result;
	}
	
    public static void main( String[] args ) throws IOException
    {
        Day11Part2 solution = new Day11Part2();
        solution.run();
    }
    
    public void run() throws IOException {
    	List<String> lines = Resources.readLines(ClassLoader.getSystemResource(input), Charsets.UTF_8);
    	long result = getResult(lines);
    	System.out.println(result);
    }
}
