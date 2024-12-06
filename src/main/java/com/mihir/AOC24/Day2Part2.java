package com.mihir.AOC24;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

public class Day2Part2 {
	String inputTest = "resource/2024/Day2Test.txt";
	String input = "resource/2024/Day2.txt";
	
	private boolean checkAsc(List<Integer> nums) {
		for(int i=1;i<nums.size();i++) {
			if(nums.get(i)<nums.get(i-1) || Math.abs(nums.get(i)-nums.get(i-1))>3 || Math.abs(nums.get(i)-nums.get(i-1))<1) {
				return false;
			}
		}
		return true;
	}
	private boolean checkDesc(List<Integer> nums) {
		for(int i=1;i<nums.size();i++) {
			if(nums.get(i)>nums.get(i-1) || Math.abs(nums.get(i)-nums.get(i-1))>3 || Math.abs(nums.get(i)-nums.get(i-1))<1) {
				return false;
			}
		}
		return true;
	}
	
	public int getReportCount(List<String> in) {
		int count = 0;
		
		for(String s: in) {
			String[] sArr = s.split(" ");
			
			List<Integer> nums = new ArrayList<>();
			for(int i=0;i<sArr.length;i++) {
				nums.add(Integer.parseInt(sArr[i]));
			}
			for(int i=0;i<nums.size();i++) {
				int x = nums.remove(i);
				boolean check = checkAsc(nums);
				boolean check2 = checkDesc(nums);
				if(check || check2) {
					count++;
					break;
				}
				nums.add(i, x);
			}
		}
		return count;
	}
	
	
    public static void main( String[] args ) throws IOException
    {
        Day2Part2 solution = new Day2Part2();
        solution.run();
    }
    
    public void run() throws IOException {
    	List<String> lines = Resources.readLines(ClassLoader.getSystemResource(input), Charsets.UTF_8);
    	int result = getReportCount(lines);
    	System.out.println(result);
    }


}
