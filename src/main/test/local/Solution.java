package local;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
//		System.out.println(s.isInterleave("a", "b", "a"));
//		System.out.println(s.trap(new int[] {2,0,2}));
//		System.out.println(s.canFinish(3, new int[][] { { 1, 0 }, { 2, 1 } }));
//		System.out.println(s.maximalSquare(new char[][] {{'1','0','1','0','0'},{'1','0','1','1','1'},{'1','1','1','1','1'},{'1','0','0','1','0'}}));
//		System.out.println(s.maximalSquare(new char[][] { { '0', '0' }, { '0', '0' } }));
//		System.out.println(numSquares(12));
//		System.out.println(findDuplicate(new int [] {2,5,9,6,9,3,8,9,7,1}));
//		System.out.println(lengthOfLIS(new int[] {10,9,2,5,3,4}));
//		System.out.println(coinChange(new int[] {1,2,5},23));
//		System.out.println(s.rob(con()));
//		System.out.println(s.decodeString("100[leetcode]"));
//		System.out.println(s.canPartition(new int[] {1,5,5,11}));
//		System.out.println(
//				s.reconstructQueue(new int[][] { { 7, 0 }, { 4, 4 }, { 7, 1 }, { 5, 0 }, { 6, 1 }, { 5, 2 } }));
//		System.out.println(s.findMedianSortedArrays(new int[] { -2, -1 }, new int[] { 3 }));
//		List<Integer> findAnagrams = s.findAnagrams("aaaaaaaaaa", "aaaaaaaaaaaaa");
//		for (Integer integer : findAnagrams) {
//			System.out.println(integer);
//		}
//        System.out.println(s.findTargetSumWays(new int[]{1, 1, 1, 1, 1}, 3));
//        s.rotateTheBox(new char[][]{{'#', '.', '.', '#'}});
        s.pathSum(s.Tree113(), 22);
    }

    static List<List<Integer>> res;
    static List<Integer> current;

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        res = new ArrayList<>();
        current = new ArrayList<>();
        dfs(root, targetSum);
        return res;
    }

    private void dfs(TreeNode node, int targetSum) {
        int sum = current.stream().reduce(0, Integer::sum);
        if (node == null) {
            return;
        }

        current.add(node.val);
        if (sum + node.val == targetSum && node.left == null && node.right == null) {
            res.add(new ArrayList<>(current));
            current.remove(current.size() - 1);
            return;
        }
        dfs(node.left, targetSum);
        dfs(node.right, targetSum);
        current.remove(current.size() - 1);
    }

    public TreeNode Tree113() {
        TreeNode r = new TreeNode(5);
        TreeNode n1 = new TreeNode(4);
        TreeNode n2 = new TreeNode(8);
        TreeNode n3 = new TreeNode(11);
        TreeNode n4 = new TreeNode(13);
        TreeNode n5 = new TreeNode(4);
        TreeNode n6 = new TreeNode(7);
        TreeNode n7 = new TreeNode(2);
        TreeNode n8 = new TreeNode(5);
        TreeNode n9 = new TreeNode(1);
        r.left = n1;
        r.right = n2;
        n1.left = n3;
        n2.left = n4;
        n2.right = n5;
        n3.left = n6;
        n3.right = n7;
        n5.left = n8;
        n5.right = n9;

        return r;
    }

    public char[][] rotateTheBox(char[][] box) {

        char[][] rotated = new char[box[0].length][box.length];
        if (box[0].length > 1) {
            for (char[] row : box) {
                boolean isMoved = true;
                while (isMoved) {
                    isMoved = false;
                    for (int i = row.length - 2; i >= 0; i--) {
                        if (row[i] == '#' && row[i + 1] == '.') {
                            row[i] = '.';
                            row[i + 1] = '#';
                            isMoved = true;
                        }
                    }
                }
            }
        }

        for (int i = 0; i < box.length; i++) {
            for (int j = 0; j < box[i].length; j++) {
                rotated[j][box.length - i - 1] = box[i][j];
            }
        }
        return rotated;
    }

    public static TreeNode con() {
        TreeNode root = new TreeNode(4);
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(5);
        TreeNode n3 = new TreeNode(2);
        TreeNode n4 = new TreeNode(1);
        TreeNode n5 = new TreeNode(3);
        TreeNode n6 = new TreeNode(1);

        root.left = n1;
//		root.right = n2;
        n1.left = n3;
//		n1.right = n4;
        n3.left = n5;
        return root;
    }

    public int majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i : nums) {
            Integer n = map.get(i);
            if (map.get(i) == null) {
                map.put(i, 1);
            } else {
                map.put(i, 1 + n);
                if (n + 1 > nums.length / 2) {
                    return i;
                }
            }
        }

        if (nums.length < 2) {
            return nums[0];
        }

        return 0;
    }

    public int maxProfit(int[] prices) {
        int profit = 0;
        for (int head = 0, tail = 1; tail < prices.length; ) {
            if (prices[head] < prices[tail]) {
                profit += prices[tail] - prices[head];
            }
            head = tail;
            tail++;
        }
        return profit;
    }

    public int[] searchRange(int[] nums, int target) {
        if (nums.length == 0) {
            return new int[]{-1, -1};
        }

        int low = 0, high = nums.length - 1;
        int mid = -1;

        int[] r = new int[2];
        while (low < high) {
            mid = (low + high) / 2;
            if (nums[mid] == target) {
                high = mid;
            } else if (nums[mid] > target) {
                high = mid;
            } else {
                if (low == mid) {
                    low = high;
                    break;
                }
                low = mid;
            }
        }
        r[0] = low;
        if (nums[r[0]] != target)
            r[0] = -1;

        high = nums.length - 1;

        while (low < high) {
            mid = (low + high) / 2;
            if (nums[mid] == target) {
                if (low == mid) {
                    if (nums[high] == target) {
                        low = high;
                    } else {
                        high = low;
                    }
                    break;
                }
                low = mid;
            } else if (nums[mid] < target) {
                low = mid;
            } else {
                high = mid;
            }
        }
        r[1] = high;
        if (nums[r[1]] != target)
            r[1] = -1;

        return r;
    }

    public String longestPalindrome(String s) {
        String maxString = "";

        char[] charArray = s.toCharArray();
        StringBuilder current = new StringBuilder();

        for (int i = 0, left, right; i < charArray.length; i++) {
            left = i - 1;
            right = i + 1;
            current.setLength(0);
            current.append(charArray[i]);
            if (current.length() > maxString.length()) {
                maxString = current.toString();
            }
            while (left >= 0 && right <= charArray.length - 1) {
                if (charArray[left] == charArray[right]) {
                    current.append(charArray[left]);
                    current.insert(0, charArray[left]);
                    if (current.length() > maxString.length()) {
                        maxString = current.toString();
                    }
                    left--;
                    right++;
                } else {
                    break;
                }
            }

            left = i;
            right = i + 1;
            current.setLength(0);
            while (left >= 0 && right <= charArray.length - 1) {
                if (charArray[left] == charArray[right]) {
                    current.append(charArray[left]);
                    current.insert(0, charArray[left]);
                    if (current.length() > maxString.length()) {
                        maxString = current.toString();
                    }
                    left--;
                    right++;
                } else {
                    break;
                }
            }
        }

        return maxString;
    }

    public int maxArea(int[] height) {
        int area = 0, currentH = 0, currentArea = 0;
        for (int head = 0, tail = height.length - 1; head < tail; ) {
            currentH = Math.min(height[head], height[tail]);
            currentArea = (tail - head) * currentH;
            if (currentArea > area) {
                area = currentArea;
            }

            if (height[head] < height[tail]) {
                head++;
            } else {
                tail--;
            }
        }
        return area;
    }

    public boolean isMatch(String s, String p) {
        return true;
    }

    public ListNode mergeKLists(ListNode[] lists) {
        ListNode r = new ListNode(0);
        ListNode h = r;
        if (lists.length == 0) {
            return null;
        }
        PriorityQueue<ListNode> pq = new PriorityQueue<ListNode>(lists.length, new Comparator<ListNode>() {

            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val - o2.val;
            }

        });

        for (ListNode listNode : lists) {
            if (listNode != null) {
                pq.add(listNode);
            }
        }

        while (pq.size() > 0) {
            r.next = pq.poll();
            r = r.next;
            if (r.next != null) {
                pq.add(r.next);
            }
        }

        return h.next;
    }

    public int longestValidParentheses(String s) {
        int maxans = 0;
        LinkedList<Integer> stack = new LinkedList<>();
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    maxans = Math.max(maxans, i - stack.peek());
                }
            }
        }
        return maxans;
    }

//    List<List<Integer>> res = new ArrayList<List<Integer>>();

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<Integer> numsCollection = new ArrayList<>();
        Arrays.sort(nums);
        Arrays.stream(nums).forEach((i) -> {
            numsCollection.add(i);
        });
        LinkedList<Integer> temp = new LinkedList<Integer>();
        back(numsCollection, temp);
        return res;
    }

    public void back(List<Integer> numsCollection, LinkedList<Integer> temp) {
        List<Integer> tempSet = new LinkedList<Integer>(numsCollection);
        Set<Integer> filter = new HashSet<Integer>();
        if (numsCollection.size() == 1) {
            temp.add(numsCollection.iterator().next());
            res.add(new ArrayList<Integer>(temp));
            temp.removeLast();
            return;
        }
        for (Integer integer : numsCollection) {
            if (filter.contains(integer)) {
                continue;
            }
            filter.add(integer);
            temp.add(integer);
            tempSet.remove(integer);
            back(tempSet, temp);
            tempSet.add(integer);
            temp.removeLast();
        }
    }

    public int getWater(int n) {
        int wn = 0;
        while (n >= 3) {
            wn += n / 3;
            n = n / 3 + n % 3;
        }
        if (n == 2) {
            wn++;
        }
        return wn;
    }

    String s1, s2, s3;
    Boolean num97[][];

    public boolean isInterleave(String s1, String s2, String s3) {
        this.s1 = s1;
        this.s2 = s2;
        this.s3 = s3;
        int k = 0, j = 0;
        this.num97 = new Boolean[s1.length()][s2.length()];

        return isInterleaveSub(j, k);
    }

    public boolean isInterleaveSub(int j, int k) {
        if (j < s1.length() && k < s2.length() && this.num97[j][k] != null) {
            return this.num97[j][k];
        }
        if (j == s1.length() && k == s2.length() && j + k == s3.length()) {
            return true;
        }
        boolean b1 = false, b2 = false;
        if (j < s1.length() && j + k < s3.length() && s1.charAt(j) == s3.charAt(j + k)) {
            b1 = isInterleaveSub(j + 1, k);
        }

        if (k < s2.length() && j + k < s3.length() && s2.charAt(k) == s3.charAt(j + k)) {
            b2 = isInterleaveSub(j, k + 1);
        }
        if (j < s1.length() && k < s2.length()) {
            this.num97[j][k] = b1 || b2;
        }
        return b1 || b2;
    }

    public int numIslands(char[][] grid) {
        int[][] maps = new int[grid.length][grid[0].length];
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1' && maps[i][j] == 0) {
                    res++;
                    isIsland(i, j, maps, grid, res);
                }
            }
        }
        return res;
    }

    public static void isIsland(int i, int j, int[][] maps, char[][] nums, int res) {
        if (i < 0 || j < 0 || i >= maps.length || j >= maps[0].length) {
            return;
        }
        if (nums[i][j] == '1' && maps[i][j] == 0) {
            maps[i][j] = res;
            isIsland(i + 1, j, maps, nums, res);
            isIsland(i - 1, j, maps, nums, res);
            isIsland(i, j + 1, maps, nums, res);
            isIsland(i, j - 1, maps, nums, res);
        }
    }

    public int trap(int[] height) {
        if (height.length < 3) {
            return 0;
        }
        int res = 0, max = 0, maxIndex = 0;
        int border = -1, borderHeight = 0;
        for (int i = 0; i < height.length; i++) {
            if (height[i] > max) {
                maxIndex = i;
                max = height[i];
            }
        }
        border = 0;
        borderHeight = height[0];
        for (int i = 1; i <= maxIndex; i++) {
            if (height[i] >= borderHeight) {
                while (border < i - 1) {
                    res += borderHeight - height[border + 1];
                    border++;
                }
                border = i;
                borderHeight = height[border];
            }
        }

        border = height.length - 1;
        borderHeight = height[height.length - 1];
        for (int i = height.length - 2; i >= maxIndex; i--) {
            if (height[i] >= borderHeight) {
                while (border > i + 1) {
                    res += borderHeight - height[border - 1];
                    border--;
                }
                border = i;
                borderHeight = height[border];
            }
        }
        return res;
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] indegrees = new int[numCourses];
        int count = 0;
        LinkedList<Integer> queue = new LinkedList<Integer>();
        for (int[] line : prerequisites) {
            indegrees[line[0]]++;
        }

        for (int i = 0; i < indegrees.length; i++) {
            if (indegrees[i] == 0) {
                queue.add(i);
            }
        }
        while (!queue.isEmpty()) {
            Integer removeFirst = queue.removeFirst();
            count++;
            for (int i = 0; i < prerequisites.length; i++) {
                if (prerequisites[i][1] == removeFirst) {
                    indegrees[prerequisites[i][0]]--;
                    if (indegrees[prerequisites[i][0]] == 0) {
                        queue.add(prerequisites[i][0]);
                    }
                }
            }
        }
        return count == numCourses;
    }

    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix[0] == null) {
            return 0;
        }
        int row = matrix.length, col = matrix[0].length;
        int max = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                max = Math.max(max(matrix, i, j), max);
            }
        }
        return max;
    }

    private int max(char[][] matrix, int x, int y) {
        int border = 0;
        boolean isSquare = true;
        for (int i = 0; i + x < matrix.length && i + y < matrix[0].length; i++) {
            for (int col = 0; col < border + 1; col++) {
                if (matrix[x + i][y + col] == '0') {
                    isSquare = false;
                    break;
                }
            }
            if (isSquare == false) {
                break;
            }
            for (int row = 0; row < border + 1; row++) {
                if (matrix[x + row][y + i] == '0') {
                    isSquare = false;
                    break;
                }
            }
            if (isSquare == false) {
                break;
            }
            border++;
        }
        return border * border;
    }

    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        if (root.left != null || root.right != null) {
            TreeNode temp;
            temp = root.left;
            root.left = root.right;
            root.right = temp;
        }
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }

    public int[] productExceptSelf(int[] nums) {
        int[] res = new int[nums.length];
        int k = 1;
        for (int i = 0; i < nums.length; i++) {
            res[i] = k;
            k = k * nums[i];
        }
        k = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            res[i] = res[i] * k;
            k = k * nums[i];
        }
        return res;
    }

    static Map<Integer, Integer> temp = new HashMap<Integer, Integer>();

    public static int numSquares(int n) {
        Integer t = temp.get(n);
        if (t != null) {
            return t;
        }
        if (n == 0) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        for (int i = 1; n >= i * i; i++) {
            min = Math.min(min, numSquares(n - i * i));
        }
        temp.put(n, 1 + min);
        return 1 + min;
    }

    public static int findDuplicate(int[] nums) {
        int walker = nums[0], runner = nums[nums[0]];
        while (walker != runner) {
            walker = nums[walker];
            runner = nums[nums[runner]];
        }

        int h = 0, m = walker;
        while (h != walker) {
            walker = nums[walker];
            h = nums[h];
        }

        return h;
    }

    public static int lengthOfLIS(int[] nums) {
        List<Integer> dp = new ArrayList<Integer>();
        int mid;
        dp.add(nums[0]);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > dp.get(dp.size() - 1)) {
                dp.add(nums[i]);
            } else {
                int h = 0, t = dp.size();
                while (h < t) {
                    mid = (h + t) / 2;
                    if (nums[i] > dp.get(mid)) {
                        if (mid == h) {
                            h++;
                        } else {
                            h = mid;
                        }
                    } else {
                        t = mid;
                    }
                }
                dp.set(h, nums[i]);
            }
        }
        return dp.size();
    }

    public static int coinChange(int[] coins, int amount) {
        if (temp.get(amount) != null) {
            return temp.get(amount);
        }
        int r = Integer.MAX_VALUE, tempInt = Integer.MAX_VALUE;
        boolean no = true;
        for (int i : coins) {
            if (amount > i) {
                r = coinChange(coins, amount - i);
                if (r > 0) {
                    tempInt = Math.min(r, tempInt);
                    no = false;
                }
            } else if (amount == i) {
                return 1;
            }
        }
        if (no) {
            return -1;
        }
        temp.put(amount, tempInt + 1);
        return r + 1;
    }

    public int rob(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int val1 = root.val, val2 = 0;
        if (root.left != null) {
            val1 += rob(root.left.left);
            val1 += rob(root.left.right);
            val2 += rob(root.left);
        }
        if (root.right != null) {
            val1 += rob(root.right.left);
            val1 += rob(root.right.right);
            val2 += rob(root.right);
        }
        return Math.max(val1, val2);
    }

    public int[] countBits(int num) {
        int[] res = new int[num + 1];
        for (int i = 1; i <= num; i++) {
            res[i] = res[i >> 1] + (i & 1);
        }

        return res;
    }

    public String decodeString(String s) {
        LinkedList<Character> stack = new LinkedList<>();
        StringBuilder sBuilder = new StringBuilder();
        StringBuilder numBuilder = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ']') {
                sBuilder.setLength(0);
                char last = stack.getLast();
                while (last != '[') {
                    stack.removeLast();
                    sBuilder.insert(0, last);
                    last = stack.getLast();
                }
                stack.removeLast();

                last = stack.getLast();
                numBuilder.setLength(0);
                while (last >= '0' && last <= '9') {
                    stack.removeLast();
                    numBuilder.insert(0, last);
                    if (stack.isEmpty()) {
                        break;
                    }
                    last = stack.getLast();
                }

                int n = Integer.valueOf(numBuilder.toString());
                String sub = sBuilder.toString();
                for (int r = 0; r < n; r++) {
                    for (int j = 0; j < sub.length(); j++) {
                        stack.add(sub.charAt(j));
                    }
                }

            } else {
                stack.add(c);
            }
        }
        sBuilder.setLength(0);

        for (Character character : stack) {
            sBuilder.append(character);
        }

        return sBuilder.toString();
    }

    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        if ((sum & 1) == 1) {
            return false;
        }
        int half = sum / 2;

        boolean[][] dp = new boolean[nums.length][half + 1];

        if (nums[0] < half) {
            dp[0][nums[0]] = true;
        }

        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                dp[i][j] = dp[i - 1][j];
                if (nums[i] == j) {
                    dp[i][j] = true;
                } else if (nums[i] < j) {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i]];
                }
            }
        }
        return dp[nums.length - 1][half];
    }

    public int[][] reconstructQueue(int[][] people) {
        int[][] res = new int[people.length][2];
        List<int[]> temp = new ArrayList<int[]>();
        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) {
                    return o1[1] - o2[1];
                } else {
                    return o2[0] - o1[0];
                }
            }
        });

        for (int i = 0; i < people.length; i++) {
            temp.add(people[i][1], people[i]);
        }
        temp.toArray(res);
        return res;
    }

    public int findRepeat(int[] nums) {
        int walker = nums[0], runner = nums[nums[0]];
        // ???????????????
        while (walker != runner) {
            walker = nums[walker];
            runner = nums[nums[walker]];
        }

        // ???????????????????????????????????????
        int h = 0, m = walker;
        while (h != walker) {
            walker = nums[walker];
            h = nums[h];
        }

        return h;
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            int[] t = nums2;
            nums2 = nums1;
            nums1 = t;
        }
        if (nums1.length == 0) {
            if ((nums2.length & 1) == 1) {
                return nums2[nums2.length >> 1];
            } else {
                return (nums2[nums2.length >> 1] + nums2[(nums2.length >> 1) - 1]) / 2.0;
            }
        }
        int head = 0, tail = nums1.length - 1, mid1 = 0, mid2, total = nums1.length + nums2.length,
                totalMid = total / 2;
        while (head <= tail) {
            if ((head + tail) / 2 == mid1) {
                if (mid1 < tail) {
                    mid1++;
                }
            } else {
                mid1 = (head + tail) / 2;
            }
            mid2 = totalMid - mid1 - 1;
            if (mid2 < nums2.length - 1 && nums1[mid1] > nums2[mid2 + 1]) {
                if (tail == 0) {
                    if ((total & 1) == 1) {
                        return nums2[totalMid];
                    } else {
                        return (nums2[totalMid] + nums2[totalMid - 1]) / 2.0;
                    }
                }
                tail = mid1;
            } else if (mid1 < nums1.length - 1 && nums2[mid2] > nums1[mid1 + 1]) {
                head = mid1;
            } else {
                if ((total & 1) == 1) {
                    return Math.max(nums1[mid1], nums2[mid2]);
                } else {
                    int m1 = nums1[mid1], m2 = nums2[mid2];
                    if (mid2 > 0) {
                        m1 = Math.max(nums1[mid1], nums2[mid2 - 1]);
                    }
                    if (mid1 > 0) {
                        m2 = Math.max(nums2[mid2], nums1[mid1 - 1]);
                    }
                    return (m1 + m2) / 2.0;
                }
            }
        }
        return 0;
    }

    public List<Integer> findAnagrams(String s, String p) {
        int[] dic = new int[26];
        int[] cur = new int[26];
        List<Integer> res = new ArrayList<Integer>();
        if (p.length() > s.length()) {
            return res;
        }
        for (int i = 0; i < p.length(); i++) {
            dic[p.charAt(i) - 'a']++;
        }
        for (int i = 0; i < p.length() - 1; i++) {
            cur[s.charAt(i) - 'a']++;
        }
        for (int i = p.length() - 1; i < s.length(); i++) {
            cur[s.charAt(i) - 'a']++;
            boolean f = true;
            for (int l = 0; l < cur.length; l++) {
                if (dic[l] != cur[l]) {
                    f = false;
                    break;
                }
            }
            if (f) {
                res.add(i - p.length() + 1);
            }
            cur[s.charAt(i - p.length() + 1) - 'a']--;
        }
        return res;
    }

    public int findTargetSumWays(int[] nums, int S) {
        int[][] dp = new int[nums.length][2001];
        dp[0][1000 + nums[0]] += 1;
        dp[0][1000 - nums[0]] += 1;
        int l, r;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < 2001; j++) {
                l = j + nums[i] >= 2001 ? 0 : j + nums[i];
                r = j - nums[i] < 0 ? 0 : j - nums[i];
                dp[i][j] = dp[i - 1][l] + dp[i - 1][r];
            }
        }
        return dp[nums.length - 1][S + 1000];
    }

}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}