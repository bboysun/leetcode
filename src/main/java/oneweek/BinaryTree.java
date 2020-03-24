package oneweek;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: Darryl
 * @Description: 二叉树的构建和前序，中序，后序遍历
 *        6
 *     3      8
 *   1    5  7   9
 * @Date: created in 2020/3/24 20:43
 */
public class BinaryTree {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(6);
        int[] arr = {3,1,5,8,7,9};
        for(int i=0; i<arr.length; i++) {
            buildTree(root, arr[i]);
        }

        List<Integer> res = new ArrayList<Integer>();
        // 前序遍历
        //beforePrint(root, res);
        // 中序遍历
        //middlePrint(root, res);
        //后序遍历
        afterPrint(root, res);
        for (Integer val : res) {
            System.out.print(" " + val + " ");
        }
    }

    // 后序遍历
    private static void afterPrint(TreeNode root, List<Integer> res) {
        if(root.left != null)
            afterPrint(root.left, res);
        if(root.right != null)
            afterPrint(root.right, res);
        res.add(root.value);
    }

    // 中序遍历一棵树
    private static void middlePrint(TreeNode root, List<Integer> res) {
        if(root.left != null)
            middlePrint(root.left, res);
        res.add(root.value);
        if(root.right != null)
            middlePrint(root.right, res);
    }

    // 前序遍历一棵树
    private static void beforePrint(TreeNode root, List<Integer> res) {
        res.add(root.value);
        if(root.left != null)
            beforePrint(root.left, res);
        if (root.right != null)
            beforePrint(root.right, res);
    }


    // 构建一棵树
    private static void buildTree(TreeNode root, int value) {
        if(root.value < value){
            if(root.right == null)
                root.right = new TreeNode(value);
            else
                buildTree(root.right, value);
        } else {
            if(root.left == null)
                root.left = new TreeNode(value);
            else
                buildTree(root.left, value);
        }
    }

    // 构建一个树节点
    static class TreeNode {
        TreeNode left;
        TreeNode right;
        int value;
        // 构造函数
        public TreeNode(int value){
            this.value = value;
        }
    }
}
