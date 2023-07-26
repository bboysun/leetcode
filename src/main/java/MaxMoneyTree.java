/**
 * @Author: darrylsun
 * @Description: 给定一个二叉树，不能选父子关系的节点数据，找到该树中按规则选取节点后求和最大值
 * 4
 * ｜——8——7
 * 2
 * ｜5
 * @Date: 2023/7/26
 */
public class MaxMoneyTree {

    static class TreeNode {

        int value;

        TreeNode rightNode;

        TreeNode leftNode;

        public TreeNode(int value, TreeNode leftNode, TreeNode rightNode) {
            this.leftNode = leftNode;
            this.rightNode = rightNode;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        TreeNode treeNode5 = new TreeNode(5, null, null);
        TreeNode treeNode6 = new TreeNode(6, null, null);
        TreeNode treeNode2 = new TreeNode(2, treeNode5, treeNode6);
        TreeNode treeNode7 = new TreeNode(7, null, null);
        TreeNode treeNode8 = new TreeNode(8, null, treeNode7);
        TreeNode treeNode4 = new TreeNode(4, treeNode2, treeNode8);

        dfs(treeNode4);
        int max = max(treeNode4);
        System.out.println(max);

        TreeNode treeNode22 = new TreeNode(2, null, null);
        TreeNode treeNode23 = new TreeNode(3, null, null);
        TreeNode treeNode21 = new TreeNode(1, treeNode22, treeNode23);
        System.out.println(max(treeNode21));
    }

    public static void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.println(root.value);
        dfs(root.leftNode);
        dfs(root.rightNode);
    }

    public static int max(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = max(root.leftNode);
        int right = max(root.rightNode);
        // 选了中间的就不能选两边的，不选中间的，从两边中取最优值
        return Math.max(root.value, left + right);
    }


}
