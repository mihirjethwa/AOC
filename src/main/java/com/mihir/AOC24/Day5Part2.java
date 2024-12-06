package com.mihir.AOC24;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

public class Day5Part2 {
	String input = "resource/2024/Day5.txt";
	String inputTest = "resource/2024/Day5Test.txt";
	
	public long getResult(List<String> in) {
		Map<Integer, List<Integer>> adjList = new HashMap<>();
		int sum = 0;
		for(String s: in) {
			if(s.contains("|")) {
				String[] arr = s.split("\\|");
				int u = Integer.parseInt(arr[1]);
				int v = Integer.parseInt(arr[0]);
				if(!adjList.containsKey(u)) {
					adjList.put(u, new ArrayList<>());
				}
				adjList.get(u).add(v);
			}
			
			if(s.contains(",")) {
				String[] arr = s.split(",");
				int n = arr.length;
				int[] nums = new int[n];
				Map<Integer, Integer> map = new HashMap<>();
				for(int i=0;i<n;i++) {
					nums[i]= Integer.parseInt(arr[i]);
					map.put(nums[i], i);
				}
				boolean flag = true;
				for(int i=0;i<n;i++) {
					map.remove(nums[i]);
					Queue<Integer> q = new LinkedList<>();
					q.add(nums[i]);
					while(!q.isEmpty()) {
						int curr = q.poll();
						List<Integer> li = adjList.get(curr);
						if(li ==null)continue;
						for(int x:li) {
							if(map.containsKey(x)) {
								flag = false;
								int temp = nums[i];
								nums[i] = x;
								int idx = map.get(x);
								nums[idx] = temp;
								map.remove(x);
								map.put(temp, idx);
								q.add(x);
								break;
							}
						}
					}
				}
				
				if(!flag) {
					sum+=nums[n/2];
				}
			}
		}
		return sum;
	}
	
    public static void main( String[] args ) throws IOException
    {
        Day5Part2 solution = new Day5Part2();
        solution.run();
    }
    
    public void run() throws IOException {
    	List<String> lines = Resources.readLines(ClassLoader.getSystemResource(input), Charsets.UTF_8);
    	long result = getResult(lines);
    	System.out.println(result);
    }
}
