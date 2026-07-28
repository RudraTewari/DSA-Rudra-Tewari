
class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

public class MergeSortedLL {

    // Function Definition
    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy=new ListNode(-1);
        ListNode temp = dummy;
        

        while(list1!=null && list2!=null){
            if(list1.val <list2.val){
                temp.next=list1;
                list1=list1.next;
            }else{
                temp.next=list2;
                list2=list2.next;
            }
            temp=temp.next;
        }
        if(list1!=null){
            temp.next=list1;
        }
        if(list2!=null){
            temp.next=list2;
        }
        return dummy.next;
    }

    public static void display(ListNode head) {

        if (head == null) {
            System.out.println("Linked List Empty");
            return;
        }

        ListNode temp = head;

        while (temp != null) {
            System.out.print(temp.val + " -> ");
            temp = temp.next;
        }

        System.out.println("null");
    }

    public static void main(String[] args) {

        // First Sorted Linked List
        ListNode list1 = new ListNode(1);
        list1.next = new ListNode(3);
        list1.next.next = new ListNode(5);

        System.out.print("List 1: ");
        display(list1);

        // Second Sorted Linked List
        ListNode list2 = new ListNode(2);
        list2.next = new ListNode(4);
        list2.next.next = new ListNode(6);

        System.out.print("List 2: ");
        display(list2);

        // Function Call
        ListNode mergedHead = mergeTwoLists(list1, list2);

        // Print Answer
        display(mergedHead);
    }
}