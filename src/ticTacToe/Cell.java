package ticTacToe;



import javafx.scene.control.*;



public class Cell extends Button{

	
	private int BIG_ROW;
	private int BIG_COL;
	private int ROW;
	private int COL;
	
	public Cell(int BIG_ROW, int BIG_COL, int ROW, int COL) {
		this.BIG_ROW = BIG_ROW;
		this.BIG_COL = BIG_COL;
		this.ROW = ROW;
		this.COL = COL;
	}
	public Cell() {
		
	}
	
	
	public int getBR() {
		return this.BIG_ROW;
	}
	
	public int getBC() {
		return this.BIG_COL;
	}
	
	public int getR() {
		return this.ROW;
	}
	
	public int getC() {
		return this.COL;
	}


}
