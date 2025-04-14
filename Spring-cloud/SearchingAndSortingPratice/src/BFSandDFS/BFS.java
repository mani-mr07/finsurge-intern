package BFSandDFS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BFS {
    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        adj.add(new ArrayList<>(Arrays.asList(2, 3, 1)));
        adj.add(new ArrayList<>(Arrays.asList(0)));
        adj.add(new ArrayList<>(Arrays.asList(0, 4)));
        adj.add(new ArrayList<>(Arrays.asList(0)));
        adj.add(new ArrayList<>(Arrays.asList(2)));
        int src=0;
        findPath(adj,0);
    }
    public static void findPath(ArrayList<ArrayList<Integer>>adj,int src){
        boolean[]visited=new boolean[adj.size()];
        Queue<Integer>queue=new LinkedList<>();
        visited[src]=true;
        queue.add(src);
        while(!queue.isEmpty()){
            int curr=queue.poll();
            System.out.println(curr);

            for(int value :adj.get(curr)){
                if(visited[value]!=true){
                    queue.offer(value);
                    visited[value]=true;
                }
            }
        }
    }
}
