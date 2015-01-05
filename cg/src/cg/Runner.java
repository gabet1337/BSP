package cg;

import java.util.Scanner;

public class Runner {

	public static void main(String[] args) throws InterruptedException {
		String poly = "draw-line 0 500 222 231\ndraw-line 222 231 0 0\ndraw-line 0 0 181 132\ndraw-line 181 132 234 202\ndraw-line 234 202 344 86\ndraw-line 344 86 500 0\ndraw-line 500 0 456 246\ndraw-line 456 246 500 500\ndraw-line 500 500 0 500\n";
		poly = poly + "construct-bsp\npre-order-tree-walk\npainters 138 125\nstd-input-to-std-in\n";
		
		String square = "draw-line 1 0 0 1 draw-line 0 1 -1 0 draw-line -1 0 0 -1 draw-line 0 -1 1 0 construct-bsp pre-order-tree-walk painters 0 0 std-input-to-std-in";
		InputParser.parse(new Scanner(poly));
		//InputParser.parse(new Scanner(square));
	}
}
