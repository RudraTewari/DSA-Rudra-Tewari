// Rotate List - LeetCode Style
// Class Name: RotateLL

class ListNode {
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

public class RotateLL {

    /*
-----------------------------------
Approach:
-----------------------------------

1. First, calculate the length of the linked list.
   - Traverse the entire list using a temporary pointer.
   - Count the number of nodes present.

2. Reduce unnecessary rotations using:
        effective_rotations = k % len

3. Perform the rotation operation effective_rotations times.
   For each rotation:
   - Find the last node (tail).
   - Find the second last node (secLastTail).
   - Connect tail to the current head.
   - Break the link from second last node.
   - Move head to tail.

4. Return the new head of the rotated list.


-----------------------------------
Intuition:
-----------------------------------

A right rotation means:
- Remove the last node of the linked list.
- Place it at the beginning.

Example:

Original List:
1 -> 2 -> 3 -> 4 -> 5

After 1 rotation:
5 -> 1 -> 2 -> 3 -> 4

After another rotation:
4 -> 5 -> 1 -> 2 -> 3

So every rotation shifts the list one step to the right.


-----------------------------------
Why Effective Rotations?
-----------------------------------

Suppose:

List Length = 5
k = 12

Rotating a list 5 times brings the list back
to its original form.

Example:

After 5 rotations:
1 -> 2 -> 3 -> 4 -> 5

Again same list.

So:
- Every complete cycle of length 'len'
  produces the original list again.
- Therefore, full cycles are unnecessary.

We only need the remaining rotations.

Formula:
    effective_rotations = k % len

Example:
    12 % 5 = 2

Meaning:
- Rotating 12 times is exactly same as rotating 2 times.

This optimization avoids unnecessary work
and improves efficiency significantly.


-----------------------------------
Dry Run:
-----------------------------------

List:
1 -> 2 -> 3 -> 4 -> 5

k = 2
len = 5

effective_rotations = 2 % 5 = 2


First Rotation:
----------------
tail = 5
secLastTail = 4

Connect:
5 -> 1

Break:
4 -> null

New List:
5 -> 1 -> 2 -> 3 -> 4


Second Rotation:
----------------
tail = 4
secLastTail = 3

Connect:
4 -> 5

Break:
3 -> null

Final List:
4 -> 5 -> 1 -> 2 -> 3


-----------------------------------
Time Complexity:
-----------------------------------

O(n * effective_rotations)

For every rotation,
we traverse the list to find the tail.

Worst Case:
O(n * k)


-----------------------------------
Space Complexity:
-----------------------------------

O(1)

No extra space is used.
*/
    public ListNode rotateRight(ListNode head, int k) {
        ListNode temp = head;
        int len=0;
        while(temp!=null){
            len++;
            temp=temp.next;
        }
        int effective_rotations=k % len;
        ListNode secLastTail,tail;
        while(effective_rotations != 0){
            secLastTail=null;
            tail=head;
            while(tail.next!=null){
                secLastTail=tail;
                tail=tail.next;
            }
            tail.next=head;
            secLastTail.next=null;
            head=tail;
            effective_rotations--;
        }
        return head;
    }

    // Utility Method to Print Linked List
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
            Sample Input:
            Linked List: 1 -> 2 -> 3 -> 4 -> 5
            k = 2

            Expected Output:
            4 -> 5 -> 1 -> 2 -> 3
        */

        // Creating Sample Linked List
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        int k = 2;

        System.out.println("Original Linked List:");
        printList(head);

        RotateLL solution = new RotateLL();

        ListNode rotatedHead = solution.rotateRight(head, k);

        System.out.println("Rotated Linked List:");
        printList(rotatedHead);
    }
}