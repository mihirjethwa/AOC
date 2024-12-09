package com.mihir.AOC24;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

public class Day6Part2 {
	String input = "resource/2024/Day6.txt";
	String inputTest = "resource/2024/Day6Test.txt";
	Set<String> set = new HashSet<>();
	int[] top = new int[] {-1, 0};
	int[] right = new int[] {0, 1};
	int[] bottom = new int[] {1, 0};
	int[] left = new int[] {0, -1};
	
	
	//1 = top, 2 = right, 3 = bottom, 4 = left
	private int move2(int x, int y, int direction, int m, int n, char[][] matrix) {
		int dir = direction;
		int i = x;
		int j = y;
		int count = 0;
		while(true) {
//			System.out.println(" i: "+ i+" j :"+j+ " direction :" + dir);
//			for(int k=0;k<m;k++) {
//				for(int l=0;l<n;l++) {
//					System.out.print(matrix[k][l]);
//				}
//				System.out.println();
//			}
			//System.out.println("==========");
			
			if(i<0 || i>=m || j<0 || j>=n) {
				break;
			}
			
			if(matrix[i][j] == '#') {
				if(dir == 1) {
					i+=bottom[0];
					j+=bottom[1];
					matrix[i][j] = '+';
					i+=right[0];
					j+=right[1];
					dir = 2;
				}else if(dir == 2) {
					i+=left[0];
					j+=left[1];
					matrix[i][j] = '+';
					i+=bottom[0];
					j+=bottom[1];
					dir = 3;
				}else if(dir == 3) {
					i+=top[0];
					j+=top[1];
					matrix[i][j] = '+';
					i+=left[0];
					j+=left[1];
					dir = 4;
				}else if(dir == 4) {
					i+=right[0];
					j+=right[1];
					matrix[i][j] = '+';
					i+=top[0];
					j+=top[1];
					dir = 1;
				}
			}else {
				set.add(i+","+j);
				//matrix[i][j] = 'X';
				if(dir == 1) {
					int ci = i;
					int cj = j;
					while(cj < n) {
						if(matrix[ci][cj] == '+' ) {
							count++;
							break;
						}
						cj++;
					}
					i+=top[0];
					j+=top[1];
				}
				if(dir == 2) {
					int ci = i;
					int cj = j;
					while(ci < m) {
						if(matrix[ci][cj] == '+' ) {
							count++;
							break;
						}
						ci++;
					}
					i+=right[0];
					j+=right[1];
				}
				if(dir == 3) {
					int ci = i;
					int cj = j;
					while(cj >= 0) {
						if(matrix[ci][cj] == '+') {
							
							count++;
							break;
						}
						cj--;
					}
					i+=bottom[0];
					j+=bottom[1];
				}
				if(dir == 4) {
					int ci = i;
					int cj = j;
					while(ci >= 0) {
						if(matrix[ci][cj] == '+') {
							
							count++;
							break;
						}
						ci--;
					}
					i+=left[0];
					j+=left[1];
				}
			}
		}
		return count;
	}

	private int move3(int x, int y, int direction, int m, int n, char[][] matrix) {
		int dir = direction;
		int i = x;
		int j = y;
		int count = 0;
		while(true) {
			matrix[i][j] = 'X';
			set.add(i+","+j+","+dir);
			
			int oldi = i;
			int oldj = j;
			
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
			
			if(i<0 || i>=m || j<0 || j>=n) {
				break;
			}
			
			if(matrix[i][j] == '#') {
				if(dir == 1) {
					i+=bottom[0];
					j+=bottom[1];
					dir = 2;
				}else if(dir == 2) {
					i+=left[0];
					j+=left[1];
					dir = 3;
				}else if(dir == 3) {
					i+=top[0];
					j+=top[1];
					dir = 4;
				}else if(dir == 4) {
					i+=right[0];
					j+=right[1];
					dir = 1;
				}
			}else {
				if (matrix[i][j] == '.') {
                    char[][] tmpMatrix = new char[m][n];
                    for(int l=0;l<m;l++) {
                    	for( int o = 0;o<n;o++) {
                    		tmpMatrix[l][o] = matrix[l][o];
                    	}
                    }
                    tmpMatrix[i][j] = 'O';
                    if (checkLoop(tmpMatrix, new HashSet<>(set), oldi, oldj, dir)) {
                        count++;
                    }
                }
			}
		}
		return count;
	}
	
	private boolean checkLoop(char[][] tmpMatrix, Set<String> visited, int i, int j, int dir) {
		boolean flag = true;
        while (true) {
            tmpMatrix[i][j] = 'X';
            if(flag)visited.add(i + "," + j + "," + dir);
            flag = true;
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

            if (i < 0 || i >= tmpMatrix.length || j < 0 || j >= tmpMatrix[0].length) {
                break;
            }
            if (tmpMatrix[i][j] == '#' || tmpMatrix[i][j] == 'O') {
            	if(dir == 1) {
					i+=bottom[0];
					j+=bottom[1];
					dir = 2;
				}else if(dir == 2) {
					i+=left[0];
					j+=left[1];
					dir = 3;
				}else if(dir == 3) {
					i+=top[0];
					j+=top[1];
					dir = 4;
				}else if(dir == 4) {
					i+=right[0];
					j+=right[1];
					dir = 1;
				}
            	flag = false;
            }
            else {
                if (visited.contains(i + "," + j + "," + dir)) {
                    return true;
                }
            }
        }
        return false;
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

		int result = move3(posX, posY, 1, m, n, matrix);
//		for(int i=0;i<m;i++) {
//			for(int j=0;j<n;j++) {
//				System.out.print(matrix[i][j]);
//			}
//			System.out.println();
//		}
		System.out.println(set);
		return result;
	}
	
    public static void main( String[] args ) throws IOException
    {
        Day6Part2 solution = new Day6Part2();
        solution.run();
    }
    
    public void run() throws IOException {
    	List<String> lines = Resources.readLines(ClassLoader.getSystemResource(input), Charsets.UTF_8);
    	int result = getResult(lines);
    	System.out.println(result);
    }
}
