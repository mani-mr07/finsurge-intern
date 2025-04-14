package Sorting;


import java.util.Arrays;

public class MergeSort2 {
    public static void main(String[] args) {
        int[]array={1,4,3,2,5};
        mergeSort(array,0,array.length-1);
        System.out.println(Arrays.toString(array));
    }
    public static void mergeSort(int[]array,int low,int high){
        if(low<high){
            int mid=(low+high)/2;
            mergeSort(array,low,mid);
            mergeSort(array,mid+1,high);
            merge(array,low,mid,high);
        }
    }
    public static void merge(int[]array,int low,int mid,int high){
        int[]leftArray=new int[mid-low+1];
        int[]rightArr=new int[high-mid];
        int lr=0;
        int rr=0;
        for (int i = low; i <mid+1; i++) {
            leftArray[lr++]=array[i];
        }
        for(int i=mid+1;i<=high;i++){
            rightArr[rr++]=array[i];

        }
        int i=0,j=0;
        int k=low;
        while(i<leftArray.length && j<rightArr.length){
            if(leftArray[i]<rightArr[j]){
                array[k++]=leftArray[i];
                i++;
            }
            else if(leftArray[i]>rightArr[j]){
                array[k++]=rightArr[j];
                j++;
            }
        }
        while (i< leftArray.length){
            array[k++]=leftArray[i];
            i++;
        }
        while(j<rightArr.length){
            array[k++]=rightArr[j++];
        }
    }

}
