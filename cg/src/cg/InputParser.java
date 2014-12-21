package cg;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;

import javax.swing.JFrame;
public class InputParser {

	public static final int SIZE = 500;
	
	public static void main(String[] args) throws InterruptedException {
		
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ViewPanel vp = new ViewPanel();
		vp.setPreferredSize(new Dimension(SIZE,SIZE));
		frame.getContentPane().add(vp, BorderLayout.CENTER);
		frame.setBackground(Color.WHITE);
		frame.getContentPane().addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				System.out.println("CLICK: " + e.getX() + " " + e.getY());
				
				int id = vp.DrawPoint(e.getX(), e.getY());
				System.out.println(id);
				vp.revalidate();
				vp.repaint();
			}
		});
		frame.pack();
		frame.setVisible(true);
		
		Scanner in = new Scanner(System.in);
		boolean shouldRun = true;
		Node BSPTree; // the root of the generated BSP
		while (shouldRun) {
			//System.out.println("enter a command");
			String command = in.next();
			int x1, x2, y1, y2;
			int id, colorCode;
			switch (command) {
			case "close":
				shouldRun = false;
				break;
			case "draw-line":
				//System.out.println("Enter the points for this line");
				x1 = in.nextInt();
				y1 = in.nextInt();
				x2 = in.nextInt();
				y2 = in.nextInt();
				id = vp.DrawLineSegment(x1, y1, x2, y2);
				vp.revalidate();
				vp.repaint();
				System.out.println(id);
				break;
			case "edit":
				id = in.nextInt();
				x1 = in.nextInt();
				y1 = in.nextInt();
				x2 = in.nextInt();
				y2 = in.nextInt();
				System.out.println(vp.EditLineSegment(id, x1, y1, x2, y2));
				vp.revalidate();
				vp.repaint();
				break;
			case "color":
				id = in.nextInt();
				colorCode = in.nextInt();
				System.out.println(vp.ColorLineSegment(0, colorCode));
				vp.revalidate();
				vp.repaint();
				break;
			case "draw-point":
				x1 = in.nextInt();
				y1 = in.nextInt();
				id = vp.DrawPoint(x1, y1);
				System.out.println(id);
				vp.revalidate();
				vp.repaint();
				break;
			case "delete":
				id = in.nextInt();
				System.out.println(vp.DeleteObjectWithID(id));
				vp.revalidate();
				vp.repaint();
				break;
			case "write-lines":
				vp.WriteAllLineSegments();
				break;
			case "write-points":
				vp.WriteAllPoints();
				break;
			case "construct-bsp":
				BSPTree = vp.ConstructBSP();
			default:
				//System.out.println("Invalid command " + command);
				break;
			}
			Thread.sleep(10);
		}
		in.close();
			
	}
	
}
