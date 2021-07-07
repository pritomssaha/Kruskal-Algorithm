import java.util.Arrays;

public class KruskalAlgorithm {

    static class  Edge implements Comparable<Edge>{
        int src, des, weight;

        @Override
        public int compareTo(Edge edge) {
            return this.weight-edge.weight;
        }
    }
    static class Subset{
        int parent, rank;
    }

    int V, E;
    Edge[] edges;
    Edge[] mst;

    //constructor
    public KruskalAlgorithm(int v, int e){
        this.V=v;
        this.E=e;
        edges=new Edge[E];
        //store all the edges in MST
        mst=new Edge[V];
        for(int i=0;i<E;i++){
            edges[i]=new Edge();
        }
        for(int i=0;i<V;i++){
            mst[i]=new Edge();
        }
    }

    void kruskal(){
        int e=0;

        //sort all the edge in increment order
        Arrays.sort(edges);

        //Union-find algorithm to find the cycle

        Subset[] subset=new Subset[V];

        for(int i=0;i<V;i++){
            subset[i]=new Subset();
        }

        for(int i=0;i<V;i++){
            subset[i].parent=i;
            subset[i].rank=0;
        }


        //pick the smallest edge
        int i=0;

        while(e<V-1) {
            Edge edge = new Edge();
            edge = edges[i++];

            int x = find(subset, edge.src);
            int y = find(subset, edge.des);

            if (x != y) {
                mst[e++] = edge;
                union(subset, x, y);
            }
        }
        int totalCost=0;
        for(int j=0;j<subset.length;j++){
            System.out.println(mst[j].src+"-"+mst[j].des+": "+mst[j].weight);
            totalCost+=mst[j].weight;
        }
        System.out.println(totalCost);

    }

    private void union(Subset[] subset, int x, int y) {
        if(subset[x].rank<subset[y].rank)
            subset[x].parent=y;
        else if(subset[x].rank>subset[y].rank)
            subset[y].parent=x;
        else{
            subset[y].parent=x;
            subset[x].rank++;
        }
    }
    //to find the parent
    private int find(Subset[] subset, int e) {
        //path compression
        if(subset[e].parent!=e){
            subset[e].parent=find(subset,  subset[e].parent);
        }
        return subset[e].parent;


        //naive approach
        /*if(subset[e].parent==e){
            return e;
        }
        else return find(subset, subset[e].parent);

         */


    }

    public static void  main(String[] args){

        KruskalAlgorithm graph = new KruskalAlgorithm(4, 5);

        // add edge 0-1
        graph.edges[0].src = 0;
        graph.edges[0].des = 1;
        graph.edges[0].weight = 10;

        // add edge 0-2
        graph.edges[1].src = 0;
        graph.edges[1].des = 2;
        graph.edges[1].weight = 6;

        // add edge 0-3
        graph.edges[2].src = 0;
        graph.edges[2].des = 3;
        graph.edges[2].weight = 5;

        // add edge 1-3
        graph.edges[3].src = 1;
        graph.edges[3].des = 3;
        graph.edges[3].weight = 15;

        // add edge 2-3
        graph.edges[4].src = 2;
        graph.edges[4].des = 3;
        graph.edges[4].weight = 4;
        graph.kruskal();
    }


}
