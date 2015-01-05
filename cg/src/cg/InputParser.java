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
	
	public static void parse(Scanner in) throws InterruptedException {
		
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
		
		//Scanner in = new Scanner("Hello");
		//Scanner in = new Scanner(System.in);
		boolean shouldRun = true;
		Node BSPTree = null; // the root of the generated BSP
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
				//System.out.println(id);
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
				System.out.println(vp.ColorLineSegment(id, colorCode));
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
				break;
			case "std-input-to-std-in":
				in = new Scanner(System.in);
				break;
			case "pre-order-tree-walk":
				Node root = BSPTree;
				BSP bsp = new BSP();
				bsp.PreOrderTreeWalk(root);
				break;
			case "painters":
				Thread.sleep(100);
				int x = in.nextInt(), y = in.nextInt();
				vp.DrawPoint(x, y);
				BSP bsp2 = new BSP();
				bsp2.PaintersAlgorithm(BSPTree, x, y);
				
				for (Interval1D i : bsp2.GetVisibleSegments(x, y)) {
					System.out.println(i);
					double sx1 = i.segment.x1, sx2 = i.segment.x2, sy1 = i.segment.y1, sy2 = i.segment.y2;
					double vx2 = x + Math.cos(i.low), vy2 = y + Math.sin(i.low);
					// x1 = sx1, y1 = sy1, x2 = sx2, y2 = sy2
					// x3 = x, y3 = y, x4 = vx2, y4 = vy2
					double Px1 = ((sx1*sy2-sy1*sx2)*(x-vx2)-(sx1-sx2)*(x*vy2-y*vx2)) / ((sx1-sx2)*(y-vy2)-(sy1-sy2)*(x-vx2));
					double Py1 = ((sx1*sy2-sy1*sx2)*(y-vy2)-(sy1-sy2)*(x*vy2-y*vx2)) / ((sx1-sx2)*(y-vy2)-(sy1-sy2)*(x-vx2));
					
					vx2 = x + Math.cos(i.high); vy2 = y + Math.sin(i.high);
					double Px2 = ((sx1*sy2-sy1*sx2)*(x-vx2)-(sx1-sx2)*(x*vy2-y*vx2)) / ((sx1-sx2)*(y-vy2)-(sy1-sy2)*(x-vx2));
					double Py2 = ((sx1*sy2-sy1*sx2)*(y-vy2)-(sy1-sy2)*(x*vy2-y*vx2)) / ((sx1-sx2)*(y-vy2)-(sy1-sy2)*(x-vx2));

					id = vp.DrawLineSegment((int)Px1, (int)Py1, (int)Px2, (int)Py2);
					vp.ColorLineSegment(id, 16711680);
					
				}
				break;
			case "visibility":
				BSP bsp3 = new BSP();
			default:
				//System.out.println("Invalid command " + command);
				break;
			}
			Thread.sleep(50);
		}
		in.close();
		
	}
	
	public static void main(String[] args) throws InterruptedException {
		parse(new Scanner(System.in));
	}
	
}
