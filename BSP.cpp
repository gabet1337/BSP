#include <iostream>
#include <cmath>
#include <algorithm>
#include <vector>
#include <string>
#include <stdlib.h>

#define NODE_SIZE 1

using namespace std;

struct Segment {
  int x1,y1,x2,y2,id;
  Segment(int _id, int _x1, int _y1, int _x2, int _y2) : id(_id), x1(_x1), y1(_y1), x2(_x2), y2(_y2) {}
};

ostream& operator<<(ostream& o, Segment &s) {
  return o << s.id << " (" << s.x1 << ", " << s.y1 << "), (" << s.x2 << ", " << s.y2 << ")" << endl;
}

struct Node {
  int id;
  vector<Segment> *segments;
  Node *left;
  Node *right;
  void visit() {
    vector<Segment> s = *segments;
    cout << id << endl;
    cout << s.size() << endl;
    for (size_t i = 0; i < s.size(); i++)
      cout << s[i] << " ";
    cout << endl;
  }
};

ostream& operator<<(ostream& o, Node &n) {
  return o << n.id << endl;
}

void InOrderTreeWalk(Node *n) {
  if ( !n ) return;
  n->visit();
  InOrderTreeWalk(n->left);
  InOrderTreeWalk(n->right);
}

int nextId = 0;
Node* BSP(vector<Segment> S) {

  size_t cardinality = S.size();
  cout << "cardinality: " << cardinality << endl;

  if (cardinality <= NODE_SIZE) {
    // Create a tree T consisting of a single leaf node, where the set S is stored explicitly
    // return T

    Node *n;
    n->left = 0; // no children
    cout << "here" << endl;
    n->right = 0; // no children
    n->segments = &S;
    n->id = nextId++;

    return n;
  } else {
    // Use l(s1) as the splitting line
    // S+ = {s intersect l(s1)+ : s in S}; T+ = BSP(S+)
    // S- = {s intersect l(s1)- : s in S}; T- = BSP(S-)
    // Create a BSP tree T with root node v, left subtree T-, right subtree T+
    // and with S(v) = {s in S : s sub l(s1)}
    // return T

    vector<Segment> S_plus;
    vector<Segment> S_minus;
    Segment s0 = S[0];
    int Ax = s0.x1, Ay = s0.y1, Bx = s0.x2, By = s0.y2;
    for (size_t i = 1; i < S.size(); i++) {
      int x1 = S[i].x1, y1 = S[i].y1, x2 = S[i].x2, y2 = S[i].y2;
      if ( (Bx-Ax)*(y1-Ay) - (By-Ay)*(x1-Ax) > 0 || (Bx-Ax)*(y2-Ay) - (By-Ay)*(x2-Ax) > 0 ) {
        S_plus.push_back(S[i]);
      }
      if ( (Bx-Ax)*(y1-Ay) - (By-Ay)*(x1-Ax) < 0 || (Bx-Ax)*(y2-Ay) - (By-Ay)*(x2-Ax) < 0 ) {
        S_minus.push_back(S[i]);
      }
    }

    Node* T_plus = BSP(S_plus);
    Node* T_minus = BSP(S_minus);

    Node* v;
    v->left = T_minus;
    v->right = T_plus;
    v->id = nextId++;
    v->segments = &S;
    
    return v;
  }
}

int main() {
  int x1,x2,y1,y2;
  vector<Segment> segments;
  int id = 0;
  string useless;
  while (cin >> useless >> x1 >> y1 >> x2 >> y2) {
    segments.push_back(Segment(id++,x1,y1,x2,y2));
  }
  //random shuffle the input segments to avoid bad splitting lines
  random_shuffle(segments.begin(), segments.end());
  for (size_t i = 0; i < segments.size(); i++)
    cout << segments[i] << endl;

  Node* root = BSP(segments);
  cout << "STARTING WALK" << endl;
  InOrderTreeWalk(root);
  return 0;
}
