class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode res = new ListNode(0);
        ListNode p = res;
        int plus = 0;
        while (l1 != null || l2 != null) {
            int a1 = l1 != null ? l1.val: 0;
            int a2 = l2 != null ? l2.val: 0;

            int a3 = a1 + a2 + plus;
            plus = a3>=10 ? 1 : 0;

            p.next = new ListNode(a3%10);
            p = p.next;
            l1 = l1!=null ? l1.next : null;
            l2 = l2!=null ? l2.next : null;
        }
        if (plus == 1) {
            p.next = new ListNode(1);
        }

        return res.next;
    }
}
