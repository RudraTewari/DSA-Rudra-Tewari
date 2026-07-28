import java.util.*;


class Node {
    int val;
    Node next;
    Node random;

    Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
/*

    APPROACH : HASHMAP + TWO PASS COPYING

    INTUITION:
    We cannot directly copy the linked list because each node
    contains two pointers:

    1. next   -> normal linked list connection
    2. random -> can point anywhere in the list

    While copying, we need to preserve both relationships.

    So we use a HashMap:

        Original Node  --->  Copied Node

    This mapping helps us quickly find the copied version
    of any original node.


    ---------------------------------------------------------
    STEP 1 : CREATE COPY NODES
    ---------------------------------------------------------

    Traverse the original linked list.

    For every original node:
        - create a new copy node
        - store mapping in HashMap

    Example:

    Original:
    A -> B -> C

    HashMap:
    A -> A'
    B -> B'
    C -> C'

    At this stage:
    Only nodes are created.
    No next/random connections yet.


    ---------------------------------------------------------
    STEP 2 : CONNECT NEXT AND RANDOM POINTERS
    ---------------------------------------------------------

    Traverse the original list again.

    For every original node:

        copy.next =
            copied version of curr.next

        copy.random =
            copied version of curr.random

    Using HashMap:

        oldToCopy.get(curr.next)
        oldToCopy.get(curr.random)

    This correctly rebuilds all links in copied list.


    ---------------------------------------------------------
    WHY HASHMAP WORKS
    ---------------------------------------------------------

    Suppose:

    curr.random points to some node X.

    We need X's copied node.

    HashMap instantly gives:

        oldToCopy.get(X)

    So random pointer structure is preserved perfectly.


    ---------------------------------------------------------
    EDGE CASE
    ---------------------------------------------------------

    If head == null:
        return null


    ---------------------------------------------------------
    TIME COMPLEXITY
    ---------------------------------------------------------

    First traversal  -> O(n)
    Second traversal -> O(n)

    Total:
    O(n)


    ---------------------------------------------------------
    SPACE COMPLEXITY
    ---------------------------------------------------------

    HashMap stores one entry per node.

    O(n)

*/
public class CopyListWithRandomPointer {

    public Node copyRandomList(Node head) {
        if(head == null)
            return null;
        
        Map<Node,Node> oldToCopy = new HashMap<>();
        Node curr = head;
        while(curr!= null){
            Node copy = new Node(curr.val);
            oldToCopy.put(curr,copy);
            curr=curr.next;
        }

        curr=head;
        while(curr!=null){
            Node copy = oldToCopy.get(curr);
            copy.next = oldToCopy.get(curr.next);
            copy.random = oldToCopy.get(curr.random);
            curr = curr.next;
        }

        return oldToCopy.get(head);
    }

    // Utility method to print linked list with random pointers
    public void printList(Node head) {

        Node temp = head;

        while (temp != null) {

            int randomVal = (temp.random != null) ? temp.random.val : -1;

            System.out.println(
                "Node Value: " + temp.val +
                ", Random Points To: " + randomVal
            );

            temp = temp.next;
        }
    }

    public static void main(String[] args) {

        /*
            Sample Input:

            Node1 -> Node2 -> Node3

            Random Connections:
            Node1.random -> Node3
            Node2.random -> Node1
            Node3.random -> Node2

            Expected Output:
            Deep copied linked list with same next
            and random pointer structure.
        */

        // Creating nodes
        Node node1 = new Node(7);
        Node node2 = new Node(13);
        Node node3 = new Node(11);

        // Connecting next pointers
        node1.next = node2;
        node2.next = node3;

        // Connecting random pointers
        node1.random = node3;
        node2.random = node1;
        node3.random = node2;

        // Head of original list
        Node head = node1;

        CopyListWithRandomPointer obj = new CopyListWithRandomPointer();

        System.out.println("Original List:");
        obj.printList(head);

        // Copy list
        Node copiedHead = obj.copyRandomList(head);

        System.out.println("\nCopied List:");
        obj.printList(copiedHead);
    }
}