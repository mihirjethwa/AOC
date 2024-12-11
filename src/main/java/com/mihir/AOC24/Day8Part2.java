package com.mihir.AOC24;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

public class Day8Part2 {
	String input = "resource/2024/Day8.txt";
	String inputTest = "resource/2024/Day8Test.txt";
	Set<String> set = new HashSet<>();
	int hCount = 0;
	
	private void placeAntinode(char[][] matrix, int[] coordinate,  int i, int j, int m, int n) {
		int downAx = i - coordinate[0];
		int downAy = j - coordinate[1];
		int tempi = i;
		int tempj = j;
		while(tempi >=0 && tempi <m && tempj>=0 && tempj <n) {
			set.add(tempi+","+(tempj));
			tempi = tempi+downAx;
			tempj = tempj+downAy;
		}
		int upAx = coordinate[0] - i;
		int upAy = coordinate[1] - j;
		tempi=coordinate[0];
		tempj=coordinate[1];
		while(tempi >=0 && tempi <m && tempj >=0 && tempj <n) {
			set.add(tempi+","+(tempj));
			tempi = tempi+upAx;
			tempj = tempj+upAy;
		}
	}
	
	private void generateAntinodes(char[][] matrix, int m, int n) {
		//Character and list of coordinates
		HashMap<Character, List<int []>> map = new HashMap<>();
		for(int i=0;i<m;i++) {
			for(int j=0;j<n;j++) {
				if(matrix[i][j] != '.' && matrix[i][j] != '#') {
					if(map.containsKey(matrix[i][j])) {
						set.add(i+","+j);
						for(int[] coordinate: map.get(matrix[i][j])) {
							placeAntinode(matrix, coordinate, i, j, m, n);
						}
						map.get(matrix[i][j]).add(new int[] {i,j});
					}else {
						List<int[]> li = new ArrayList<>();
						li.add(new int[] {i, j});
						map.put(matrix[i][j], li);
					}
				}
			}
		}
	}
	
	public long getResult(List<String> in) {
		int m = in.size();
		int n = in.get(0).length();
		char[][] matrix = new char[m][n];
		for(int i=0;i<m;i++) {
			for(int j=0;j<n;j++) {
				matrix[i][j] = in.get(i).charAt(j);
			}
		}
		generateAntinodes(matrix, m, n);
		return set.size();
	}
	
    public static void main( String[] args ) throws IOException
    {
        Day8Part2 solution = new Day8Part2();
        solution.run();
    }
    
    public void run() throws IOException {
    	List<String> lines = Resources.readLines(ClassLoader.getSystemResource(input), Charsets.UTF_8);
    	long result = getResult(lines);
    	System.out.println(result);
    }
}
