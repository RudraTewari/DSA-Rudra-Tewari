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
/*
APPROACH:

The question asks us to reorder the linked list in this pattern:

L0 → Ln → L1 → Ln-1 → L2 → Ln-2 ...

Example:
1 → 2 → 3 → 4 → 5

becomes:
1 → 5 → 2 → 4 → 3


---------------------------------------------------
STEP 1 : FIND THE MIDDLE OF THE LINKED LIST
---------------------------------------------------

Use Slow and Fast Pointer approach.

- slow moves 1 step
- fast moves 2 steps

When fast reaches the end,
slow will be standing at the middle.

Example:

1 → 2 → 3 → 4 → 5

slow stops at 3

Now split the list into two halves:

First Half:
1 → 2 → 3

Second Half:
4 → 5


---------------------------------------------------
STEP 2 : REVERSE THE SECOND HALF
---------------------------------------------------

Reverse the second linked list using the standard
3-pointer reversal technique.

Before Reverse:
4 → 5

After Reverse:
5 → 4

Now we have:

First Half:
1 → 2 → 3

Reversed Second Half:
5 → 4

---------------------------------------------------
STEP 3 : MERGE BOTH HALVES ALTERNATELY
---------------------------------------------------

At this stage we have:

1st Linked List  -> first half
2nd Linked List  -> reversed second half

Now merge both lists by taking nodes alternately.


ALGORITHM:

    1. Create two pointers:
    - temp1 for first list
    - temp2 for second list

    2. Repeat until second list becomes null:

    a) Store next nodes:
        m1 = temp1.next
        m2 = temp2.next

    b) Connect current node of first list
        to current node of second list:
        
        temp1.next = temp2

    c) Connect current node of second list
        to next node of first list:
        
        temp2.next = m1

    d) Move both pointers forward:
        
        temp1 = m1
        temp2 = m2


---------------------------------------------------
WHY THIS WORKS ?
---------------------------------------------------

Each iteration inserts one node from
the second list in between nodes of
the first list.

So the pattern becomes:

first node
second-half node
first node
second-half node
...

which creates the required reordered list.


---------------------------------------------------
WHY STORE m1 AND m2 ?
---------------------------------------------------

Because changing pointers breaks old connections.

So before modifying links,
we safely store the next nodes
to continue traversal later.
*/

public class ReorderList {

    // Function Definition (LeetCode Style)
    public void reorderList(ListNode head) {

        ListNode slow=head;
        ListNode fast=head;
        while(fast!=null && fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }

        ListNode secondHead=slow.next;
        slow.next=null;

        ListNode prev=null;
        ListNode curr = secondHead;

        while(curr != null){
            ListNode newNode = curr.next;
            curr.next=prev;
            prev=curr;
            curr=newNode;
        }

        ListNode temp1=head;
        ListNode temp2=prev;

        while(temp2!=null){
            ListNode m1=temp1.next;
            ListNode m2=temp2.next;

            temp1.next=temp2;
            temp2.next=m1;
            temp2=m2;
            temp1=m1;
        }
    }

    // Display Linked List
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
            1 -> 2 -> 3 -> 4 -> 5

            Expected Output:
            1 -> 5 -> 2 -> 4 -> 3
        */

        // Creating Sample Linked List
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        System.out.println("Original Linked List:");
        printList(head);

        // Function Call
        ReorderList obj = new ReorderList();
        obj.reorderList(head);

        System.out.println("Reordered Linked List:");
        printList(head);
    }
}