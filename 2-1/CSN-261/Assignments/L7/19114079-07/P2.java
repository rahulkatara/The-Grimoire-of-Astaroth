/*************************

	CSN-261 Lab 7 Question 2
	P2.java
	Shreyas Dodamani
	19114079
	shreyas_d@cs.iitr.ac.in 

*************************/

import java.util.Scanner;

class P2 {
  static int n;
  static int in[];
  static int pre[];
  static int pre_ind=0;
  static void get_post_order_traversal (int start, int end) {
    
    if (start > end) return;
    
    int root = pre[pre_ind++];
    int index_of_root_in_in_order = end;
    // FIND INDEX OF ROOT IN THE IN ORDER TRAVERSAL TO GET RIGTH AND LEFT SUB TREES
    for (int i=start; i<end; i++) {
      if (in[i] == root){ 
        index_of_root_in_in_order = i;
        break;
      }
    }
    // TRAVERSE LEFT SUB TREE
    get_post_order_traversal(start, index_of_root_in_in_order-1);
    // TRAVERSE RIGHT SUB TREE
    get_post_order_traversal(index_of_root_in_in_order+1, end);

    // PRINT ROOT BECAUSE ROOT COMES AFTER LEFT AND RIGHT IN POST ORDER
    System.out.print(root + " ");
  }

  public static void main (String args[]) {
    Scanner sc = new Scanner(System.in);
    n = sc.nextInt();


    in = new int[n];
    pre = new int[n];
    for (int i=0; i<n; i++) in[i] = sc.nextInt();
    for (int i=0; i<n; i++) pre[i] = sc.nextInt();
    
    
    get_post_order_traversal(0, n-1);
    System.out.println("");
  }
}