package com.dingfubing.algorithm;

import java.util.Scanner;

/**
 * TODO
 *
 * @author dingfubing
 * @since 2023/2/24 10:49
 */
public class Offer024 {

    public ListNode reverseList(ListNode head) {
        ListNode cur = head;
        ListNode temp = head.next;
        ListNode pre = null;
        while (cur != null) {
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        return pre;

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            System.out.println(scanner.next());
        }
        // ListNode listNode1 = new ListNode(1);
        // ListNode listNode2 = new ListNode(2);
        // ListNode listNode3 = new ListNode(3);
        // ListNode listNode4 = new ListNode(4);
        // ListNode listNode5 = new ListNode(5);
        // ListNode listNode6 = new ListNode(6);
        // listNode1.next = listNode2;
        // listNode2.next = listNode3;
        // listNode3.next = listNode4;
        // listNode4.next = listNode5;
        // listNode5.next = listNode6;
        // listNode6.next = null;
        // reverse(listNode1);
    }

    static ListNode  reverse(ListNode head) {
        if (head.next == null) {
            return head;
        }
        ListNode last = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return last;
    }


    public ListNode dp(ListNode cur, ListNode pre) {
        // 退出循环条件
        if (cur == null) {
            return pre;
        }
        ListNode res = dp(cur.next, cur);
        cur.next = pre;
        return res;
    }

    static class ListNode {

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
}
