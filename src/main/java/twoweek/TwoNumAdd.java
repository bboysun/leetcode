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
        ListNode node1 = new ListNode(2);
        ListNode node11 = new ListNode(4);
        ListNode node12 = new ListNode(3);
        node1.next = node11;
        node11.next = node12;

        ListNode node2 = new ListNode(5);
        ListNode node21 = new ListNode(6);
        ListNode node22 = new ListNode(4);
        node2.next = node21;
        node21.next = node22;

        ListNode resNode = solution(node1, node2);
        System.out.println("over");
        //while ()
    }

    private static ListNode solution(ListNode node1, ListNode node2) {
        int tempVal = 0;
        int more = 0;
        ListNode root = new ListNode(0);

        if (node1.val + node2.val + more >= 10) {
            tempVal = node1.val + node2.val - 10;
            more = 1;
            root.val = tempVal;
        } else {
            tempVal = node1.val + node2.val;
            root.val = tempVal;
        }

        ListNode tempNode = root;
        ListNode tempNode1 = node1.next;
        ListNode tempNode2 = node2.next;

        while (tempNode1 != null && tempNode2 != null) {
            ListNode node = new ListNode(0);
            if (tempNode1.val + tempNode2.val + more >= 10) {
                tempVal = tempNode1.val + tempNode2.val + more - 10;
                more = 1;
                node.val = tempVal;
                tempNode.next = node;
                tempNode = node;
                tempNode1 = tempNode1.next;
                tempNode2 = tempNode2.next;
            } else {
                tempVal = tempNode1.val + tempNode2.val + more;
                more = 0;
                node.val = tempVal;
                tempNode.next = node;
                tempNode = node;
                tempNode1 = tempNode1.next;
                tempNode2 = tempNode2.next;
            }
        }

        /*if (more == 1) {

        }*/

        return root;
    }

    static class ListNode {
        int val;
        ListNode next;
        public ListNode(int val){
            this.val = val;
        }
    }
}
