package local;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Main {
	static LinkedList<Long>ll = new LinkedList<Long>();
	static LinkedList<Long>temp = new LinkedList<Long>();
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Main m = new Main();
		
		while(in.hasNextLine()) {
			ll.clear();
			temp.clear();
			String[] mn = in.nextLine().split(" ");
			for (String s : mn) {
				calc(Long.valueOf(s));
			}
			while(ll.size()>0) {
				System.out.print(ll.removeLast()+ " ");
			}
			System.out.println();
		}
	}
	
	private static void calc(Long i) {
		Long t=i;
		while(t>0 && ll.size()>0) {
			Long removeLast = ll.removeLast();
			temp.add(removeLast);
			t-=removeLast;
		}
		if(t==0) {
			temp.clear();
			calc(i*2);
		}else {
			while(temp.size()>0) {
				ll.add(temp.removeLast());
			}
			ll.add(i);
		}
	}
	
	public String findMaxSubString(String s) {
		int h = 0, t = 0, max = 0, cur = 0;

		Set<Character> set = new HashSet<Character>();
		int i = 0, j = 0;
		// 遍历字符串
		while (i <= j && j < s.length()) {
			if (!set.contains(s.charAt(j))) {
				// 新字符不是重复字符，尾部向后移
				cur++;
				set.add(s.charAt(j));
				if (cur > max) {
					max = cur;
					h = i;
					t = j;
				}
				j++;
			} else {
				// 新字符重复，头部向后移
				set.remove(s.charAt(i));
				i++;
				cur--;
			}
		}
		// 最后返回 在h-t之间子串为结果
		StringBuilder sbBuilder = new StringBuilder();
		for (int k = h; k <= t; k++) {
			sbBuilder.append(s.charAt(k));
		}
		return sbBuilder.toString();
	}

	List<int[]> position = new ArrayList<>();

	public char[][] wrap(char[][] in) {
		int row = in.length;
		int col = in[0].length;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				findO(in, i, j);
			}
		}
		
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				in[i][j]='X';
			}
		}
		for (int[] ij : position) {
			in[ij[0]][ij[1]]='O';
		}

		return in;
	}

	public void findO(char[][] in, int i, int j) {
		if ((in[i][j] == 'O') && (i == 0 || i == in.length || j == 0 || j == in[0].length)) {
			dfs(in, i, j);
		}
	}

	public void dfs(char[][] in, int i, int j) {
		if ((i >= 0 && i < in.length && j >= 0 && j < in[0].length) && in[i][j] == 'O') {
			position.add(new int[] { i, j });
			in[i][j]='o';
			dfs(in, i - 1, j);
			dfs(in, i + 1, j);
			dfs(in, i, j - 1);
			dfs(in, i, j + 1);
		}
	}
}
