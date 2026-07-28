class ListNode {
    int val;
    ListNode next;

    ListNode(int val) {
        this.val = val;
        this.next = null;
    }
}

public class PalindromeLL {

    // LeetCode style function
    // public boolean isPalindrome(ListNode head) {

    //     ListNode temp=head;
    //     int count=0;
    //     while(temp!=null){
    //         count++;
    //         temp=temp.next;
    //     }
    //     int[] stack = new int[count];
    //     int top=-1;

    //     temp=head;
    //     while(temp!=null){
    //         stack[++top]=temp.val;
    //         temp=temp.next;
    //     }
    //     temp=head;
    //     while(top!=-1 && temp!=null){
    //         if(stack[top] != temp.val){
    //             return false;
    //         }
    //         top--;
    //         temp=temp.next;
    //     }
    //     return true;
    // }

/*
APPROACH : Reverse Second Half + Compare

INTUITION:
A linked list is a palindrome if:
- values from left to right
- and values from right to left
are exactly the same.

Since singly linked list cannot be traversed backward,
we reverse the second half of the list and compare both halves.

---------------------------------------------------------
STEP 1 : FIND THE MIDDLE OF THE LINKED LIST
---------------------------------------------------------

Use Slow and Fast pointer technique.

- slow moves 1 step at a time
- fast moves 2 steps at a time

When fast reaches the end:
- slow will be standing at the middle node

Why it works:
Since fast moves twice as fast,
slow only covers half the distance.

Example:
1 -> 2 -> 3 -> 2 -> 1

slow ends at:
3

---------------------------------------------------------
STEP 2 : REVERSE THE SECOND HALF
---------------------------------------------------------

Start reversing from slow node.

Use standard linked list reversal:
- prev stores reversed part
- curr traverses current nodes
- nextNode stores next pointer safely

After reversal:

Original:
1 -> 2 -> 3 -> 2 -> 1

Reversed second half:
1 -> 2 -> 3

Now prev points to the head of reversed half.

---------------------------------------------------------
STEP 3 : COMPARE BOTH HALVES
---------------------------------------------------------

Take two pointers:
- temp1 = head of original list
- temp2 = head of reversed second half

Compare values one by one:
- if values differ → not palindrome
- if all values match → palindrome

We traverse only till temp2 becomes null because:
- second half is smaller or equal in size
- for odd length lists, middle node does not affect answer

---------------------------------------------------------
KEY POINTS
---------------------------------------------------------

1. Slow/Fast pointer finds middle efficiently
2. Reverse only second half
3. Compare first half with reversed second half
4. Traverse comparison till second half ends
5. No extra space used
*/
    public boolean isPalindrome(ListNode head) {
        ListNode fast = head;
        ListNode slow=head;

        while(fast!=null && fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }

        ListNode prev=null;
        ListNode curr=slow;
        while(curr != null){
            ListNode newNode = curr.next;
            curr.next=prev;
            prev=curr;
            curr=newNode;
        }

        ListNode temp1=head;
        ListNode temp2 = prev;
        while(temp2!=null){
            if(temp1.val != temp2.val){
                return false;
            }
            temp1=temp1.next;
            temp2=temp2.next;
        }
        return true;
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
            Sample Input:
            1 -> 2 -> 2 -> 1

            Expected Output:
            true
        */

        // Creating linked list
        // ListNode head = new ListNode(1);
        // head.next = new ListNode(2);
        // head.next.next = new ListNode(2);
        // head.next.next.next = new ListNode(1);

        /*
        Sample Input:
        1 -> 2 -> 3 -> 4

        Expected Output:
        false
        */

        // Creating linked list
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);

        // Printing input list
        System.out.print("Linked List: ");
        printList(head);

        // Object creation
        PalindromeLL obj = new PalindromeLL();

        // Function call
        boolean result = obj.isPalindrome(head);

        // Printing output
        System.out.println("Is Palindrome: " + result);
    }
}