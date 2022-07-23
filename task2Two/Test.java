package task2Two;

public class Test {
    public static void main(String[] args) {
        DoubleLinkedList<Integer> temp= new DoubleLinkedList<>();
        temp.addFirst(10);
        temp.addFirst(20);
        temp.addFirst(30);
        temp.addFirst(40);
        temp.addFirst(50);
        temp.addLast(5);
        temp.print();
        try{
            temp.add(3,44);
        }catch (IndexOutOfBoundsException e){
            System.out.println(e);
        }
        temp.print();

        try {
            temp.removeFirst();
        }catch (RuntimeException e){
            System.out.println(e);
        }
        temp.print();
        try {
            temp.removeLast();
        }catch (RuntimeException e){
            System.out.println(e);
        }
        temp.print();
        try {
            temp.remove(2);
        }catch (IndexOutOfBoundsException e){
            System.out.println(e);
        }
        temp.print();
        try {
            temp.getData(2);
        }catch (IndexOutOfBoundsException e){
            System.out.println(e);
        }
        temp.print();
        try {
            temp.reverse();
        }catch (RuntimeException e){
            System.out.println(e);

        }
        temp.print();
        temp.clear();
        temp.print();



    }
}
