#include <iostream>
#include <cmath>
#include <algorithm>
#include <random>
#include <vector>
using namespace std;

//algorithm:
//place n random points in the square as well as the corners.
//sort them radially around the center of the square
//connect the points in that order

int SQUARE_SIZE; // most like 500

bool radial(pair<int,int> a, pair<int,int> b)
{
  pair<int,int> center = make_pair(SQUARE_SIZE/2, SQUARE_SIZE/2);
  if (a.first - center.first >= 0 && b.first - center.first < 0)
    return true;
  if (a.first - center.first < 0 && b.first - center.first >= 0)
    return false;
  if (a.first - center.first == 0 && b.first - center.first == 0) {
    if (a.second - center.second >= 0 || b.second - center.second >= 0)
      return a.second > b.second;
    return b.second > a.second;
  }

  // compute the cross product of vectors (center -> a) x (center -> b)
  int det = (a.first - center.first) * (b.second - center.second) - (b.first - center.first) * (a.second - center.second);
  if (det < 0)
    return true;
  if (det > 0)
    return false;

  // points a and b are on the same line from the center
  // check which point is closer to the center
  int d1 = (a.first - center.first) * (a.first - center.first) + (a.second - center.second) * (a.second - center.second);
  int d2 = (b.first - center.first) * (b.first - center.first) + (b.second - center.second) * (b.second - center.second);
  return d1 > d2;
}

int main() {

  int n;
  cin >> n >> SQUARE_SIZE;
  vector<pair<int, int> > points;
  points.push_back(make_pair(0,0));
  points.push_back(make_pair(0,SQUARE_SIZE));
  points.push_back(make_pair(SQUARE_SIZE, 0));
  points.push_back(make_pair(SQUARE_SIZE, SQUARE_SIZE));
  default_random_engine generator;
  uniform_int_distribution<int> distribution(0,SQUARE_SIZE);
  for (size_t i = 0; i < n; i++) {
    int x = distribution(generator);
    int y = distribution(generator);
    points.push_back(make_pair(x,y));
  }

  sort(points.begin(), points.end(),radial);
  for (size_t i = 0; i < points.size(); i++) {
    if (i)
      cout << points[i].first << " " << points[i].second << endl;
    cout << "draw-line ";
    cout << points[i].first << " " << points[i].second << " ";
  }
  cout << points[0].first << " " << points[0].second << endl;

  return 0;
}
