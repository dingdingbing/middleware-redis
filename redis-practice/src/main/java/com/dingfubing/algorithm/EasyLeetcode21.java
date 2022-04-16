package com.dingfubing.algorithm;

/**
 * TODO
 *
 * @author dingfubing
 * @since 2022/4/15 16:30
 */
public class EasyLeetcode21 {

    public static void main(String[] args) {

    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // val 升序
        ListNode result = new ListNode(0);
        ListNode res = result;
        if (list1 == null) {
            res.next = list2;
        }
        if (list2 == null) {
            res.next = list1;
        }
        while (list1 != null && list2 != null) {
            if (list1.val > list2.val) {
                res.next = new ListNode(list2.val);
                list2 = list2.next;
            } else {
                res.next = new ListNode(list1.val);
                list1 = list1.next;
            }
            res = res.next;
            if (list1 == null) {
                // 加所有的都进去
                res.next = list2;
            }
            if (list2 == null) {
                res.next = list1;
            }


        }

        return result.next;
    }


    public static class ListNode {

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
