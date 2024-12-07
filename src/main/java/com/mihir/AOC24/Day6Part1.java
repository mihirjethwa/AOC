package com.mihir.AOC24;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

public class Day6Part1 {
	String input = "resource/2024/Day6.txt";
	String inputTest = "resource/2024/Day6Test.txt";
	Set<String> set = new HashSet<>();
	int[] top = new int[] {-1, 0};
	int[] right = new int[] {0, 1};
	int[] bottom = new int[] {1, 0};
	int[] left = new int[] {0, -1};
	
	
	//1 = top, 2 = right, 3 = bottom, 4 = left
	private void move(int i, int j, int direction, int m, int n, char[][] matrix) {
		if(i<0 || i>=m || j<0 || j>=n) {
			return;
		}
		if(matrix[i][j] == '#') {
			if(direction == 1) {
				i+=bottom[0];
				j+=bottom[1];
				i+=right[0];
				j+=right[1];
				
				move(i, j, 2, m, n, matrix);
			}
			if(direction == 2) {
				i+=left[0];
				j+=left[1];
				i+=bottom[0];
				j+=bottom[1];
				move(i, j, 3, m, n, matrix);
			}
			if(direction == 3) {
				i+=top[0];
				j+=top[1];
				i+=left[0];
				j+=left[1];
				move(i, j, 4, m, n, matrix);
			}
			if(direction == 4) {
				i+=right[0];
				j+=right[1];
				i+=top[0];
				j+=top[1];
				move(i, j, 1, m, n, matrix);
			}
		}else {
			set.add(i+","+j);
			if(direction == 1) {
				i+=top[0];
				j+=top[1];
				move(i, j, 1, m, n, matrix);
			}
			if(direction == 2) {
				i+=right[0];
				j+=right[1];
				move(i, j, 2, m, n, matrix);
			}
			if(direction == 3) {
				i+=bottom[0];
				j+=bottom[1];
				move(i, j, 3, m, n, matrix);
			}
			if(direction == 4) {
				i+=left[0];
				j+=left[1];
				move(i, j, 4, m, n, matrix);
			}
		}
		
	}
	
	private void move2(int x, int y, int direction, int m, int n, char[][] matrix) {
		int dir = direction;
		int i = x;
		int j = y;
		while(true) {
			System.out.println(" i: "+ i+" j :"+j+ " direction :" + dir);
			if(i<0 || i>=m || j<0 || j>=n) {
				break;
			}
			if(matrix[i][j] == '#') {
				if(dir == 1) {
					i+=bottom[0];
					j+=bottom[1];
					i+=right[0];
					j+=right[1];
					dir = 2;
				}else if(dir == 2) {
					i+=left[0];
					j+=left[1];
					i+=bottom[0];
					j+=bottom[1];
					dir = 3;
				}else if(dir == 3) {
					i+=top[0];
					j+=top[1];
					i+=left[0];
					j+=left[1];
					dir = 4;
				}else if(dir == 4) {
					i+=right[0];
					j+=right[1];
					i+=top[0];
					j+=top[1];
					dir = 1;
				}
			}else {
				set.add(i+","+j);
				if(dir == 1) {
					i+=top[0];
					j+=top[1];
				}
				if(dir == 2) {
					i+=right[0];
					j+=right[1];
				}
				if(dir == 3) {
					i+=bottom[0];
					j+=bottom[1];
				}
				if(dir == 4) {
					i+=left[0];
					j+=left[1];
				}
			}
		}
	}
	
	public int getResult(List<String> in) {
		int m = in.size();
		int n = in.get(0).length();
		char[][] matrix = new char[m][n];
		int posX = -1;
		int posY = -1;
		for(int i=0;i<m;i++) {
			for(int j=0;j<n;j++) {
				matrix[i][j] = in.get(i).charAt(j);
				if(matrix[i][j] == '^') {
					matrix[i][j] = '.';
					posX = i;
					posY = j;
				}
			}
		}
//		for(int i=0;i<m;i++) {
//			for(int j=0;j<n;j++) {
//				System.out.print(matrix[i][j]);
//			}
//			System.out.println();
//		}
		move2(posX, posY, 1, m, n, matrix);
		//System.out.println(set);
		return set.size();
	}
	
    public static void main( String[] args ) throws IOException
    {
        Day6Part1 solution = new Day6Part1();
        solution.run();
    }
    
    public void run() throws IOException {
    	List<String> lines = Resources.readLines(ClassLoader.getSystemResource(input), Charsets.UTF_8);
    	int result = getResult(lines);
    	System.out.println(result);
    }
}
