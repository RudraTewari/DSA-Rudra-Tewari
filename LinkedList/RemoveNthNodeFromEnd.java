/**
 * Definition for singly-linked list.
 */
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

public class RemoveNthNodeFromEnd {

    public ListNode removeNthFromEnd(ListNode head, int n) {

        

        return null;
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

         n = 2

         Expected Output:
         1 -> 2 -> 3 -> 5
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

        int n = 2;

        System.out.println("Original Linked List:");
        printList(head);

        // Calling solution method
        RemoveNthNodeFromEnd solution = new RemoveNthNodeFromEnd();

        ListNode result = solution.removeNthFromEnd(head, n);

        System.out.println("Linked List After Removing Nth Node From End:");
        printList(result);
    }
}