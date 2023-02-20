package com.dingfubing.algorithm;

/**
 * TODO
 *
 * @author dingfubing
 * @since 2023/2/16 15:38
 */
public class EasyLeetCode02 {

    public static void main(String[] args) {

    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode listNode = new ListNode();
        ListNode pre = listNode;
        int carry = 0;
        while (l1 != null || l2 != null) {
            listNode.next = new ListNode();
            int a = l1 == null ? 0 : l1.val;
            int b = l2 == null ? 0 : l2.val;
            listNode.next.val = (a + b + carry) % 10;
            carry = (a + b + carry) / 10;
            l1 = l1 == null ? null :  l1.next;
            l2 = l2 == null ? null :  l2.next;
            listNode = listNode.next;
        }
        if (carry > 0) {
            listNode.next = new ListNode(carry, null);
        }

        return pre.next;
    }
}

class ListNode {

    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}