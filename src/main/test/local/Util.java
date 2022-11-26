package local;

import java.util.ArrayList;
import java.util.List;

public class Util {

    public static TreeNode convertArrayToTree(Integer[] arr) {
        List<TreeNode> queue = new ArrayList();
        // 先把根节点加入队列
        queue.add(new TreeNode(arr[0]));

        // 标记待插入子节点的树节点
        int insertNode = 0;

        // 标记该插入到左子节点还是右子节点
        boolean isLeft = true;
        for (int i = 1; i < arr.length; i++) {
            TreeNode node = arr[i] == null ? null : new TreeNode(arr[i]);

            // 不可能插入到空节点的子节点、跳过。。。
            while (queue.get(insertNode) == null) {
                insertNode += 1;
            }
            if (isLeft) {
                queue.get(insertNode).left = node;
                isLeft = false;
            } else {
                queue.get(insertNode).right = node;
                insertNode += 1;
                isLeft = true;
            }
            queue.add(node);
        }
//        replaceNullNode(queue.get(0));

        return queue.get(0);
    }

    public static void replaceNullNode(TreeNode root) {
        if (root == null) {
            return;
        }

        if (root.left != null && root.left.val == null) {
            root.left = null;
        }
        if (root.right != null && root.right.val == null) {
            root.right = null;
        }
        replaceNullNode(root.left);
        replaceNullNode(root.right);
    }

    public static void main(String[] args) {
        TreeNode treeNode = convertArrayToTree(new Integer[]{3, 9, 20, 8, null, 15, 7, null, null, 55});
        System.out.println(treeNode);
    }
}


class TreeNode {
    Integer val;
    TreeNode left;
    TreeNode right;

    TreeNode(Integer x) {
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