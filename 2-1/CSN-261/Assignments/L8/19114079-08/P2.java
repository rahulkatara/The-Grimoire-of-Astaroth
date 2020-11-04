/*************************

	CSN-261 Lab 8 Question 2
	P2.java
	Shreyas Dodamani
	19114079
	shreyas_d@cs.iitr.ac.in 

*************************/

import java.util.Scanner;

class Graph {

  int[][] adj_list; // adjacency list
  int n; // number of nodes in the graph
  boolean[] vis; // to store visited or not while performing topological sort
  int[] order; // to store topological sort
  
  Graph (int num_nodes) {
    // initialise graph 
    this.n = num_nodes; // initialise number of nodes
    this.adj_list = new int[num_nodes][num_nodes]; // initialise adjacency list (empty for now)
    this.vis = new boolean[n]; // initialise visited array
    this.order = new int[n]; // initialise empty array for order 

    for (int i=0; i<num_nodes; i++) { // all nodes not visited initially
      this.vis[i] = false;
    }
  }

  int dfs (int k, int node) {
    this.vis[node] = true; // mark as visited

    for (int i=0; i<this.n; i++) {
      if (this.adj_list[node][i] == 1) { // for each adjacent node of current node
        if (!this.vis[i]) { // if it isn't visited, perform dfs on it
          k = this.dfs(k, i);
        }
      }
    }

    // if all nodes are traversed (or if no nodes are adjacent, 
    // then it must be a leaf node (no child nodes)) --> add to end of topological sort
    this.order[k] = node; 
    return k-1;
  }

  void topological_sort () {
    int k = this.n-1; // start with n-1 because we need to insert elements from the end of the list and decrement every time an element is added
    for (int i=0; i<this.n; i++) {
      if (!this.vis[i]) { // if not visited, perform dfs
        k = this.dfs(k, i);
      }
    }

    for (int i=0; i<this.n; i++) { // print final order
      System.out.print(this.order[i] + " ");
    }
    System.out.println("");
  }

}

class P2 {
  public static void main (String args[]) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();

    Graph graph = new Graph(n);

    for (int i=0; i<n; i++) {
      for (int j=0; j<n; j++) {
        graph.adj_list[i][j] = sc.nextInt(); // take input
      }
    }

    graph.topological_sort();
  }
}
