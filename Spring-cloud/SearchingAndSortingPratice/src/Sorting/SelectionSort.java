package Sorting;

import java.util.Arrays;

public class SelectionSort {
    public static void main(String[] args) {
        int[]array={10,8,7,11,2};
        for (int i = 0; i < array.length-1; i++) {
            int index=i;
            for(int j=i+1;j< array.length;j++){
                if(array[index]>array[j]){
                    index=j;
                }
            }
            if(index!=i){
                swap(array,i,index);
            }
        }
        System.out.println(Arrays.toString(array));
    }
        public static void swap(int[]array,int i,int j){
            int temp=array[i];
            array[i]=array[j];
            array[j]=temp;
        }
    }

