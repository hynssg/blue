package task2One;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        String string;
        System.out.println("输入一个算术式：");
        Scanner scanner=new Scanner(System.in);
        string=scanner.next();
        new Calculator().math(string);
    }



    }


