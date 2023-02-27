package com.dingfubing.algorithm;

/**
 * TODO
 *
 * @author dingfubing
 * @since 2023/2/23 10:13
 */
public class EasyLeetCode19 {

    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);
        ListNode listNode5 = new ListNode(5);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;
        listNode5.next = null;
        ListNode temp = listNode1;
        while (temp != null) {
            System.out.println(temp.val);
            temp = temp.next;
        }
        ListNode listNode = removeNthFromEnd(listNode1, 2);
        while (listNode != null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }
    public static  ListNode removeNthFromEnd(ListNode head, int n) {
        // 需要考虑只存在一个的情况, 需要返回空数组
        ListNode temp = new ListNode(0);
        temp.next = head;
        ListNode left = temp;
        ListNode right = temp;
        while (n > 0) {
            right = right.next;
            n--;
        }
        if (right == null) {
            return temp.next;
        }

        while (right.next != null) {
            left = left.next;
            right = right.next;
        }
        // 此时left 就是需要删除的节点
        left.next = left.next.next;
        return temp.next;

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

