package task2Two;




public class DoubleLinkedList<T> {
    private static int size;
    private Node<T> first;
    private Node<T> last;

    private static class Node<T> {
        private T data;
        private Node<T> pri;
        private Node<T> next;
        public Node() { }
        public Node(T data) {
            this.data = data;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public Node<T> getPri() {
            return pri;
        }

        public void setPri(Node<T> pri) {
            this.pri = pri;
        }

        public Node<T> getNext() {
            return next;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }

    }

    public static int getSize() {
        return size;
    }


    public boolean addFirst(T value){
        Node<T> node = new Node<>(value);
        if (size == 0) {
            first = node;
            last = first;
        }else {
            node.next = first;
            first.pri = node;
            first = node;
        }
        size++;
        return true;
    }

    public boolean addLast(T value){
        if (size == 0){
            return addFirst(value);
        }else {
            Node<T> node = new Node<>(value);
            last.next = node;
            node.pri = last;
            last = node;
        }
        size++;
        return true;
    }

    public boolean add(int index,T value){
        if (index < 0 || index > size){
            throw new IndexOutOfBoundsException("数据下标越界 Index:" + index + "\tsize:" + size);
        } else if(index == 0){
            return addFirst(value);
        }else if (index == size){
            return addLast(value);
        }else {
            Node<T> node = new Node<>(value);
            Node<T> head = first;
            for (int i = 0; i < index-1; i++) {
                head = head.getNext();
            }
            node.next = head.next;
            head.next = node;
            node.pri = head;
            node.next.pri = node;
        }
        size++;
        return true;
    }


    public T removeFirst(){
        if (size == 0){
            throw new RuntimeException("链表为空！");
        }
        T data = first.getData();
        Node<T> node = first.next;
        node.setPri(null);
        first = node;
        return data;
    }


    public T removeLast(){
        if (size == 0){
            throw new RuntimeException("链表为空！");
        }
        T data = last.getData();
        Node<T> node = last.pri;
        node.setNext(null);
        last = node;
        return data;
    }


    public T remove(int index){
        if (size == 0){
            throw new RuntimeException("链表为空！");
        }
        //注意添加的时候，下标取不到size，但是添加的位置可以是size，但是删除的时候不行
        if (index < 0 || index > size-1){
            throw new IndexOutOfBoundsException("数据下标越界 Index:" + index + "\tsize:" + size);
        } else if(index == 0){
            return removeFirst();
        }else {
            Node<T> node = first;
            for (int i = 0; i < index - 1; i++) {
                node = node.next;
            }
            Node<T> temp = node.next;
            if (temp != last){
                T data = temp.getData();
                node.next = temp.next;
                temp.next.pri = node;
                temp.setNext(null);
                return data;
            }else {
                return removeLast();
            }
        }
    }


    public T getData(int index){
        if (index<0 || index>size-1){
            throw new IndexOutOfBoundsException("数据下标越界 Index:" + index + "\tsize:" + size);
        }else if (size == 0){
            throw new RuntimeException("链表为空");
        }else if (size == 1){
            return first.data;
        }else {
            Node<T> node = first;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
            return node.data;
        }
    }


    public void clear(){
        first = null;
        last = null;
    }


    public void print(){
        if (size == 0) {
            System.out.println("该链表为空!");
        }
        Node<T> node = first;
        while (node != null) {
            System.out.print(node.getData() + "\t");
            node = node.next;
        }
        System.out.println();
    }


    public void detailPrint(){
        if (size == 0) {
            System.out.println("该链表为空!");
        }
        Node<T> node = first;
        while (node != null) {
            System.out.print("前驱节点值：");
            System.out.printf("%-5s",node.pri == null ? "null\t" : node.pri.getData()+"\t");
            System.out.print("当前节点值：");
            System.out.printf("%-6s",node.getData() + "\t");
            System.out.print("后继节点值：");
            System.out.printf("%-5s",node.next == null ? "null\t" : node.next.getData()+"\t");
            System.out.println();
            node = node.getNext();
        }
        System.out.println();
    }
    public void reverse(){
        if (size == 0){
            throw new RuntimeException("链表为空！");
        }
        //定义临时节点指针，初始化值为null
        Node<T> temp=null;
        //定义当前节点指针，初始化指向head节点
        Node<T> current=first;
        //定义tail指针，初始化指向head
       last=first;
        while (current!=null){
            //temp指向当前节点的前一个节点（初始时head的前一个节点为null）
            temp=current.pri;
            //当前节点的前向指针指向当前节点的下一个节点
            current.pri=current.next;
            //当前节点的next指针指向临时节点指针（temp初始时为null）
            current.next=temp;
            //当前节点指针current指向下一个节点（往后移动）
            current=current.pri;
        }
        //循环结束后temp指向第二个节点，利用temp的前向指针（pre）指向第一个节点，将head指向反转后链表的第一个节点
        if(temp!=null){
            first=temp.pri;
        }
    }









}
