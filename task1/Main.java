package task1;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BookLost a1=new BookLost(10,"java");
        BookLost a2=new BookLost(1,"c");
        BookLost a3=new BookLost(7,"javascript");
        BookLost a4=new BookLost(5,"lin");

        CardLost c1=new CardLost(7,"vip");
        CardLost c2=new CardLost(8,"ssr");
        CardLost c3=new CardLost(55,"s");
        CardLost c4=new CardLost(77,"gold card");
        CardLost c5=new CardLost(4,"sVip");
        Lost[] lostArray={a1,a2,a4,a3,c1,c2,c4,c5,c3};
        new Solution().sortLost(lostArray);
        Scanner scanner=new Scanner(System.in);
        String key=scanner.next();
        Lost []END =new Solution().selectByKeyword(lostArray,key);
        System.out.println(Arrays.toString(END));



    }
}
