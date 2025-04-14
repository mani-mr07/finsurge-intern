package Searching;

public class BinarySearch {
    public static void main(String[] args) {
        int[]array={1,2,3,4,5,6,7,8};
        int left=0;
        int value=7;
        int right= array.length-1;
        while(left<=right){
            int mid=(left+right)/2;
            if(array[mid]==value){
                System.out.println("Index "+mid);
                return;
            } else if (array[mid]>value) {
                right=mid-1;
            }
            else{
                left=mid+1;
            }
        }
        System.out.println("Not Found");
    }
}
//TIME COMPLEXITY
//BEST CASE-O(1)
//AVERAGE CASE-O(logN)
//WORST CASE-O(logN)

//SPACE COMPLEXITY-O(1)
