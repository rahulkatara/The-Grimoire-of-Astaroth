/*************************

	CSN-261 Lab 7 Question 3
	P3.java
	Shreyas Dodamani
	19114079
	shreyas_d@cs.iitr.ac.in 

*************************/

import java.util.Scanner;

class BT { // Class for storing Binary Tree
  
  Node root; // Root node of tree
  int in[]; // Array to store in_order traversal of Binary Tree
  int in_index=0; // Variable to keep track of which index to update in in order array during traversal

  class Node { // Each node of binary tree
    int id; // ID of node
    Node left; // left child of node
    Node right; // right child of node
    Node (int id) {
      this.id = id; // initialise node with id value
    }
  }

  BT (int a[]) {
    // Initialise tree with the values given in the array

    // Number of nodes in BT
    int n = a.length;

    // initialise in_order traversal array
    this.in = new int[n];

    // First node of array represents root node
    this.root = new Node(a[0]);
    this.root.left = null;
    this.root.right = null;

    // Store pointers to nodes so we won't have to traverse the whole tree to find the corresponding parent node every time.
    // Indices of this array correspond to indices of input array
    Node node_pointers[] = new Node[n];

    // First index corresponds to root node
    node_pointers[0] = root;


    int index; // To maintain index of array to be accessed during iteration... index = i-1;
    int value; // Value refers to the value of the node to be added... value = a[index];

    // The left child of the node at the array position k will be at A(2∗k) 
    // and right child of the node at the array position k will be at A(2∗k+1). 
    // (Considering it to be indexed 1).
    // Parent index is the index of the parent node for the current node. 
    // Hence parent_index = i/2-1 always (even if i is odd, it is integer division so balances out)
    int parent_index; 

    // Declare a new node, which is initialised with a new value in each iteration
    Node new_node;

    for (int i=2; i<=n; i++) {
      index = i-1;
      value = a[index];

      // Initialise new code to be inserted
      new_node = new Node(value);
      new_node.left = null;
      new_node.right = null;

      // Get index of parent
      parent_index = i/2-1;

      // Insert new node as child of parent node by getting pointer from parent array
      if (i%2==0) {
        node_pointers[parent_index].left = new_node;
      } else {
        node_pointers[parent_index].right = new_node;
      }
      // Insert new node into node pointers array
      node_pointers[index] = new_node;
    }
  }

  public void in_order_traversal (Node source) {
    // IN-ORDER TRAVSERAL: LEFT ROOT RIGHT
    
    // If left child present, traverse left sub-tree first
    if (source.left != null) {
      in_order_traversal(source.left);
    }

    if (this.in_index == this.in.length) return; // IF ALL NODES HAVE BEEN TRAVERSED, END ALGORITHM
    this.in[this.in_index++] = source.id; // Insert root node into traversal array

    // If right child present, traverse right sub-tree last
    if (source.right != null) {
      in_order_traversal(source.right);
    }
  }
}

class P3 {

  static int partition (int[] a, int l, int h) {
    int pivot = a[l]; //Initialize the first element as pivot element.
    int n = a.length;
    int i=l; 
    int j=h;
    while(i<j){
      do{
        i++;
      } while(i<n && a[i]<=pivot);

      do{
        j--;
      } while(j<n && a[j]>pivot);

      if(i<j){
        // SWAP a[i] and a[j];
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
      }
    }
    //SWAP a[j] and a[l];
    int tt = a[j];
    a[j] = a[l];
    a[l] = tt;
    return j;
  }
  static void quick_sort (int[] s, int l, int h) {
    if (l < h) { //Ensure that there are at least two elements by checking if l<h

      //Find the index of pivot element after is kept in correct position
      int p = partition(s, l, h);

      //Apply quicksort on the two smaller partitions
      quick_sort(s, l, p);
      quick_sort(s, p+1, h);
    }
  }
  public static void main (String args[]) {
    Scanner sc = new Scanner(System.in);
    
    // Input number of nodes
    int n = sc.nextInt();

    // Create one array to store the input nodes
    int a[] = new int[n];

    // Take input for node values
    for (int i=0; i<n; i++) {
      a[i] = sc.nextInt();
    }

    // Initialise binary tree object
    BT bt = new BT(a);

    // Sort input array so we can later compare with the in order traversal
    quick_sort(a, 0, n);

    // Do in order traversal on bt and generate in order traversal array 
    // which is stored as a characteristic variable of bt object
    bt.in_order_traversal(bt.root);

    // for (int i=0; i<n; i++) {
    //   System.out.print(a[i] + " ");
    // }
    // System.out.println("");
    // for (int i=0; i<n; i++) {
    //   System.out.print(s[i] + " ");
    // }
    // System.out.println("");
    // for (int i=0; i<n; i++) {
    //   System.out.print(bt.in[i] + " ");
    // }
    // System.out.println("");
    

    int swaps = 0;

    // CONCEPT: IN ORDER TAVERSAL ARRAY OF BST IS SORTED IN ASCENDING ORDER
    // HENCE, WE MUST SEE THE NUMBER OF SWAPS REQUIRED TO CONVERT IN ORDER TRAVERSAL
    // ARRAY TO SORTED ARRAY
    for (int i=0; i<n; i++) {
      if (bt.in[i] != a[i]) { // IF in order traversal element doesn't match the sorted element
        for (int j=i; j<n; j++) {
          if (a[i] == bt.in[j]) { // Go forward until we find the element and then swap
            int t = bt.in[i];
            bt.in[i] = bt.in[j];
            bt.in[j] = t;
            swaps++;
            break;
          }
        }
      }
    }

    System.out.println(swaps);
    
  }
}
