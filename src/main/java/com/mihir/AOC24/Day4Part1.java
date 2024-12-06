package com.mihir.AOC24;

import java.io.IOException;
import java.util.List;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;

public class Day4Part1 {
	String inputTest = "resource/2024/Day4Test.txt";
	String input = "resource/2024/Day4.txt";
	char[][] arr;
	String check = "XMAS";
	int m;
	int n;
	
	private int right(int i, int j, int initial) {
		if(j-initial == 4) {
			return 1;
		}
		if(j>= n || check.charAt(j-initial)!=arr[i][j]) {
			return 0;
		}
		
		return right(i, j+1, initial);	
	}
	private int left(int i, int j, int initial) {
		if(Math.abs(j-initial) == 4) {
			return 1;
		}
		if(j<0 || check.charAt(Math.abs(j-initial))!=arr[i][j]) {
			return 0;
		}
		
		return left(i, j-1, initial);	
	}
	private int top(int i, int j, int initial) {
		if(Math.abs(i-initial) == 4) {
			return 1;
		}
		if(i<0 || check.charAt(Math.abs(i-initial))!=arr[i][j]) {
			return 0;
		}
		
		return top(i-1, j, initial);	
	}
	private int bottom(int i, int j, int initial) {
		if(Math.abs(i-initial) == 4) {
			return 1;
		}
		if(i>=m || check.charAt(Math.abs(i-initial))!=arr[i][j]) {
			return 0;
		}
		
		return bottom(i+1, j, initial);	
	}
	private int topRight(int i, int j, int initial) {
		if(Math.abs(i-initial) == 4) {
			return 1;
		}
		if(i<0 || j>=n || check.charAt(Math.abs(i-initial))!=arr[i][j]) {
			return 0;
		}
		
		return topRight(i-1, j+1, initial);	
	}
	private int topLeft(int i, int j, int initial) {
		if(Math.abs(i-initial) == 4) {
			return 1;
		}
		if(i<0 || j<0|| check.charAt(Math.abs(i-initial))!=arr[i][j]) {
			return 0;
		}
		
		return topLeft(i-1, j-1, initial);	
	}
	private int bottomRight(int i, int j, int initial) {
		if(Math.abs(i-initial) == 4) {
			return 1;
		}
		if(i>=m || j>=m|| check.charAt(Math.abs(i-initial))!=arr[i][j]) {
			return 0;
		}
		
		return bottomRight(i+1, j+1, initial);	
	}
	private int bottomLeft(int i, int j, int initial) {
		if(Math.abs(i-initial) == 4) {
			return 1;
		}
		if(i>=m|| j<0|| check.charAt(Math.abs(i-initial))!=arr[i][j]) {
			return 0;
		}
		
		return bottomLeft(i+1, j-1, initial);	
	}
	
	public long getResult(List<String> in) {
		 m = in.size();
		 n = in.get(0).length();
		arr = new char[m][n];
		long count = 0;
		for(int i=0;i<m;i++) {
			for(int j=0;j<n;j++) {
				arr[i][j] = in.get(i).charAt(j);
			}
		}
		for(int i=0;i<m;i++) {
			for(int j=0;j<n;j++) {
				if(arr[i][j]=='X') {
					count+=right(i, j, j);
					count+=left(i, j, j);
					count+=top(i, j, i);
					count+=bottom(i, j, i);
					count+=topRight(i, j, i);
					count+=topLeft(i, j, i);
					count+=bottomRight(i, j, i);
					count+=bottomLeft(i, j, i);
				}
			}
		}
		return count;
	}
	
    public static void main( String[] args ) throws IOException
    {
        Day4Part1 solution = new Day4Part1();
        solution.run();
    }
    
    public void run() throws IOException {
    	List<String> lines = Resources.readLines(ClassLoader.getSystemResource(input), Charsets.UTF_8);
    	long result = getResult(lines);
    	System.out.println(result);
    }
}
