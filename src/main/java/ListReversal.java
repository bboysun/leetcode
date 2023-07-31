/**
 * @Author: darrylsun
 * @Description: 链表反转
 * 例如：
 * 链表：3->6->2->8
 * 反转：8->2->6->3
 * @Date: 2023/7/26
 */
public class ListReversal {

    static class ListNode {
        int value;
        ListNode next;

        public ListNode(int value, ListNode next) {
            this.value = value;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        ListNode node4 = new ListNode(8, null);
        ListNode node3 = new ListNode(2, node4);
        ListNode node2 = new ListNode(6, node3);
        ListNode node1 = new ListNode(3, node2);

        //ListNode reversal = reversal(node1);

        ListNode recursion = recursion(node1);
        System.out.println();

//        ListNode current = reversal;
//        while (current != null) {
//            System.out.println(current.value);
//            current = current.next;
//        }
    }

    // 迭代
    public static ListNode reversal(ListNode head) {
        // 用于迭代
        ListNode current = head;
        ListNode reversalNext = null;
        while (current != null) {
            // 暂存正序的下一个 node
            ListNode temp = current.next;

            // 让当前反转指向
            current.next = reversalNext;

            // 用于下一次迭代方向指向的对象
            reversalNext = current;

            // 用于迭代循环
            current = temp;
        }
        return reversalNext;
    }


    // 递归
    public static ListNode recursion(ListNode head) {
        // 递归推出条件
        if (head == null || head.next == null) {
            return head;
        }
        // 返回新的头节点
        ListNode temp = recursion(head.next);
        // 反向指向前一个节点
        head.next.next = head;
        // 断开原来的next指针
        head.next = null;
        // 返回新头节点
        return temp;
    }
}
