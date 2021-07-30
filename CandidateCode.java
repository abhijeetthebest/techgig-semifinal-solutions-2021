

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class CandidateCode {




    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = reader.readLine().split(" ");
        int n = Integer.parseInt(inputs[0]);
        int e = Integer.parseInt(inputs[1]);
        Map<Long, ArrayList<long[]>> graph = new HashMap();
        for (int i = 0; i < e; i++) {
            String[] edge = reader.readLine().split(" ");
            long from = Long.parseLong(edge[0]);
            long to = Long.parseLong(edge[1]);
            long cost = Long.parseLong(edge[2]);
            if (!graph.containsKey(from))
                graph.put(from, new ArrayList<>());
            if (!graph.containsKey(to))
                graph.put(to, new ArrayList<>());
            graph.get(from).add(new long[]{to, cost});
            graph.get(to).add(new long[]{from, cost});
        }

        boolean[] visited = new boolean[n + 1];
        long[] dist = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            dist[i] = 1000001;
        }


        PriorityQueue<long[]> queue = new PriorityQueue<long[]>(new Comparator<long[]>() {
            @Override
            public int compare(long[] o1, long[] o2) {
                if (o1[1]>o2[1])
                    return 1;
                else if (o2[1]>o1[1])
                    return -1;
                else
                    return 0;
            }
        });


        //starting nodes
        int r = Integer.parseInt(reader.readLine());
        for (int i = 0; i < r; i++) {
            long point = Long.parseLong(reader.readLine());
            dist[(int) point] = 0;
            queue.offer(new long[]{point, 0});
        }

        while (!queue.isEmpty()) {
            long[] q = queue.poll();

            long node = (int) q[0];
            long d = q[1];


            if (visited[(int) node])
                continue;
            visited[(int) node] = true;

            for (int i = 0; i < graph.get(node).size(); i++) {
                int newn = (int) graph.get(node).get(i)[0];
                long c = graph.get(node).get(i)[1];
                if (!visited[newn] && (dist[newn]>c+d))
                    dist[newn] = c + d;
                    queue.offer(new long[]{(long) newn, dist[newn]});
            }
        }
        for (int i = 1; i <= n; i++)
            System.out.println(dist[i]);


    }

}





