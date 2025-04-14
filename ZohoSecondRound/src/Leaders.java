public class Leaders {
    public static void main(String[] args) {
        int[]array={4,3,16,17,5,2};
        int leader=0;
//        boolean[]leaders=new boolean[array.length];
//        for(int i=array.length-1;i>=0;i--){
//            if(array[i]>leader){
//                leader=array[i];
//                leaders[i]=true;
//            }
//        }
//        for(int i=0;i<leaders.length;i++){
//            if(leaders[i]==true){
//                System.out.println(array[i]);
//            }
//        }
//        for (int i = 0; i < array.length; i++) {
//            int flag=0;
//            for(int j=i+1;j<array.length;j++){
//                if(array[i]<array[j]){
//                    flag=1;
//                    break;
//                }
//            }
//            if(flag==0){
//                System.out.println(array[i]);
//            }
//
//        }
        boolean[]leaders=new boolean[array.length];
        for (int i = 0; i < leaders.length; i++) {
            leaders[i]=true;
        }
        leader=array[0];
        for(int i=0;i<array.length;i++){
                for(int j=0;j<i;j++){
                    if(array[j]<leader){
                    leaders[j]=false;}
            }
            leader=array[i];
        }
        for(int i=0;i<leaders.length;i++){
            if(leaders[i]==true){
                System.out.println(array[i]);
            }
        }
    }
}
