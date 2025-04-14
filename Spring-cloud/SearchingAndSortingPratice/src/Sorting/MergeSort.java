package Sorting;

import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {
        int[] array={10,2,3,4,5};
        int left=0;
        int right=array.length-1;
        mergeSort(array,0,array.length-1);
        System.out.println(Arrays.toString(array));
    }
    public static void mergeSort(int[]array,int low,int high){
        if(low<high){
            int mid=(low+high)/2;
            mergeSort(array, low, mid);
            mergeSort(array,mid+1,high);
            merge(array,low,mid,high);
        }
    }
    public static void merge(int[]arr,int low,int mid,int high){
        int[]leftArr=new int[mid-low+1];
        int[]rightArr=new int[high-mid];
        int li=0;
        int ri=0;
        for (int i = low; i < mid+1; i++) {
            leftArr[li++]=arr[i];
        }
        for(int i=mid+1;i<=high;i++){
            rightArr[ri++]=arr[i];
        }
        int i=0;int j=0;
        int k=low;
        while(i<leftArr.length && j<rightArr.length){
                if(leftArr[i]<rightArr[j]){
                    arr[k]=leftArr[i];
                    i++;
                } else if (leftArr[i]>rightArr[j]) {
                    arr[k]=rightArr[j];
                    j++;
                }
                k++;
        }
        while(i< leftArr.length){
            arr[k++]=leftArr[i++];
        }
        while(j< rightArr.length){
            arr[k++]=rightArr[j++];
        }
    }
}
