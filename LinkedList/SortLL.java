// File Name: SortLL.java

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

public class SortLL {

    /*
========================================
APPROACH : MERGE SORT ON LINKED LIST
========================================

Why Merge Sort?
---------------
- Linked List does NOT support random access like arrays.
- Merge Sort works efficiently on Linked Lists because:
    1. Splitting can be done using slow-fast pointers.
    2. Merging two sorted lists is easy using pointers.
- Time Complexity remains O(N log N).

----------------------------------------
OVERALL IDEA
----------------------------------------

1. Find the middle of the linked list.
2. Split the list into two halves.
3. Recursively sort both halves.
4. Merge the two sorted halves.
5. Return the merged sorted list.

This follows:
    Divide -> Sort -> Merge

----------------------------------------
STEP 1 : FIND MIDDLE
----------------------------------------

ListNode slow = head;
ListNode fast = head.next;

Why fast = head.next ?
----------------------
- Helps in getting the FIRST middle node.
- Important for proper splitting.
- Prevents infinite recursion.

Example:
---------
1 -> 2 -> 3 -> 4

slow stops at 2
right half starts from 3

Without this:
- slow may stop incorrectly
- split may fail

Condition:
-----------
while(fast != null && fast.next != null)

Movement:
----------
slow = slow.next       (1 step)
fast = fast.next.next  (2 steps)

----------------------------------------
STEP 2 : SPLIT THE LIST
----------------------------------------

ListNode leftHead = head;
ListNode rightHead = middle.next;
middle.next = null;

Why middle.next = null ?
------------------------
- Breaks the linked list into TWO separate lists.
- Very important step.
- Without this:
    recursion never ends
    because list remains connected.

After splitting:

Left  : head -> middle
Right : middle.next -> end

----------------------------------------
STEP 3 : RECURSIVELY SORT BOTH HALVES
----------------------------------------

leftHead = sortList(leftHead);
rightHead = sortList(rightHead);

Base Case:
-----------
if(head == null || head.next == null)
    return head;

Meaning:
---------
- Empty list is already sorted.
- Single node list is already sorted.

----------------------------------------
STEP 4 : MERGE TWO SORTED LISTS
----------------------------------------

Use dummy node technique.

Why dummy node?
----------------
- Simplifies linking.
- Avoids handling head separately.

Logic:
-------
Compare nodes from both lists:
- smaller node gets attached
- move corresponding pointer
- move temp forward

After loop:
------------
One list may still have remaining nodes.
Attach remaining nodes directly.

if(l1 != null)
    temp.next = l1;

if(l2 != null)
    temp.next = l2;

----------------------------------------
IMPORTANT TIPS TO REMEMBER
----------------------------------------

1. Always use:
        fast = head.next
    for merge sort middle finding.

2. Always disconnect lists:
        middle.next = null

3. Base case is VERY important:
        head == null || head.next == null

4. Merge step assumes:
        both halves are already sorted.

5. Dummy node makes merging easier.

6. Linked List Merge Sort is preferred over Quick Sort
    because partitioning in linked list is expensive.

----------------------------------------
TIME COMPLEXITY
----------------------------------------

Finding middle:
    O(N)

Merging:
    O(N)

Levels of recursion:
    log N

Total:
    O(N log N)

----------------------------------------
SPACE COMPLEXITY
----------------------------------------

Recursive stack:
    O(log N)

No extra array used.

----------------------------------------
FLOW VISUALIZATION
----------------------------------------

4 -> 2 -> 1 -> 3

Split:
    4 -> 2
    1 -> 3

Split again:
    4 | 2
    1 | 3

Merge:
    2 -> 4
    1 -> 3

Final Merge:
    1 -> 2 -> 3 -> 4

========================================
*/
    public static ListNode findMiddle(ListNode head){
        ListNode slow=head,fast=head.next;
        while(fast!=null && fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }
        return slow;
    }

    public static ListNode mergeTwoList(ListNode l1,ListNode l2){
        ListNode dummy = new ListNode(-1),temp=dummy;

        while(l1!=null && l2!=null){
            if(l1.val < l2.val){
                temp.next=l1;
                l1=l1.next;
            }else{
                temp.next=l2;
                l2=l2.next;
            }
            temp=temp.next;
        }
        if(l1!=null){
            temp.next=l1;
        }
        if(l2!=null){
            temp.next=l2;
        }
        return dummy.next;
    }

    public ListNode sortList(ListNode head) {

        if(head==null || head.next==null){
            return head;
        }

        ListNode middle = findMiddle(head);
        ListNode leftHead = head, rightHead = middle.next;
        middle.next=null;

        leftHead = sortList(leftHead);
        rightHead=sortList(rightHead);

        return mergeTwoList(leftHead,rightHead);
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
        4 -> 2 -> 1 -> 3

        Expected Output:
        1 -> 2 -> 3 -> 4
        */

        // Sample Input Creation
        ListNode head = new ListNode(4);
        head.next = new ListNode(2);
        head.next.next = new ListNode(1);
        head.next.next.next = new ListNode(3);

        System.out.println("Original Linked List:");
        printList(head);

        // Calling sortList function
        SortLL obj = new SortLL();
        ListNode sortedHead = obj.sortList(head);

        System.out.println("Sorted Linked List:");
        printList(sortedHead);
    }
}