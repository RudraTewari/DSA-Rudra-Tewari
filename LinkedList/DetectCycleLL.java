// import java.util.Map;
// import java.util.HashMap;

class ListNode {
    int val;
    ListNode next;

    ListNode(int val) {
        this.val = val;
        this.next = null;
    }
}

public class DetectCycleLL {
    /*
    APPROACH:

    1. Create a HashMap to store visited nodes.
    - Key   -> ListNode
    - Value -> Boolean (or anything)

    2. Start traversing the linked list using a temp pointer.

    3. For every node:
    - Check if the current node already exists in the HashMap.
    - If YES:
        -> It means we have already visited this node before.
        -> Therefore, a cycle exists.
        -> Return true.

    4. If the node is not present:
    - Store the node inside the HashMap.
    - Move temp to the next node.

    5. If traversal reaches null:
    - No node was repeated.
    - Therefore, no cycle exists.
    - Return false.

*/

    /*
    public static boolean hasCycle(ListNode head) {
        Map<ListNode,Boolean> seen = new HashMap<>();
        ListNode temp = head;
        while(temp!=null){
            if(seen.containsKey(temp)==true){
                return true;
            }
            seen.put(temp,true);
            temp=temp.next;
        }
        return false;
    }*/

/*
    APPROACH (Floyd's Cycle Detection Algorithm / Fast & Slow Pointer):

    1. Create two pointers:
    - slow -> moves one step at a time
    - fast -> moves two steps at a time

    2. Initialize both pointers at head.

    3. Traverse the linked list while:
    - fast is not null
    - fast.next is not null

    4. In every iteration:
    - Move slow by 1 node
    - Move fast by 2 nodes

    5. Check if fast and slow become equal:
    - If they meet at the same node,
        it means a cycle exists.
    - Return true.

    6. If fast reaches null or fast.next becomes null:
    - The linked list ends normally.
    - Therefore, no cycle exists.
    - Return false.

    WHY THIS WORKS:
    - In a cyclic linked list,
    the fast pointer eventually catches
    the slow pointer inside the cycle.

*/
    public static boolean hasCycle(ListNode head){
        ListNode fast = head;
        ListNode slow = head;

        while(fast != null && fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
            if(fast==slow){
                return true;
            }
        }
        return false;
    }


    public static void display(ListNode head, int limit) {
        ListNode temp = head;
        int count = 0;

        while (temp != null && count < limit) {
            System.out.print(temp.val + " -> ");
            temp = temp.next;
            count++;
        }

        System.out.println("null");
    }

    public static void main(String[] args) {

        /*
            Sample Input:

            1 -> 2 -> 3 -> 4 -> 5
                      ^         |
                      |_________|

            Cycle exists
        */

        // Creating nodes
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        head.next.next.next.next.next = head.next;

        boolean ans = hasCycle(head);

        System.out.println("Cycle Present: " + ans);

        
    }
}