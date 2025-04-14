public class Main {
    public static void main(String[] args) {

        int noOfRows=10;
        int value=0;
        for(int i=1;i<=noOfRows;i++){
            value=i;
            for(int j=1;j<=i;j++){
                System.out.printf("%-3d",value);
                value+=(noOfRows-j);
            }
            System.out.println();
        }
    }
}