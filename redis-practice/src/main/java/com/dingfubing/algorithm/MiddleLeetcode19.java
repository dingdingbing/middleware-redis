package com.dingfubing.algorithm;

/**
 * 快慢指针儿~
 *
 * @author dingfubing
 * @since 2022/4/19 14:40
 */
public class MiddleLeetcode19 {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(4);
        ListNode l5 = new ListNode(5);
        head.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;
        l5.next = null;

        ListNode listNode = removeNthFromEnd2(head, 4);
        while (listNode != null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return null;
        }
        // 普通操作，循环一遍得到链表长度
        ListNode temp = head;
        int length = 0;
        while (temp != null) {
            length++;
            temp = temp.next;
        }
        if (n == length) {
            return head.next;
        }
        // 再循环一遍找到倒数第n个节点 1 2 3 4 5  length = 5 n = 2 删除的节点为 4 ,index = 3
        int deleteInd = length - n;
        temp = head;
        ListNode pre = null;
        int currentInd = 0;
        while (temp != null) {
            if (currentInd == deleteInd - 1) {
                pre = temp;
            }
            if (currentInd == deleteInd && pre != null) {
                pre.next = temp.next;
            }
            currentInd++;
            temp = temp.next;
        }
        return head;
    }

    public static ListNode removeNthFromEnd2(ListNode head, int n) {
        if (head == null) {
            return null;
        }
        // 一次循环操作，使用快慢指针，快指针比慢指针多n+1个身位
        ListNode fast = head;
        for (int i = 1; i < n+1; i++) {
            fast = fast.next;
        }
        if (fast == null) {
            return head.next;
        }

        ListNode temp = head;
        ListNode slow = head;
        while (temp != null) {
            // 当快指针到达尾的时候，删除慢指针的节点
            if (fast.next == null) {
                slow.next = slow.next.next;
                break;
            } else {
                temp = temp.next;
                fast = fast.next;
                slow = slow.next;
            }
        }
        return head;
    }

    private static class ListNode {

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
