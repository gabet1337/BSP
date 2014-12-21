package cg;
import java.util.*;
class Segment {
	int id,x1,x2,y1,y2;
	public Segment(int id, int x1, int y1, int x2, int y2) {
		this.id = id; this.x1 = x1;	this.y1 = y1; this.x2 = x2; this.y2 = y2;
	}
	public String toString() {
		return id + " (" + x1 + ", " + y1 + "), (" + x2 + ", " + y2 + ")";
	}
}

class Node {
	int id;
	ArrayList<Segment> segments;
	Node left;
	Node right;
	void visit() {
		System.out.println("====== ID: " + id + " ======");
		if (left == null) System.out.println("left: null");
		else System.out.println("left: " + left.id);
		if (right == null) System.out.println("right: null");
		else System.out.println("right: " + right.id);
		for (Segment s : segments)
			System.out.println(s);
		System.out.println("=====================");
	}
}

class Interval {
	double start,end;
	Segment segment;
}

public class BSP {

	public void PreOrderTreeWalk(Node n) {
		if (n == null) return;
		n.visit();
		PreOrderTreeWalk(n.left);
		PreOrderTreeWalk(n.right);
	}
	
	public void InOrderTreeWalk(Node n) {
		if (n == null) return;
		InOrderTreeWalk(n.left);
		n.visit();
		InOrderTreeWalk(n.right);
	}
	
	public void PostOrderTreeWalk(Node n) {
		if (n == null) return;
		PostOrderTreeWalk(n.left);
		PostOrderTreeWalk(n.right);
		n.visit();
	}	
	
	public static final int NODE_SIZE = 1;
	int nextID = 0;
	private Node ConstructBSP2(ArrayList<Segment> S) {
		int cardinality = S.size();
		if (cardinality <= NODE_SIZE) {
			Node n = new Node();
			n.left = null;
			n.right = null;
			n.segments = S;
			n.id = nextID++;
			return n;
		} else {
			//TODO: If the segment intersects then it should be split and one should be added to each side
			ArrayList<Segment> S_plus = new ArrayList<Segment>();
			ArrayList<Segment> S_minus = new ArrayList<Segment>();
			ArrayList<Segment> S_contained = new ArrayList<Segment>();
			Segment s0 = S.get(0);
			S_contained.add(s0);
			int Ax = s0.x1, Ay = s0.y1, Bx = s0.x2, By = s0.x2;
			for (int i = 1; i < S.size(); i++) {
				int x1 = S.get(i).x1, y1 = S.get(i).y1, x2 = S.get(i).x2, y2 = S.get(i).y2;
				if ( (Bx-Ax)*(y1-Ay) - (By-Ay)*(x1-Ax) > 0 || (Bx-Ax)*(y2-Ay) - (By-Ay)*(x2-Ax) > 0 ) {
					S_plus.add(S.get(i));
				} 
				if ( (Bx-Ax)*(y1-Ay) - (By-Ay)*(x1-Ax) < 0 || (Bx-Ax)*(y2-Ay) - (By-Ay)*(x2-Ax) < 0 ) {
					S_minus.add(S.get(i));
				}
				if ( (Bx-Ax)*(y1-Ay) - (By-Ay)*(x1-Ax) == 0 && (Bx-Ax)*(y2-Ay) - (By-Ay)*(x2-Ax) == 0 ) {
					S_contained.add(S.get(i));
				}
			}
			Node T_plus = ConstructBSP2(S_plus);
			Node T_minus = ConstructBSP2(S_minus);
			
			Node v = new Node();
			v.left = T_minus;
			v.right = T_plus;
			v.id = nextID++;
			v.segments = S_contained;
			return v;
		}
	}
	
	public Node ConstructBSP(ArrayList<Segment> segments) {
		Collections.shuffle(segments);
		return ConstructBSP2(segments);
	}
	
	public List<Segment> GetVisibleSegmentsForPoint(Node root, int x, int y) {
		ArrayList<Segment> result = new ArrayList<Segment>();
		
		ArrayList<Interval> viewPoint = new ArrayList<Interval>();
		
		
		return result;
	}
	
	
	public static void main(String[] args) {
		BSP bsp = new BSP();
		Scanner in = new Scanner(System.in);
		String useless;
		int x1,x2,y1,y2;
		int id = 0;
		ArrayList<Segment> segments = new ArrayList<Segment>();
		while (in.hasNext()) {
			useless = in.next();
			x1 = in.nextInt();
			y1 = in.nextInt();
			x2 = in.nextInt();
			y2 = in.nextInt();
			segments.add(new Segment(id++,x1,y1,x2,y2));
		}
		Node root = bsp.ConstructBSP(segments);
		bsp.PreOrderTreeWalk(root);
	}

}















