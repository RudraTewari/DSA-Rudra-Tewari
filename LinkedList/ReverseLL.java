
class ListNode{
    int val;
    ListNode next;

    ListNode(int val){
        this.val =val;
        this.next=null;
    }
}

class ReverseLL{
    static ListNode head;
    public void display(ListNode head){
        if(head == null){
            System.out.println("Linked List Empty");
            return;
        }
        ListNode temp = head;
        while(temp != null){
            System.out.print(temp.val+" -> ");
            temp=temp.next;
        }
        System.out.println("END");
    }
    public void insertFirst(int val){
        ListNode newNode = new ListNode(val);
        newNode.next=head;
        head=newNode;
    }

    public ListNode reverseList(ListNode head){
        ListNode prev=null;
        ListNode curr=head;

        while(curr != null){
            ListNode nextNode = curr.next;
            curr.next = prev;
            prev = curr;
            curr=nextNode;
        }
        return prev;
    }
    public static void main(String[] args){
        ReverseLL listnode = new ReverseLL();
        listnode.insertFirst(5);
        listnode.insertFirst(4);
        listnode.insertFirst(3);
        listnode.insertFirst(2);
        listnode.insertFirst(1);

        ListNode ans = listnode.reverseList(head);
        listnode.display(ans);
    }
}