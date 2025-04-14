package Sorting;

import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
        int[]array={11,8,5,9,7};
        quickSort(array,0,array.length-1);
        System.out.println(Arrays.toString(array));
    }
    public static void quickSort(int[]arr,int low,int high){
        if(low<high){
            int pi=partition(arr,low,high);
            quickSort(arr,low,pi-1);
            quickSort(arr,pi+1,high);
        }
    }
    public static int partition(int []arr,int low ,int high){
        int partition=arr[high];
        int i=low-1;
        for (int j = low; j <=high; j++) {
            if(arr[j]<partition){
                i++;
                swap(arr,i,j);
            }
        }
        swap(arr,i+1,high);
        System.out.println(Arrays.toString(arr));
        return i+1;
    }
    public static void swap(int[]arr,int i,int j){
        int temp=arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }
}
