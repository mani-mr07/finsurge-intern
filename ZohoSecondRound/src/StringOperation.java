public class StringOperation {
    public static void main(String[] args) {
        String a="abaaac";
        String b="baaabc";
        String a1="";
        String a2="";
        int flag=0;
        for (int i = 0; i <a.length() ; i++) {
            if(a.charAt(i)==b.charAt(i)){
                if(flag==1){
                    System.out.println(a1+","+a2);
                    a1="";
                    a2="";
                    flag=0;
                }
            }
            else{
                a1+=a.charAt(i);
                a2+=b.charAt(i);
                flag=1;

            }
        }
        if(flag==1){
            System.out.println(a1+","+a2);

        }
    }
}
