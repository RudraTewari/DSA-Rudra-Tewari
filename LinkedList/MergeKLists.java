import java.util.*;

/*
 * LeetCode Style Boilerplate
 * Problem: Merge K Sorted Lists
 *
 * Input:
 * lists = [
 *   1 -> 4 -> 5,
 *   1 -> 3 -> 4,
 *   2 -> 6
 * ]
 *
 * Expected Output:
 * 1 -> 1 -> 2 -> 3 -> 4 -> 4 -> 5 -> 6
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

class Pair{
    int first;
    ListNode second;
    Pair(int first,ListNode second){
        this.first=first;
        this.second=second;
    }
}

public class MergeKLists {
/*
    APPROACH: Merge K Sorted Linked Lists using Min Heap (Priority Queue)

    Idea:
    - At any moment, the smallest node among all list heads should be chosen.
    - Use a Min Heap to efficiently get the smallest node.
    - Store (node value, node reference) in the heap.

    Steps:
    1. Handle edge case:
    - If lists array is null or empty, return null.

    2. Initialize a Min Heap:
    - Heap is ordered by node value.
    - Insert the head node of every non-empty list.

    3. Create a dummy node:
    - Helps in building the merged linked list.
    - temp pointer is used to append nodes.

    4. Process until heap becomes empty:
    - Extract the smallest node from the heap.
    - Attach it to the result list.
    - Move temp forward.
    - If the extracted node has a next node,
        insert that next node into the heap.

    5. Return:
    - dummy.next is the head of the merged list.

    Example:
    Lists:
    1->4->5
    1->3->4
    2->6

    Heap Operations:
    [1,1,2] -> take 1
    [1,2,4] -> take 1
    [2,3,4] -> take 2
    ...

    Result:
    1->1->2->3->4->4->5->6

    Time Complexity:
    - Let N = total number of nodes
    - Let K = number of linked lists
    - Each node is inserted and removed once from heap

    TC = O(N log K)

    Space Complexity:
    - Heap stores at most K nodes

    SC = O(K)
*/
    public ListNode mergeKLists(ListNode[] lists){ 
        if(lists==null || lists.length==0){
            return null;
        }
        
        PriorityQueue<Pair> minHeap = new PriorityQueue<>((a,b)->Integer.compare(a.first,b.first));
        for(int i=0;i<lists.length;i++){
            if(lists[i]!=null)
                minHeap.offer(new Pair(lists[i].val,lists[i]));
        }
        ListNode dummy = new ListNode(-1);
        ListNode temp = dummy;
        while(!minHeap.isEmpty()){
            Pair p = minHeap.peek();
            minHeap.poll();
            temp.next = p.second;
            temp=temp.next;
            if(p.second.next != null){
                minHeap.offer(new Pair((p.second.next.val),p.second.next));
            }
        }
        return dummy.next;
    }

    public static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val);

            if (head.next != null) {
                System.out.print(" -> ");
            }

            head = head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {

        // List 1: 1 -> 4 -> 5
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(5);

        // List 2: 1 -> 3 -> 4
        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(4);

        // List 3: 2 -> 6
        ListNode l3 = new ListNode(2);
        l3.next = new ListNode(6);

        ListNode[] lists = { l1, l2, l3 };

        MergeKLists solution = new MergeKLists();

        ListNode result = solution.mergeKLists(lists);

        System.out.println("Merged List:");
        printList(result);

        /*
         * Expected Output:
         * 1 -> 1 -> 2 -> 3 -> 4 -> 4 -> 5 -> 6
         */
    }
}