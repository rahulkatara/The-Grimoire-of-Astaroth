import java.util.*;

class P1{

    static int a[];
    static int bst[];
    static int n;

    // Quick Sort to sort the array in O(nlog(n)) complexity -- Functions Partition() and QuickSort() for the same
    static int Partition(int l , int r){
        //choosing a random index in range [l,r]    // This ensures randomisation and helps prevent worst case(Sorted array)
        Random rand = new Random();
        int ind = rand.nextInt(r+1);
        ind = l + (ind % (r-l+1));  

        int temp = 0;   //for swapping purposes

        //swap rightmost element with the element at random index -- this helps randomise the array
        temp = a[r];
        a[r] = a[ind];
        a[ind] = temp;

        int i = l-1;
        for(int j = l ; j < r ; ++j){
            if(a[j] < a[r]){
                ++i;

                //swap a[i] and a[j]
                temp = a[i];
                a[i] = a[j];
                a[j] = temp;
            }
        }
        //swap a[i+1] and a[r]
        temp = a[i+1];
        a[i+1] = a[r];
        a[r] = temp; 
        
        return i+1;
    }
    static void QuickSort(int l , int r){
        if(l < r){
            int ind = Partition(l, r);

            QuickSort(l, ind-1);
            QuickSort(ind+1, r);
        }
    }
    /*
        Algorithm I used:
        After sorting, if i keep on choosing the middle vertex as the root, and the left root as middle of left half and right root as middle of right half,
        then, our tree would be a BST with max difference between layers as 1 as, we always choose two vertices for a given vertex, and divide the vertices symetrically
        Here we can easily prove that the differnce is not more than 1 by contradiction, 
        {Contradiction to number of vertices being more than half in a single half of the tree, but we chose middle as the root. and recursively for all levels of tree}
    */
    static void MakeBST(int ind, int l , int r){
        if(l > r)
            return;
        if(l == r){
            bst[ind] = a[l];
            return;
        }

        int m = (l+r)/2;    

        bst[ind] = a[m];
        if(2*ind+1 <= 2*n)
            MakeBST(2*ind+1, l, m-1);
        if(2*ind+2 <= 2*n)
            MakeBST(2*ind+2, m+1, r);
        
    }


    public static void main(String args[]){
        Scanner in = new Scanner(System.in);

        n = in.nextInt();
        a = new int[n];
        bst = new int[2*n+1];

        for( int i = 0 ; i < n ; ++i){
            a[i] = in.nextInt();
        }

        QuickSort(0, n-1);

        // To find a node id that is not assigned to any vertex of graph. This would be needed at last while printing.
        int notPresent = 0; // this would contain an integer that isn't found in the array -- here it is given that numbers are distinct
        for(int i = 0 ; i < n ; ++i){
            if(a[i] == notPresent)
                notPresent++;
        }
        for(int i = 0 ; i <= 2*n ; ++i){
            bst[i] = notPresent;
        }
        MakeBST(0,0,n-1);
        for(int i = 0 ; i <= 2*n ; ++i){
           if(bst[i] != notPresent) //  when notPresent value is the value of an index, then we can conlcude that it remained unoccupied
            System.out.print(bst[i]+" ");
        }

    }
}

/*
    Logic behind 2*n and Final Printing: 

    As, I am making the tree in a very symmetric manner going layer by layer the number of nodes in layers starting from top are as 1, 2 , 4 ,8 ... 
    Hence, the only unoccupancy that might occur (when n != 2^i -1) would be in last layer, thus a max unoccupancy of n, so I have taken the bst array size as 2*n
    {As 2^i = (1 + 2 + ... 2^(i-1)) +1}
    Finally, I can print directly still keeping an AVL tree as, each layer except last is full(going level by level), so difference would be max equal to 1
*/