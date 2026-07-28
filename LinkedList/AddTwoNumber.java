// AddTwoNumber.java

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

public class AddTwoNumber {
    /*
-----------------------------------------
INTUITION
-----------------------------------------

Each linked list stores a number in reverse order.

Example:
l1 = 2 -> 4 -> 3  represents 342
l2 = 5 -> 6 -> 4  represents 465

We add numbers exactly like elementary addition:

    digit + digit + carry

At every step:
1. Add current digits from both lists
2. Add previous carry
3. Store (sum % 10) in a new node
4. Update carry as (sum / 10)

Why modulo?
- sum % 10 gives the current digit

Why division?
- sum / 10 gives carry for next position

The loop continues until:
- both linked lists are exhausted

Finally:
- if carry still remains, create one extra node

A dummy node is used to simplify insertion.

-----------------------------------------
APPROACH
-----------------------------------------

1. Create a dummy node
    - Helps in easy result list construction

2. Create a pointer 'curr'
    - Used to build answer list

3. Create two traversal pointers
    - t1 for l1
    - t2 for l2

4. Initialize:
    carry = 0

5. Traverse while at least one list exists
    while(t1 != null || t2 != null)

6. Start sum with carry

7. If t1 exists:
    add t1.val to sum

8. If t2 exists:
    add t2.val to sum

9. Create new node with:
    sum % 10

10. Attach node to answer list

11. Update carry:
    carry = sum / 10

12. Move pointers forward

13. After loop:
    if carry != 0
    create one final node

14. Return:
    dummy.next

-----------------------------------------
TIME COMPLEXITY
-----------------------------------------

O(max(N, M))

N = length of l1
M = length of l2

-----------------------------------------
SPACE COMPLEXITY
-----------------------------------------

O(max(N, M))

(Result linked list space)
*/

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode curr = dummy;
        ListNode t1 = l1,t2=l2;

        int carry=0,sum=0;
        while(t1!=null || t2!=null){
            sum = carry;
            if(t1!=null) sum+=t1.val;
            if(t2!=null) sum+=t2.val;

            ListNode newNode = new ListNode(sum%10);
            curr.next=newNode;
            curr=curr.next;

            carry = sum/10;

            if(t1!=null) t1=t1.next;
            if(t2!=null) t2=t2.next;
        }
        if(carry!=0)
        {
            ListNode newNode = new ListNode(carry);
            curr.next=newNode;
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
            Input:
            l1 = [2,4,3]
            l2 = [5,6,4]

            Represents:
            342 + 465

            Expected Output:
            [7,0,8]

            Represents:
            807
        */

        // Create first linked list: 2 -> 4 -> 3
        ListNode l1 = new ListNode(2,new ListNode(4,new ListNode(3)));

        // Create second linked list: 5 -> 6 -> 4
        ListNode l2 = new ListNode(5,new ListNode(6,new ListNode(4)));

        AddTwoNumber obj = new AddTwoNumber();

        // Call function
        ListNode result = obj.addTwoNumbers(l1, l2);

        // Print output
        System.out.println("Result Linked List:");
        printList(result);
    }
}