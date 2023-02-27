package com.dingfubing.algorithm;

import com.dingfubing.algorithm.Offer024.ListNode;

/**
 * TODO
 *
 * @author dingfubing
 * @since 2023/2/24 16:21
 */
public class EasyLeetcode092 {

    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);
        ListNode listNode5 = new ListNode(5);
        ListNode listNode6 = new ListNode(6);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;
        listNode5.next = listNode6;
        listNode6.next = null;
        ListNode listNode = reverseBetween(listNode1, 2, 4);
        while (listNode != null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }
    public static ListNode reverseBetween(ListNode head, int left, int right) {
        if(left == 1) {
            return reverseN(head, right);
        }
        head.next = reverseBetween(head.next, left-1, right-1);
        return head;
    }
    // 后面一个指针
    static ListNode ser = null;
    // 反转前N个
    public static ListNode reverseN(ListNode head, int m) {
        if(m == 1) {
            ser = head.next;
            return head;
        }
        ListNode res = reverseN(head.next, m-1);
        head.next.next = head;
        head.next = ser;
        // ser.next = head;
        // head.next = null;
        return res;
    }
}
