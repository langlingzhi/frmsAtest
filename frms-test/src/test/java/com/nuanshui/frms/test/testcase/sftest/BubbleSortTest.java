package com.nuanshui.frms.test.testcase.sftest;

import org.junit.Test;

public class BubbleSortTest {
    @Test
    //请写出冒泡排序
    public void  BubbleSortTest(){
        int []list ={12,34,11,59,43,89,27,99,61,44};
        for(int i=0;i<list.length-1;i++){
            for(int j=i+1;j<list.length;j++){
                if(list[i]<list[j]){
                    int res=list[j];
                    list[j]=list[i];
                    list[i]=res;
                }
            }
        }
        for(int k=0;k<list.length;k++){
            System.out.println(list[k]);
        }
    }

}
