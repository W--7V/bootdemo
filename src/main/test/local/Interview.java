package local;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Interview {
	public static boolean check(int[][] oranges) {
		for (int[] row : oranges) {
			for (int col : row) {
				if (col == 1) {
					return false;
				}
			}
		}
		return true;
	}

	public static int orangesRotting(int[][] oranges) {
		if (check(oranges)) {
			// 没有新鲜的橘子
			return 0;
		}
		int count = 0, lastCount = 0, minuteCount = 0;

		int[][] mid = new int[oranges.length][oranges[0].length];
		copy(oranges, mid);

		while (!check(oranges)) {
			lastCount = count;
			for (int i = 0; i < oranges.length; i++) {
				for (int j = 0; j < oranges[i].length; j++) {
					if (oranges[i][j] == 2) {
						// 把周围的橘子腐烂
						count += rot(i - 1, j, mid);
						count += rot(i + 1, j, mid);
						count += rot(i, j - 1, mid);
						count += rot(i, j + 1, mid);
					}
				}
			}
			copy(mid, oranges);
			if (lastCount == count && !check(oranges)) {
				return -1;
			}
			minuteCount++;
		}
		return minuteCount;
	}

	public static void copy(int[][] src, int[][] tar) {
		for (int i = 0; i < src.length; i++) {
			for (int j = 0; j < tar.length; j++) {
				tar[i][j] = src[i][j];
			}
		}
	}

	public static int rot(int i, int j, int[][] oranges) {
		if (i < 0 || i > oranges.length - 1 || j < 0 || j > oranges[0].length - 1) {
			return 0;
		}
		if (oranges[i][j] == 1) {
			oranges[i][j] = 2;
			return 1;
		}
		return 0;
	}

	public static int commonParent(int root, int n1, int n2, int k) {
		if (n1 > n2) {
			int t = n1;
			n1 = n2;
			n2 = t;
		}
		if (n1 == root) {
			return n1;
		}
		if (n2 == root) {
			return n2;
		}
		if (n1 < root && n2 > root) {
			return root;
		}

		int nextRoot = 0;

		if (n1 > root) {
			nextRoot = root + (int) Math.pow(2, k - 2);
		} else {
			nextRoot = root - (int) Math.pow(2, k - 2);
		}
		return commonParent(nextRoot, n1, n2, k - 1);
	}

	public void maxArray() {
		TreeNode root = new TreeNode(1);
		TreeNode n1 = new TreeNode(2);
		TreeNode n2 = new TreeNode(2);
		TreeNode n3 = new TreeNode(3);
		TreeNode n4 = new TreeNode(4);
		TreeNode n5 = new TreeNode(4);
		TreeNode n6 = new TreeNode(3);
		root.left = n1;
		root.right = n2;
		n1.left = n3;
		n1.right = n4;
		n2.left = n5;
		n2.right = n6;

		int[] in = new int[] { -2, 1, -3, 4, -1, 2, 1, -5, 4 };
		int head = 0, tail = 0, res = 0, cur = 0;
		while (head <= tail && tail < in.length - 1) {
			if (in[tail] + cur > 0) {
				cur += in[tail];
				res = Math.max(res, cur);
				tail++;
			} else {
				cur = 0;
				head++;
				if (head > tail) {
					tail = head;
				}
			}
		}
		System.out.println(res);
	}

	public boolean sym(TreeNode root) {
		return isKidEqual(root.left, root.right);
	}

	public boolean isKidEqual(TreeNode left, TreeNode right) {
		if (left == null && right == null) {
			return true;
		}

		if (left.val != right.val) {
			return false;
		}

		return isKidEqual(left.left, right.right) && isKidEqual(left.right, right.left);
	}

	public void firsHigher() {
		int[] nums = new int[] { 2, 1, 3, 4, 1, 2, 1, 5, 4 };
		LinkedList<Integer> stack = new LinkedList<Integer>();
		int[] res = new int[nums.length];
		int head = 0;
		stack.push(head);
		for (int i = 1; i < nums.length; i++) {
			while (stack.size() > 0 && nums[i] > nums[stack.peek()]) {
				res[stack.poll()] = nums[i];
			}
			stack.push(i);
		}

		for (int i : res) {
			System.out.println(i);
		}
	}

	public boolean wordPattern(String pattern, String str) {
		// 空值校验

		Map<Character, String> pMap = new HashMap<Character, String>();
		String[] words = str.split(" ");
		if (words.length != pattern.length()) {
			return false;
		}

		for (int i = 0; i < words.length; i++) {
			String mapWord = pMap.get(pattern.charAt(i));
			if (mapWord == null) {
				pMap.put(pattern.charAt(i), words[i]);
			} else {
				if (!mapWord.equals(words[i])) {
					return false;
				}
			}
		}

		return true;
	}
}
