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

public class OddEvenLL {

    /*
INTUITION:
------------
We need to rearrange the linked list such that:
- All nodes at odd positions come first
- All nodes at even positions come after them

Example:
Input:
1 -> 2 -> 3 -> 4 -> 5

Positions:
1st  -> 1
2nd  -> 2
3rd  -> 3
4th  -> 4
5th  -> 5

Output:
1 -> 3 -> 5 -> 2 -> 4

------------------------------------------------------------

MAIN IDEA:
------------
Instead of creating new lists,
we rearrange the existing pointers in-place.

We maintain:
1. even  -> traverses odd-position nodes
2. odd   -> traverses even-position nodes
3. temp  -> stores starting node of even-position list

Why store temp?
Because after separating both chains,
we need to attach the even list at the end of odd list.

------------------------------------------------------------

STEP-BY-STEP LOGIC:
-------------------

1. Handle edge cases
    If list is empty or contains only one node,
    no rearrangement is needed.

2. Initialize pointers
    even = head
    odd  = head.next
    temp = odd

    Here:
    even pointer actually tracks odd-position nodes
    odd pointer tracks even-position nodes

3. Separate odd-position chain
    even.next = odd.next

    This skips current even-position node
    and connects current odd-position node
    to next odd-position node.

4. Move even pointer forward
    even = even.next

5. Separate even-position chain
    odd.next = even.next

    This skips current odd-position node
    and connects current even-position node
    to next even-position node.

6. Move odd pointer forward
    odd = odd.next

7. Continue until even-position list ends

8. Attach stored even list at the end
    even.next = temp

------------------------------------------------------------

TIME COMPLEXITY:
----------------
O(N)
Each node is visited once.

SPACE COMPLEXITY:
-----------------
O(1)
Only pointers are used.
*/

    public ListNode oddEvenList(ListNode head) {

        if(head == null || head.next==null){
            return head;
        }
        ListNode even=head;
        ListNode odd = head.next;
        ListNode temp=odd;
        while(odd!=null && odd.next!=null){
            even.next=odd.next;
            even=even.next;
            odd.next=even.next;
            odd=odd.next;
        }
        even.next=temp;
        return head;
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

         Expected Output:
         1 -> 3 -> 5 -> 2 -> 4
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

        System.out.println("Original Linked List:");
        printList(head);

        // Calling solution method
        OddEvenLL solution = new OddEvenLL();
        ListNode result = solution.oddEvenList(head);

        System.out.println("After Odd-Even Rearrangement:");
        printList(result);
    }
}