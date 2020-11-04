/*************************

	CSN-261 Lab 7 Question 1
	P1.java
	Shreyas Dodamani
	19114079
	shreyas_d@cs.iitr.ac.in 

*************************/

import java.util.Scanner;

class P1 {
  public static void main (String args[]) {
    int h;
    Scanner sc = new Scanner(System.in);
    h = sc.nextInt();

    // BASE CASE
    if (h==0) {
      System.out.println(1);
      return;
    }

    int nodes[] = new int[h+1];
    
    // BASE CASES FOR RELATION
    nodes[0] = 1;
    nodes[1] = 2;
    
    for (int i=2; i<=h; i++) {
      nodes[i] = nodes[i-1] + nodes[i-2] + 1;
    }

    System.out.println(nodes[h]);
  }
}
