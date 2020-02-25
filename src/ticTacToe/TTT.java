package ticTacToe;

import javafx.application.*;
import javafx.event.*;
import javafx.scene.*;
import javafx.scene.shape.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.*;

public class TTT extends Application{

		
	private int count = 0;
	private int[][][][] score = new int[3][3][3][3];
	private Button[][][][] btn;
	private double h;
	private Circle[][][][] crcs;
	private X[][][][] exes;
	private X[][] largeX;
	private Circle[][] largeCrc;
	private int[][] bigScore = new int[3][3];
	
	
	public void start(Stage primaryStage) throws Exception {

		/*
			[[ 1, 2, 3],
			[4, 5, 6]]

		*/
		
		
		Group root = new Group();
		GridPane btns = new GridPane();
		Scene scene = new Scene(root, 612, 612);
		
		h = scene.getHeight();
		double buf = h/100;

		
		
		Line leftLine = new Line(h/3, 0, h/3, h);
		Line rightLine = new Line(2*h/3, 0, 2*h/3, h); 
		Line topLine = new Line(0, h/3, h, h/3);
		Line botLine = new Line(0, 2*h/3, h, 2*h/3);
		
		///////// Tiny Boards
		Line l1 = new Line(h/9, buf, h/9, h/3-buf);
		Line l2 = new Line(2*h/9, buf, 2*h/9, h/3-buf);
		Line l3 = new Line(buf, h/9, h/3-buf, h/9);
		Line l4 = new Line(buf, 2*h/9, h/3-buf, 2*h/9);
		
		Shape mini1 = new Line(); Shape mini2 = new Line(); Shape miniBoard = new Line();
		mini1 = mini1.union(l1, l2);
		mini2 = mini2.union(l3, l4);
		miniBoard = miniBoard.union(mini1, mini2);
		
		Line l5 = new Line(4*h/9, buf, 4*h/9, h/3-buf);
		Line l6 = new Line(5*h/9, buf, 5*h/9, h/3-buf);
		Line l7 = new Line(h/3+buf, h/9, 2*h/3-buf, h/9);
		Line l8 = new Line(h/3+buf, 2*h/9, 2*h/3-buf, 2*h/9);
		
		Line l9 = new Line(7*h/9, buf, 7*h/9, h/3-buf);
		Line l10 = new Line(8*h/9, buf, 8*h/9, h/3-buf);
		Line l11 = new Line(2*h/3+buf, h/9, h-buf, h/9);
		Line l12 = new Line(2*h/3+buf, 2*h/9, h-buf, 2*h/9);
		
		Line l13 = new Line(h/9, h/3+buf, h/9, 2*h/3-buf);
		Line l14 = new Line(2*h/9, h/3+buf, 2*h/9, 2*h/3-buf);
		Line l15 = new Line(buf, 4*h/9, h/3-buf, 4*h/9);
		Line l16 = new Line(buf, 5*h/9, h/3-buf, 5*h/9);
		
		Line l17 = new Line(4*h/9, h/3+buf, 4*h/9, 2*h/3-buf);
		Line l18 = new Line(5*h/9, h/3+buf, 5*h/9, 2*h/3-buf);
		Line l19 = new Line(h/3+buf, 4*h/9, 2*h/3-buf, 4*h/9);
		Line l20 = new Line(h/3+buf, 5*h/9, 2*h/3-buf, 5*h/9);
		
		Line l21 = new Line(7*h/9, h/3+buf, 7*h/9, 2*h/3-buf);
		Line l22 = new Line(8*h/9, h/3+buf, 8*h/9, 2*h/3-buf);
		Line l23 = new Line(2*h/3+buf, 4*h/9, h-buf, 4*h/9);
		Line l24 = new Line(2*h/3+buf, 5*h/9, h-buf, 5*h/9);
		
		Line l25 = new Line(h/9, 2*h/3+buf, h/9, h-buf);
		Line l26 = new Line(2*h/9, 2*h/3+buf, 2*h/9, h-buf);
		Line l27 = new Line(buf, 7*h/9, h/3-buf, 7*h/9);
		Line l28 = new Line(buf, 8*h/9, h/3-buf, 8*h/9);
		
		Line l29 = new Line(4*h/9, 2*h/3+buf, 4*h/9, h-buf);
		Line l30 = new Line(5*h/9, 2*h/3+buf, 5*h/9, h-buf);
		Line l31 = new Line(h/3+buf, 7*h/9, 2*h/3-buf, 7*h/9);
		Line l32 = new Line(h/3+buf, 8*h/9, 2*h/3-buf, 8*h/9);
		
		Line l33 = new Line(7*h/9, 2*h/3+buf, 7*h/9, h-buf);
		Line l34 = new Line(8*h/9, 2*h/3+buf, 8*h/9, h-buf);
		Line l35 = new Line(2*h/3+buf, 7*h/9, h-buf, 7*h/9);
		Line l36 = new Line(2*h/3+buf, 8*h/9, h-buf, 8*h/9);
		
		crcs = new Circle[3][3][3][3];
		for(int i = 0; i < crcs.length; i++)
			for(int j = 0; j < crcs.length; j++)
				for(int k = 0; k < crcs.length; k++)
					for(int l = 0; l < crcs.length; l++) {
						crcs[i][j][k][l] = new Circle(h/18 - buf);
						
						root.getChildren().add(crcs[i][j][k][l]);
						
						crcs[i][j][k][l].setLayoutX((l*h/9)+(j*h/3) + (h/18));
						crcs[i][j][k][l].setLayoutY((k*h/9)+(i*h/3) + (h/18));
						
						crcs[i][j][k][l].setFill(Color.TRANSPARENT);
						crcs[i][j][k][l].setStroke(Color.BLACK);
						crcs[i][j][k][l].setStrokeWidth(2);
						
						crcs[i][j][k][l].setVisible(false);
		}
		
		
		largeCrc = new Circle[3][3];
		for(int rowC = 0; rowC < largeCrc.length; rowC++)
			for(int colC = 0; colC < largeCrc.length; colC++) {
				largeCrc[rowC][colC] = new Circle(h/6 - buf);
				
				largeCrc[rowC][colC].setFill(Color.TRANSPARENT);
				largeCrc[rowC][colC].setStroke(Color.BLACK);
				largeCrc[rowC][colC].setStrokeWidth(2);
				
				root.getChildren().add(largeCrc[rowC][colC]);
				
				largeCrc[rowC][colC].setLayoutX(colC*h/3 + h/6);
				largeCrc[rowC][colC].setLayoutY(rowC*h/3 + h/6);
				
				
				largeCrc[rowC][colC].setVisible(false);
		}
		
		
		exes = new X[3][3][3][3];
		for(int m = 0; m < crcs.length; m++)
			for(int n = 0; n < crcs.length; n++)
				for(int o = 0; o < crcs.length; o++)
					for(int p = 0; p < crcs.length; p++) {
						exes[m][n][o][p] = new X(h/9-2*buf, (p*h/9)+(n*h/3) + buf, (o*h/9)+(m*h/3) + buf, 2, Color.BLACK);	
						root.getChildren().add(exes[m][n][o][p].draw());
						exes[m][n][o][p].vis(false);
		}
		
		largeX = new X[3][3];
		for(int rowX = 0; rowX < largeX.length; rowX++)
			for(int colX = 0; colX < largeX.length; colX++) {
				largeX[rowX][colX] = new X(h/3-2*buf, colX*h/3 + buf, rowX*h/3 + buf, 2, Color.DARKMAGENTA);
				root.getChildren().add(largeX[rowX][colX].draw());
				largeX[rowX][colX].vis(false);
		}
		
		
		
		
		
		
		
		
		
		root.getChildren().addAll(leftLine, rightLine, topLine, botLine, btns, miniBoard, l5, l6, l7, l8, l9, l10, l11, l12, l13, l14, l15, l16, 
				l17, l18, l19, l20, l21, l22, l23, l24, l25, l26, l27, l28, l29, l30, l31, l32, l33, l34, l35, l36);
		
		
		btn = new Button[3][3][3][3];
		for(int bigRow = 0; bigRow < btn.length; bigRow++) 
			for(int bigCol = 0; bigCol < btn.length; bigCol++) 
				for(int row = 0; row < btn[0].length; row +=1) 
					for(int col = 0; col < btn[0][0].length; col++) {
						btn[bigRow][bigCol][row][col] = new Cell(bigRow, bigCol, row, col);
						btn[bigRow][bigCol][row][col].setPrefSize(h/9, h/9);
						btn[bigRow][bigCol][row][col].setVisible(true);
						btn[bigRow][bigCol][row][col].setOpacity(0);
					
						btn[bigRow][bigCol][row][col].setOnAction(e -> handle(e));
					
					
						btns.add(btn[bigRow][bigCol][row][col], (int)((col*h/9)+(bigCol*h/3)), (int)((row*h/9)+(bigRow*h/3)));
					
								
		}
		
		
		
		leftLine.toFront(); rightLine.toFront(); topLine.toFront(); botLine.toFront();
		leftLine.setStrokeWidth(2); rightLine.setStrokeWidth(2); topLine.setStrokeWidth(2); botLine.setStrokeWidth(2);
		
		
		primaryStage.setTitle("Mega TicTacToe");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
    
	
	public void handle(ActionEvent e) {
		count++;
		
		Cell temp = new Cell();
		temp = (Cell)e.getSource();
		int br = temp.getBR();
		int bc = temp.getBC();
		int r = temp.getR();
		int c = temp.getC();
		
		for(int bigR = 0; bigR < btn.length; bigR++) 
			for(int bigC = 0; bigC < btn.length; bigC++) 
				for(int row1 = 0; row1 < btn.length; row1++) 
					for(int col1 = 0; col1 < btn.length; col1++) {
						
						if(bigScore[r][c] == 0) {
							if(bigR == r && bigC == c) {
								if(score[bigR][bigC][row1][col1] != 0) 
									btn[bigR][bigC][row1][col1].setDisable(true);
								else
									btn[bigR][bigC][row1][col1].setDisable(false);
							}
							else
								btn[bigR][bigC][row1][col1].setDisable(true);
						}
						else {
							if(bigR == r && bigC == c) {
								btn[bigR][bigC][row1][col1].setDisable(true);
							}
							else if(score[bigR][bigC][row1][col1] != 0) {
								btn[bigR][bigC][row1][col1].setDisable(true);
							}
							else if(bigScore[bigR][bigC] != 0) {
								btn[bigR][bigC][row1][col1].setDisable(true);
							}
							else {
								btn[bigR][bigC][row1][col1].setDisable(false);
							}	
						}
		
		}
		btn[br][bc][r][c].setDisable(true);		
			
		
		
		
		if(count % 2 == 0) {
			score[br][bc][r][c] = 1;
			crcs[br][bc][r][c].setVisible(true);
		}
		else {
			score[br][bc][r][c] = 2;
			exes[br][bc][r][c].vis(true);
		}
		
		if(checkScore(br, bc) == 1) {
			bigScore[br][bc] = 1;
			largeCrc[br][bc].setVisible(true);
		}
		if(checkScore(br, bc) == 2) {
			bigScore[br][bc] = 2;
			largeX[br][bc].vis(true);
		}
		
		if(bigCheck() == 1) {
			System.out.println("O's win!");
		}
		if(bigCheck() == 2) {
			System.out.println("X's win!");
		}
	}	
	
	
	public int checkScore(int cRow, int cCol) {
		if(score[cRow][cCol][0][0] == score[cRow][cCol][0][1] && score[cRow][cCol][0][0] == score[cRow][cCol][0][2] && score[cRow][cCol][0][0] == 1 ||
				score[cRow][cCol][1][0] == score[cRow][cCol][1][1] && score[cRow][cCol][1][0] == score[cRow][cCol][1][2] && score[cRow][cCol][1][0] == 1 ||
				score[cRow][cCol][2][0] == score[cRow][cCol][2][1] && score[cRow][cCol][2][0] == score[cRow][cCol][2][2] && score[cRow][cCol][2][0] == 1 ||
				score[cRow][cCol][0][0] == score[cRow][cCol][1][0] && score[cRow][cCol][0][0] == score[cRow][cCol][2][0] && score[cRow][cCol][0][0] == 1 ||
				score[cRow][cCol][0][1] == score[cRow][cCol][1][1] && score[cRow][cCol][0][1] == score[cRow][cCol][2][1] && score[cRow][cCol][0][1] == 1 ||
				score[cRow][cCol][0][2] == score[cRow][cCol][1][2] && score[cRow][cCol][0][2] == score[cRow][cCol][2][2] && score[cRow][cCol][0][2] == 1 ||
				score[cRow][cCol][0][0] == score[cRow][cCol][1][1] && score[cRow][cCol][0][0] == score[cRow][cCol][2][2] && score[cRow][cCol][0][0] == 1 ||
				score[cRow][cCol][0][2] == score[cRow][cCol][1][1] && score[cRow][cCol][0][2] == score[cRow][cCol][2][0] && score[cRow][cCol][0][2] == 1)		
			return 1;
		else if(score[cRow][cCol][0][0] == score[cRow][cCol][0][1] && score[cRow][cCol][0][0] == score[cRow][cCol][0][2] && score[cRow][cCol][0][0] == 2 ||
				score[cRow][cCol][1][0] == score[cRow][cCol][1][1] && score[cRow][cCol][1][0] == score[cRow][cCol][1][2] && score[cRow][cCol][1][0] == 2 ||
				score[cRow][cCol][2][0] == score[cRow][cCol][2][1] && score[cRow][cCol][2][0] == score[cRow][cCol][2][2] && score[cRow][cCol][2][0] == 2 ||
				score[cRow][cCol][0][0] == score[cRow][cCol][1][0] && score[cRow][cCol][0][0] == score[cRow][cCol][2][0] && score[cRow][cCol][0][0] == 2 ||
				score[cRow][cCol][0][1] == score[cRow][cCol][1][1] && score[cRow][cCol][0][1] == score[cRow][cCol][2][1] && score[cRow][cCol][0][1] == 2 ||
				score[cRow][cCol][0][2] == score[cRow][cCol][1][2] && score[cRow][cCol][0][2] == score[cRow][cCol][2][2] && score[cRow][cCol][0][2] == 2 ||
				score[cRow][cCol][0][0] == score[cRow][cCol][1][1] && score[cRow][cCol][0][0] == score[cRow][cCol][2][2] && score[cRow][cCol][0][0] == 2 ||
				score[cRow][cCol][0][2] == score[cRow][cCol][1][1] && score[cRow][cCol][0][2] == score[cRow][cCol][2][0] && score[cRow][cCol][0][2] == 2)		
			return 2;
		else
			return 0;
	}
	
	public int bigCheck() {
		if(bigScore[0][0] == bigScore[0][1] && bigScore[0][0] == bigScore[0][2] && bigScore[0][0] == 1 ||
				bigScore[1][0] == bigScore[1][1] && bigScore[1][0] == bigScore[1][2] && bigScore[1][0] == 1 ||
				bigScore[2][0] == bigScore[2][1] && bigScore[2][0] == bigScore[2][2] && bigScore[2][0] == 1 ||
				bigScore[0][0] == bigScore[1][0] && bigScore[0][0] == bigScore[2][0] && bigScore[0][0] == 1 ||
				bigScore[0][1] == bigScore[1][1] && bigScore[0][1] == bigScore[2][1] && bigScore[0][1] == 1 ||
				bigScore[0][2] == bigScore[1][2] && bigScore[0][2] == bigScore[2][2] && bigScore[0][2] == 1 ||
				bigScore[0][0] == bigScore[1][1] && bigScore[0][0] == bigScore[2][2] && bigScore[0][0] == 1 ||
				bigScore[0][2] == bigScore[1][1] && bigScore[0][2] == bigScore[2][0] && bigScore[0][2] == 1)		
			return 1;
		if(bigScore[0][0] == bigScore[0][1] && bigScore[0][0] == bigScore[0][2] && bigScore[0][0] == 2 ||
				bigScore[1][0] == bigScore[1][1] && bigScore[1][0] == bigScore[1][2] && bigScore[1][0] == 2 ||
				bigScore[2][0] == bigScore[2][1] && bigScore[2][0] == bigScore[2][2] && bigScore[2][0] == 2 ||
				bigScore[0][0] == bigScore[1][0] && bigScore[0][0] == bigScore[2][0] && bigScore[0][0] == 2 ||
				bigScore[0][1] == bigScore[1][1] && bigScore[0][1] == bigScore[2][1] && bigScore[0][1] == 2 ||
				bigScore[0][2] == bigScore[1][2] && bigScore[0][2] == bigScore[2][2] && bigScore[0][2] == 2 ||
				bigScore[0][0] == bigScore[1][1] && bigScore[0][0] == bigScore[2][2] && bigScore[0][0] == 2 ||
				bigScore[0][2] == bigScore[1][1] && bigScore[0][2] == bigScore[2][0] && bigScore[0][2] == 2)		
			return 2;
		return 0;
	}
	
	
	public static void main(String args[]){   
		launch(args);      
	}
	

}
