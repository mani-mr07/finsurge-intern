package BFSandDFS;

import java.util.ArrayList;
import java.util.Arrays;

public class DFS {
    static boolean[]visited=new boolean[5];

    public static void main(String[] args) {
        int V=5;
        ArrayList<ArrayList<Integer>>adj=new ArrayList<>();

        int[][] edges = {
                { 1, 2 }, { 1, 0 }, { 2, 0 }, { 2, 3 }, { 2, 4 }
        };
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
        for(int[]e :edges){
            add(adj,e[0],e[1]);
        }
        findDFS(adj,1);

    }
    public static void add(ArrayList<ArrayList<Integer>>adj,int e1,int e2){
        adj.get(e1).add(e2);
        adj.get(e2).add(e1);
    }
    public static void findDFS(ArrayList<ArrayList<Integer>>adj,int src){
        visited[src]=true;
        System.out.println(src);
        for(int i:adj.get(src)){
            if(visited[i]!=true){
                findDFS(adj,i);
            }
        }

    }
}
