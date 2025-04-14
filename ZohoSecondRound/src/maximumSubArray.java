public class maximumSubArray {
    public static void main(String[] args) {
        int[]array={1,1,0,0,1,0,1,0,1,1,1,1};
        int[]array1={1,0,1,0,1,0};
        int[]array2={0,0,0,0};
        int count=1;
        int maxCount=1;
        for (int i = 1; i < array.length; i++) {
            if(array[i]==array[i-1]){
                count++;
                if(count>maxCount){
                    maxCount=count;
                }
            }
            else{
                count=1;
            }
        }
        if(maxCount==1){
            System.out.println("No Subarray Found");
        }
        else{
            System.out.println(maxCount);
        }
    }
}
