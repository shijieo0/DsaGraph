package com.sj.dsa;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * �ڽӱ�
 * @author ShiJie
 * @since 2020-04-21
 */
public class AdjList {
    private int V;
    private int E;
    //private int[][] adj;
    private LinkedList<Integer>[] adj;

    public AdjList(String filename) {
        File file = new File(filename);
        try (Scanner scanner = new Scanner(file)) {
            V = scanner.nextInt();
            if (V < 0) throw new IllegalArgumentException("V must be non-negative");
            adj = new LinkedList[V];
            for (int i = 0; i < V; i++) {
                adj[i] = new LinkedList<>();
            }

            E = scanner.nextInt();
            if (E < 0) throw new IllegalArgumentException("E must be non-negative");
            for (int i = 0; i < E; i++) {
                int a = scanner.nextInt();
                validateVertex(a);
                int b = scanner.nextInt();
                validateVertex(b);

                // ��ͼ�������Ի��ߺ�ƽ�б�
                if (a == b) throw new IllegalArgumentException("Self Loop Edge is Detected!");
                if (adj[a].contains(b)) throw new IllegalArgumentException("Parallel Edges are Detected!");

                adj[a].add(b);
                adj[b].add(a);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void validateVertex(int v) {
        if (v < 0 || v >= V) {
            throw new IllegalArgumentException("vertex " + v + " is invalid");
        }
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public boolean hasEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        return adj[v].contains(w);
    }

    // ��һ��������ڵ�
    public LinkedList<Integer> adj(int v) {
        validateVertex(v);
        return adj[v];
    }

    // ��һ������Ķ�
    public int degree(int v) {
        return adj(v).size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("V = %d, E = %d\n", V, E));
        for (int i = 0; i < V; i++) {
            sb.append(String.format("%d: ", i));
            for (int w: adj[i]) {
                sb.append(String.format("%d ", w));
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        AdjList adjMatrix = new AdjList("g.txt");
        System.out.println(adjMatrix);
    }

}