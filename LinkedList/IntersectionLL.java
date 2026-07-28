// File Name: IntersectionLL.java
import java.util.Map;
import java.util.HashMap;
class ListNode {
    int val;
    ListNode next;

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

public class IntersectionLL {

    // Function to find intersection node of two linked lists
    /*
LOGIC:
1. Create a HashMap to store all nodes of Linked List A.
2. Traverse List A:
    - Store each node reference as key in the map.
3. Traverse List B:
    - For every node, check whether it already exists in the map.
    - If found, that node is the intersection node.
4. Return the intersecting node.
5. If no common node is found, return null.

WHY IT WORKS:
- Intersection in linked list means both lists share the SAME NODE ADDRESS,
    not just same value.
- HashMap stores node references, so checking containsKey(tempB)
    checks whether both lists point to the exact same node.

TIME COMPLEXITY:
- O(N + M)
    N = length of List A
    M = length of List B

SPACE COMPLEXITY:
- O(N)

DEMERITS:
1. Extra Space Usage:
        - Uses HashMap which requires additional memory.

2. Not Optimal:
        - Better solutions exist with O(1) space
        using two-pointer technique.

3. More Memory Consumption:
        - For very large linked lists,
        memory usage increases significantly.

4. Slightly Slower Due to Hashing:
        - HashMap operations involve hashing overhead.
*/
    // public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {

    //     Map<ListNode,Integer> mapA=new HashMap<>();

    //     ListNode tempA=headA,tempB=headB;
    //     while(tempA!=null){
    //         mapA.put(tempA,1);
    //         tempA=tempA.next;
    //     }

    //     while(tempB!=null){
    //         if(mapA.containsKey(tempB)){
    //             return tempB;
    //         }
    //         tempB=tempB.next;
    //     }

    //     return null;
    // }

    /*
    INTUITION:
    - If two linked lists intersect, then after the intersection point
    both lists share the exact same nodes.
    - The problem is that both lists may have different lengths,
    so their starting positions are misaligned.
    - To compare nodes fairly, we first align both lists so that
    both pointers have equal number of nodes left to travel.

    APPROACH:
    1. Find Length of Both Lists
        - Traverse List A and count nodes -> cntA
        - Traverse List B and count nodes -> cntB

    2. Calculate Length Difference
        - Find absolute difference between cntA and cntB.

    3. Move Pointer of Longer List
        - If List A is longer:
                move tempA forward by (cntA - cntB) nodes.
        - If List B is longer:
                move tempB forward by (cntB - cntA) nodes.

    WHY?
        - After skipping extra nodes,
            both pointers now have equal distance left till the end.

    4. Traverse Both Lists Together
        - Move tempA and tempB one step at a time.
        - If tempA == tempB:
                intersection node found.
        - Return that node.

    5. If No Intersection Exists
        - Both pointers reach null.
        - Return null.

    WHY IT WORKS:
    - Once lengths are aligned,
    both pointers enter the shared part at the same time.
    - Since intersection means same memory reference,
    comparing using (tempA == tempB) correctly identifies intersection.

    TIME COMPLEXITY:
    - O(N + M)
    N = length of List A
    M = length of List B

    SPACE COMPLEXITY:
    - O(1)
    No extra data structure used.

    ADVANTAGE:
    - Better than HashMap approach because it uses constant space.
    - Efficient and optimal solution.
*/
    public static ListNode getIntersectionNode(ListNode headA,ListNode headB){
        
        ListNode tempA=headA,tempB=headB;
        int cntA=0,cntB=0;
        while(tempA!=null){
            cntA++;
            tempA=tempA.next;
        }
        while(tempB!=null){
            cntB++;
            tempB=tempB.next;
        }

        int skipCount=0;
        // Finding the number of nodes to skip in longer list
        if(cntA>cntB){
            skipCount=cntA-cntB;
            tempA=headA;
            while(skipCount!=0){
                skipCount--;
                tempA=tempA.next;
            }
            tempB=headB; // Initialize tempB to headB for next while
        }else if(cntB > cntA){
            skipCount=cntB-cntA;
            tempB=headB;
            while(skipCount!=0){
                skipCount--;
                tempB=tempB.next;
            }
            tempA=headA; // Initialize tempA to headA for next while
        }


        while(tempA!=null && tempB!=null){
            if(tempA==tempB)
                return tempA;

            tempA=tempA.next;
            tempB=tempB.next;
        }
        return null;
    }

    public static void main(String[] args) {

        /*
            Example:

            List A: 4 -> 1 \
                                        8 -> 4 -> 5
            List B:      5 -> 6 -> 1 /

            Expected Output:
            8
        */

        // Common intersecting part
        ListNode common = new ListNode(8);
        common.next = new ListNode(4);
        common.next.next = new ListNode(5);

        // List A
        ListNode headA = new ListNode(4);
        headA.next = new ListNode(1);
        headA.next.next = common;

        // List B
        ListNode headB = new ListNode(5);
        headB.next = new ListNode(6);
        headB.next.next = new ListNode(1);
        headB.next.next.next = common;

        // Call function
        ListNode intersectionNode = getIntersectionNode(headA, headB);

        // Print output
        if (intersectionNode != null) {
            System.out.println("Intersection Node Value: " + intersectionNode.val);
        } else {
            System.out.println("No Intersection Found");
        }
    }
}