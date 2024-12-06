package com.mihir.AOC24;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

public class Day1Part2 {

	String input = "resource/2024/Day1.txt";
	
	public long getResult(List<String> in) {
		List<Integer> list1 = new ArrayList<>();
		HashMap<Integer, Integer> map = new HashMap<>();
		
		for(String s: in) {
			String[] sArr = s.split("\\s+");
			list1.add(Integer.parseInt(sArr[0]));
			int x = Integer.parseInt(sArr[1]);
			map.put(x,map.getOrDefault(x,0)+1);
		}
		
		long result = 0;
		for(int i=0;i<list1.size();i++) {
			result += list1.get(i)*map.getOrDefault(list1.get(i), 0);
		}
		return result;
	}
	
    public static void main( String[] args ) throws IOException
    {
        Day1Part2 solution = new Day1Part2();
        solution.run();
    }
    
    public void run() throws IOException {
    	List<String> lines = Resources.readLines(ClassLoader.getSystemResource(input), Charsets.UTF_8);
    	long result = getResult(lines);
    	System.out.println(result);
    }
    
    

}
