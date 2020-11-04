/**********************************

	CSN-261 Lab 6 Question 1 : Doubly Linked List
	P1.cpp
	Shreyas Dodamani
	19114079
	shreyas_d@cs.iitr.ac.in 

**********************************/

#include <iostream>
using namespace std;

class List {
  private:
    struct Node { // TO STORE ELEMENTS OF LIST
      int id; 
      struct Node* next;
      struct Node* second;
    };
    int n; // NUMBER OF NODES IN LIST
    int number_of_cycles; // TO COUNT NUMBER OF CYCLES IN LIST
    struct Node* head; // HEAD NODE OF LIST
    struct Node* curr_cycle_root; // UPDATED DURING ITERATION THROUGH LIST -- TO KEEP TRACK OF ROOT NODE OF CYCLE
    struct Node* cycle_nodes; // STORES ALL NODES IN THE CURRENT CYCLE IN LIFO ORDER, BUT ORDER IS FIXED WHILE PRINTING OUTPUT
    string cycles_output; // STORES OUTPUT STRING OF ALL CYCLES, WHICH IS FINALLY PRINTED AT THE END
    bool *vis; // ARRAY TO STORE WHICH IVISTED HAS ALREADY BEEN VISITED
  public:
    List (int nodes) { // INITIALISE LINKED LIST 
      n = nodes;
      this->cycle_nodes = NULL;
      this->cycles_output = "";
      this->number_of_cycles = 0;
    }
    /**
     * IF WE ARE CREATING A NEW LINKED LIST, THEN GENERATE ALL NODES ( WHERE HEAD NODE IS NULL )
     * OR, INPUT THE HEAD NODE OF THE DUPLICATED LINKED LIST
     **/
    void generate_list (struct Node *new_head) {       
      if (new_head == NULL) {
        head = new Node();
        head->id = 1;
        head->next = NULL;
        struct Node *curr = head;
        struct Node *temp;
        for (int i=2; i<=n; i++) {
          temp = new Node();
          temp->id = i;
          temp->next = NULL;
          temp->second = NULL;
          curr->next = temp;
          curr = curr->next;
        }
      } else {
        head = new_head;
      }
    }

    /**
     * TO BE USED TO ASSIGN SECOND POINTERS WHEN 
     * TAKING NEW LINKED LIST AS INPUT
     **/
    void add_second_pointer (int node, int second) {
      if (1 <= node && node <= n && 1 <= second && second <= n ) { // CHECK TO SEE IF NODE VALUES ARE VALID
        // LOCATE SOURCE NODE
        struct Node *curr = head;
        while (curr->id != node) {
          curr = curr->next;
        }
        // LOCATE SECOND POINTER NODE
        struct Node *sec = head;
        while (sec->id != second) {
          sec = sec->next;
        }
        // ASSIGN VALUE OF SECOND POINTER AS THE SECOND NODE
        curr->second = sec;
      }
      return;
    }
    
    /**
     * FIRST DUPLICATE EACH NODE OF THE ORIGINAL LIST SUCH THAT THE LIST LOOKS LIKE :
     *    1->1->2->2->3->3->....->n->n->NULL
     * THEN FOR THE FIRST NODE OF EACH PAIR
     *    node->next->second = node->second->next;
     * THIS WILL CREATE A SINGLE LIST, WITH TWO DIFFERENT LISTS WEAVED INTO EACH OTHER
     * FINALLY, WE SEPARATE THE LINKED LISTS AND RETURN THE POINTER TO THE HEAD NODE OF THE DUPLICATE LIST
     **/
    struct Node* duplicate_list () {
      struct Node *curr = this->head;
      struct Node *temp = NULL;
      while (curr != NULL) {
        temp = new Node(); // CREATE NEW NODE TO BE INSERTED BETWEEN 1 AND 2
        temp->id = curr->id; // COPY ID VALUE
        temp->next = curr->next; // INSERTED NODE WITH VALUE i BETWEEN i AND i+1
        curr->next = temp; 
        curr = temp->next; // MOVE ITERATOR TO NODE WITH ID i+1 ( OR NULL IF LAST NODE )
      }

      curr = this->head;
      while (curr != NULL) { // SET ALL NULL POINTERS
        curr->next->second = curr->second->next;
        curr = curr->next->next;  // SKIP FROM NODE i to i+1 IN THE LIST WITH NODES 
                                  // i->i->i+1
                                  // ^
                                  //   to
                                  // i->i->i+1
                                  //        ^
      }

      curr = this->head;
      temp = this->head->next;
      struct Node *copy = this->head->next; // NODE TO BE RETURNED

      // SPLIT THE TWO LISTS
      while (curr != NULL && temp != NULL) {
        if (curr->next != NULL) {
          curr->next = curr->next->next;
        }
        if (temp->next != NULL) {
          temp->next = temp->next->next;
        }
        curr = curr->next;
        temp = temp->next;
      }
      return copy; // RETURN POINTER TO HEAD OF THE DUPLICATE ARRAY
    }

    void show_list () {
      struct Node *curr = this->head;
      cout << "Node " << "Next " << "Second\n";
      cout << "-----" << "-----" << "------\n";
      while (curr != NULL) {
        cout << curr->id << " "; 

        if (curr->next != NULL) cout << curr->next->id << " ";
        else cout << "NONE ";

        if (curr->second != NULL) cout << curr->second->id << " ";
        else cout << "NONE ";
        
        cout << "\n";
        curr = curr->next;
      }
    }

    void delete_node (int node) {
      struct Node* curr = this->head;

      // ITERATE THROUGH THE ARRAY AND SET SECOND POINTERS
      while (curr != NULL) {
        if (curr->second != NULL && curr->second->id == node) {
          // IF SECOND POINTER IS THE NODE TO BE DELETED, SET THE SECOND POINTER TO THE second->second
          curr->second = curr->second->second;
        }
        curr = curr->next;
      }

      curr = this->head;
      if (this->head->id == node) {
        // IF HEAD NODE IS DELETED, THEN MOVE HEAD POINTER FORWARD ONCE
        this->head = this->head->next;
      } else {

        while (curr != NULL) {
          if (curr->next != NULL && curr->next->id == node) {
            curr->next = curr->next->next;
            return;
          }
          curr = curr->next;
        }
      }
    }

    /**
     * FUNCTION TO RETURN INDEX CORRESPONDING TO THE CURRENT NODE ID
     * FOR ARRAY REFERENCE, SO IN COMPLEX METHODS, IT IS EASIER TO UNDERSTAND
     * WHAT i AM DOING BY SEEING FUNCTION CALL
     **/
    int get_index_from_id (int id) {
      return id-1;
    }

    /**
     * COUNTS THE NUMBER OF REFERENCES FOR EACH NODE, AND THEN ITERATES THROUGH THAT 
     * ARRAY TO FIND MAXIMUM NUMBER OF REFERENCES
     * ONE FINAL ITERATION THROUGH THAT ARRAY TO PRINT ALL NODES WITH MAXIMUM NUMBER
     * OF REFERENCES
     **/
    void get_highest_referenced_nodes () {
      int a[this->n] = {0}; // ALL REFERENCES INITIALLY ZERO
      int max_references = 0;

      // ITERATE THROUGH THE LIST AND INCREASE THE REFERENCE COUNT BY ONE IF
      // NODE THAT IT IS POINTING TO IS REFERENCED EITHER THROUGH NEXT OR SECOND POINTER
      struct Node *curr = this->head;
      while (curr != NULL) {
        if (curr->next != NULL) a[ get_index_from_id(curr->next->id) ]++;
        if (curr->second != NULL) a[ get_index_from_id(curr->second->id) ]++;
        curr = curr->next;
      }

      // GET MAX COUNT
      for (int i=0; i<n; i++) {
        if (a[i] > max_references) max_references = a[i];
      }

      cout << "\nThe nodes having maximum references of " << max_references << " references before node deletion are: ";
      for (int id=1; id<=n; id++) {
        // REFERENCE COUNT IS EQUAL TO MAX COUNT, PRINT NODE ID
        if (a[ get_index_from_id(id) ] == max_references) cout << id << " ";
      }

      cout << "\n";
    }
    /**
     * THE CYCLE IS REPRESENTED BY A STACK FOR O(1) PUSH AND POP
     * SINCE POP WOULD BE IN LIFO ORDER, I PRINT THE NODE VALUES 
     * IN REVERSE ORDER OF POP
     **/
    void push_to_cycle (int id) {
      // CREATE A NODE WITH THE SAME ID TO BE PUSHED TO THE CYCLE
      struct Node *node = new Node();
      node->id = id;
      if (this->cycle_nodes == NULL) {
        node->next = NULL;
        this->cycle_nodes = node;
      } else {
        node->next = this->cycle_nodes;
        this->cycle_nodes = node;
      }
    }

    void pop_from_cycle () {
      if (this->cycle_nodes == NULL) return;
      if (this->cycle_nodes->next == NULL) this->cycle_nodes = NULL;
      else this->cycle_nodes = this->cycle_nodes->next;
      return;
    }

    void print_cycle () {
      struct Node *c = this->cycle_nodes;
      if (c == NULL) return; // IF CYCLE EMPTY, DON'T PRINT ANYTHING

      string op = to_string(this->curr_cycle_root->id); // START STRING WITH THE ROOT NOOD OF CYCLE, BECAUSE IT APPEARS FIRST AND LAST

      while (c != NULL) {
        op = to_string(c->id) + " " + op; // TO COMPENSATE FOR LIFO ORDER
        c = c->next;
      }
      
      this->cycles_output += op + "\n"; // STORE CYCLE IN STATE VARIABLE
      this->number_of_cycles++;
      return;
    }

    void get_cycles_for_node (Node *node) {
      // IF CYCLE SEARCH HAS ALREADY BEEN PERFORMED WITH THIS NODE AS ROOT, WE
      // MUST NNOT EXPLORE THIS PATH BECAUSE THAT WOULD GIVE DUPLICATE CYCLES
      if (node->id < this->curr_cycle_root->id) return; 

      // GET INDEX VALUE FOR ACCESSING ARRAY
      int index = get_index_from_id(node->id);

      if (vis[index]) {
        // IF ALREADY VISITED IT MEANS CYCLE IS FOUND.
        // HOWEVER, WE ARE ONLY LOOKING FOR THOSE CYCLES WHICH START
        // WITH THE CURRENT CYCLE ROOT
        if (this->curr_cycle_root == node) {
          this->print_cycle();
        }
        return;
      } else {
        // SET VISITED TO TRUE
        vis[index] = true;
        this->push_to_cycle(node->id);
        
        // IF NEXT NODE EXISTS, RECURSE THROUGHT THAT NODE
        if (node->next != NULL) {
          get_cycles_for_node(node->next);
        }

        // IF SECOND NODE EXISTS, AND IT IS NOT THE SAME AS NEXT NODE, RECURSE THROUGH THAT
        // IT MUST BE DIFFERENT FROM THE NEXT NODE BECAUSE WE WANT UNIQUE CYCLES
        if (node->second != NULL) {
          if (node->next == NULL || node->next != node->second) {
            get_cycles_for_node(node->second);
          }
        }

        // MARK AS UNVISITED, BECAUSE IF THERE EXISTS A DIFFERENT CYCLE WITH THIS
        // NODE PRESENT AND CURRENT CYCLE ROOT AS THE ROOT NODE WE WOULD LIKE TO 
        // COUNT THAT TOO
        vis[index] = false;
        this->pop_from_cycle();
      }
    }

    void get_all_cycles () {
      this->vis = new bool[this->n];
      for (int i=0; i<n; i++) vis[i] = false;

      Node *curr = this->head;
      while (curr != NULL) {
        this->curr_cycle_root = curr;
        get_cycles_for_node(curr);
        curr = curr->next;
      }
      cout << "\nTotal number of unique cycles are: " << this->number_of_cycles << "\n";
      cout << this->cycles_output;
      return;
    }
};

int main() 
{
  int n;
  cin >> n;

  List list(n);
  list.generate_list(NULL);

  int node, next, second;
  for (int i=0; i<n; i++) {
    cin >> node >> next >> second;
    list.add_second_pointer(node, second);
  }
  
  // DUPLICATE
  List duplicate(n);
  duplicate.generate_list(list.duplicate_list());

  cout << "\nOriginal List:\n";
  list.show_list();
  cout << "\nDuplicate List:\n";
  duplicate.show_list();
  

  // DELETION
  int node_to_be_deleted;
  cin >> node_to_be_deleted;

  duplicate.delete_node(node_to_be_deleted);
  
  cout << "\nAfter deleting node number: " << node_to_be_deleted << "\n";
  duplicate.show_list();


  // HIGHEST REFERENCED
  list.get_highest_referenced_nodes();

  // CYCLES
  list.get_all_cycles();
  
  cout << "\n";
	return 0;
}
