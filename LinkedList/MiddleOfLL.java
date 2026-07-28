class ListNode {
    int val;
    ListNode next;

    ListNode(int val) {
        this.val = val;
        this.next = null;
    }
}

class MiddleOfLL {

    ListNode head;

    public void insert(int val) {
        ListNode newNode = new ListNode(val);

        if (head == null) {
            head = newNode;
            return;
        }

        ListNode temp = head;

        while (temp.next != null) {
            temp = temp.next;
        }

        temp.next = newNode;
    }

    public void display() {
        ListNode temp = head;

        while (temp != null) {
            System.out.print(temp.val + " -> ");
            temp = temp.next;
        }

        System.out.println("null");
    }

/*
        slow moves 1 step at a time
        fast moves 2 steps at a time

        When fast reaches the end of the linked list,
        slow will be standing at the middle node.

        Example:
        1 -> 2 -> 3 -> 4 -> 5

        Iteration 1:
        slow = 2
        fast = 3

        Iteration 2:
        slow = 3
        fast = 5

        fast cannot move further,
        so slow is the middle node.
*/
    public ListNode middleNode() {

        ListNode slow=head;
        ListNode fast=head;

        while(fast != null && fast.next != null){
            slow=slow.next;
            fast=fast.next.next;
        }
        return slow;
    }

    public static void main(String[] args) {

        MiddleOfLL list = new MiddleOfLL();

        // Odd length
        // list.insert(1);
        // list.insert(2);
        // list.insert(3);
        // list.insert(4);
        // list.insert(5);

        // list.display();

        //Even Length
        list.insert(1);
        list.insert(2);
        list.insert(3);
        list.insert(4);
        list.insert(5);
        list.insert(6);

        list.display();

        ListNode middle = list.middleNode();

        System.out.println("Middle Node: " + middle.val);
    }
}