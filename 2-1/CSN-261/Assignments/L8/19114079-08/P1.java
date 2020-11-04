/*************************

	CSN-261 Lab 8 Question 1
	P1.java
	Shreyas Dodamani
	19114079
	shreyas_d@cs.iitr.ac.in 

*************************/

import java.util.Scanner;

class Queue {
  Node front;
  Node rear;

  class Node { // Each node of binary tree
    int first; // start index
    int second; // end index
    Node next; // for queue foramtion
    Node (int f, int s) {
      this.first = f; 
      this.second = s; 
      this.next = null;
    }
  }
  
  Queue () {
    this.front = null;
    this.rear = null;
  }

  boolean empty () {
    return this.front == null;
  }

  void enqueue(int f, int s){
    Node node = new Node(f, s);
    if(this.front == null){ //INSERT ELEMENT WHEN QUEUE IS EMPTY
      this.front = node;
      this.rear = node;
    } else { // INSERT ELEMENT WHEN QUEUE IS NOT EMPTY
      this.rear.next = node;
      this.rear = node;
    }
    return;
  }
  
  int[] dequeue(){ 
    int[] vals = new int[2];
    vals[0] = this.front.first;
    vals[1] = this.front.second;
    if (this.front == this.rear) { // REMOVE ELEMENT WHEN ONLY ONE ITEM IN QUEUE
      //SET BOTH POINTERS TO NULL
      this.front = null;
      this.rear = null;
    } else { //REMOVE ELEMENT WHEN MORE THAN ONE ELEMENT PRESENT IN QUEUE
      this.front = this.front.next;
    }
    return vals; //RETURN POPPED START AND END INDICES FOR CURRENT ITERATION
  }
}

class Graph{
  int[] a;
  int[] order;
  int n;
  int index;

  Graph (int num_nodes) {
    this.n = num_nodes;
    this.order = new int[num_nodes];
    this.a = new int[num_nodes];
    this.index = 0;
  } 

  void get_order () {
    // Initialise queue
    Queue q = new Queue();

    // first queue the start and end of the entire array 
    // to find middle
    q.enqueue(0, this.n-1);

    int v[];
    int start, end;
    while (!q.empty()) {
      // pop next pair of start and end indices from queue (comes as array)
      v = q.dequeue();
      // get start and end indices from array
      start = v[0];
      end = v[1];
      
      if (start == end) { // only one element in this sub-array -> add that element to array
        this.order[this.index++] = a[start];
      } else if (start == end - 1) { // two elements in the sub-array -> add first element to array, and move on
        this.order[this.index++] = a[start];
        q.enqueue(end, end); // push the remaining element into the queue
      } else { // more than two elements in the sub-array
        int mid = (start+end)/2; // calculate middle element and add that to array
        this.order[this.index++] = a[mid];
        // push the first and second halves into the queue
        q.enqueue(start, mid-1); 
        q.enqueue(mid+1, end);
      }
      // if array is full, break
      if (this.index == n) break;
    }

    // print order
    for (int i=0; i<this.n; i++) {
      System.out.print(this.order[i] + " ");
    }
    System.out.println("");
  }
}

class P1 {

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
    int n = sc.nextInt();
    Graph g = new Graph(n);
    for (int i=0; i<n; i++) {
      g.a[i] = sc.nextInt();
    }

    // sort array
    quick_sort(g.a, 0, n);

    // get correct for insertion so that the binary tree will
    // be an avl tree at each step of insertion
    g.get_order();
  }
}
