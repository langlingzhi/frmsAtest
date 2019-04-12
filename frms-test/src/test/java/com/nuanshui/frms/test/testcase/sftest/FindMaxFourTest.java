package com.nuanshui.frms.test.testcase.sftest;

import org.junit.Test;

public class FindMaxFourTest {
    @Test
    public void FindMaxFourTest(){
//        String [] dog=
//                {"1"};
//        String dogs[]={"a"};
//        String cats[] = new String[10];
//        for (int i=0;i<10;i++){
//            cats[i]="cat"+i;
//            System.out.println(cats[i]);
//        }
        int []list ={12,34,11,59,43,89,27,99,61,44};
        int results[]=FindMaxFourTest(4,list);
        for(int k=0;k<results.length;k++){
            System.out.println(list[k]);
        }

    }
    //从一个数组中找出前4个最大的数，用最优解

    public int[] FindMaxFourTest(int n,int list[]){
        int results[]= new int[n];
        for(int i=0;i<n;i++){
            for(int j=i+1;j<list.length;j++){
                if(list[i]<list[j]){
                    int res=list[j];
                    list[j]=list[i];
                    list[i]=res;
                }
            }
            results[i]=list[i];
        }
        return results;
    }
}
