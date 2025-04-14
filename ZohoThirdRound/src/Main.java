public class Main {
    public static void main(String[] args) {
        int n=5;
        int value=1;
        String pattern="*";
        for(int i=1;i<=n;i++){
            for(int j=1;j<=2*n-1;j++){
                if(j<=n-i || j>2*n-i){
                System.out.printf("%-3s",pattern);}
                else  {
                    System.out.printf("%-3s",value);
                    if(i%2==0){
                        value--;
                    }
                    else{
                        value++;
                    }
                }
            }
            if(i%2==0){
            value+=n+1;}
            else{
                value+=n-1;
            }
            System.out.println();
            }

        }
    }
