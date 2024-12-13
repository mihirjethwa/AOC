package com.mihir.AOC24;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

public class Day9Part2 {
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
	
	private void moveFile() {
        int maxID = list.get(list.size()-1);
        for (int i = maxID; i >= 0; --i) {
            int[] file = findFile(i);
            int fileBegin = file[0];
            int fileEnd = file[1];
            int fileLength = fileEnd - fileBegin;
            int[] newLocation = findNewLocation(fileBegin, fileLength);
            int newBegin = newLocation[0];
            int newEnd = newLocation[1];
            if (newEnd - newBegin == fileLength && list.get(newBegin) == -1) {
                moveFile(fileBegin, fileLength, newBegin);
            }
        }
    }

    private int[] findFile(int ID) {
        int fileBegin = 0;
        while (fileBegin < list.size() &&  list.get(fileBegin) != ID) {
            ++fileBegin;
        }
        int fileEnd = fileBegin + 1;
        while (fileEnd < list.size() && list.get(fileEnd) == ID) {
            ++fileEnd;
        }
        return new int[]{fileBegin, fileEnd};
    }

    private int[] findNewLocation(int fileBegin, int fileLength) {
        int newBegin = 0;
        int newEnd = -1;
        while (newBegin < fileBegin - fileLength) {
            while (newBegin < fileBegin - fileLength && list.get(newBegin) != -1) {
                ++newBegin;
            }
            newEnd = newBegin + 1;
            while (newEnd <= fileBegin && list.get(newEnd) == -1 && newEnd - newBegin < fileLength) {
                ++newEnd;
            }
            if (newEnd - newBegin == fileLength) {
                break;
            } else {
                newBegin = newEnd;
            }
        }
        return new int[]{newBegin, newEnd};
    }

    private void moveFile(int fileBegin, int fileLength, int newBegin) {
        for (int i = 0; i < fileLength; ++i) {
            int temp = list.get(newBegin + i);
            list.set(newBegin + i , list.get(fileBegin + i));
            list.set(fileBegin + i, temp);
        }
    }
	private long calculateCheckSum() {
		long result = 0;
		for(int i=0;i<list.size();i++) {
			if(list.get(i) == -1) continue;
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
		moveFile();
		long result = calculateCheckSum();
		
		return result;
	}
	
    public static void main( String[] args ) throws IOException
    {
        Day9Part2 solution = new Day9Part2();
        solution.run();
    }
    
    public void run() throws IOException {
    	List<String> lines = Resources.readLines(ClassLoader.getSystemResource(input), Charsets.UTF_8);
    	long result = getResult(lines);
    	System.out.println(result);
    }
}
