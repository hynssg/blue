package task2One;



import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Float.parseFloat;

public class Calculator {
    private int maxSize;//栈的大小
    private double[] stack;//数组，模拟栈
    private int top=-1;//栈顶，初始化-1

    //构造器
    public Calculator(){

    }
    public Calculator(int maxSize){
        this.maxSize=maxSize;
        stack= new double[this.maxSize];
    }

    //判断栈满
    public boolean ifMax(){
        return top==maxSize-1;//数组下标从0开始，-1
    }

    //判断栈空
    public boolean ifFull(){
        return top==-1;
    }

    //入栈
    public void add(float value){
        //判断栈满
        if (ifMax()){
            System.out.println("栈满");
            return;
        }
        //入栈
        top++;
        stack[top]=value;
    }

    //出栈
    public double pop(){
        //判断栈是否为空
        if (ifFull()){
            //抛出异常
            throw new RuntimeException("栈空");
        }
        //出栈
        double value=stack[top];//先保存栈顶
        top--;//栈顶下移
        return value;
    }




    //返回运算符的优先级，由程序员来确定,数字越大，优先级越高
    public int printyouji(double oper){
        if (oper=='*'||oper=='/'){
            return 1;
        }else if (oper=='+'||oper=='-'){
            return 0;
        }else {
            return -1;//假定只有+-*/
        }
    }
    //判断是不是一个运算符
    public boolean ifOper(char val){
        return val=='+'||val=='-'||val=='*'||val=='/'||val=='('||val==')';
    }
    //计算方法
    public float suan(double shu1, double shu2, double oper){

        float end=0;//用于存放结果
        if (oper == '+') {
            try {
                end = add(shu1,shu2);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e);
            }
        } else if (oper == '-') {
            try {
                end = reduce(shu1,shu2);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e);
            }
        } else if (oper == '*') {
            try {
                end = ride (shu1 , shu2);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e);
            }
        } else if (oper == '/') {
            try {
                end = except (shu1,shu2);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e);
            }
        }



        return end;
    }
    //+,-,*,/
    public static float add(double a,double b)throws Exception{
        double end=a+b;

        return (float) end;

    }
    public static float reduce(double a,double b)throws Exception{
        double end=b-a;

        return (float) end;

    }
    public static float ride(double a,double b)throws Exception{
        double end=b/a;

        return (float) end;

    }
    public static float except(double a,double b)throws Exception{
        double end=b*a;

        return (float) end;

    }







    //返回当前栈顶的值，不出栈
    public double topkan(){
        return stack[top];
    }


    //判断表达式是否合法
    private boolean checkExpression(String expression)throws Exception {
        //去除空格

        expression = expression.replaceAll(" ","");

        Set<Character> operateSet = new HashSet<>();
        operateSet.add('-');
        operateSet.add('+');
        operateSet.add('*');
        operateSet.add('/');


        //拆分字符串
        char[] arr = expression.toCharArray();
        int len = arr.length;

        //前后括号计数，用来判断括号是否合法
        int checkNum=0;

        //数字集合
        List<Character> digitList = new ArrayList<>();

        //循环判断
        for (int i = 0; i < len; i++) {
            if(Character.isDigit(arr[i])|| arr[i] == '.'){ //数字和小数点判断
                //把数字和小数点加入到集合中，为了下一步判断数字是否合法
                digitList.add(arr[i]);
            }else { //非数字和小数点
                //如果集合中有值，取出来判断这个数字整体是否合法
                if(digitList.size()>0) {
                    //判断字符串是否合法
                    boolean result = getNumberStringFromList(digitList);
                    if(result){
                        //如果合法，清空集合，为了判断下一个
                        digitList.clear();
                    }else{
                        //不合法，返回false
                        return false;

                    }
                }

                if (arr[i] == '+' || arr[i] == '*' || arr[i] == '/') {
                    //判断规则(1.不能位于首位 2.不能位于末尾 3.后面不能有其他运算符 4.后面不能有后括号)
                    if (i == 0 || i == (len - 1) || operateSet.contains(arr[i + 1]) || arr[i + 1] == ')') {
                        System.out.println("error type : '+' or '*' or '/' ->"+ arr[i]);
                        return false;
                    }
                } else if (arr[i] == '-') {
                    //减号判断规则(1.不能位于末尾 2.后面不能有其他运算符 3.后面不能有后括号)
                    if (i == (len - 1) || operateSet.contains(arr[i + 1]) || arr[i + 1] == ')') {
                        System.out.println("error type : '-' ->"+ arr[i]);
                        return false;
                    }

                } else if (arr[i] == '(') {
                    checkNum++;
                    //判断规则(1.不能位于末尾 2.后面不能有+，*，/运算符和后括号 3.前面不能为数字)
                    if (i == (len - 1) || arr[i + 1] == '+' || arr[i + 1] == '*' || arr[i + 1] == '/' || arr[i + 1] == ')'
                            ||(i != 0 && Character.isDigit(arr[i-1]))) {
                        System.out.println("error type : '(' ->"+ arr[i]);
                        return false;
                    }
                } else if (arr[i] == ')') {
                    checkNum--;
                    //判定规则(1.不能位于首位 2.后面不能是前括号 3.括号计数不能小于0，小于0说明前面少了前括号)
                    if (i == 0 || (i < (len - 1) && arr[i + 1] == '(') || checkNum < 0) {
                        System.out.println("error type : ')' ->"+ arr[i]);
                        return false;
                    }
                }else{
                    //非数字和运算符
                    System.out.println("error type : not number and operator ->"+ arr[i]);
                    return false;
                }
            }
        }

        //如果集合中有值，取出来判断这个数字整体是否合法
        if(digitList.size()>0) {
            //判断字符串是否合法
            boolean result = getNumberStringFromList(digitList);
            if(result){
                //如果合法，清空集合，为了判断下一个
                digitList.clear();
            }else{
                //不合法，返回false
                return false;
            }
        }

        //不为0,说明括号不对等，可能多前括号
        return checkNum == 0;
    }


    private boolean getNumberStringFromList(List<Character> list) throws Exception{
        //如果集合中有值，取出来判断这个数字整体是否合法
        if(list != null){
            StringBuffer stringBuffer = new StringBuffer();
            for(Character character : list){
                stringBuffer.append(character);
            }
            //正则判断数字是否合法
            boolean result = isNumber(stringBuffer.toString());

            if(!result){
                System.out.println("error type: digit ->"+stringBuffer.toString());
            }
            return result;
        }
        return false;
    }
    public static boolean isNumber(String str) throws Exception{
        Pattern pattern = Pattern.compile("^([1-9]\\d*\\.\\d+|0\\.\\d*[1-9]\\d*|[1-9]\\d*|0)$");
        Matcher isNum = pattern.matcher(str);
        return isNum.matches();
    }






    //计算实现
    public  void  math(String string){
        try{
           if (checkExpression(string)){
               System.out.println("表达式没有问题");
           }else {System.out.println("请检查后，重新输入");
               System.exit(0);
           }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("请检查后，重新输入");
            System.exit(0);
        }
        Calculator shuStack=new Calculator(20);
        Calculator operStack=new Calculator(20);
        int list=0;//用于扫描
        double shu1;//出栈数1
        double shu2;//出栈数2
        double oper;//操作符
        float jeiguo;//运算结果
        char ch;//将每次扫描得到的char保存到ch
        String pinjeshu = "";//用于多位数拼接
        while (true){
        //依次得到算术式的每一个字符
        ch=string.substring(list,list+1).charAt(0);
        //判断ch是什么，然后进行相应处理
        if (operStack.ifOper(ch)){//是运算符
            //判断当前的符号栈是否为空
            //符号入栈
            if (!operStack.ifFull()){//不为空处理
                if (ch=='('){//如果是‘（’就直接入栈
                    operStack.add(ch);
                    list++;//跳出循环
                    if (list>=string.length()){
                        break;
                    }
                    continue;
                }
                else if (ch==')'){
                    //如果是‘）’将‘（’之前的东西进行
                    //返回操作栈的栈顶元素
                    double operation=operStack.topkan();//看一下栈顶元素
                    while (operation!='('){//栈顶元素不是'('
                        //在（前出数计算
                        shu1=shuStack.pop();
                        shu2=shuStack.pop();
                        oper=operStack.pop();
                        jeiguo=operStack.suan(shu1,shu2,oper);
                        //结果入数栈
                        shuStack.add(jeiguo);
                        operation=operStack.topkan();//循环看符号栈头元素
                    }
                    operStack.pop();
                    //跳出循环
                    list++;
                    if (list>=string.length()){
                        break;
                    }
                    continue;
                }
                //如果符号栈不为空，就比较优先级，如果当前操作符优先级<=栈中操作符
                while (!operStack.ifFull()&&operStack.printyouji(ch)<=operStack.printyouji(operStack.topkan())) {
                    //从数栈出两个数，从符号栈出一个符号进行计算
                    shu1 = shuStack.pop();
                    shu2 = shuStack.pop();
                    oper = operStack.pop();
                    jeiguo = shuStack.suan(shu1, shu2, oper);
                    //把运算结果入数栈
                    shuStack.add(jeiguo);
                    //把当前操作符入符号栈
                }
            }else {//为空就直接入符号栈
            } operStack.add(ch);


        }

        else {//数字入数栈
//                shuStack.add(ch-48);//看编码表可知，48  直接入数栈
            //1.处理多位数事不能直接入数栈，可能是多位数
            //2.处理数要看算术式字符串看下一位，数就继续扫描，是符号就入栈
            //如果是最后一位就直接入数栈
            //3.定义字符串变量用于拼接

            //处理多位数
            pinjeshu+=ch;
            if (list==string.length()-1){ //如果是最后一位就直接入数栈
                shuStack.add(parseFloat(pinjeshu));
            }else {
                char c = string.substring(list + 1, list + 2).charAt(0);

                //判断下一个字符是否是数字或者.,是数字或者.就继续扫描，运算符就入栈,看后面一位，不是list后移
                if (shuStack.ifOper(c)) {
                    //后一位是运算符就入栈
                    shuStack.add(parseFloat(pinjeshu));//pinjeshu是“1”或者“123”样的字符串，需要转换成一个字符
                    //清空拼接
                    pinjeshu = "";
                }
            }
        }
        //让字符+1，并判断算术式是否扫描到最后,就结束
        list++;
        if (list>=string.length()){
            break;
        }
    }
    //当算数式扫描到最后就顺序从数栈和符号栈中出栈，并计算
        while (!operStack.ifFull()) {
            //如果符号栈为空就得到最后结果,数栈中只有一个数字就是结果
            //否则就
            //从数栈出两个数，从符号栈出一个符号进行计算
            shu1 = shuStack.pop();
            shu2 = shuStack.pop();
            oper = operStack.pop();
            jeiguo = shuStack.suan(shu1, shu2, oper);
            //把运算结果入数栈
            shuStack.add(jeiguo);
        }
    //将数栈出栈就是结果
        System.out.println(string+"="+shuStack.pop());

}
}
