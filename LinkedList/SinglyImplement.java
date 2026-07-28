
class LinkList{
    ListNode head;
    class ListNode{
        private int val;
        private ListNode next;
        ListNode(int val){
            this.val=val;
            this.next=null;
        }
    }

    public void insertFirst(int val){
        ListNode newNode = new ListNode(val);
        newNode.next=head;
        head=newNode;
    }

    public void insertLast(int val){
        ListNode newNode = new ListNode(val);
        if(head == null){
            head=newNode;
            return;
        }
        ListNode temp = head;
        while(temp.next != null){
            temp=temp.next;
        }        
        temp.next = newNode;
    }

    public void insertAtPos(int val, int pos){
        ListNode newNode = new ListNode(val);
        if(pos == 0){
            newNode.next=head;
            head= newNode;
            return;
        }
        ListNode temp=head;
        for(int i=1;i<pos-1;i++){
            if(temp==null){
                System.out.println("Position Out Of Bounds");
                return;
            }
            temp=temp.next;
        }
        newNode.next=temp.next;
        temp.next=newNode;      
    }

    public void deleteFirst(){
        if(head==null){
            System.out.println("No node to delete");
            return;
        }
        System.out.println(head.val);
        head = head.next;
    }

    public void deleteLast(){
        if(head == null){
            System.out.println("No node to delete");
            return;
        }
        ListNode prev = head;
        ListNode curr = head.next;
        while(curr.next!=null){
            prev = curr;
            curr=curr.next;
        }
        System.out.println("Delete Node : "+curr.val);
        prev.next=null;
    }

    public void deleteAtPos(int pos){
        if(head==null){
            System.out.println("No node to delete");
            return;
        }
        ListNode prev = head;
        ListNode curr = head.next;
        for(int i=1;i<pos-1;i++){
            if(curr==null){
                System.out.println("Position Out Of Bounds");
                return;
            }
            prev=curr;
            curr=curr.next;
        }
        System.out.println("Delete Node : "+curr.val);
        prev.next=curr.next;
    }

    public void display(){
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
}

class SinglyImplement{
    public static void main(String[] args){
        LinkList ll = new LinkList();
        ll.insertLast(5);
        ll.insertLast(6);
        ll.insertLast(7);
        ll.insertLast(8);
        ll.insertLast(9);
        ll.insertAtPos(10,3);
        ll.display();

        // ll.deleteFirst();
        // ll.display();

        // ll.deleteLast();
        // ll.display();

        ll.deleteAtPos(3);
        ll.display();
    }
}