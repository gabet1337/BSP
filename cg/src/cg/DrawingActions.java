package cg;

public interface DrawingActions {
	
	/**
	 * Draws a line segment from (x1,y1) to (x2,y2)
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @return id of the newly drawn line segment
	 */
	public int DrawLineSegment(int x1, int y1, int x2, int y2);
	
	/**
	 * Edits the line segment with the given id to the newly given points
	 * @param id
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @return true if line segment was successfully edited, otherwise false
	 */
	public boolean EditLineSegment(int id, int x1, int y1, int x2, int y2);
	
	/**
	 * Colors the line segment with the given id to the color code given
	 * @param id
	 * @param colorCode
	 * @return true if the line segment was successfully colored
	 */
	public boolean ColorLineSegment(int id, int colorCode);
	
	/**
	 * Draws a point with the given coordinate (x,y)
	 * @param x
	 * @param y
	 * @return the id of the newly drawn point
	 */
	public int DrawPoint(int x, int y);
	
	/**
	 * Deletes the line segment or point with the given id
	 * @param id
	 * @return true if deleted or false otherwise
	 */
	public boolean DeleteObjectWithID(int id);
	
	/**
	 * Writes all line segments to standard out
	 */
	public void WriteAllLineSegments();
	
	/**
	 * Writes all points to standard out
	 */
	public void WriteAllPoints();
	
}
