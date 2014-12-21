package cg;

public class Point {

	public int id = -1;
	public int x,y;
	
	public Point(int id, int x, int y) {
		this.id = id;
		this.x = x;
		this.y = y;
	}
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public String toString() {
		if (id != -1) return id + ": " + x + " " + y;
		return x + " " + y;
	}
	
}
