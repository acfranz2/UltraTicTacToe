package ticTacToe;


import javafx.scene.shape.*;
import javafx.scene.paint.Color;

public class X extends Polygon{
	
	private Line down;
	private Line up;
	private Shape ex;
	private Color c;
	
	public X(double height, double x, double y, double strokeWidth, Color c) {
		this.down = new Line(x, y, x + height, y+height);
		this.up = new Line(x, y+height, x + height, y);
		this.c = c;
		down.setStrokeWidth(strokeWidth); down.setStroke(c);
		up.setStrokeWidth(strokeWidth); up.setStroke(c);
		ex = new Line();
		
	}
	
	public Shape draw() {
		ex = X.union(up, down);
		return ex;
	}
	
	public Shape vis(boolean seen) {
		ex.setVisible(seen);
		ex.setFill(c);;
		return ex;
	}
}
