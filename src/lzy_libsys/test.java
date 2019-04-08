package lzy_libsys;

import java.util.ArrayList;
import java.util.List;

public class test {
    //求斐波那契数列
    public static int fibonacci(int n){
        int n0 = 0;
        int n1 = 1;
        int n2 = n0+n1;
        for(int i =2; i<n;i++){
            n2 = n0+n1;
            n0 = n1;
            n1 = n2;
            System.out.print(n2+"\t");
        }
        return n2;
    }

    public static void main(String[] args) {
//        fibonacci(10);
//        System.out.println(recursion(5));
        List<Integer> list = new ArrayList<>();
        for(int i =1;i<=1000;i++){
            if(wanshu(i)){
                list.add(i);
            }
        }
        for(Integer i : list){
            System.out.println(i);
        }

    }

    //递归求阶乘
    public static int recursion(int num){
        if(num ==1){
            return 1;
        }else if(num>1){
            return num*recursion(num-1);
        }
        return -1;
    }

    //求完数
    public static Boolean wanshu(int num){
        List<Integer> list = new ArrayList<>();
        for(int i = 1;i<num ; i++){
            if(num%i==0){
                list.add(i);
            }
        }
        int sum = 0;
        for(int i = 0;i<list.size();i++){
            sum +=list.get(i);
        }
        if(num == sum){
            return true;
        }
        return  false;
    }


}
