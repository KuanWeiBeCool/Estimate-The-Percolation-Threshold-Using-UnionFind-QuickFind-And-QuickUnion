package com.kuan;

public class QuickFind {
    public int[] id;
    private int i;
    public QuickFind(int N){
        id = new int[N];
        for (i = 0; i < N; i++)
            id[i] = i;
    }

    public boolean connected (int p, int q){
        return (id[p] == id[q]);
    }

    public void union (int p, int q){
        if (!connected(p, q)){
            int idP = id[p];
            int idQ = id[q];
            for (i = 0; i < id.length; i++) {
                if (id[i] == idQ)
                    id[i] = idP;
            }

        }
    }
}

