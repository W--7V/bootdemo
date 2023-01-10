package local;

import java.util.*;

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
//        s.pathSum(s.Tree113(), 22);
//        s.champagneTower(2,1,1);
//        s.customSortString("cba", "abcd");
//        System.out.println(s.orderOfLargestPlusSign(5, new int[][]{{4, 2}}));

//        System.out.println(s.zigzagLevelOrder(Util.convertArrayToTree(new Integer[]{
//                0, 2, 4, 1, null, 3, -1, 5, 1, null, 6, null, 8})));

//        s.levelOrderBottom(Util.convertArrayToTree(new Integer[]{3, 9, 20, null, null, 15, 7}));

//        s.kSmallestPairs(new int[]{1, 7, 11}, new int[]{2, 4, 6}, 3);
//        int res = s.maxAreaOfIsland(new int[][]{{0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
//                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
//                {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
//                {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
//                {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
//                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
//                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
//                {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}});
//        System.out.println(res);
//        System.out.println(s.findCircleNum(new int[][]{{1, 1, 0}, {1, 1, 0}, {0, 0, 1}}));
//        List<Integer> integers1 = Arrays.asList(-10);
//        List<Integer> integers2 = Arrays.asList(3, 4);
//        List<Integer> integers3 = Arrays.asList(6, 5, 7);
//        List<Integer> integers4 = Arrays.asList(4, 1, 8, 3);
//        List<List<Integer>> list = new ArrayList<>();
//        list.add(integers1);
////        list.add(integers2);
////        list.add(integers3);
////        list.add(integers4);
//        System.out.println(s.minimumTotal(list));

//        System.out.println(s.nthSuperUglyNumber(12, new int[]{2, 7, 13, 19}));
//        s.topKFrequent(new String[]{"the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"}, 2);
//        System.out.println(s.numDecodings("12"));
//        System.out.println(s.searchMatrix(new int[][]{{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}}, 16));
//        System.out.println(s.combine(4, 2));
//        System.out.println(s.subsetsWithDup(new int[]{1, 2, 2}));
//        s.recoverTree(Util.convertArrayToTree(new Integer[]{3, 1, 4, null, null, 2}));
//        s.recoverTree(Util.convertArrayToTree(new Integer[]{1, 3, null, null, 2}));
//        System.out.println(s.isSameTree(Util.convertArrayToTree(new Integer[]{10, 5, 15}), Util.convertArrayToTree(new Integer[]{10, 5, null, null, 15})));
        System.out.println(s.countNodes(Util.convertArrayToTree(new Integer[]{1, 2, 3, 4, 5, 6})));
    }

    public int countNodes(TreeNode root) {
        int minHeight = getRightHeight(root);
        int base = (1 << minHeight) - 1;
        return base + getLastLevel(root, minHeight, 1);
    }

    public int getLastLevel(TreeNode node, int minHeight, int level) {
        if (node == null) {
            return 0;
        }
        int left = getRightHeight(node.left) + level;
        if (left > minHeight) {
            return (1 << (minHeight - level)) + getLastLevel(node.right, minHeight, level + 1);
        } else {
            return getLastLevel(node.left, minHeight, level + 1);
        }
    }

    public int getRightHeight(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return getRightHeight(node.right) + 1;
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null)
            return true;

        if (p == null || q == null) {
            return false;
        }
        if (q.val != p.val) {
            return false;
        }

        boolean leftRes = isSameTree(p.left, q.left);
        if (!leftRes)
            return false;
        boolean rightRes = isSameTree(p.right, q.right);
        return rightRes;
    }

    public void recoverTree(TreeNode root) {
        List<TreeNode> nodeList = new ArrayList<>();
        inOrder(root, nodeList);
        TreeNode node1 = null, node2 = null;
        for (int i = 0; i < nodeList.size() - 1; i++) {
            if (nodeList.get(i).val > nodeList.get(i + 1).val) {
                node2 = nodeList.get(i + 1);
                if (node1 == null) {
                    node1 = nodeList.get(i);
                } else {
                    break;
                }
            }
        }

        int temp = node1.val;
        node1.val = node2.val;
        node2.val = temp;
    }

    public void inOrder(TreeNode node, List<TreeNode> nodeList) {
        if (node == null) {
            return;
        }
        inOrder(node.left, nodeList);
        nodeList.add(node);
        inOrder(node.right, nodeList);
    }

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        LinkedList<Integer> path = new LinkedList<>();
        List<List<Integer>> res = new ArrayList<>();
        Set<String> set = new HashSet<>();
        res.add(new ArrayList<>());
        dfs(0, nums, path, res, set);
        return res;
    }

    public void dfs(int i, int[] nums, LinkedList<Integer> path, List<List<Integer>> res, Set<String> set) {
        if (i == nums.length) {
            return;
        }

        path.addLast(nums[i]);
        List cur = new ArrayList<>(path);
        StringBuilder stringBuilder = new StringBuilder();
        cur.stream().sorted().forEach(n -> stringBuilder.append(n + ","));
        if (!set.contains(stringBuilder.toString())) {
            res.add(cur);
            set.add(stringBuilder.toString());
        }
        dfs(i + 1, nums, path, res, set);
        path.removeLast();

        dfs(i + 1, nums, path, res, set);
    }

    public List<List<Integer>> combine(int n, int k) {
        LinkedList<Integer> path = new LinkedList<>();
        List<List<Integer>> res = new ArrayList<>(k);
        combineSub(n, k, path, res);

        return res;
    }

    public void combineSub(int n, int k, LinkedList<Integer> path, List<List<Integer>> res) {
        if (k == 0) {
            res.add(new ArrayList<>(path));
            return;
        }

        if (n == 0) {
            return;
        }

        path.addLast(n);
        combineSub(n - 1, k - 1, path, res);
        path.removeLast();

        combineSub(n - 1, k, path, res);

    }

    public boolean searchMatrix(int[][] matrix, int target) {
        int row = binarySearchRow(matrix, target);
//        for (; row < matrix.length - 1; row++) {
//            if (matrix[row + 1][0] > target) {
//                break;
//            }
//        }

        int col = 0;
        for (; col < matrix[row].length; col++) {
            if (matrix[row][col] == target) {
                return true;
            }
        }

        return false;
    }

    public int binarySearchRow(int[][] matrix, int target) {
        int head = 0, tail = matrix.length - 1;
        int mid;
        while (head < tail) {
            mid = head + (tail - head) / 2;
            if (matrix[mid][0] == target) {
                return mid;
            }
            if (matrix[mid][0] < target) {
                head = mid + 1;
            } else {
                tail = mid - 1;
            }
        }
        return head;
    }

    public int numDecodings(String s) {
        if (s.charAt(0) == '0' || s.contains("00")) {
            return 0;
        }
        int[] dp = new int[s.length() + 1];
        dp[s.length() - 1] = 1;
        dp[s.length()] = 1;

        for (int i = s.length() - 2; i >= 0; i--) {
            Integer sn = Integer.valueOf("" + s.charAt(i) + s.charAt(i + 1));
            if (sn < 10 || sn.equals(10) || sn.equals(20)) {
                dp[i] = dp[i + 1];
            } else if (sn <= 26) {
                dp[i] = dp[i + 1];
                if (i + 2 >= s.length()) {
                    dp[i] += dp[i + 2];
                } else if (s.charAt(i + 2) != '0') {
                    dp[i] += dp[i + 2];
                }
            } else {
                if (sn % 10 == 0) {
                    return 0;
                }
                dp[i] = dp[i + 1];
            }
        }

        return dp[0];
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
    Boolean nnnnum97[][];

    public boolean isInterleave(String s1, String s2, String s3) {
        this.s1 = s1;
        this.s2 = s2;
        this.s3 = s3;
        int k = 0, j = 0;
        this.nnnnum97 = new Boolean[s1.length()][s2.length()];

        return isInterleaveSub(j, k);
    }

    public boolean isInterleaveSub(int j, int k) {
        if (j < s1.length() && k < s2.length() && this.nnnnum97[j][k] != null) {
            return this.nnnnum97[j][k];
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
            this.nnnnum97[j][k] = b1 || b2;
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
        // 寻找相遇点
        while (walker != runner) {
            walker = nums[walker];
            runner = nums[nums[walker]];
        }

        // 寻找入口，入口处即为重复数
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

    public int lenLongestFibSubseq(int[] arr) {
        Map<Integer, Integer> indices = new HashMap<Integer, Integer>();
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            indices.put(arr[i], i);
        }
        int[][] dp = new int[n][n];
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i - 1; j >= 0 && arr[j] * 2 > arr[i]; j--) {
                int k = indices.getOrDefault(arr[i] - arr[j], -1);
                if (k >= 0) {
                    dp[j][i] = Math.max(dp[k][j] + 1, 3);
                }
                ans = Math.max(ans, dp[j][i]);
            }
        }
        return ans;
    }

    public double champagneTower(int poured, int query_row, int query_glass) {
        double[] row = {poured};
        for (int i = 1; i <= query_row; i++) {
            double[] nextRow = new double[i + 1];
            for (int j = 0; j < i; j++) {
                double volume = row[j];
                if (volume > 1) {
                    nextRow[j] += (volume - 1) / 2;
                    nextRow[j + 1] += (volume - 1) / 2;
                }
            }
            row = nextRow;
        }
        return Math.min(1, row[query_glass]);
    }

    public String customSortString(String order, String s) {
        char[] chars = s.toCharArray();
        int[] orderTable = new int[26];

        for (int i = 0; i < order.length(); i++) {
            orderTable[order.charAt(i) - 'a'] = i;
        }

        List<Character> charList = new ArrayList();
        for (char c : chars) {
            charList.add(c);
        }

        Collections.sort(charList, Comparator.comparingInt(o -> orderTable[o - 'a']));
        StringBuilder stringBuilder = new StringBuilder();
        charList.stream().forEach(c -> stringBuilder.append(c));

        return stringBuilder.toString();
    }

    public int orderOfLargestPlusSign(int n, int[][] mines) {
        int[][] grid = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = 1;
            }
        }

        for (int i = 0; i < mines.length; i++) {
            grid[mines[i][0]][mines[i][1]] = 0;
        }

//        Arrays.stream(grid).forEach(r -> Arrays.fill(r, 1));
//        Arrays.stream(mines).forEach(r -> grid[r[0]][r[1]] = 0);

        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                res = Math.max(res, dfs(grid, i, j));
            }
        }

        return res;
    }

    public int dfs(int[][] grid, int row, int col) {
        if (grid[row][col] == 0) {
            return 0;
        }

        int down = move(grid, new int[]{row, col}, 1, 0);
        int up = move(grid, new int[]{row, col}, -1, 0);
        int right = move(grid, new int[]{row, col}, 0, 1);
        int left = move(grid, new int[]{row, col}, 0, -1);

        return Math.min(Math.min(down, up), Math.min(right, left));
    }

    public int move(int[][] grid, int[] base, int row, int col) {
        if (base[0] < 0 || base[1] < 0 || base[0] >= grid[0].length || base[1] >= grid[0].length || grid[base[0]][base[1]] == 0) {
            return 0;
        }
        base[0] += row;
        base[1] += col;
        return 1 + move(grid, base, row, col);
    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        List<TreeNode> nextLevel = new ArrayList<>();
        nextLevel.add(root);
        while (nextLevel.size() > 0) {
            nextLevel = leftOrder(res, nextLevel);
            nextLevel = rightOrder(res, nextLevel);
        }

        return res;
    }

    public List<TreeNode> leftOrder(List<List<Integer>> res, List<TreeNode> left) {
        LinkedList<TreeNode> nextLevel = new LinkedList<>();
        List<Integer> currentLevel = new ArrayList<>();
        for (TreeNode node : left) {
            if (node.left != null) {
                nextLevel.addFirst(node.left);
            }
            if (node.right != null) {
                nextLevel.addFirst(node.right);
            }
            currentLevel.add(node.val);
        }

        if (currentLevel.size() > 0) {
            res.add(currentLevel);
        }
        return nextLevel;
    }

    public List<TreeNode> rightOrder(List<List<Integer>> res, List<TreeNode> right) {
        LinkedList<TreeNode> nextLevel = new LinkedList<>();
        List<Integer> currentLevel = new ArrayList<>();
        for (TreeNode node : right) {
            if (node.right != null) {
                nextLevel.addFirst(node.right);
            }
            if (node.left != null) {
                nextLevel.addFirst(node.left);
            }
            currentLevel.add(node.val);
        }

        if (currentLevel.size() > 0) {
            res.add(currentLevel);
        }

        return nextLevel;
    }

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        Stack<List<Integer>> stack = new Stack<>();
        if (root == null) {
            return res;
        }

        List<TreeNode> nextOrder = new LinkedList<>();
        nextOrder.add(root);

        while (nextOrder.size() > 0) {
            nextOrder = nextLevel(stack, nextOrder);
        }

        while (!stack.isEmpty()) {
            res.add(stack.pop());
        }

        return res;
    }

    public List<TreeNode> nextLevel(Stack<List<Integer>> stack, List<TreeNode> currentOrder) {
        List<TreeNode> nextOrder = new LinkedList<>();
        List<Integer> currentValue = new ArrayList<>();
        for (TreeNode node : currentOrder) {
            currentValue.add(node.val);
            if (node.left != null) {
                nextOrder.add(node.left);
            }
            if (node.right != null) {
                nextOrder.add(node.right);
            }
        }

        if (currentValue.size() > 0) {
            stack.push(currentValue);
        }

        return nextOrder;
    }

    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(k, (o1, o2) -> {
            return nums1[o1[0]] + nums2[o1[1]] - nums1[o2[0]] - nums2[o2[1]];
        });
        List<List<Integer>> ans = new ArrayList<>();
        int m = nums1.length;
        int n = nums2.length;
        for (int i = 0; i < Math.min(m, k); i++) {
            pq.offer(new int[]{i, 0});
        }
        while (k-- > 0 && !pq.isEmpty()) {
            int[] idxPair = pq.poll();
            List<Integer> list = new ArrayList<>();
            list.add(nums1[idxPair[0]]);
            list.add(nums2[idxPair[1]]);
            ans.add(list);
            if (idxPair[1] + 1 < n) {
                pq.offer(new int[]{idxPair[0], idxPair[1] + 1});
            }
        }

        return ans;
    }

    public int maxAreaOfIsland(int[][] grid) {
        int res = 0;
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                res = Math.max(res, dfs_island(grid, row, col));
            }
        }
        return res;
    }

    public int dfs_island(int[][] grid, int row, int col) {
        if (row < 0 || col < 0 || row >= grid.length || col >= grid[row].length || grid[row][col] == 0) {
            return 0;
        }

        grid[row][col] = 0;

        int down = dfs_island(grid, row + 1, col);
        int up = dfs_island(grid, row - 1, col);
        int right = dfs_island(grid, row, col + 1);
        int left = dfs_island(grid, row, col - 1);
        return 1 + down + up + right + left;
    }

    public int findCircleNum(int[][] isConnected) {
        int cities = isConnected.length;
        int[] parent = new int[cities];
        for (int i = 0; i < cities; i++) {
            parent[i] = i;
        }
        for (int i = 0; i < cities; i++) {
            for (int j = i + 1; j < cities; j++) {
                if (isConnected[i][j] == 1) {
                    union(parent, i, j);
                }
            }
        }
        int provinces = 0;
        for (int i = 0; i < cities; i++) {
            if (parent[i] == i) {
                provinces++;
            }
        }
        return provinces;
    }

    public void union(int[] parent, int index1, int index2) {
        parent[find(parent, index1)] = find(parent, index2);
    }

    public int find(int[] parent, int index) {
        if (parent[index] != index) {
            parent[index] = find(parent, parent[index]);
        }
        return parent[index];
    }


    public int minimumTotal(List<List<Integer>> triangle) {
        int res = Integer.MAX_VALUE;
        int[][] dp = new int[triangle.size()][triangle.size()];
        Arrays.stream(dp).forEach(r -> Arrays.fill(r, 100000));
        dp[0][0] = triangle.get(0).get(0);
        for (int i = 1; i < triangle.size(); i++) {
            List<Integer> row = triangle.get(i);
            for (int col = 0; col < row.size(); col++) {
                if (col == 0) {
                    dp[i][col] = row.get(col) + dp[i - 1][col];
                } else {
                    dp[i][col] = row.get(col) + Math.min(dp[i - 1][col], dp[i - 1][col - 1]);
                }
            }
        }

        for (int i = 0; i < triangle.size(); i++) {
            res = Math.min(res, dp[triangle.size() - 1][i]);
        }

        return res;
    }

    public List<TreeNode> generateTrees(int n) {
        List<TreeNode> res = new ArrayList<>();

        List<TreeNode> allNodes = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            TreeNode node = new TreeNode(i);
            allNodes.add(node);
        }

        for (int i = 0; i < n; i++) {

        }

        return res;
    }

    public int nthUglyNumber(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1;
        int p2 = 1, p3 = 1, p5 = 1;
        for (int i = 2; i <= n; i++) {
            int num2 = dp[p2] * 2, num3 = dp[p3] * 3, num5 = dp[p5] * 5;
            dp[i] = Math.min(Math.min(num2, num3), num5);
            if (dp[i] == num2) {
                p2++;
            }
            if (dp[i] == num3) {
                p3++;
            }
            if (dp[i] == num5) {
                p5++;
            }
        }
        return dp[n];
    }

    public int nthSuperUglyNumber(int n, int[] primes) {
        List<Long> dp = new ArrayList<>();
        PriorityQueue<Long> pq = new PriorityQueue<>();
        Set<Long> set = new HashSet<>();
        dp.add(1L);
        Arrays.stream(primes).forEach(num -> {
            pq.offer(num * 1L);
        });
        int[] pointers = new int[primes.length];
        while (dp.size() < n) {
            long min = pq.poll();
            if (set.contains(min)) {
                continue;
            }
            dp.add(min);
            for (int i = 0; i < pointers.length; i++) {
                if (min == primes[i] * dp.get(pointers[i])) {
                    pointers[i]++;
                    set.add(min);
                    pq.offer(primes[i] * dp.get(pointers[i]));
                }
            }
        }
        return dp.get(n - 1).intValue();
    }

    public List<String> topKFrequent(String[] words, int k) {
        List<String> res = new ArrayList<>();
        Map<String, Integer> countMap = new HashMap<>();
        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<Map.Entry<String, Integer>>((o1, o2) -> {
            if (o1.getValue().equals(o2.getValue())) {
                return o1.getKey().compareTo(o2.getKey());
            } else {
                return o2.getValue().compareTo(o1.getValue());
            }
        });

        for (String word : words) {
            Integer count = countMap.getOrDefault(word, 0);
            countMap.put(word, count + 1);
        }

        for (Map.Entry entry : countMap.entrySet()) {
            pq.offer(entry);
        }

        for (int i = 0; i < k; i++) {
            res.add(pq.poll().getKey());
        }
        return res;
    }

}
