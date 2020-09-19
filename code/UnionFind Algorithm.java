package com.kuan;

public class UnionFind {
    public int[] id;
    private int[] sz;

    public UnionFind(int N){
        id = new int[N];
        sz = new int[N];
        // Initialize ID and Size
        for(int i = 0; i < N; i++){
            id[i] = i;
            sz[i] = 1; //each unit is independent when initialized
        }
    }

    public int root(int i){
        while(id[i] != i) {
            id[i] = id[id[i]]; //Compression
            i = id[i];
        }
        return i;
    }

    public boolean connected(int p, int q){
        int rootP = root(p);
        int rootQ = root(q);
        return (rootP == rootQ);
    }

    public void union(int p, int q){
        int rootQ = root(q);
        int rootP = root(p);
        if (rootQ != rootP) {
            int size_p = sz[rootP];
            int size_q = sz[rootQ];
            if (size_p >= size_q) {
                id[rootQ] = rootP;
                sz[rootQ] += sz[rootP];
            } else {
                id[rootP] = rootQ;
                sz[rootP] += sz[rootQ];
            }
        }
    }
}
