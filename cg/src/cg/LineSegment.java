package cg;

import java.awt.Color;

public class LineSegment {

	int id;
	Point p,q;
	Color color;
	
	public LineSegment(int id, Point p, Point q, Color c) {
		this.id = id;
		this.p = p;
		this.q = q;
		this.color = c;
	}
	
	public String toString() {
		return id + ": " + p.toString() + " " + q.toString();		
	}
	
}
