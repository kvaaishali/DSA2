import java.util.*;

class Edge {
    int src, dest, weight;

    Edge(int s, int d, int w) {
        src = s;
        dest = d;
        weight = w;
    }
}

public class KruskalSimple {

    static int[] parent = new int[10];

    static int find(int x) {
        if (parent[x] == x)
            return x;
        return find(parent[x]);
    }

    static void union(int x, int y) {
        parent[find(x)] = find(y);
    }

    public static void main(String[] args) {

        Edge edges[] = {
            new Edge(6,1,10),
            new Edge(4,3,12),
            new Edge(2,7,14),
            new Edge(2,3,16),
            new Edge(7,4,18),
            new Edge(5,4,22),
            new Edge(5,7,24),
            new Edge(6,5,25),
            new Edge(1,2,28)
        };

        for(int i=0;i<10;i++)
            parent[i]=i;

        Arrays.sort(edges, (a,b) -> a.weight - b.weight);

        int cost = 0;

        System.out.println("MST Edges:");

        for(Edge e : edges) {

            int u = find(e.src);
            int v = find(e.dest);

            if(u != v) {
                union(u,v);
                cost += e.weight;

                System.out.println(
                    e.src + " - " +
                    e.dest + " = " +
                    e.weight
                );
            }
        }

        System.out.println("Total MST Cost = " + cost);
    }
}