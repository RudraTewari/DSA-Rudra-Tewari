class Node{
    int val;
    Node prev,next;
    Node(int val){
        this.val=val;
        this.prev=prev;
        this.next=next;
    }
}

class DoublyLL{
    static Node head;

    public void insertFirst(int val){
        Node newNode= new Node(val);
        if(head==null){
            head=newNode;
            return;
        }
        newNode.next=head;
        head.prev=newNode;
        head=newNode;
    }

    public void insertEnd(int val){
        
        Node newNode = new Node(val);
        if(head==null){
            head=newNode;
            return;
        }
        Node tail=head;
        while(tail.next!=null){
            tail=tail.next;
        }
        
        tail.next=newNode;
        newNode.prev=tail;
    }

    public void deleteFirst(){
        if(head==null){
            System.out.println("No node to delete");
            return;
        }
        System.out.println(head.val);
        head=head.next;
        head.prev=null;
    }

    public void deleteEnd(){
        if(head==null){
            System.out.println("No node to delete");
            return;
        }
        Node tail=head;
        while(tail.next!=null){
            tail=tail.next;
        }

        System.out.println(tail.val);
        tail=tail.prev;
        tail.next=null;
    }


    public static void displayFromFront(Node head){
        if(head==null)
        {
            System.out.println("No node to display");
            return;
        }
        Node temp=head;
        while(temp!=null){
            System.out.print(temp.val+" < = > ");
            temp=temp.next;
        }
        System.out.println("NULL");
    }

    public static void displayFromRear(Node head){
        if(head==null)
        {
            System.out.println("No node to display");
            return;
        }

        Node tail=head;
        while(tail.next!=null){
            tail=tail.next;
        }
        Node temp=tail;
        while(temp!=null){
            System.out.print(temp.val+" < = > ");
            temp=temp.prev;
        }
        System.out.println("NULL");
    }


    public static void main(String[] args){
        DoublyLL obj = new DoublyLL();
        obj.insertFirst(4);
        obj.insertFirst(5);
        obj.insertFirst(6);

        obj.insertEnd(7);
        obj.insertEnd(8);
        obj.insertEnd(9);


        displayFromFront(head);
        // displayFromRear(head);

        obj.deleteFirst();
        obj.deleteFirst();

        displayFromFront(head);
        // displayFromRear(head);

        obj.deleteEnd();
        obj.deleteEnd();

        displayFromFront(head);
        // displayFromRear(head);

        
    }
}