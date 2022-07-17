package task1;

import java.util.Arrays;

class Solution{
        CardLost temp=new CardLost();
        BookLost example=new BookLost();
       public Lost  []end=null;


        /**
         * 失物排序方法
         * @param lostArray 待排序的失物数组
         */
        public  void sortLost(Lost[] lostArray){
                Lost temp;
                        for (int i = 0; i < lostArray.length - 1; i++) {
                                for (int j = 0; j < lostArray.length - 1 - i; j++) {
                                        if (lostArray[j].time > lostArray[j + 1].time) {
                                               temp  = lostArray[j];
                                                lostArray[j] = lostArray[j + 1];
                                                lostArray[j + 1] = temp;
                                        }
                                }
                        }
                System.out.println(Arrays.toString(lostArray));
                }
        public Lost[] selectByKeyword(Lost[] lostArray,String keyword){

                int j=0;
                for (Lost lost : lostArray) {
                        if (lost instanceof CardLost) {
                                temp = (CardLost) lost;

                                if (temp.getCardKey().equals(keyword)) {
                                        j++;
                                }
                        }
                        if (lost instanceof BookLost) {
                                example = (BookLost) lost;
                                if (example.getBookKey().equals(keyword)) {
                                        j++;
                                }
                        }

                }
                end=new Lost[j];
                int m=0;


                for (Lost lost : lostArray) {
                        if (lost instanceof CardLost) {
                                temp = (CardLost) lost;
                                if (temp.getCardKey().equals(keyword)) {
                                        end[m] = temp;
                                        m++;
                                }
                        }
                        if (lost instanceof BookLost) {
                                example = (BookLost) lost;
                                if (example.getBookKey().equals(keyword)) {
                                        end[m] = example;
                                        m++;
                                }
                        }
                }
                return end;
        }

        }






