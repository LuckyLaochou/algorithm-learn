package cn.laochou.dijkstra;

import java.util.*;

public class Dijkstra {

    /**
     * n 个节点 求 k 到每个节点的距离
     * @param edges 节点到节点之间的边的关系
     * @param k 节点k
     * @param n n 个节点
     * @return k 到每个节点的距离
     */
    public Map<Integer, Integer> dijkstra(int[][] edges, int n, int k) {
        Map<Integer, Integer> distances = new HashMap<>();
        for(int i = 1; i <= n; i++) {
            distances.put(i, Integer.MAX_VALUE);
        }
        // 图
        Map<Integer, List<int[]>> graph = new HashMap<>();
        Arrays.stream(edges).forEach(edge -> graph.computeIfAbsent(edge[0], v -> new ArrayList<>()).add(new int[] {edge[1], edge[2]}));
        boolean[] visited = new boolean[n + 1];
        distances.put(k, 0);
        while (true) {
            int curNode = -1;
            int curMinDistance = Integer.MAX_VALUE;
            // 找到选择的点
            for(int i = 1; i <= n; i++) {
                if(!visited[i] && distances.get(i) < curMinDistance) {
                    curMinDistance = distances.get(i);
                    curNode = i;
                }
            }
            // 如果没有找到需要选择的点，跳出
            if(curNode == -1) break;
            // 标记已经选中的点
            visited[curNode] = true;
            // 找到选中点的能触达的点
            List<int[]> connectionEdge = graph.getOrDefault(curNode, new ArrayList<>());
            for(int[] edge : connectionEdge) {
                if(!visited[edge[0]]) {
                    int currentNodeDistance = distances.get(edge[0]);
                    distances.put(edge[0], Math.min(currentNodeDistance, curMinDistance + edge[1]));
                }
            }
        }
        return distances;
    }


    public int networkDelayTime(int[][] times, int n, int k) {
        // dijkstra 算法
        Map<Integer, Integer> dict = dijkstra(times, n, k);
        int res = 0;
        for(int distance : dict.values()) {
            if(distance == Integer.MAX_VALUE) return -1;
            res = Math.max(res, distance);
        }
        return res;
    }

    public static void main(String[] args) {
        Dijkstra dijkstra = new Dijkstra();
        System.out.println(dijkstra.networkDelayTime(new int[][]{{2, 1, 1}, {2, 3, 1}, {3, 4, 1}}, 4, 2));
    }

}
