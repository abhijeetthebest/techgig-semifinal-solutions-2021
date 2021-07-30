// Basically we had to implement optimised dijkstra algo using priority queue. Score 80/100

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class girfriend {

    private static boolean isNotVisited(int x,
                                        List<int[]> path)
    {
        int size = path.size();
        for(int i = 0; i < size; i++)
            if (path.get(i)[0] == x)
                return false;

        return true;
    }


    public static int bfs(int[][] graph, int src, int dest, int houses){

        boolean[] visited=new boolean[houses+1];

        PriorityQueue<int[]> queue = new PriorityQueue<int[]>(
                (t1,t2) -> t1[1]-t2[1]) ;

        //starting node
        queue.offer(new int[]{src,0});

        int m=10000001;



        while (!queue.isEmpty()) {
            int[] q = queue.poll();

            int node = q[0];
            int s = q[1];

            // If last vertex is the desired destination
            if (node == dest) {
                return s;
            }

            if  (visited[node])
                continue;
            visited[node]=true;

            for (int i=1;i<=houses;i++){
                if (graph[node][i]>0 && !visited[i])
                    queue.offer(new int[]{i, Math.max(0, Math.max(graph[node][i], s))});
            }

        }
        return m;


    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = reader.readLine().split(" ");
        int houses = Integer.parseInt(inputs[0]);
        int roads = Integer.parseInt(inputs[1]);
        int[][] graph = new int[houses + 1][houses + 1];
        for (int i = 0; i < roads; i++) {
            String[] edge = reader.readLine().split(" ");
            int from=Integer.parseInt(edge[0]);
            int to=Integer.parseInt(edge[1]);
            int cost=Integer.parseInt(edge[2]);
            graph[from][to]=cost;
            graph[to][from]=cost;
        }
        int result=bfs(graph,1, houses, houses);

        if (result==10000001)
            System.out.println("NOT POSSIBLE");
        else
            System.out.println(result);
    }


}
