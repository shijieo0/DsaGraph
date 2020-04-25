package com.sj.dsa;

import java.util.ArrayList;

/**
 * @author ShiJie
 * @since 2020-04-21
 */
public class GraphDFS {
    private Graph graph;
    private boolean[] visited;
    private ArrayList<Integer> pre = new ArrayList<>();
    private ArrayList<Integer> post = new ArrayList<>();

    public GraphDFS(Graph graph) {
        this.graph = graph;
        visited = new boolean[graph.V()];
        // dfs(0);  // 只从直接开始遍历，对存在连通分量的图，遍历不全
        for (int i = 0; i < graph.V(); i++) {
            if (!visited[i]) {
                dfs(i);
            }
        }
    }

    private void dfs(int v) {
        visited[v] = true;
        pre.add(v);
        for (int w: graph.adj(v)) {
            if (!visited[w]) {
                dfs(w);
            }
        }
        post.add(v);
    }

    public Iterable<Integer> pre() {
        return pre;
    }

    public Iterable<Integer> post() {
        return post;
    }

    public static void main(String[] args) {
        Graph graph = new Graph("g.txt");
        GraphDFS graphDFS = new GraphDFS(graph);
        System.out.println(graphDFS.pre());
        System.out.println(graphDFS.post());
    }
}
