package Searching;

public class LinearSearch {
    public static void main(String[] args) {
        int[]array={1,2,3,4,5,6,7};
        int value=7;
        int[]duplicateArray=array;
        for(int i=0;i<array.length;i++){
            if(array[i]==value) {
                System.out.println("Index "+i);
                return;
            }
        }
        System.out.println("Not Found");
    }
}
// TIME COMPLEXITY
// AVERAGE-O(N)
// WORST-O(N)
// BEST-O(1)

//SPACE COMPLEXITY-O(1)