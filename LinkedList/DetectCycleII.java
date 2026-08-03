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

public class DetectCycleII {

    /*
        Function to detect the starting node of the cycle
        Return:
        - Starting node of cycle if cycle exists
        - null if no cycle exists
    */
    // public static ListNode detectCycle(ListNode head) {

    //     Map<ListNode,Integer> seen = new HashMap<>();
    //     ListNode temp=head;
    //     while(temp!=null){
    //         if(seen.containsKey(temp)==true) return temp;

    //         seen.put(temp,1);
    //         temp=temp.next;
    //     }

    //     return null;
    // }

    /*
    Floyd’s Cycle Detection + Cycle Start Finding Algorithm

    Step 1: Detect whether a cycle exists
    -------------------------------------
    - Use two pointers:
        slow -> moves 1 step at a time
        fast -> moves 2 steps at a time

    - If there is no cycle:
        fast will eventually reach null.

    - If there is a cycle:
        fast and slow must meet at some node
        inside the cycle because fast moves
        quicker and laps slow.

    ------------------------------------------------

    Step 2: Find the starting node of the cycle
    -------------------------------------------
    - After slow and fast meet, move slow back to head.

    - Now:
        slow starts from head
        fast stays at meeting point

    - Move both one step at a time.

    - The node where they meet again is the starting point of the cycle.

    ------------------------------------------------

    Why does this work?
    -------------------
    Let:
        D = distance from head to cycle start
        M = distance from cycle start to meeting point
        C = cycle length

    When pointers first meet:
        distance traveled by fast
        = 2 × distance traveled by slow

    This mathematical relation guarantees that:
        if one pointer starts from head
        and another from meeting point,
        moving both one step at a time,
        they will meet exactly at the
        cycle starting node.

    ------------------------------------------------

    Time Complexity  : O(n)
    Space Complexity : O(1)
*/
    public static ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while(fast!=null && fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
            if(slow==fast){
                slow=head;
                while(slow!=fast){
                    slow=slow.next;
                    fast=fast.next;
                }
                return slow;
            }
        }
        return null;
    }

    // Function to print result
    public static void printResult(ListNode node) {
        if (node == null) {
            System.out.println("No Cycle Detected");
        } else {
            System.out.println("Cycle starts at node with value: " + node.val);
        }
    }

    public static void main(String[] args) {

        /*
            Sample Input:

            3 -> 2 -> 0 -> -4
                 ^         |
                 |_________|

            Tail connects to node with value 2

            Expected Output:
            Cycle starts at node with value: 2
        */

        // Creating nodes
        ListNode head = new ListNode(3);
        ListNode second = new ListNode(2);
        ListNode third = new ListNode(0);
        ListNode fourth = new ListNode(-4);

        // Connecting nodes
        head.next = second;
        second.next = third;
        third.next = fourth;

        // Creating cycle
        fourth.next = second;

        // Function call
        ListNode result = detectCycle(head);

        // Printing output
        printResult(result);
    }
}