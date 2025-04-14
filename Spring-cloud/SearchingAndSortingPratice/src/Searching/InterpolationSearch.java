package Searching;

public class InterpolationSearch {
    public static void main(String[] args) {
        int[]array={1,2,3,4,5,6,7,8,9,10,11};
        int low=0;
        int high= array.length-1;
        int value=10;
        int index=search(array,low,high,value);
        if(index!=-1){
            System.out.println("Index "+index);
        }
        else{
            System.out.println("Not Found");
        }

    }
    public static int search(int[]array,int low,int high,int value){
        int pos=0;
        if(low <high && value>=array[low] && value<=array[high]){
            pos=low+((high-low)/(array[high]-array[low]))*(value-array[low]);
            if(array[pos]==value){
                return pos;
            }
            else if(array[pos]>value){
                return search(array, low, pos-1, value);
            }
            else{
                return search(array, pos+1, high, value);
            }
        }
        return -1;
    }
}
