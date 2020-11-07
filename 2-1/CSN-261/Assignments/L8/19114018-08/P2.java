import java.util.*;
/*
    Algorithm Used:
    We perform traversal similar to dfs traversal of the graph.(recursive calling)
    First we push all unvisited adjacent nodes to our stack, but before any push we recursively call the same function for the given vertex.  
    This ensures that all vertices that are encountered later are first pushed. 
    Any vertex that was not encountered in first call won't cause any problem too, as finally they would be pushed later. Thus a topological sort in the popped vertices.
*/

class UtilStack{    // I have made this class to store the nodes in it's objects, while traversing the graph for Topological Sort{whole structure would act like a stack} 
    int key;
    UtilStack(int x){
        key = x;
    }
    public UtilStack next;
}
class P2 {
    static int n;
    static int adj[][]; //  The graph as adjacency matrix
    
    static boolean visited[];
    static  UtilStack Top = new UtilStack(-1);  //  The next of this would point to top of  UtilStack{Linked List}

    static void TopologicalSort(int v){
        UtilStack vertex = new UtilStack(v);

        for(int i = 0 ; i < n ; ++i){
            if(adj[v][i] == 1 && visited[i] == false)
                TopologicalSort(i);
        }
        visited[v] = true;
        vertex.next = Top.next;
        Top.next = vertex;
    }

    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        
        n = in.nextInt();
        adj = new int[n][n];

        for(int i = 0 ; i < n ; ++i){
            for(int j = 0 ; j < n ; ++j){
                adj[i][j] = in.nextInt();
            }
        }
      
        visited = new boolean[n];
        for(int i = 0 ; i < n ; ++i){
            if(visited[i] == false){
                TopologicalSort(i);
            }
        }
        while(Top.next != null){
            System.out.print(Top.next.key+" ");
            Top = Top.next;
        }
    }
}
