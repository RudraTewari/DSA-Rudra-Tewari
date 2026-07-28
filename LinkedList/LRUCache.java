import java.util.*;

/*
 * LeetCode Style LRU Cache Boilerplate
 *
 * Input:
 * capacity = 2
 * operations:
 * put(1, 1)
 * put(2, 2)
 * get(1)
 * put(3, 3)
 * get(2)
 *
 * Expected Output:
 * 1
 * -1
 */

class Node {
    int key;
    int value;
    Node prev;
    Node next;

    Node(int key, int value) {
        this.key = key;
        this.value = value;
    }
}
/*
=========================================
LRU CACHE (HashMap + Doubly Linked List)
=========================================

INTUITION:
----------
Need O(1) for both:
1. get(key)
2. put(key, value)

HashMap:
--------
key -> node address

Doubly Linked List:
-------------------
Maintain usage order.

head <-> Most Recently Used (MRU)
tail <-> Least Recently Used (LRU)

Dummy Nodes:
------------
head <-> ... <-> tail

head.next  = most recently used node
tail.prev  = least recently used node

------------------------------------------------

OPERATIONS:

1) GET(key)
-----------
Case 1:
key not present
return -1

Case 2:
key present

node = map.get(key)

Since accessed recently:
    remove node from current position
    insert node after head

return node.val

------------------------------------------------

2) PUT(key, value)
------------------

Case 1:
Key already exists

node = map.get(key)

remove node from DLL
update node value
insert node after head

Reason:
Updated node becomes most recently used.

------------------------------------------------

Case 2:
Key does not exist

If cache full:
--------------
LRU node = tail.prev

remove LRU node from map
remove LRU node from DLL

Create new node
put into map
insert after head

------------------------------------------------

HELPER FUNCTIONS

deleteNode(node)
----------------
prevNode = node.prev
nextNode = node.next

prevNode.next = nextNode
nextNode.prev = prevNode

Purpose:
Remove node from DLL in O(1)

------------------------------------------------

insertFirst(node)
-----------------
Insert immediately after head

head <-> node <-> oldFirst

Purpose:
Mark node as most recently used.

------------------------------------------------

WHY tail.prev IS REMOVED?
-------------------------
tail.prev always points to

Least Recently Used Node

because:
Every access/update moves node
to front (after head).

------------------------------------------------

EXAMPLE

Capacity = 2

put(1,1)

head <-> 1 <-> tail

put(2,2)

head <-> 2 <-> 1 <-> tail

get(1)

head <-> 1 <-> 2 <-> tail

put(3,3)

Cache Full

Remove tail.prev => 2

head <-> 1 <-> tail

Insert 3

head <-> 3 <-> 1 <-> tail

------------------------------------------------

TIME COMPLEXITY
---------------
get()  : O(1)
put()  : O(1)

SPACE COMPLEXITY
----------------
O(capacity)

------------------------------------------------

REVISION KEYWORDS
-----------------
HashMap + DLL
Map stores address of node
Head = MRU side
Tail = LRU side
Access => Move to front
Update => Move to front
Full cache => Remove tail.prev
All operations O(1)

=========================================
*/
public class LRUCache {

    private int capacity;
    private Map<Integer, Node> cache;
    private Node head;
    private Node tail;

    public LRUCache(int capacity) {
        // Initialization logic
    }

    public int get(int key) {
        // TODO: Implement get logic
        return 0;
    }

    public void put(int key, int value) {
        // TODO: Implement put logic
    }

    private void addNode(Node node) {
        // TODO: Implement add node logic
    }

    private void removeNode(Node node) {
        // TODO: Implement remove node logic
    }

    private void moveToHead(Node node) {
        // TODO: Implement move to head logic
    }

    private Node removeTail() {
        // TODO: Implement remove tail logic
        return null;
    }

    public static void main(String[] args) {

        // Sample Input
        LRUCache lruCache = new LRUCache(3);

        /*
        Capacity = 3

        put(1,10)
        Cache: [1]

        put(2,20)
        Cache: [2,1]

        put(3,30)
        Cache: [3,2,1]

        get(1) -> 10
        Cache: [1,3,2]

        put(4,40)
        Evicts 2
        Cache: [4,1,3]

        get(2) -> -1

        put(3,300)
        Update existing key
        Cache: [3,4,1]

        put(5,50)
        Evicts 1
        Cache: [5,3,4]

        get(1) -> -1

        get(3) -> 300
        Cache: [3,5,4]

        put(6,60)
        Evicts 4
        Cache: [6,3,5]

        get(4) -> -1

        get(5) -> 50
        Cache: [5,6,3]

        get(6) -> 60
        Cache: [6,5,3]
        */

        lruCache.put(1, 10);
        lruCache.put(2, 20);
        lruCache.put(3, 30);

        System.out.println(lruCache.get(1)); // 10

        lruCache.put(4, 40);

        System.out.println(lruCache.get(2)); // -1

        lruCache.put(3, 300);

        lruCache.put(5, 50);

        System.out.println(lruCache.get(1)); // -1
        System.out.println(lruCache.get(3)); // 300

        lruCache.put(6, 60);

        System.out.println(lruCache.get(4)); // -1
        System.out.println(lruCache.get(5)); // 50
        System.out.println(lruCache.get(6)); // 60
    }
}