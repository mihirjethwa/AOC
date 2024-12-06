package com.mihir.AOC24;

import java.io.IOException;
import java.util.List;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

public class Day4Part2 {
	String inputTest = "resource/2024/Day4Test.txt";
	String input = "resource/2024/Day4.txt";
	char[][] arr;
	
	int m;
	int n;
	
	private int check(int i, int j) {
		if(i-1 <0 || i+1 >= m || j-1<0 || j+1 >= n) {
			return 0;
		}
		if(arr[i-1][j-1] == 'M' && arr[i+1][j+1] == 'S') {
			if(arr[i+1][j-1] == 'M' && arr[i-1][j+1] == 'S') {
				return 1;
			}else if(arr[i+1][j-1] == 'S' && arr[i-1][j+1] == 'M'){
				return 1;
			}
		}
		if(arr[i-1][j-1] == 'S' && arr[i+1][j+1] == 'M') {
			if(arr[i+1][j-1] == 'M' && arr[i-1][j+1] == 'S') {
				return 1;
			}else if(arr[i+1][j-1] == 'S' && arr[i-1][j+1] == 'M'){
				return 1;
			}
		}
		return 0;
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
				if(arr[i][j]=='A') {
					count+=check(i, j);
				}
			}
		}
		return count;
	}
	
    public static void main( String[] args ) throws IOException
    {
        Day4Part2 solution = new Day4Part2();
        solution.run();
    }
    
    public void run() throws IOException {
    	List<String> lines = Resources.readLines(ClassLoader.getSystemResource(input), Charsets.UTF_8);
    	long result = getResult(lines);
    	System.out.println(result);
    }
}
