package com.dingfubing.algorithm;

/**
 * TODO
 *
 * @author dingfubing
 * @since 2023/2/23 16:40
 */
public class EasyLeetcode21_1 {

    public static void main(String[] args) {
        ListNode list10 = new ListNode(1);
        ListNode list11 = new ListNode(2);
        ListNode list12 = new ListNode(4);
        list10.next = list11;
        list11.next = list12;

        ListNode list20 = new ListNode(1);
        ListNode list21 = new ListNode(3);
        ListNode list22 = new ListNode(4);
        list20.next = list21;
        list21.next = list22;
        ListNode res = mergeTwoLists(list10, list20);
        while (res != null) {
            System.out.println(res.val);
            res = res.next;
        }
    }

    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        if (list1.val < list2.val) {
            list1.next = mergeTwoLists(list1.next, list2);
            return list1;
        } else {
            list2.next = mergeTwoLists(list1, list2.next);
            return list2;
        }
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
