package Sorting;

import java.util.Arrays;



public class Bubblesort {
    public static void main(String[] args) {
        int count=0;
        int[]array={1,10,9,7,6};
        String[]arr={"935","6357"};
        int n= arr.length;
        for (int i = 0; i <n; i++) {
            for (int j = i+1; j < n; j++) {
                count++;
                if(arr[i].compareTo(arr[j])<0){
                    swap(arr,i,j);
                }
            }
            System.out.println(Arrays.toString(arr));
        }
        System.out.println("For sorting 5 elements it takes "+ count + " times");
        System.out.println(Arrays.toString(arr));
    }
    public static void swap(String[]array,int i,int j){
        String temp=array[i];
        array[i]=array[j];
        array[j]=temp;
    }
}
