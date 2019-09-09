package ticTacToe;

import javafx.scene.control.Button;

public class UCell extends Button {
	
	private int giantRow;
	private int giantCol;
	private int bigRow;
	private int bigCol;
	private int row;
	private int col;
	
	public UCell(int giantRow, int giantCol, int bigRow, int bigCol, int row, int col) {
		this.giantRow = giantRow;
		this.giantCol = giantCol;
		this.bigRow = bigRow;
		this.bigCol = bigCol;
		this.row = row;
		this.col = col;
	}
	public UCell() {
		
	}
	
	
	
	public int getGR() {
		return giantRow;
	}
	
	public int getGC() {
		return giantCol;
	}
	
	public int getBR() {
		return bigRow;
	}
	
	public int getBC() {
		return bigCol;
	}
	
	public int getR() {
		return row;
	}
	
	public int getC() {
		return col;
	}
	
}
