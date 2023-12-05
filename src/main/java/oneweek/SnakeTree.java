package oneweek;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @Auther: Darryl
 * @Description: 蛇形输出一棵二叉树
 * 当一棵二叉树
 *        6
 *     3      8
 *  1    5  7   9
 *
 * 蛇形输出：
 * 6 8 3 1 5 7 9
 * @Date: created in 2020/3/24 21:20
 */
public class SnakeTree {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(6);
        int[] arr = {3,1,5,8,7,9};
        // 构建一棵树
        for (int i=0; i<arr.length; i++)
            buildTree(root, arr[i]);

        List<Integer> res1 = new ArrayList<>();
        midPrint(res1, root);
        // 期望结果：1356789
        res1.forEach(v -> System.out.println(v));



        List<LinkedList<Integer>> res = snakePrint(root);
        for (LinkedList<Integer> sub : res) {
            for (Integer val : sub)
                System.out.print(" " + val + " ");
        }
    }

    /**
     * 中序遍历
     * @param res
     * @param root
     */
    private static void midPrint(List<Integer> res, TreeNode root) {
        if (root == null) {
            return;
        }
        midPrint(res, root.left);
        res.add(root.value);
        midPrint(res, root.right);
    }

    private static List<LinkedList<Integer>> snakePrint(TreeNode root) {
        // 蛇形遍历后的结果集合
        List<LinkedList<Integer>> res = new ArrayList<LinkedList<Integer>>();
        // 保存每一层节点数据
        LinkedList<Integer> levelNode = new LinkedList<Integer>();
        // 缓存每个节点数据,用null来分割每一层数据
        LinkedList<TreeNode> cacheNode = new LinkedList<TreeNode>();
        // 表示遍历方向
        boolean isLeft = true;

        cacheNode.addLast(root);
        cacheNode.addLast(null);

        while (cacheNode.size() > 0) {
            TreeNode curNode = cacheNode.poll();
            if (curNode != null) {
                if (isLeft)
                    levelNode.addLast(curNode.value);
                else
                    levelNode.addFirst(curNode.value);

                if (curNode.left != null)
                    cacheNode.addLast(curNode.left);
                if (curNode.right != null)
                    cacheNode.addLast(curNode.right);
            } else {
                isLeft = !isLeft;
                if (levelNode.size() >0)
                    // 此时 cacheNode add null 是为了分割每一层做标示，如果是树的叶子节点，相当于为叶子节点下多加一层null节点，然后根据null节点跳出循环
                    // 这里很关键，也很难理解
                    cacheNode.addLast(null);
                res.add(levelNode);
                levelNode = new LinkedList<Integer>();
            }
        }
        return res;
    }

    private static void buildTree(TreeNode root, int value) {
        if(root.value < value) {
            if (root.right == null)
                root.right = new TreeNode(value);
            else
                buildTree(root.right, value);
        } else {
            if (root.left == null)
                root.left = new TreeNode(value);
            else
                buildTree(root.left, value);
        }
    }

    // 树节点
    static class TreeNode{
        TreeNode left;
        TreeNode right;
        int value;
        public TreeNode(int value) {
            this.value = value;
        }
    }
}
