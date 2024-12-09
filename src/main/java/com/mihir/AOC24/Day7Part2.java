package com.mihir.AOC24;

import java.io.IOException;
import java.util.List;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

public class Day7Part2 {
	String input = "resource/2024/Day7.txt";
	String inputTest = "resource/2024/Day7Test.txt";
	boolean flag = false;
	
	private void solve(long[] nums, int pivot, long result, long curr) {
		if(flag) return;
		if(curr == result) {
			flag = true;
			return;
		}
		if(pivot>=nums.length) return;
		
		solve(nums, pivot+1, result, curr+nums[pivot]);
		solve(nums, pivot+1, result, curr*nums[pivot]);
		long nextNumber = nums[pivot];
		long concatenatedValue = Long.parseLong(curr + "" + nextNumber);
		solve(nums, pivot + 1,result, concatenatedValue);
		
	}
	public long getResult(List<String> in) {
		long total = 0;
		for(String s: in) {
			String[] sArr = s.split(":");
			long result = Long.parseLong(sArr[0]);
			String[] arr = sArr[1].trim().split(" ");
			long[] nums = new long[arr.length];
			int i=0;
			for(String curr: arr) {
				nums[i] = Long.parseLong(curr);
				i++;
			}
			solve(nums, 1, result, nums[0]);
			if(flag) {
				total+=result;
				flag = false;
			}
		}
		return total;
	}
	
    public static void main( String[] args ) throws IOException
    {
        Day7Part2 solution = new Day7Part2();
        solution.run();
    }
    
    public void run() throws IOException {
    	List<String> lines = Resources.readLines(ClassLoader.getSystemResource(input), Charsets.UTF_8);
    	long result = getResult(lines);
    	System.out.println(result);
    }
}
