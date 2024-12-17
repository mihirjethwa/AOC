package com.mihir.AOC24;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

public class Day12Part1 {
	String input = "resource/2024/Day12.txt";
	String inputTest = "resource/2024/Day12Test.txt";
	private final int[][] dirs = new int[][] {{0,1}, {0,-1}, {1,0},{-1, 0}};
	
	private int[][] transform(char[][] matrix){
		int m = matrix.length;
		int n = matrix[0].length;
		int[][] transformed = new int[m][n];
		int counter = 1;
		for(int i=0;i<m;i++) {
			for(int j=0;j<n;j++) {
				if(transformed[i][j] == 0) {
					char current = matrix[i][j];
					makeRegion(matrix, transformed, i, j, counter, current, m, n);
					counter++;
				}
			}
		}
		return transformed;
	}
	
	private void makeRegion(char[][] matrix, int[][] transformed, int i, int j, int counter, char current, int m, int n) {
		if(matrix[i][j] != current || transformed[i][j] != 0) {
			return;
		}
		transformed[i][j] = counter;
		for(int[] dir: dirs) {
			int ni = dir[0]+i;
			int nj = dir[1]+j;
			if(ni < m && ni >= 0 && nj < n && nj >= 0) {
				makeRegion(matrix, transformed, ni, nj, counter, current, m, n);
			}
		}
	}
	
	private long computeBorder(int[][] matrix) {
		Map<Integer, Integer> freqMap= new HashMap<>();
		Map<Integer, Integer> perimeterMap= new HashMap<>();
		int m = matrix.length;
		int n = matrix[0].length;
		for(int i=0;i<m;i++) {
			for(int j=0;j<n;j++) {
				//System.out.println(matrix[i][j]);
				int perimeter = 0;
				for(int[] dir: dirs) {
					int ni = dir[0]+i;
					int nj = dir[1]+j;
					if(ni >= m || ni < 0 || nj >=n || nj < 0) {
						perimeter++;
					}else {
						if(matrix[ni][nj] != matrix[i][j]) {
							perimeter++;
						}
					}
				}
				freqMap.put(matrix[i][j], freqMap.getOrDefault(matrix[i][j], 0)+1);
				perimeterMap.put(matrix[i][j], perimeterMap.getOrDefault(matrix[i][j], 0)+perimeter);
			}
		}
		return computeCost(freqMap, perimeterMap);
	}
	
	private long computeCost(Map<Integer, Integer> freqMap, Map<Integer, Integer> perimeterMap) {
		
		long result = 0;
		for(int x: freqMap.keySet()) {
			//System.out.print(x + " => ");
			//System.out.print(freqMap.get(x) + " , ");
			//System.out.println(perimeterMap.get(x));
			result+=freqMap.get(x) * perimeterMap.get(x);
		}
		return result;
	}
	
	public long getResult(List<String> in) {
		int m = in.size();
		int n = in.get(0).length();
		char[][] matrix = new char[m][n];
		for(int i=0;i<m;i++) {
			String curr = in.get(i);
			for(int j=0;j<n;j++) {
				matrix[i][j] = curr.charAt(j);
			}
		}
		int[][] transformedMatrix = transform(matrix);
		long result = computeBorder(transformedMatrix);
		return result;
	}
	
    public static void main( String[] args ) throws IOException
    {
        Day12Part1 solution = new Day12Part1();
        solution.run();
    }
    
    public void run() throws IOException {
    	List<String> lines = Resources.readLines(ClassLoader.getSystemResource(input), Charsets.UTF_8);
    	long result = getResult(lines);
    	System.out.println(result);
    }
}
