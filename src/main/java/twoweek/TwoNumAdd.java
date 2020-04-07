package twoweek;

/**
 * @Auther: Darryl
 * @Description:
 *
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 *
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 *
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 * 示例：
 *
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 *
 * @Date: created in 2020/3/30 22:21
 */

public class TwoNumAdd {

    public static void main(String[] args) {

        // 构建列表方法1
        ListNode node1 = buildList1(new int[]{2,4,3});

        // 构建列表方法2
        ListNode node2 = new ListNode(1);
        buildList2(node2, new int[]{6,4});

        ListNode resNode = solution(node1, node2);
        while (resNode != null) {
            System.out.print(resNode.val + " ");
            resNode = resNode.next;
        }
    }

    private static ListNode solution(ListNode node1, ListNode node2) {
        ListNode header = new ListNode(0);
        ListNode temp = header;
        // 记录进位数
        int up = 0;
        while (node1 != null || node2 != null) {
            int a = node1 != null ? node1.val : 0;
            int b = node2 != null ? node2.val : 0;

            int res;
            if (a + b + up >= 10) {
                res = (a + b + up) % 10;
                up = 1;
            } else {
                res = a + b + up;
                up = 0;
            }

            // 要将最终的结果通过next连接起来
            temp.next = new ListNode(res);
            temp = temp.next;

            // 最终遍历到最后
            if (node1 != null)
                node1 = node1.next;
            if (node2 != null)
                node2 = node2.next;
        }

        if (up > 0)
            temp.next = new ListNode(up);

        return header.next;
    }

    static ListNode buildList1(int[] vals) {
        ListNode header = new ListNode(vals[0]);
        ListNode temp = header;
        for (int i=1; i<vals.length; i++) {
            temp.next = new ListNode(vals[i]);
            temp = temp.next;
        }
        return header;
    }

    static void buildList2(ListNode header, int[] vals) {
        ListNode temp = header;
        for (int i=0; i<vals.length; i++) {
            temp.next = new ListNode(vals[i]);
            temp = temp.next;
        }
    }

    static class ListNode {
        int val;
        ListNode next;
        public ListNode(int val){
            this.val = val;
        }
    }
}
