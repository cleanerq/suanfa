package com.qby.suanfa;

public class ListNode {
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

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                '}';
    }

    public static ListNode makeListNode(Integer[] sz)  {
        ListNode head = new ListNode(sz[0]);
        ListNode listNode = head;
        for (int i = 1; i < sz.length; i++) {
            ListNode next = new ListNode(sz[i]);
            listNode.next = next;
            listNode = next;
        }
        return head;
    }
}
