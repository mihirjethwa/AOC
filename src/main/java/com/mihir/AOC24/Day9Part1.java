package com.mihir.AOC24;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

public class Day9Part1 {
	String input = "resource/2024/Day9.txt";
	String inputTest = "resource/2024/Day9Test.txt";
	List<Integer> list = new ArrayList<>();	
	
	private void generateBlocks() {
		int i = 0;
		boolean even = false;
		List<Integer> li = new ArrayList<>();
		for(int x : list) {
			if(even) {
				while(x>0) {
					li.add(-1);
					x--;
				}
				even = false;
			}else {
				while(x>0) {
					li.add(i);
					x--;
				}
				i++;
				even = true;
			}
		}
		list = li;
	}
	private void moveFileBlocks() {
		int left = 0;
		int  right = list.size()-1;
		while(list.get(left) != -1) {
			left++;
		}
		while(list.get(right) == -1) {
			right--;
		}
		while(left<right) {
			list.set(left, list.get(right));
			list.set(right, -1);
			left++;
			while(list.get(left) != -1) {
				left++;
			}
			right--;
			while(list.get(right) == -1) {
				right--;
			}
		}
	}
	private long calculateCheckSum() {
		long result = 0;
		for(int i=0;i<list.size();i++) {
			if(list.get(i) == -1) break;
			result += i * list.get(i);
		}
		return result;
	}
	public long getResult(List<String> in) {
		for(String s:in) {
			for(int i=0;i<s.length();i++) {
				list.add(s.charAt(i)-'0');
			}
		}
		generateBlocks();
		moveFileBlocks();
		long result = calculateCheckSum();
		return result;
	}
	
    public static void main( String[] args ) throws IOException
    {
        Day9Part1 solution = new Day9Part1();
        solution.run();
    }
    
    public void run() throws IOException {
    	List<String> lines = Resources.readLines(ClassLoader.getSystemResource(input), Charsets.UTF_8);
    	long result = getResult(lines);
    	System.out.println(result);
    }
}
