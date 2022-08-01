package cn.laochou.dijkstra;

import java.util.*;

/**
 * 使用队列实现Dijkstra
 */
public class PQDijkstra {

    public Map<Integer, Integer> dijkstra(int[][] edges, int n, int k) {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        // 当前的最优路径
        Map<Integer, Integer> dict = new HashMap<>();
        Arrays.stream(edges).forEach(edge -> graph.computeIfAbsent(edge[0], v -> new ArrayList<>()).add(new int[] {edge[1], edge[2]}));
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        queue.offer(new int[] {k, 0});
        while (!queue.isEmpty()) {
            int[] curNode = queue.poll();
            int node = curNode[0], w = curNode[1];
            // 说明该点，已经在最优路径选录中了
            if(dict.containsKey(node)) continue;
            dict.put(node, w);
            List<int[]> connectionNodes = graph.getOrDefault(node, new ArrayList<>());
            for(int[] edge : connectionNodes) {
                if(!dict.containsKey(edge[0])) {
                    queue.offer(new int[] {edge[0], w + edge[1]});
                }
            }
        }
        return dict;
    }

}
