package Searching;

import java.util.HashMap;

public class HashSearch {
    public static void main(String[] args) {
        HashMap<Integer,Integer>map=new HashMap<>();
        map.put(1,2);
        map.put(2,3);
        map.put(3,4);
        map.put(4,5);

        int value=5;
        if(map.containsKey(value)){
            System.out.println(map.get(value));
        }
        else{
            System.out.println("Not Found");
        }
    }

}
// TIME COMPLEXITY-O(1)
// SPACE COMPLEXITY-O(N)
