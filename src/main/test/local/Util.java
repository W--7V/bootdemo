package local;

import java.util.ArrayList;
import java.util.List;

public class Util {

    public static TreeNode convertArrayToTree(Integer[] arr) {
        List<TreeNode> queue = new ArrayList();
        queue.add(new TreeNode(arr[0]));
        int insertNode = 0;
        for (int i = 1; i < arr.length; i++) {
            TreeNode node = new TreeNode(arr[i]);

            while (queue.get(insertNode).val == null) {
                insertNode += 1;
            }
            if (queue.get(insertNode).left == null) {
                queue.get(insertNode).left = node;
            } else if (queue.get(insertNode).right == null) {
                queue.get(insertNode).right = node;
                insertNode += 1;
            }
            queue.add(node);
        }
        replaceNullNode(queue.get(0));

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
        TreeNode treeNode = convertArrayToTree(new Integer[]{3, 9, 20, null, null, 15, 7});
        System.out.println(treeNode);
    }
}
