class ListNode {
    int val;
    ListNode next;

    ListNode() {}

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

public class ReverseLLII {

    public ListNode reverseBetween(ListNode head, int left, int right) {
        if(head==null || left==right)
            return head;
        
        ListNode dummy = new ListNode(-1);
        dummy.next=head;

        ListNode prev = dummy;
        for(int i=1;i<left;i++){
            prev=prev.next;
        }
        ListNode curr = prev.next;
        ListNode nextNode = null;
        for(int i=0;i<right-left;i++){
            nextNode = curr.next;
            curr.next = nextNode.next;
            nextNode.next=prev.next;
            prev.next=nextNode;
        }
        return dummy.next;
    }

    // Utility method to print linked list
    public static void printList(ListNode head) {
        ListNode temp = head;

        while (temp != null) {
            System.out.print(temp.val);

            if (temp.next != null) {
                System.out.print(" -> ");
            }

            temp = temp.next;
        }

        System.out.println();
    }

    public static void main(String[] args) {

        /*
            Input Linked List:
            1 -> 2 -> 3 -> 4 -> 5

            left = 2
            right = 4

            Expected Output:
            1 -> 4 -> 3 -> 2 -> 5
        */

        // Creating sample linked list
        ListNode head = new ListNode(
                1,
                new ListNode(
                        2,
                        new ListNode(
                                3,
                                new ListNode(
                                        4,
                                        new ListNode(5)
                                )
                        )
                )
        );

        int left = 2;
        int right = 4;

        System.out.println("Original Linked List:");
        printList(head);

        ReverseLLII obj = new ReverseLLII();

        ListNode result = obj.reverseBetween(head, left, right);

        System.out.println("Modified Linked List:");
        printList(result);
    }
}