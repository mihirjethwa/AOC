package com.mihir.AOC24;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

public class Day10Part1 {
	String input = "resource/2024/Day10.txt";
	String inputTest = "resource/2024/Day10Test.txt";
	int m;
	int n;
	int [][] dirs = new int[][] {{0,1}, {0, -1}, {1, 0}, {-1, 0}};
	
	private int getTrailScore(int[][] matrix, int i, int j, int curr,boolean[][] visited) {
		if(i>=m || i<0 || j>=n || j<0) {
			return 0;
		}
		if(matrix[i][j] != curr || visited[i][j] == true) return 0;
		if(matrix[i][j] == 9) {
			visited[i][j] = true;
			return 1;
		}
		visited[i][j] = true;
		int a = getTrailScore(matrix, i+1, j, curr+1, visited);
		int b = getTrailScore(matrix, i-1, j, curr+1, visited);
		int c = getTrailScore(matrix, i, j+1, curr+1, visited);
		int d = getTrailScore(matrix, i, j-1, curr+1, visited);
		return a+b+c+d;
	}
	public long getResult(List<String> in) {
		int count = 0;
		m = in.size();
		n = in.get(0).length();
		int[][] matrix = new int[m][n];
		for(int i=0;i<m;i++) {
			for(int j=0;j<n;j++) {
				matrix[i][j] = in.get(i).charAt(j) - '0';
			}
		}
		
		for(int i=0;i<m;i++) {
			for(int j=0;j<n;j++) {
				if(matrix[i][j] == 0) {
					boolean[][] visited = new boolean[m][n];
					count+=getTrailScore(matrix, i, j, 0, visited);
				}
			}
		}
		return count;
	}
	
    public static void main( String[] args ) throws IOException
    {
        Day10Part1 solution = new Day10Part1();
        solution.run();
    }
    
    public void run() throws IOException {
    	List<String> lines = Resources.readLines(ClassLoader.getSystemResource(input), Charsets.UTF_8);
    	long result = getResult(lines);
    	System.out.println(result);
    }
}
